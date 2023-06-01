import java.util.Random;
/**
*EnemyGenerator is a Factory class that will randomly generate an enemy every time its generator method is called.
*This should be used to generate enemies to allow enemy creation to happen a runtime and to have cleaner code in the main class.
*/
public class EnemyGenerator {
	/**Will generate a random number to pick a random enemy.*/
  private Random rand;
    
  /**
  *EnemyGenerator is a Factory class that will randomly generate an enemy every time its generator method is called.
  *This should be used to generate enemies to allow enemy creation to happen a runtime and to have cleaner code in 
  *the main class.
  */
  public EnemyGenerator() {
    rand = new Random();
  }// end default constructor()
	
  /**Generates a random enemy and decorates it based on the hero's current level. 
  * @param heroLevel The current level of the hero.
  * @return A randomly generated enemy.
  */
  public Enemy generateEnemy(int herolevel)
  {
    Enemy enemy;
    int rand = (int) (Math.random() * 4) + 1; //selects random number 1-4

    //depending on random number a new enemy will be created
    if (rand == 1){
      enemy = new Troll();
    }

    else if (rand == 2) {
      enemy = new Orc();
    }

    else if (rand == 3){
      enemy = new Goblin();
    }

    else{
      enemy = new Froglok();
    }

    if (herolevel > 1) { //hero level greater than 1
      int num = ( int ) (Math.random() * 2) + 1; //generate random number 1-2
      int loop = herolevel - 1;

      if (num == 1) {
        for(int i = 0; i < loop; i++)
        {
          enemy = new Warlock(enemy); //decorate enemy with Warlock
        }
      }
      else {
        for(int i = 0; i < loop; i++){
          enemy = new Warrior(enemy); //decorate enemy with Warrior
        }
      }
    }
    return enemy;
  }
}