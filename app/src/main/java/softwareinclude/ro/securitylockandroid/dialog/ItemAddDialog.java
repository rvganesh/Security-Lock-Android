package softwareinclude.ro.securitylockandroid.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import softwareinclude.ro.securitylockandroid.R;

/**
 * Created by Sebastian Manolescu on 20.08.2014.
 */
public class ItemAddDialog extends Dialog implements View.OnClickListener{

    private Context context;
    private Button cancelAdd;
    private Button doneAdd;

    public ItemAddDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_item_dialog);

        init();
    }

    public void init(){

        cancelAdd = (Button) findViewById(R.id.cancelAdd);
        cancelAdd.setOnClickListener(this);
        doneAdd = (Button) findViewById(R.id.doneAdd);
        doneAdd.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cancelAdd: {
                dismiss();
                break;
            }

            case R.id.doneAdd: {
                dismiss();
                break;
            }

            default: {
                break;
            }
        }
    }
}
