package dndtest;

import dnd.models.Exit;
import dnd.models.Monster;
import dnd.models.Stairs;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class PassageTest {
	public PassageTest() {
	}

	@Before
	public void setup() {
		// set up any instance variables here so that they have fresh values for every
		// test
	}

	/**
	 * Test of getDoors method, of class Passage. Add 1 doors, associated with 1
	 * section
	 */
	@Test
	public void testGetDoors() {

		System.out.println("Testing getDoors Function");

		int num = 12;
		Passage testInstance = new Passage();

		for (int i = 0; i < num; i++) {
			PassageSection newPassageSection = new PassageSection();
			Door newDoor = new Door();

			testInstance.addPassageSection(newPassageSection);
			testInstance.setDoor(newDoor);
		}

		int result = testInstance.getDoors().size();
		assertEquals(result, num);
	}

	/**
	 * Test of getDoor method, of class Passage.
	 */
	@Test
	public void testGetDoor() {
		System.out.println("Testing getDoor Function");

		Passage testInstance = new Passage();

		PassageSection passageSection1 = new PassageSection();
		Door door1 = new Door();
		testInstance.addPassageSection(passageSection1);
		testInstance.setDoor(door1);

		PassageSection passageSection2 = new PassageSection();
		Door door2 = new Door();
		testInstance.addPassageSection(passageSection2);
		testInstance.setDoor(door2);

		assertEquals(door1, testInstance.getDoor(0));
		assertEquals(door2, testInstance.getDoor(1));
		assertEquals(null, testInstance.getDoor(2));
	}

	/**
	 * Test of addMonster method, of class Passage.
	 */
	@Test
	public void testAddMonster() {
		System.out.println("Testing addMonster Function");

		Passage testInstance = new Passage();

		Monster monster1 = new Monster();
		monster1.setType(5);
		PassageSection passageSection1 = new PassageSection();
		testInstance.addPassageSection(passageSection1);
		testInstance.addMonster(monster1, 0);

		Monster monster2 = new Monster();
		monster2.setType(23);
		PassageSection passageSection2 = new PassageSection();
		testInstance.addPassageSection(passageSection2);
		testInstance.addMonster(monster2, 1);

		assertEquals(monster1, testInstance.getMonster(0));
		assertEquals(monster2, testInstance.getMonster(1));
	}

	/**
	 * Test of getMonster method, of class Passage.
	 */
	@Test
	public void testGetMonster() {
		System.out.println("Testing getMonster Function");

		int num = 12;

		Passage testInstance = new Passage();

		for (int i = 0; i < num; i++) {
			Monster monster = new Monster();
			PassageSection passageSection = new PassageSection();
			testInstance.addPassageSection(passageSection);
			testInstance.addMonster(monster, i);

			assertEquals(monster, testInstance.getMonster(i));
		}
	}

	/**
	 * Test of addPassageSection method, of class Passage.
	 */
	@Test
	public void testAddPassageSection() {
		System.out.println("Testing addPassageSection Function");

		PassageSection passageSection1 = new PassageSection("test");
		Passage passage1 = new Passage();
		passage1.addPassageSection(passageSection1);

		PassageSection passageSection2 = new PassageSection("meow");
		Passage passage2 = new Passage();
		passage2.addPassageSection(passageSection2);

		PassageSection passageSection3 = new PassageSection("woof");
		Passage passage3 = new Passage();
		passage3.addPassageSection(passageSection3);

		// TODO review the generated test code and remove the default call to fail.
		assertTrue(passage1.getDescription().contains("test"));
		assertTrue(passage2.getDescription().contains("meow"));
		assertTrue(passage3.getDescription().contains("woof"));

	}

	/**
	 * Test of setDoor method, of class Passage.
	 */
	@Test
	public void testSetDoor() {
		System.out.println("Testing setDoor Function");

		int num = 6;
		Door newDoor = new Door();
		Passage testInstance = new Passage();
		testInstance.addPassageSection(new PassageSection());
		testInstance.setDoor(newDoor);

		for (int i = 0; i < num; i++) {
			testInstance.addPassageSection(new PassageSection());
			testInstance.setDoor(new Door());
		}

		Door firstDoor = testInstance.getDoor(0);

		assertEquals(newDoor, firstDoor);
		assertEquals(testInstance.getDoors().size(), num + 1);

		// TODO review the generated test code and remove the default call to fail.

	}

	/**
	 * Test of getDescription method, of class Passage.
	 */
	@Test
	public void testGetDescription() {
		System.out.println("Testing getDescription Function");

		Passage passage1 = new Passage();
		passage1.addPassageSection(new PassageSection("test"));
		Monster monster1 = new Monster();
		passage1.addMonster(monster1, 0);

		Passage passage2 = new Passage();
		passage2.addPassageSection(new PassageSection("meow"));
		Monster monster2 = new Monster();
		passage2.addMonster(monster2, 0);

		String result1 = passage1.getDescription();
		String result2 = passage2.getDescription();

		assertTrue(result1.contains(monster1.getDescription()) && result1.contains("test"));
		assertTrue(result2.contains(monster2.getDescription()) && result2.contains("meow"));

		// TODO review the generated test code and remove the default call to fail.

	}
}
