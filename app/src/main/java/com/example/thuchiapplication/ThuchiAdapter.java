package com.example.thuchiapplication;

import static com.example.thuchiapplication.R.color.colorAccent;
import static com.example.thuchiapplication.R.color.light_blue_600;
import static com.example.thuchiapplication.R.color.purple_200;
import static com.example.thuchiapplication.R.color.purple_500;
import static com.example.thuchiapplication.R.color.tran_parent;
import static com.example.thuchiapplication.R.color.yellow;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ThuchiAdapter extends BaseAdapter {
    private ArrayList<ThuChi> ListThuChi;
    private Activity context;
    private LayoutInflater inflater;

    public ThuchiAdapter(ArrayList<ThuChi> data, Activity context) {
        ListThuChi = data;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return ListThuChi.size();
    }

    @Override
    public Object getItem(int position) {
        return ListThuChi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.item_thuchi, null);
            holder = new ViewHolder();
            holder.thuchi = view.findViewById(R.id.thuchi);
            holder.khoantien = view.findViewById(R.id.khoantien);
            holder.tien = view.findViewById(R.id.tien);
            holder.ngaythang=view.findViewById(R.id.ngaythang);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ThuChi tc = ListThuChi.get(i);
        holder.khoantien.setText(tc.getTaikhoan());
        holder.tien.setText("" + tc.getSotien());
        holder.ngaythang.setText("" + tc.getNgayThang());
        if(tc.isThuChi()==1)
        {
            holder.thuchi.setText("Thu");
            holder.thuchi.setBackgroundColor(tran_parent);
            holder.tien.setBackgroundColor(colorAccent);
            holder.khoantien.setBackgroundColor(colorAccent);
        }
        else
            holder.thuchi.setText("Chi");



        return view;
    }



    static class ViewHolder {
        TextView thuchi;
        TextView khoantien;
        TextView tien;
        TextView ngaythang;
    }
}
