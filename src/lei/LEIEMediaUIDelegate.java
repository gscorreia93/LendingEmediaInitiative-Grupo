package lei;

import java.io.File;
import java.util.Observable;

import model.EMedium;
import model.events.AnnotationAddedEvent;
import model.events.AnnotationRemovedEvent;
import model.events.BookmarkToggleEvent;
import model.rentals.BookRental;
import model.rentals.NoSuchPageException;
import controller.delegates.EMediumUIDelegate;

/**
 * The e-media viewer ui delegate default implementation
 * 
 * @author fmartins
 *
 */
public class LEIEMediaUIDelegate extends EMediumUIDelegate {

	private EMedium eMedia;

	public void setEMedia (EMedium eMedia) {

		this.eMedia = eMedia;
	}

	@Override
	public void setObservers() {

		eMedia.addObserver(this);
	}


	@Override
	public void deleteObservers() {

		eMedia.deleteObserver(this);
	}


	@Override
	public void setLastPageVisited(int pageNum) {

		if(eMedia instanceof BookRental)
			((BookRental) eMedia).setLastPageVisited(pageNum);
	}

	@Override
	public boolean isBookmarked(int pageNum) {

		if(eMedia instanceof BookRental)
			try {
				return ((BookRental) eMedia).isBookmarked(pageNum);
			} catch (NoSuchPageException e) {
				//DO NOTHING
			}

		return false;
	}


	@Override
	public int getLastPageVisited() {

		if(eMedia instanceof BookRental)
			return ((BookRental) eMedia).getLastPageVisited();

		return 0;
	}

	@Override
	public File getEMediaFile() {

		return eMedia.getFile();
	}


	@Override
	public boolean hasAnnotations(int pageNum) {

		if(eMedia instanceof BookRental)
			return ((BookRental) eMedia).hasAnnotations(pageNum);

		return false;
	}


	@Override
	public void toggleBookmark(int pageNum) {

		if(eMedia instanceof BookRental)
			((BookRental) eMedia).toggleBookmark(pageNum);
	}

	@Override
	public EMedium getEMedia() {

		return eMedia;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
		if(arg1 instanceof BookmarkToggleEvent){
			if(((BookmarkToggleEvent) arg1).isBookmarked())
				updateBookmarkLabel(((BookmarkToggleEvent) arg1).getPageNum(), true);
			else
				updateBookmarkLabel(((BookmarkToggleEvent) arg1).getPageNum(), false);
		}else if(arg1 instanceof AnnotationRemovedEvent)
			updateAnnotationsLabel(((AnnotationRemovedEvent) arg1).getPageNum(), hasAnnotations(((AnnotationRemovedEvent) arg1).getPageNum()));
		else if(arg1 instanceof AnnotationAddedEvent)
			updateAnnotationsLabel(((AnnotationAddedEvent) arg1).getPageNum(), hasAnnotations(((AnnotationAddedEvent) arg1).getPageNum()));
	}
}
