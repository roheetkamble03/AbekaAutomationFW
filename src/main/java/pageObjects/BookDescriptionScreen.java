package pageObjects;

import base.GenericAction;
import elementConstants.BookDescription;

public class BookDescriptionScreen extends GenericAction {
    @Override
    protected void setUp(String browserName) {

    }

    public BookDescriptionScreen selectBookingCriteria(String enrollmentPeriod, String materials, String programOption, String quantity){
        click(enrollmentPeriod);
        click(materials);
        click(programOption);
        type(BookDescription.quantityTxtBox, quantity);
        return this;
    }

    public void clickOnAddToCart(){
        click(BookDescription.addToCart);
    }
}
