/**
* Froglok.java - Type of enemy that can be created to attack hero
*/
public class Froglok extends Enemy{
	/**
  * Constructor for Froglok enemy
  */
  public Froglok() {
		super("Froglok", 3);
	}
	
	/**
	* Attack method does a random damage and returns damage done
	* @param e - passing entity 
	* @return x - returns name of enemy with damage done
	*/
	public String attack(Entity e) {
		int damage = (int) (Math.random() * 3) + 1; // damage range from 1 to 3
		e.takeDamage(damage); // apply damage calculations to hp
		String d = String.valueOf(damage);
		String x = " attacks " + e.getName() + " for " + d + " damage";
		return (x);
	} 
}