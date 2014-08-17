package model.shelves.criteria;

import model.rentals.Rental;

public class OrCriterium extends CompoundCriterium {

	public OrCriterium(ICriterium left, ICriterium right){

		super(left, right);
	}

	@Override
	public boolean satisfies(Rental rental) {

		return (left.satisfies(rental) || right.satisfies(rental));
	}
}
