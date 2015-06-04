package hm.viruchatel;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class TutorialActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_tutorial);
        ViewPager viewPager=(ViewPager)findViewById(R.id.vpPager);
        MyPagerAdapter pagerAdapter=new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        //PagerTabStrip tabStrip=(PagerTabStrip)findViewById(R.id.pager_pagination);
       // tabStrip.setBackgroundResource(R.drawable.ic_pagination_actine);
        SharedPreferences sp=getSharedPreferences("AppPref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();

        editor.putBoolean("isFirstStart",false);
        editor.commit();



    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return TutorialFragment1.newInstance(0) ;
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return TutorialFragment2.newInstance(1);

                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }
    @Override
    public void onBackPressed() {

       super.onBackPressed();
    }

}
