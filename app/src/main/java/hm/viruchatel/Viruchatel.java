package hm.viruchatel;

import android.app.Application;

import com.facebook.FacebookSdk;


/**
 * Created by Дмитрий on 04.06.2015.
 */
public class Viruchatel extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());


    }
}
