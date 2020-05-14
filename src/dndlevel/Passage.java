package dndlevel;

import dnd.models.Monster;
import java.util.ArrayList;
/*
A passage begins at a door and ends at a door.  It may have many other doors along
the way

You will need to keep track of which door is the "beginning" of the passage
so that you know how to
*/

public class Passage extends Space {

    /**
     * Class instance variable for list of passage sections.
     */
    private ArrayList<PassageSection> thePassage = new ArrayList<PassageSection>();
    /**
     * Class instance variable for list of doors.
     */
    private ArrayList<Door> passageDoorList = new ArrayList<Door>();
    /**
     * Class instance variable for passage section description.
     */
    private PassageSection passageSectionDescription;
    /**
     * Class instance variable for door's index.
     */
    private int doorIndex = 0;
    /**
     * Class instance variable for monster's index.
     */
    private int monsterIndex = 0;
    /**
     * Class instance variable for the string of the monsterInfo.
     */
    private String monsterInfo;
    /**
     * Class instance variable for str, a string to hold a name.
     */
    private String str;

    /**
     * @return the list of doors.
     */
    public ArrayList<Door> getDoors() {
        // gets all of the doors in the entire passage
        return passageDoorList;
    }

    /**
     * @param i the door at index i from the door list.
     *
     * @return the door at index i or null.
     */
    public Door getDoor(int i) {
        // returns the door in section 'i'. If there is no door, returns null
        doorIndex = i;
        if (passageDoorList.size() > 0) {
            if (doorIndex >= passageDoorList.size()) {
                return null;
            } else {
                return passageDoorList.get(doorIndex); // return monster at index i section
            }
        } else {
            return null;
        }
    }

    /**
     * @param theMonster the monster that the user wants to add.
     *
     * @param i          the index of where to add the monster to the monsterlist.
     */
    public void addMonster(Monster theMonster, int i) {
        // adds a monster to section 'i' of the passage
        monsterIndex = i;
        thePassage.get(monsterIndex).addPassageMonster(theMonster);
        monsterInfo = theMonster.getDescription();
    }

    /**
     *
     * @param i the index of where to get the monster from the monsterlist.
     *
     * @return the monster from index i or null.
     */
    public Monster getMonster(int i) {
        // returns Monster door in section 'i'. If there is no Monster, returns null
        monsterIndex = i;
        if (thePassage.size() > 0) {
            if (monsterIndex >= thePassage.size()) {
                return null;
            } else {
                return thePassage.get(monsterIndex).getMonster(); // return monster at index i section
            }
        } else {
            return null;
        }
    }

    /**
     * @param toAdd the passage section to the passage section list.
     */
    public void addPassageSection(PassageSection toAdd) {
        // adds the passage section to the passageway
        // tempPassageSectionMonster = toAdd.getMonster();
        passageSectionDescription = toAdd;
        str = passageSectionDescription.getDescription();
        thePassage.add(toAdd);
    }

    /**
     * @param newDoor adds a door to the door list.
     */
    public void setDoor(Door newDoor) {
        // should add a door connection to the current Passage Section
        passageDoorList.add(newDoor);
    }

    /**
     * @return string with the monster info.
     */
    public String getDescription() {
        return str + monsterInfo;
    }
}
