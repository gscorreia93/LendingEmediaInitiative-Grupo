package model.shelves;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.naming.OperationNotSupportedException;

import model.events.ShelfAddedEvent;
import model.events.ShelfRemovedEvent;
import model.rentals.Rental;
import model.shelves.criteria.ICriterium;

public class Shelves extends Observable implements IShelves {

	private Map<String, Shelf> shelves;
	private NormalShelf myRentals;

	public Shelves(NormalShelf myRentals){

		this.myRentals = myRentals;
		shelves = new HashMap<String, Shelf>();
	}

	@Override
	public boolean addNormalShelf(String name) {
		
		setChanged();
		notifyObservers(new ShelfAddedEvent(name));
		NormalShelf shelf = new NormalShelf(name);		
		shelves.put(name, shelf);

		return true;
	}

	@Override
	public boolean addSmartShelf(String name, ICriterium criterion) {

		setChanged();
		notifyObservers(new ShelfAddedEvent(name));
		SmartShelf smartShelf = new SmartShelf(name, myRentals ,criterion);		
		shelves.put(name, smartShelf);

		return true;
	}

	@Override
	public void removeShelf(String name) {
		
		setChanged();
		notifyObservers(new ShelfRemovedEvent(name));		
		shelves.remove(name);
	}

	@Override
	public boolean isTheRentalShelf(String name) {

		return myRentals.getName().equals(name);
	}

	@Override
	public boolean addRental(Rental rental) {

		try {
			myRentals.addRental(rental);
		} catch (OperationNotSupportedException e) {
			return false;
		}	

		return true;
	}

	@Override
	public boolean addRentalToShelf(String target, Rental rental)
			throws OperationNotSupportedException {

		if(shelves.containsKey(target)){
			shelves.get(target).addRental(rental);
			return true;
		}else
			return false;
	}

	@Override
	public void removeRentalFromShelf(String name, Rental rental)
			throws OperationNotSupportedException {

		if(shelves.containsKey(name))
			shelves.remove(name).removeRental(rental);
	}

	@Override
	public NormalShelf getRentals() {

		return myRentals;
	}

	@Override
	public Iterable<Rental> getShelfRentals(String shelfName) {

		return shelves.get(shelfName);
	}

	@Override
	public boolean isRented(Rental rental) {

		return !rental.isExpired();
	}

	@Override
	public boolean isExpired(Rental rental) {

		return rental.isExpired();
	}

	@Override
	public void addShelfCollectionObserver(Observer observer) {

		addObserver(observer);
	}

	@Override
	public void removeShelfCollectionObserver(Observer observer) {

		deleteObserver(observer);
	}

	@Override
	public void addRentalCollectionObserver(String shelfName, Observer observer) {
		
		shelves.get(shelfName).addObserver(observer);
	}

	@Override
	public void removeRentalCollectionObserver(String shelfName,
			Observer observer) {
	
		shelves.get(shelfName).deleteObserver(observer);
	}

	@Override
	public Iterator<Shelf> iterator() {

		return shelves.values().iterator();
	}
}
