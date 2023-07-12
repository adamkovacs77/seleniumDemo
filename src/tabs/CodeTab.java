package tabs;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;

public class CodeTab {

    private ChromeDriver driver;
    private WebDriverWait wait;
    private By codeDialogElement = By.cssSelector("div.Layout-main > div.file-navigation.mb-3.d-flex.flex-items-start > span.d-none.d-md-flex.ml-2 > get-repo > details");
    private By repositoryUrlInputFiledElement = By.cssSelector("#local-panel > ul > li:nth-child(1) > tab-container > div:nth-child(2) > div > input");
    private By noReleasesElement = By.cssSelector("#repo-content-pjax-container > div > div > div.Layout.Layout--flowRow-until-md.Layout--sidebarPosition-end.Layout--sidebarPosition-flowRow-end > div.Layout-sidebar > div > div:nth-child(2) > div > h2 > a > span");

    public CodeTab(ChromeDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickOnCodeDialog() {
        driver.findElement(codeDialogElement).click();
    }

    public String getRepositoryUrlFromCodeDialog() {
        wait.until(ExpectedConditions.elementToBeClickable(repositoryUrlInputFiledElement));
        return driver.findElement(repositoryUrlInputFiledElement).getAttribute("aria-label");
    }

    public void closeDialogIfOpen() {
        if(Utils.isAttributePresent(driver.findElement(codeDialogElement), "open")) {
            driver.findElement(repositoryUrlInputFiledElement).sendKeys(Keys.ESCAPE);
        }
    }

    public String getNumberOfReleases() {
        wait.until(ExpectedConditions.elementToBeClickable(noReleasesElement));
        return driver.findElement(noReleasesElement).getText();
    }
}
