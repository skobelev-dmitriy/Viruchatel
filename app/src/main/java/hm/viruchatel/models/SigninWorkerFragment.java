package hm.viruchatel.models;

import android.app.Fragment;
import android.os.Bundle;

import hm.viruchatel.models.LoginModel;

/**
 * Created by Дмитрий on 04.06.2015.
 */
public class SigninWorkerFragment extends Fragment {

    private final SigninModel signinModel;

    public SigninWorkerFragment(){
        signinModel=new SigninModel();
    }
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public SigninModel getSigninModel() {
        return signinModel;
    }
}
