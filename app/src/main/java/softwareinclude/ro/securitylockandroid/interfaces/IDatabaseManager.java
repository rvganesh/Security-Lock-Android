package softwareinclude.ro.securitylockandroid.interfaces;

import java.util.List;

import softwareinclude.ro.securitylockandroid.model.AccountDataModel;

/**
 * Created by Sebastian Manolescu on 20.08.2014.
 */
public interface IDatabaseManager {

    /**
     * Close Connection
     */
    public void closeDbConnections();

    /**
     * Add New Account Item
     * @param accountData
     */
    public void insertAccountDataItem(AccountDataModel accountData);

    /**
     *Update Account item in DB
     */
    public void updateAccountDataItem();

    /**
     * Delete Account Item
     * @param accountData
     */
    public void deleteAccountDataItem(AccountDataModel accountData);

    /**
     * Find Account using unique ID
     */
    public void findAccountById();

    /**
     * Load All Account type Objects from Database
     * @return
     */
    public List<AccountDataModel> loadListAccounts();

}
