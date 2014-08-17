package model.events;

import java.util.Iterator;
import model.EMedium;
import model.rentals.Page;

public abstract class AnnotationEvent extends EMediumEvent {
	
	private Page page;
	
	public AnnotationEvent(EMedium medium, Page page) {
		
		super(medium, page);
		this.page = page;
	}

	public int getPageNum(){
		
		return page.getPageNum();
	}
	
	public int getAnnotationsNum(){
		
		Iterator<String> aux = page.getAnnotations().iterator();
		
		int count = 0;
		
		while(aux.hasNext()){
			count++;
			aux.next();
		}
		return count;
	}
	
	public boolean hasAnnotations(){
		
		return page.hasAnnotations();
	}
}
