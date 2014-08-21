package softwareinclude.ro.securitylockandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
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
import softwareinclude.ro.securitylockandroid.popup.AccountDetailsPopup;

/**
 * Created by Sebastian Manolescu on 21.08.2014.
 */
public class AccountItemAdapter extends ArrayAdapter<AccountDataModel>{

    private Activity context;
    private List<AccountDataModel> accountDataModel;
    private ViewHolder holder;
    private IDatabaseManager databaseManager;
    private AccountDetailsPopup accountDetailsPopup;
    private Point listElementLocationPoint;

    /**
     * Adapter constructor
     * @param context
     * @param accountDataModel
     */
    public AccountItemAdapter(Activity context, List<AccountDataModel> accountDataModel,IDatabaseManager databaseManager) {
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
    private void initUI(final ViewHolder holder,View convertView, final AccountDataModel accountObject) {
        holder.elementItemName = (TextView)convertView.findViewById(R.id.elementItemName);
        holder.removeItemElement = (ImageButton)convertView.findViewById(R.id.removeItemElement);
        holder.elementItemName.setText(accountObject.getAccountName());

        //Event Action on TextView
        holder.elementItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //GET POSITION ON SCREEN
                int[] location = new int[2];
                holder.elementItemName.getLocationOnScreen(location);
                listElementLocationPoint = new Point();
                listElementLocationPoint.x = location[0];
                listElementLocationPoint.y = location[1];

                //find row element height
                int rowHeight = holder.elementItemName.getLayout().getHeight();

                //Added row height to total height because the top
                // of the arrow from the popup was pointing on the top fo the selected row
                int listElementHeight = listElementLocationPoint.y + rowHeight;

                //show popup details
                accountDetailsPopup = new AccountDetailsPopup(context, listElementHeight, accountObject);
                accountDetailsPopup.showPopup();
            }
        });

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
