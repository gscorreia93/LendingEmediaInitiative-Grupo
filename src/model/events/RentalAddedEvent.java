package model.events;

import model.EMedium;

public class RentalAddedEvent extends RentalCollectionEvent {

	public RentalAddedEvent(EMedium eMedium) {
	
		super(eMedium);
	}
}
