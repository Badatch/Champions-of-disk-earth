import java.util.*;
import java.io.*;
/**
 * This class implements the behaviour expected from the CODE
 * system as required for 5COM0087 Cwk 3 - Feb 2014
 * 
 * @author A.A.Marczyk edited by Harpreet Badatch - 14091594 & Nadeem Qureshi - 14086267
 * @version 03/03/14
 */

public class Player implements Game, Serializable
{
    private String name;
    private int treasury;

    private HashMap<String, Champion> allChampionsForHire = new HashMap<String, Champion>();
    protected ArrayList<Challenge> allChallenges = new ArrayList<Challenge>();

    private HashMap<String, Champion> playerArmy = new HashMap<String, Champion>();
    private HashMap<String, Champion> deadChampions = new HashMap<String, Champion>();

    //**************** DiscEarth ************************** 
    /** Constructor requires the name of the player
     * @param player is the name of the player
     */  
    public Player(String player)
    {
        name = player;
        treasury = 1000;
        setupChampions();
        setupChallenges();
    }

    /** Constructor requires the name of the player and the
     * name of the file storing champions
     * @param player is the name of the player
     * @param filename name of file storing champions
     */  
    public Player(String player, String filename)
    {
        name = player;
        treasury = 1000;       
        readChampions(filename); // read from text file
        //comment for testing Task 5 
        //setupChallenges();
        // uncomment for testing Task 5
        readChallenges();
    }

    /**Returns the name of the player 
     * @return is the name of the player 
     **/ 
    public String getName()
    {
        return name;
    }

    /**Returns a String representation of the state of the game,
     * including the name of the player, state of the treasury,whether 
     * player has lost or is still OK, and the champions in the army 
     * (or, "No champions" if army is empty)
     * @return a String representation of the state of the game,
     * including the name of the player, state of the treasury, whether 
     * player has lost or is still OK, and the champions in the army 
     * (or, "No champions" if army is empty)
     **/
    public String toString()
    {
        String s = "Player: " + name ;
        s = s + "\nTreasury: " + treasury;        
        if (hasLost())
        {
            s = s + "\nYou have lost \n" ;
        }
        else
        {
            s = s + "\nYou are still OK \n" ;
        }
        // add the army to this String, or "no Champions in the army"
        return s + getArmy();
    }

    /** returns the amount of money in the treasury
     * @returns the amount of money in the treasury
     */
    public int getMoney()
    {
        return treasury;
    }

    /** returns true if treasury <=0 and the army has no champions 
     * who can be dismissed. 
     * @returns true if treasury <=0 and the army has no champions 
     * who can be dismissed. 
     */
    public boolean hasLost()
    {
        if(treasury <= 0 && playerArmy.isEmpty())
        {
            return true;
        }
        return false;
    }

    // ***************** Army of Champions ************************  
    /**Returns a String representation of all champions available for hire
     * @return a String representation of all champions available for hire
     **/
    public String getAllChampionsForHire()
    {   
        String s="";
        for(Champion champ: allChampionsForHire.values())
        {
            s += champ.toString() + "\n\n";
        }
        return s;
    }

    /** Returns details of a champion with the given name
     * @return details of a champion with the given name
     **/
    public String getChampion(String name)
    {
        if(allChampionsForHire.containsKey(name))
        {
            return allChampionsForHire.get(name).toString();
        }
        else if(playerArmy.containsKey(name))
        {
            return playerArmy.get(name).toString();
        }
        else if (!playerArmy.containsKey(name) && !allChampionsForHire.containsKey(name))
        {
            return name + " does not exist.";
        }
        return null;
    }

    // ***************** Army Champions ************************   
    /** Allows a champion to be hired for the army, if there 
     * is enough money in the treasury for their hire fee.The champion's 
     * state is set to "active"
     * @param name is the name of the champion
     * @return name and either "- not found" if champion not found,
     * or "- cannot be hired" if champion is not for hire,already hired/dead, "- hired and 
     * avialable" if champion hired, "- not enough money" if not enough money
     * in the treasury
     **/        
    public String hireChampion(String name)
    {   
        if(deadChampions.containsKey(name))
        {
            return name + " - is Dead.";
        }
        if(!allChampionsForHire.containsKey(name) && !isInArmy(name))
        {
            return name + " - not found.";
        }
        else if(isInArmy(name))
        {
            return name + " is already in army.";
        }
        Champion champ = allChampionsForHire.get(name);
        if(treasury < champ.getHireFee())
        {
            return name + " - not enough money.";
        }
        else if(isDead(name) || playerArmy.containsValue(champ))
        {
            return name + " - cannot be hired.";
        }
        else
        {
            playerArmy.put(champ.getName(), champ);
            champ.champState = ChampionState.ACTIVE;
            treasury -= champ.getHireFee();
            allChampionsForHire.remove(champ.getName());
            return name + " - hired and available.";
        }
    }

    /**Returns a String representation of the champions in the player's army
     * with an appropriate header, or the message "No champions hired"
     * @return a String representation of the champions in the player's army
     **/
    public String getArmy()
    {   
        String s = "********** Player's Army **********\n";
        if(playerArmy.isEmpty())
        {
            return "No Champions hired.";
        }
        else
        {
            for(Champion champ: playerArmy.values())
            {            
                s += champ.toString() + "\n\n";            
            }
        }
        return s + "\n"; 
    }

    /** Returns true if the champion with the name is in the player's army, 
     * false otherwise.
     * @param name is the name of the champion
     * @return true if the champion with the name is in the player's army, 
     * false otherwise.
     **/
    public boolean isInArmy(String name)
    {   
        for(Champion champ : playerArmy.values())
        {
            if(champ.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }

    /** Dismisses a champion from the army and add half of their hire fee 
     * to the treasury.Champion must be active or resting.Champion should
     * now be for hire.
     * pre-condition: isInArmy(name)and is not dead
     * @param name is the name of the champion
     * @return true if dismissed, else false
     **/
    public boolean dismissChampion(String name)
    {       
        Collection<Champion> col = playerArmy.values();
        for(Champion champ : col)
        {
            if(champ.getName().equals(name) && isInArmy(name) && (champ.champState == ChampionState.RESTING || champ.champState == ChampionState.ACTIVE))
            {
                champ.champState = ChampionState.FORHIRE;
                allChampionsForHire.put(champ.getName(), champ);
                playerArmy.remove(champ.getName());
                treasury += (champ.getHireFee() / 2);
                return true;
            }
        }
        return false;
    }

    /**Restores a champion to the army by setting their state to ACTIVE 
     * @param the name of the champion to be restored
     * @return true if restored, else false
     */
    public boolean restoreChampion(String name)
    {       
        if(isInArmy(name) && playerArmy.get(name).champState == ChampionState.RESTING)
        {
            playerArmy.get(name).champState = ChampionState.ACTIVE;
            treasury -= 50;
            return true;
        }
        return false;
    }

    /**Returns whether the Champion is dead or not with true or false
     * @param the name of the Champion
     * @return true if dead, else false
     */
    private boolean isDead(String name)
    {
        for(Champion champ : allChampionsForHire.values())
        {
            if(champ.getState().equals(" Dead"))
            {
                return true;
            }
        }
        return false;
    }

    /**Returns a message of the treasury if it is less than or equal to zero
     * @return a message of the treasury if it is less than or equal to zero
     */
    private String noTreasury()
    {
        String s = "";
        if(treasury <= 0)
        {
            s += "You have NO money in the treasury.";
        }
        return s;
    }

    //**********************Challenges************************* 
    /** returns true if the number represents a challenge
     * @param num is the reference number of the challenge
     * @returns true if the reference number represents a challenge
     **/
    public boolean isChallenge(int num)
    {
        if(num <= allChallenges.size() && num > 0)
        {
            return true;
        }
        return false;
    }

    /** Meets the challenge represented by the challenge number (or returns 
     * " - no such challenge").
     * Find a champion from the army who can meet the 
     * challenge and return a result which is one of the following: “Challenge 
     * won by...“ – add reward to treasury, set the champion to resting and add 
     * the name of champion, “Challenge lost as no champion available” – deduct 
     * reward from treasury,“Challenge lost on skill level”- deduct reward from 
     * treasury, the champion is killed, so add "<champion name> is dead" to the 
     * return String. If the challenge is lost and there is no money left, add 
     * "You have NO money in the treasury".
     * @param challNo is the reference number of the challenge
     * @return a String showing the result of meeting the challenge
     */
    public String meetChallenge(int challNo)
    {  
        if(!isChallenge(challNo))
        {
            return challNo + " - no such challenge.";
        }
        Collection<Champion> col = playerArmy.values();
        Challenge chall = allChallenges.get(challNo - 1);
        String s = "";
        for(Champion champ : col)
        {
            if(champ.champState == ChampionState.ACTIVE && champ.canCompete(chall.getChallType()) && champ.getSkillLevel() >= chall.getSkillRequired())
            {
                treasury += chall.getReward();
                champ.champState = ChampionState.RESTING;
                s += "Challenge won by " + champ.getName() + ".";
                s += noTreasury(); 
                return s;
            }
        }
        for(Champion champ : col)
        {
            if(champ.champState == ChampionState.ACTIVE && champ.canCompete(chall.getChallType()))
            {
                champ.champState = ChampionState.DEAD;
                treasury -= chall.getReward();
                s += "Challenge lost on skill level. " + champ.getName() + " is"+ ChampionState.DEAD + ".";
                s+= noTreasury();
                deadChampions.put(champ.getName(), champ);
                playerArmy.remove(champ.getName());
                return s;
            }
        }
        treasury -= chall.getReward();
        s+= "Challenge lost as no champion available.";
        s += noTreasury();
        return s;
    }

    /** Provides a String representation of a challenge given by challenge number
     * pre-condition isChallenge(num)
     * @param num the number of the challenge
     * @return returns a String representation of a challenge given by 
     * the challenge number
     **/
    public String getChallenge(int num)
    {   
        String s= "";
        if(isChallenge(num))
        {
            s += allChallenges.get(num-1).toString();
        }
        else 
        {
            s += "Not a valid challenge number";
        }
        return s;
    }

    /** Provides a String representation of all requests 
     * @return returns a String representation of of all requests
     **/
    public String getAllChallenges()
    {   
        String s="";
        for(Challenge chall : allChallenges)
        {
            s += chall.toString() + "\n\n";
        }  
        return s;
    }

    //*********************** Task 1 ***********************************************
    /**
     * Creates and adds the champions to the HashMap for all Champions given in the assignment
     */
    private void setupChampions()
    {   
        // depends on your collections
        Champion champ1 = new Wizard("Ganfrank", 7, true, "Transmutation");
        Champion champ2 = new Wizard("Rudolf", 6, true, "Invisibility");
        Champion champ3 = new Warrior("Elblond", 200, "Sword");
        Champion champ4 = new Warrior("Flimsi", 300, "Bow");
        Champion champ5 = new Dragon("Drabina", false);
        Champion champ6 = new Dragon("Golum", true);
        Champion champ7 = new Warrior("Argon", 900 , "Mace");
        Champion champ8 = new Wizard("Neon", 7, false, "Translocation");
        Champion champ9 = new Dragon("Xenon", true);

        allChampionsForHire.put(champ1.getName(), champ1);
        allChampionsForHire.put(champ2.getName(), champ2);
        allChampionsForHire.put(champ3.getName(), champ3);
        allChampionsForHire.put(champ4.getName(), champ4);
        allChampionsForHire.put(champ5.getName(), champ5);
        allChampionsForHire.put(champ6.getName(), champ6);
        allChampionsForHire.put(champ7.getName(), champ7);
        allChampionsForHire.put(champ8.getName(), champ8);
        allChampionsForHire.put(champ9.getName(), champ9);
    }

    /**
     * Creates and adds the challenges to the ArrayList for all Challenges given in the assignment
     */
    private void setupChallenges()
    {
        // depends on your collections
        Challenge chall1 = new Challenge(ChallengeType.MAGIC, "Borg", 3, 100);
        Challenge chall2 = new Challenge(ChallengeType.FIGHT, "Huns", 3, 120);
        Challenge chall3 = new Challenge(ChallengeType.MYSTERY, "Ferengi", 3, 150);
        Challenge chall4 = new Challenge(ChallengeType.MAGIC, "Vandals", 9, 200);
        Challenge chall5 = new Challenge(ChallengeType.MYSTERY, "Borg", 7, 90);
        Challenge chall6 = new Challenge(ChallengeType.FIGHT, "Goths", 8, 45);
        Challenge chall7 = new Challenge(ChallengeType.MAGIC, "Franks", 10, 200);
        Challenge chall8 = new Challenge(ChallengeType.FIGHT, "Sith", 10, 150);

        allChallenges.add(chall1);
        allChallenges.add(chall2);
        allChallenges.add(chall3);
        allChallenges.add(chall4);
        allChallenges.add(chall5);
        allChallenges.add(chall6);
        allChallenges.add(chall7);
        allChallenges.add(chall8);
    }

    /*******************************************************************************

    /************************ Task 5 ************************************************/
    /** reads data about champions from a text file and stores in collection of 
     * champions. Data in the file is  "comma separated" and so editable
     * @param fileName name of the file to be read
     */   
    private void readChampions(String fileName)
    { 
        try 
        {
            BufferedReader reader = new BufferedReader (new FileReader(fileName+".txt"));
            String readLine;
            while((readLine = reader.readLine()) != null)
            {
                String[] champInfo = readLine.split(",");
                if(champInfo[0].equals("warrior"))
                {
                    allChampionsForHire.put(champInfo[1], new Warrior(champInfo[1], Integer.parseInt(champInfo[2]), champInfo[3]));
                }
                else if(champInfo[0].equals("wizard"))
                {
                    allChampionsForHire.put(champInfo[1], new Wizard(champInfo[1], Integer.parseInt(champInfo[2]), Boolean.parseBoolean(champInfo[3]), champInfo[4]));
                }
                else if(champInfo[0].equals("dragon"))
                {
                    allChampionsForHire.put(champInfo[1], new Dragon(champInfo[1], Boolean.parseBoolean(champInfo[2])));
                }
            }
        }
        catch(IOException e) 
        {
            System.out.println("No file of that name");
        }
    }   

    //************************ Task 6 **********************************
    /** reads data about challenges from an object file and stores in collection of 
     * champions. Data in the file is not editable
     * @param fileName name of the file to be read
     */
    private void readChallenges()
    {
        try
        {
            FileInputStream fos = new FileInputStream("challenges.obj");
            ObjectInputStream oos  = new ObjectInputStream(fos);
            Challenge chall = null;
            chall = (Challenge)oos.readObject();
            System.out.println("The following challenges have been added:" + "\n");
            while(chall != null)
            {
                allChallenges.add(chall);
                chall = (Challenge)oos.readObject();
            }  
            oos.close();
        }
        catch(IOException e) 
        {
            System.out.println(e);
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e);
        }

        for(Challenge chall : allChallenges)
        {
            System.out.println(chall.toString() + "\n");
        }
    }

    // ********************   Task 6 object write/read  *********************
    /**
     * Saves the state of the game to the file with the given name 
     * @param filename the name of the file 
     */ 
    public void saveGame(String fname)
    {   // uses object serialisation         
        BufferedReader brdr = new BufferedReader(new InputStreamReader(System.in)); 
        String save = fname + ".obj";
        try
        {
            FileOutputStream fis = new FileOutputStream(save);
            ObjectOutputStream ois = new ObjectOutputStream(fis);            
            Player p = this;
            ois.writeObject(p);
            ois.close();
        }
        catch(IOException e) 
        {
            System.out.println(e);
        }
    }

    /** 
     * Restores the game from the file with the given name
     * @param filename the name of the file 
     */
    public Game restoreGame(String fname)
    {   // uses object serialisation         
        try
        {
            File restore = new File(fname + ".obj");
            if (!restore.exists()) 
            {
                restore.isFile();
                System.out.println("File doesn't exist.");
            }
            FileInputStream fis = new FileInputStream(restore);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Player p = (Player) ois.readObject();
            System.out.println("Game restored.");
            return p;
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e);
            return null;        
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found, re-check spelling or check if file exists.");
            return null;
        }
        catch(IOException e)
        {
            System.out.println(e);
            return null;
        }
        catch(NullPointerException e)
        {
            System.out.println(e);
            return null;
        }
    } 
}
