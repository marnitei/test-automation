package testImplementations.account;

import models.AccountModel;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.AccountPage;

import java.util.Optional;

public class CreateNewAccount {
    AccountPage accountView;
    AccountModel accountData;

    public CreateNewAccount(AccountPage accountPage, AccountModel accountModel) {
        accountView = accountPage;
        accountData = accountModel;
    }

    public AccountPage createNewAccount() {
        SoftAssert softAssert = new SoftAssert();
        accountView.goToAccountListOverview().openAllAccountsList().clickOnNew();
        Optional.ofNullable(accountData.getAccountName()).ifPresent(accountView::fillAccountName);
        Optional.ofNullable(accountData.getPhone()).ifPresent(accountView::setAccountPhone);
        Optional.ofNullable(accountData.getFax()).ifPresent(accountView::setAccountFax);
        Optional.ofNullable(accountData.getWebsite()).ifPresent(accountView::setAccountWebsite);
        accountView.clickOnSave();
        softAssert.assertTrue(accountView.getAlert().contains("Account \"" + accountData.getAccountName() + "\" was created"), "Alert mismatch");
        softAssert.assertEquals(accountView.getAccountNameFieldValue(), accountData.getAccountName(), "Account was not created or Account Name is wrong");
        softAssert.assertAll();
        return this.accountView;
    }
}
