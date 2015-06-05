package hm.viruchatel;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import hm.viruchatel.Api.ApplicationInterface;


public class MainActivity extends ActionBarActivity implements ApplicationInterface, View.OnClickListener{
    public static final String TAG="myLogs";
    Drawer.Result drawer;
    private static FragmentManager fragmentManager;
    Toolbar toolbar;
    SettingsFragment frSettings=new SettingsFragment();
    NeedHelpFragment frNeedHelp=new NeedHelpFragment();
    NeedHelpList frNeedHelpList=new NeedHelpList();
    MessagesFragment frMessages=new MessagesFragment();
    FriendsFragment frFriends=new FriendsFragment();
    GroupsFragment frGroups=new GroupsFragment();
    GPSFragment frGPS=new GPSFragment();
    ProfileFragment frProfile=new ProfileFragment();
    AboutFragment frAbout=new AboutFragment();


    ImageButton sos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.text_color3));
        fragmentManager=getFragmentManager();

        sos=(ImageButton)findViewById(R.id.but_sos);
        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransact(new SOSFragment(), "Сигнал тревоги");

            }
        });



        //

        setSupportActionBar(toolbar);

     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentTransact(frNeedHelpList, R.string.need_help);
        drawer = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {

                        switch (i) {
                            case 1:
                                fragmentTransact(frNeedHelpList, R.string.need_help);
                                break;
                            case 2:
                                fragmentTransact(frMessages, R.string.messages);
                                break;
                            case 3:
                                fragmentTransact(frFriends, R.string.frends);
                                break;
                            case 4:
                            fragmentTransact(frGroups,R.string.groups);
                            break;
                            case 5:
                                fragmentTransact(frGPS, R.string.gps_tracer);
                                break;
                            case 6:
                                fragmentTransact(frProfile, R.string.profile);
                                break;
                            case 7:
                                fragmentTransact(frSettings,R.string.settings);
                                break;
                            case 8:
                                fragmentTransact(frAbout,R.string.about);
                                break;
                            default:
                                break;
                        }
                    }
                })
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.need_help).withIcon(getResources().getDrawable(R.drawable.ic_menu_help)).withBadge("3").withIdentifier(1),
                        new PrimaryDrawerItem()
                                .withName(R.string.messages).withIcon(getResources().getDrawable(R.drawable.ic_menu_messages)).withBadge("12").withIdentifier(2),
                        new PrimaryDrawerItem()
                                .withName(R.string.frends).withIcon(getResources().getDrawable(R.drawable.ic_menu_fiends)).withBadge("7").withIdentifier(3),
                        new PrimaryDrawerItem()
                                .withName(R.string.groups).withIcon(getResources().getDrawable(R.drawable.ic_menu_groups)).withIdentifier(1),
                        new PrimaryDrawerItem()
                                .withName(R.string.gps_tracer).withIcon(getResources().getDrawable(R.drawable.ic_menu_gps)).withIdentifier(1),
                        new PrimaryDrawerItem()
                                .withName(R.string.profile).withIcon(getResources().getDrawable(R.drawable.ic_menu_profile)).withIdentifier(1),
                        new PrimaryDrawerItem()
                                .withName(R.string.settings).withIcon(getResources().getDrawable(R.drawable.ic_menu_settings)).withIdentifier(1),
                        new PrimaryDrawerItem()
                                .withName(R.string.about).withIcon(getResources().getDrawable(R.drawable.ic_menu_about)).withIdentifier(1)


                    )
                .build();

        ImageButton  but_loguot=(ImageButton)drawer.getHeader().findViewById(R.id.but_logout);
        but_loguot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }



    @Override
    public void onBackPressed() {
        finish();
        //super.onBackPressed();
    }
    public void fragmentTransact (Fragment fragment,String title){
        // if (netState(this)!=false){
        fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.contaner, fragment).commit();
        toolbar.setTitle(title);
        // }
        // else{
        //     Toast.makeText(this, "Нет подключения к интернету :(", Toast.LENGTH_LONG).show();
        //  }
    }
    public void fragmentTransact (Fragment fragment,int resId){
        // if (netState(this)!=false){
        fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.contaner, fragment).commit();
        toolbar.setTitle(getResources().getString(resId));
        // }
        // else{
        //     Toast.makeText(this, "Нет подключения к интернету :(", Toast.LENGTH_LONG).show();
        //  }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();





        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {


    }


    @Override
    public void sendAlarm(String caption,String message, int type) {
        fragmentTransact(frNeedHelpList, R.string.need_help);
        Log.d(TAG, caption + " | " + message + " | " + type);
        Toast.makeText(this, "Сигнал отправлен",Toast.LENGTH_LONG).show();
    }


    public void logout() {
        SharedPreferences sp=getSharedPreferences("AppPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("login",false);
        editor.commit();
        Intent   intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
