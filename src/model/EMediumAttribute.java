package model;

/**
 * @author fmartins
 *
 * The attributes that can be added to characterise 
 * an e-medium. Some attributes are mandatory and should
 * be part of all e-medium items.
 */
public enum EMediumAttribute {
	
	MEDIUM_TYPE, 		// of String - mandatory
	TITLE,				// of String - mandatory
	PATH, 				// of String - mandatory
	LANGUAGE, 			// of String 
	AUTHOR, 			// of String 
	INTERNET_TYPE, 		// of String - mandatory
	TAGS, 				// of List<String> 
	ALBUM,				// of String 
	WIDTH,				// of int 
	HEIGHT, 			// of int
	LICENSES,			// of int
	LAST_VISITED_PAGE;	// of int
}
