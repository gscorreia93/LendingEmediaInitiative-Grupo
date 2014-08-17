package controller.swing;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import view.IBookshelfUI;

/**
 * @author fmartins
 *
 *	The bookself e-medium contoller interface
 */
public interface IBookshelfEMediaController {
	
	/**
	 * Links the delegate back to its UI
	 * 
	 * @param bookshelf The bookshelf UI
	 */
	public void setBookshelfUI (IBookshelfUI bookshelfUI);

	/**
	 * @return The thumbnail selection controller
	 */
	public MouseAdapter thumbnailSelection();	
	
	/**
	 * @return The thumbnail drag motion controller
	 */
	public MouseAdapter thumbnailDragMotion();
	
	
	/**
	 * @return The show lendable properties controller
	 */
	public ActionListener showLendableProperties();
	
	/**
	 * @return The revoke lending controller
	 */
	public ActionListener revokeLending();
	
	
	/**
	 * @return The show rental properties controller
	 */
	public ActionListener showRentalProperties();
	
	
	/**
	 * @return The return rental controller
	 */
	public ActionListener returnRental();
	
	
	/**
	 * @param shelfName The self in which the rental is
	 * @return The delete rental controller for a self
	 */
	public ActionListener deleteRental(final String shelfName);
	
	
	/**
	 * @return The show e-medium metadata controller
	 */
	public ActionListener showEMediumMetadata();

	
	/**
	 * @return The set slideshow duration controller (bonus)
	 */
	public ActionListener setSlideShowDuration();
}
