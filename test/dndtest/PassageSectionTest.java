package dndtest;

import dnd.models.Exit;
import dnd.models.Monster;
import org.junit.Test;
import static org.junit.Assert.*;

public class PassageSectionTest {

    /**
     * Test of getDoor method, of class PassageSection.
     */
    @Test
    public void testGetDoor() {
        System.out.println("Testing testGetDoor Function");

        String passageSectionDescription = "passage ends in Door to a Chamber";
        PassageSection testInstance = new PassageSection(passageSectionDescription);

        if (passageSectionDescription.contains("door") || passageSectionDescription.contains("Door")) {
            Door door = testInstance.getDoor();

            if (door != null) {
                boolean result = (door.getSpaces().size() >= 0);
                boolean expResult = true;
                assertEquals(expResult, result);
            } else {
                fail("PassageSection has a door, but program does not make a new door for the PassageSection when PassageSection.getDoor is called...");
            }
        } else {
            assertTrue(!(passageSectionDescription.contains("door")) || !(passageSectionDescription.contains("Door")));
        }
    }

    /**
     * Test of getMonster method, of class PassageSection. Set a monster based on
     * string description
     */
    @Test
    public void testGetMonster() {
        System.out.println("Testing getMonster Function");

        String description1 = "Wandering Monster (passage continues straight for 10 ft)";
        PassageSection passageSection1 = new PassageSection(description1);
        Monster monster1 = passageSection1.getMonster();
        boolean result1 = (monster1 != null);
        assertEquals(true, result1);

        String description2 = "left turn into archway then continue 10 ft";
        PassageSection passageSection2 = new PassageSection(description2);
        Monster monster2 = passageSection2.getMonster();
        boolean result2 = (monster2 != null);
        assertEquals(false, result2);
    }

    /**
     * Test of getDescription method, of class PassageSection.
     */
    @Test
    public void testGetDescription() {
        System.out.println("Testing getDescription Function");

        String description1 = "left turn into archway then continue 10 ft";
        PassageSection passageSection1 = new PassageSection(description1);
        String result1 = passageSection1.getDescription();

        String description2 = "left turn into archway";
        PassageSection passageSection2 = new PassageSection(description2);
        String result2 = passageSection2.getDescription();

        assertTrue(result1.contains(description1));
        assertTrue(result2.contains(description2));

        assertTrue(!(result2.contains(description1)));
    }

    /**
     * Test of addPassageMonster method, of class PassageSection.
     */
    @Test
    public void testAddPassageMonster() {
        System.out.println("Testing addPassageMonster Function");

        PassageSection passageSection1 = new PassageSection();
        Monster monster1 = new Monster();
        passageSection1.addPassageMonster(monster1);

        PassageSection passageSection2 = new PassageSection();
        Monster monster2 = new Monster();
        passageSection2.addPassageMonster(monster2);

        assertEquals(monster1, passageSection1.getMonster());
        assertEquals(monster2, passageSection2.getMonster());
    }

    /**
     * Test of addPassageMonster method, of class PassageSection.
     */
    @Test
    public void testPassageSection() {
        System.out.println("Testing PassageSection Function");

        String description1 = "test";
        PassageSection passageSection1 = new PassageSection(description1);

        String description2 = "Wandering Monster";
        PassageSection passageSection2 = new PassageSection(description2);

        String passageSection1_result = passageSection1.getDescription();
        String passageSection2_result = passageSection2.getDescription();

        assertEquals(description1, passageSection1_result);
        assertEquals(description2, passageSection2_result);

        assertTrue(passageSection2.getMonster() != null);
    }
}
