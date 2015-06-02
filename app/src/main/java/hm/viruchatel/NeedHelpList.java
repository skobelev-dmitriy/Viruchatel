package hm.viruchatel;

import com.blunderer.materialdesignlibrary.fragments.ScrollViewFragment;

/**
 * Created by Дмитрий on 02.06.2015.
 */
public class NeedHelpList extends ScrollViewFragment {
    @Override
    public int getContentView() {
        return 0;
    }

    @Override
    public boolean pullToRefreshEnabled() {
        return false;
    }

    @Override
    public int[] getPullToRefreshColorResources() {
        return new int[0];
    }

    @Override
    public void onRefresh() {

    }
}
