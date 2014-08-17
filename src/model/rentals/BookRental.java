package model.rentals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.events.AnnotationRemovedEvent;
import model.events.BookmarkToggleEvent;
import model.lendables.Lendable;

public class BookRental extends Rental {

	private Map<Integer, Page> pages;
	private List<Integer> bookmarks;
	private int lastPageVisited;

	public BookRental(Lendable book) {
		
		super(book);
		pages = new HashMap<Integer, Page>();
		bookmarks = new ArrayList<Integer>();
		lastPageVisited = 1;
	}

	public void addAnnotation(int pageNum, String text) {
	
		Page pagina = new Page(pageNum);
		
		if(pages.containsKey(pageNum))
			pages.get(pageNum).addAnnotation(text);			
		else{
			pages.put(pageNum, pagina);
			pages.get(pageNum).addAnnotation(text);
		}
	}

	public void removeAnnotation(int pageNum, int n) throws NoSuchPageException {
		
		if(pages.containsKey(pageNum)){
			setChanged();
			notifyObservers(new AnnotationRemovedEvent(this, pages.get(pageNum), n));
			pages.get(pageNum).removeAnnotation(n);
		}else
			throw new NoSuchPageException();
	}

	public Iterable<String> getAnnotations(int pageNum) {
		
		if(pages.containsKey(pageNum))
			return pages.get(pageNum).getAnnotations();
		else
			return new LinkedList<String>();
	}

	public String getAnnotationText(int pageNum, int n) throws NoSuchPageException {
		
		if(pages.containsKey(pageNum))
			return pages.get(pageNum).getAnnotationText(n);
		else
			throw new NoSuchPageException();
	}

	public boolean hasAnnotations(int n) {

		if(pages.containsKey(n))
			return pages.get(n).hasAnnotations();
		else
			return false;
	}

	public boolean isBookmarked(int n) throws NoSuchPageException{

		if(pages.containsKey(n))
			return pages.get(n).isBookmark();
		else
			return false;
	}

	public List<Integer> getBookmarks() {
		
		return bookmarks;
	}

	public void toggleBookmark(int n) {		
		
		if(bookmarks.contains(n)){
			setChanged();
			pages.get(n).toggleBookmark();
			notifyObservers(new BookmarkToggleEvent(this, pages.get(n)));
			bookmarks.remove(bookmarks.indexOf(n));
		}else{
			Page pagina = new Page(n);
			pages.put(n, pagina);
			bookmarks.add(n);
			
			setChanged();
			pages.get(n).toggleBookmark();
			notifyObservers(new BookmarkToggleEvent(this, pages.get(n))); 
		}
	}

	public int getLastPageVisited() {
		
		return lastPageVisited;
	}

	public void setLastPageVisited(int lastPageVisited){

		this.lastPageVisited = lastPageVisited;
	}
}
