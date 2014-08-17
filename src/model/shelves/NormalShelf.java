package model.shelves;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import model.events.RentalAddedEvent;
import model.events.RentalRemovedEvent;
import model.rentals.Rental;

public class NormalShelf extends Shelf {

	private List<Rental> shelf;

	public NormalShelf(String name) {

		super(name);
		this.shelf = new ArrayList<Rental>();
	}

	@Override
	public boolean addRental(Rental rental)
			throws OperationNotSupportedException {

		if(!shelf.contains(rental)){	
			shelf.add(rental);
			setChanged();
			notifyObservers(new RentalAddedEvent(rental));

			return true;
		}else{
			shelf.get(shelf.indexOf(rental)).renewRental();
		}
		return false;
	}

	@Override
	public boolean removeRental(Rental rental)
			throws OperationNotSupportedException {

		if(shelf.contains(rental)){
			shelf.get(shelf.indexOf(rental)).endRental();
			setChanged();
			notifyObservers(new RentalRemovedEvent(rental));

			return true;
		}else
			return false;
	}

	@Override
	public Iterator<Rental> iterator() {
		int index = 0;
		List <Rental> aux = new ArrayList<Rental>();
		
		while(index < shelf.size()){
			if(!shelf.get(index).isExpired())
				aux.add(shelf.get(index));
			index++;
		}
		return aux.iterator();
	}

	public void endRental(Rental rental){

		rental.endRental();
	}
}
