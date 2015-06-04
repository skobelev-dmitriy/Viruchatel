package hm.viruchatel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


//import com.github.gorbin.asne.core.SocialNetworkManager;

import com.rengwuxian.materialedittext.MaterialEditText;

import hm.viruchatel.models.LoginModel;
import hm.viruchatel.models.LoginWorkerFragment;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener, LoginModel.Observer {
    public static final String TAG="myLogs";
    private static final String TAG_WORKER = "TAG_WORKER";
    Button but_vk, but_fb, but_login,but_skip_pass,  but_signup;
    boolean errors;
    MaterialEditText et_email, et_pass;
    MaterialEditText newMail;
    TextView tv_error;
     AlertDialog dialog;
    private LoginModel mLoginModel;
  // public  static SocialNetworkManager socialNetworkManager;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       // Fragment fragment = getSupportFragmentManager().findFragmentByTag(BaseDemoFragment.SOCIAL_NETWORK_TAG);
      //  if (fragment != null) {
      //      fragment.onActivityResult(requestCode, resultCode, data);
       // }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       setContentView(R.layout.activity_login);

        et_email=(MaterialEditText)findViewById(R.id.edit_email);
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
    private void checkViews(){
        errors=false;

        checkEmpty(et_email);
        checkEmpty(et_pass);

        et_email.validate("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$","Не верный формат!");

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
        Log.d(TAG, "new mail: "+mail);
        Intent intent=new Intent(this,ChangePassActivity.class);
        startActivity(intent);
    }
    private void showSkipDialog(){

        View v=getLayoutInflater().inflate(R.layout.dialog_skip_pass, null);
        Button cancel=(Button)v.findViewById(R.id.but_cancel);
        Button send=(Button)v.findViewById(R.id.but_send);
        newMail=(MaterialEditText)v.findViewById(R.id.ed_new_email);
        cancel.setOnClickListener(this);
        send.setOnClickListener(this);


        AlertDialog.Builder aBuilder=new AlertDialog.Builder(this)
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


                break;
            case R.id.but_vk:

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
                skipPass();
                dialog.dismiss();
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
        editor.putBoolean("login",true);
        editor.commit();
        Intent intent=new Intent(this,MainActivity.class);
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
    private  void clearError(){
        tv_error.setVisibility(View.INVISIBLE);
        tv_error.setText(null);
    }
}
