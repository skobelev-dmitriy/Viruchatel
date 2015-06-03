package hm.viruchatel;


import android.content.Intent;
import android.widget.Toast;

import com.blunderer.materialdesignlibrary.activities.NavigationDrawerActivity;
import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsMenuHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerBottomHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerTopHandler;
import com.blunderer.materialdesignlibrary.models.Account;


public class MainActivity extends NavigationDrawerActivity {

    @Override
    public NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler() {
        return new NavigationDrawerAccountsHandler(this);


    }

    @Override
    public NavigationDrawerAccountsMenuHandler getNavigationDrawerAccountsMenuHandler() {
        return new NavigationDrawerAccountsMenuHandler(this);
              //  .addAddAccount(new Intent(getApplicationContext(), AddAccountActivity.class))
              //  .addManageAccounts(new Intent(getApplicationContext(), ManageAccountsActivity.class));
    }



    @Override
    public void onNavigationDrawerAccountChange(Account account) {
        Toast.makeText(getApplicationContext(), "Account changed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public NavigationDrawerTopHandler getNavigationDrawerTopHandler() {
        return new NavigationDrawerTopHandler(this)
                .addItem(R.string.need_help, new NeedHelpFragment())
                .addItem(R.string.messages, new MessagesFragment())
                .addItem(R.string.frends, new MessagesFragment())
                .addItem(R.string.groups, new MessagesFragment())
                .addItem(R.string.gps_tracer, new MessagesFragment())
                .addItem(R.string.profile, new MessagesFragment())
                .addItem(R.string.settings, new MessagesFragment())
                .addItem(R.string.about, new Intent(this, TutorialActivity.class));

    }

    @Override
    public NavigationDrawerBottomHandler getNavigationDrawerBottomHandler() {
        return new NavigationDrawerBottomHandler(this);
            /*    .addSettings(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                      //  Intent intent = new Intent(this, AboutActivity.class);
                      // startActivity(intent);
                    }

                })*/
              /*  .addHelpAndFeedback(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), HelpAndFeedbackActivity.class);
                        startActivity(intent);
                    }

                });*/
    }

    @Override
    public boolean overlayActionBar() {
        return true;
    }

    @Override
    public boolean replaceActionBarTitleByNavigationDrawerItemTitle() {
        return true;
    }

    @Override
    public int defaultNavigationDrawerItemSelectedPosition() {
        return 0;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }


}
