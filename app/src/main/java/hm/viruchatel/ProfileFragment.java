package hm.viruchatel;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hm.viruchatel.Api.ApplicationInterface;
import hm.viruchatel.models.Profile;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{
    TextView numSaved, numSaves, name, city, rating, top, numFriends, numGroups,numReviews;
    ImageView photo;
    Button addInFriends, sendMessage, savesMe;
    LinearLayout friends, groups, reviews;
    int type=3;
    ApplicationInterface listener;




public ProfileFragment(){

}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener=(ApplicationInterface)activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement ApplicationInterface");

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_profile,container,false);


        numSaved=(TextView)view.findViewById(R.id.tv_num_saved);
        numSaves=(TextView)view.findViewById(R.id.tv_num_saves);
        name=(TextView)view.findViewById(R.id.tv_title);
        city=(TextView)view.findViewById(R.id.tv_city);
        rating =(TextView)view.findViewById(R.id.tv_rating);
        top=(TextView)view.findViewById(R.id.tv_top);
        numFriends=(TextView)view.findViewById(R.id.tv_num_friends);
        numGroups=(TextView)view.findViewById(R.id.tv_num_groups);
        numReviews=(TextView)view.findViewById(R.id.tv_num_reviews);
        photo=(ImageView)view.findViewById(R.id.im_photo);
        addInFriends=(Button)view.findViewById(R.id.but_add_in_friends);
        sendMessage=(Button)view.findViewById(R.id.but_send_message);
        savesMe=(Button)view.findViewById(R.id.but_save_me);

        friends=(LinearLayout)view.findViewById(R.id.ll_friend_num);
        groups=(LinearLayout)view.findViewById(R.id.ll_groups_num);
        reviews=(LinearLayout)view.findViewById(R.id.ll_reviews_num);

        addInFriends.setOnClickListener(this);
        sendMessage.setOnClickListener(this);
        savesMe.setOnClickListener(this);
        friends.setOnClickListener(this);
        groups.setOnClickListener(this);
        reviews.setOnClickListener(this);

        createView(new Profile());

        return view;
    }

    private void createView(Profile profile){
        numSaved.setText(profile.getNumSavedS());
        numSaves.setText(profile.getNumSavesS());
        name.setText(profile.getName());
        city.setText(profile.getCity());
        rating.setText(profile.getRating());
        top.setText(profile.getRating());
        numFriends.setText(profile.getNumFriends());
        numGroups.setText(profile.getNumGroups());
        numReviews.setText(profile.getNumReviews());
        type=profile.getType();



        if(type==0){
            addInFriends.setVisibility(View.GONE);
            sendMessage.setVisibility(View.GONE);
            savesMe.setVisibility(View.GONE);
        }


    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_add_in_friends:
                listener.addInFriends();

                break;
            case R.id.but_send_message:
                listener.sendMessage();

                break;
            case R.id.but_save_me:
                listener.savesMe();

                break;
            case R.id.ll_friend_num:
                listener.openFriends();

                break;
            case R.id.ll_groups_num:
                listener.openGroups();

                break;
            case R.id.ll_reviews_num:
                listener.openReviews();

                break;
        }
    }
}
