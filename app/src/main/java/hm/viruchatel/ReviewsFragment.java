package hm.viruchatel;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import hm.viruchatel.Api.ApplicationInterface;
import hm.viruchatel.models.Message;
import hm.viruchatel.models.Review;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class ReviewsFragment extends Fragment implements View.OnClickListener {
    public static final String TAG ="myLogs";
    ApplicationInterface listener;
    ListView listViewAll,listViewPos,listViewNeg,listViewNet;
    CardView emptyList;
    private ArrayList<Review> reviewListAll, reviewListNeg,reviewListPos, reviewListNet;
    private Adapter adapterAll,adapterNeg,adapterPos,adapterNet;
    TabHost tabs;
    TabHost.TabSpec tabAll, tabNeg, tabPos, tabNet;
    Button newReview;


    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        try {
            listener=(ApplicationInterface)activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement ApplicationInterface");

        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //return super.onCreateView(inflater, container, savedInstanceState);
        //   api=new Api();
        View v=inflater.inflate(R.layout.fragment_reviews, container, false);

        emptyList=(CardView)v.findViewById(R.id.empty_list);
        tabs=(TabHost)v.findViewById(R.id.tabs_reviews);
        listViewAll=(ListView)v.findViewById(R.id.listViewReviewAll);
        listViewPos=(ListView)v.findViewById(R.id.listViewReview_pos);
        listViewNeg=(ListView)v.findViewById(R.id.listViewReview_neg);
        listViewNet=(ListView)v.findViewById(R.id.listViewReview_net);
        reviewListAll=new ArrayList<Review>();
        reviewListPos=new ArrayList<Review>();
        reviewListNeg=new ArrayList<Review>();
        reviewListNet=new ArrayList<Review>();
        adapterAll=new Adapter(reviewListAll,listViewAll);
        adapterNeg=new Adapter(reviewListNeg,listViewNeg);
       adapterPos=new Adapter(reviewListPos,listViewPos);
        adapterNet=new Adapter(reviewListNet,listViewNet);

        listViewAll.setAdapter(adapterAll);
        listViewNet.setAdapter(adapterNet);
        listViewNeg.setAdapter(adapterNeg);
        listViewPos.setAdapter(adapterPos);

        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.review_statistic, listViewAll, false);
        newReview=(Button)header.findViewById(R.id.but_new_review);
        newReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.openNewReview();
            }
        });
        listViewAll.addHeaderView(header, null, false);



        tabs.setup();

        tabAll=tabs.newTabSpec("all");
        tabAll.setContent(R.id.listViewReviewAll);
        tabAll.setIndicator("ВСЕ");
        tabs.addTab(tabAll);

        tabPos=tabs.newTabSpec("pos");
        tabPos.setContent(R.id.listViewReview_pos);
        tabPos.setIndicator("ПОЛОЖИТЕЛЬНЫЕ");
        tabs.addTab(tabPos);

        tabNet=tabs.newTabSpec("net");
        tabNet.setContent(R.id.listViewReview_net);
        tabNet.setIndicator("НЕЙТРАЛЬНЫЕ");
        tabs.addTab(tabNet);

        tabNeg=tabs.newTabSpec("neg");
        tabNeg.setContent(R.id.listViewReview_neg);
        tabNeg.setIndicator("ОТРИЦАТЕЛЬНЫЕ");
        tabs.addTab(tabNeg);
        loadMessages();


        return v;
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();

       /* if (asyncLogin!=null) {
            Log.d(TAG, "asyncLogin cancel " + asyncLogin.cancel(false));
        }*/
    }
    private void loadMessages(){
        for (int i=0; i<20; i++){
            Review review=new Review();

            reviewListAll.add(review);
            switch (review.getState()){
                case 1:
                    reviewListNeg.add(review);
                    adapterNeg.notifyDataSetChanged();
                    break;
                case 2:
                    reviewListPos.add(review);
                    adapterPos.notifyDataSetChanged();
                    break;
                case 3:
                    reviewListNet.add(review);
                    adapterNet.notifyDataSetChanged();
                    break;
            }

        }
    }
    private class Adapter extends BaseAdapter {
        ArrayList<Review> arrayList;
        ListView listView;
public Adapter(ArrayList list, ListView listView)     {
    arrayList=list;
    this.listView=listView;
}

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (getCount()!=0) {

                emptyList.setVisibility(View.GONE);
            //listView.setVisibility(View.VISIBLE);
                Review msg = (Review) getItem(position);
                LayoutInflater inflater = (LayoutInflater) getActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_review, null);

                TextView review = (TextView) convertView.findViewById(R.id.tv_review);


                TextView name = (TextView) convertView.findViewById(R.id.tv_title);
                ImageView photo = (ImageView) convertView.findViewById(R.id.im_photo);
                ImageView color=(ImageView)convertView.findViewById(R.id.im__review_color);



                review.setText(msg.getMessage());

                name.setText(msg.getName());
                color.setBackgroundColor(R.color.color_yellow);

            }else{
              listView.setVisibility(View.GONE);
                emptyList=(CardView)convertView.findViewById(R.id.empty_list);
                emptyList.setVisibility(View.VISIBLE);

            }

            return convertView;
        }
    }
}
