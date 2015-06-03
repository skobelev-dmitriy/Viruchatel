package hm.viruchatel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener {
    public static final String TAG="myLogs";
    Button but_vk, but_fb, but_login,but_skip_pass,  but_signup;
    EditText et_email, et_pass;
    EditText newMail;
     AlertDialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       setContentView(R.layout.activity_login);
        but_fb=(Button)findViewById(R.id.but_fb);
        but_vk=(Button)findViewById(R.id.but_vk);
        but_login=(Button)findViewById(R.id.but_login);
        but_signup=(Button)findViewById(R.id.but_signup);
        but_skip_pass=(Button)findViewById(R.id.but_skip_pass);

        but_login.setOnClickListener(this);
        but_signup.setOnClickListener(this);
        but_skip_pass.setOnClickListener(this);
        but_vk.setOnClickListener(this);
        but_fb.setOnClickListener(this);

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


    private void signup(){
        Intent intent=new Intent(this,SignupActivity.class);
        startActivity(intent);
    }
    private void login(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private void skipPass(){
        String mail=newMail.getText().toString();
        Log.d(TAG, "new mail: "+mail);
        Intent intent=new Intent(this,ChangePassActivity.class);
        startActivity(intent);
    }
    private void showSkipDialog(){
     /*   DialogInterface.OnClickListener listener=new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case R.id.but_cancel:
                        Log.d(TAG, "Dialog - Cancel");
                        break;
                    case R.id.but_send:
                        Log.d(TAG, "Dialog - OK");
                        break;
                }
            }
        };*/
        View v=getLayoutInflater().inflate(R.layout.dialog_skip_pass,null);
        Button cancel=(Button)v.findViewById(R.id.but_cancel);
        Button send=(Button)v.findViewById(R.id.but_send);
        newMail=(EditText)v.findViewById(R.id.ed_new_email);
        cancel.setOnClickListener(this);
        send.setOnClickListener(this);


        AlertDialog.Builder aBuilder=new AlertDialog.Builder(this)
                .setView(v);



        dialog= aBuilder.create();
        dialog.show();



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_fb:


                break;
            case R.id.but_vk:

                break;
            case R.id.but_login:

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
}
