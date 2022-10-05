package dataProviders;

import models.AccountModel;
import org.testng.annotations.DataProvider;

public class AccountDataProvider {
    @DataProvider(name = "account-data-creation")
    public static Object[][] provideAccountsForCreation() {
        return new Object[][]{
                {AccountModel.builder().accountName("TestInterviewAccountData").fax("9379992").phone("+39 222555458").website("websitetest.com").build()},
                {AccountModel.builder().accountName("TestInterviewAccountData1").phone("+37 87642228").build()},
                {AccountModel.builder().accountName("TestInterviewAccountData2").phone("+35 8784511").website("webagaintest.com").build()}
        };
    }

    @DataProvider(name = "account-data-edit")
    public static Object[][] provideAccountsForUpdate() {
        return new Object[][]{
                {
                        AccountModel.builder().accountName("TestInterviewAccountData").fax("11111").phone("+39 21111111").website("itestit1.com").build(),
                        AccountModel.builder().accountName("TestInterviewAccountData").fax("11111").phone("+39 21111111").website("itestit1.com").build()
                },
                {
                        AccountModel.builder().accountName("TestInterviewAccountData1").website("itestit2.com").build(),
                        AccountModel.builder().accountName("TestInterviewAccountData1").fax("").phone("+37 87642228").website("itestit2.com").build()
                },
                {
                        AccountModel.builder().accountName("TestInterviewAccountData2").newAccountName("Sole").phone("+375 4545455").fax("3333333333").build(),
                        AccountModel.builder().accountName("Sole").phone("+375 4545455").fax("(333) 333-3333").website("webagaintest.com").build()
                }
        };
    }
}
