package com.example.samsungsorucevap.Adapter;

import com.example.samsungsorucevap.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.samsungsorucevap.Entity.Cevap;

public class CevaplarAdapter extends BaseAdapter {

	ArrayList<Cevap> cevaplar;
	Context context;

	public CevaplarAdapter(Context context, ArrayList<Cevap> cevaplar) {
		this.context = context;
		this.cevaplar = cevaplar;
	}

	@Override
	public int getCount() {
		return cevaplar.size();
	}

	@Override
	public Cevap getItem(int position) {
		return cevaplar.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		View view = inflater
				.inflate(R.layout.cevap_list_view_item, null, false);

		TextView tvPuan = (TextView) view.findViewById(R.id.txtPuan);
		TextView tvIcerik = (TextView) view.findViewById(R.id.txtIcerik);
		TextView tvKisi = (TextView) view.findViewById(R.id.txtKisi);
		ImageView dogruImageView=(ImageView)view.findViewById(R.id.dogru);
		
		if (!getItem(position).isDogruCevap()) {
			dogruImageView.setVisibility(View.INVISIBLE);
		}
		


		tvIcerik.setText(getItem(position).getIcerik().toString());
		tvPuan.setText(Integer.toString(getItem(position).getPuan()));
		tvKisi.setText(getItem(position).getUyeAd());

		return view;
	}
}