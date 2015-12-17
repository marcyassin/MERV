package cayenne.team.merv;

/**
 * Created by Marc on 11/15/15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class PagerAdapterTasks extends FragmentStatePagerAdapter{
    int mNumOfTabs;

    public PagerAdapterTasks(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SummaryFragment tab1 = new SummaryFragment();
                return tab1;
            case 1:
                TeamFragment tab2 = new TeamFragment();
                return tab2;
            case 2:
                TrainingFragment tab3 = new TrainingFragment();
                return tab3;
            case 3:
                TabFragment4 tab4 = new TabFragment4();
                return tab4;
            case 4:
                TabFragment5 tab5 = new TabFragment5();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
