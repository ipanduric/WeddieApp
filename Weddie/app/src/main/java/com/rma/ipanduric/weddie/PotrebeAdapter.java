package com.rma.ipanduric.weddie;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ipand on 8.8.2017..
 */

public class PotrebeAdapter extends BaseAdapter {

    private ArrayList<PotrebeItem> mPotrebe;
    private ArrayList<PotrebeItem> arraylist = null;
    Context context;

    public PotrebeAdapter(Context context, ArrayList<PotrebeItem> potrebe) {
        this.context = context;
        this.mPotrebe = potrebe;
        this.arraylist = new ArrayList<PotrebeItem>();
        this.arraylist.addAll(mPotrebe);
    }

    @Override
    public int getCount() {
        return this.mPotrebe.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mPotrebe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mPotrebe.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        PotrebeAdapter.ViewHolder potrebeVH;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            v = inflater.inflate(R.layout.items_potrebe, parent, false);
            potrebeVH = new PotrebeAdapter.ViewHolder(v);
            v.setTag(potrebeVH);
        } else {
            potrebeVH = (PotrebeAdapter.ViewHolder) v.getTag();
        }

        PotrebeItem potrebe = this.mPotrebe.get(position);
        potrebeVH.tvPotrebeIme.setText(potrebe.getpNaziv());
        potrebeVH.tvPotrebeBroj.setText(String.valueOf(potrebe.getpRezultat()));
        potrebeVH.tvPotrebeJedinica.setText(potrebe.getpJedinica());
        if (position % 2 == 1) {
            v.setBackgroundColor(Color.WHITE);
        } else {
            v.setBackgroundColor(Color.parseColor("#F5ECCE"));
        }
        return v;
    }

    private class ViewHolder {

        TextView tvPotrebeIme, tvPotrebeBroj, tvPotrebeJedinica;

        public ViewHolder(View potrebeView) {
            tvPotrebeIme = (TextView) potrebeView.findViewById(R.id.tvPotrebeIme);
            tvPotrebeBroj = (TextView) potrebeView.findViewById(R.id.tvPotrebeBroj);
            tvPotrebeJedinica = (TextView) potrebeView.findViewById(R.id.tvPotrebeJedinica);
        }
    }


}
