package abekaUISuite;

import base.BaseClass;
import base.Generic;
import dataProvider.DataProviders;
import elementConstants.AbekaHome;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.AbekaHomeScreen;
import pageObjects.LoginScreen;

public class LoginTestSuite extends Generic {
    AbekaHomeScreen abekaHomeScreen;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browserName) {
        super.launchApp(browserName);
    }

    @Test(dataProvider = "credentials", dataProviderClass = DataProviders.class)
    public void enrollmentPurchase(String userId, String password, String loggedInUserName){
        abekaHomeScreen = loginToAbeka(userId,password,loggedInUserName);
        abekaHomeScreen.navigateToShopByGrade();
    }
}
