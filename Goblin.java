/**
* Goblin.java - Type of enemy that can be created to attack hero
*/
public class Goblin extends Enemy{
	/**
  * Constructor for Goblin enemy
  */
  public Goblin() {
		super("Goblin", 2);
	}
	
	/**
	* Attack method does a random damage and returns damage done
	* @param e - passing entity 
	* @return x - returns name of enemy with damage done
	*/
	public String attack(Entity e) {
		int damage = (int) (Math.random() * 2) + 1; // damage range from 1 to 2
		e.takeDamage(damage); // apply damage calculations to hp
		String d = String.valueOf(damage);
		String x = " attacks " + e.getName() + " for " + d + " damage";
		return (x);
	} 
}