package com.mobadarti.ptuk.safeinternet.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mobadarti.ptuk.safeinternet.Adapters.FragmentAdapter;
import com.mobadarti.ptuk.safeinternet.Fragments.BookFragment;
import com.mobadarti.ptuk.safeinternet.Fragments.HomeFragment;
import com.mobadarti.ptuk.safeinternet.Fragments.InfoFragment;
import com.mobadarti.ptuk.safeinternet.Fragments.VideosFragment;
import com.mobadarti.ptuk.safeinternet.Fragments.WebFragment;
import com.mobadarti.ptuk.safeinternet.R;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment=new HomeFragment();
    private VideosFragment videosFragment=new VideosFragment();
    private WebFragment webFragment=new WebFragment();
    private InfoFragment infoFragment=new InfoFragment();
    private BookFragment bookFragment=new BookFragment();
    private TextView mTextMessage;
    private ViewPager viewPager;
    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.info:
                    viewPager.setCurrentItem(3);
                    return true;
                case R.id.books:
                    viewPager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mTextMessage =findViewById(R.id.message);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager=findViewById(R.id.viewpager);
        FragmentAdapter adapter=new FragmentAdapter(getSupportFragmentManager(),new Fragment[]{homeFragment,videosFragment,webFragment,infoFragment,bookFragment},getResources().getStringArray(R.array.titles));
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add){
            startActivity(new Intent(MainActivity.this,AddActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
