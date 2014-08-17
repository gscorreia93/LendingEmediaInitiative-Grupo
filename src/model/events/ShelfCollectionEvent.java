package model.events;

public abstract class ShelfCollectionEvent {
	
	private String name;
	
	public ShelfCollectionEvent(String name){
		
		this.name = name;
	}
	
	public String getShelfName(){
		
		return name;
	}
}
