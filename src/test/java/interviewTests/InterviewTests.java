package interviewTests;

import baseUtils.BaseWebTest;
import models.AccountModel;
import org.testng.annotations.Test;
import testImplementations.account.CreateNewAccount;
import testImplementations.account.EditAccountFlow;

public class InterviewTests extends BaseWebTest {

    @Test(priority = 0, description = "Account Creation")
    public void createInterviewAccount() {
        CreateNewAccount account = new CreateNewAccount(accountPage, accountModelToCreate());
        account.createNewAccount();
    }

    @Test(priority = 1, description = "Edit Account")
    public void editInterviewAccount() {
        EditAccountFlow editAccountFlow = new EditAccountFlow(accountPage, accountModelToEdit());
        editAccountFlow.editAccount(accountModelToCreate().getAccountName());
    }

    private AccountModel accountModelToCreate() {
        return AccountModel.builder()
                .accountName("TestInterviewAccount")
                .fax("9379992")
                .phone("+39 222555458")
                .website("websitetest.com")
                .build();
    }

    private AccountModel accountModelToEdit() {
        return AccountModel.builder()
                .fax("11111111")
                .phone("+39 111111111")
                .website("editedWebsite.com")
                .build();
    }
}
