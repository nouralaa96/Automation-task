package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CoursesPage;
import pages.LoginPage;

public class CourseCreationTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private CoursesPage coursesPage;

    @BeforeMethod
    public void setUp() {
        // Set up web driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // navigate to this website
        driver.get("https://swinji.azurewebsites.net");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        coursesPage = new CoursesPage(driver);
    }

    @Test
    public void testCourseCreation() {
        String courseName = "Create Test Course1";
        String subject = "Technology Information";
        String grade = "1";
        String username = "testregister@aaa.com";
        String password = "Wakram_123";
        loginPage.login(username,password );
        coursesPage.openCourses();
        coursesPage.createCourse(courseName, subject, grade);
        Assert.assertEquals(coursesPage.isTestCourseCreated(), courseName);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
