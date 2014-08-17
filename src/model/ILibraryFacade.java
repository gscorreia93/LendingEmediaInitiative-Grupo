package model;

import java.util.Observer;


/**
 * @author fmartins
 *
 * The façade for the operations with the e-media library, 
 * a proxy to the server data.
 */
public interface ILibraryFacade {
	
	/**
	 * @return The media available in the library
	 */
	public Iterable<? extends EMedium> getEMedia ();

	/**
	 * @param type The type of e-medium to be added to the library 
	 * 			(just for testing purposes) 
	 * @param lendableProperties The properties of the e-medium to be added
	 * 			to the library
	 * @return	if the lendable was added to the library
	 */
	public boolean addLendable(EMediumType type, EMediumPropertiesData lendableProperties);

	
	/**
	 * @return The last e-medium added to the library
	 */
	public EMedium getLastAddedLendable();

	
	/**
	 * @param eMedium The e-medium to be checked if it can be rented
	 * @return	if the e-medium can be rented
	 */
	public boolean canBeRent(EMedium eMedium);
	
	
	/**
	 * @param eMedium The e-medium to rent 
	 * @requires canBeRent(eMedium)
	 */
	public void rent(EMedium eMedium);
	
	public void addObserver(Observer observer);
}
