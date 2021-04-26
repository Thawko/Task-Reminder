package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class HatirlatmaActivity extends SingleFragmentActivity {
    private static final String EXTRA_HATIRLATMA_ID =
            "com.bignerdranch.android.final.hatirlatma_id";
    public static Intent newIntent(Context packageContext, int id) {
        Intent intent = new Intent(packageContext, HatirlatmaActivity.class);
        intent.putExtra(EXTRA_HATIRLATMA_ID, id);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        int id = (int) getIntent()
                .getSerializableExtra(EXTRA_HATIRLATMA_ID);
        return HatirlatmaFragment.newInstance(id);

    }

}