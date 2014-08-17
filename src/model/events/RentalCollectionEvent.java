package model.events;

import model.EMedium;

public class RentalCollectionEvent {
	
	EMedium eMedium;
	
	public RentalCollectionEvent(EMedium eMedium){
		
		this.eMedium = eMedium;
	}
	
	public EMedium getEMedium(){
		
		return eMedium;
	}
}
