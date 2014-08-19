package softwareinclude.ro.securitylockandroid.gui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import softwareinclude.ro.securitylockandroid.R;
import softwareinclude.ro.securitylockandroid.util.ApplicationConstants;

public class LockScreen extends Activity implements View.OnClickListener{

    private EditText passwordInput;
    private Button unlock;

    private String loadPasswordSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lock_screen);

        initUI();
        initData();
    }

    /**
     * Init UI views and components
     */
    public void initUI() {
        passwordInput = (EditText)findViewById(R.id.passwordInput);
        unlock = (Button) findViewById(R.id.applicationUnlock);
        unlock.setOnClickListener(this);
    }

    /**
     * Init Data or Set Data for this application
     */
    public void initData() {
        loadPasswordSharedPref =  loadPasswordPref(ApplicationConstants.SHARED_PREFERENCES_PASSWORD,this);
    }

    /**
     * Load Password from SharedPreferences
     * @param key
     * @param context
     * @return
     */
    public static String loadPasswordPref(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.applicationUnlock: {
               String currentInputPassword = passwordInput.getText().toString();
                if(passwordInput.getText().toString().isEmpty()){
                    passwordInput.setError("Empty");
                    break;
                }
                if(!currentInputPassword.equals(loadPasswordSharedPref)){
                    passwordInput.setError("Wrong Password");
                    break;
                }
                finish();
                break;
            }

            default: {
                break;
            }
        }
    }
}
