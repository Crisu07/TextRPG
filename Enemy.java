/*
* Enemy.java - Represents the enemy the hero will encounter
**/
public abstract class Enemy extends Entity { //enemy inherits from entity
  /**
  * Constructor for enemy with a name and hp
  * @param n - name of enemy
  * @param h - hp of enemy 
  */
	public Enemy(String n, int h) {
		super(n, h);
	}
}
