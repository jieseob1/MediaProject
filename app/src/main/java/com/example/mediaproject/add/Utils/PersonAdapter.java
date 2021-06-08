package com.example.mediaproject.add.Utils;


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
import com.example.mediaproject.subinfo.SubInfoActivity;
import com.example.mediaproject.update.UpdateActivity;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private List<Person> mPeopleList;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private ImageView profile;
        private TextView date;
        private TextView applicationName;
        private TextView sorting;
        private TextView subscribeMoney;
        private TextView dueDate;
        private TextView nextPaymentdate;

        private CircleImageView circleImageView1,circleImageView2,circleImageView3;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;

            profile = (ImageView) v.findViewById(R.id.icon);
            date = (TextView) v.findViewById(R.id.date);
            applicationName = (TextView) v.findViewById(R.id.applicationName);
            sorting = (TextView) v.findViewById(R.id.sorting);
            subscribeMoney = (TextView) v.findViewById(R.id.subscribeMoney);
            dueDate = (TextView) v.findViewById(R.id.dueDate);
            nextPaymentdate = (TextView) v.findViewById(R.id.nextPaymentdate);
            dueDate = (TextView) v.findViewById(R.id.dueDate);
            nextPaymentdate = (TextView) v.findViewById(R.id.nextPaymentdate);
            circleImageView1 = v.findViewById(R.id.shareUser1_image);
            circleImageView2 = v.findViewById(R.id.shareUser2_image);
            circleImageView3 = v.findViewById(R.id.shareUser3_image);
        }
    }

    public void add(int position, Person person) {
        mPeopleList.add(position, person);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mPeopleList.remove(position);
        notifyItemRemoved(position);
    }
    public String searchPrice(int position) {
        return mPeopleList.get(position).getPrice();
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public PersonAdapter(List<Person> myDataset, Context context, RecyclerView recyclerView) {
        mPeopleList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.recycler_item, parent, false);
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


        //다음달 형식 구하는 법






        holder.date.setText(person.getLastPaymentDate());
        holder.applicationName.setText(person.getServiceName());
        holder.sorting.setText(person.getTypeOfMembership());
        holder.subscribeMoney.setText(person.getPrice());
        holder.dueDate.setText(person.getDiffPaymentDate());
        holder.nextPaymentdate.setText(person.getNextMonthPaymentDate());


//        Picasso.with(mContext).load(person.getImageUrl()).placeholder(R.mipmap.ic_launcher).into(holder.profile);
        Glide.with(mContext).load(person.getImageUrl()).into(holder.profile);

//        Picasso.with(mContext).load(person.getShareInfoimageurl1()).placeholder(R.mipmap.ic_launcher).into(holder.circleImageView1);
//        Picasso.with(mContext).load(person.getShareInfoimageurl2()).placeholder(R.mipmap.ic_launcher).into(holder.circleImageView2);
//        Picasso.with(mContext).load(person.getShareInfoimageurl3()).placeholder(R.mipmap.ic_launcher).into(holder.circleImageView3);

        Glide.with(mContext).load(person.getShareInfoimageurl1()).into(holder.circleImageView1);
        Glide.with(mContext).load(person.getShareInfoimageurl2()).into(holder.circleImageView2);
        Glide.with(mContext).load(person.getShareInfoimageurl3()).into(holder.circleImageView3);
        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
//            누르면 해야 할 것
            public void onClick(View v) {
                goToSubInfoActivity(person.getId());

//                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                builder.setTitle("Choose option");
//                builder.setMessage("Update or delete user?");
//                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        //go to update activity
//                        goToUpdateActivity(person.getId());
//
//                    }
//                });
//                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        PersonDBHelper dbHelper = new PersonDBHelper(mContext);
//                        dbHelper.deletePersonRecord(person.getId(), mContext);
//
//                        mPeopleList.remove(position);
//                        mRecyclerV.removeViewAt(position);
//                        notifyItemRemoved(position);
//                        notifyItemRangeChanged(position, mPeopleList.size());
//                        notifyDataSetChanged();
//                    }
//                });
//                builder.set   NegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                builder.create().show();
            }
        });


    }

    private void goToSubInfoActivity(long personId) {
        Intent goToSubInfo = new Intent(mContext, SubInfoActivity.class);
        goToSubInfo.putExtra("USER_ID", personId);
        mContext.startActivity(goToSubInfo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        notifyDataSetChanged();
    }




    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public  int getItemCount() {
        return mPeopleList.size();
    }


}