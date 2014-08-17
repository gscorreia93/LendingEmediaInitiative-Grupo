package model;

import java.util.Observer;

import model.lendables.Library;

public class LibraryFacade implements ILibraryFacade {

	private Library library;
	
	public LibraryFacade (){
		
		library = new Library();
	}
	
	@Override
	public Iterable<? extends EMedium> getEMedia() {

		return library;
	}
	
	public void addObserver(Observer observer){
		
		library.addObserver(observer);
	}

	@Override
	public boolean addLendable(EMediumType type,
			EMediumPropertiesData lendableProperties) {
		
		return library.addLendable(type, lendableProperties);
	}

	@Override
	public EMedium getLastAddedLendable() {
		
		return library.getLastAddedLendable();
	}

	@Override
	public boolean canBeRent(EMedium eMedium) {
		
		return library.canBeRent(eMedium);
	}

	@Override
	public void rent(EMedium eMedium) {

		library.rent(eMedium);
	}
}
