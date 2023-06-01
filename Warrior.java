/**
* Warrior.java - Decorator class for enemies
*/
public class Warrior extends EnemyDecorator{
	/**
  * Constructs Warrior decorator
  */
  public Warrior(Enemy e) {
		super(e, e.getName(), e.getHP() + 2); // Warrior hp increased by 2 when added to enemy
	}
	/**
  * attack() - attack done by Warrior depending on magic attack selected
  * @param e - entity passed in 
  * @return - returns magical attack
  */
	public String attack(Entity e) {
		int damage = (int) (Math.random() * 3) + 1; // Does damage ranging from 1-3
		e.takeDamage(damage); // apply damage calculations to hp
		String d = String.valueOf(damage);
		String x = "\nAttacks " + e.getName() + " for " + d + " damage";
		return super.attack(e) + x;
	}
	
  /**
  * Attaches decorator title to enemy warrior
  * @return title - title of the enemy decorated with warrior 
  */
	public String getName() {
		String title = super.getName() + " Warrior";
		return title;
	}
}