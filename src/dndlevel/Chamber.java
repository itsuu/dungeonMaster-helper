package dndlevel;

import dnd.die.D20;
import dnd.die.Percentile;
import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.Monster;
import dnd.models.Treasure;
import dnd.models.Trap;
import dnd.models.Stairs;
import dnd.models.Exit;
import dnd.exceptions.UnusualShapeException;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Chamber extends Space {

    /**
     * Class instance variable for chamber contents.
     */
    private ChamberContents myContents;
    /**
     * Class instance variable for chamber shape.
     */
    private ChamberShape mySize;
    /**
     * Class instance variable for monster.
     */
    private Monster myMonster;
    /**
     * Class instance variable for treasure.
     */
    private Treasure myTreasure;
    /**
     * Class instance variable for trap.
     */
    private Trap myTrap;
    /**
     * Class instance variable for stairs.
     */
    private Stairs myStairs;
    /**
     * Class instance variable for chamberDesription.
     */
    private String chamberDescription = "";
    /**
     * Class instance variable for name of chamber shape.
     */
    private String chamberShape = "";
    /**
     * Class instance variable for list of doors.
     */
    private ArrayList<Door> myDoorList = new ArrayList<Door>();
    /**
     * Class instance variable for list of monsters.
     */
    private ArrayList<Monster> myMonsterList = new ArrayList<Monster>();
    /**
     * Class instance variable for list of treasure.
     */
    private ArrayList<Treasure> myTreasureList = new ArrayList<Treasure>();
    /**
     * Class instance variable for list of stairs.
     */
    private ArrayList<Stairs> myStairsList = new ArrayList<Stairs>();
    /**
     * Class instance variable for list of traps.
     */
    private ArrayList<Trap> myTrapList = new ArrayList<Trap>();
    /**
     * Class instance variable for list of exits in the chamber.
     */
    private ArrayList<Exit> exitsInChamber = new ArrayList<Exit>(); // mignt not need
    /**
     * Class instance variable for a door to be made multiple times.
     */
    private Door tempDoor;
    /**
     * Class instance variable for a D20 die to select the contents in chamber.
     */
    private D20 d20ChamberContents;
    /**
     * Class instance variable for a D20 die to select the contents in chamber.
     */
    private D20 d20ChamberShape;
    /**
     * Class instance variable for a D20 die to select the trap content.
     */
    private D20 d20Trap;
    /**
     * Class instance variable for a D20 die to select the stairs.
     */
    private D20 d20Stairs;
    /**
     * Class instance variable for a D20 die to select the treasure container.
     */
    private D20 d20TreasureContainer;
    /**
     * Class instance variable for a D20 die to select the treasure.
     */
    private Percentile treasurePercentile;
    /**
     * Class instance variable for a D20 die to select the exits.
     */
    private D20 d20Exits;
    /**
     * Class instance variable for a D20 die to select the trap content.
     */
    private Percentile monsterPercentile;

    /**
     * REFACTORED: Change the way contents and shapes are generated and add dice
     * rolls for the random generation Creates a chamber with random contents and
     * shape/size.
     */
    public Chamber() {
        d20ChamberContents = new D20();
        d20ChamberShape = new D20();
        d20Exits = new D20();
        ChamberContents tempContents = new ChamberContents();
        tempContents.chooseContents(d20ChamberContents.roll());
        ChamberShape tempSize = ChamberShape.selectChamberShape(d20ChamberShape.roll());
        myContents = tempContents;
        mySize = tempSize;
        mySize.setNumExits();
        while (mySize.getNumExits() >= 5) {
            mySize.setNumExits(); // Force that the number of doors cannot be 5
        }
        chamberDescription = myContents.getDescription();
        chamberShape = mySize.getShape();
        if (mySize.getNumExits() == 0) {
            tempDoor = new Door();
            setDoor(tempDoor);
        } else {
            for (int j = 0; j < mySize.getNumExits(); j++) {
                tempDoor = new Door();
                setDoor(tempDoor);
            }
        }
    }

    /**
     * Allows user to input their chamber content and shape/size to to the chamber
     * and it will compensate for the number of exits in the chamber.
     *
     * @param theContents Userinput of their chamber contents
     *
     * @param theShape    Userinput of their chamber shape
     */
    public Chamber(ChamberShape theShape, ChamberContents theContents) {
        myContents = theContents;
        mySize = theShape;
        myDoorList.clear();
        for (int i = 0; i < mySize.getNumExits(); i++) {
            tempDoor = new Door();
            setDoor(tempDoor);
        }
    }

    /**
     * Allows user to input their shape of the chamber to what they want it to be.
     *
     * @param theShape Userinput of their chamber shape
     */
    public void setShape(ChamberShape theShape) {
        mySize = theShape;
        myDoorList.clear(); // Clears the current list of doors and lets the user input the amount of doors
                            // they want
        // I dont like this, clearing the number of doors
        if (mySize.getNumExits() == 0) {
            setDoor(new Door());
        } else {
            for (int j = 0; j < mySize.getNumExits(); j++) {
                setDoor(new Door());
            }
        }
    }

    /**
     * @return Checks if the door list is empty. If not, return door list else if
     *         will return null.
     *
     */
    public ArrayList<Door> getDoors() {
        if (myDoorList.size() > 0) {
            return myDoorList;
        } else {
            return null;
        }
    }

    /**
     * @param theMonster Adds monster from the param to the specific monster list.
     */
    public void addMonster(Monster theMonster) {
        myMonsterList.add(theMonster);
    }

    /**
     * @return the monsterlist of monster when called.
     */
    public ArrayList<Monster> getMonsters() {
        return myMonsterList;
    }

    /**
     * @param theTreasure Adds treasure from the param to the treasure list.
     */
    public void addTreasure(Treasure theTreasure) {
        myTreasureList.add(theTreasure);
    }

    /**
     * @return the treasurelist of the treasure when called.
     */
    public ArrayList<Treasure> getTreasureList() {
        return myTreasureList;
    }

    /**
     * @param theTrap Adds trap to the trap list.
     */
    public void addTrap(Trap theTrap) {
        myTrapList.add(theTrap);
    }

    /**
     * @return traplist of traps when called.
     */
    public ArrayList<Trap> getTrapList() {
        return myTrapList;
    }

    /**
     * @param theStairs Adds stair from the param to the stairlist.
     */
    public void addStairs(Stairs theStairs) {
        myStairsList.add(theStairs);
    }

    /**
     * @return the stairlist of stairs when called.
     */
    public ArrayList<Stairs> getStairsList() {
        return myStairsList;
    }

    /**
     * @return the description of the contents of chamber when called.
     */
    public String getDescription() {
        return myContents.getDescription();
    }

    /**
     * @param newDoor adds door to doorlist when called.
     */
    public void setDoor(Door newDoor) {
        myDoorList.add(newDoor); // should add a door connection to this room
    }

    /**
     * REFACTORED: Took out the exitsInChamber arraylist since jar file doesnt use
     * it Method to call when wanting to print out all number of doors in the
     * chamber. This method is called by printChamberContent method.
     */
    public String printExitsInChamber() {
        StringBuilder str = new StringBuilder();

        if (/* exitsInChamber.size() */ mySize.getNumExits() == 0) {
            System.out.println("NUMBER OF DOORS IN CHAMBER: " + myDoorList.size());
            System.out.println("EXIT DOOR: Exit out of Chamber into the next passage."); // This might be not correct
            // Chambers should have 1 door minimun, but if it possible to get a chamber with
            // 0 doors so we force one to generate an exit
            str.append("NUMBER OF DOORS IN CHAMBER: " + myDoorList.size() + "\n");
            str.append("EXIT DOOR: Exit out of Chamber into the next passage.");
            return str.toString();

        } else {
            System.out.println("NUMBER OF DOORS IN CHAMBER: " + myDoorList.size());
            str.append("NUMBER OF DOORS IN CHAMBER: " + myDoorList.size() + "\n");

            for (int i = 0; i < mySize.getNumExits(); i++) {
                System.out.println("DOOR #" + (i + 1) + ". DESCRIPTION: " + myDoorList.get(i).getDescription() + ". ");
                str.append("DOOR #" + (i + 1) + ". DESCRIPTION: " + myDoorList.get(i).getDescription() + ".\n");

            }

            for (int j = 0; j < mySize.getNumExits(); j++) {
                if (myDoorList.get(j).getDescription().contains("Unlocked")
                        || myDoorList.get(j).getDescription().contains("archway")) {
                    // System.out.println();
                    System.out.println("\nEnter Door #" + (j + 1) + " to exit out of Chamber into the next passage.");
                    str.append("\nEnter Door #" + (j + 1) + " to exit out of Chamber into the next passage.");
                    break;
                }
            }

            return str.toString();
        }
    }

    /**
     * This method is called by Level.java to get all the infomation about the
     * chamber out to the user.
     */
    public String getAllChamber() {
        String des = printChamberShape();
        String des2 = printChamberContents();
        /*
         * des.concat(printChamberShape()); des.concat(" ");
         * des.concat(printChamberContents());
         */
        String des3 = des + " " + des2;
        return des3;
    }

    /**
     * REFACTORED: Took out the blocks of code and made them individual methods This
     * method is called by getAllChamber. This will print out all information about
     * the chamber regarding its shape and size.
     */
    public String printChamberShape() {
        String des = "";
        if (chamberShape.equals("Square")) {
            des = chamberShapeSqaure();
            return des;
        } else if (chamberShape.equals("Rectangle")) {
            des = chamberShapeRectangle();
            return des;
        } else {
            des = chamberShapeUnusual();
            return des;

        }
    }

    /**
     * REFACTORED: Took out try and catch exceptions Prints out the info for a
     * square chamber shape. Added this to make printChamberShape smaller
     */
    public String chamberShapeSqaure() {
        /*
         * System.out.println(); System.out.println("Shape and Size of Chamber");
         * System.out.println(
         * "----------------------------------------------------------------");
         * System.out.println(); System.out.println("SHAPE: " + mySize.getShape());
         * System.out.println("AREA: " + mySize.getArea() + "'");
         * System.out.println("LENGTH: " + mySize.getLength() + "'");
         * System.out.println("WIDTH: " + mySize.getWidth() + "'");
         * System.out.println(); System.out.println(
         * "----------------------------------------------------------------");
         */
        StringBuilder str = new StringBuilder();
        str.append("Shape and Size of Chamber\n");
        str.append("----------------------------------------------------------------\n");
        str.append("SHAPE: " + mySize.getShape() + "\n");
        str.append("AREA: " + mySize.getArea() + "'\n");
        str.append("LENGTH: " + mySize.getLength() + "'\n");
        str.append("WIDTH: " + mySize.getWidth() + "'\n");
        str.append("----------------------------------------------------------------\n");
        return str.toString();
    }

    /**
     * REFACTORED: Took out try and catch exceptions Prints out the info for a
     * rectangle chamber shape. Added this to make printChamberShape smaller
     */
    public String chamberShapeRectangle() {
        /*
         * System.out.println(); System.out.println("Shape and Size of Chamber");
         * System.out.println(
         * "----------------------------------------------------------------");
         * System.out.println(); System.out.println("SHAPE: " + mySize.getShape());
         * System.out.println("AREA: " + mySize.getArea() + "'");
         * System.out.println("LENGTH: " + mySize.getLength() + "'");
         * System.out.println("WIDTH: " + mySize.getWidth() + "'");
         * System.out.println(); System.out.println(
         * "----------------------------------------------------------------");
         */
        StringBuilder str = new StringBuilder();
        str.append("Shape and Size of Chamber\n");
        str.append("----------------------------------------------------------------\n");
        str.append("SHAPE: " + mySize.getShape() + "\n");
        str.append("AREA: " + mySize.getArea() + "'\n");
        str.append("LENGTH: " + mySize.getLength() + "'\n");
        str.append("WIDTH: " + mySize.getWidth() + "'\n");
        str.append("----------------------------------------------------------------\n");
        return str.toString();
    }

    /**
     * Prints out the info for a unusual chamber shape. Added this to make
     * printChamberShape smaller
     */
    public String chamberShapeUnusual() {
        /*
         * System.out.println(); System.out.println("Shape and Size of Chamber");
         * System.out.println(
         * "----------------------------------------------------------------");
         * System.out.println(); System.out.println("SHAPE: " + mySize.getShape());
         * System.out.println("AREA: " + mySize.getArea() + "'"); try {
         * System.out.println("LENGTH: " + mySize.getLength() + "'");
         * System.out.println("WIDTH: " + mySize.getWidth() + "'"); } catch
         * (UnusualShapeException e) { System.out.println("LENGTH: No length");
         * System.out.println("WIDTH: No width"); } System.out.println();
         * System.out.println(
         * "----------------------------------------------------------------");
         */
        StringBuilder str = new StringBuilder();
        str.append("Shape and Size of Chamber\n");
        str.append("----------------------------------------------------------------\n");
        str.append("SHAPE: " + mySize.getShape() + "\n");
        str.append("AREA: " + mySize.getArea() + "'\n");
        try {
            str.append("LENGTH: " + mySize.getLength() + "'\n");
            str.append("WIDTH: " + mySize.getWidth() + "'\n");
        } catch (UnusualShapeException e) {
            str.append("LENGTH: No length\n");
            str.append("WIDTH: No width\n");
        }
        str.append("----------------------------------------------------------------\n");
        return str.toString();
    }

    /**
     * REFACTORED: Took out the blocks of code and made them individual methods This
     * method is called by getAllChamber. This will print out all information about
     * the chamber regarding its contents.
     */
    public String printChamberContents() {
        String des = "";
        if (chamberDescription.equals("monster only")) {
            des = contentMonsterOnly();
            return des;
        } else if (chamberDescription.equals("monster and treasure")) {
            des = contentMonsterAndTreasure();
            return des;

        } else if (chamberDescription.equals("stairs")) {
            des = contentStairs();
            return des;

        } else if (chamberDescription.equals("trap")) {
            des = contentTrap();
            return des;

        } else if (chamberDescription.equals("treasure")) {
            des = contentTreasureOnly();
            return des;

        } else {
            des = contentEmpty();
            return des;

        }
    }

    /**
     * Prints out the contents of chamber with only a monster. Added this to make
     * printChamberContents smaller
     */
    public String contentMonsterOnly() {
        monsterPercentile = new Percentile();
        myMonster = new Monster();
        myMonster.setType(monsterPercentile.roll());
        addMonster(myMonster);
        /*
         * System.out.println("\nContents Inside Chamber"); System.out.println(
         * "----------------------------------------------------------------\n");
         * System.out.println("MONSTER ONLY: " + myMonsterList.get(0).getDescription() +
         * "  Number: " + myMonsterList.get(0).getMinNum() + "-" +
         * myMonsterList.get(0).getMaxNum()); printExitsInChamber(); System.out.println(
         * "----------------------------------------------------------------");
         */
        StringBuilder str = new StringBuilder();
        str.append("\nContents Inside Chamber\n");
        str.append("----------------------------------------------------------------\n");
        str.append("MONSTER ONLY: " + myMonsterList.get(0).getDescription() + "\n");
        str.append("Number: " + myMonsterList.get(0).getMinNum() + "-" + myMonsterList.get(0).getMaxNum() + "\n");
        str.append("----------------------------------------------------------------");
        return str.toString();

    }

    /**
     * Prints out the contents of chamber with monster and treasure. Added this to
     * make printChamberContents smaller
     */
    public String contentMonsterAndTreasure() {
        treasurePercentile = new Percentile();
        monsterPercentile = new Percentile();
        d20TreasureContainer = new D20();
        myTreasure = new Treasure();
        myTreasure.chooseTreasure(treasurePercentile.roll());
        myTreasure.setContainer(d20TreasureContainer.roll());
        myMonster = new Monster();
        myMonster.setType(monsterPercentile.roll());
        addMonster(myMonster);
        addTreasure(myTreasure);
        /*
         * System.out.println("\nContents Inside Chamber"); System.out.println(
         * "----------------------------------------------------------------\n");
         * System.out.println("MONSTER AND TREASURE\n"); System.out.println("MONSTER: "
         * + myMonsterList.get(0).getDescription() + "  Number: " +
         * myMonsterList.get(0).getMinNum() + "-" + myMonsterList.get(0).getMaxNum());
         * System.out.println("TREASURE: " +
         * myTreasureList.get(0).getWholeDescription()); printExitsInChamber();
         * System.out.println(
         * "\n----------------------------------------------------------------");
         */
        StringBuilder str = new StringBuilder();
        str.append("\nContents Inside Chamber\n");
        str.append("----------------------------------------------------------------\n");
        str.append("MONSTER AND TREASURE\n");
        str.append("MONSTER ONLY: " + myMonsterList.get(0).getDescription() + "\n");
        str.append("Number: " + myMonsterList.get(0).getMinNum() + "-" + myMonsterList.get(0).getMaxNum() + "\n");
        str.append("TREASURE: " + myTreasureList.get(0).getWholeDescription() + "\n");
        str.append("----------------------------------------------------------------");
        return str.toString();
    }

    /**
     * Prints out the contents of chamber with only stairs. Added this to make
     * printChamberContents smaller
     */
    public String contentStairs() {
        d20Stairs = new D20();
        myStairs = new Stairs();
        myStairs.setType(d20Stairs.roll());
        addStairs(myStairs);
        /*
         * System.out.println("\nContents Inside Chamber"); System.out.println(
         * "----------------------------------------------------------------\n");
         * System.out.println( "STAIRS: There are only stairs here, continue on." +
         * myStairsList.get(0).getDescription() ); printExitsInChamber(); // Print out
         * the door list of chamber System.out.println(
         * "\n----------------------------------------------------------------");
         */
        StringBuilder str = new StringBuilder();
        str.append("\nContents Inside Chamber\n");
        str.append("----------------------------------------------------------------\n");
        str.append("STAIRS: There are only stairs here, continue on.\n");
        str.append("----------------------------------------------------------------");
        return str.toString();
    }

    /**
     * Prints out the contents of chamber with only a trap. Added this to make
     * printChamberContents smaller
     */
    public String contentTrap() {
        d20Trap = new D20();
        myTrap = new Trap();
        myTrap.chooseTrap(d20Trap.roll());
        addTrap(myTrap);
        /*
         * System.out.println("\nContents Inside Chamber"); System.out.println(
         * "----------------------------------------------------------------\n");
         * System.out.println("TRAP: " + myTrapList.get(0).getDescription());
         * printExitsInChamber(); System.out.println(
         * "\n----------------------------------------------------------------");
         */
        StringBuilder str = new StringBuilder();
        str.append("\nContents Inside Chamber\n");
        str.append("----------------------------------------------------------------\n");
        str.append("TRAP: " + myTrapList.get(0).getDescription() + "\n");
        str.append("----------------------------------------------------------------");
        return str.toString();
    }

    /**
     * Prints out the contents of chamber with only treasure. Added this to make
     * printChamberContents smaller
     */
    public String contentTreasureOnly() {
        treasurePercentile = new Percentile();
        d20TreasureContainer = new D20();
        myTreasure = new Treasure();
        myTreasure.chooseTreasure(treasurePercentile.roll());
        myTreasure.setContainer(d20TreasureContainer.roll());
        addTreasure(myTreasure);
        /*
         * System.out.println("\nContents Inside Chamber"); System.out.println(
         * "----------------------------------------------------------------\n");
         * System.out.println("TREASURE: " +
         * myTreasureList.get(0).getWholeDescription()); printExitsInChamber();
         * System.out.println(
         * "\n----------------------------------------------------------------");
         */
        StringBuilder str = new StringBuilder();
        str.append("\nContents Inside Chamber\n");
        str.append("----------------------------------------------------------------\n");
        str.append("TREASURE: " + myTreasureList.get(0).getWholeDescription() + "\n");
        str.append("----------------------------------------------------------------");
        return str.toString();
    }

    /**
     * Prints out the contents of chamber that is empty. Added this to make
     * printChamberContents smaller
     */
    public String contentEmpty() {
        /*
         * System.out.println("\nContents Inside Chamber"); System.out.println(
         * "----------------------------------------------------------------\n");
         * System.out.println("NO TREASURE, MONSTER, STAIRS OR TRAP");
         * printExitsInChamber(); // Print out the door list of chamber
         * System.out.println(
         * "\n----------------------------------------------------------------");
         */
        StringBuilder str = new StringBuilder();
        str.append("\nContents Inside Chamber\n");
        str.append("----------------------------------------------------------------\n");
        str.append("NO TREASURE, MONSTER, STAIRS OR TRAP\n");
        str.append("----------------------------------------------------------------");
        return str.toString();
    }
}
