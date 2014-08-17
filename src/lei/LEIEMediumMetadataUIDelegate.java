package lei;

import java.util.LinkedList;
import java.util.Observable;

import model.EMedium;
import model.events.AnnotationAddedEvent;
import model.events.AnnotationRemovedEvent;
import model.events.BookmarkToggleEvent;
import model.rentals.BookRental;
import model.rentals.NoSuchPageException;
import model.rentals.Rental;
import controller.delegates.EMediumMetadataUIDelegate;

/**
 * The document's metadata ui delegate default implementation
 * 
 * @author fmartins
 *
 */
public class LEIEMediumMetadataUIDelegate extends EMediumMetadataUIDelegate {
	
	private EMedium document;
	
	public void setEMedium (EMedium document) {
		
		this.document = document;
	}
	
	@Override
	public void deleteObservers() {
		
		document.deleteObserver(this);
	}
	
	@Override
	public void addObservers(){
		
		document.addObserver(this);
	}

	@Override
	public Iterable<Integer> getDocumentBookmarks() {
		
		if(document instanceof BookRental)
			return ((BookRental) document).getBookmarks();
		
		return new LinkedList<Integer>();
	}

	@Override
	public Iterable<String> getPageAnnotations(int pageNum) {
		
		if(document instanceof BookRental)
			return ((BookRental) document).getAnnotations(pageNum);
		else if(document instanceof Rental)
			return ((Rental) document).getAnnotations();
		
		return new LinkedList<String>();
	}

	@Override
	public String getDocumentTitle() {
		
		return document.getTitle();
	}

	@Override
	public void addAnnotation(int pageNum, String text) {
		
		if(document instanceof BookRental)
			((BookRental) document).addAnnotation(pageNum, text);
	}

	@Override
	public void removeAnnotation(int pageNum, int annotNum) {

		if(document instanceof BookRental)
			try {
				((BookRental) document).removeAnnotation(pageNum, annotNum);
			} catch (NoSuchPageException e) {
				System.out.println(e.toString());
			}
	}

	@Override
	public void toggleBookmark(int pageNum) {
		
		if(document instanceof BookRental)
			((BookRental) document).toggleBookmark(pageNum);
	}

	@Override
	public String getAnnotationText(int pageNum, int annotNum) {
		
		if(document instanceof BookRental)
			try {
				return ((BookRental) document).getAnnotationText(pageNum, annotNum);
			} catch (NoSuchPageException e) {
				return e.toString();
			}
		
		return "";
	}

		@Override
		public void update(Observable arg0, Object hint) {
			
			if(hint instanceof BookmarkToggleEvent){
				if(((BookmarkToggleEvent) hint).isBookmarked())
					addBookmarkTreeNode(((BookmarkToggleEvent) hint).getPageNum());
				else
					addBookmarkTreeNode(((BookmarkToggleEvent) hint).getPageNum());
				
			}else if(hint instanceof AnnotationRemovedEvent)
				removeAnnotationTreeNode(((AnnotationRemovedEvent) hint).getAnnotationNum());
			else if(hint instanceof AnnotationAddedEvent)
				addAnnotationTreeNode(((AnnotationAddedEvent) hint).getAnnotationText());
		}
}
