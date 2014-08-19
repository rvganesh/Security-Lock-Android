package softwareinclude.ro.securitylockandroid.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import softwareinclude.ro.securitylockandroid.R;
import softwareinclude.ro.securitylockandroid.util.ApplicationConstants;


public class MainActivity extends Activity implements View.OnClickListener{



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
    private LinearLayout addPasswordLayout;
    private LinearLayout changePasswordLayout;
    private LinearLayout removePasswordLayout;


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
        addPassword = (ImageButton) findViewById(R.id.addPasswordIcon);
        addPassword.setOnClickListener(this);
        changePassword = (ImageButton) findViewById(R.id.changePasswordIcon);
        changePassword.setOnClickListener(this);
        removePassword = (ImageButton) findViewById(R.id.removePasswordIcon);
        removePassword.setOnClickListener(this);

        //Layout action
        addPasswordLayout = (LinearLayout) findViewById(R.id.addPasswordLayout);
        changePasswordLayout = (LinearLayout) findViewById(R.id.changePasswordLayout);
        removePasswordLayout = (LinearLayout) findViewById(R.id.removePasswordLayout);

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
    }

    /**
     * Initialize startup data and views
     */
    private void initData() {

        addPasswordLayout.setVisibility(View.GONE);
        changePasswordLayout.setVisibility(View.GONE);
        removePasswordLayout.setVisibility(View.GONE);

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



    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.addPasswordIcon: {
                addPasswordLayout.setVisibility(View.VISIBLE);
                changePasswordLayout.setVisibility(View.GONE);
                removePasswordLayout.setVisibility(View.GONE);

                clearInputData();
                break;
            }

            case R.id.changePasswordIcon: {
                addPasswordLayout.setVisibility(View.GONE);
                changePasswordLayout.setVisibility(View.VISIBLE);
                removePasswordLayout.setVisibility(View.GONE);

                clearInputData();
                break;
            }

            case R.id.removePasswordIcon: {
                addPasswordLayout.setVisibility(View.GONE);
                changePasswordLayout.setVisibility(View.GONE);
                removePasswordLayout.setVisibility(View.VISIBLE);

                clearInputData();
                break;
            }

            case R.id.addPasswordDone: {
                Toast.makeText(this,"Password Added",Toast.LENGTH_SHORT).show();

                addPasswordLayout.setVisibility(View.GONE);
                break;
            }

            default: {
                break;
            }

        }
    }
}
