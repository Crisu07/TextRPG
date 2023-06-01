/**
* EnemyDecorator.java - creates base classes for enemy and decorates them with Warrior and Warlock
*/
public abstract class EnemyDecorator extends Enemy {
	private Enemy enemy;
	
  /**
  * EnemyDecorator - constructs the enemy with name and hp passed in 
  * @param e - enemy passed in 
  * @param n - name 
  * @param h - hit points
  */
	public EnemyDecorator(Enemy e, String n, int h) {
		super(n, h);
		enemy = e;
	}
	/**
  * attack - returns enemy's attack 
  * @param e - entity passed in 
  * @return - enemy's attack
  */
	public String attack(Entity e) {
		return enemy.attack(e);
	}
}