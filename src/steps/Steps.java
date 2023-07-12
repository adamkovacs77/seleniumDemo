package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tabs.CodeTab;
import tabs.IssuesTab;
import utils.Utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Steps {

    private ChromeDriver driver;
    private WebDriverWait wait;
    private IssuesTab issuesTab;
    private CodeTab codeTab;
    private Map<String, String> jsonFields;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver","src\\utils\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        issuesTab = new IssuesTab(driver, wait);
        codeTab = new CodeTab(driver, wait);
        jsonFields = new HashMap<>();
    }

    @After
    public void after() {
        driver.quit();
    }

    @Given("I navigate to {string}")
    public void iNavigateToURL(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("I click on \"Code\" dialog")
    public void iClickOnCodeDialog() {
        codeTab.clickOnCodeDialog();
    }

    @When("I close the \"Code\" dialog if it's open")
    public void iCloseTheCodeDialogIfItsOpen() {
        codeTab.closeDialogIfOpen();
    }

    @When("I get the repository URL")
    public void iGetTheRepositoryURL() {
        jsonFields.put("Repository URL", codeTab.getRepositoryUrlFromCodeDialog());
    }

    @When("I get the number of releases")
    public void iGetTheNumberOfReleases() {
        jsonFields.put("Number of releases", codeTab.getNumberOfReleases());
    }

    @Then("I create the output json file named {string}")
    public void iCreateTheOutputJsonFileNamed(String fileName) {
        Utils.createJsonFile(jsonFields, fileName);
    }

    @When("I open the \"Issues\" tab")
    public void iOpenTheIssuesTab() {
        issuesTab.openIssuesTab();
    }

    @When("I clear the issues search bar")
    public void iClearTheIssuesSearchBar() {
        issuesTab.clearIssuesSearchBar();
    }

    @When("I insert into issues search bar {string}")
    public void iInsertIntoIssuesSearchBar(String text) {
        issuesTab.insertIntoIssuesSearchBar(text);
    }

    @When("I open Label filter menu")
    public void iOpenLabelFilterMenu() {
        issuesTab.openLabelFilterMenu();
    }

    @When("I search by label filter {string}")
    public void iInsertIntoLabelFilter(String text) {
        issuesTab.insertIntoLabelFilterSearchBarAndSearch(text);
    }

    @When("I click on \"Open\" issues button")
    public void iClickOnOpenIssuesButton() {
        issuesTab.clickOnOpenIssuesButton();
    }

    @When("I get the first issue row")
    public void iGetTheFirstIssueRow() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        issuesTab.getFirstIssueRow();
    }

    @When("I get the issue title")
    public void iGetTheIssueTitle() {
        jsonFields.put("Title", issuesTab.getIssueTitle());
    }

    @When("I get the issue labels")
    public void iGetTheIssueLabels() {
        jsonFields.put("Labels", issuesTab.getIssueLabels());
    }

    @When("I get the issue number of comments")
    public void iGetTheIssueNOComments() {
        jsonFields.put("Number of comments", issuesTab.getNumberOfIssueComments());
    }

    @When("I get the issue id")
    public void iGetTheIssueID() {
        jsonFields.put("ID", issuesTab.getIssueId());
    }

    @When("I get the issue author")
    public void iGetTheIssueAuthor() {
        jsonFields.put("Author", issuesTab.getIssueAuthor());
    }

    @When("I get the issue creation time")
    public void iGetTheIssueCreationTime() {
        jsonFields.put("Creation time", issuesTab.getIssueCreationTime());
    }
}