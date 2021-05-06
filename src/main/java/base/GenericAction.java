package base;

import elementConstants.AbekaHome;
import elementConstants.Login;
import pageObjects.AbekaHomeScreen;

public abstract class GenericAction extends SelenideExtended{

    public AbekaHomeScreen loginToAbeka(String userName, String password, String loggedInUserName){
        click(AbekaHome.login);
        click(AbekaHome.logInCreateAccount);
        type(Login.emailAddress,userName);
        type(Login.password,password);
        click(Login.loginBtn);
        waitForElementTobeVisible("Hello, "+loggedInUserName, pollingTimeOut, elementLoadWait);
        return new AbekaHomeScreen();
    }
}
