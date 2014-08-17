package controller.swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.naming.OperationNotSupportedException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.TransferHandler;
import javax.swing.border.MatteBorder;

import model.EMedium;
import model.EMediumPropertiesData;
import services.viewer.Viewer;
import view.IBookshelfUI;
import view.swing.DialogData;
import view.swing.EMediumLabel;
import view.swing.EMediumPropertiesUI;

/**
 * @author fmartins
 *
 *	The e-mediua bookshelf controller
 */
abstract class BookshelfEMediaController implements IBookshelfEMediaController {
	
	/**
	 * Reference to the boojself's UI
	 */
	IBookshelfUI bookshelfUI;
	
	
	/**
	 * The selected e-medium
	 */
	private EMediumLabel selectedEMediumLabel;
	

	@Override
	public void setBookshelfUI (IBookshelfUI bookshelfUI) {
		this.bookshelfUI = bookshelfUI;
	}

	
	@Override
	public MouseAdapter thumbnailSelection() {
		return new MouseAdapter () {			
			@Override
			public void mouseClicked(MouseEvent event) {
				if (selectedEMediumLabel != null)
					selectedEMediumLabel.setBorder(null);
				selectedEMediumLabel = (EMediumLabel) event.getSource();
				selectedEMediumLabel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.YELLOW));
				if (event.getButton() == MouseEvent.BUTTON1) {
					if (event.getClickCount() >= 2) {
						// on double click, view the document
						if (canBeViewed(selectedEMediumLabel.getEMedium()))
							if (selectedEMediumLabel.getEMediumViewer() != null) {
								eMediumShow(selectedEMediumLabel);
							} else
								JOptionPane.showMessageDialog(bookshelfUI, "Cannot obtain a viewer for this type of document", 
									"Error obtaining viewer for document", JOptionPane.ERROR_MESSAGE);
						else
							JOptionPane.showMessageDialog(bookshelfUI, "You do not have a valid rental for the e-medium", 
									"Error viewing the e-medium", JOptionPane.ERROR_MESSAGE);							
					}
				}
				else if (event.getButton() == MouseEvent.BUTTON3) {
					selectedEMediumLabel = (EMediumLabel) event.getSource();
					JPopupMenu contextMenu = selectedEMediumLabel.getContextMenu(); 
					contextMenu.show (selectedEMediumLabel, event.getX(), event.getY());
				}
		      }
		};
	}
	
	
	@Override	
	public MouseAdapter thumbnailDragMotion() {
		return new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent event) {
				JComponent jc = (JComponent) event.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, event, TransferHandler.COPY);
			}
		};
	}	

	
	@Override	
	public ActionListener showLendableProperties() {
		return new ActionListener()  {
			@Override
			public void actionPerformed(ActionEvent event) {
				new EMediumPropertiesUI(bookshelfUI, 
						new DialogData(selectedEMediumLabel.getEMedium().getEMediumProperties()), true, false);
			}
		};
	}

	
	@Override	
	public ActionListener revokeLending() {
		return new ActionListener()  {
			@Override
			public void actionPerformed(ActionEvent event) {
				revokeLending(selectedEMediumLabel.getEMedium());
			}
		};
	}

	
	@Override	
	public ActionListener showRentalProperties() {
		return new ActionListener()  {
			@Override
			public void actionPerformed(ActionEvent event) {
				EMediumPropertiesData eMediumProperties = selectedEMediumLabel.getEMedium().getEMediumProperties();
				new EMediumPropertiesUI(bookshelfUI, new DialogData(eMediumProperties), true, true);
				updateRental (selectedEMediumLabel.getEMedium(), eMediumProperties);
			}
		};
	}

	
	@Override	
	public ActionListener setSlideShowDuration() {
		return new ActionListener()  {
			@Override
			public void actionPerformed(ActionEvent event) {
				Viewer viewer = selectedEMediumLabel.getEMediumViewer();
				if (viewer != null && viewer.canSlideshow()) {
					String sec = (String) JOptionPane.showInputDialog(selectedEMediumLabel.getParent(), 
							"Slide duration (secs):", "Slideslow definitions", 
							JOptionPane.QUESTION_MESSAGE, null, null, selectedEMediumLabel.getSlideDuration());
					try {
						int secs = new Integer(sec);
						if (secs <= 0) {
							JOptionPane.showMessageDialog(selectedEMediumLabel.getParent(), 
									"Invalid number of seconds", "Invalid data", JOptionPane.ERROR_MESSAGE);
						} else
							selectedEMediumLabel.setSlideDuration(secs);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(selectedEMediumLabel.getParent(), 
								"Invalid number of seconds", "Invalid data", JOptionPane.ERROR_MESSAGE);
					}
				} else
					JOptionPane.showMessageDialog(selectedEMediumLabel.getParent(), 
							"Medium cannot be slideshown", "Slideshow error", JOptionPane.ERROR_MESSAGE);
			}		
		};
	}


	@Override	
	public ActionListener returnRental() {
		return new ActionListener()  {
			@Override
			public void actionPerformed(ActionEvent event) {
				returnRental(selectedEMediumLabel.getEMedium());
			}
		};
	}
	
	
	@Override	
	public ActionListener deleteRental(final String shelfName) {
		return new ActionListener()  {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					removeEMediumShelf(shelfName, selectedEMediumLabel.getEMedium());
				} catch (OperationNotSupportedException e) {
					JOptionPane.showMessageDialog(selectedEMediumLabel.getParent(),
						    "E-medium cannot be removed from the shelf.",
						    "Remove document error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		};
	}	
	
	
	@Override
	public ActionListener showEMediumMetadata() {
		return new ActionListener()  {
			@Override
			public void actionPerformed(ActionEvent event) {
				metadataShow(selectedEMediumLabel, bookshelfUI);
			}
		};
	}


	// abstract methods used in the template methods above
	
	/**
	 * @param eMedium The e-medium to be revoked
	 */
	public abstract void revokeLending(EMedium eMedium);

	
	/**
	 * @param eMedium The e-medium to be rented
	 */
	public abstract void returnRental(EMedium eMedium);

	
	/**
	 * @param shelfName The self name where the e-medium is going to be removed
	 * @param eMedium The e-medium to be removed
	 * @throws OperationNotSupportedException When trying to remove an item from a smart self.
	 */
	public abstract void removeEMediumShelf(String shelfName, EMedium eMedium) throws OperationNotSupportedException;

	
	/**
	 * @param eMedium 
	 * @param eMediumProperties
	 */
	public abstract void updateRental(EMedium eMedium, EMediumPropertiesData eMediumProperties);

	
	/**
	 * @param selectedEMediaLabel The e-medium to be shown
	 */
	public abstract void eMediumShow(EMediumLabel selectedEMediaLabel);
	
	
	/**
	 * @param eMedium The e-medium 
	 * @return if the e-medium can be viewed 
	 */
	public abstract boolean canBeViewed(EMedium eMedium);

	
	/**
	 * @param selectedEMediaLabel The e-medium label
	 * @param bookshelfUI The bookshelf UI frame
	 */
	public abstract void metadataShow(EMediumLabel selectedEMediaLabel, IBookshelfUI bookshelfUI);
}
