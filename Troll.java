/**
* Troll.java - Type of enemy that can be created to attack hero
*/
public class Troll extends Enemy{
	/**
  * Constructor for Troll enemy
  */
  public Troll() {
		super("Troll", 5);
	}
	
	/**
	* Attack method does a random damage and returns damage done
	* @param e - passing entity 
	* @return x - returns name of enemy with damage done
	*/
	public String attack(Entity e) {
		int damage = (int) (Math.random() * 5); // damage range from 0 to 5
		e.takeDamage(damage); // apply damage calculations to hp
		String d = String.valueOf(damage);
		String x = " attacks " + e.getName() + " for " + d + " damage";
		return (x);
	} 
}