package softwareinclude.ro.securitylockandroid.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import softwareinclude.ro.securitylockandroid.R;
import softwareinclude.ro.securitylockandroid.dialog.ItemAddDialog;
import softwareinclude.ro.securitylockandroid.interfaces.IDatabaseManager;
import softwareinclude.ro.securitylockandroid.manager.DatabaseManager;
import softwareinclude.ro.securitylockandroid.model.AccountDataModel;
import softwareinclude.ro.securitylockandroid.util.ApplicationConstants;

/**
 * @author Sebastian Manolescu
 */
public class MainActivity extends Activity implements View.OnClickListener{

    //Lock Icon
    private ImageButton addPassword;
    private ImageButton changePassword;
    private ImageButton removePassword;
    //Add Password
    private EditText addPasswordInput;
    private EditText addRetypePasswordInput;
    private Button addPasswordDone;
    //Change Password
    private EditText changePasswordCurrentInput;
    private EditText changePasswordNewInput;
    private EditText changePasswordRetypeInput;
    private Button changePasswordDone;
    //Remove Password
    private EditText removePasswordInput;
    private Button removePasswordDone;
    //Layout
    private RelativeLayout lockStatusIcon;
    private RelativeLayout addPasswordLayout;
    private RelativeLayout changePasswordLayout;
    private RelativeLayout removePasswordLayout;
    //hide option
    private ImageButton hideLockIcon;
    private ImageButton hideAddView;
    private ImageButton hideChangeView;
    private ImageButton hideRemoveView;
    //show option
    private ImageButton addItem;
    //help buttons
    private Button lockOptions;
    //List
    private ListView accountItemListView;
    private ArrayAdapter<String> accountItemsArrayAdapter ;
    private List<String> accountList;

    private IDatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // init database manager
        databaseManager = new DatabaseManager(this);


        initUI();
        initData();
    }

    /**
     * Initialize UI components and Data
     */
    private void initUI() {
        //Icon button
        addPassword = (ImageButton) findViewById(R.id.addPasswordIcon);
        addPassword.setOnClickListener(this);
        changePassword = (ImageButton) findViewById(R.id.changePasswordIcon);
        changePassword.setOnClickListener(this);
        removePassword = (ImageButton) findViewById(R.id.removePasswordIcon);
        removePassword.setOnClickListener(this);

        //Layout action
        lockStatusIcon = (RelativeLayout) findViewById(R.id.lockStatusIcon);
        addPasswordLayout = (RelativeLayout) findViewById(R.id.addPasswordLayout);
        changePasswordLayout = (RelativeLayout) findViewById(R.id.changePasswordLayout);
        removePasswordLayout = (RelativeLayout) findViewById(R.id.removePasswordLayout);

        //Add Password
        addPasswordInput = (EditText) findViewById(R.id.addPasswordInput);
        addRetypePasswordInput = (EditText) findViewById(R.id.addRetypePasswordInput);
        addPasswordDone = (Button) findViewById(R.id.addPasswordDone);
        addPasswordDone.setOnClickListener(this);

        //Change Password
        changePasswordCurrentInput = (EditText) findViewById(R.id.changeCurrentPasswordInput);
        changePasswordNewInput = (EditText) findViewById(R.id.changeNewPasswordInput);
        changePasswordRetypeInput = (EditText) findViewById(R.id.changeRetypePasswordInput);
        changePasswordDone = (Button) findViewById(R.id.changePasswordDone);
        changePasswordDone.setOnClickListener(this);

        //Remove Password
        removePasswordInput = (EditText) findViewById(R.id.removePasswordInput);
        removePasswordDone = (Button) findViewById(R.id.removePasswordDone);
        removePasswordDone.setOnClickListener(this);

        //Hide Views
        hideLockIcon = (ImageButton) findViewById(R.id.hideLockIcon);
        hideLockIcon.setOnClickListener(this);
        hideAddView = (ImageButton) findViewById(R.id.hideAddView);
        hideAddView.setOnClickListener(this);
        hideChangeView = (ImageButton) findViewById(R.id.hideChangeView);
        hideChangeView.setOnClickListener(this);
        hideRemoveView = (ImageButton) findViewById(R.id.hideRemoveView);
        hideRemoveView.setOnClickListener(this);

        //Show Views
        addItem = (ImageButton) findViewById(R.id.addItem);
        addItem.setOnClickListener(this);

        lockOptions = (Button) findViewById(R.id.lockOptions);
        lockOptions.setOnClickListener(this);

        accountItemListView = (ListView) findViewById(R.id.accountItemListView);
        accountList = new ArrayList<String>();
        List<AccountDataModel> databaseItemsList = databaseManager.loadListAccounts();
        for(AccountDataModel dataModel : databaseItemsList){
            accountList.add(dataModel.getAccountName());
        }
        accountItemsArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, accountList);
        accountItemListView.setAdapter( accountItemsArrayAdapter );
    }

    /**
     * Initialize startup data and views
     */
    private void initData() {
        addPasswordLayout.setVisibility(View.GONE);
        changePasswordLayout.setVisibility(View.GONE);
        removePasswordLayout.setVisibility(View.GONE);

        String currentPassword = loadPasswordPref(ApplicationConstants.SHARED_PREFERENCES_PASSWORD,this);
        if(currentPassword != null && !currentPassword.isEmpty()){
            Intent lockScreenIntent = new Intent(MainActivity.this, LockScreen.class);
            startActivity(lockScreenIntent);
        }
    }

    /**
     * Save Password in SharedPreferences
     * @param key
     * @param value
     * @param context
     */
    private static void savePasswordPref(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Load Password from SharedPreferences
     * @param key
     * @param context
     * @return
     */
    private static String loadPasswordPref(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    /**
     * Clear text from all EditText components when
     * the lock status icon is pressed
     */
    private void clearInputData() {
        addPasswordInput.setText(ApplicationConstants.EMPTY);
        addRetypePasswordInput.setText(ApplicationConstants.EMPTY);
        changePasswordCurrentInput.setText(ApplicationConstants.EMPTY);
        changePasswordNewInput.setText(ApplicationConstants.EMPTY);
        changePasswordRetypeInput.setText(ApplicationConstants.EMPTY);
        removePasswordInput.setText(ApplicationConstants.EMPTY);
    }

    /**
     * Check if EditText input is empty
     * @param edittext
     * @return
     */
    private boolean emptyInputCheck(EditText edittext){
        if(edittext.getText().toString().isEmpty()){
            edittext.setError("Empty");
            return true;
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.addPasswordIcon: {
                addPasswordLayout.setVisibility(View.VISIBLE);
                changePasswordLayout.setVisibility(View.GONE);
                removePasswordLayout.setVisibility(View.GONE);
                hideLockIcon.setVisibility(View.GONE);
                clearInputData();
                break;
            }

            case R.id.changePasswordIcon: {
                addPasswordLayout.setVisibility(View.GONE);
                changePasswordLayout.setVisibility(View.VISIBLE);
                removePasswordLayout.setVisibility(View.GONE);
                hideLockIcon.setVisibility(View.GONE);
                clearInputData();
                break;
            }

            case R.id.removePasswordIcon: {
                addPasswordLayout.setVisibility(View.GONE);
                changePasswordLayout.setVisibility(View.GONE);
                removePasswordLayout.setVisibility(View.VISIBLE);
                hideLockIcon.setVisibility(View.GONE);
                clearInputData();
                break;
            }

            case R.id.addPasswordDone: {
                //check if fields are empty
                if(addPasswordInput == null || addPasswordInput==null ||
                   emptyInputCheck(addPasswordInput) || emptyInputCheck(addRetypePasswordInput)){
                    break;
                }
                //check if password match and save in SharedPreferences
                if(addPasswordInput.getText().toString().equals(addRetypePasswordInput.getText().toString())){
                    savePasswordPref(ApplicationConstants.SHARED_PREFERENCES_PASSWORD,addPasswordInput.getText().toString(),this);
                    addPasswordLayout.setVisibility(View.GONE);
                    hideLockIcon.setVisibility(View.VISIBLE);
                }else{
                    addRetypePasswordInput.setError("Please Retype the same password as above");
                }
                break;
            }

            case R.id.changePasswordDone: {
                //Check for null or empty fields
                if(emptyInputCheck(changePasswordCurrentInput) || emptyInputCheck(changePasswordNewInput)
                                                               || emptyInputCheck(changePasswordRetypeInput)){
                    break;
                }
                //check current password if valid
                String password = loadPasswordPref(ApplicationConstants.SHARED_PREFERENCES_PASSWORD,this);
                if(!changePasswordCurrentInput.getText().toString().equals(password)){
                    changePasswordCurrentInput.setError("Invalid Password");
                    break;
                }
                //check password match
                if(!changePasswordNewInput.getText().toString().equals(changePasswordRetypeInput.getText().toString())){
                    changePasswordRetypeInput.setError("Please Retype the same password as above");
                    break;
                }
                //save new password in SharedPreferences
                savePasswordPref(ApplicationConstants.SHARED_PREFERENCES_PASSWORD,changePasswordNewInput.getText().toString(),this);

                changePasswordLayout.setVisibility(View.GONE);
                hideLockIcon.setVisibility(View.VISIBLE);
                break;
            }

            case R.id.removePasswordDone: {
                String password = loadPasswordPref(ApplicationConstants.SHARED_PREFERENCES_PASSWORD,this);
                if(removePasswordInput.getText().toString().equals(password)){
                    savePasswordPref(ApplicationConstants.SHARED_PREFERENCES_PASSWORD,ApplicationConstants.EMPTY,this);
                    removePasswordLayout.setVisibility(View.GONE);
                    hideLockIcon.setVisibility(View.VISIBLE);
                }else{
                    removePasswordInput.setError("Wrong Password");
                }
                break;
            }

            case R.id.lockOptions: {
                    lockStatusIcon.setVisibility(View.VISIBLE);
                if(addPasswordLayout.getVisibility() == View.GONE && changePasswordLayout.getVisibility() == View.GONE
                                                                  && removePasswordLayout.getVisibility() == View.GONE){
                    hideLockIcon.setVisibility(View.VISIBLE);
                }
                break;
            }

            case R.id.hideLockIcon: {
                lockStatusIcon.setVisibility(View.GONE);
                break;
            }

            case R.id.hideAddView: {
                addPasswordLayout.setVisibility(View.GONE);
                hideLockIcon.setVisibility(View.VISIBLE);
                clearInputData();
                break;
            }

            case R.id.hideChangeView: {
                changePasswordLayout.setVisibility(View.GONE);
                hideLockIcon.setVisibility(View.VISIBLE);
                clearInputData();
                break;
            }

            case R.id.hideRemoveView: {
                removePasswordLayout.setVisibility(View.GONE);
                hideLockIcon.setVisibility(View.VISIBLE);
                clearInputData();
                break;
            }

            case R.id.addItem: {
                ItemAddDialog itemAddDialog = new ItemAddDialog(this,databaseManager);
                itemAddDialog.show();
                break;
            }

            default: {
                break;
            }
        }
    }

    /**
     * Called after your activity has been stopped, prior to it being started again.
     * Always followed by onStart()
     */
    @Override
    protected void onRestart() {
        if (databaseManager == null)
            databaseManager = new DatabaseManager(this);

        super.onRestart();
    }

    /**
     * Called after onRestoreInstanceState(Bundle), onRestart(), or onPause(), for your activity
     * to start interacting with the user.
     */
    @Override
    protected void onResume() {
        // init database manager
        databaseManager = DatabaseManager.getInstance(this);

        super.onResume();
    }

    /**
     * Called when you are no longer visible to the user.
     */
    @Override
    protected void onStop() {
        if (databaseManager != null)
            databaseManager.closeDbConnections();

        super.onStop();
    }

}
