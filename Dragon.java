
/**
 * A child class of the Champion class which has its own unique fields and methods
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dragon extends Champion implements java.io.Serializable
{
    private boolean canTalk;

    /**
     * Constructor for objects of class Dragon
     * @param name - The name of the Dragon
     * @param talk - True or false if the Dragon talks or not 
     */
    public Dragon(String name, boolean talk)
    {
        super(name);
        skillLevel = 7;
        hireFee = 500;
        canTalk = talk;
        champState = ChampionState.FORHIRE;
        if(canTalk == true)
        {
            challType.add(ChallengeType.FIGHT);
            challType.add(ChallengeType.MYSTERY);
        }
        else
        {
            challType.add(ChallengeType.FIGHT);
        }
    }

    /**
     * Returns true or false if the Dragon speaks or not
     * @return true or false if the Dragon speaks or not
     */
    public boolean dragonCanTalk()
    {
        return canTalk;
    }

    /**
     * Returns all unique information about the Dragon class
     * @return all unique information about the Dragon class
     */
    public String toString()
    {
        return super.toString() + "Can Talk: " + canTalk;
    }
}
