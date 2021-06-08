package com.example.mediaproject.Home.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject.Home.adapter.PersonReceiptAdapter;
import com.example.mediaproject.R;
import com.example.mediaproject.add.Utils.PersonAdapter;
import com.example.mediaproject.add.Utils.PersonDBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReceiptFragment extends Fragment {
    private static final String TAG = "ReceiptFragment";

    private RecyclerView recyclerView;
    private PersonDBHelper dbHelper;
    private PersonReceiptAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private String filter = "";
    private TextView monthMoney, currentMonth;
    String price, new_count;    
    private int count, count2;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_receipt, container, false);
//        mListView = (ListView) view.findViewById(R.id.listView);
        recyclerView = (RecyclerView) view.findViewById(R.id.receipt_recyclerview);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        populaterecyclerView(filter);
        monthMoney = (TextView) view.findViewById(R.id.monthMoney);
        currentMonth = (TextView) view.findViewById(R.id.currentMonth);

        currentMoney(monthMoney);

        currentTime(currentMonth);

        return view;
    }

    public void currentTime(TextView textView) {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());

        String month = monthFormat.format(currentTime);//현재 월
        textView.setText(month);
    }

    private void populaterecyclerView(String filter) {
        dbHelper = new PersonDBHelper(getActivity().getApplicationContext());
        adapter = new PersonReceiptAdapter(dbHelper.peopleList(filter), getActivity().getApplicationContext(), recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void currentMoney(TextView textView) {
        int i;
        for (i = 0; i < adapter.getItemCount(); i++) {
            price = adapter.searchPrice(i);
            count = Integer.parseInt(price);
            count2 += count;
        }
        new_count = String.valueOf(count2);
        textView.setText(new_count);

    }
}
