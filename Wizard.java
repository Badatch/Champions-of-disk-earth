
/**
 * A child class of the Champion class with its own unique fields and methods
 * 
 * @author Harpreet Badatch - 14091594 & Nadeem Qureshi - 14086267
 * @version 03/03/14
 */
public class Wizard extends Champion implements java.io.Serializable
{
    private boolean necromancer;
    private String spellSpeciality;

    /**
     * Constructor for creating a Wizard class which extend champion
     * @param name - The name of the wizard champion
     * @param sLvl - The skill level of this champion
     * @param necro - True or false if this wizard is a necromancer or not
     * @param spec - The name of the spell that the Wizard specialises in
     */
    public Wizard(String name, int sLvl, boolean necro, String spec)
    {
        super(name);
        skillLevel = sLvl;
        necromancer = necro;
        if(necro == true)
        {
            hireFee = 400;
        }
        else
        {
            hireFee = 300;
        }
        spellSpeciality = spec;
        champState = ChampionState.FORHIRE;
        challType.add(ChallengeType.MAGIC);
        challType.add(ChallengeType.MYSTERY);
    }

    /**
     * Returns true or false if the wizard is a necromancer or not
     * @return true or false if the wizard is a necromancer or not
     */
    public boolean getNecromancer()
    {
        return necromancer;
    }

    /**
     * Returns the Wizard's spell speciality
     * @return the Wizard's spell speciality
     */
    public String getSpeciality()
    {
        return spellSpeciality;
    }

    /**
     * Returns all information unique to the Wizard class
     * @return all information unique to the Wizard class
     */    
    public String toString()
    {
        return super.toString() +   
        "Necromancer: " + necromancer +
        "\nSpeciality: " + spellSpeciality;
    }
}