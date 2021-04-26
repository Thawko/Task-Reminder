package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HatirlatmaListFragment  extends Fragment {
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private RecyclerView mHatirlatmaRecyclerView;
    private HatirlatmaAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hatirlatma_list, container, false);
        mHatirlatmaRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mHatirlatmaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }
        updateUI();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_hatirlatma_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_hatirlatma:
                Hatirlatma hatirlatma = new Hatirlatma();
                HatirlatmaLab.get(getActivity()).addHatirlatma(hatirlatma);
                Intent intent = HatirlatmaPagerActivity
                        .newIntent(getActivity(), hatirlatma.getID());
                startActivity(intent);
                return true;
            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void updateSubtitle() {
        HatirlatmaLab hatirlatmaLab = HatirlatmaLab.get(getActivity());
        int crimeCount = hatirlatmaLab.getHatirlatmalar().size();
        String subtitle = getString(R.string.subtitle_format, crimeCount);
        if (!mSubtitleVisible) {
            subtitle = null;
        }
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }
    private void updateUI() {
        HatirlatmaLab hatirlatmaLab = HatirlatmaLab.get(getActivity());
        List<Hatirlatma> hatirlatmalar = hatirlatmaLab.getHatirlatmalar();
        if (mAdapter == null){
            mAdapter = new HatirlatmaAdapter(hatirlatmalar);
            mHatirlatmaRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
        updateSubtitle();

    }

    private class HatirlatmaHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mIDTextView;
        private TextView mTitleTextView;
        private TextView mDetailsTextView;
        private Hatirlatma mHatirlatma;
        private ImageView mSolvedImageView;

        public HatirlatmaHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_hatirlatma, parent, false));
            itemView.setOnClickListener(this);
            mIDTextView= (TextView) itemView.findViewById(R.id.hatirlatma_id);
            mTitleTextView = (TextView) itemView.findViewById(R.id.hatirlatma_baslik);
            mDetailsTextView = (TextView) itemView.findViewById(R.id.hatirlatma_detay);
            mSolvedImageView= (ImageView) itemView.findViewById(R.id.tamamlanma);
        }
        public void bind(Hatirlatma hatirlatma) {
            mHatirlatma = hatirlatma;
            mIDTextView.setText(String.valueOf(mHatirlatma.getID()));
            mTitleTextView.setText(mHatirlatma.getBaslik());
            mDetailsTextView.setText(mHatirlatma.getDetay());
            mSolvedImageView.setVisibility(mHatirlatma.isTamamlandi() ? View.VISIBLE : View.GONE);
        }
        @Override
        public void onClick(View view) {
            Intent intent = HatirlatmaPagerActivity.newIntent(getActivity(), mHatirlatma.getID());
            startActivity(intent);
        }

    }
    private class HatirlatmaAdapter extends RecyclerView.Adapter<HatirlatmaHolder> {
        private List<Hatirlatma> mHatirlatmalar;
        public HatirlatmaAdapter(List<Hatirlatma> hatirlatmalar) {
            mHatirlatmalar = hatirlatmalar;
        }

        @NonNull
        @Override
        public HatirlatmaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new HatirlatmaHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(@NonNull HatirlatmaHolder holder, int position) {
            Hatirlatma hatirlatma = mHatirlatmalar.get(position);
            holder.bind(hatirlatma);
        }
        @Override
        public int getItemCount() {
            return mHatirlatmalar.size();
        }
    }
}