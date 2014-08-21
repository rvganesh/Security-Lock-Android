package softwareinclude.ro.securitylockandroid.interfaces;

import java.util.List;

import softwareinclude.ro.securitylockandroid.model.AccountDataModel;

/**
 * Created by sema9419 on 20.08.2014.
 */
public interface IDatabaseManager {

    public void closeDbConnections();
    public void insertAccountDataItem(AccountDataModel accountData);
    public void updateAccountDataItem();
    public void deleteAccountDataItem(AccountDataModel accountData);
    public void findAccountById();
    public List<AccountDataModel> loadListAccounts();


}
