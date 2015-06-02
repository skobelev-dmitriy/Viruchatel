package hm.viruchatel;

import android.os.Bundle;

import com.blunderer.materialdesignlibrary.activities.ViewPagerActivity;
import com.blunderer.materialdesignlibrary.handlers.ActionBarDefaultHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class AboutActivity extends ViewPagerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // /!\ Do not call setContentView(). /!\

    }

    @Override
    public ViewPagerHandler getViewPagerHandler() {
        // Return the Pages of the ViewPager.

        return new ViewPagerHandler(getApplicationContext())
                .addPage(null, new TutorialFragment1())
                .addPage(null, new TutorialFragment2());
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        // Return the position of the default selected page.

        return 0;
    }

    @Override
    public boolean showViewPagerIndicator() {
        // If return true, the ViewPager Indicator will be shown.

        return true;
    }

    @Override
    public boolean replaceActionBarTitleByViewPagerPageTitle() {
        // If return true, the ActionBar Title will be replaced by Pages Titles.

        return true;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);

    }

}
