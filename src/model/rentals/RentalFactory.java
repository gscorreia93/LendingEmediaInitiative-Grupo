package model.rentals;

import model.EMedium;
import model.EMediumType;
import model.lendables.Lendable;

public class RentalFactory {

	public Rental createRental(EMedium m) {

		if (m.getType() == EMediumType.DOCUMENT)
			return new BookRental(new Lendable(m.getType(),
					m.getEMediumProperties()));
		else
			return new Rental(new Lendable(m.getType(),
					m.getEMediumProperties()));
	}
}
