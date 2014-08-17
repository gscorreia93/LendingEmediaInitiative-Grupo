package model.events;

import model.EMedium;
import model.rentals.Page;

public class BookmarkToggleEvent extends EMediumEvent {
	
	private Page page;
	
	public BookmarkToggleEvent(EMedium medium, Page page) {
		
		super(medium, page);
		this.page = page;
	}

	public boolean isBookmarked(){
		
		return page.isBookmark();
	}
}
