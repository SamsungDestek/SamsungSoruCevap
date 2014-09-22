package com.example.samsungsorucevap.Fragment;

import com.example.samsungsorucevap.ConvertStreamToString;
import com.example.samsungsorucevap.R;
import com.example.samsungsorucevap.Adapter.SorularAdapter;
import com.example.samsungsorucevap.Entity.Soru;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

public class fragmentSorular extends Fragment {

	ArrayList<Soru> sorular;
	ListView lvSorular;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_sorular, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		sorular = new ArrayList<Soru>();
		lvSorular = (ListView) getActivity().findViewById(R.id.listView1);
		new DownloadSoruJson().execute();
	}

	private class DownloadSoruJson extends AsyncTask<Void, Void, Void> {
		JSONArray jsonArray = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			String url = "http://android.e2yazilim.com/api/soru";
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
					Soru soru = new Soru();
					soru.setId(Integer.parseInt(jsonArray.getJSONObject(i)
							.getString("Id").toString()));
					soru.setBaslik(jsonArray.getJSONObject(i)
							.getString("Baslik").toString());
					soru.setIcerik(jsonArray.getJSONObject(i)
							.getString("Icerik").toString());
					soru.setUyeId(Integer.parseInt(jsonArray.getJSONObject(i)
							.getString("UyeId").toString()));
					soru.setPuan(Integer.parseInt(jsonArray.getJSONObject(i)
							.getString("Puan").toString()));
					soru.setGoruntulenme(Integer.parseInt(jsonArray
							.getJSONObject(i).getString("Goruntulenme")
							.toString()));
					soru.setUyeAd(jsonArray.getJSONObject(i).getString("UyeAd")
							.toString());
					soru.setCevapSayi(Integer
							.parseInt(jsonArray.getJSONObject(i)
									.getString("CevapSayi").toString()));
					sorular.add(soru);
				}
				SorularAdapter adapter = new SorularAdapter(getActivity()
						.getBaseContext(), sorular);
				lvSorular.setAdapter(adapter);
			} catch (JSONException e) {
				Log.e("Hata", e.getMessage());
			}
		}

	}
}
