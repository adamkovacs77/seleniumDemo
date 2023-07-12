package tabs;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IssuesTab {
    private ChromeDriver driver;
    private WebDriverWait wait;
    private By issuesTabElement = By.id("issues-tab");
    private By issuesSearchBarElement = By.id("js-issues-search");
    private By labelFilterMenuElement = By.id("label-select-menu");
    private By labelFilterMenuSearchBarElement = By.id("label-filter-field");
    private By clearFiltersElement = By.cssSelector("#repo-content-turbo-frame > div > div.issues-reset-query-wrapper > a");
    private By openIssuesButtonElement = By.xpath("//a[contains(@data-ga-click, \"Open\")]");
    private By issuesElements = By.xpath("//div[contains(@class, \"js-issue-row\")]");
    private WebElement firstIssueRow;
    private String author, id, creationTime;

    public IssuesTab(ChromeDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openIssuesTab() {
        driver.findElement(issuesTabElement).click();
        wait.until(ExpectedConditions.elementToBeClickable(issuesSearchBarElement));
    }

    public void clearIssuesSearchBar() {
        driver.findElement(issuesSearchBarElement).clear();
    }

    public void insertIntoIssuesSearchBar(String text) {
        driver.findElement(issuesSearchBarElement).sendKeys(text);
        driver.findElement(issuesSearchBarElement).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(clearFiltersElement));
    }

    public void openLabelFilterMenu() {
        driver.findElement(labelFilterMenuElement).click();
        wait.until(ExpectedConditions.elementToBeClickable(labelFilterMenuSearchBarElement));
    }

    public void insertIntoLabelFilterSearchBarAndSearch(String text) {
        driver.findElement(labelFilterMenuSearchBarElement).sendKeys(text);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.xpath("//div[contains(text(), \"" + text + "\")]")).click();
    }

    public void clickOnOpenIssuesButton() {
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(openIssuesButtonElement).get(1)));
        driver.findElements(openIssuesButtonElement).get(1).click();
    }

    public void getFirstIssueRow() {
        wait.until(ExpectedConditions.stalenessOf(driver.findElements(issuesElements).get(0)));
        firstIssueRow = driver.findElements(issuesElements).get(0);
        setIssueDetails();
    }

    public String getIssueTitle() {
        return firstIssueRow.findElement(By.cssSelector("div > div.flex-auto.min-width-0.p-2.pr-3.pr-md-2 > a")).getText();
    }

    public String getIssueLabels() {
        return firstIssueRow.findElements(By.cssSelector("div > div.flex-auto.min-width-0.p-2.pr-3.pr-md-2 > span > a")).stream().map(WebElement::getText).toList().toString();
    }

    public String getIssueCreationTime() {
        return creationTime;
    }

    public String getIssueAuthor() {
        return author;
    }

    public String getIssueId() {
        return id;
    }

    public String getNumberOfIssueComments() {
        return firstIssueRow.findElement(By.cssSelector("div > div.flex-shrink-0.col-4.col-md-3.pt-2.text-right.pr-3.no-wrap.d-flex.hide-sm > span:nth-child(3) > a > span")).getText();
    }

    private void setIssueDetails() {
        String details = firstIssueRow.findElement(By.cssSelector("div > div.flex-auto.min-width-0.p-2.pr-3.pr-md-2 > div > span.opened-by")).getText();
        id = details.substring(details.indexOf("#") + 1, details.indexOf("opened") - 1);
        creationTime = details.substring(details.indexOf("on") + 3, details.indexOf("by") - 13);
        author = details.substring(details.indexOf("by") + 3);
    }

}
