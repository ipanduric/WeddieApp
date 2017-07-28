package com.rma.ipanduric.weddie;

import android.content.Context;
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
        ViewHolder cvijeceVH;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_cvijece, parent, false);
            cvijeceVH = new ViewHolder(convertView);
            convertView.setTag(cvijeceVH);
        } else {
            cvijeceVH = (CvijeceAdapter.ViewHolder) convertView.getTag();
        }

        CvijeceItem cvijece = this.mCvijece.get(position);
        cvijeceVH.tvCvijeceIme.setText(cvijece.getcIme());
        return convertView;
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
