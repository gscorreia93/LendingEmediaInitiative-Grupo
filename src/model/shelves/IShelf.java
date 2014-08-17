package model.shelves;

import javax.naming.OperationNotSupportedException;

import model.rentals.Rental;

/**
 * @author fmartins
 *
 * A Shelf. A shelf has a name and a collection of rentals that can
 * be added to, or removed from, the shelf.
 * 
 * A shelf must be iterable in its contents (the rentals) and comparable.
 * Two shelves are comparable in lexicographical order. 
 */
public interface IShelf extends Iterable<Rental>, Comparable<IShelf> {
	
	/**
	 * @return The name of the shelf.
	 */
	public String getName();
	
		
	/**
	 * @param rental The rental to be added to the shelf.
	 * @return	if the rental was added to the shelf.
	 * @throws OperationNotSupportedException Raised whenever 
	 * 			the shelf does not allow rentals to be added 
	 * 			manually to the shelf.
	 * 
	 * Adds a new rental to the shelf. If there is already an identical
	 * rental in the shelf, the method returns false. If the shelf does 
	 * not allow the insertion of new rentals, it raises the 
	 * OperationNotSupportedException.
	 */
	public boolean addRental (Rental rental)
		throws OperationNotSupportedException;
	
	
	/**
	 * @param rental The rental to be removed from the shelf.
	 * @return If the rental was removed from the shelf.
	 * @throws OperationNotSupportedException Raised whenever 
	 * 			the shelf does not allow rentals to be removed
	 * 			manually from the shelf.
	 */
	public boolean removeRental (Rental rental)
		throws OperationNotSupportedException;
}
