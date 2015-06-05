package hm.viruchatel;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class ProfileFragment extends Fragment {

public ProfileFragment(){

}
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_profile,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
