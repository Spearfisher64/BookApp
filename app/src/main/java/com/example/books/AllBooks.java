package com.example.books;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AllBooks extends AppCompatActivity {
    Button mHome;
    FloatingActionButton floatingActionButton;
    private static String JSON_URL = "https://run.mocky.io/v3/1a1198c4-be91-462c-9b7a-2e45d23901bc";

    List<BookModelClass> bookList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);
        floatingActionButton = findViewById(R.id.fab_btn);
        mHome = findViewById(R.id.homeBtn);
        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent myIntent = new Intent(Intent.ACTION_SEND);
             myIntent.setType("text/plain");
             String shareBody = "Your Body ";
             myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
             startActivity(Intent.createChooser(myIntent,"Share Using"));
            }
        });

        bookList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView2);


        GetData getData = new GetData();
        getData.execute();
    }

    public class GetData extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {

            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.connect();



                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while (data != -1){
                        current += (char) data;
                        data = isr.read();

                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("books");

                for (int i=0 ;i<jsonArray.length();i++){
                    JSONObject jsonObject1 =jsonArray.getJSONObject(i);

                    BookModelClass model = new BookModelClass();
                    model.setId(jsonObject1.getString("id"));
                    model.setName(jsonObject1.getString("name"));
                    model.setImg(jsonObject1.getString("image"));

                    bookList.add(model);

                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

            PutDataIntoRecyclerView (bookList);

        }
    }

    private void  PutDataIntoRecyclerView(List<BookModelClass> bookList){

        BookAdapter bookAdapter = new BookAdapter(this,bookList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(bookAdapter);

    }
}

