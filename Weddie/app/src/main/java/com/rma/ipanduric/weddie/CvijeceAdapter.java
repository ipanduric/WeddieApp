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
 * Created by ipand on 28.7.2017..
 */

class CvijeceAdapter extends BaseAdapter {

    private ArrayList<CvijeceItem> mCvijece;
    private ArrayList<CvijeceItem> arraylist = null;
    private Context context;

    public CvijeceAdapter(Context context, ArrayList<CvijeceItem> cvijece) {
        this.context = context;
        this.mCvijece = cvijece;
        this.arraylist = new ArrayList<CvijeceItem>();
        this.arraylist.addAll(mCvijece);
    }

    @Override
    public int getCount() {
        return this.mCvijece.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mCvijece.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        ViewHolder cvijeceVH;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            v = inflater.inflate(R.layout.item_cvijece, parent, false);
            cvijeceVH = new ViewHolder(v);
            v.setTag(cvijeceVH);
        } else {
            cvijeceVH = (CvijeceAdapter.ViewHolder) v.getTag();
        }

        CvijeceItem cvijece = this.mCvijece.get(position);
        cvijeceVH.tvCvijeceIme.setText(cvijece.getcIme());

        if (position % 2 == 1) {
            v.setBackgroundColor(Color.WHITE);
        } else {
            v.setBackgroundColor(Color.parseColor("#F5ECCE"));
        }
        return v;
    }

    private class ViewHolder {

        TextView tvCvijeceIme;

        public ViewHolder(View fotoView) {
            tvCvijeceIme = (TextView) fotoView.findViewById(R.id.tvCvijeceIme);
        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mCvijece.clear();
        if (charText.length() == 0) {
            mCvijece.addAll(arraylist);
        }
        else
        {
            for (CvijeceItem cv : arraylist) {
                if (cv.getcIme().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mCvijece.add(cv);
                }
            }
        }
        notifyDataSetChanged();
    }

}
