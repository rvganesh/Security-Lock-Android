package softwareinclude.ro.securitylockandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import softwareinclude.ro.securitylockandroid.R;
import softwareinclude.ro.securitylockandroid.interfaces.IDatabaseManager;
import softwareinclude.ro.securitylockandroid.model.AccountDataModel;

/**
 * Created by Sebastian Manolescu on 21.08.2014.
 */
public class AccountItemAdapter extends ArrayAdapter<AccountDataModel>{

    private Context context;
    private List<AccountDataModel> accountDataModel;
    private ViewHolder holder;
    private IDatabaseManager databaseManager;

    /**
     * Adapter constructor
     * @param context
     * @param accountDataModel
     */
    public AccountItemAdapter(Context context, List<AccountDataModel> accountDataModel,IDatabaseManager databaseManager) {
        super(context, R.layout.account_item_element, accountDataModel);
        this.context = context;
        this.accountDataModel = accountDataModel;
        this.databaseManager = databaseManager;
    }

    /**
     *  getView method is called for each item of ListView
     */
    public View getView(int position,  View convertView , ViewGroup parent){

        AccountDataModel accountObject = accountDataModel.get(position);


            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.account_item_element, null);
            holder = new ViewHolder();
            initUI(holder, convertView,accountObject);

        return convertView ;
    }

    /**
     * Init List Element components and click action on remove icon
     * @param holder
     * @param convertView
     * @param accountObject
     */
    private void initUI(ViewHolder holder,View convertView, final AccountDataModel accountObject) {
        holder.elementItemName = (TextView)convertView.findViewById(R.id.elementItemName);
        holder.removeItemElement = (ImageButton)convertView.findViewById(R.id.removeItemElement);
        holder.elementItemName.setText(accountObject.getAccountName());
        //Remove list Element
        holder.removeItemElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseManager.deleteAccountDataItem(accountObject);
                accountDataModel.remove(accountObject);
                AccountItemAdapter.this.notifyDataSetChanged();
            }
        });
    }





    /**
     * View Holder contains UI components
     */
    static class ViewHolder
    {
         TextView elementItemName;
         ImageButton removeItemElement;

    }
}
