package model.rentals;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Observable;

import model.EMedium;
import model.EMediumPropertiesData;
import model.EMediumType;
import model.events.AnnotationAddedEvent;
import model.events.AnnotationRemovedEvent;
import model.lendables.Lendable;

public class Rental extends Observable implements EMedium {

	private Lendable lendable;
	private Date timestamp;
	private boolean expired;
	private Collection<String> annotations;
	
	public Rental(Lendable lendable){
		
		ArrayList<Integer> abc = new ArrayList<Integer>();
		
		abc.add(1);
		
		this.lendable = lendable;
		timestamp = new Date();
		expired = false;
		annotations = new ArrayList<String>();
	}
	
	public Date getRentalTimestamp(){
		
		return timestamp;
	}
	
	public void addAnnotation(String text){
		
		annotations.add(text);
		
		Page dummy = null;
		setChanged();		
		notifyObservers(new AnnotationAddedEvent(this, dummy));
	}
	
	public void removeAnnotation(int n){
		
		if (n <= annotations.size()){
			annotations.remove(n);
		
		Page dummy = null;
		setChanged();
		notifyObservers(new AnnotationRemovedEvent(this, dummy,n));
		}		
	}
	
	public Iterable<String> getAnnotations(){
		
		return annotations;
	}
	
	public void renew(){
		
		timestamp = new Date();
		expired = false;
	}
	
	public boolean isExpired(){
		
		return expired;
	}

	@Override
	public File getFile() {
		
		return lendable.getFile();
	}

	@Override
	public String getTitle() {
		
		return lendable.getTitle();
	}

	@Override
	public String getInternetType() {
		
		return lendable.getInternetType();
	}

	@Override
	public EMediumType getType() {
		
		return lendable.getType();
	}

	@Override
	public EMediumPropertiesData getEMediumProperties() {
		
		return lendable.getEMediumProperties();
	}
	
	@Override
	public int compareTo(EMedium arg0) {
		
		return lendable.compareTo(arg0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lendable == null) ? 0 : lendable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rental other = (Rental) obj;
		if (lendable == null) {
			if (other.lendable != null)
				return false;
		} else if (!lendable.equals(other.lendable))
			return false;
		return true;
	}
	
	public void endRental(){
		this.expired = true;
	}
	
	public void renewRental(){
		this.expired = false;
	}
}
