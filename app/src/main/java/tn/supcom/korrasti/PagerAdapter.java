package tn.supcom.korrasti;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Eya on 18/11/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Contes();
            case 1:
                return new Chansons();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {

        return mNumOfTabs;
     }
}
