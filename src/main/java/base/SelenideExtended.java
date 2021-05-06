package base;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import constants.EnumUtil;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static com.codeborne.selenide.Selenide.*;

public abstract class SelenideExtended extends BaseClass {
    WebDriver driver;
    String textXpath = "//*[normalize-space(text())='%s']";
    String textContainsXpath = "//*[contains(normalize-space(text()),'%s')]";

    public void click(String identifier){
        getElement(identifier).click();
    }

    public void scrollToVisibleElement(String identifier) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", getElement(identifier));
        click("id=123");
    }

    public boolean isElementDisplayed(String identifier) {
        try {
            return getElement(identifier).isDisplayed();
        }catch (ElementNotFound e){
            return false;
        }
    }

    public boolean isSelected(String identifier) {
        return getElement(identifier).isSelected();
    }

    public boolean isEnabled(String identifier) {
        return getElement(identifier).isEnabled();
    }

    /**
     * Type text at location
     *
     * @param identifier element identifier
     * @param text
     * @return - true/false
     */
    public void type(String identifier, String text) {
        SelenideElement ele = getElement(identifier);
        ele.setValue(text);
    }

    /**
     *
     * @param identifier element identifier
     * @param value to be selected
     */
    public void selectOptionBySendkeys(String identifier, String value) {
        getElement(identifier).sendKeys(value);
    }

    /**
     * select value from DropDown by using selectByIndex
     *
     * @param identifier element identifier
     *
     * @param index       : Index of value wish to select from dropdown list.
     *
     */
    public void selectByIndex(String identifier, int index) {
            Select s = new Select(getElement(identifier));
            s.selectByIndex(index);
    }

    /**
     * select value from DD by using value
     *
     * @param identifier element identifier
     *
     * @param value       : Value wish to select from dropdown list.
     */
    public void selectByValue(String identifier,String value) {
//            Select s = new Select(getElement(identifier).selectOptionByValue(););
//            s.selectByValue(value);
            getElement(identifier).selectOptionByValue(value);
    }

    /**
     * select value from DropDown by using selectByVisibleText
     *
     * @param identifier element identifier
     *
     * @param visibletext : VisibleText wish to select from dropdown list.
     *
    **/
    public void selectByVisibleText(String identifier, String visibletext) {
            Select s = new Select(getElement(identifier));
            s.selectByVisibleText(visibletext);
    }

    /**
     *
     * @param identifier element identifier
     * @return
     */
    public void mouseHoverByJavaScript(String identifier) {
            SelenideElement element = getElement(identifier);
            String javaScript = "var evObj = document.createEvent('MouseEvents');"
                    + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                    + "arguments[0].dispatchEvent(evObj);";
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(javaScript, element);
    }

    /**
     *
     * @param identifier element identifier
     */
    public void clickByJavaScript(String identifier) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", getElement(identifier));
    }

    /**
     * Switching frame by index
     * @param index
     * @return
     */
    public void switchToFrameByIndex(int index) {
            new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
            driver.switchTo().frame(index);
    }

    /**
     * This method switch the to frame using frame ID.
     *
     * @param idValue : Frame ID wish to switch
     *
     */
    public void switchToFrameById(String idValue) {
            driver.switchTo().frame(idValue);
    }

    /**
     * This method switch the to frame using frame Name.
     *
     * @param nameValue : Frame Name wish to switch
     *
     */
    public void switchToFrameByName(String nameValue) {
            driver.switchTo().frame(nameValue);
    }

    /**
     * Switching to default frame
     * @param driver
     */
    public void switchToDefaultFrame(WebDriver driver) {
            driver.switchTo().defaultContent();
    }

    /**
     * Mouse over on an element
     * @param identifier element identifier
     */
    public void mouseOverOnElement(String identifier) {
        new Actions(driver).moveToElement(getElement(identifier)).build().perform();
    }

    /**
     * Bringing element into view via java script
     * @param identifier
     * @return
     */
    public void bringElementIntoView(String identifier) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].scrollIntoView(true);", getElement(identifier));
            Actions actions = new Actions(driver);
            actions.moveToElement(getElement(identifier)).build().perform();
    }

    /**
     * Dragging selecting element from one point to another
     * @param identifier
     * @param x
     * @param y
     * @return
     */
    public void dragElementFromOnePointXToAnotherY(String identifier, int x, int y) {
            new Actions(driver).dragAndDropBy(getElement(identifier), x, y).build().perform();
            implicitWaitFor(5);
    }

    /**
     * Dragging element from one element to another element
     * @param source
     * @param target
     * @return
     */
    public void dragAndDropElementFromToElement(String source, String target) {
            new Actions(driver).dragAndDrop(getElement(source), getElement(target)).perform();
    }

    /**
     * Slide element
     * @param identifier
     * @param x
     * @param y
     * @return
     */
    public void slideElement(String identifier, int x, int y) {
            new Actions(driver).dragAndDropBy(getElement(identifier), x, y).build().perform();// 150,0
            implicitWaitFor(5);
    }

    /**
     * Right click on element
     * @param identifier
     * @return
     */
    public void rightClickOnElement(String identifier) {
            Actions clicker = new Actions(driver);
            clicker.contextClick(getElement(identifier)).perform();
    }


    /**
     * Switching to window by title
     * @param windowTitle
     * @param count
     * @return
     */
    public void switchWindowByTitle(String windowTitle, int count) {
            Set<String> windowList = driver.getWindowHandles();

            String[] array = windowList.toArray(new String[0]);

            driver.switchTo().window(array[count-1]);
    }

    /**
     * Switching to new window
     */
    public void switchToNewWindow() {
            Set<String> s=driver.getWindowHandles();
            Object popup[]=s.toArray();
            driver.switchTo().window(popup[1].toString());
    }

    /**
     * Switching to old window
     */
    public void switchToOldWindow() {
         Set<String> s=driver.getWindowHandles();
         Object popup[]=s.toArray();
         driver.switchTo().window(popup[0].toString());
    }

    /**
     *
     * @param row
     * @return
     */
    public int getColumncount(String row) {
        List<WebElement> columns = getElement(row).findElements(By.tagName("td"));
        int a = columns.size();
        System.out.println(columns.size());
        for (WebElement column : columns) {
            System.out.print(column.getText());
            System.out.print("|");
        }
        return a;
    }

    /**
     *
     * @param table
     * @return
     */
    public int getRowCount(String table) {
        List<WebElement> rows = getElement(table).findElements(By.tagName("tr"));
        int a = rows.size() - 1;
        return a;
    }


    /**
     * Verify alert present or not
     *
     * @return: Boolean (True: If alert preset, False: If no alert)
     *
     */
    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
            // if present consume the alert
            alert.accept();
    }

    /**
     * Navigates to specified URL
     * @param url
     */
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    /**
     * Is alert peresent
     * @return
     */
    public boolean isAlertPresent()
    {
        try
        {
            driver.switchTo().alert();
            return true;
        }   // try
        catch (NoAlertPresentException Ex)
        {
            return false;
        }   // catch
    }

    /**
     *
     * @return
     */
    public String getPageTitle() {
        boolean flag = false;

        String text = driver.getTitle();
        if (flag) {
            System.out.println("Title of the page is: \""+text+"\"");
        }
        return text;
    }

    /**
     *
     * @return
     */
    public String getCurrentURL()  {
        boolean flag = false;

        String text = driver.getCurrentUrl();
        if (flag) {
            System.out.println("Current URL is: \""+text+"\"");
        }
        return text;
    }

    /**
     *
     * @param identifier
     * @param timeOut
     * @param pollingTime
     */
    public void waitForElementTobeVisible(String identifier, int timeOut, int pollingTime) {
        Wait<WebDriver> wait = null;
        wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(timeOut))
                    .pollingEvery(Duration.ofSeconds(pollingTime))
                    .ignoring(Exception.class);
            wait.until(ExpectedConditions.visibilityOf(getElement(identifier)));
    }

    /**
     *
     * @param timeOutInSeconds
     */
    public void implicitWaitFor(int timeOutInSeconds) {
        driver.manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
    }

    /**
     *
     * @param identifier
     * @param timeOut
     */
    public void explicitWait(String identifier, int timeOut ) {
        WebDriverWait wait = new WebDriverWait(driver,timeOut);
        wait.until(ExpectedConditions.presenceOfElementLocated(getByClause(identifier)));
    }

    /**
     *
     * @param timeOut
     */
    public void waitTillPageLoad(int timeOut) {
        driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
    }

    /**
     *
     * @param filename
     * @return
     */
    public String screenShot(String filename) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (Exception e) {
            e.getMessage();
        }
        // This new path for jenkins
        String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename + "_"
                + dateName + ".png";
        return newImageString;
    }

    @SneakyThrows
    public SelenideElement getElement(String identifier) {
        return $(getByClause(identifier));
    }

    @SneakyThrows
    public List<SelenideElement> getElements(String identifier) {
        return $$(getByClause(identifier));
    }

    @SneakyThrows
    public By getByClause(String identifier){
        if(identifier.startsWith(EnumUtil.XPATH)){
            return By.xpath(identifier.substring(EnumUtil.XPATH.length()));
        }
        if(identifier.startsWith(EnumUtil.TEXT)){
            return By.xpath(String.format(textXpath,identifier.substring(EnumUtil.TEXT.length())));
        }
        if(identifier.startsWith(EnumUtil.CONTAINS_TEXT)){
            return By.xpath(String.format(textContainsXpath,identifier.substring(EnumUtil.TEXT.length())));
        }
        if(identifier.startsWith(EnumUtil.ID_KEY)){
            return By.id(identifier.substring(EnumUtil.ID_KEY.length()));
        }
        if(identifier.startsWith(EnumUtil.NAME_KEY)){
            return By.name(identifier.substring(EnumUtil.NAME_KEY.length()));
        }
        if(identifier.startsWith(EnumUtil.CLASS_NAME)){
            return By.className(identifier.substring(EnumUtil.CLASS_NAME.length()));
        }
        if(identifier.startsWith(EnumUtil.LINK_TEXT)){
            return By.linkText(identifier.substring(EnumUtil.LINK_TEXT.length()));
        }
        if(identifier.startsWith(EnumUtil.TAG_NAME)){
            return By.tagName(identifier.substring(EnumUtil.TAG_NAME.length()));
        }
        if(identifier.startsWith(EnumUtil.CSS_KEY)){
            return By.cssSelector(identifier.substring(EnumUtil.CSS_KEY.length()));
        }
        throw new Exception("Element identifier criteria does not match, use "+ EnumUtil.class);
    }
}
