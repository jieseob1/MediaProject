package com.example.mediaproject.Home.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediaproject.Home.adapter.PersonHomeAdapter;
import com.example.mediaproject.R;
import com.example.mediaproject.add.Utils.PersonAdapter;
import com.example.mediaproject.add.Utils.PersonDBHelper;
import com.example.mediaproject.seerecycleractivity.SeeRecyclerActivity;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by User on 5/28/2017.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "HomeFragment";
    private RecyclerView recyclerView;
    private RecyclerView horizontalRecyclerview;
    String price, new_count;
    private int count, count2;
    private ImageView next_black_home_fragment,home_banner_image;
    private TextView numberOfRecyclerview, numberOfRecyclerview_text1, numberOfRecyclerview_text2, howMuchMoneyiGot,startSubscribe;
    private PersonDBHelper dbHelper;
    private PersonAdapter adapter;
    private PersonHomeAdapter personHomeAdapter;
    private LinearLayoutManager mLayoutManager, mLayoutManager1;
    private LinearLayout haverecyclerviewlinlayout;
    private String filter = "diffpaymentdate";
    private int counter;
//price 같은 경우는 price += price; 로 진행하면 될 듯

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView currentDate = (TextView) view.findViewById(R.id.currentDate);
        currentTime(currentDate);

        howMuchMoneyiGot = view.findViewById(R.id.howMuchMoneyiGot);
        haverecyclerviewlinlayout = (LinearLayout) view.findViewById(R.id.haverecyclerviewlinlayout);
        startSubscribe = view.findViewById(R.id.startSubscribe);
//        리사이클러뷰 부분
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        horizontalRecyclerview = (RecyclerView) view.findViewById(R.id.haverecyclerview);

        mLayoutManager1 = new LinearLayoutManager(view.getContext());
        mLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerview.setHasFixedSize(true);
        horizontalRecyclerview.setLayoutManager(mLayoutManager1);

        home_banner_image = (ImageView) view.findViewById(R.id.home_banner_image);
        haverecyclerviewlinlayout.setOnClickListener(this);



        numberOfRecyclerview = (TextView) view.findViewById(R.id.numberOfRecyclerview);
        numberOfRecyclerview_text1 = (TextView) view.findViewById(R.id.numberOfRecyclerview_text1);
        numberOfRecyclerview_text2 = (TextView) view.findViewById(R.id.numberOfRecyclerview_text2);

        next_black_home_fragment = (ImageView) view.findViewById(R.id.next_black_home_fragment);
        populaterecyclerView(filter);
        populateHorizontalRecyclerView(filter);

        if (counter == 0){
            startSubscribe.setText("새로운 구독을 추가해보라구미 ");
            home_banner_image.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            startSubscribe.setText("곧 구독 갱신이 시작된다구미 ");
            home_banner_image.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
        numberOfRecyclerview.setText(String.valueOf(counter));

        currentMoney(howMuchMoneyiGot);
//현재 날짜 쓰는 부분


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        populaterecyclerView(filter);
        populateHorizontalRecyclerView(filter);
        if (counter == 0){
            startSubscribe.setText("새로운 구독을 추가해보라구미 ");
            home_banner_image.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            startSubscribe.setText("곧 구독 갱신이 시작된다구미 ");
            home_banner_image.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }


    private void populaterecyclerView(String filter) {

        dbHelper = new PersonDBHelper(getActivity().getApplicationContext());
        adapter = new PersonAdapter(dbHelper.peopleList(filter), getActivity().getApplicationContext(), recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void populateHorizontalRecyclerView(String filter) {
        dbHelper = new PersonDBHelper(getActivity().getApplicationContext());
        personHomeAdapter = new PersonHomeAdapter(dbHelper.peopleList(filter), getActivity().getApplicationContext(), horizontalRecyclerview);
        horizontalRecyclerview.setAdapter(personHomeAdapter);
        personHomeAdapter.notifyDataSetChanged();

        counter = personHomeAdapter.getItemCount();
        if (counter > 0) {
            haverecyclerviewlinlayout.setBackgroundResource(R.drawable.round_button_home_fragment_recyclerview_black);
            numberOfRecyclerview.setTextColor(Color.parseColor("#FFFFFF"));
            numberOfRecyclerview_text1.setTextColor(Color.parseColor("#FFFFFF"));
            numberOfRecyclerview_text2.setTextColor(Color.parseColor("#FFFFFF"));
            next_black_home_fragment.setImageResource(R.drawable.ic_next_white);
        } else {
            haverecyclerviewlinlayout.setBackgroundResource(R.drawable.round_button_morelightgrey);
            numberOfRecyclerview.setTextColor(Color.parseColor("#373737"));
            numberOfRecyclerview_text1.setTextColor(Color.parseColor("#373737"));
            numberOfRecyclerview_text2.setTextColor(Color.parseColor("#373737"));
            next_black_home_fragment.setImageResource(R.drawable.ic_next_black);

        }


    }

    public void currentTime(TextView textView) {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

        String year = yearFormat.format(currentTime);//현재 년
        String month = monthFormat.format(currentTime);//현재 월
        textView.setText(year + "년 " + month + "월");
    }

    private void currentMoney(TextView textView){
        int i;
        for (i = 0; i < adapter.getItemCount() ; i ++)
        {
        price = adapter.searchPrice(i);//string price
        count = Integer.parseInt(price);
        count2 += count;
        }
        new_count = String.valueOf(count2);
        textView.setText(new_count+"원");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.haverecyclerviewlinlayout:
                Intent goToSeeRecycler = new Intent(getContext().getApplicationContext(), SeeRecyclerActivity.class);
                startActivity(goToSeeRecycler);
                break;
        }

    }
}




