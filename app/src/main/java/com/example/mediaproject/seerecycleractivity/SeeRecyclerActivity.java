package com.example.mediaproject.seerecycleractivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mediaproject.R;
import com.example.mediaproject.add.AddActivity;
import com.example.mediaproject.add.Utils.PersonAdapter;
import com.example.mediaproject.add.Utils.PersonDBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SeeRecyclerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PersonDBHelper dbHelper;

    private TextView recyclernumber,currentyearmonth;
    private PersonAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private String filter = "diffpaymentdate";
    private int counter ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_see_recycler);

            currentyearmonth= findViewById(R.id.currentyearmonth);
            currentTime(currentyearmonth);

            recyclernumber = findViewById(R.id.recyclernumber);

            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

            recyclernumber.setText(String.valueOf(counter));

            System.out.println("counter is:"+counter);
            populaterecyclerView(filter);
            FloatingActionButton fab = findViewById(R.id.fab);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), AddActivity.class);
                    startActivity(i);

                }
            });
    }

    @Override
    protected void onResume() {
        super.onResume();
        populaterecyclerView(filter);
        recyclernumber.setText(String.valueOf(counter));
        System.out.println("counter is:"+counter);

    }

    private void populaterecyclerView(String filter) {
        dbHelper = new PersonDBHelper(getApplicationContext());
        adapter = new PersonAdapter(dbHelper.peopleList(filter), getApplicationContext(), recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        counter = adapter.getItemCount();

    }
    public void currentTime(TextView textView) {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        String year = yearFormat.format(currentTime);//현재 년
        String month = monthFormat.format(currentTime);//현재 월
        textView.setText(year + "년 " + month + "월");
    }
}