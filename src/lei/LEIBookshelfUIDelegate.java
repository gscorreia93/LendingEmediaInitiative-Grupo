package lei;

import java.util.Observable;

import javax.naming.OperationNotSupportedException;

import model.EMedium;
import model.EMediumPropertiesData;
import model.EMediumType;
import model.ILibraryFacade;
import model.IShelvesFacade;
import model.events.RentalAddedEvent;
import model.events.RentalRemovedEvent;
import model.events.ShelfAddedEvent;
import model.events.ShelfRemovedEvent;
import model.lendables.Lendable;
import controller.delegates.BookshelfUIDelegate;

/**
 * @author fmartins
 * 
 *         The bookshelf ui delegate default implementation. This is the class
 *         you need to program in order to connect the UI events to your
 *         application (the model).
 */
public class LEIBookshelfUIDelegate extends BookshelfUIDelegate {

	private IShelvesFacade shelvesHandler;
	private ILibraryFacade libraryHandler;

	public LEIBookshelfUIDelegate(IShelvesFacade shelvesHandler,
			ILibraryFacade libraryHandler) {

		this.shelvesHandler = shelvesHandler;
		this.libraryHandler = libraryHandler;
		shelvesHandler.addShelfCollectionObserver(this);
		libraryHandler.addObserver(this);
	}

	@Override
	public Iterable<? extends EMedium> getShelfRentals(String selectedShelf) {

		return shelvesHandler.getShelfRentals(selectedShelf);
	}

	@Override
	public Iterable<? extends EMedium> getRentals() {

		return shelvesHandler.getRentals();
	}

	@Override
	public Iterable<? extends EMedium> getLibraryEMedia() {

		return libraryHandler.getEMedia();
	}

	@Override
	public boolean addNormalShelf(String shelfName) {

		return shelvesHandler.addNormalShelf(shelfName);
	}

	@Override
	public void removeShelf(String shelfName) {

		shelvesHandler.removeShelf(shelfName);
	}

	@Override
	public void updateRental(EMedium document,
			EMediumPropertiesData documentProperties) {

		//DO NOTHING
	}

	@Override
	public void removeEMediumShelf(String shelfName, EMedium eMedium)
			throws OperationNotSupportedException {

		shelvesHandler.removeRental(shelfName, eMedium);
	}

	@Override
	public Iterable<String> getShelves() {

		return shelvesHandler.getShelves();
	}

	@Override
	public boolean addRentalEMedium(EMedium eMedium) {

		return shelvesHandler.addRental(eMedium);
	}

	@Override
	public boolean addEMediumShelf(String shelfName, EMedium eMedium)
			throws OperationNotSupportedException {

		return shelvesHandler.addShelfRental(shelfName, eMedium);
	}

	@Override
	public String getEMediumTitle(EMedium d) {

		return d.getTitle();
	}

	@Override
	public void returnRental(EMedium eMedia) {
		
		shelvesHandler.returnRental(eMedia);
	}

	@Override
	public void revokeLending(EMedium eMedium) {

		shelvesHandler.returnRental(eMedium);
	}

	@Override
	public boolean addEMediumLibrary(String type,
			EMediumPropertiesData lendableProperties) {
		
		if (type.equals("Document"))
			return libraryHandler.addLendable(EMediumType.DOCUMENT,
					lendableProperties);
		else if (type.equals("Song"))
			return libraryHandler.addLendable(EMediumType.SONG,
					lendableProperties);
		else if (type.equals("Image"))
			return libraryHandler.addLendable(EMediumType.IMAGE,
					lendableProperties);

		return false;
	}

	@Override
	public boolean canBeViewed(EMedium eMedium) {
		
		if(eMedium instanceof Lendable)
			return false;
		else
			return !shelvesHandler.isRentalExpired(eMedium);
	}

	@Override
	public boolean isRent(EMedium eMedium) {
		
		return shelvesHandler.isRented(eMedium);
	}

	@Override
	public void shelfSelected(String selectedShelfName) {
		//DO NOTHING
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		if (arg1 instanceof ShelfAddedEvent)
			addShelfTreeNode(((ShelfAddedEvent) arg1).getShelfName());
		else if (arg1 instanceof ShelfRemovedEvent)
			removeShelfTreeNode(((ShelfRemovedEvent) arg1).getShelfName());
		else if (arg1 instanceof RentalAddedEvent)	
			addToEMediaPanel(((RentalAddedEvent) arg1).getEMedium().getTitle(),
					((RentalAddedEvent) arg1).getEMedium());
		else if (arg1 instanceof RentalRemovedEvent)	
			removeEMediumFromPanel(((RentalAddedEvent) arg1).getEMedium());
	}

}
