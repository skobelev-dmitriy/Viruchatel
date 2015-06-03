package hm.viruchatel;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class SignupActivity extends ActionBarActivity {
    Toolbar toolbar;
    EditText fio, phone, email, pass1, pass2;
    RadioGroup radioGroup;
    RadioButton male, female;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
       // toolbar.setLogo(R.drawable.ic_back);
        toolbar.setNavigationIcon(R.drawable.ic_back);

        fio=(EditText)findViewById(R.id.et_fio);
        phone=(EditText)findViewById(R.id.et_phone);
        email=(EditText)findViewById(R.id.et_email);
        pass1=(EditText)findViewById(R.id.et_pass1);
        pass2=(EditText)findViewById(R.id.et_pass2);
        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);
        signup=(Button)findViewById(R.id.but_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(errors()==null){
                    showEndDialog();
                }
            }
        });


    }
    private String errors(){

        return null;
    }
    private void showEndDialog(){
        final AlertDialog dialog;
        View v=getLayoutInflater().inflate(R.layout.dialog_end_registration, null);
        Button ok=(Button)v.findViewById(R.id.but_ok);
        AlertDialog.Builder aBuilder=new AlertDialog.Builder(this)
                .setView(v);
        dialog= aBuilder.create();
        dialog.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}
