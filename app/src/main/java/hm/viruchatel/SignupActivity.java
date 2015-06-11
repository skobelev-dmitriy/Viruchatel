package hm.viruchatel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import hm.viruchatel.models.LoginWorkerFragment;
import hm.viruchatel.models.SigninModel;
import hm.viruchatel.models.SigninWorkerFragment;


public class SignupActivity extends ActionBarActivity implements SigninModel.Observer{
    private static final String TAG_WORKER = "TAG_WORKER";
    Toolbar toolbar;
    MaterialEditText fio, phone, email, pass1, pass2;

    RadioGroup radioGroup;
    RadioButton male, female;
    Button signup;
    boolean errors;
    SigninModel mSigninModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
       // toolbar.setLogo(R.drawable.ic_back);
        toolbar.setNavigationIcon(R.drawable.ic_back);

        fio=(MaterialEditText)findViewById(R.id.et_fio);
        phone=(MaterialEditText)findViewById(R.id.et_phone);
        email=(MaterialEditText)findViewById(R.id.et_email);
        pass1=(MaterialEditText)findViewById(R.id.et_pass21);
        pass2=(MaterialEditText)findViewById(R.id.et_pass22);
        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);
        signup=(Button)findViewById(R.id.but_signup);

        final SigninWorkerFragment retainedWorkerFragment=(SigninWorkerFragment)getFragmentManager().findFragmentByTag(TAG_WORKER);
        if(retainedWorkerFragment!=null){
            mSigninModel=retainedWorkerFragment.getSigninModel();
        }else{
            final SigninWorkerFragment workerFragment=new SigninWorkerFragment();
            getFragmentManager().beginTransaction()
                    .add(workerFragment,TAG_WORKER)
                    .commit();
            mSigninModel=workerFragment.getSigninModel();
        }

        mSigninModel.registerObserver(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkViews();
                if(!errors){
                    String mFio=fio.getText().toString();
                    String mPhone=phone.getText().toString();
                    String mEmail=email.getText().toString();
                    String mPass=pass1.getText().toString();
                   int gender=radioGroup.getCheckedRadioButtonId();

                    mSigninModel.signin( mEmail, mPass,mFio, gender,mPhone);


                }
            }
        });


    }
    private void checkViews(){
        errors=false;

        checkEmpty(fio);
        checkEmpty(phone);
        checkEmpty(email);
        checkEmpty(pass1);
        checkEmpty(pass2);
        checkEqual(pass1, pass2);
        email.validate("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", "Не верный формат!");

    }
    private void checkEmpty(MaterialEditText v){
        String error="Не заполнено поле!";


        if(TextUtils.isEmpty(v.getText().toString())){
            v.setError(error);
            errors=true;
        }
    }
    private void checkEqual(MaterialEditText v1,MaterialEditText v2){
        String error="Пароли не совпадают!";

        if(!v1.getText().toString().equals(v2.getText().toString())){
            v2.setError(error);
            errors=true;
        }
    }




    private void showEndDialog(){
        InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(toolbar.getWindowToken(), 0);
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

    @Override
    public void onSignInStarted(SigninModel mSigninModel) {
        showProgress(true);
    }

    @Override
    public void onSignInSucceeded(SigninModel mSigninModel) {
        showEndDialog();

    }

    @Override
    public void onSignInFailed(SigninModel mSigninModel) {
        showProgress(false);

        Toast.makeText(this, "Ошибка входа", Toast.LENGTH_LONG).show();
    }
    private void showProgress(final boolean show){

        fio.setEnabled(!show);
        phone.setEnabled(!show);
        email.setEnabled(!show);
        pass1.setEnabled(!show);
        pass2.setEnabled(!show);
        radioGroup.setEnabled(!show);
        signup.setEnabled(!show);
    }
}
