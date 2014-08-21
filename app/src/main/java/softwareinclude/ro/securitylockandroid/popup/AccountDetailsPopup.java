package softwareinclude.ro.securitylockandroid.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import softwareinclude.ro.securitylockandroid.R;
import softwareinclude.ro.securitylockandroid.model.AccountDataModel;

/**
 * Created by Sebastian Manolescu on 21.08.2014.
 */
public class AccountDetailsPopup extends PopupWindow{

    private Activity context;
    private TextView detailsPopupAccountName;
    private TextView detailsPopupAccountPassword;
    private TextView detailsPopupAccount;
    private int locationOnScreenHeight;
    private AccountDataModel accountObject;

    public AccountDetailsPopup(Activity context, int locationOnScreenHeight, AccountDataModel accountObject){
        this.context = context;
        this.locationOnScreenHeight = locationOnScreenHeight;
        this.accountObject = accountObject;
    }

    /**
     * Display Popup on screen
     */
    public void showPopup() {

        //Find screen size/resolution
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        // Inflate the popup_layout.xml
        //LinearLayout viewGroup = (LinearLayout) ((Activity)context).findViewById(R.id.detailsPopupLayout);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.details_account_popup, null);

        //Init Data and UI
        initUI(layout);
        initData();


        //Popup Width and height
        int popupWidth = width/(5/2);
        int popupHeight = height / 3;

        setContentView(layout);
        setWidth(popupWidth);
        setHeight(popupHeight);

        //Dismiss on outside touch
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);

        // Displaying the popup at the specified location, + offsets if any.
         showAtLocation(layout, Gravity.NO_GRAVITY, width/2 - popupWidth/2 , locationOnScreenHeight);
    }

    /**
     * Init UI components
     */
    public void initUI(View layout) {
        detailsPopupAccountName = (TextView)layout.findViewById(R.id.detailsPopupAccountName);
        detailsPopupAccountPassword = (TextView)layout.findViewById(R.id.detailsPopupAccountPassword);
        detailsPopupAccount = (TextView)layout.findViewById(R.id.detailsPopupAccount);

        detailsPopupAccountName.setText(accountObject.getAccountName().toString());
        detailsPopupAccountPassword.setText(accountObject.getAccountPassword().toString());
        detailsPopupAccount.setText(accountObject.getAccountDetails().toString());
    }

    /**
     * Initialize components with data from the account objects
     */
    public void initData() {



    }

}

