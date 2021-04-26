package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class HatirlatmaPagerActivity extends AppCompatActivity {
    private static final String EXTRA_HATIRLATMA_ID =
            "com.bignerdranch.android.final.hatirlatma_id";

    private ViewPager mViewPager;
    private List<Hatirlatma> mHatirlatmalar;
    public static Intent newIntent(Context packageContext, int id) {
        Intent intent = new Intent(packageContext, HatirlatmaPagerActivity.class);
        intent.putExtra(EXTRA_HATIRLATMA_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hatirlatma_pager);
        int id = (int) getIntent()
                .getSerializableExtra(EXTRA_HATIRLATMA_ID);

        mViewPager = (ViewPager) findViewById(R.id.hatirlatma_view_pager);
        mHatirlatmalar = HatirlatmaLab.get(this).getHatirlatmalar();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Hatirlatma hatirlatma = mHatirlatmalar.get(position);
                return HatirlatmaFragment.newInstance(hatirlatma.getID());
            }
            @Override
            public int getCount() {
                return mHatirlatmalar.size();
            }
        });

        for (int i = 0; i < mHatirlatmalar.size(); i++) {
            if (mHatirlatmalar.get(i).getID()==id) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }


    }

}
