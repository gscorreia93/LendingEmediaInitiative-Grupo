package model;

import java.util.Observer;

import javax.naming.OperationNotSupportedException;

import model.shelves.criteria.ICriterium;

/**
 * @author fmartins
 *
 *	The façade with the operations on shelves. 
 */
public interface IShelvesFacade {
	
		/**
		 * @param name The name of the self to be added
		 * @return	if the shelf was added successfully
		 * 
		 * Adds a user shelf to the system. 
		 */
		public boolean addNormalShelf (String name);
		
		
		/**
		 * @param name The name of the self to be added
		 * @param criteria	The criteria for defining e-media of the self
		 * @return if the self was added successfully
		 * 
		 * Adds a user smart self to the system.
		 */
		public boolean addSmartShelf (String name, ICriterium criteria);
		
		
		/**
		 * @param name The name of the self to be removed
		 * 
		 * Removes a shelf from the system
		 */
		public void removeShelf(String name);

		
		/**
		 * @return The shelves currently in the system
		 */
		public Iterable<String> getShelves ();

		
		/**
		 * @param rental The rental to be added to the users rental shelf.
		 * @return If the rental was added successfully.
		 * @requires !isRented(rental)
		 * 
		 * Rents an e-medium. The rental is going to be added to the 
		 * shelf that registers all the rentals of the user (e.g., My Rentals).
		 */
		public boolean addRental(EMedium rental);

		
		/**
		 * @param rental The rental to be returned.
		 * @requires isRented(rental)
		 * 
		 * Returns an e-medium. From this moment on, the rental is no longer
		 * accessible for reading, but the user can still access its notes
		 * on the rental. This operation should release the e-medium license
		 * so that another user may rent the item again. After returning a 
		 * rental, the item should be disappear from the smart selves, but not 
		 * from normal selves, where the user explicitly added the rental, nor
		 * from the My Rentals shelf.    
		 */
		public void returnRental(EMedium rental);
		
		
		/**
		 * @param shelfName The name of the shelf where the rental 
		 * 			is going to be added-
		 * @param rental The rented to be added to the self.
		 * @return If the rental was added to the shelf.
		 * @throws OperationNotSupportedException When the self does not
		 * 			support the add operation.
		 * 
		 * Adds a rental to a shelf. This operation allows users to manually
		 * organise their e-media. An e-medium cannot be added to a smart shelf,
		 * nor to a normal shelf where the item was already added. In the former
		 * case the operation raises an exception; in the latter case, the
		 * operation returns false. 
		 */
		public boolean addShelfRental(String shelfName, EMedium rental) 
				throws OperationNotSupportedException;

		
		/**
		 * @param selfName The name of the self where the e-medium 
		 * 			will be removed.
		 * @param rental The e-medium rental to be removed 
		 * @throws OperationNotSupportedException When the remove operation
		 * 			is not possible.
		 * Removes a rental from a user shelf. It is not possible
		 * to remove rental from smart selves. The e-media of a smart shelf
		 * is determined by the self's criterium and not by user ad hoc
		 * intervention.  
		 */
		public void removeRental (String selfName, EMedium rental) 
				throws OperationNotSupportedException;

		
		/**
		 * @return The rentals in the My Rentals shelf.
		 */
		public Iterable<? extends EMedium> getRentals ();


		/**
		 * @param shelfName The name of a user shelf
		 * @return The rentals in a user shelf, either normal or smart.
		 */
		public Iterable<? extends EMedium> getShelfRentals (String shelfName);
		
		/**
		 * @param rental The e-medium to check if it is rented
		 * @return if the e-medium has ever been rented.
		 */
		public boolean isRented(EMedium rental);
		
		
		/**
		 * @param rental The e-medium to check if its rental is experied
		 * @return if the e-medium rental is expired.  
		 * @requires isRented(rental)
		 * 
		 * Returns the status of the rental. In case a rental is expired it 
		 * means that the e-medium cannot be visualised.  
		 */
		public boolean isRentalExpired(EMedium rental);
		
		
		/**
		 * @param observer The observer to be added.
		 * 
		 * An observer that will be notified when a user self is added or removed
		 */
		public void addShelfCollectionObserver(Observer observer);

		
	    /**
	     * @param observer The observer to be removed
	     * 
	     * Removes the user collection shelf observer
	     */
	    public void removeShelfCollectionObserver(Observer observer);
	    
	    
	    /**
	     * @param shelfName The name of the shelf that the observer is interested in
	     * 			getting notifications.
	     * @param observer The observer to be added.
	     * 
	     * An observer that will be notified when an e-medium is added or removed from 
	     * the shelf with selfName.
	     */
	    public void addRentalCollectioObserver(String shelfName, Observer observer);

	    
	    /**
	     * @param shelfName The name of the self to remove the observer from.
	     * @param observer The observer to be removed.
	     * 
	     * Removes the observer from the shelf named shelfName.  
	     */
	    public void removeRentalCollectionObserver(String shelfName, Observer observer);
}
