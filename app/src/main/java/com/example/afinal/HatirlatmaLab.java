package com.example.afinal;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class HatirlatmaLab {
    private static HatirlatmaLab sHatirlatmaLab;
    private List<Hatirlatma> mHatirlatmalar;
    private int deleting=0;

    public static HatirlatmaLab get(Context context) {
        if (sHatirlatmaLab == null) {
            sHatirlatmaLab = new HatirlatmaLab(context);
        }
        return sHatirlatmaLab;
    }
    private HatirlatmaLab(Context context) {
        mHatirlatmalar= new ArrayList<>();

    }
    public void addHatirlatma(Hatirlatma h) {
        int size=getHatirlatmalar().size()+deleting;
        h.setID(size+1);
        mHatirlatmalar.add(h);
    }
    public void deleteHatirlatma(int id) {
        Hatirlatma hatirlatma = getHatirlatma(id);
        mHatirlatmalar.remove(hatirlatma);
        deleting+=1;
    }

    public List<Hatirlatma> getHatirlatmalar() {
        return mHatirlatmalar;
    }
    public Hatirlatma getHatirlatma(int id) {
        for (Hatirlatma hatirlatma : mHatirlatmalar) {
            if (hatirlatma.getID()==id) {
                return hatirlatma;
            }
        }
        return null;
    }

}
