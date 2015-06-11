package hm.viruchatel;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class StartActivity extends Activity {
    public static final String TAG="myLogs";
    private static final int STOPSPLASH=0;
    private static final long SPLASHTIME=1000;

    private boolean login;


    private Handler splashHandler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case STOPSPLASH:
                    selectActivity();
            }
            super.handleMessage(msg);
        }
    };

    private void selectActivity() {
        Intent intent;
        if (isFirstStart()){
            intent = new Intent(this, TutorialActivity.class);
        }else if(checkLogin()) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
              }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_start);
        checkLogin();

        Message msg=new Message();
        msg.what=STOPSPLASH;
        splashHandler.sendMessageDelayed(msg, SPLASHTIME);






    }
    private boolean checkLogin(){
        SharedPreferences sp=getSharedPreferences("AppPref", Activity.MODE_PRIVATE);
        boolean login=sp.getBoolean("login",false);
        Log.d(TAG, "Login=" + login);
        return login;
    }
    private boolean isFirstStart(){
        SharedPreferences sp=getSharedPreferences("AppPref",Activity.MODE_PRIVATE);
        boolean isFirst=sp.getBoolean("isFirstStart", true);
        Log.d(TAG, "isFirst Start: "+isFirst);
        return isFirst;

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

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}
