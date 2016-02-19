package cayenne.team.merv.Main.Tabs.Tasks;

/**
 * Created by Marc on 11/15/15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cayenne.team.merv.Main.Tabs.Training.TabTraining;
import cayenne.team.merv.Main.Tabs.Social.TabSocial;
import cayenne.team.merv.Main.Tabs.Tasks.Tabs.SummaryFragment;
import cayenne.team.merv.Main.Tabs.Tasks.Tabs.TeamFragment;
import cayenne.team.merv.Main.Tabs.Tasks.Tabs.TrainingFragment;


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
                return new SummaryFragment();
            case 1:
                return new TeamFragment();
            case 2:
                return new TrainingFragment();
            case 3:
                return new TabTraining();
            case 4:
                return new TabSocial();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
