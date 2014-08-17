package model.lendables;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Set;

import model.EMedium;
import model.EMediumPropertiesData;
import model.EMediumType;
import model.events.RentalAddedEvent;

public class Library extends Observable implements Iterable<Lendable> {

	private Set<Lendable> set;
	private Lendable last;

	public Library() {

		set = new HashSet<Lendable>();
		last = null;
	}

	public boolean addLendable(EMediumType type,
			EMediumPropertiesData properties) {

		Lendable created = new Lendable(type, properties);
		last = created;
		
		if (set.contains(created))
			return false;

		set.add(created);
		
		setChanged();
		notifyObservers(new RentalAddedEvent(created));
		
		return true;
	}

	public Lendable getLastAddedLendable() {

		return last;
	}

	public void rent(EMedium eMedium) {

		Iterator<Lendable> usable = set.iterator();

		Lendable aux;

		while (usable.hasNext()){
			aux = usable.next();
			if (aux.equals(eMedium))
				aux.rent();
		}
	}

	public boolean canBeRent(EMedium eMedium) {

		Iterator<Lendable> usable = set.iterator();

		while (usable.hasNext())
			if (usable.next().hasLicensesAvailable()
					&& usable.next().equals(eMedium))
				return true;

		return false;
	}

	@Override
	public Iterator<Lendable> iterator() {

		return set.iterator();
	}
}