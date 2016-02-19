package cayenne.team.merv.Main;

/**
 * Created by Marc on 11/15/15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cayenne.team.merv.Main.Tabs.Tasks.TabTasks;
import cayenne.team.merv.Main.Tabs.JobList.TabJobs;
import cayenne.team.merv.Main.Tabs.News.TabNews;
import cayenne.team.merv.Main.Tabs.Training.TabTraining;
import cayenne.team.merv.Main.Tabs.Social.TabSocial;


public class PagerAdapter extends FragmentStatePagerAdapter{
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new TabTasks();
            case 1:
                return new TabJobs();
            case 2:
                return new TabNews();
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
