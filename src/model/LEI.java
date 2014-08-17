package model;

/**
 * @author fmartins
 *
 *	The main class of the application that is responsible for
 *	starting up the application.
 */
public class LEI {

	private ILibraryFacade libraryFacade;
	private IShelvesFacade shelvesFacade;
	
	public LEI(){
		
		libraryFacade = new LibraryFacade();
		shelvesFacade = new ShelvesFacade();
	}
	
	public ILibraryFacade getLibraryFacade() {
		
		return libraryFacade;
	}
	
	public IShelvesFacade getShelvesFacade() {
		
		return shelvesFacade;
	}
}
