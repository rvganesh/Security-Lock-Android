package softwareinclude.ro.securitylockandroid.interfaces;

import softwareinclude.ro.securitylockandroid.model.AccountDataModel;

/**
 * Created by sema9419 on 20.08.2014.
 */
public interface IDatabaseManager {

    public void closeDbConnections();
    public void insertAccountDataItem(AccountDataModel accountData);
    public void updateAccountDataItem();
    public void deleteAccountDataItem();
    public void findAccountById();

}
