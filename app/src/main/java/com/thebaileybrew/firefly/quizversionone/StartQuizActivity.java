package com.thebaileybrew.firefly.quizversionone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.Vector;

/**
 * Created by grumnb on 2/8/2018.
 */

public class StartQuizActivity extends FragmentActivity {

    private android.support.v4.view.PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_quiz_start);

        this.initializePaging();
    }


    private void initializePaging() {
        List<Fragment> fragments = new Vector<>();
        fragments.add(Fragment.instantiate(this, FragmentOne.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentTwo.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentThree.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentFour.class.getName()));
        this.mPagerAdapter  = new com.thebaileybrew.firefly.quizversionone.PagerAdapter(super.getSupportFragmentManager(), fragments);
        //
        ViewPager pager = (ViewPager)super.findViewById(R.id.viewPager);
        pager.setAdapter(this.mPagerAdapter);
    }
}

