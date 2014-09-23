package com.example.samsungsorucevap.Fragment;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.samsungsorucevap.ConvertStreamToString;
import com.example.samsungsorucevap.Helper;
import com.example.samsungsorucevap.R;
import com.example.samsungsorucevap.Adapter.CevaplarAdapter;
import com.example.samsungsorucevap.Entity.Cevap;
import com.example.samsungsorucevap.Entity.Soru;
import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class fragmentSoruCevaplari extends Fragment {


	ListView lvCevaplar;
	ArrayList<Cevap> cevaplar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_soru_cevaplari, container, false);
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		cevaplar = new ArrayList<Cevap>();
		lvCevaplar = (ListView) getActivity().findViewById(R.id.lvCevaplar);
		new DownloadCevapJson().execute();
	}

	private class DownloadCevapJson extends AsyncTask<Void, Void, Void> {

		JSONArray jsonArray = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			String url = "http://android.e2yazilim.com/api/cevap/1";
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);
			HttpResponse response;

			try {
				response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					String result = ConvertStreamToString
							.convertStreamToString(instream);
					jsonArray = new JSONArray(result);
					instream.close();
				}
			} catch (ClientProtocolException e) {
				Log.e("Hata", e.getMessage());
			} catch (IOException e) {
				Log.e("Hata", e.getMessage());
			} catch (JSONException e) {
				Log.e("Hata", e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void str) {
			try {
				for (int i = 0; i < jsonArray.length(); ++i) {
					Cevap cevap = new Cevap();
					cevap.setId(Integer.parseInt(jsonArray.getJSONObject(i)
							.getString("Id").toString()));
					cevap.setSoruId(Integer.parseInt(jsonArray.getJSONObject(i)
							.getString("SoruId").toString()));
					cevap.setUyeId(Integer.parseInt(jsonArray.getJSONObject(i)
							.getString("UyeId").toString()));
					cevap.setIcerik(jsonArray.getJSONObject(i)
							.getString("Icerik").toString());
					cevap.setPuan(Integer.parseInt(jsonArray.getJSONObject(i)
							.getString("Puan").toString()));
					cevap.setUyeAd(jsonArray.getJSONObject(i)
							.getString("UyeAd").toString());
					cevaplar.add(cevap);
				}
				CevaplarAdapter adapter = new CevaplarAdapter(getActivity()
						.getBaseContext(), cevaplar);
				lvCevaplar.setAdapter(adapter);
				Helper.getListViewSize(lvCevaplar);
			} catch (JSONException e) {
				Log.e("Hata", e.getMessage());
			}
		}
	}

}
