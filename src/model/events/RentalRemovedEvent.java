package model.events;

import model.EMedium;

public class RentalRemovedEvent extends RentalCollectionEvent {

	public RentalRemovedEvent(EMedium eMedium) {
	
		super(eMedium);
	}
}
