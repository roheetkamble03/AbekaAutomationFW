package base;

import elementConstants.AbekaHome;
import elementConstants.Login;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageObjects.AbekaHomeScreen;

public abstract class GenericAction extends SelenideExtended{

    @BeforeMethod
    protected void setUp(String browserName) {
        super.setUp();
        launchApp(browserName);
        //Allure report writing will be done letter
    }

    @AfterMethod
    public void tearDown(){
        log("After each method tearing down the test in GenericAction.class");
        super.tearDown();
    }
    public AbekaHomeScreen loginToAbeka(String userName, String password){
        waitAndCloseSignUpPop();
        click(AbekaHome.login);
        click(AbekaHome.logInCreateAccount);
        type(Login.emailAddress,userName);
        type(Login.password,password);
        click(Login.loginBtn);
        waitForAbekaBGProcessLogoDisappear();
        waitForElementTobeVisible("text=Hello, RCG");
        return new AbekaHomeScreen();
    }

    public AbekaHomeScreen logoutFromAbeka(){
        click(AbekaHome.accountGreeting);
        click(AbekaHome.logout);
        waitForElementTobeVisible(AbekaHome.account);
        return new AbekaHomeScreen();
    }

    public void waitAndCloseSignUpPop(){
        waitForElementTobeEnabled(AbekaHome.closeSignup);
        click(AbekaHome.closeSignup);
    }
}
