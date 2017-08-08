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

    private List<GostItem> mGost;
    Context context;

    public GostiAdapter(List<GostItem> gosti, Context context)
    {
        this.mGost = gosti;
        this.context = context;
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
        ViewHolder taskViewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.items_gosti, parent, false);
            taskViewHolder = new ViewHolder(convertView);
            convertView.setTag(taskViewHolder);
        } else {
            taskViewHolder = (ViewHolder) convertView.getTag();
        }

        GostItem gost = this.mGost.get(position);
        taskViewHolder.tvPrezime.setText(gost.getgPrezime());
        taskViewHolder.tvIme.setText(gost.getgIme());
        taskViewHolder.tvKategorija.setText(gost.getgKategorija());
        taskViewHolder.tvBroj.setText(gost.getgBroj());
        return convertView;
    }

    public void dodajNovogGosta (GostItem gost) {
        mGost.add(gost);
        notifyDataSetChanged();
    }

    public void deleteAt(int position) {
        mGost.remove(position);
        notifyDataSetChanged();
    }


    private class ViewHolder {

        TextView tvPrezime, tvIme, tvKategorija, tvBroj;

        public ViewHolder(View taskView) {
            tvKategorija = (TextView) taskView.findViewById(R.id.tvKategorija);
            tvPrezime = (TextView) taskView.findViewById(R.id.tvPrezime);
            tvIme = (TextView) taskView.findViewById(R.id.tvIme);
            tvBroj = (TextView) taskView.findViewById(R.id.tvBroj);
        }

    }
}
