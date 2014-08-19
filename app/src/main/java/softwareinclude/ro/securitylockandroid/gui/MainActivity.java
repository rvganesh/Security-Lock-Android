package softwareinclude.ro.securitylockandroid.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import softwareinclude.ro.securitylockandroid.R;
import softwareinclude.ro.securitylockandroid.util.ApplicationConstants;

/**
 * @author Manolescu Sebastian
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
    private ImageButton showLockOptions;
    //help buttons
    private Button helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

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
        showLockOptions = (ImageButton) findViewById(R.id.showLockOptions);
        showLockOptions.setVisibility(View.GONE);
        showLockOptions.setOnClickListener(this);

        helpButton = (Button) findViewById(R.id.helpButton);
        helpButton.setOnClickListener(this);
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
                if(changePasswordCurrentInput == null || changePasswordNewInput == null || changePasswordRetypeInput == null
                                                      || emptyInputCheck(changePasswordCurrentInput)
                                                      || emptyInputCheck(changePasswordNewInput)
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

            case R.id.helpButton: {
                Toast.makeText(this,"Help in progress",Toast.LENGTH_SHORT).show();

                String password = loadPasswordPref(ApplicationConstants.SHARED_PREFERENCES_PASSWORD,this);
                Log.i("Saved Password Value: ",""+ password);
                break;
            }

            case R.id.hideLockIcon: {
                lockStatusIcon.setVisibility(View.GONE);
                showLockOptions.setVisibility(View.VISIBLE);
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

            case R.id.showLockOptions: {
                lockStatusIcon.setVisibility(View.VISIBLE);
                showLockOptions.setVisibility(View.GONE);
                hideLockIcon.setVisibility(View.VISIBLE);
                break;
            }

            default: {
                break;
            }
        }
    }
}
