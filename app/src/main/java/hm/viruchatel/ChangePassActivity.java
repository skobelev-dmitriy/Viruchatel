package hm.viruchatel;

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
import android.widget.EditText;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;


public class ChangePassActivity extends ActionBarActivity {
    MaterialEditText oldPass, pass1, pass2;
    String soldPass,sPswd;
    Toolbar toolbar;

    private void saveNewPass(){
        InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(toolbar.getWindowToken(), 0);
        if (errors()==null){
            Intent intent =new Intent(this,LoginActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Ошибка",Toast.LENGTH_LONG).show();
        }

    }
    private String errors(){
        String empty="Не заполнено поле";
        String notEqual="Пароли не совпадают";
        String error=null;

        if (TextUtils.isEmpty(pass1.getText().toString())||TextUtils.isEmpty(pass2.getText().toString())){
            error=empty;
        }
        if (pass1.getText().toString().equals(pass2.getText().toString())){
            sPswd=pass1.getText().toString();
        }else{
            error=notEqual;
        }

        return error;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        boolean forget=intent.getBooleanExtra("forget", true);
        setContentView(R.layout.activity_change_pass);
        oldPass=(MaterialEditText)findViewById(R.id.et_pass_old);
        pass1=(MaterialEditText)findViewById(R.id.et_pass1);
        pass2=(MaterialEditText)findViewById(R.id.et_pass2);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));

        toolbar.setLogo(R.drawable.ic_close);

        if (forget){
            oldPass.setVisibility(View.GONE);
        }else {
            oldPass.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_change_pass, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            saveNewPass();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
