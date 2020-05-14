package dndtest;

import dnd.models.Trap;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class DoorTest {

    public DoorTest() {
    }

    /* set up similar to the sample in PassageTest.java */

    /**
     * Test of setTrapped method, of class Door. Case: flag == true, with roll
     * Expected result, Trap is set 1/20.
     * 
     */
    @Test
    public void testSetTrappedTwo() {
        System.out.println("Testing setTrapped Function");

        boolean flag1 = true;
        boolean flag2 = false;

        Door trap1 = new Door();
        trap1.setArchway(false);
        trap1.setTrapped(flag1, 6);
        String trap1_trapDescription = " Pit, 10' deep, 3 in 6 to fall in.";

        Door trap2 = new Door();
        trap2.setArchway(true);
        trap2.setTrapped(flag2, 16);
        String trap2_trapDescription = null;

        assertEquals(flag1, trap1.isTrapped());
        assertEquals(false, trap1.isArchway());
        assertEquals(trap1_trapDescription, trap1.getTrapDescription());

        assertEquals(flag2, trap2.isTrapped());
        assertEquals(true, trap2.isArchway());
        assertEquals(trap2_trapDescription, trap2.getTrapDescription());
    }

    /**
     * Test of setOpen method, of class Door. Test the door can be open only if
     * unlocked or close assume default door is close and unlocked.
     */
    /**
     * Test of setOpen method, of class Door. Case: Archway cannot set to close
     * 
     */
    @Test
    public void testSetOpen() {
        System.out.println("Testing setOpen Function");

        boolean flag = false;

        Door door1 = new Door();
        door1.setArchway(true);
        door1.setOpen(flag);

        Door door2 = new Door();
        door2.setArchway(false);
        door2.setOpen(flag);

        assertEquals(true, door1.isOpen());
        assertEquals(false, door2.isOpen());

    }

    /**
     * Test of setArchway method, of class Door.
     */
    @Test
    public void testSetArchway() {
        System.out.println("Testing setArchway Function");

        boolean flag1 = true;
        boolean flag2 = false;

        Door door1 = new Door();
        Door door2 = new Door();

        door1.setArchway(flag1);
        door2.setArchway(flag2);

        boolean door1_result = door1.isArchway();
        boolean door2_result = door2.isArchway();

        assertEquals(door1_result, flag1);
        assertEquals(door2_result, flag2);
    }

    /**
     * Test of isTrapped method, of class Door. SetTrap to true
     */
    @Test
    public void testIsTrapped() {
        System.out.println("Testing isTrapped Function");

        boolean flag1 = true;
        boolean flag2 = false;

        Door door1 = new Door();
        door1.setArchway(false);
        door1.setTrapped(flag1);

        Door door2 = new Door();
        door2.setArchway(false);
        door2.setTrapped(flag2);

        assertEquals(flag1, door1.isTrapped());
        assertEquals(flag2, door2.isTrapped());

    }

    /**
     * Test of isOpen method, of class Door. Case door is closed
     */
    @Test
    public void testIsOpenOne() {
        System.out.println("Testing isOpen Function");

        boolean flag1 = true;
        boolean flag2 = false;

        Door door1 = new Door();
        door1.setOpen(flag1);
        boolean door1_expResult = false;
        // Islocked
        if (door1.isArchway()) {
            door1_expResult = true;
        }

        Door door2 = new Door();
        door2.setOpen(flag2);
        boolean door2_expResult = false;
        if (door2.isArchway()) {
            door2_expResult = true;
        }

        assertEquals(door1_expResult, door1.isOpen());
        assertEquals(door2_expResult, door2.isOpen());

    }

    /**
     * Test of isArchway method, of class Door. Check case archway is false;
     */
    @Test
    public void testIsArchwayOne() {
        System.out.println("Testing isArchway Function");

        boolean flag1 = true;
        boolean flag2 = false;

        Door door1 = new Door();
        door1.setArchway(flag1);

        Door door2 = new Door();
        door2.setArchway(flag2);

        assertEquals(flag1, door1.isArchway());
        assertEquals(flag2, door2.isArchway());

    }

    /**
     * Test of getTrapDescription method, of class Door.
     */
    @Test
    public void testGetTrapDescription() {
        System.out.println("Testing getTrapDescription Function");
        Door testInstance = new Door();
        testInstance.setArchway(false);
        testInstance.setTrapped(true, 16);
        String expResult = "Spear trap, 1-3 spears, 1 in 20 is poisoned.";

        assertTrue(testInstance.getTrapDescription().contains(expResult));
    }

    /**
     * Test of setSpaces method, of class Door. Case: Just kip the references to the
     * spaces. Don't consider the case of Door need to be existed in Chamber.
     */
    @Test
    public void testSetSpaces() {
        System.out.println("Testing setSpaces Function");

        Door testInstance = new Door();
        Chamber chamber1 = new Chamber();
        Chamber chamber2 = new Chamber();

        testInstance.setSpaces(chamber1, chamber2);
        int expResult = 2;

        assertEquals(expResult, testInstance.getSpaces().size());
        assertEquals(chamber2, testInstance.getSpaces().get(1));

    }

    /**
     * Test of getSpaces method, of class Door.
     */
    @Test
    public void testGetSpaces() {
        System.out.println("Testing getSpaces Function");

        Door testInstance = new Door();
        Chamber chamber1 = new Chamber();
        Chamber chamber2 = new Chamber();

        testInstance.setSpaces(chamber1, chamber2);

        assertEquals(chamber1, testInstance.getSpaces().get(0));
        assertEquals(chamber2, testInstance.getSpaces().get(1));

    }

    /**
     * Test of getDescription method, of class Door.
     */
    @Test
    public void testGetDescription() {
        System.out.println("Testing getDescription Function");

        Door testInstance = new Door();
        testInstance.setArchway(false);
        testInstance.setTrapped(true, 15);

        String result = testInstance.getDescription();
        assertTrue(result.contains("trap") || result.contains("Trap") || result.contains("traps")
                || result.contains("Traps"));

    }
}
