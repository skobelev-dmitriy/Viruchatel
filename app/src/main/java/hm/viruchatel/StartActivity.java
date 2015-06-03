package hm.viruchatel;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


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

        if (isFirstStart()){
            startActivity(new Intent(this, TutorialActivity.class));

        }else if(login)
            startActivity(new Intent(this, MainActivity.class) );
        else
            startActivity(new Intent(this, LoginActivity.class));
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
    private void checkLogin(){
        SharedPreferences sp=getPreferences(Activity.MODE_PRIVATE);
        login=sp.getBoolean("login",false);
        Log.d(TAG, "Login=" + login);
    }
    private boolean isFirstStart(){
        SharedPreferences sp=getPreferences(Activity.MODE_PRIVATE);
        boolean isFirst=sp.getBoolean("isFirstStart", false);



        //return isFirst;
        return true;
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
