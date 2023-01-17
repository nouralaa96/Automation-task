package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
    By courseTeacherLocator = By.xpath("//span[@class='ui-select-placeholder text-muted']");
    By courseCompletionLocator = By.xpath("//i[@class='lms-position-relative-imp']");
    By courseCreateLocator = By.id("btnSaveAsDraftCourse");


    public CoursesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openCourses() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButtonLocator)).click();
        wait.until(ExpectedConditions.elementToBeClickable(coursesLocator)).click();
    }

    public void createCourse(String CourseName, String subject, String grade, String courseTeacher) {
        driver.findElement(addCourseLocator).click();
        wait.until(ExpectedConditions.elementToBeClickable(courseNameLocator)).sendKeys(CourseName);
        Select drpSubject = new Select(driver.findElement(courseSubjectLocator));
        drpSubject.selectByVisibleText(subject);
        driver.findElement(selectGradeLocator).click();
        Select drpGrade = new Select(driver.findElement(selectYearLocator));
        drpGrade.selectByVisibleText(grade);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",
                driver.findElement(By.xpath("//span[@class='ui-select-placeholder text-muted']")));

      //  wait.until(ExpectedConditions.elementToBeClickable(courseTeacherLocator)).sendKeys(courseTeacher);
        //  driver.findElement(courseTeacherLocator).sendKeys(courseTeacher);

        driver.findElement(courseCompletionLocator).click();
        driver.findElement(courseCreateLocator).click();
    }

    public boolean isTestCourseDisplayed() {
        return driver.findElement(By.xpath("//td[text()='Test Course']")).isDisplayed();
    }
}

