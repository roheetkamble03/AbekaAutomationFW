package pageObjects;

import base.GenericAction;
import elementConstants.ShoppingCart;

public class ShoppingCartPage extends GenericAction {
    @Override
    protected void setUp(String browserName) {

    }

    public CheckoutScreen clickOnCheckOut(){
        bringElementIntoView(ShoppingCart.checkOutBtn);
        click(ShoppingCart.checkOutBtn);
        return new CheckoutScreen();
    }
}
