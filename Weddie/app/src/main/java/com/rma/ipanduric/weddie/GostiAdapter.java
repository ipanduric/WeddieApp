package com.rma.ipanduric.weddie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ipand on 1.8.2017..
 */

public class GostiAdapter extends BaseAdapter {

    private ArrayList<GostItem> mGost;

    public GostiAdapter(ArrayList<GostItem> gosti)
    {
        this.mGost = gosti;
    }



    @Override
    public int getCount() {
        return this.mGost.size();
    }

    @Override
    public Object getItem(int position) {
        return mGost.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder gostViewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.items_gosti, parent, false);
            gostViewHolder = new ViewHolder(convertView);
            convertView.setTag(gostViewHolder);
        } else {
            gostViewHolder = (ViewHolder) convertView.getTag();
        }

        GostItem gost = this.mGost.get(position);
        gostViewHolder.tvPrezime.setText(gost.getgPrezime());
        gostViewHolder.tvIme.setText(gost.getgIme());
        gostViewHolder.tvKategorija.setText(gost.getgKategorija());
        gostViewHolder.tvBroj.setText(gost.getgBroj());
        return convertView;
    }

    public void dodajNovogGosta (GostItem gost) {
        this.mGost.add(gost);
        this.notifyDataSetChanged();
    }

    public void deleteAt(int position) {
        this.mGost.remove(position);
        this.notifyDataSetChanged();
    }


    private class ViewHolder {

        public TextView tvPrezime, tvIme, tvKategorija, tvBroj;

        public ViewHolder(View gostView) {
            tvKategorija = (TextView) gostView.findViewById(R.id.tvaKategorija);
            tvPrezime = (TextView) gostView.findViewById(R.id.tvaPrezime);
            tvIme = (TextView) gostView.findViewById(R.id.tvaIme);
            tvBroj = (TextView) gostView.findViewById(R.id.tvaBrojGosti);
        }

    }
}
