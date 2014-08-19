package softwareinclude.ro.securitylockandroid.gui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
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
import android.widget.ImageView;

import softwareinclude.ro.securitylockandroid.R;
import softwareinclude.ro.securitylockandroid.util.ApplicationConstants;

public class LockScreen extends Activity implements View.OnClickListener{

    private EditText passwordInput;
    private Button unlock;
    private ImageView lockImage;

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
        lockImage = (ImageView) findViewById(R.id.lockImage);
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

    /**
     * Create rotation and after the animation finish activity
     */
    public void startAnimation() {
        float dest = 360;
        if (lockImage.getRotation() == 360) {
            dest = 0;
        }
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(lockImage,
                "rotation", dest);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }});
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

                startAnimation();

                break;
            }

            default: {
                break;
            }
        }
    }
}
