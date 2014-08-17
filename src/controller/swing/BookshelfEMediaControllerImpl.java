package controller.swing;

import javax.naming.OperationNotSupportedException;

import controller.delegates.BookshelfUIDelegate;
import controller.delegates.EMediumMetadataUIDelegate;
import controller.delegates.EMediumUIDelegate;
import model.EMedium;
import model.EMediumPropertiesData;
import view.IBookshelfUI;
import view.swing.EMediumLabel;
import view.swing.EMediumMetadataUI;
import view.swing.EMediumShowUI;

/**
 * @author fmartins
 *
 *	Delegate bookself e-media controller methods to the respective delegate object
 */
public class BookshelfEMediaControllerImpl extends BookshelfEMediaController {
	
	/**
	 * The bookself UI delegate
	 */
	private BookshelfUIDelegate bookshelfUIDelegate;
	
	
	/**
	 * The e-medium UI delegate
	 */
	private EMediumUIDelegate eMediumUIDelegate;
	
	
	/**
	 * The e-medium metadata UI delegate
	 */
	private EMediumMetadataUIDelegate eMediumMetadataUIDelegate;
	
	
	/**
	 * Constructs a bookself media controller given the bookself delegate,
	 * the e-medium delegate, and the e-medium metadata delegate
	 * 
	 * @param bookshelfUIDelegate The bookshelf UI delegate
	 * @param eMediumUIDelegate The e-medium UI delegate
	 * @param eMediumMetadataUIDelegate The e-medium metadata UI delegate
	 */
	public BookshelfEMediaControllerImpl(BookshelfUIDelegate bookshelfUIDelegate,
			EMediumUIDelegate eMediumUIDelegate, 
			EMediumMetadataUIDelegate eMediumMetadataUIDelegate) {
		this.bookshelfUIDelegate = bookshelfUIDelegate;
		this.eMediumUIDelegate = eMediumUIDelegate;
		this.eMediumMetadataUIDelegate = eMediumMetadataUIDelegate;
	}
	
	
	@Override
	public void revokeLending(EMedium eMedium) {
		bookshelfUIDelegate.revokeLending(eMedium);
	}

	
	@Override
	public void returnRental(EMedium eMedium) {
		bookshelfUIDelegate.returnRental(eMedium);		
	}

	
	@Override
	public void removeEMediumShelf(String shelfName, EMedium eMedium) throws OperationNotSupportedException {
		bookshelfUIDelegate.removeEMediumShelf(shelfName, eMedium);
	}

	
	@Override
	public void updateRental(EMedium eMedium, EMediumPropertiesData eMediumProperties) {
		bookshelfUIDelegate.updateRental (eMedium, eMediumProperties);
	}

	
	@Override
	public void eMediumShow(EMediumLabel selectedEMediaLabel) {
		eMediumUIDelegate.setEMedia(selectedEMediaLabel.getEMedium());
		eMediumUIDelegate.setObservers();
		new EMediumShowUI (bookshelfUI, 
				selectedEMediaLabel.getEMediumViewer(), 
				new EMediumShowController(eMediumUIDelegate, 
						eMediumMetadataUIDelegate, selectedEMediaLabel),
				eMediumUIDelegate);
	}

	
	@Override
	public boolean canBeViewed(EMedium eMedium) {
		return bookshelfUIDelegate.canBeViewed(eMedium);
	}

	
	@Override
	public void metadataShow(EMediumLabel selectedEMediaLabel, IBookshelfUI bookshelfUI) {
		eMediumMetadataUIDelegate.setEMedium(selectedEMediaLabel.getEMedium());
		new EMediumMetadataUI (bookshelfUI, 1, // show metadata over the first page
				eMediumMetadataUIDelegate, null);
		eMediumMetadataUIDelegate.deleteObservers();
	}	
}
