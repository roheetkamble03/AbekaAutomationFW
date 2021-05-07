package abekaUISuite;

import base.GenericAction;
import constants.CheckoutCriteria;
import constants.CommonTexts;
import dataProvider.DataProviders;
import elementConstants.BookDescription;
import elementConstants.Search;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.AbekaHomeScreen;

public class LoginTestSuite extends GenericAction {
    AbekaHomeScreen abekaHomeScreen;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browserName) {
        super.setUp(browserName);
    }

    @Test(dataProvider = "credentials", dataProviderClass = DataProviders.class)
    public void enrollmentPurchase(String userId, String password){
        abekaHomeScreen = loginToAbeka(userId,password);
        abekaHomeScreen.navigateToShopByGrade().selectProduct(Search.gradeOneEnrollment).
                selectBookingCriteria(BookDescription.fullYear,BookDescription.videoAndBooks,BookDescription.accredited,CommonTexts.one).
                clickOnAddToCart();
        abekaHomeScreen.navigateToShoppingCartPage().clickOnCheckOut().
                selectCheckoutCriteria(CheckoutCriteria.builder().build()).
                clickOnPlaceOrder().clickOnFinishYourEnrollment().validateNewlyEnrolledCourses(CommonTexts.gradeOneAccredited);
        abekaHomeScreen.logoutFromAbeka();
    }
}
