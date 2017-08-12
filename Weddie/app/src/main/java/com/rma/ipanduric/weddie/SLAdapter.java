package com.rma.ipanduric.weddie;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.checked;

/**
 * Created by ipand on 11.8.2017..
 */

public class SLAdapter extends BaseAdapter {

    private ArrayList<SLItem> slItem;
    private ArrayList<SLItem> arraylist = null;
    private Context context;

    public SLAdapter(Context context, ArrayList<SLItem> sl) {
        this.context = context;
        this.slItem = sl;
        this.arraylist = new ArrayList<SLItem>();
        this.arraylist.addAll(slItem);
    }

    @Override
    public int getCount() {
        return this.slItem.size();
    }

    @Override
    public Object getItem(int position) {
        return this.slItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        SLAdapter.ViewHolder slVH;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            v = inflater.inflate(R.layout.items_stavke, parent, false);
            slVH = new SLAdapter.ViewHolder(v);
            v.setTag(slVH);
        } else {
            slVH = (SLAdapter.ViewHolder) v.getTag();
        }

        SLItem sl = this.slItem.get(position);

        slVH.tvStavkeNaziv.setText(sl.getSlNaziv());
        if (position % 2 == 1) {
            v.setBackgroundColor(Color.WHITE);
        } else {
            v.setBackgroundColor(Color.parseColor("#F5ECCE"));
        }
        return v;
    }


    public static class ViewHolder  {

        public static int status = 0;
        TextView tvStavkeNaziv;

        public ViewHolder(View slView) {
            tvStavkeNaziv = (TextView) slView.findViewById(R.id.tvStavkeNaziv);

        }
    }


    public void deleteAt(int position) {
        this.slItem.remove(position);
        this.notifyDataSetChanged();
    }


}
