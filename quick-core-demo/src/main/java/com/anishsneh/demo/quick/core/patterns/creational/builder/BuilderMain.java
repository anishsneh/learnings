package com.anishsneh.demo.quick.core.patterns.creational.builder;

/** 
 *
 * When too many parameters needed to create an object, not easy using direct constructor
 * 
 * A customer ordering a pizza. 
 * 
 * Used to avoid: 
 * 
 *  The Telescoping Constructor (Anti)Pattern, 
 *  In this pattern, your POJO has numerous constructors each taking a different number of parameters that, 
 *  if the class has been written correctly, delegate to a default constructor.
 *  
 *  JavaBeans style of construction:  first by calling a no-argument constructor, and then by calling a series of setXXX methods
 * 
 */
public class BuilderMain {
	public static void main(final String[] args) {
		WaiterDirector waiter = new WaiterDirector();
		PizzaBuilder hawaiian_pizzabuilder = new HawaiianPizzaBuilder();
		PizzaBuilder spicy_pizzabuilder = new SpicyPizzaBuilder();

		waiter.setPizzaBuilder(hawaiian_pizzabuilder);
		waiter.constructPizza();

		Pizza pizza = waiter.getPizza();
	}
}

class Pizza {
	private String dough = "";
	private String sauce = "";
	private String topping = "";

	public void setDough(String dough) {
		this.dough = dough;
	}

	public void setSauce(String sauce) {
		this.sauce = sauce;
	}

	public void setTopping(String topping) {
		this.topping = topping;
	}
}

/** 
 * 
 * "Abstract Builder" 
 * 
 * */
abstract class PizzaBuilder {
	protected Pizza pizza;

	public Pizza getPizza() {
		return pizza;
	}

	public void createNewPizzaProduct() {
		pizza = new Pizza();
	}

	public abstract void buildDough();

	public abstract void buildSauce();

	public abstract void buildTopping();
}

/** 
 * 
 * "ConcreteBuilder" 
 * 
 * */
class HawaiianPizzaBuilder extends PizzaBuilder {
	public void buildDough() {
		pizza.setDough("cross");
	}

	public void buildSauce() {
		pizza.setSauce("mild");
	}

	public void buildTopping() {
		pizza.setTopping("ham+pineapple");
	}
}

/** 
 * 
 * "ConcreteBuilder" 
 * 
 * */
class SpicyPizzaBuilder extends PizzaBuilder {
	public void buildDough() {
		pizza.setDough("pan baked");
	}

	public void buildSauce() {
		pizza.setSauce("hot");
	}

	public void buildTopping() {
		pizza.setTopping("pepperoni+salami");
	}
}

/** 
 * 
 * "Director" 
 * 
 * RESPONSIBLE FOR CORRECT SEQUENCE OF OBJECT CREATION STEPS
 * 
 * */
class WaiterDirector {
	private PizzaBuilder pizzaBuilder;

	public void setPizzaBuilder(PizzaBuilder pb) {
		pizzaBuilder = pb;
	}

	public Pizza getPizza() {
		return pizzaBuilder.getPizza();
	}

	public void constructPizza() {
		pizzaBuilder.createNewPizzaProduct();
		pizzaBuilder.buildDough();
		pizzaBuilder.buildSauce();
		pizzaBuilder.buildTopping();
	}
}