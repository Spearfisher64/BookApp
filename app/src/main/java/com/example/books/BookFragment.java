package com.example.books;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;


public class BookFragment extends Fragment {
    private static final String TAG= BookFragment.class.getSimpleName();
    EditText etBookTitle;
    TextView mTitleText, mAuthorText;
    Button searchBtn;
    private ConstraintLayout layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_books,container,false);
        etBookTitle= (EditText) view.findViewById(R.id.editTextTextPersonName);
        mTitleText =  (TextView) view.findViewById(R.id.titleText);
        mAuthorText =(TextView) view.findViewById(R.id.authorText);
        searchBtn=(Button) view.findViewById(R.id.button2);
        layout=(ConstraintLayout) view.findViewById(R.id.coordinator_layout);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showSnackbar();
            }
        });

        return view;


    }

    public void showSnackbar(){
        Snackbar snackbar = Snackbar.make(layout,"API Crashed.",Snackbar.LENGTH_INDEFINITE)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar snackbar1 = Snackbar.make(layout,"Closing Error",Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                });
        snackbar.show();


    }



/*    public void searchBooks(View view){
        String queryString = etBookTitle.getText().toString();
        Log.i(TAG,"SearchBooks:"+queryString);
        new FetchBook(mTitleText, mAuthorText).execute(queryString);
    } */



}