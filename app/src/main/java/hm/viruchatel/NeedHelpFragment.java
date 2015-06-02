package hm.viruchatel;

import com.blunderer.materialdesignlibrary.fragments.ViewPagerWithTabsFragment;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class NeedHelpFragment extends ViewPagerWithTabsFragment {
    @Override
    protected boolean expandTabs() {
        return false;
    }

    @Override
    public ViewPagerHandler getViewPagerHandler() {
        return null;
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }
}
