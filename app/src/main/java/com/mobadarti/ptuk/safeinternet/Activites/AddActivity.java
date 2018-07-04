package com.mobadarti.ptuk.safeinternet.Activites;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mobadarti.ptuk.safeinternet.Adapters.FragmentAdapter;
import com.mobadarti.ptuk.safeinternet.Fragments.AddBookFragment;
import com.mobadarti.ptuk.safeinternet.Fragments.AddInfoFragment;
import com.mobadarti.ptuk.safeinternet.Fragments.AddVideoFragment;
import com.mobadarti.ptuk.safeinternet.Fragments.AddWebsiteFragment;
import com.mobadarti.ptuk.safeinternet.R;

public class AddActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tabLayout= findViewById(R.id.tablayout);
        viewPager= findViewById(R.id.viewpager);
        FragmentAdapter adapter=new FragmentAdapter(getSupportFragmentManager(),new Fragment[]{new AddVideoFragment(),new AddWebsiteFragment(), new AddInfoFragment(),new AddBookFragment()},
                getResources().getStringArray(R.array.add_tab_items));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
