/**
* Orc.java - Type of enemy that can be created to attack hero
*/
public class Orc extends Enemy{
	/**
  * Constructor for Orc enemy
  */
  public Orc() {
		super("Orc", 4);
	}
	
	/**
	* Attack method does a random damage and returns damage done
	* @param e - passing entity 
	* @return x - returns name of enemy with damage done
	*/
	public String attack(Entity e) {
	  int damage = (int) (Math.random() * 4); // damage range from 0 to 4
		e.takeDamage(damage); // apply damage calculations to hp
		String d = String.valueOf(damage);
		String x = " attacks " + e.getName() + " for " + d + " damage";
		return (x);
	} 
}