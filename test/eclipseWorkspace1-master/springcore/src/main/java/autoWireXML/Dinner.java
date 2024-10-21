package autoWireXML;

public class Dinner {
	
	private int price;
	private Menu items;
	@Override
	public String toString() {
		return "Dinner [price= Rs." + price + ", items=" + items + "]";
	}
	public Dinner(int price, Menu items) {
		super();
		this.price = price;
		this.items = items;
	}
	public Dinner() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Menu getItems() {
		return items;
	}
	public void setItems(Menu items) {
		this.items = items;
	}
}
