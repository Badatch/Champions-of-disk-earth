
/**
 * A child class of the Champion class with its own unique fields and methods
 * 
 * @author Harpreet Badatch - 14091594 & Nadeem Qureshi - 14086267
 * @version 03/03/14
 */
public class Warrior extends Champion implements java.io.Serializable
{
    private String weapon;

    /**
     * Constructor for objects of class Warrior
     * @param name - The name of the Warrior
     * @param hFee - The hire fee of the Warrior
     * @param wpn - The weapon the Warrior uses
     */
    public Warrior(String name, int hFee, String wpn)
    {
        super(name);
        hireFee = hFee;
        skillLevel = hFee/100;
        weapon = wpn;
        champState = ChampionState.FORHIRE;
        challType.add(ChallengeType.FIGHT);
        challType.add(ChallengeType.MYSTERY);
    }

    /**
     * Returns the Warrior's weapon as a String
     * @return the Warrior's weapon as a String
     */
    public String getWeapon()
    {
        return weapon;
    }

    /**
     * Returns all information unique to the Warrior class
     * @return all information unique to the Warrior class
     */
    public String toString()
    {
        return super.toString() + "Weapon: " + weapon;
    }
}
