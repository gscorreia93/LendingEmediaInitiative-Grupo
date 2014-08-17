package model.shelves.criteria;

import model.rentals.Rental;

public class AndCriterium extends CompoundCriterium {
	
	public AndCriterium(ICriterium left, ICriterium right){
		
		super(left, right);
	}

	@Override
	public boolean satisfies(Rental rental) {
		
		return (left.satisfies(rental) && right.satisfies(rental));
	}
}
