package com.mobadarti.ptuk.safeinternet.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
    private Fragment []frags;
    private String []titles;
    public FragmentAdapter(FragmentManager fm,Fragment []frags,String []titles) {
        super(fm);
        this.frags=frags;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return frags[position];
    }

    @Override
    public int getCount() {
        return frags.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
