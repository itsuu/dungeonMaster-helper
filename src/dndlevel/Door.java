package dndlevel;

import dnd.models.Trap;
import dnd.die.D20;
import dnd.die.D10;
import dnd.die.D6;
import java.util.ArrayList;

public class Door {

    /**
     * Class instance variable for trapState.
     */
    private boolean trapState;
    /**
     * Class instance variable for trap.
     */
    private Trap myTrap;
    /**
     * Class instance variable for openState.
     */
    private boolean openState;
    /**
     * Class instance variable for archwayState.
     */
    private boolean archwayState;
    /**
     * Class instance variable for mySpaceList.
     */
    private ArrayList<Space> mySpaceList;
    /**
     * Class instance variable for D20 object.
     */
    private D20 d20;
    /**
     * Class instance variable to select trap.
     */
    private D20 d20Trap;
    /**
     * Class instance variable for D10 object.
     */
    private D10 d10;
    /**
     * Class instance variable for D6 object.
     */
    private D6 d6;
    /**
     * Class instance variable for string of doorDescription.
     */
    private String doorDescription = "";

    /**
     * Randomly generate value within the door class when a new door is called and
     * created.
     */
    public Door() {
        // needs to set defaults
        d20 = new D20();
        d10 = new D10();
        d6 = new D6();
        trapState = false;
        openState = false;
        archwayState = false;

        if (d10.roll() == 1) {
            // Is an archways
            archwayState = true;
            trapState = false;
            openState = true;
        } else {
            archwayState = false;
            // Is a door
            if (d20.roll() == 1) {

                trapState = true;
            } else {
                trapState = false;
            }

            if (d6.roll() == 1) {
                openState = false;
            } else {
                openState = true;
            }
        }
        myTrap = null;
        mySpaceList = new ArrayList<Space>();

        doorDescription = getDescription();
    }

    /**
     * REFACTORED: Uses a die to generate random trap.
     * @param flag takes in a bool value from user and sets it to trapState.
     *
     * @param roll takes in the index of the array and set it as the trap
     *             description.
     */
    public void setTrapped(boolean flag, int... roll) {
        // true == trapped. Trap must be rolled if no integer is given
        trapState = flag; // or this.trapState = flag;
        if (trapState) {
            if (myTrap == null) { /* if it's empty, call a constructor */
                d20Trap = new D20();
                myTrap = new Trap();
                myTrap.chooseTrap(d20Trap.roll());
            }
        }
        doorDescription = getDescription();
    }

    /**
     * @param flag sets bool value from user to openState.
     */
    public void setOpen(boolean flag) {
        // true == open
        openState = flag;
        doorDescription = getDescription();
    }

    /**
     * @param flag sets bool value from user to archwayState and changed trapState
     *             and openState when archwayState is changed.
     */
    public void setArchway(boolean flag) {
        // true == is archway
        archwayState = flag;
        if (archwayState) {
            setTrapped(false);
            setOpen(true);
        }
        doorDescription = getDescription();
    }

    /**
     * @return trapState.
     */
    public boolean isTrapped() {
        return trapState;
    }

    /**
     * @return archwayState.
     */
    public boolean isOpen() {
        if (archwayState) {
            return true;
        }
        return openState;
    }

    /**
     * @return archwayState.
     */
    public boolean isArchway() {
        if (archwayState) {
            archwayState = true;

        } else {
            archwayState = false;
        }
        return archwayState;
    }

    /**
     * @return description of trap.
     */
    public String getTrapDescription() {
        if (trapState) {
            return myTrap.getDescription();
        } else {
            return null;
        }
    }

    /**
     * @return the spacelist.
     */
    public ArrayList<Space> getSpaces() {
        return mySpaceList;
    }

    /**
     * @param spaceOne first space between the door.
     *
     * @param spaceTwo second space between the door.
     */
    public void setSpaces(Space spaceOne, Space spaceTwo) {
        mySpaceList.add(spaceOne);
        mySpaceList.add(spaceTwo);
        spaceOne.setDoor(this);
        spaceTwo.setDoor(this);
    }

    /**
     * @return the string of the door/archyway based on its states.
     */
    public String getDescription() {
        String des = "";
        if ((!trapState) && archwayState && openState) {
            des = "Open and non-trapped archway";
            return des;
        } else if ((!archwayState) && openState && (!trapState)) {
            des = "Unlocked and non-trapped door";
            return des;
        } else if ((!archwayState) && (!openState) && (!trapState)) {
            des = "Locked and non-trapped door";
            return des;
        } else if ((!archwayState) && (!openState) && trapState) {
            des = "Locked and trapped door";
            return des;
        } else if ((!archwayState) && openState && trapState) {
            des = "Unlocked and trapped door";
            return des;
        }
        return null;
    }

    /**
     * This is a method that gets called in Level.java to print out all the
     * information about the door to the user.
     */
    public void getAllDoors() {
        //System.out.println();
        System.out.println("\nDOOR/ARCHWAY DESCRIPTION");
        System.out.println("----------------------------------------------------------------\n");
        //System.out.println();
        if (doorDescription.contains("archway")) {
            System.out.println("ARCHWAY DESCRIPTION: " + doorDescription);
        } else {
            System.out.println("DOOR DESCRIPTION: " + doorDescription);
        }
        //System.out.println();
        System.out.println("\n----------------------------------------------------------------");
    }
}
