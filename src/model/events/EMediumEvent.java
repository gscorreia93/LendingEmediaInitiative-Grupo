package model.events;

import model.EMedium;
import model.rentals.Page;

public abstract class EMediumEvent {
	
	private EMedium medium;
	private Page page;
	
	public EMediumEvent(EMedium medium, Page page){
		
		this.medium = medium;
		this.page = page;
	}
	
	public EMedium getEMedium(){
		
		return medium;
	}
	
	public int getPageNum(){
		
		return page.getPageNum();
	}
}
