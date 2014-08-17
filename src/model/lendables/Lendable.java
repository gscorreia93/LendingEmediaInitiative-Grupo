package model.lendables;

import java.io.File;
import java.util.Observable;

import model.EMedium;
import model.EMediumAttribute;
import model.EMediumPropertiesData;
import model.EMediumType;

public class Lendable extends Observable implements EMedium {

	private EMediumType type;
	private EMediumPropertiesData properties;
	private File file;
	private int licences;

	public Lendable(EMediumType type, EMediumPropertiesData properties) {

		this.properties = properties;
		this.type = type;
		file = new File((String) properties.getAttribute(EMediumAttribute.PATH));
		licences = (int) properties.getAttribute(EMediumAttribute.LICENSES);
	}

	@Override
	public File getFile() {

		return this.file;
	}

	@Override
	public String getTitle() {

		return (String) properties.getAttribute(EMediumAttribute.TITLE);
	}

	@Override
	public String getInternetType() {

		return (String) properties.getAttribute(EMediumAttribute.INTERNET_TYPE);
	}

	@Override
	public EMediumType getType() {

		return this.type;
	}

	@Override
	public EMediumPropertiesData getEMediumProperties() {

		return properties;
	}

	public void rent() {
				
		if (hasLicensesAvailable()){
			licences--;
			setChanged();
			notifyObservers();
		}
	}

	public boolean hasLicensesAvailable() {

		return licences > 0;
	}

	@Override
	public int compareTo(EMedium o) {

		return o.getTitle().compareTo(
				(String) properties.getAttribute(EMediumAttribute.TITLE));
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Lendable other = (Lendable) obj;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
