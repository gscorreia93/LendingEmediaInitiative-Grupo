package model.events;

import model.EMedium;
import model.rentals.Page;

public class AnnotationAddedEvent extends AnnotationEvent {
	
	private Page page;
	
	public AnnotationAddedEvent(EMedium medium, Page page) {
	
		super(medium, page);
		this.page=page;
	}

	public String getAnnotationText(){
		
		return page.getAnnotations().toString();
	}
}
