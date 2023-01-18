package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CoursesPage {

    private WebDriver driver;
    private WebDriverWait wait;

    By menuButtonLocator = By.xpath("//div[@id='btnMinifyMe']//em[contains(@class,'lms-font-larger')]");
    By coursesLocator = By.xpath("//span[@class='menu-item-parent lms-whiteSpace-normal'][normalize-space()='Courses']");
    By addCourseLocator = By.id("btnListAddCourse");
    By courseNameLocator = By.id("txtCourseName");
    By courseSubjectLocator = By.id("courseSubject");
    By selectGradeLocator = By.xpath(" //em[@class='lms-margin-end-5 lms-position-relative-imp']");
    By selectYearLocator = By.id("courseGrade");
    By courseTeacherSearchLocator = By.xpath("//i[@role='button']");
    By courseTeacherLocator = By.id("ui-select-choices-row-0-0");
    By courseCompletionLocator = By.xpath("//i[@class='lms-position-relative-imp']");
    By courseCreateLocator = By.id("btnSaveAsDraftCourse");
    By sortCourseLocator = By.id("CoursesOrderBy");
    By getCreatredCourseLocator = By.xpath("//*[@id=\"lnkListCourseSelcted\"][1]");

    public CoursesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openCourses() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButtonLocator)).click();
        wait.until(ExpectedConditions.elementToBeClickable(coursesLocator)).click();
    }

    public void createCourse(String courseName, String subject, String grade) {
        driver.findElement(addCourseLocator).click();
        wait.until(ExpectedConditions.elementToBeClickable(courseNameLocator)).sendKeys(courseName);
        Select drpSubject = new Select(driver.findElement(courseSubjectLocator));
        drpSubject.selectByVisibleText(subject);
        driver.findElement(selectGradeLocator).click();
        Select drpGrade = new Select(driver.findElement(selectYearLocator));
        drpGrade.selectByVisibleText(grade);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
        wait.until(ExpectedConditions.elementToBeClickable(courseTeacherSearchLocator)).click();
        driver.findElement(courseTeacherLocator).click();

        driver.findElement(courseCompletionLocator).click();
        driver.findElement(courseCreateLocator).click();
    }

    public String isTestCourseCreated()  {
        // wait to the creation of cousre
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("courseNameView")));
        // go to the course page
        openCourses();
        //go to the sort drop menu to get the latest courses
        wait.until(ExpectedConditions.elementToBeClickable(sortCourseLocator));
        Select drpSort = new Select(driver.findElement(sortCourseLocator));
        drpSort.selectByVisibleText("Creation Date Descending");
        //get the created course element and get is text
        WebElement createdCourse = wait.until(ExpectedConditions.visibilityOfElementLocated(getCreatredCourseLocator));
        return createdCourse.getText();
    }
}

