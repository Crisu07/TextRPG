import java.util.Scanner;
import java.io.*;
import java.io.IOException;
import java.math.*;
import java.util.*;
import java.awt.*;
/**
 * Map.java - Creates the map for the game
 */
public class Map
{
    // instance variables - replace the example below with your own
    private char[][] map;
    private boolean[][] revealed;
    private static Map instance;

    /**
     * Constructor for objects of class Map
     */
    private Map()
    {
      map = new char[5][5];
      revealed = new boolean[5][5];
    }

    /**
    * loadMap - loads the map for the game 
    * @param mapNum - passes the number of map to load
    */
    public void loadMap(int mapNum)
    {
      try
      {
        int count = 0;
        File file = new File("Map" + mapNum + ".txt");
        Scanner inp = new Scanner(file);
        while(inp.hasNext())
        {
          StringTokenizer tempString = new StringTokenizer(inp.nextLine());
          for(int x = 0; x < 5; x++)
          {
            map[count][x] = tempString.nextToken().charAt(0);
          }
          count++;
        }
        revealed = new boolean[5][5];
      } catch(FileNotFoundException f)
      {
        System.out.println("Map could not be loaded");
      }
    }
    /**
    * getCharAtLoc - gets the letter at specific location 
    * @param p - point passed in from map
    */
    public char getCharAtLoc(Point p)
    {
      char temp = map[(int) p.getX()][(int) p.getY()];
      return temp;
    }

    /**
    * getInstance - if no instance, then creates a new map, otherwise returns the existing one
    * @return - returns instance 
    */
    public static Map getInstance()
    {
      if(instance == null){
        instance = new Map();
      }
      return instance;
    }
    /**
    * mapToString - creates the strings for the map depending on the point passed in 
    * @param p - point passed in 
    * @return - returns string for the point
    */
    public String mapToString(Point p)
    {
      String ans = "";
      for(int x = 0; x < map.length; x++)
      {
        for(int y = 0; y < map[0].length; y++)
        {
          if(x == (int)p.getX() && y == (int)p.getY())
          {
            ans += "*";
          } else if(revealed[x][y] && map[x][y] == 's')
          {
            ans += "s";
          } else if(revealed[x][y] && map[x][y] == 'm')
          {
            ans += "m";
          } else if(revealed[x][y] && map[x][y] == 'i')
          {
            ans += "i";
          } else if(revealed[x][y] && map[x][y] == 'f')
          {
            ans += "f";
          } else if(revealed[x][y] && map[x][y] == 'n')
          {
            ans += "n";
          } else
          {
            ans += "x";
          }

          if(y != map[0].length - 1)
          {
            ans += " ";
          }
        }
        ans += "\n";
      }
      return ans;
    }
    /**
    * findStart - define start of the location
    * @return - returns location of point 
    */
    public Point findStart()
    {
      for(int x = 0; x < map.length; x++)
      {
        for(int y = 0; y < map[0].length; y++)
        {
          if(map[x][y] == 's')
          {
            reveal(new Point(x, y));
            return new Point(x, y);
          }
        }
      }
      return null;
    }
    /**
    * reveal - reveals point on map 
    */
    public void reveal(Point p)
    {
      revealed[(int) p.getX()][(int) p.getY()] = true;
    }
    /**
    * removeCharAtLoc - removes the char already existing and replaces with n 
    * @param p - point passed in to remove
    */
    public void removeCharAtLoc(Point p)
    {
      map[(int) p.getX()][(int) p.getY()] = 'n';
    }
}
