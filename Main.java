/**
Group: Chris Nguyen, Andrew Bae, Evelyn Lucero Frias
Date: May 4th, 2021
Description: Program that allows the user to go through a dungeon maze and attack monsters that he/she encounters on the way
*/
import java.awt.*;
import java.util.Scanner;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("Greetings!, What is your name traveler?");
    Hero hero = new Hero(in.next()); //creation of hero (player)
    boolean quits = true;

    while (hero.getHP() != 0 && quits){ //keep running while hero is alive and doesn't quit

      System.out.println(hero.toString()); //print hero information with HP
      //Menu for directions to move through maze
      System.out.println("1. Go North");
      System.out.println("2. Go South");
      System.out.println("3. Go East");
      System.out.println("4. Go West");
      System.out.println("5. Quit");
      int option = CheckInput.getIntRange(1, 5);

      char d = ' ';

      switch(option){ // Direction in which Hero moves
        case 1: 
          if((int) (hero.getLoc().getX() - 1) >= 0){
            Map.getInstance().reveal(new Point((int) hero.getLoc().getX() - 1, (int) hero.getLoc().getY()));
            if(Map.getInstance().getCharAtLoc(hero.getLoc()) != 's' && Map.getInstance().getCharAtLoc(hero.getLoc()) != 'f')
            {
              Map.getInstance().removeCharAtLoc(hero.getLoc());
            }
          }
          d = hero.goNorth();
          break;
        case 2:
          if((int) (hero.getLoc().getX() + 1) < 5){
            Map.getInstance().reveal(new Point((int) hero.getLoc().getX() + 1, (int) hero.getLoc().getY()));
              if(Map.getInstance().getCharAtLoc(hero.getLoc()) != 's' && Map.getInstance().getCharAtLoc(hero.getLoc()) != 'f')
              {
                Map.getInstance().removeCharAtLoc(hero.getLoc());
              }
          }
          d = hero.goSouth();
          break;
        case 3:
          if((int) (hero.getLoc().getY() + 1) < 5){
            Map.getInstance().reveal(new Point((int) hero.getLoc().getX(), (int) hero.getLoc().getY() + 1));
            if(Map.getInstance().getCharAtLoc(hero.getLoc()) != 's' && Map.getInstance().getCharAtLoc(hero.getLoc()) != 'f')
            {
              Map.getInstance().removeCharAtLoc(hero.getLoc());
            }
          }
          d = hero.goEast();
          break;
        case 4:
          if((int) (hero.getLoc().getY() - 1) >= 0){
            Map.getInstance().reveal(new Point((int) hero.getLoc().getX(), (int) hero.getLoc().getY() - 1));
            if(Map.getInstance().getCharAtLoc(hero.getLoc()) != 's' && Map.getInstance().getCharAtLoc(hero.getLoc()) != 'f')
            {
              Map.getInstance().removeCharAtLoc(hero.getLoc());
            }
          }
          d = hero.goWest();
          break;
          case 5:
          quits = false;
          break;
      } //end switch
      //Instructions based on hero's directions
      switch (d) {
        case 'x': 
          System.out.println("Location was out of bounds\n");
          break;
        case 'n':
          System.out.println("Nothing here\n");
          break;
        case 's':
          System.out.println("You are back at the start\n");
          store(hero);
          break;
        case 'f':
          System.out.println("You found the exit. \n");
          if(hero.hasKey()) //if hero has a key can move to next level
          {
            System.out.println("Proceeding to the next level.");
            hero.useKey(); //use the key
            hero.levelUp(); //level up
          } else
          {
            System.out.println("However, you don't have a key...");
          }
          break;
        case 'i':
          int droprate = (int) (Math.random() * 10) + 1; // Generate rand num from 1-10 for key and hp potion drop rates
          if (droprate <= 5){
            System.out.println("Congrats, You found a key! You picked it up. \n");
            hero.pickUpKey();
          }
          else { // 50% chance for healing potion 
            System.out.print("Congrats, You found a heal potion!.");
            if(hero.getHP() == hero.getMaxHP()) { // if hp == hpmax doesn't increase
              System.out.println(" But it had no effect...\n");
            } 
            else {
              System.out.println(" You drink it to restore your health\n");
            }
              hero.heal(25); //increasing hp to 25 which is max
          }
          break;
        case 'm':
          EnemyGenerator enemy = new EnemyGenerator(); //selecting a random enemy
          monsterRoom(hero, enemy.generateEnemy(hero.getLevel())); //monster shows up
          break;      
      }
    }
  }
  /**
  * MonsterRoom - displays the enemy and asks the player if he wants to fight * or run away
  * @param h - passing hero's information
  * @param e - passing enemy's information
  * @return - true if hero is alive, false if not.
  */
  public static boolean monsterRoom(Hero h, Enemy e) {
    Scanner in = new Scanner(System.in);
        
    System.out.print("You've encountered a ");
    boolean runAway = false;
    boolean alive = true;
    while (h.getHP() > 0 && e.getHP() > 0 && runAway == false && alive == true){
      // Call individual parameters instead of toString in order to get correct titles for enemy
      System.out.println(e.getName()); //adds name of enemy
      System.out.println("HP: " + e.getHP() + "/" + e.getMaxHP()); //adds hp of enemy

      System.out.println("1. Fight");
      System.out.println("2. Run away");
      int choice = in.nextInt();

      if (choice == 1) // If Hero fights the enemy
      {
        alive = fight(h, e); 
      }
      else if (choice == 2) // If hero runs away
      {
        runAway = true; 
        int ranDir = ((int) (Math.random() * 4)) + 1; //selects a random number to take a direction
        char d = ' ';
        //directions based on the random number chosen
        switch(ranDir){
          case 1: 
           if((int) (h.getLoc().getX() - 1) >= 0){
              Map.getInstance().reveal(new Point((int) h.getLoc().getX() - 1, (int) h.getLoc().getY()));
              d = h.goNorth();
            } else 
            {
              Map.getInstance().reveal(new Point((int) h.getLoc().getX() + 1, (int) h.getLoc().getY()));
              d = h.goSouth();
            }
            break;
          case 2:
            if((int) (h.getLoc().getX() + 1) < 5){
              Map.getInstance().reveal(new Point((int) h.getLoc().getX() + 1, (int) h.getLoc().getY()));
              d = h.goSouth();
            } else 
            {
              Map.getInstance().reveal(new Point((int) h.getLoc().getX() - 1, (int) h.getLoc().getY()));
              d = h.goNorth();
            }
            break;
          case 3:
            if((int) (h.getLoc().getY() + 1) < 5){
              Map.getInstance().reveal(new Point((int) h.getLoc().getX(), (int) h.getLoc().getY() + 1));
              d = h.goEast();
            } else 
            {
              Map.getInstance().reveal(new Point((int) h.getLoc().getX(), (int) h.getLoc().getY() - 1));
              d = h.goWest();
            }
            break;
          case 4:
            if((int) (h.getLoc().getY() - 1) >= 0){
              Map.getInstance().reveal(new Point((int) h.getLoc().getX(), (int) h.getLoc().getY() - 1));
              d = h.goWest();
            } else 
            {
              Map.getInstance().reveal(new Point((int) h.getLoc().getX(), (int) h.getLoc().getY() + 1));
              d = h.goEast();
            }
            break;
        }//end switch
        //menu depending on location
        switch (d) {
          case 'x': 
            System.out.println("Location was out of bounds\n");
            break;
          case 'n':
            System.out.println("Nothing here\n");
            break;
          case 's':
            System.out.println("You are back at the start\n");
            store(h);
            break;
          case 'f':
            System.out.println("You found the exit.\n");
            if(h.hasKey()) //if hero has a key can move to next level
            {
              System.out.println("Proceeding to the next level.");
              h.useKey(); //use key to enter next level
              h.levelUp(); //increase level
            } else
            {
              System.out.println("However, you don't have a key...");
            }
            break;
          case 'i':
            int droprate = (int) (Math.random() * 10) + 1; // Generate rand num from 1-10 for key and hp potion drop rates
            if (droprate <= 5) { // 50% chance to obtain key from item rooms
              System.out.println("Congrats, You found a key! You pick it up. \n");
              h.pickUpKey();
            }
            else { // 50% chance for healing potion 
              System.out.print("Congrats, You found a heal potion!.");
              if(h.getHP() == h.getMaxHP()) { // if hp == hpmax doesn't increase
                System.out.println(" But it had no effect...\n");
              } 
              else {
                System.out.println(" You drink it to restore your health\n");
              }
              h.heal(25); //increasing hp to 25 which is max
            }
            break;
          case 'm':
            EnemyGenerator enemy = new EnemyGenerator();
            monsterRoom(h, enemy.generateEnemy(h.getLevel()));
            break;      
        }
      }
    }
    if (alive) // Checks if hero is still alive at the end
      return true;
    else
      return false;
  }
  /** Fight - allows the user to do a physical or magical attack to the enemy. * Enemy also attacks back if alive.
  * @param h - passing hero's information
  * @param e - passing enemy's information
  * @return - true if hero still alive, false if not.
  */
  public static boolean fight(Hero h, Enemy e)
  {
    Scanner in = new Scanner(System.in);
    System.out.println("1. Physical Attack");
    System.out.println("2. Magical Attack");
    int attack = CheckInput.getIntRange(1, 2);
    
    if (attack == 1)
    {
      System.out.println(h.attack(e)); //hero does a physical attack by calling attack method
    }
    else if (attack ==2)
    {
      System.out.println(h.MAGIC_MENU); //menu to choose what magical attack is wanted
      int magical = CheckInput.getIntRange(1, 3);;

      switch(magical){
        case 1:
          System.out.println(h.magicMissile(e)); //calling magical Missile attack against enemy
          break;
        case 2:
          System.out.println(h.fireball(e)); //calling fireball attack against enemy
          break;
        case 3:
          System.out.println(h.thunderclap(e)); //calling thunderclap attack against enemy
          break;
      }
    }

    if(e instanceof Enemy && e.getHP() > 0) 
    {
      System.out.println(e.getName() + e.attack(h)); //enemy attack method called against hero
    }

    if(e.getHP() == 0) //if enemy was defeated by hero
    {
      System.out.println("You have defeated the " + e.getName());
      int rand = ( int )( Math.random() * 80000) + 3; // generate the random number for gold dropped from enemy
      System.out.println("Congrats, You earned " + rand + " gold!");
      h.collectGold(rand); // Hero collects the money
    }

    if(h.getHP() == 0) //if hero is defeated 
    {
      return false;
    }
    return true;
  }
  /** Store - allows hero to purchase heal potions or keys using the gold collected
  * @param h - passing hero's information
  */  
  public static void store (Hero h)
  {
    //store menu
    System.out.println("Hello! Welcome to our store, what would you like to buy today?");
    System.out.println("1. Health Potions");
    System.out.println("2. Keys");
    System.out.println("3. Exit");
    int userChoice = CheckInput.getIntRange(1, 3);
    	
    switch(userChoice) {
    	case 1:
    		if (h.getGold() >= 25) //if hero has more than 25 gold
    		{
          if(h.getHP() == h.getMaxHP()) { // if hp == hpmax doesn't increase
            System.out.println("You don't need a health potion...\n");
          } 
          else {
            h.spendGold(25); //spend 25 gold from hero
            h.heal(50); //heal hero with heal potion
            System.out.println("Congrats, you bought a health potion\n");
          }
    		}
    		else
    		{
    			System.out.println("You don't have enough gold to purchase\n");
    		}
    		break;
    	case 2:
    		if (h.getGold() >= 50) //if hero has more than 50 gold
    		{
    			h.spendGold(50); //use/spend 50 gold from hero
    			h.pickUpKey(); //pick up key purchased for hero
    			System.out.println("Congrats, you bought a key\n");
    		}
    		else
    		{
    			System.out.println("You don't have enough gold to purchase\n");
    		}
    		break;
    	case 3:
    		break;
    }
  }
}