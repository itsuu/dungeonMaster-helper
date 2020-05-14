package dndtest;

import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	public static void main(String [] args) {

        System.out.println("TESTING CHAMBER");
        System.out.println("=================================================");
        Result result1 = JUnitCore.runClasses(ChamberTest.class);
        System.out.println("=================================================");
        System.out.println("FAILED TESTS:");
        List <Failure> failedList1 = result1.getFailures();
        failedList1.forEach(f->{System.out.println(f);});
        System.out.println("Number of Failed Tests = " + failedList1.size());
        System.out.println();


        System.out.println("TESTING DOOR");
        System.out.println("=================================================");
        Result result2 = JUnitCore.runClasses(DoorTest.class);
        System.out.println("=================================================");
        System.out.println("FAILED TESTS:");
        List <Failure> failedList2 = result1.getFailures();
        failedList2.forEach(f->{System.out.println(f);});
        System.out.println("Number of Failed Tests = " + failedList2.size());
        System.out.println();


        System.out.println("TESTING PASSAGE");
        System.out.println("=================================================");
        Result result3 = JUnitCore.runClasses(PassageTest.class);
        System.out.println("=================================================");
        System.out.println("Failed Tests:");
        List <Failure> failedList3 = result3.getFailures();
        failedList3.forEach(f->{System.out.println(f);});
        System.out.println("Number of Failed Tests = " + failedList3.size());
        System.out.println();

        System.out.println("TESTING PASSAGE SECTION");
        System.out.println("=================================================");
        Result result4 = JUnitCore.runClasses(PassageSectionTest.class);
        System.out.println("=================================================");
        List <Failure> failedList4 = result4.getFailures();
        System.out.println("Failed Tests:");
        failedList4.forEach(f->{System.out.println(f);});
        System.out.println("Number of Failed Tests = " + failedList4.size());
        System.out.println();


        /*repeat steps the above for each junit test file you have*/
    }
}