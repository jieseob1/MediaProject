package com.example.mediaproject.Home.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mediaproject.R;
import com.example.mediaproject.add.model.Person;
import com.example.mediaproject.seerecycleractivity.SeeRecyclerActivity;
import com.example.mediaproject.subinfo.SubInfoActivity;
import com.example.mediaproject.update.UpdateActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


public class PersonHomeAdapter extends RecyclerView.Adapter<PersonHomeAdapter.ViewHolder> {
    private List<Person> mPeopleList;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView profile;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;

            profile = (ImageView) v.findViewById(R.id.icon);


        }
    }

//    public void add(int position, Person person) {
//        mPeopleList.add(position, person);
//        notifyItemInserted(position);
//    }
//
//    public void remove(int position) {
//        mPeopleList.remove(position);
//        notifyItemRemoved(position);
//    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public PersonHomeAdapter(List<Person> myDataset, Context context, RecyclerView recyclerView) {
        mPeopleList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.recycler_showimage_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Person person = mPeopleList.get(position);

//        Picasso.with(mContext).load(person.getImageUrl()).placeholder(R.mipmap.ic_launcher).into(holder.profile);

        Glide.with(mContext).load(person.getImageUrl()).into(holder.profile);
        //listen to single view layout click


    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPeopleList.size();
    }


}