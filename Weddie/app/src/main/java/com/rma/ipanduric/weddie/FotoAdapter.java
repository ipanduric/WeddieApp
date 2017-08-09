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

public class FotoAdapter extends BaseAdapter{

    private ArrayList<FotoItem> mFoto;
    private ArrayList<FotoItem> arraylist = null;
    private Context context;

    public FotoAdapter(Context context, ArrayList<FotoItem> foto) {
        this.context = context;
        this.mFoto = foto;
        this.arraylist = new ArrayList<FotoItem>();
        this.arraylist.addAll(mFoto);
    }
    @Override
    public int getCount() {
        return this.mFoto.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mFoto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mFoto.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;
        ViewHolder fotoVH;
        if (v == null) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        v = inflater.inflate(R.layout.items_foto, parent, false);
        fotoVH = new ViewHolder(v);
        v.setTag(fotoVH);
    } else {
        fotoVH = (ViewHolder) v.getTag();
    }

    FotoItem foto = this.mFoto.get(position);
        fotoVH.tvFotoIme.setText(foto.getfIme());
        if (position % 2 == 1) {
            v.setBackgroundColor(Color.WHITE);
        } else {
            v.setBackgroundColor(Color.parseColor("#F5ECCE"));
        }
        return v;
}
private class ViewHolder {

    TextView tvFotoIme;

    public ViewHolder(View fotoView) {
        tvFotoIme = (TextView) fotoView.findViewById(R.id.tvFotoIme);
    }
}

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mFoto.clear();
        if (charText.length() == 0) {
            mFoto.addAll(arraylist);
        }
        else
        {
            for (FotoItem ft : arraylist) {
                if (ft.getfIme().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mFoto.add(ft);
                }
            }
        }
        notifyDataSetChanged();
    }


}
