package controller.swing;

import controller.delegates.BookshelfUIDelegate;
import model.EMedium;

public class BookshelfTreeControllerImpl extends BookshelfTreeController {

	private BookshelfUIDelegate bookshelfUIDelegate;
	
	public BookshelfTreeControllerImpl(BookshelfUIDelegate bookshelfUIDelegate) {
		this.bookshelfUIDelegate = bookshelfUIDelegate;
	}
	
	@Override
	public Iterable<? extends EMedium> getShelfRentals(String selectedShelfName) {
		return bookshelfUIDelegate.getShelfRentals(selectedShelfName);
	}

	@Override
	public Iterable<? extends EMedium> getRentals() {
		return bookshelfUIDelegate.getRentals();
	}

	@Override
	public boolean addNormalShelf(String shelfName) {
		return bookshelfUIDelegate.addNormalShelf(shelfName);
	}

	@Override
	public Iterable<? extends EMedium> getLibraryEMedia() {
		return bookshelfUIDelegate.getLibraryEMedia();
	}

	@Override
	public void removeShelf(String caption) {
		bookshelfUIDelegate.removeShelf (caption);
	}

	@Override
	public void shelfSelected(String selectedShelfName) {
		bookshelfUIDelegate.shelfSelected (selectedShelfName);
	}

}
