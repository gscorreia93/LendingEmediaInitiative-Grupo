package lei;

import java.io.IOException;

import model.ILibraryFacade;
import model.IShelvesFacade;
import model.LEI;
import model.shelves.criteria.RecentlyAddedCriterium;
import view.StartupUI;

/**
 * The main project class
 * 
 * @author fmartins
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		// create the main application class
		LEI lei = new LEI();

		IShelvesFacade shFac = lei.getShelvesFacade();
		ILibraryFacade liFac = lei.getLibraryFacade();
		
		RecentlyAddedCriterium crit = new RecentlyAddedCriterium(10*1000);
		
		shFac.addSmartShelf("SmartShelf", crit);
		
		// start the UI
		StartupUI.run(
				new LEIBookshelfUIDelegate(shFac, liFac), new LEIEMediaUIDelegate(),
				new LEIEMediumMetadataUIDelegate());
	}
}
