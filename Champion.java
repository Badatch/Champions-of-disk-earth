import java.util.*;
import java.io.*;
/**
 * Abstract class Champion. The parent class for Warrior, Wizard and Dragon which holds all the 
 * fields that they have in common.
 * 
 * @author Harpreet Badatch - 14091594 & Nadeem Qureshi - 14086267
 * @version 03/03/14
 */
public abstract class Champion implements Serializable
{
    private String name;
    protected int skillLevel;
    protected int hireFee;
    protected ChampionState champState;
    //protected ArrayList<ChampionState> champState = new ArrayList<ChampionState>();
    protected ArrayList<ChallengeType> challType = new ArrayList<ChallengeType>();

    /**
     * Contructor for the champion class.
     * @param name - The name of the champion
     */
    public Champion(String name)
    {
        this.name = name;
        skillLevel = 0;
        hireFee = 0;
    }

    /**
     * Returns the name of the champion
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the skill level of the champion
     */
    public int getSkillLevel()
    {
        return skillLevel;
    }

    /**
     * Returns the hire fee of the champion
     */
    public int getHireFee()
    {
        return hireFee;
    }

    /**
     * Returns the state of the champion
     */
    public String getState()
    {
        return champState.toString();
    }

    /**
     * Returns true or false if the champion can compete in a challege by using the challenge types 
     * @param type - The type of challenge to check
     */
    public boolean canCompete(String type)
    {
        for (ChallengeType t : challType)
        {
            if (type.equals(t.toString()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all information about the Champion class
     */
    public String toString()
    {
        return  "Name: " + name +
        "\nSkill Level: " + skillLevel +
        "\nHire Fee: " + hireFee +
        "\nChampion State:" + getState() + "\n";
    }
}

/**
 * Enumeration class ChampionState. This stores all the possible champion states so they cannot be changed
 * 
 * @author Harpreet Badatch - 14091594 & Nadeem Qureshi - 14086267
 * @version 03/03/14
 */
enum ChampionState implements Serializable
{
    FORHIRE(" For hire"), ACTIVE(" Active"),RESTING(" Resting"),DEAD (" Dead");
    private String state;

    /**
     * The constructor for the ChampionState enum class
     * @param st - The champion state
     */
    private ChampionState(String st)
    {
        state = st;
    }

    /**
     * Returns the champion state as a String
     */
    public String toString()
    {
        return state;
    }
}