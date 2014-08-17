package model;

import java.io.File;
import java.util.Observer;

/**
 * @author fmartins
 * 
 * An e-medium. This is the interface of an e-medium that 
 * is make available outside the model package.
 */
public interface EMedium extends Comparable<EMedium> {
	
	/**
	 * @return The file pointing to the local resource with 
	 * 			the contents of the e-medium.
	 */
	public File getFile ();
	
	
	/**
	 * @return The title of the e-medium.
	 */
	public String getTitle ();
	
	
	/**
	 * @return The internet type of the e-medium according to the 
	 * 			RFCxxx. It describes the contents of the file and 
	 * 			selects the appropriate viewer to explore the contents
	 * 			of the e-medium.
	 */
	public String getInternetType();
	
	
	/**
	 * @return The e-medium type
	 */
	public EMediumType getType();
	
	
	/**
	 * @return The properties associated with the e-medium.
	 */
	public EMediumPropertiesData getEMediumProperties();
	
	
	/**
	 * @param observer The observer to be added to the e-medium.
	 * 
	 * Adds an observer to the e-medium that gets notified whenever 
	 * an annotation is added or removed, or a bookmark is toggled.  
	 */
	void addObserver(Observer observer);
	
	
	/**
	 * @param observer The observer to be removed from the e-medium.
	 * 
	 * Removes the observer from the e-medium.
	 */
	void deleteObserver(Observer observer);
}
