package model.shelves;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.naming.OperationNotSupportedException;

import model.rentals.Rental;
import model.shelves.criteria.ICriterium;

public class SmartShelf extends Shelf {

	private Shelf smartShelf;
	private ICriterium criterium;

	public SmartShelf(String name, Shelf myRentals, ICriterium criterium) {

		super(name);
		smartShelf = myRentals;
		this.criterium = criterium;
	}

	@Override
	public boolean addRental(Rental rental)
			throws OperationNotSupportedException {

		throw new OperationNotSupportedException();
	}

	@Override
	public boolean removeRental(Rental rental)
			throws OperationNotSupportedException {

		throw new OperationNotSupportedException();
	}

	@Override
	public Iterator<Rental> iterator() {

		List<Rental> aux = new ArrayList<Rental>();
		
		for (Rental rental : smartShelf) 
			if (criterium.satisfies(rental)) 
				aux.add(rental);
		
		return aux.iterator();
	}
}	
