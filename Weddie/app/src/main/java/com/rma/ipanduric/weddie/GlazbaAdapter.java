package com.rma.ipanduric.weddie;

import android.content.Context;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Tomislav on 18.07.2017..
 */
public class GlazbaAdapter extends BaseAdapter {

    private ArrayList<GlazbaItem> mGlazba;
    private ArrayList<GlazbaItem> arraylist = null;
    Context context;

    public GlazbaAdapter(Context context, ArrayList<GlazbaItem> glazba) {
        this.context = context;
        this.mGlazba = glazba;
        this.arraylist = new ArrayList<GlazbaItem>();
        this.arraylist.addAll(mGlazba);
    }

    @Override
    public int getCount() {
        return this.mGlazba.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mGlazba.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mGlazba.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder glazbaVH;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.items_glazba, parent, false);
            glazbaVH = new ViewHolder(convertView);
            convertView.setTag(glazbaVH);
        } else {
            glazbaVH = (ViewHolder) convertView.getTag();
        }

        GlazbaItem glazba = this.mGlazba.get(position);
        glazbaVH.tvGlazbaIme.setText(glazba.getgIme());
        return convertView;
    }

    private class ViewHolder {

        TextView tvGlazbaIme;

        public ViewHolder(View glazbaView) {
            tvGlazbaIme = (TextView) glazbaView.findViewById(R.id.tvGlazbaIme);
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mGlazba.clear();
        if (charText.length() == 0) {
            mGlazba.addAll(arraylist);
        }
        else
        {
            for (GlazbaItem gl : arraylist) {
                if (gl.getgIme().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mGlazba.add(gl);
                }
            }
        }
        notifyDataSetChanged();
    }

}
