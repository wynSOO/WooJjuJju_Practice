package app.com.woo_page.helper;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class StringPagerAdapter extends FragmentStatePagerAdapter{

    List<String> pageList;

    public StringPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public StringPagerAdapter(FragmentManager fm, List<String> pageList) {
        super(fm);
        this.pageList = pageList;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageList.get(position);
    }

    @Override
    public Fragment getItem(int i) {
        return new StringFragment();
    }

    @Override
    public int getCount() {
        return pageList.size();
    }
}
