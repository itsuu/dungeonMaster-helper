/**
 * Name: Henry Wong
 * Student ID: 1057822
 * Course: CIS 2430
 * Assignment: A2
 * Email: hwong10@uoguelph.ca
 */

package dndlevel;

import dnd.models.Stairs;
import dnd.die.D20;
import java.util.ArrayList;

//java -cp build;lib/dnd-20190914.jar dndlevel.Level
//.:lib/dnd-20190914.jar:build menu

public final class Level {

    public int chamberCounter = 0;
    public int passageSectionCounter = 0;
    public int passageCheck = 0;
    public int diceRoll = -21;
    public D20 d20;
    public Chamber newChamber;
    public Door newDoor;
    public Passage newPassage = new Passage();
    public PassageSection newPassageSection;
    public Stairs newStairs;
    public ArrayList<Chamber> chamberList = new ArrayList<Chamber>();

    /**
     * To prevent and instance from being made.
     */
    public Level() {
        /*
         * chamberCounter = 0; passageSectionCounter = 0; passageCheck = 0; int diceRoll
         * = -21; D20 d20; Chamber newChamber; Door newDoor; Passage newPassage = new
         * Passage(); PassageSection newPassageSection; Stairs newStairs;
         * ArrayList<Chamber> chamberList = new ArrayList<Chamber>();
         */
    }

    /**
     *
     * @param args "Main" to create the level.
     */
    /*
     * public static void main(String[] args) { int chamberCounter = 0; int
     * passageSectionCounter = 0; int passageCheck = 0; int diceRoll = -21; D20 d20;
     * Chamber newChamber; Door newDoor; Passage newPassage = new Passage();
     * PassageSection newPassageSection; Stairs newStairs; ArrayList<Chamber>
     * chamberList = new ArrayList<Chamber>();
     * 
     * 
     * 
     * 
     * }
     */
    /**
     *
     * "Main" to create the level.
     */
    public void runMe() {

        while (chamberCounter != 5) {
            d20 = new D20();
            diceRoll = d20.roll();

            if (passageCheck == 10) {

                // Force generate when there is 10 passage sections in a row
                newPassageSection = new PassageSection();
                newPassage.addPassageSection(newPassageSection);

                // Might need new door?
                newDoor = new Door();
                newPassage.setDoor(newDoor);
                newDoor.getAllDoors();

                // Chamber generating should be done
                newChamber = new Chamber();
                chamberCounter++;
                // newChamber.getAllChamber();
                chamberList.add(newChamber);
                passageCheck = 0;
            } else {
                if ((diceRoll >= 1) && (diceRoll <= 2)) {
                    // System.out.println("passage goes straight for 10 ft");
                    passageSectionCounter++; // Has to be at the beginning
                    newPassageSection = new PassageSection("passage goes straight for 10 ft");
                    newPassage.addPassageSection(newPassageSection);
                    passageCheck++;
                } else if ((diceRoll >= 3) && (diceRoll <= 5)) {
                    // System.out.println("passage ends in Door to a Chamber");
                    newDoor = new Door();
                    newDoor.setArchway(false);
                    newPassage.setDoor(newDoor);
                    newDoor.getAllDoors();
                    // Chamber generating should be done
                    newChamber = new Chamber();
                    // newChamber.getAllChamber();
                    chamberCounter++;
                    chamberList.add(newChamber);
                    passageCheck = 0;

                } else if ((diceRoll >= 6) && (diceRoll <= 7)) {
                    // System.out.println("archway (door) to right (main passage continues straight
                    // for 10 ft)");
                    passageSectionCounter++;
                    newDoor = new Door();
                    newDoor.setArchway(true);
                    newPassage.setDoor(newDoor);
                    newDoor.getAllDoors();
                    newPassageSection = new PassageSection(
                            "archway (door) to right (main passage continues straight for 10 ft)");
                    newPassage.addPassageSection(newPassageSection);
                    passageCheck++;
                } else if ((diceRoll >= 8) && (diceRoll <= 9)) {
                    // System.out.println("archway (door) to left (main passage continues straight
                    // for 10 ft)");
                    passageSectionCounter++;
                    newDoor = new Door();
                    newDoor.setArchway(true);
                    newPassage.setDoor(newDoor);
                    newDoor.getAllDoors();
                    newPassageSection = new PassageSection(
                            "archway (door) to left (main passage continues straight for 10 ft)");
                    newPassage.addPassageSection(newPassageSection);
                    passageCheck++;
                } else if ((diceRoll >= 10) && (diceRoll <= 11)) {
                    // System.out.println("passage turns to left and continues for 10 ft");
                    passageSectionCounter++;
                    newPassageSection = new PassageSection("passage turns to left and continues for 10 ft");
                    newPassage.addPassageSection(newPassageSection);
                    passageCheck++;
                } else if ((diceRoll >= 12) && (diceRoll <= 13)) {
                    // System.out.println("passage turns to right and continues for 10 ft");
                    passageSectionCounter++;
                    newPassageSection = new PassageSection("passage turns to right and continues for 10 ft");
                    newPassage.addPassageSection(newPassageSection);
                    passageCheck++;
                } else if ((diceRoll >= 14) && (diceRoll <= 16)) {
                    // System.out.println("passage ends in archway (door) to chamber");
                    newDoor = new Door();
                    newDoor.setArchway(true);
                    newPassage.setDoor(newDoor);
                    newDoor.getAllDoors();
                    // Chamber generating should be done
                    newChamber = new Chamber();
                    // newChamber.getAllChamber();
                    chamberCounter++;
                    chamberList.add(newChamber);
                    passageCheck = 0;
                } else if (diceRoll == 17) {
                    // System.out.println("Stairs, (passage continues straight for 10 ft)");
                    // Stair
                    passageSectionCounter++;
                    newPassageSection = new PassageSection();
                    newPassage.addPassageSection(newPassageSection);
                    newStairs = new Stairs();
                    // System.out.println();
                    // System.out.println("STAIRS DESCRIPTION");
                    // System.out.println("----------------------------------------------------------------");
                    // System.out.println();
                    // System.out.println("STAIRS: There are only stairs here, continue on.");
                    // System.out.println();
                    // System.out.println("----------------------------------------------------------------");
                    passageCheck++;

                } else if ((diceRoll >= 18) && (diceRoll <= 19)) {
                    // System.out.println("Dead End - go backwards");
                    // break or rerun the program
                    // break;
                    // Inform the user and then continue the program until 5 chambers are generated
                    // as stated on moodle
                } else if (diceRoll == 20) {
                    // System.out.println("Wandering Monster (passage continues straight for 10
                    // ft)");
                    // Wandering Monster
                    passageSectionCounter++;
                    newPassageSection = new PassageSection("Wandering Monster (passage continues straight for 10 ft)");
                    newPassage.addPassageSection(newPassageSection);
                    // System.out.println();
                    // System.out.println("MONSTER DESCRIPTION INSIDE PASSAGE SECTION");
                    // System.out.println("----------------------------------------------------------------");
                    // System.out.println();
                    // System.out.println("MONSTER: " +
                    // newPassageSection.getMonster().getDescription());
                    // System.out.println();
                    // System.out.println("----------------------------------------------------------------");
                    passageCheck++;
                } else {
                    // System.out.println("ERROR: Random Dice Roll exceeded range of 1 - 20");
                    // System.out.println(diceRoll);
                }
            }

        }
    }

    public ArrayList<Chamber> returnChamberList() {
        return chamberList;
    }
}
