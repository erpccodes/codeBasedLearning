package autoWireAnnotation;

public class Menu {
	private String burger;
	private String coca;
	public String getBurger() {
		return burger;
	}
	public void setBurger(String burger) {
		this.burger = burger;
	}
	public String getCoca() {
		return coca;
	}
	@Override
	public String toString() {
		return "Menu [burger=" + burger + ", coca=" + coca + "]";
	}
	public void setCoca(String coca) {
		this.coca = coca;
	}
	public Menu(String burger, String coca) {
		super();
		this.burger = burger;
		this.coca = coca;
	}
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
