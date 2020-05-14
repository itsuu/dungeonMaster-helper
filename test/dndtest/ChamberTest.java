package dndtest;

import dnd.die.D20;
import dnd.die.Percentile;
import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.DnDElement;
import dnd.models.Monster;
import dnd.models.Stairs;
import dnd.models.Trap;
import dnd.models.Treasure;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChamberTest {
	ChamberShape theShape;
	ChamberContents theContents;
	Monster myMonster;
	Treasure myTreasure;

	public ChamberTest() {
	}

	/* set up similar to the sample in PassageTest.java */
	@Before
	public void setup() {
		D20 d20ChamberShape = new D20();
		D20 d20ChamberContents = new D20();
		theShape = ChamberShape.selectChamberShape(d20ChamberShape.roll());
		// theShape.setShape();
		theShape.setNumExits();
		theContents = new ChamberContents();
		// theContents.setDescription();
		theContents.chooseContents(d20ChamberContents.roll());
	}

	/**
	 * Test of setShape method, of class Chamber.
	 */
	@Test
	public void testSetShape() {
		System.out.println("Testing setShape Function");
		theShape = ChamberShape.selectChamberShape(18);
		theShape.setNumExits(3);

		Chamber testInstance = new Chamber();
		testInstance.setShape(theShape);
		int testInstance_numberOfExit = testInstance.getDoors().size();
		int numberOfExit = theShape.getNumExits();
		if (numberOfExit == 0) {
			// Force set door to one
			numberOfExit = 1;
		}
		assertEquals(numberOfExit, testInstance_numberOfExit);
	}

	@Test
	public void testGetDoors() {
		System.out.println("Testing getDoors Function");
		theShape = ChamberShape.selectChamberShape(10);
		// theShape.setShape(10); //ensures no unusual shape
		theShape.setNumExits(2); // 3 exits

		int numberOfExit = theShape.getNumExits();
		theContents = new ChamberContents();

		Chamber testInstance = new Chamber(theShape, theContents);
		int testInstance_numberOfExit = testInstance.getDoors().size();
		assertTrue(numberOfExit <= testInstance_numberOfExit);

	}

	/**
	 * Test of getMonsters method, of class Chamber.
	 */
	@Test
	public void testGetMonsters() {
		System.out.println("Testing getMonsters Function");
		D20 monsterPercent = new D20();

		int num = 8;
		Chamber testInstance = new Chamber();

		for (int i = 0; i < num; i++) {
			testInstance.addMonster(new Monster());
		}

		myMonster = new Monster();
		myMonster.setType(monsterPercent.roll());
		testInstance.addMonster(myMonster);

		Monster testInstance_Monster = testInstance.getMonsters().get(num);
		int testInstance_numberOfMonster = testInstance.getMonsters().size();

		assertEquals(myMonster, testInstance_Monster);
		assertTrue(testInstance_numberOfMonster == num + 1);

	}

	/**
	 * Test of addTreasure method, of class Chamber.
	 */
	@Test
	public void testAddTreasure() {
		System.out.println("Testing addTreasure Function");
		Percentile myTreasurePercent = new Percentile();
		D20 myTreasureD20 = new D20();

		Chamber testInstance = new Chamber();
		int num = 6;

		for (int i = 0; i < num; i++) {
			testInstance.addTreasure(new Treasure());
		}

		myTreasure = new Treasure();
		myTreasure.chooseTreasure(myTreasurePercent.roll());
		myTreasure.setContainer(myTreasureD20.roll());
		testInstance.addTreasure(myTreasure);
		

		Treasure testInstance_Treasure = testInstance.getTreasureList().get(num);
		int testInstance_numberOfTreasure = testInstance.getTreasureList().size();

		assertEquals(myTreasure, testInstance_Treasure);
		assertTrue(testInstance_numberOfTreasure == num + 1);

	}

	/**
	 * Test of getTreasureList method, of class Chamber.
	 */
	@Test
	public void testGetTreasureList() {
		System.out.println("Testing getTreasureList Function");
		Percentile percentT1 = new Percentile();
		Percentile percentT2 = new Percentile();
		D20 d20T1 = new D20();
		D20 d20T2 = new D20();

		Chamber testInstance = new Chamber();

		Treasure treasure1 = new Treasure();
		treasure1.chooseTreasure(percentT1.roll());
		treasure1.setContainer(d20T1.roll());

		Treasure treasure2 = new Treasure();
		treasure1.chooseTreasure(percentT2.roll());
		treasure1.setContainer(d20T2.roll());

		testInstance.addTreasure(treasure1);
		testInstance.addTreasure(treasure2);

		ArrayList<Treasure> testInstance_TreasureList = testInstance.getTreasureList();

		assertEquals(treasure1, testInstance_TreasureList.get(0));
		assertEquals(treasure2, testInstance_TreasureList.get(1));
		assertTrue(testInstance_TreasureList.size() == 2);
	}

	/**
	 * Test of getDescription method, of class Chamber.
	 */
	@Test
	public void testGetDescription() {
		System.out.println("Testing getDescription Function");
		Chamber testInstance = new Chamber(theShape, theContents);

		String testInstance_Description = testInstance.getDescription();
		assertTrue(testInstance.getDescription().contains(theContents.getDescription()));
	}

	/**
	 * Test of setDoor method, of class Chamber.
	 */
	@Test
	public void testSetDoor() {
		System.out.println("Testing setDoor Function");
		Chamber testInstance = new Chamber(theShape, theContents);

		Door newDoor = new Door();
		Passage pass = new Passage();

		testInstance.setDoor(newDoor);
		newDoor.setSpaces(testInstance, pass);

		String testInstance_Spaces = newDoor.getSpaces().get(0).getDescription();

		assertEquals(testInstance, newDoor.getSpaces().get(0));
		assertEquals(pass, newDoor.getSpaces().get(1));
		assertTrue(testInstance_Spaces.contains(theContents.getDescription()));
	}

}