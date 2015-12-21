
/**
 * The Tester Class.
 * 
 * @author Harpreet Badatch - 14091594 & Nadeem Qureshi - 14086267
 * @version 03/03/14
 */
public class Tester
{
    public static void main(String[] args)
    {
        Game CODE = new Player("Horatio");

        System.out.println(CODE.getName()); //returns the name of the player, Horatio
        System.out.println(CODE.toString()); //returns the state of the player
        System.out.println(CODE.getMoney()); //returns the player's treasury balance
        System.out.println(CODE.hasLost()); //returns if the player has lost, false
        System.out.println(CODE.getAllChampionsForHire()); //returns the HashMap Key of each Champion, their name
        System.out.println(CODE.getChampion("Steve")); //returns the name of a champion if they exist, Steve does not exist
        System.out.println(CODE.getArmy()); //retusn the player's army, currently there are no champions in the army
        System.out.println(CODE.hireChampion("Steve")); //not found
        System.out.println(CODE.hireChampion("Ganfrank")); //hired
        System.out.println(CODE.hireChampion("Ganfrank")); //is already in army 
        System.out.println(CODE.getChampion("Ganfrank")); //returns Ganfrank's details

        System.out.println(CODE.getArmy()); //returns the player's army, only Ganfrank is in the army
        System.out.println(CODE.isInArmy("Ganfrank")); //returns true because Ganfrank is in the army
        System.out.println(CODE.isInArmy("Argon")); //returns false because Argon is not in the army
        System.out.println(CODE.dismissChampion("Ganfrank")); //returns true becuase a champion has been successfully dismissed from the army
        System.out.println(CODE.dismissChampion("Steve")); //retuns false because Steve is does not exist and is not in the army
        //restoreChampion
        System.out.println(CODE.isChallenge(0)); //not a challenge
        System.out.println(CODE.isChallenge(1)); //Challenge lost because there is no champion in the army

        System.out.println(CODE.hireChampion("Xenon")); //Xenon is successfully hired
        System.out.println(CODE.meetChallenge(0)); //no such challenge
        System.out.println(CODE.meetChallenge(1)); //Challenge lost as no champion available because Xenon is a dragon
        System.out.println(CODE.meetChallenge(2));//"Challenge won by Xenon and he's resting
        System.out.println(CODE.hireChampion("Elblond")); //Elblond successfully hired
        System.out.println(CODE.meetChallenge(7)); //Challenge lost as no champion available, Elblond is a warrior; cannot do magic challenges
        System.out.println(CODE.restoreChampion("Xenon")); //Xenon successfully restored, returns true
        System.out.println(CODE.meetChallenge(5)); //Challenge won by Xenon, player has negative balance, Xenon is resting
        System.out.println(CODE.meetChallenge(8)); //Elblond dies, skill level is lower than requirement
        System.out.println(CODE.restoreChampion("Xenon")); //Xenon successfully restored, returns true
        System.out.println(CODE.restoreChampion("Xenon")); //Xenon already restored, returns false        
        System.out.println(CODE.getArmy()); //retuns the player's army, only Xenon is in it and is active
        System.out.println(CODE.meetChallenge(8)); //Xenon dies, skill level is lower than requirement
        System.out.println(CODE.hasLost()); //this happens in the GameIO class, returns true if the player has lost
    }
}
