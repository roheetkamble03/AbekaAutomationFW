package pageObjects;

import base.GenericAction;
import elementConstants.AbekaHome ;

public class AbekaHomeScreen extends GenericAction {
    public void navigateToShopByGrade(){
        navigateToHeaderBannerSubmenu(AbekaHome.shop,AbekaHome.firstGrade);
    }

    public void navigateToHeaderBannerSubmenu(String menu, String submenu){
        mouseOverOnElement(menu);
        click(submenu);
    }

    @Override
    protected void setUp(String browserName) {

    }
}
