package controller.delegates;

import java.util.Observer;

import javax.naming.OperationNotSupportedException;

import model.EMedium;
import model.EMediumPropertiesData;
import view.IBookshelfUI;

/**
 * Delegate that mediates the interaction between view and the model
 * 
 * @author fmartins
 *
 */
public abstract class BookshelfUIDelegate implements Observer {
	
	/**
	 * Reference to the UI's delegate
	 */
	private IBookshelfUI bookshelf;
	
	
	/**
	 * Links the delegate back to its UI
	 * 
	 * @param bookshelf The bookshelf UI
	 */
	public void setBookshelfUI (IBookshelfUI bookshelf) {
		this.bookshelf = bookshelf;
	}
	
	
	/**
	 * Adds a tree node to the UI for a shelf with name shelfName
	 * 
	 * @param shelfName The name of the shelf to be added as a tree node
	 */
	protected void addShelfTreeNode (String shelfName) {
		bookshelf.addShelfTreeNode(shelfName);
	}
	
	
	/**
	 * Removes the selected tree node shelf from the tree of shelves
	 */
	protected void removeShelfTreeNode (String caption) {
		bookshelf.removeShelfTreeNode(caption);
	}
	
	
	/**
	 * Adds a thumbnail representing the e-medium to the e-medium's panel
	 * 
	 * @param EMedium The e-medium whose thumbnail will be added to the panel
	 */
	protected void addToEMediaPanel (String target, EMedium eMedium) {
		bookshelf.addToEMediaPanel(target, eMedium);
	}
	
	
	/**
	 * Removes the selected thumbnail from the document's panel
	 */
	protected void removeEMediumFromPanel (EMedium eMedium) {
		bookshelf.removeEMediumFromPanel (eMedium); 
	}
	
	
	/**
	 * Gets shelf's list of e-media
	 * 
	 * @param shelfName The name of the shelf to get the e-media from
	 * @return An iterable with the shelf's e-media.
	 */
	public abstract Iterable<? extends EMedium> getShelfRentals(String shelfName);
	
	public abstract Iterable<? extends EMedium> getRentals();


	/**
	 * Gets library's list of e-media
	 * 
	 * @return An iterable with the library's e-media.
	 */
	public abstract Iterable<? extends EMedium> getLibraryEMedia();

	
	/**
	 * Creates a normal shelf
	 * 
	 * @param shelfName The name of the shelf to be added
	 * @return if the shelf was added 
	 */
	public abstract boolean addNormalShelf(String shelfName);

	
	/**
	 * Removes a shelf
	 * 
	 * @param string The name of the shelf to be removed
	 */
	public abstract void removeShelf(String shelfName);

	
	/**
	 * Updates e-medium properties
	 * 
	 * @param eMedium The e-medium to be updated
	 * @param eMediumProperties The new e-medium properties
	 */
	public abstract void updateRental(EMedium eMedium, EMediumPropertiesData eMediumProperties);

		
	/**
	 * Removes an e-medium from a shelf
	 * 
	 * @param shelfName The name of the shelf to remove the e-medium from
	 * @param eMedium The e-medium to be removed
	 * @throws OperationNotSupportedException Thrown in case of a special shelf
	 */
	public abstract void removeEMediumShelf(String shelfName, EMedium eMedium) throws OperationNotSupportedException;
	
	
	/**
	 * @return The existent shelves
	 */
	public abstract Iterable<String> getShelves();

	
	/**
	 * Adds an e-medium to a shelf
	 * 
	 * @param shelfName The shelf's name
	 * @param eMedium The e-medium to be added
	 * @return if the e-medium was added to the shelf
	 * @throws OperationNotSupportedException Thrown in case of a special shelf
	 */
	public abstract boolean addEMediumShelf(String shelfName, EMedium eMedium) throws OperationNotSupportedException;

	
	/**
	 * Gets the e-media's title
	 * 
	 * @param eMedium The e-medium to query the title
	 * @return the title of the e-medium
	 */
	public abstract String getEMediumTitle(EMedium eMedium);

	
	/**
	 * Returns a rental
	 * 
	 * @param eMedia The e-medium to return
	 */
	public abstract void returnRental(EMedium eMedium);

	
	/**
	 * Removes an e-medium from the library. After removal the item  
	 * is no longer available for being lent. 
	 * 
	 * @param eMedium
	 */
	public abstract void revokeLending(EMedium eMedium);

	
	/**
	 * Adds an e-medium to the library. 
	 * 
	 * @param type The type of the e-medium.
	 * @param properties The properties of the e-medium
	 * @return if the item was added to the library. An item is not added
	 * if it is already in the library.
	 */
	public abstract boolean addEMediumLibrary(String type, EMediumPropertiesData properties);

	
	/**
	 * Rents an e-medium
	 * 
	 * @param eMedium The e-medium to be rent
	 * @return if the e-medium was rent
	 */
	public abstract boolean addRentalEMedium(EMedium eMedium);

	
	/**
	 * @param eMedium The e-medium to check if it is rent
	 * @return if the e-medium is rent
	 */
	public abstract boolean isRent(EMedium eMedium);

	
	/**
	 * @param eMedium The e-medium to check if it can be viewed
	 * @return if the e-medium can be viewed
	 */
	public abstract boolean canBeViewed(EMedium eMedium);

	
	/**
	 * @param selectedShelfName The shelf selected in the UI
	 */
	public abstract void shelfSelected(String selectedShelfName);
}
