/**
* Warlock.java - Decorator class for enemies
*/
public class Warlock extends EnemyDecorator implements Magical{
	/**
  * Constructs Warlock decorator
  */
  public Warlock(Enemy e) {
		super(e, e.getName(), e.getHP() + 1); // Warlock hp increased by 1 when added to enemy
	}
	/**
  * attack() - attack done by Warlock depending on magic attack selected
  * @param e - entity passed in 
  * @return - returns magical attack
  */
	public String attack(Entity e) { // Randomly choose 1 of the 3 magic attacks for Warlock
		int randSpell = (int) (Math.random() * 3) + 1;
		
		if (randSpell == 1) {
			return super.attack(e) + magicMissile(e);
		}
		else if (randSpell == 2) {
			return super.attack(e) + fireball(e);
		}
		else {
			return super.attack(e) + thunderclap(e);
		}
	}
	
	/**
  * magicMissile - type of magical attack to do damage
  * @param e - passes entity
  * @return x - name and damage done
  */
	public String magicMissile(Entity e) {
		int damage = (int) (Math.random() * 2); // Calculates damage number from 0 to 2
		e.takeDamage(damage); // apply damage calculations to hp
		String d = String.valueOf(damage);
		String x = "\nAttacks " + e.getName() + " with Magic Missiles for " + d + " damage";
		return (x);
	}
  /**
  * fireball - type of magical attack to do damage
  * @param e - passes entity
  * @return x - name and damage done
  */
	public String fireball(Entity e) {
		int damage = (int) (Math.random() * 3); // Calculates damage number up to 3
		e.takeDamage(damage); // apply damage calculations to hp
		String d = String.valueOf(damage);
		String x = "\nAttacks " + e.getName() + " with Fireball for " + d + " damage";
		return (x);
	}
  /**
  * thunderclap - type of magical attack to do damage
  * @param e - passes entity
  * @return x - name and damage done
  */
	public String thunderclap(Entity e) {
		int damage = (int) (Math.random() * 3) + 1; // Calculates damage number between 1 and 3
		e.takeDamage(damage); // apply damage calculations to hp
		String d = String.valueOf(damage);
		String x = "\nAttacks " + e.getName() + " with Thunderclap for " + d + " damage";
		return (x);
	}
	
  /**
  * Attaches decorator title to enemy Warlock 
  * return title - title of the enemy decorated with Warlock
  */
	public String getName() {
		String title = super.getName() + " Warlock";
		return title;
	}
}