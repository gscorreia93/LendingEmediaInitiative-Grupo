package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;

import javax.naming.OperationNotSupportedException;

import model.lendables.Lendable;
import model.rentals.Rental;
import model.rentals.RentalFactory;
import model.shelves.NormalShelf;
import model.shelves.Shelf;
import model.shelves.Shelves;
import model.shelves.criteria.ICriterium;

public class ShelvesFacade implements IShelvesFacade {

	private Shelves shelves;
	private RentalFactory f;

	public ShelvesFacade() {

		NormalShelf normalShelf = new NormalShelf("myRentals");
		shelves = new Shelves(normalShelf);
		f = new RentalFactory();
	}

	@Override
	public boolean addNormalShelf(String name) {
		
		return shelves.addNormalShelf(name);
	}

	@Override
	public boolean addSmartShelf(String name, ICriterium criteria) {

		return shelves.addSmartShelf(name, criteria);
	}

	@Override
	public void removeShelf(String name) {

		shelves.removeShelf(name);
	}

	@Override
	public Iterable<String> getShelves() {
		
		List<String> ret = new ArrayList<String>();
		Iterator<Shelf> x = shelves.iterator();

		while (x.hasNext())
			ret.add(x.next().getName());

		return ret;
	}

	@Override
	public boolean addRental(EMedium rental) {

		return shelves.addRental(f.createRental(rental));
	}

	@Override
	public void returnRental(EMedium rental) {

			if(rental instanceof Rental){
				try {
					shelves.getRentals().removeRental((Rental)rental);
				} catch (OperationNotSupportedException e) {
					//DO NOTHING
				}
			} else if(rental instanceof Lendable){
				try {
					shelves.getRentals().removeRental(f.createRental(rental));
				} catch (OperationNotSupportedException e) {
					//DO NOTHING
				}
			}
	
	}

	@Override
	public boolean addShelfRental(String shelfName, EMedium rental)
			throws OperationNotSupportedException {

		return shelves.addRentalToShelf(shelfName, f.createRental(rental));
	}

	@Override
	public void removeRental(String shelfName, EMedium rental)
			throws OperationNotSupportedException {

		shelves.removeRentalFromShelf(shelfName, f.createRental(rental));
	}

	@Override
	public Iterable<? extends EMedium> getRentals() {

		return shelves.getRentals();
	}

	@Override
	public Iterable<? extends EMedium> getShelfRentals(String shelfName) {

		return shelves.getShelfRentals(shelfName);
	}

	@Override
	public boolean isRented(EMedium rental) {

		return shelves.isRented(f.createRental(rental));
	}

	@Override
	public boolean isRentalExpired(EMedium rental) {

		return shelves.isExpired((Rental)(rental));
	}

	@Override
	public void addShelfCollectionObserver(Observer observer) {

		shelves.addShelfCollectionObserver(observer);
	}

	@Override
	public void removeShelfCollectionObserver(Observer observer) {

		shelves.removeShelfCollectionObserver(observer);
	}

	@Override
	public void addRentalCollectioObserver(String shelfName, Observer observer) {

		shelves.addRentalCollectionObserver(shelfName, observer);
	}

	@Override
	public void removeRentalCollectionObserver(String shelfName,
			Observer observer) {

		shelves.removeRentalCollectionObserver(shelfName, observer);
	}
}
