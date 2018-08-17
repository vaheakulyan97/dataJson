package com.example.hp.recievedatafromjson.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hp.recievedatafromjson.JsonItemsAdapter;
import com.example.hp.recievedatafromjson.R;
import com.example.hp.recievedatafromjson.models.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Models> list = new ArrayList<>();
    private  Models jsonModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.id_recyclerView);
        JsonAsyncTask jsonAsyncTask = new JsonAsyncTask();
        jsonAsyncTask.execute();

    }

    public Models getJsonModel() {
        return jsonModel;
    }

    public void setJsonModel(Models jsonModel) {
        this.jsonModel = jsonModel;
    }

    private void setRecyclerView() {
        JsonItemsAdapter jsonItemsAdapter = new JsonItemsAdapter(list, MainActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(jsonItemsAdapter);
    }

   class JsonAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("https://jsonplaceholder.typicode.com/photos");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream input = new BufferedInputStream(connection.getInputStream());
                BufferedReader bf = new BufferedReader(new InputStreamReader(input));
                String line;
                StringBuffer stringBuffer = new StringBuffer();
                while ((line = bf.readLine()) != null) {
                    stringBuffer.append(line);
                }
                JSONArray jsonArray = new JSONArray(stringBuffer.toString());
                for (int i = 0; i <jsonArray.length() ; i++) {
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    Models models = new Models();
                    models.setAlbumId( Integer.valueOf(jsonObject.getString("albumId")));
                    models.setId(Integer.valueOf(jsonObject.getString("id")));
                    models.setThumbnailUrl(jsonObject.getString("thumbnailUrl"));
                    models.setTitle(jsonObject.getString("title"));
                    models.setUrl(jsonObject.getString("url"));
                    list.add(models);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

      @Override
      protected void onPostExecute(String s) {
          super.onPostExecute(s);
          setRecyclerView();
      }
  }
}
