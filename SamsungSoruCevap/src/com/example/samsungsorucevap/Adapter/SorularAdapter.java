package com.example.samsungsorucevap.Adapter;

import com.example.samsungsorucevap.HomeActivity;
import com.example.samsungsorucevap.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.samsungsorucevap.Entity.Soru;
import com.example.samsungsorucevap.Fragment.fragmentSoruCevaplari;
import com.example.samsungsorucevap.Fragment.fragmentSorular;

public class SorularAdapter extends BaseAdapter {
	ArrayList<Soru> sorular;
	Context context;

	public SorularAdapter(Context context, ArrayList<Soru> sorular) {
		this.context = context;
		this.sorular = sorular;
	}

	@Override
	public int getCount() {
		return sorular.size();
	}

	@Override
	public Soru getItem(int position) {
		return sorular.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.soru_list_view_item, null, false);

		TextView tvPuan = (TextView) view.findViewById(R.id.txtPuan);
		TextView tvCevapSayi = (TextView) view
				.findViewById(R.id.txtCevapSayisi);
		TextView tvSoru = (TextView) view.findViewById(R.id.txtSoru);
		TextView tvIsim = (TextView) view.findViewById(R.id.txtKisi);

		tvPuan.setText(Integer.toString(getItem(position).getPuan()));
		tvCevapSayi.setText(Integer.toString(getItem(position).getCevapSayi()));
		tvSoru.setText(getItem(position).getBaslik());
		tvIsim.setText(getItem(position).getUyeAd());
	
		return view;
	}
}