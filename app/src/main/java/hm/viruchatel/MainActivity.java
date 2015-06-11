package hm.viruchatel;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import hm.viruchatel.Api.ApplicationInterface;


public class MainActivity extends ActionBarActivity implements ApplicationInterface, View.OnClickListener{
    public static final String TAG="myLogs";
    public static final int MENU =1;
    public static final int BACK=2;
    public static final int CLOSE=3;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    Drawer drawer;
    private static FragmentManager fragmentManager;
    Toolbar toolbar;
    SettingsFragment frSettings=new SettingsFragment();
    NeedHelpFragment frNeedHelp=new NeedHelpFragment();
    NeedHelpFullFragment frNeedHelpfull=new NeedHelpFullFragment();
    MessagesFragment frMessages=new MessagesFragment();
    FriendsFragment frFriends=new FriendsFragment();
    GroupsFragment frGroups=new GroupsFragment();
    GPSFragment frGPS=new GPSFragment();
    ProfileFragment frProfile=new ProfileFragment();
    AboutFragment frAbout=new AboutFragment();
    SOSFragment sosFragment=new SOSFragment();


    ImageButton sos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.text_color3));
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        fragmentManager=getFragmentManager();

        sos=(ImageButton)findViewById(R.id.but_sos);
        sos.setOnClickListener(this);


        setSupportActionBar(toolbar);

     getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setNavigation(R.drawable.ic_menu, MENU);
        drawer =new  DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
              //  .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
             //   .withDrawerLayout(R.layout.drawer_layout)
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

               .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                   @Override
                   public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {

                       switch (i) {
                           case 0:
                               fragmentTransact(frNeedHelp, R.string.need_help, false);
                               break;
                           case 1:
                               fragmentTransact(frMessages, R.string.messages, false);
                               break;
                           case 2:
                               fragmentTransact(frFriends, R.string.frends, false);
                               break;
                           case 3:
                               fragmentTransact(frGroups, R.string.groups, false);
                               break;
                           case 4:
                               fragmentTransact(frGPS, R.string.gps_tracer, false);
                               break;
                           case 5:
                               fragmentTransact(frProfile, R.string.profile, false);
                               break;
                           case 6:
                               fragmentTransact(frSettings, R.string.settings, false);
                               break;
                           case 7:
                               fragmentTransact(frAbout, R.string.about, false);
                               break;
                           default:
                               break;
                       }
                   return false;
                   }
               })

                .build();
        fragmentTransact(frNeedHelp, R.string.need_help,false);
        ImageButton  but_loguot=(ImageButton)drawer.getHeader().findViewById(R.id.but_logout);
        but_loguot.setOnClickListener(this);

    }

    public void setNavigation(int resId, final int but_action){


        toolbar.setNavigationIcon(resId);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (but_action){
                    case MENU:
                        drawer.openDrawer();
                        break;
                    case BACK:
                        fragmentManager.popBackStack();


                        break;
                    case CLOSE:
                        fragmentManager.popBackStack();

                        break;
                }

            }
        });
    }



    @Override
    public void onBackPressed() {
        if( sosFragment.isAdded()){

        }
        if( frNeedHelpfull.isAdded()){
            fragmentManager.popBackStack();
        }
        if( frNeedHelp.isAdded()){
            super.onBackPressed();
        }
        else{
            fragmentTransact(frNeedHelp, R.string.need_help,false);
        }

       // super.onBackPressed();
    }
    public void fragmentTransact (Fragment fragment,String title,boolean backstack){
        // if (netState(this)!=false){
        if (backstack==true){
            fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.contaner, fragment).addToBackStack(null).commit();
        }else{
            fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.contaner, fragment).commit();
        }
        toolbar.setTitle(title);
        // }
        // else{
        //     Toast.makeText(this, "Нет подключения к интернету :(", Toast.LENGTH_LONG).show();
        //  }
    }
    public void fragmentTransact (Fragment fragment,int resId,boolean backstack){
        // if (netState(this)!=false){
        if (backstack==true){
            fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.contaner, fragment).addToBackStack(null).commit();
        }else{
            fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.contaner, fragment).commit();
        }

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
        switch (item.getItemId()){
            case android.R.id.home:
                Log.d(TAG, "HOME button pressed");
                if( sosFragment.isAdded()){
                    fragmentManager.popBackStack();

                }else{
                    drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
                }
                break;

        }





        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_sos:
                fragmentTransact(sosFragment, R.string.alarm_signal,true);
                setNavigation(R.drawable.ic_back,BACK);
                break;
            case R.id.but_logout:
                logout();
                break;
        }


    }


    @Override
    public void sendAlarm(String caption,String message, int type) {
    //    fragmentTransact(frNeedHelpList, R.string.need_help);
        fragmentManager.popBackStack();
        setNavigation(R.drawable.ic_menu,MENU);
        Log.d(TAG, caption + " | " + message + " | " + type);
        Toast.makeText(this, "Сигнал отправлен",Toast.LENGTH_LONG).show();
    }

    @Override
    public void addInFriends() {

    }

    @Override
    public void sendMessage() {

    }

    @Override
    public void savesMe() {

    }

    @Override
    public void acceptFriend() {
        Toast.makeText(this, "Вы приняли дружбу",Toast.LENGTH_LONG).show();
    }
    @Override
    public void rejectFriend() {
        Toast.makeText(this, "Вы отклонили дружбу",Toast.LENGTH_LONG).show();
    }
    @Override
    public void openFriends() {
        fragmentTransact(new UserFriendsFragment(), R.string.user_friends,true);
        setNavigation(R.drawable.ic_back, BACK);
    }
    @Override
    public  void openInviteFriends(){
        fragmentTransact(new InviteFriendsFragment(), R.string.invite_friends,true);
        setNavigation(R.drawable.ic_back, BACK);

    }

    @Override
    public void openGroups() {
        fragmentTransact(new GroupsFragment(),R.string.user_groups,true);
        setNavigation(R.drawable.ic_back, BACK);
    }
    @Override
    public void openGroup(String title,int id) {
        Bundle args=new Bundle();
        args.putInt("id", id);
        args.putBoolean("member", true);
        GroupFragment frGroup=new GroupFragment();
        frGroup.setArguments(args);
          fragmentTransact(frGroup, title, true);
        setNavigation(R.drawable.ic_back, BACK);
       // startActivity(new Intent(this, GroupActivity.class));
    }

    @Override
    public void openUserInfo() {
        startActivity(new Intent(this, UserInfoActivity.class));
    }

    @Override
    public void openImage() {

    }

    @Override
    public void takePhoto() {
        final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri fileUri;
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }

    @Override
    public void openReviews() {
        fragmentTransact(new ReviewsFragment(),R.string.reviews,true);
        setNavigation(R.drawable.ic_back,BACK);

    }

    @Override
    public void openNewReview() {
        Intent intent=new Intent(this, SendReviewActivity.class );
        startActivity(intent);
    }

    @Override
    public void openProfile(int id) {
        //Добавить загрузку по id
        Log.d(TAG, "Открываем профиль номер "+id);
        fragmentTransact(new ProfileFragment(), R.string.profile, true);
        setNavigation(R.drawable.ic_back, BACK);
    }

    @Override
    public void openChat() {
        fragmentTransact(new ChatFragment(), "", true);
        setNavigation(R.drawable.ic_back, BACK);
    }

    @Override
    public void openFullHelpRequest() {
        fragmentTransact(new NeedHelpFullFragment(), R.string.need_help_2,true);
        setNavigation(R.drawable.ic_back, BACK);

    }

    @Override
    public void orderGPS() {
        Toast.makeText(this, "Вы Заказали GPS",Toast.LENGTH_LONG).show();
    }

    @Override
    public void openLinkGPS() {
        fragmentTransact(new GPSLinkFragment(),R.string.link_gps_number, true);
        setNavigation(R.drawable.ic_back, CLOSE);
    }

    @Override
    public void linkGPS(String number) {

    }

    @Override
    public void openChangePass() {
        Intent intent=new Intent(this, ChangePassActivity.class);
        intent.putExtra("forget",false);
        startActivity(intent);
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

    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }
}
