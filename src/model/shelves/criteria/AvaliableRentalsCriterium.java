package model.shelves.criteria;

import model.rentals.Rental;

public class AvaliableRentalsCriterium implements ICriterium{

	@Override
	public boolean satisfies(Rental rental) {

		return rental.isExpired() ? true : false;
	}
}
