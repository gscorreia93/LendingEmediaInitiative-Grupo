package model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fmartins
 * 
 *         The properties of an e-medium. For the sake of being as general as
 *         possible, we do not impose attributes (besides its type, file, and
 *         title) to e-medium. Instead we should maintain a collection of the
 *         EMediumAttribute and its associated value.
 * 
 *         Elements of the class should be clonal.
 */
public class EMediumPropertiesData implements Cloneable {

	private Map<EMediumAttribute, Object> map;

	public EMediumPropertiesData() {

		map = new HashMap<EMediumAttribute, Object>();
	}

	/**
	 * @param attribute
	 *            The attribute to get the value from
	 * @return The value of the attribute identified by *attribute*
	 */
	public Object getAttribute(EMediumAttribute attribute) {

		return map.get(attribute);
	}

	/**
	 * Add/Changes a medium attribute given its id and value
	 * 
	 * @param attribute
	 *            The attribute id to add/change
	 *            The value of the attribute
	 */
	public void addAttribute(EMediumAttribute attribute, Object value) {

		map.put(attribute, value);
	}

	/**
	 * @param type
	 *            The type to check if the e-medium is a member of
	 * @return If the e-medium is of type *type*
	 */
	public boolean isMediumType(EMediumType type) {

		return map.containsValue(type);
	}

	@Override
	public EMediumPropertiesData clone() throws CloneNotSupportedException {

		super.clone();
		EMediumPropertiesData cloned = new EMediumPropertiesData();
		cloned.map = new HashMap<EMediumAttribute, Object>(map);
		return cloned;
	}
}
