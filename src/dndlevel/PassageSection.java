package dndlevel;

import dnd.models.Monster;
import dnd.die.Percentile;

/* Represents a 10 ft section of passageway */

public class PassageSection {
    /**
     * Class instance variable for door.
     */
    private Door passageDoor;
    /**
     * Class instance variable for monster.
     */
    private Monster passageMonster;
    /**
     * Class instance variable for the string description of passage.
     */
    private String passageDescription = "";
    /**
     * Class instance variable to select the monster.
     */
    private Percentile monsterPercentile;

    /**
     * Class instance variable for door.
     */
    public PassageSection() {
        // sets up the 10 foot section with default settings
        // passageDoor = null;
        // passageMonster = null;
    }

    /**
     * @param description takes in a passage section with description.
     */
    public PassageSection(String description) {
        // sets up a specific passage based on the values sent in from
        // modified table 1
        passageDescription = description;
    }

    /**
     * @return a door in a passage section with description.
     */
    public Door getDoor() {
        // returns the door that is in the passage section, if there is one.
        passageDoor = new Door();
        return passageDoor;
    }

    /**
     * @return the monster that is in the passage section, if there is one.
     */
    public Monster getMonster() {
        if (passageDescription.contains("Wandering Monster")) {
            monsterPercentile = new Percentile();
            this.passageMonster = new Monster(); // new monster case
            this.passageMonster.setType(monsterPercentile.roll());
            return passageMonster;
        } else if (passageDescription.contains("")) {

            return passageMonster; // passed through monster from passage
        } else {
            return null;
        }
    }

    /**
     * @return the string description of the passage section.
     */
    public String getDescription() {
        return passageDescription;
    }

    /**
     * @param toAdd add a monster to the passage section.
     */
    public void addPassageMonster(Monster toAdd) {
        passageMonster = toAdd;
    }
}
