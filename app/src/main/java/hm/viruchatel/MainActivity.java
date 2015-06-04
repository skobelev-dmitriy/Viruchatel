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
        return new NavigationDrawerAccountsMenuHandler(this)
                .addAddAccount(new Intent(getApplicationContext(), SignupActivity.class))
               .addManageAccounts(new Intent(getApplicationContext(), LoginActivity.class));
    }



    @Override
    public void onNavigationDrawerAccountChange(Account account) {
        Toast.makeText(getApplicationContext(), "Account changed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public NavigationDrawerTopHandler getNavigationDrawerTopHandler() {
        return new NavigationDrawerTopHandler(this)
                .addItem(R.string.need_help, R.drawable.ic_menu_help, new NeedHelpFragment())
                .addItem(R.string.messages,R.drawable.ic_menu_messages, new MessagesFragment())
                .addItem(R.string.frends,R.drawable.ic_menu_fiends, new MessagesFragment())
                .addItem(R.string.groups,R.drawable.ic_menu_groups, new MessagesFragment())
                .addItem(R.string.gps_tracer, R.drawable.ic_menu_gps, new MessagesFragment())
                .addItem(R.string.profile,R.drawable.ic_menu_profile, new MessagesFragment())
                .addItem(R.string.settings,R.drawable.ic_menu_settings, new MessagesFragment());
                //.addItem(R.string.about,R.drawable.ic_menu_about, new AboutFragment());

    }

    @Override
    public void onBackPressed() {
        finish();
        //super.onBackPressed();
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
