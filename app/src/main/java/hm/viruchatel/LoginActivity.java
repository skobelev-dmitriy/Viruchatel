package hm.viruchatel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKError;
import com.vk.sdk.dialogs.VKCaptchaDialog;


import java.util.Arrays;

import hm.viruchatel.models.LoginModel;
import hm.viruchatel.models.LoginWorkerFragment;



public class LoginActivity extends ActionBarActivity implements View.OnClickListener, LoginModel.Observer  {
    public static final String TAG="myLogs";
    private static final String TAG_WORKER = "TAG_WORKER";
    public static final int NATIVE=0;
    public static final int FB=1;
    public static final int VK=2;


    Button but_vk,but_fb,  but_login,but_skip_pass,  but_signup;

    boolean errors;
    MaterialEditText et_email, et_pass;
    MaterialEditText newMail;
    TextView tv_error;
     AlertDialog dialog;
    static Context context;
    private LoginModel mLoginModel;
    CallbackManager callbackManager;
    private static String sTokenKey = "VK_ACCESS_TOKEN";
    private static String[] sMyScope = new String[]{VKScope.FRIENDS, VKScope.WALL, VKScope.PHOTOS, VKScope.NOHTTPS};



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        VKUIHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VKSdk.initialize(sdkListener, getResources().getString(R.string.vk_app_id), VKAccessToken.tokenFromSharedPreferences(this, sTokenKey));

        callbackManager = CallbackManager.Factory.create();
        VKUIHelper.onCreate(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);
        context = this;




      //  homeAsUpByBackStack();

        et_email=(MaterialEditText) findViewById(R.id.edit_email);
        et_pass=(MaterialEditText)findViewById(R.id.edit_pass);
        but_fb=(Button)findViewById(R.id.but_fb);

        but_vk=(Button)findViewById(R.id.but_vk);
        but_login=(Button)findViewById(R.id.but_login);
        but_signup=(Button)findViewById(R.id.but_signup);
        but_skip_pass=(Button)findViewById(R.id.but_skip_pass);
        tv_error=(TextView)findViewById(R.id.tw_error);

        but_login.setOnClickListener(this);
        but_signup.setOnClickListener(this);
        but_skip_pass.setOnClickListener(this);
        but_vk.setOnClickListener(this);
        but_fb.setOnClickListener(this);


      //  but_fb.setReadPermissions("user_friends");
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Success", "Login");
                SharedPreferences sp=getSharedPreferences("AppPref", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putBoolean("login_fb",true);
                editor.putInt("account_mode",FB);
                editor.commit();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        final LoginWorkerFragment retainedWorkerFragment=(LoginWorkerFragment)getFragmentManager().findFragmentByTag(TAG_WORKER);
        if(retainedWorkerFragment!=null){
            mLoginModel=retainedWorkerFragment.getLoginModel();
        }else{
            final LoginWorkerFragment workerFragment=new LoginWorkerFragment();
            getFragmentManager().beginTransaction()
                    .add(workerFragment,TAG_WORKER)
                    .commit();
            mLoginModel=workerFragment.getLoginModel();
        }

        mLoginModel.registerObserver(this);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VKUIHelper.onDestroy(this);
        mLoginModel.unregisterObserver(this);

        if(isFinishing()){
            mLoginModel.stopLogin();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getSupportFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void checkViews(){
        errors=false;

        checkEmpty(et_email);
        checkEmpty(et_pass);
        checkEmail(et_email);



    }
    private void checkEmail(MaterialEditText v){
        String error="Не верный формат";


        v.validate("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$","Не верный формат!");
            errors=true;

    }
    private void checkEmpty(MaterialEditText v){
        String error="Не заполнено поле!";


        if(TextUtils.isEmpty(v.getText().toString())){
            v.setError(error);
            errors=true;
        }
    }

      /*  if (pass1.getText().toString().equals(pass2.getText().toString())){
            sPswd=pass1.getText().toString();
        }else{
            error=notEqual;
        }*/



    private void signup(){
        Intent intent=new Intent(this,SignupActivity.class);
        startActivity(intent);
    }
    private void login(){
        checkViews();
        if (!errors){
            final  String email=et_email.getText().toString();
            final String pass=et_pass.getText().toString();
            mLoginModel.login(email,pass);
        }

    //
    }
    private void skipPass(){
        String mail=newMail.getText().toString();
        Log.d(TAG, "new mail: " + mail);
        Intent intent=new Intent(this, ChangePassActivity.class);
        intent.putExtra("forget",true);
        startActivity(new Intent());
    }
    private void showSkipDialog(){

        View v = getLayoutInflater().inflate(R.layout.dialog_skip_pass, null);
        Button cancel=(Button)v.findViewById(R.id.but_cancel);
        Button send=(Button) v.findViewById(R.id.but_send);
        newMail=(MaterialEditText)v.findViewById(R.id.ed_new_email);
        cancel.setOnClickListener(this);
        send.setOnClickListener(this);


        AlertDialog.Builder aBuilder=new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                .setView(v);



        dialog= aBuilder.create();
        dialog.show();



    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.but_fb:
                LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList("public_profile", "user_friends"));
                break;
            case R.id.but_vk:
                VKSdk.authorize(sMyScope, true, false);
                break;
            case R.id.but_login:
                clearError();

                login();



                break;
            case R.id.but_signup:
                signup();

                break;
            case R.id.but_skip_pass:
                showSkipDialog();

                break;
            case R.id.but_cancel:
                dialog.dismiss();
                break;
            case R.id.but_send:
                checkEmail(newMail);
                newMail.validate("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$","Не верный формат!");
                if( newMail.getError().length()==0){
                    skipPass();
                    dialog.dismiss();
                }

                break;
        }


    }

    @Override
    public void onSignInStarted(LoginModel loginModel) {
        showProgress(true);
    }

    @Override
    public void onSignInSucceeded(LoginModel loginModel) {
        SharedPreferences sp=getSharedPreferences("AppPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("login_native",true);
        editor.putInt("account_mode",NATIVE);
        editor.commit();
       Intent intent=new Intent(this, MainActivity.class);

        startActivity(intent);
        finish();
    }

    @Override
    public void onSignInFailed(LoginModel loginModel) {
        showProgress(false);
        setError("Ошибка входа");
    //    Toast.makeText(this,"Ошибка входа", Toast.LENGTH_LONG).show();

    }

    private void showProgress(final boolean show){
        et_email.setEnabled(!show);
        et_pass.setEnabled(!show);
        but_login.setEnabled(!show);
        but_skip_pass.setEnabled(!show);
        but_fb.setEnabled(!show);
        but_vk.setEnabled(!show);
        but_signup.setEnabled(!show);
    }
    private void setError(String error){

        tv_error.setText(error);
        tv_error.setVisibility(View.VISIBLE);
    }
    private  void clearError() {
        tv_error.setVisibility(View.INVISIBLE);
        tv_error.setText(null);
    }

    private VKSdkListener sdkListener = new VKSdkListener() {
        @Override
        public void onCaptchaError(VKError captchaError) {
            new VKCaptchaDialog(captchaError).show();
        }

        @Override
        public void onTokenExpired(VKAccessToken expiredToken) {
            VKSdk.authorize(sMyScope);
        }

        @Override
        public void onAccessDenied(VKError authorizationError) {
            new AlertDialog.Builder(LoginActivity.this)
                    .setMessage(authorizationError.errorMessage)
                    .show();
        }

        @Override
        public void onReceiveNewToken(VKAccessToken newToken) {
            newToken.saveTokenToSharedPreferences(LoginActivity.this, sTokenKey);
            SharedPreferences sp=getSharedPreferences("AppPref", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putBoolean("login_vk",true);
            editor.putInt("account_mode",VK);
            editor.commit();
            Log.d(TAG, "newToken: "+newToken.toString());
             Intent i = new Intent(LoginActivity.this, MainActivity.class);
             startActivity(i);
        }

        @Override
        public void onAcceptUserToken(VKAccessToken token) {
            SharedPreferences sp=getSharedPreferences("AppPref", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.putBoolean("login_vk",true);
            editor.putInt("account_mode",VK);
            editor.commit();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
             startActivity(i);
            Log.d(TAG, "token: "+token.toString());
        }
    };


}
