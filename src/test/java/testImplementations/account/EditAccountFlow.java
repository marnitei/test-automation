package testImplementations.account;

import models.AccountModel;
import org.testng.asserts.SoftAssert;
import pages.AccountPage;

import java.util.Optional;

public class EditAccountFlow {

    AccountPage accountView;
    AccountModel accountUpdatesModel;

    public EditAccountFlow(AccountModel accountModelUpdates, AccountPage pageObject) {
        accountView = pageObject;
        this.accountUpdatesModel = accountModelUpdates;
    }

    public void editAccount(String accountName, AccountModel expectedResult) {
        SoftAssert softAssert = new SoftAssert();

        accountView.goToAccountListOverview().openAllAccountsList().openAccountRecord(accountName).clickOnEdit();
        Optional.ofNullable(accountUpdatesModel.getNewAccountName()).ifPresent(accountView::fillAccountName);
        Optional.ofNullable(accountUpdatesModel.getPhone()).ifPresent(accountView::setAccountPhone);
        Optional.ofNullable(accountUpdatesModel.getFax()).ifPresent(accountView::setAccountFax);
        Optional.ofNullable(accountUpdatesModel.getWebsite()).ifPresent(accountView::setAccountWebsite);
        accountView.clickOnSave();

        softAssert.assertEquals(accountView.getAccountFaxFieldValue(), expectedResult.getFax(), "Account Fax was not updated");
        softAssert.assertEquals(accountView.getAccountPhoneFieldValue(), expectedResult.getPhone(), "Account Phone was not updated");
        softAssert.assertEquals(accountView.getAccountWebsiteFieldValue(), expectedResult.getWebsite(), "Account Website was not updated");
        softAssert.assertEquals(accountView.getAccountNameFieldValue(), expectedResult.getAccountName(), "Account Name is wrong");
        softAssert.assertAll();
    }

}
