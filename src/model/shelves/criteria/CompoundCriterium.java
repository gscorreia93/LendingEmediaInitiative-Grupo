package model.shelves.criteria;

public abstract class CompoundCriterium implements ICriterium {

	protected ICriterium left, right;

	public CompoundCriterium(ICriterium left, ICriterium right) {
		
		this.left = left;
		this.right = right;
	}
}
