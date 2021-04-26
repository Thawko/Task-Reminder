package com.example.afinal;

import androidx.fragment.app.Fragment;

public class HatirlatmaListActivity  extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new HatirlatmaListFragment();
    }
}
