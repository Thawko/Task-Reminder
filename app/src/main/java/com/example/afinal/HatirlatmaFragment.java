package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class HatirlatmaFragment extends Fragment {
    private static final String ARG_HATIRLATMA_ID = "hatirlatma_id";

    private Hatirlatma mHatirlatma;
    private EditText mTitleField;
    private EditText mDetailsField;
    private CheckBox mTamamlandiCheckBox;
    private Button buton;
    public static HatirlatmaFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_HATIRLATMA_ID, id);
        HatirlatmaFragment fragment = new HatirlatmaFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = (int) getArguments().getSerializable(ARG_HATIRLATMA_ID);
        mHatirlatma = HatirlatmaLab.get(getActivity()).getHatirlatma(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hatirlatma, container, false);

        mTitleField = (EditText) v.findViewById(R.id.hatirlatma_title);
        mTitleField.setText(mHatirlatma.getBaslik());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mHatirlatma.setBaslik(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mDetailsField = (EditText) v.findViewById(R.id.hatirlatma_details);
        mDetailsField.setText(mHatirlatma.getDetay());
        mDetailsField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mHatirlatma.setDetay(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mTamamlandiCheckBox = (CheckBox)v.findViewById(R.id.hatirlatma_tamamlandi);
        mTamamlandiCheckBox.setChecked(mHatirlatma.isTamamlandi());
        mTamamlandiCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mHatirlatma.setTamamlandi(isChecked);
            }
        });
        buton=(Button) v.findViewById(R.id.button);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HatirlatmaLab hatirlatmaLab = HatirlatmaLab.get(getActivity());
                hatirlatmaLab.deleteHatirlatma(mHatirlatma.getID());
                Intent intent = new Intent(getActivity(), HatirlatmaListActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}


