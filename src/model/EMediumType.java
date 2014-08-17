package model;

/**
 * @author fmartins
 * 
 *         The types of documents available in the library to be rented.
 */
public enum EMediumType {
	
	DOCUMENT("Document"), SONG("Song"), IMAGE("Image");

	private String description;

	/**
	 * @param description
	 *            Creates a type given its description.
	 * 
	 *            Notice that the constructor is private, meaning that objects
	 *            of type EMediumType cannot be created from outside the enum
	 *            class.
	 */
	private EMediumType(String description) {

		this.description = description;
	}

	@Override
	public String toString() {

		return this.description;
	}
}
