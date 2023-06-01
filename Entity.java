/**
* Entity.java - Describes a character in the game
*/

public abstract class Entity {
  private String name; //entity name
  private int maxHp; //max number of hp entity can have
  private int hp; //hp points

  /**
  * Constructor entity - creates an entity with name and hit points
  * @param n - name of entity
  * @param h - hit points 
  */
  public Entity(String n, int h) {
    name = n;
    maxHp = h;
    hp = maxHp;
  }
  /**
  * getName - gets name of entity and returns it 
  * @return name - returns name
  */
  public String getName() {
    return name;
  }
  /**
  * getHP - gets the hp of entity and returns it
  * @return hp - returns hit points
  */
  public int getHP() {
    return hp;
  }
  /**
  * getMaxHP - gets the maximum number of hit points
  * @return maxHP - returns max hp
  */
  public int getMaxHP() {
    return maxHp;
  }

  public abstract String attack(Entity e); //Hero and Enemy will overwrite this method

  /**
  * heal - increase the entity's hp but doesn't pass the max hp allowed
  * @param h - passing number of hp to increase 
  */
  public void heal(int h) {
    hp += h;
    if (hp > maxHp) {
      hp = maxHp;
    }
  }
  /**
  * takeDamage - decrease the entity's hp by the amount passed but not below 0
  * @param d - amount of damage to do to hp
  */
  public void takeDamage(int d) {
    hp -= d; //reduce hp with damage passed
    if(hp <= 0)
    {
      hp = 0;
    }
  }
  /**
  * toString - displays the name of the entity with hp and maxHP 
  * @return - returns name, hp, and maxHP 
  */
  public String toString() {
    return (name + "\n" + "HP: " + hp + "/" + maxHp);
  }
}