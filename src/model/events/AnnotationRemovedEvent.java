package model.events;

import model.EMedium;
import model.rentals.Page;

public class AnnotationRemovedEvent extends AnnotationEvent {
	
	private int annotationNum;

	public AnnotationRemovedEvent(EMedium medium, Page page, int annotationNum) {
		
		super(medium, page);
		this.annotationNum = annotationNum;
	}
	
	public int getAnnotationNum() {
		
		return annotationNum;
	}
}
