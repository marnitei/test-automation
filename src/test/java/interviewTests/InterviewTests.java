package interviewTests;

import baseUtils.BaseWebTest;
import dataProviders.AccountDataProvider;
import models.AccountModel;
import org.testng.annotations.Test;
import testImplementations.account.CreateNewAccount;
import testImplementations.account.EditAccountFlow;

public class InterviewTests extends BaseWebTest {

    @Test(priority = 0, description = "Account Creation", dataProvider = "account-data-creation", dataProviderClass = AccountDataProvider.class)
    public void createInterviewAccount(AccountModel accountCreation) {
        CreateNewAccount account = new CreateNewAccount(accountCreation, accountPage);
        account.createNewAccount();
    }

    @Test(priority = 1, description = "Edit Account", dataProvider = "account-data-edit", dataProviderClass = AccountDataProvider.class)
    public void editInterviewAccount(AccountModel accountUpdate, AccountModel result) {
        EditAccountFlow editAccountFlow = new EditAccountFlow(accountUpdate, accountPage);
        editAccountFlow.editAccount(accountUpdate.getAccountName(), result);
    }
}
