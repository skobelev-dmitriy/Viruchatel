package hm.viruchatel.models;

import android.app.Fragment;
import android.os.Bundle;

import hm.viruchatel.models.LoginModel;

/**
 * Created by Дмитрий on 04.06.2015.
 */
public class LoginWorkerFragment extends Fragment {

    private final LoginModel loginModel;

    public LoginWorkerFragment(){
        loginModel=new LoginModel();
    }
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public LoginModel getLoginModel() {
        return loginModel;
    }
}
