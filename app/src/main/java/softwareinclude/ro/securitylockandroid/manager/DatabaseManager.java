package softwareinclude.ro.securitylockandroid.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import softwareinclude.ro.securitylockandroid.interfaces.IDatabaseManager;
import softwareinclude.ro.securitylockandroid.model.AccountDataModel;
import softwareinclude.ro.securitylockandroid.model.AccountDataModelDao;
import softwareinclude.ro.securitylockandroid.model.DaoMaster;
import softwareinclude.ro.securitylockandroid.model.DaoSession;

/**
 * Created by Sebastian Manolescu on 20.08.2014.
 */
public class DatabaseManager implements IDatabaseManager {

    /**
     * Class tag. Used for debug.
     */
    private static final String TAG = DatabaseManager.class.getCanonicalName();

    /**
     * Instance of DatabaseManager
     */
    private static DatabaseManager instance;

    /**
     * The Android Activity reference for access to DatabaseManager.
     */
    private Context context;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase database;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    /**
     * Constructs a new DatabaseManager with the specified arguments.
     *
     * @param context The Android {@link android.content.Context}.
     */
    public DatabaseManager(final Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(this.context, "sample-database", null);
    }

    /**
     * @param context The Android {@link android.content.Context}.
     * @return this.instance
     */
    public static DatabaseManager getInstance(Context context) {

        if (instance == null) {
            instance = new DatabaseManager(context);
        }

        return instance;
    }

    /**
     * Query for readable DB
     */
    public void openReadableDb() throws SQLiteException {
        database = mHelper.getReadableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    /**
     * Query for writable DB
     */
    public void openWritableDb() throws SQLiteException {
        database = mHelper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    @Override
    public void closeDbConnections() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
        if (database != null && database.isOpen()) {
            database.close();
        }
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
        if (instance != null) {
            instance = null;
        }
    }

    @Override
    public void insertAccountDataItem(AccountDataModel accountData) {
        try {
            if (accountData != null) {
                openWritableDb();
                AccountDataModelDao userDao = daoSession.getAccountDataModelDao();
                userDao.insert(accountData);

                Log.d(TAG, "Inserted user: " + accountData.getAccountName() + " to the schema.");

                daoSession.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccountDataItem() {

    }

    @Override
    public void deleteAccountDataItem(AccountDataModel accountData) {

        try {
            openWritableDb();
            AccountDataModelDao accountDataModelDao = daoSession.getAccountDataModelDao();
            QueryBuilder<AccountDataModel> queryBuilder = accountDataModelDao.queryBuilder();
            List<AccountDataModel> userToDelete = queryBuilder.list();
            for (AccountDataModel user : userToDelete) {
                accountDataModelDao.delete(user);
            }
            daoSession.clear();
            Log.d(TAG, userToDelete.size() + " entry. " + "Deleted account: " + accountData.getAccountName() + " from the schema.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void findAccountById() {

    }

    @Override
    public List<AccountDataModel> loadListAccounts() {
        List<AccountDataModel> accoutsList = null;
        try {
            openReadableDb();
            AccountDataModelDao accountDataModelDao = daoSession.getAccountDataModelDao();
            accoutsList = accountDataModelDao.loadAll();

            daoSession.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return accoutsList;
    }
}
