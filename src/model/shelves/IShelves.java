package model.shelves;

import java.util.Observer;

import javax.naming.OperationNotSupportedException;

import model.rentals.Rental;
import model.shelves.criteria.ICriterium;

/**
 * @author fmartins
 *
 * The interface with a collection of shelves: the 
 * shelfs added manually by the user.
 */
public interface IShelves extends Iterable<Shelf> {

	/**
	 * @param name The name of the normal shelf to be added.
	 * @return If the shelf was added successfully.
	 * 
	 * Adds a user normal shelf to the system.
	 */
	public boolean addNormalShelf(String name);
	
	
	/**
	 * @param name The name the smart shelf to be added.
	 * @param criterion The criterion that e-medium should satisfy 
	 * 			in order to be a member of the shelf.
	 * @return if the shelf was added successfully.
	 * 
	 * Adds a user smart shelf to the system given its name and
	 * the criterion its rentals must satisfy in order to be part
	 * of the shelf automatically.
	 */
	public boolean addSmartShelf(String name, ICriterium criterion);

	
	/**
	 * @param name The name of the shelf to be removed.
	 * 
	 * Removes a shelf with name from the system.
	 */
	public void removeShelf(String name);

	
	/**
	 * @param name The name of the shelf to check if it is the rental shelf
	 * @return if the shelf *name* is the rental shelf
	 */
	public boolean isTheRentalShelf (String name);

	
	/**
	 * @param rental The rental to add to the Rental shelf
	 * @return if the rental was added to the rental shelf
	 */
	public boolean addRental(Rental rental);
	
	
	/**
	 * @param target The self to add the rental to
	 * @param rental The rental to be added to the *target* shelf
	 * @return if the *rental* was added to the *target* shelf 
	 * @throws OperationNotSupportedException In case the shelf does not support
	 * 			the adding of rentals.
	 */
	public boolean addRentalToShelf(String target, Rental rental) 
			throws OperationNotSupportedException;

	
	/**
	 * @param name The name of the shelf to remove the *rental* from
	 * @param rental The rental to be removed from the *name* shelf
	 * @throws OperationNotSupportedException In case the shelf does not support
	 * 			the removal of rentals.
	 */
	public void removeRentalFromShelf(String name, Rental rental) 
			throws OperationNotSupportedException;
	
	
	/**
	 * @return The rentals from the rentals shelf
	 */
	public Iterable<Rental> getRentals ();
	

	/**
	 * @param shelfName The shelf name to get the rentals from
	 * @return The rentals in the *shelfName* self.
	 */
	public Iterable<Rental> getShelfRentals (String shelfName);

	
	/**
	 * @param rental The rental to check if it has ever been rented by
	 * 			this user.
	 * @return if the rental was ever rented by this user
	 */
	public boolean isRented(Rental rental);

	
	/**
	 * @param rental The rental to check if it is expired
	 * @return if the rental (was rented before) and is now expired
	 */
	public boolean isExpired(Rental rental);
	
	
	/**
	 * @param observer The observer to be added
	 * 
	 * Adds an observer to be notified when shelves are added or removed
	 */
	public void addShelfCollectionObserver(Observer observer);
	
	
    /**
     * @param observer The observer to be removed
     * 
     * Removes an observer from the collection of shelves
     */
    public void removeShelfCollectionObserver(Observer observer);
    
    
    /**
     * @param shelfName The name of the shelf to be observed
     * @param observer The observer to be added
     * 
     * Adds an observer to the *shelfName* shelf that gets notified whenever
     * a rental is added or removed in the shelf.
     */
    public void addRentalCollectionObserver(String shelfName, Observer observer);

    
    /**
     * @param shelfName The name of the shelf to remove the observer
     * @param observer The observer to be removed
     * 
     * Remove an observer the is listening to events happening at *shelfName* shelf
     */
    public void removeRentalCollectionObserver(String shelfName, Observer observer);    
}
