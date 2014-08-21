package softwareinclude.ro.securitylockandroid.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.List;

import softwareinclude.ro.securitylockandroid.R;
import softwareinclude.ro.securitylockandroid.adapter.AccountItemAdapter;
import softwareinclude.ro.securitylockandroid.interfaces.IDatabaseManager;
import softwareinclude.ro.securitylockandroid.model.AccountDataModel;

/**
 * Created by Sebastian Manolescu on 20.08.2014.
 */
public class ItemAddDialog extends Dialog implements View.OnClickListener{

    private Context context;
    private Button cancelAdd;
    private Button doneAdd;
    private EditText accountItemName;
    private EditText accountItemPassword;
    private EditText detailsItemInput;
    private ImageButton addDetailsItem;
    private LinearLayout addDetailsLayout;

    private IDatabaseManager databaseManager;
    private AccountItemAdapter accountItemAdapter;
    List<AccountDataModel> databaseItemsList;

    public ItemAddDialog(Context context, IDatabaseManager databaseManager,AccountItemAdapter accountItemAdapter, List<AccountDataModel> databaseItemsList) {
        super(context);
        this.context = context;
        this.databaseManager = databaseManager;
        this.accountItemAdapter = accountItemAdapter;
        this.databaseItemsList = databaseItemsList;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_item_dialog);

        initUI();
        initData();
    }

    private void initUI(){

        cancelAdd = (Button) findViewById(R.id.cancelAdd);
        cancelAdd.setOnClickListener(this);
        doneAdd = (Button) findViewById(R.id.doneAdd);
        doneAdd.setOnClickListener(this);

        accountItemName = (EditText) findViewById(R.id.accountItemName);
        accountItemPassword = (EditText) findViewById(R.id.accountItemPassword);
        detailsItemInput = (EditText) findViewById(R.id.detailsItemInput);

        addDetailsItem = (ImageButton) findViewById(R.id.addDetailsItem);
        addDetailsItem.setOnClickListener(this);

        addDetailsLayout = (LinearLayout) findViewById(R.id.addDetailsLayout);

    }

    private void initData() {
        detailsItemInput.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cancelAdd: {
                accountItemAdapter.setNotifyOnChange(true);
                accountItemAdapter.notifyDataSetChanged();
                dismiss();
                break;
            }

            case R.id.doneAdd: {

                //Insert new AccountDataModel object in Database
                AccountDataModel accountData = new AccountDataModel();
                accountData.setAccountName(accountItemName.getText().toString());
                accountData.setAccountPassword(accountItemPassword.getText().toString());
                if(detailsItemInput.isEnabled()){
                    accountData.setAccountDetails("Test");
                }else {
                    accountData.setAccountDetails(detailsItemInput.getText().toString());
                }

                databaseManager.insertAccountDataItem(accountData);
                databaseItemsList.add(accountData);
                accountItemAdapter.notifyDataSetChanged();

                dismiss();
                break;
            }

            case R.id.addDetailsItem: {
                addDetailsLayout.setVisibility(View.GONE);
                detailsItemInput.setVisibility(View.VISIBLE);
                break;
            }

            default: {
                break;
            }
        }
    }
}
