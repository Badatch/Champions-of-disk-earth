
/**
 * Creates Challenge objects.
 * 
 * @author Harpreet Badatch - 14091594 & Nadeem Qureshi - 14086267
 * @version 03/03/14
 */
public class Challenge implements java.io.Serializable
{
    private ChallengeType challType;
    private String enemy;
    private int skillRequired;
    private int reward;

    /**
     * Constructor for the Challenge class.
     * @param cType - The challenge type (Fight, Mystery, Magic)
     * @param nme = The name of the enemy
     * @param sReq - The skill required for the challenge to be met
     * @param rew - The reward for beating the challenge 
     */
    public Challenge(ChallengeType cType, String nme, int sReq, int rew)
    {
        challType = cType;
        enemy = nme;
        skillRequired = sReq;
        reward = rew;
    }

    /**
     * Returns the challenge enemy name
     * @return the challenge enemy name
     */
    public String getName()
    {
        return enemy;
    }

    /**
     * Returns the skill level for the challenge
     * @return the skill level for the challenge
     */
    public int getSkillRequired()
    {
        return skillRequired;
    }

    /**
     * Returns the reward for beating this challenge
     * @return the reward for beating this challenge
     */
    public int getReward()
    {
        return reward;
    }

    /**
     * Returns the challenge type as a String
     * @return the challenge type as a String
     */
    public String getChallType()
    {
        return challType.toString();
    }

    /**
     * Returns all information about this challenge
     * @return all information about this challenge
     */
    public String toString()
    {
        return  "Enemy Name: " + enemy +
        "\nChallenge Type: " + getChallType() +
        "\nSkill Required: " + skillRequired +
        "\nReward: " + reward;
    }
}

/**
 * Enumeration class ChallengeType. This stores the challenge types which can applied to champions
 * 
 * @author Harpreet Badatch - 14091594 & Nadeem Qureshi - 14086267
 * @version 03/03/14
 */
enum ChallengeType implements java.io.Serializable
{
    MAGIC("Magic"), FIGHT("Fight"), MYSTERY ("Mystery");
    private String type;

    /**
     * Constructor for the ChallengeType enum class
     * @param ty - The type of challenge
     */
    private ChallengeType(String ty)
    {
        type = ty;
    }

    /**
     * Returns the challenge type
     * @return the challenge type
     */
    public String toString()
    {
        return type;
    }
}
