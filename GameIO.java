import java.io.*;
/**
 * Provide a command line interface for the CODE game.
 * 
 * @author A.A.Marczyk
 * @version 02/02/14
 */
public class GameIO
{
    private static Game gp ;
    private static BufferedReader myIn = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)
    {
        int choice;
        String playerName;

        try
        {
            System.out.println("Enter player's name");
            String s = myIn.readLine();
            gp = new Player(s); // create Player
            choice = -1;
            while (choice != 0 && !gp.hasLost())
            {
                choice = getMenuItem();
                if (choice == 1)
                {
                    System.out.println(gp.getAllChampionsForHire());
                }
                else if (choice == 2)
                {
                    System.out.println(gp.getArmy());
                }
                else if (choice == 3)
                {
                    System.out.println("Enter Champion's name");
                    String nme = (myIn.readLine()).trim();
                    if(!gp.hasLost())
                    {
                        System.out.println(gp.hireChampion(nme));
                    }
                    else
                    {
                        System.out.println("You have lost the game - quit ");
                    }
                } 
                else if (choice == 4)
                {
                    System.out.println("Enter Champion's name");
                    String nme = (myIn.readLine()).trim();
                    if(gp.getChampion(nme) != null)
                    {
                        boolean result = gp.dismissChampion(nme);
                        if(result)
                        {
                            System.out.println("\nChampion " + nme +
                                " dismissed" + "\nTreasury: " + gp.getMoney());
                        }
                        else
                        {
                            System.out.println("Dismissal not performed");
                        }
                    }
                }
                else if (choice == 5)
                {   
                    System.out.println("Enter Champion's name");
                    String nme = (myIn.readLine()).trim();
                    boolean result = gp.restoreChampion(nme);
                    if(result)
                    {
                        System.out.println("\nChampion " + nme +
                            " restored" + "\nTreasury: " + gp.getMoney());
                    }
                    else
                    {
                        System.out.println("Restoration not performed");
                    }
                }
                else if (choice == 6)
                {   
                    System.out.println("Enter Champion's name");
                    String nme = (myIn.readLine()).trim();
                    System.out.println(gp.getChampion(nme));
                }
                else if (choice ==7)
                {
                    System.out.println("Enter number of the challenge");
                    String chal = myIn.readLine();
                    int number = Integer.parseInt(chal);
                    System.out.println(gp.meetChallenge(number));                    
                }
                else if (choice == 8)
                {
                    System.out.println(gp.toString());
                }
                else if (choice == 9)
                {
                    System.out.println(gp.getAllChallenges());
                }
                else if (choice == 10)
                {
                    System.out.println("Treasury: " + gp.getMoney());
                }
                else if (choice == 11)
                {
                    System.out.println("Write to file");
                    System.out.println("Enter filename");
                    String nme = (myIn.readLine()).trim();
                    gp.saveGame(nme);
                }
                else if (choice == 12)
                {
                    System.out.println("Restore from file");
                    System.out.println("Enter filename");
                    String nme = (myIn.readLine()).trim();
                    Game gp2 = gp.restoreGame(nme);
                    System.out.println(gp2.toString());               
                }
                else if ( choice == 0)
                {
                    System.out.println("Quitting");
                }
            } 
            System.out.println(gp.toString());
        }
        catch (IOException e) {System.out.println (e);} 

    }

    private static int getMenuItem()throws IOException
    {   int option = -1;  
        System.out.println("Main Menu");
        System.out.println("1. list all champions for hire");
        System.out.println("2. list my army of champions"); 
        System.out.println("3. hire a champion");
        System.out.println("4. dismiss a champion");
        System.out.println("5. restore a champion");
        System.out.println("6. get a champion");
        System.out.println("7. meet a challenge");
        System.out.println("8. view my state");
        System.out.println("9. list all challenges");
        System.out.println("10. show state of treasury");
        System.out.println("11. save this game");
        System.out.println("12. restore a game");
        System.out.println("0. quit");

        while (option < 0|| option  > 12)
        {
            System.out.println("Enter the number of your choice");
            option =  Integer.parseInt(myIn.readLine());
        }
        return option;        
    }  
}