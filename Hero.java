/**
* Hero.java - Describes the character that represents the user
*/

import java.awt.*;

public class Hero extends Entity implements Magical
{
  //private Map map; //creates a map
  private Point loc; //creates from point class
  private int level; //level 
  private int gold; //gold
  private int key; // key

  /**
  * Constructs the hero with name, new map, hp, and start at level 1
  * @param n - pass in hero's name
  */
  Hero(String n)
  {
    super(n, 50);
    level = 1;
    //map = new Map(); //creates new map 
    try{
      Map.getInstance().loadMap(level % 4); //loads map
    } catch (Exception e)
    {
      System.out.println("Map could not be located");
    }
    loc = Map.getInstance().findStart(); //starts location of map
  }
  /**
  * hasKey - returns if hero has a key or not 
  * @return true or false if hero has key
  */
  public boolean hasKey()
  {
    if(key > 0){
      return true;
    }
    return false;
  }
  /**
  * useKey - if key is used is subtracted from total keys, returns true or false if used 
  * @return true or false if key used
  */
  public boolean useKey()
  {
    if(hasKey()){
      key--;
      return true;
    }
    return false;
  }
  /**
  * pickUpKey - collects another key for hero
  */
  public void pickUpKey()
  {
    key++;
  }
    
  /**
  * toString - display name, level, location of the map 
  * @return - name, level, location 
  */
  public String toString()
  {
    return super.toString() + "\n" + "Level: " + level + "\n" + "Gold: " + gold + "\n" + "Keys: " + key + "\n" + Map.getInstance().mapToString(loc);
  }
  /**
  * attack - returns a random number of damage done
  * @param e - passes entity 
  * @return name and damage done.
  */
  public String attack(Entity e)
  {
    int attack = (int) (Math.random() * 3) + 1; //random number to do damage
    e.takeDamage(attack); //calls takeDamage method to create damage with the number (attack) generated
    return "\n" + this.getName() + " attacks " + e.getName() + " for " + attack + " damage";
  }

  /* Magic Attacks */

  /**
  * magicMissile - type of magical attack to do damage
  * @param e - passes entity
  * @return x - name and damage done
  */
  public String magicMissile(Entity e) {
    int damage = (int) (Math.random() * 2) + 1; // Calculate damage number between 1 and 2
    e.takeDamage(damage); // apply damage calculations to hp
    String d = String.valueOf(damage);
    String x = "\n" + this.getName() + " attacks " + e.getName() + " with Magic Missiles for " + d + " damage";
    return (x);
  }
  /**
  * Fireball - type of magical attack to do damage
  * @param e - passes entity
  * @return x - name and damage done
  */
  public String fireball(Entity e) {
    int damage = (int) (Math.random() * 3) + 1; // damage range 1-3
    e.takeDamage(damage);
    String d = String.valueOf(damage);
    String x = "\n" + this.getName() + " attacks " + e.getName() + " with Fireball for " + d + " damage";
    return (x);
  }
  /**
  * thunderclap - type of magical attack to do damage
  * @param e - passes entity
  * @return x - name and damage done
  */
  public String thunderclap(Entity e) {
    int damage = (int) (Math.random() * 3); // damage range 0-3
    e.takeDamage(damage);
    String d = String.valueOf(damage);
    String x = "\n" + this.getName() + " attacks " + e.getName() + " with Thunderclap for " + d + " damage";
    return (x);
  }
  /**
  * levelUp - increments the hero's level and loads new map 
  */
  public void levelUp()
  {
    try
    {
      // Need to check for key
      Map.getInstance().loadMap((level % 3) + 1);
      level++; //increases hero's level
    } catch(Exception E)
    {
      System.out.println("Error: Map Not Found");
      System.out.println(level % 4);
    }
  }
  /**
  * goNorth - Changes the heroâ€™s location, reveals it, gets the character at
  * that location, removes it and then return its.
  * @return location with an 'x'
  */
  public char goNorth()
  {
    if((int) (loc.getX() - 1) >= 0)
    {
      loc = new Point((int) loc.getX() - 1, (int) loc.getY());
      return Map.getInstance().getCharAtLoc(loc);
    }
    return 'x';
  }
  /**
  * goSouth - Changes the heroâ€™s location, reveals it, gets the character at
  * that location, removes it and then return its.
  * @return location with an 'x'
  */
  public char goSouth()
  {
    if((int) (loc.getX() + 1) < 5)
    {
      loc = new Point((int) loc.getX() + 1, (int) loc.getY());
      return Map.getInstance().getCharAtLoc(loc);
    }
    return 'x';
  }
  /**
  * goEast - Changes the heroâ€™s location, reveals it, gets the character at
  * that location, removes it and then return its.
  * @return location with an 'x'
  */
  public char goEast()
  {
    if((int) (loc.getY() + 1) < 5)
      {
        loc = new Point((int) loc.getX(), (int) loc.getY() + 1);
        return Map.getInstance().getCharAtLoc(loc);
      }
    return 'x';
  }
  /**
  * goWest - Changes the heroâ€™s location, reveals it, gets the character at
  * that location, removes it and then return its.
  * @return location with an 'x'
  */
  public char goWest()
  {
    if((int) (loc.getY() - 1) >= 0)
      {
        loc = new Point((int) loc.getX(), (int) loc.getY() - 1);
        return Map.getInstance().getCharAtLoc(loc);
      }
    return 'x';
  }
    
  /* Gold methods */

  /**
  * getGold - returns the gold hero has 
  * @return gold - returns hero's gold
  */
  public int getGold() {
    	return gold;
  }
  /**
  * collectGold - collects the gold hero wins 
  * @param g - gold collected by hero 
  */
  public void collectGold(int g) {
    gold += g;
  }
  /**
  * spendGold - subtract the gold hero spends 
  * @param g - gold spent passed in 
  */  
  public void spendGold(int g) {
    gold -= g;
  }
  /**
  * getLevel - gets the hero's level in the game 
  * @return - returns hero's level
  */
  public int getLevel(){
    return level;
  }
  /**
  * getLoc - gets hero's location 
  * @return hero's location
  */
  public Point getLoc(){
    return loc;
  }
}