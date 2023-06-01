/**
* Magical.java - defines magical abilities, they get implemented by Hero and * * MagicalEnemy.
*/
interface Magical {

	public static final String MAGIC_MENU = "1.Magic Missile\n2.Fireball\n3.Thunderclap"; //menu to choose magical attack
	
	public String magicMissile(Entity e); //method for magical ability 1

	public String fireball (Entity e); //method for magical ability 2
  
	public String thunderclap(Entity e); //method for magical ability 3
	
}