package com.example.mediaproject.Home;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.mediaproject.Home.Fragment.HomeFragment;
import com.example.mediaproject.Home.Fragment.ReceiptFragment;
import com.example.mediaproject.Home.adapter.ViewPagerAdapter;
import com.example.mediaproject.R;
import com.example.mediaproject.add.AddActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";

    private FirebaseAuth firebaseAuth;

//    Button memberWithdrawal,logOut;
//    ProgressDialog progressDialog;
    private ViewPager viewPager;
    BottomNavigationView bottomNavigationView;

    ReceiptFragment receiptFragment;
    HomeFragment homeFragment;
    MenuItem prevMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        FloatingActionButton fab = findViewById(R.id.fab);

        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bv_bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.ic_house:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.ic_search:
                                viewPager.setCurrentItem(1);
                                break;
                        }
                        return false;
                    }
                });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(i);

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       /*  //Disable ViewPager Swipe

       viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        */

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFragment = new HomeFragment();
        receiptFragment = new ReceiptFragment();
        adapter.addFragment(homeFragment);
        adapter.addFragment(receiptFragment);
        viewPager.setAdapter(adapter);
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.memberWithdrawal:
//                deleteUser();
//                break;
//            case R.id.logOut:
//                firebaseAuth.signOut();
//                finish();
//                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//                break;
        }


    }
}
