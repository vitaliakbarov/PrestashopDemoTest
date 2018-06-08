package TestCases;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)

public class TestRunner {
    public static void main(String[] args){
        //JUnitCore.main("TestCases.MainPageTest", "TestCases.DataDrivenTestingLogin", "TestCases.ContactUsTest");
        JUnitCore.main("TestCases.MainPageTest");
        //JUnitCore.runClasses(TestCases.MainPageTest.class,TestCases.DataDrivenTestingLogin.class);
        //added

    }

}
