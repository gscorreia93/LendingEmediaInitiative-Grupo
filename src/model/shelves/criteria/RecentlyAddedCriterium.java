package model.shelves.criteria;

import java.util.Date;

import model.rentals.Rental;

public class RecentlyAddedCriterium implements ICriterium {

	private long milisecs;

	public RecentlyAddedCriterium(long milisecs) {
		
		this.milisecs = milisecs;
	}
	
	@Override
	public boolean satisfies(Rental rental) {
		
		Date present = new Date();
		
		if((present.getTime() - rental.getRentalTimestamp().getTime()) < milisecs)
			return true;
				
		return false;
	}

}
