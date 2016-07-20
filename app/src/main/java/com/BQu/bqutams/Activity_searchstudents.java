package com.BQu.bqutams;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Activity_searchstudents extends AppCompatActivity {

    private String result;
    public String login_url = "http://bqutvc.bqutest.co.uk/poshithatest/search.php";
    ListView lv;
    List<String> students = new ArrayList<String>();
    SearchView searchView = null;
    private ArrayAdapter arrayAdapter;
    ProgressDialog proDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchstudents);
        lv=(ListView) findViewById(R.id.listView);
        searchView=(SearchView) findViewById(R.id.searchView);
        test();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                arrayAdapter.getFilter().filter(text);

                return false;
            }
        });

    }

    public void test() {

        SearchStudent ss = new SearchStudent(this,"");
        ss.execute();
    }

    private class SearchStudent extends AsyncTask<String, Void, String> {

        HttpURLConnection conn;
        URL url = null;
        String searchQuery;
        Context context;

       /* public SearchStudent(String searchQuery){
            this.searchQuery=searchQuery;
        }*/
       public SearchStudent(Context context, String data) {
           this.context = context;
           this.searchQuery = data;

       }
        @Override
        protected String doInBackground(String... params) {

            try {

                // Enter URL address where your php file resides
                url = new URL("http://bqutvc.bqutest.co.uk/poshithatest/search.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                //conn.setReadTimeout(READ_TIMEOUT);
                // conn.setConnectTimeout(CONNECTION_TIMEOUT);

                // setDoInput and setDoOutput to true as we send and recieve data
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // add parameter to our above url
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("searchQuery", searchQuery);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {
                    return("Connection error");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            proDialog = new ProgressDialog(context);
            proDialog.setTitle("Loading");
            proDialog.setMessage("Loading... Please Wait");
            proDialog.show();
        }

        @Override
        protected void onPostExecute(String result) {
            proDialog.dismiss();
            parse(result);
        }
    }

    private void parse(String result) {
        try {
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;

            students.clear();

            for (int i = 0; i < ja.length(); i++) {

                jo = ja.getJSONObject(i);
                String name = jo.getString("Student_name");
                students.add(name);


            }

            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, students);
            lv.setAdapter(arrayAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //return 0;
    }
}
