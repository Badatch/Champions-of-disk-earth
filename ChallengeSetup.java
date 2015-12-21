import java.io.*;
/**
 * Allows user to set up their own challenges and save them to an object file.
 * 
 * @author Harpreet Badatch - 14091594 & Nadeem Qureshi - 14086267
 * @version 03/03/14
 */
public class ChallengeSetup implements Serializable
{    
    /**
     * Constructor for the ChallengeSetup class that can run outside of the BlueJ environment
     */
    public static void main(String[] args)
    {
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in)); 
        String fName = "challenges.obj";
        Challenge chall = null;

        try
        {
            FileOutputStream fos = new FileOutputStream(fName);
            ObjectOutputStream oos  = new ObjectOutputStream (fos);           

            System.out.println("Type of Challenge: 1-Fight, 2-Mystery, 3-Magic, 0-Finish");
            String s = brdr.readLine();
            int choice = Integer.parseInt(s);
            while(choice != 0)
            {
                if(choice == 1)
                {
                    System.out.println("Name:");
                    String nme = brdr.readLine();
                    try
                    {                        
                        System.out.println("Skill Level:");
                        String sr = brdr.readLine();
                        int sReq = Integer.parseInt(sr);
                        System.out.println("Reward:");
                        String r = brdr.readLine();
                        int rew = Integer.parseInt(r);
                        chall = new Challenge(ChallengeType.FIGHT, nme, sReq, rew);
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Value entered is NOT an integer. Enter Fight Challenge again.\n");
                        continue;
                    }
                }
                else if(choice == 2)
                {
                    System.out.println("Name:");
                    String nme = brdr.readLine();
                    try
                    {                        
                        System.out.println("Skill Level:");
                        String sr = brdr.readLine();
                        int sReq = Integer.parseInt(sr);
                        System.out.println("Reward:");
                        String r = brdr.readLine();
                        int rew = Integer.parseInt(r);
                        chall = new Challenge(ChallengeType.MYSTERY, nme, sReq, rew);
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Value entered is NOT an integer. Enter Mystery Challenge again.\n");
                        continue;
                    }
                }
                else if(choice == 3)
                {
                    System.out.println("Name:");
                    String nme = brdr.readLine();
                    try
                    {                        
                        System.out.println("Skill Level:");
                        String sr = brdr.readLine();
                        int sReq = Integer.parseInt(sr);
                        System.out.println("Reward:");
                        String r = brdr.readLine();
                        int rew = Integer.parseInt(r);
                        chall = new Challenge(ChallengeType.MAGIC, nme, sReq, rew);
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Value entered is NOT an integer. Enter Magic Challenge again.\n");
                        continue;
                    }
                }
                oos.writeObject(chall);
                System.out.println ("Type of Challenge: 1-Fight, 2-Mystery, 3-Magic, 0-Finish");
                s = brdr.readLine();
                choice = Integer.parseInt(s);
            }
            System.out.println("Challenge Setup complete.");
            oos.writeObject(null);
            oos.close();
        }
        catch(IOException e) 
        {
            System.out.println(e);
        }
    }
}
