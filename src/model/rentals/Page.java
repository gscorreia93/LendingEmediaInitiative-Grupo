package model.rentals;

import java.util.ArrayList;
import java.util.List;

public class Page {
	
	private boolean bookmark;
	private int pageNum;
	private List<String> annotations;
	
	public Page(int pageNum){
	
		this.pageNum = pageNum;
		annotations = new ArrayList<String>();
	}
	
	public void addAnnotation(String text){
		
		annotations.add(text);
	}
	
	public Iterable<String> getAnnotations(){
		
		return annotations;
	}
	
	public String getAnnotationText(int n){
		
		return annotations.get(n);
	}
	
	public boolean isBookmark(){
		
		return bookmark;
	}
	
	public void toggleBookmark(){
		
		bookmark = !bookmark;
	}
	
	public int getPageNum(){
		
		return pageNum;
	}
	
	public void removeAnnotation(int n){
		
		annotations.remove(n);
	}
	
	public boolean hasAnnotations(){
		
		return !annotations.isEmpty();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pageNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		if (pageNum != other.pageNum)
			return false;
		return true;
	}
	
	
}