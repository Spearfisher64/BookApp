/*package com.example.books;

import android.os.AsyncTask;
import android.widget.TextView;

public class FetchBook extends AsyncTask<String,Void,String> {
    private TextView mTitleText;
    private TextView mAuthorText;

    public FetchBook(TextView titleText, TextView mTitleText){
        this.mTitleText=mTitleText;
        this.mAuthorText=mAuthorText;
    }

    @Override
    protected String doInBackground(String... strings) {

        return NetworkUtils.getBookInfo(strings[2]);
    }
    @Override
    protected  void onPostExecute (String s){
        super.onPostExecute(s);
    }
} */
