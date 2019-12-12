package com.example.webtech;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpGetLyricsRequest extends AsyncTask<String, Void, String> {

    private final static String TAG = HttpGetLyricsRequest.class.getSimpleName();
    List<Song> songs;
    private OnRequestCompleted mOnRequestsCompleted;
    HttpGetLyricsRequest(OnRequestCompleted onRequestCompleted) {
    mOnRequestsCompleted = onRequestCompleted;
    }

    @Override
    protected String doInBackground(String... strings) {
        String inputLine;
        JSONArray jsonArray = null;

        try {
            //Create URL
            URL url = new URL("https://genius.p.rapidapi.com/artists/16775/songs");

            //Create Connection with handler
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);

            httpURLConnection.setRequestProperty("Content-type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("X-RapidAPI-Key", "5769f67b65msh0ddc434326100adp190113jsn120c6f32efd1");

            //Connect
            httpURLConnection.connect();

            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());

            //Create a new buffered reader and String Builder
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            //Check if the line we are readind is not null
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            //Parse to Json
            JSONObject resultObject = new JSONObject(stringBuilder.toString());
            JSONObject songObject = resultObject.getJSONObject("response");
            jsonArray = songObject.getJSONArray("songs");


            //Close
            bufferedReader.close();
            inputStreamReader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray != null ? jsonArray.toString() : null;

    }
    @Override
    protected void onPostExecute (String s) {
        super.onPostExecute(s);
        Log.i(TAG,s);

        songs = new ArrayList<>();

        try{
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0;i < jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                songs.add(
                        new Song(
                                jsonObject.getInt("id"),
                                jsonObject.getString("title"),
                                jsonObject.getString("song_art_image_thumbnail_url"),
                                jsonObject.getString("url"),
                                jsonObject.getJSONObject("primary_artist").getString("name")
                        ));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mOnRequestsCompleted.onGetSongsCompleted(songs);
    }
}
