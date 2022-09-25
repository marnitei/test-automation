package testImplementations.account;

import models.AccountModel;
import org.testng.asserts.SoftAssert;
import pages.AccountPage;

import java.util.Optional;

public class EditAccountFlow {

    AccountPage accountView;
    AccountModel accountUpdatesModel;

    public EditAccountFlow(AccountPage accountPage, AccountModel accountModelUpdates) {
        accountView = accountPage;
        this.accountUpdatesModel = accountModelUpdates;
    }

    public void editAccount(String accountName) {
        SoftAssert softAssert = new SoftAssert();
        accountView.goToAccountListOverview().openAllAccountsList().openAccountRecord(accountName).clickOnEdit();
        Optional.ofNullable(accountUpdatesModel.getAccountName()).ifPresent(accountView::fillAccountName);
        Optional.ofNullable(accountUpdatesModel.getPhone()).ifPresent(accountView::setAccountPhone);
        Optional.ofNullable(accountUpdatesModel.getFax()).ifPresent(accountView::setAccountFax);
        Optional.ofNullable(accountUpdatesModel.getWebsite()).ifPresent(accountView::setAccountWebsite);
        accountView.clickOnSave();

        softAssert.assertEquals(accountView.getAccountFaxFieldValue(), accountUpdatesModel.getFax(), "Account Fax was not updated");
        softAssert.assertEquals(accountView.getAccountPhoneFieldValue(), accountUpdatesModel.getPhone(), "Account Phone was not updated");
        softAssert.assertEquals(accountView.getAccountWebsiteFieldValue(), accountUpdatesModel.getWebsite(), "Account Website was not updated");
        if (accountUpdatesModel.getAccountName() == null) {
            softAssert.assertEquals(accountView.getAccountNameFieldValue(), accountName, "Account Name was not updated");
        } else {
            softAssert.assertEquals(accountView.getAccountNameFieldValue(), accountUpdatesModel.getAccountName(), "Account Name is wrong");
        }
        softAssert.assertAll();
    }
}
