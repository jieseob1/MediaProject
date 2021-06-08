package com.example.mediaproject.update;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mediaproject.Home.HomeActivity;
import com.example.mediaproject.R;
import com.example.mediaproject.add.Utils.PersonDBHelper;
import com.example.mediaproject.add.model.Person;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "UpdateActivity";

    Switch aSwitch;
    Button updateBtn;
    LinearLayout linlayout1, linlayout2, linlayout3, linlayout4, linlayout5;
    ImageView imageButton, netflix_img, youtube_img, watcha_img, spotify_img, melon_img, medium_img, millie_img, ridibooks_img,
            notion_img, adobeilluste_img, baemin_img, yogiyo_img, coupang_img, socar_img, finishThisActivity;
    EditText et_serviceName;
    EditText et_typeOfMembership;
    EditText et_price;
    EditText et_lastPaymentDate;
    EditText moreDetail;
    String imageUrl;
    TextView delete_text, serName_text_update;
    private long receivedPersonId;
    private ProgressBar uploadProgressBar;
    Calendar myCalendar = Calendar.getInstance();
    private PersonDBHelper dbHelper;
    private String switchCondition = "OFF";
    private String nextMonthPaymentDate, beforeMonthPayment = "";
    private String diffPaymentDate;

    private String text_shareInfo_string = "", text_shareInfo2_string = "", text_shareInfo3_string = "";
    private String imageUrl_shareInfo_string = "", imageUrl_shareInfo2_string = "", imageUrl_shareInfo3_string = "";
    LinearLayout linearLayout_shareInfo_1, linearLayout_shareInfo_2, linearLayout_shareInfo_3;
    CircleImageView shareInfo_image1_imgview, shareInfo_image2_imgview, shareInfo_image3_imgview;
    TextView shareInfo_text1_tv, shareInfo_text2_tv, shareInfo_text3_tv;


    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
            int new_month = month + 2;
            int new_month2 = month + 1;
            beforeMonthPayment = new_month2 + "." + dayOfMonth;
            nextMonthPaymentDate = String.valueOf(new_month + "." + dayOfMonth);
            System.out.println("nextMonthPaymentDate:" + nextMonthPaymentDate);
            System.out.println("beforeMonthPayment:" + beforeMonthPayment);

            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.KOREA);

            String day = dayFormat.format(currentTime);
            int myDay = Integer.parseInt(day);
            diffPaymentDate = String.valueOf(Math.abs(myDay - dayOfMonth));
        }
    };

    private void updateLabel() {
        String myFormat = "MM.dd";    // 출력형식   2018/11/28
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);

        et_lastPaymentDate.setText(sdf.format(myCalendar.getTime()));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        netflix_img = findViewById(R.id.netflix_img);
        youtube_img = findViewById(R.id.youtube_img);
        watcha_img = findViewById(R.id.watcha_img);
        spotify_img = findViewById(R.id.spotify_img);
        melon_img = findViewById(R.id.melon_img);
        medium_img = findViewById(R.id.medium_img);
        millie_img = findViewById(R.id.millie_img);
        ridibooks_img = findViewById(R.id.ridibooks_img);
        notion_img = findViewById(R.id.notion_img);
        adobeilluste_img = findViewById(R.id.adobeilluste_img);
        baemin_img = findViewById(R.id.baemin_img);
        yogiyo_img = findViewById(R.id.yogiyo_img);
        coupang_img = findViewById(R.id.coupang_img);
        socar_img = findViewById(R.id.socar_img);
        serName_text_update = findViewById(R.id.serName_text_update);

        updateBtn = (Button) findViewById(R.id.uploadBtnUpdate);
        imageButton = (ImageView) findViewById(R.id.selectLogo_update);


        delete_text = (TextView) findViewById(R.id.delete_text);
//        삭제하는 부분
        et_serviceName = (EditText) findViewById(R.id.et_serviceNameUpdate);
        et_typeOfMembership = (EditText) findViewById(R.id.et_typeOfMembershipUpdate);
        et_price = (EditText) findViewById(R.id.et_priceUpdate);
        et_lastPaymentDate = (EditText) findViewById(R.id.et_lastPaymentDateUpdate);
        moreDetail = (EditText) findViewById(R.id.moreDetailUpdate);


        linlayout1 = findViewById(R.id.linLayout1);
        linlayout2 = findViewById(R.id.linLayout2);
        linlayout3 = findViewById(R.id.linLayout3);
        linlayout4 = findViewById(R.id.linLayout4);
        linlayout5 = findViewById(R.id.linLayout5);

        linearLayout_shareInfo_1 = findViewById(R.id.linearLayout_shareInfo_1_update);
        linearLayout_shareInfo_2 = findViewById(R.id.linearLayout_shareInfo_2_update);
        linearLayout_shareInfo_3 = findViewById(R.id.linearLayout_shareInfo_3_update);

        shareInfo_image1_imgview = findViewById(R.id.shareInfo_image1_update);
        shareInfo_image2_imgview = findViewById(R.id.shareInfo_image2_update);
        shareInfo_image3_imgview = findViewById(R.id.shareInfo_image3_update);

        shareInfo_text1_tv = findViewById(R.id.shareInfo_text1_update);
        shareInfo_text2_tv = findViewById(R.id.shareInfo_text2_update);
        shareInfo_text3_tv = findViewById(R.id.shareInfo_text3_update);

        shareInfo_image1_imgview.setOnClickListener(this);
        shareInfo_image2_imgview.setOnClickListener(this);
        shareInfo_image3_imgview.setOnClickListener(this);



        finishThisActivity = findViewById(R.id.finishThisActivity);
        et_lastPaymentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(UpdateActivity.this, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dbHelper = new PersonDBHelper(this);

        try {
            //get intent to get person id
            receivedPersonId = getIntent().getLongExtra("USER_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        /***populate user data before update***/
        Person queriedPerson = dbHelper.getPerson(receivedPersonId);
        //set field to this user data
        et_serviceName.setText(queriedPerson.getServiceName());
        et_typeOfMembership.setText(queriedPerson.getTypeOfMembership());
        et_price.setText(queriedPerson.getPrice());
        et_lastPaymentDate.setText(queriedPerson.getLastPaymentDate());
        moreDetail.setText(queriedPerson.getDetail());

        shareInfo_text1_tv.setText(queriedPerson.getShareInfotext1());
        shareInfo_text2_tv.setText(queriedPerson.getShareInfotext2());
        shareInfo_text3_tv.setText(queriedPerson.getShareInfotext3());


        fromImageUrlToCircleImage(queriedPerson.getShareInfoimageurl1(), shareInfo_image1_imgview);
        fromImageUrlToCircleImage(queriedPerson.getShareInfoimageurl2(), shareInfo_image2_imgview);
        fromImageUrlToCircleImage(queriedPerson.getShareInfoimageurl3(), shareInfo_image3_imgview);


        serName_text_update.setText("구독 서비스");
        Glide.with(this)
                .asBitmap()
                .load(queriedPerson.getImageUrl())
                .into(imageButton);

        changeEdittext(et_serviceName, linlayout1);
        changeEdittext(et_typeOfMembership, linlayout2);
        changeEdittext(et_price, linlayout3);
        changeEdittext(et_lastPaymentDate, linlayout4);
        changeEdittext(moreDetail, linlayout5);

//        이 부분은 마지막 결제일을 눌렀을 때 진행될 부분
        imageButton.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        delete_text.setOnClickListener(this);
        finishThisActivity.setOnClickListener(this);
        aSwitch = (Switch) findViewById(R.id.addSwitch);
        switchMotion(aSwitch);

    }

    @Override
    protected void onResume() {
        super.onResume();
        shareInfo_image1_imgview.setVisibility(View.VISIBLE);
        linearLayout_shareInfo_1.setVisibility(View.VISIBLE);

    }

    private void updatePerson() {
        String servicename = et_serviceName.getText().toString().trim();
        String typeOfMembership = et_typeOfMembership.getText().toString().trim();
        String price = et_price.getText().toString().trim();
        String lastPaymentDate = et_lastPaymentDate.getText().toString().trim();
        String detail = moreDetail.getText().toString().trim();


        if (servicename.isEmpty()) {
            //error servicename is empty
            Toast.makeText(this, "구독명을 적어달라구미!", Toast.LENGTH_SHORT).show();
        }

        if (typeOfMembership.isEmpty()) {
            //error servicename is empty
            Toast.makeText(this, "멤버십을 선택해달라구미!", Toast.LENGTH_SHORT).show();
        }

        if (price.isEmpty()) {
            //error servicename is empty
            Toast.makeText(this, "가격을 적어달라구미!", Toast.LENGTH_SHORT).show();
        }


        if (lastPaymentDate.isEmpty()) {
            //error servicename is empty
            Toast.makeText(this, "날짜를 선택해달라구미!", Toast.LENGTH_SHORT).show();
        }
//String serviceName, String price, String typeOfMembership,String lastPaymentDate,String detail, String image
        //create new person
        if (servicename.length() > 0 && typeOfMembership.length() > 0 && price.length() > 0 && lastPaymentDate.length() > 0) {
            Person updatePerson = new Person(servicename, price, typeOfMembership, lastPaymentDate, detail, imageUrl, switchCondition, nextMonthPaymentDate, diffPaymentDate
                    , imageUrl_shareInfo_string, imageUrl_shareInfo2_string, imageUrl_shareInfo3_string, text_shareInfo_string, text_shareInfo2_string, text_shareInfo3_string);
            dbHelper.updatePersonRecord(receivedPersonId, this, updatePerson);
            finish();
        }
        else{
            Toast.makeText(this, "아직 빈곳이 남아있다구미!", Toast.LENGTH_SHORT).show();
        }
        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete

    }


    public void switchMotion(Switch sw) {
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton v, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "푸시 알림이 설정되었다구미!", Toast.LENGTH_SHORT).show();
                    switchCondition = "ON";
                } else {
                    Toast.makeText(getApplicationContext(), "푸시 알림이 설정이 해제되었다구미!", Toast.LENGTH_SHORT).show();
                    switchCondition = "OFF";
                }
            }
        });
    }


    private void changeEdittext(EditText editText, LinearLayout linearLayout) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            //입력하기 전 호출
            public void beforeTextChanged(CharSequence charSequence, int start, int counter, int after) {

            }

            //EditText에 변화가 있을 때-난 글자가 1개  이상일경우 색깔 분홍색으로
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String text = charSequence.toString();
                if (text.length() >= 1) { //1글자 이상이면
                    linearLayout.setBackground(getResources().getDrawable(R.drawable.bottom_only_solid_after_edittext, getTheme()));
                } else {
                    linearLayout.setBackground(getResources().getDrawable(R.drawable.bottom_only_solid_before_edittext, getTheme()));
                }

            }

            @Override
            //입력이 끝났을 경우
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void deleteText() {

        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setTitle("리스트 삭제");
        builder.setMessage("리스트를 정말 삭제하시겠습니까?");
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                PersonDBHelper dbHelper = new PersonDBHelper(getApplicationContext());
                dbHelper.deletePersonRecord(receivedPersonId, getApplicationContext());
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

//    private void showBottomSheetDialogShareInfo() {
//        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
//        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout_share_info);
//
//        LinearLayout somin = bottomSheetDialog.findViewById(R.id.somin);
//        LinearLayout onejung = bottomSheetDialog.findViewById(R.id.onejung);
//        LinearLayout jieseob = bottomSheetDialog.findViewById(R.id.jieseob);
//
//        bottomSheetDialog.show();
//
//        clickButtonShareInfo(somin, bottomSheetDialog, "https://i.ibb.co/gmY2pS0/img-profile-user1.png", "SOMIN");
//        clickButtonShareInfo(onejung, bottomSheetDialog, "https://i.ibb.co/YPrr9gd/img-profile-user2.png", "ONEJUNG");
//        clickButtonShareInfo(jieseob, bottomSheetDialog, "https://i.ibb.co/wBwyxgz/img-profile-user3.png", "JIESEOB");
//
//    }

//    private void clickButtonShareInfo(LinearLayout linearLayout, BottomSheetDialog bottomSheetDialog, String imageWebUrl, String string) {
//
//        linearLayout.setOnClickListener(new View.OnClickListener() { //bottom sheet dialog중 하나를 누르게 되면
//
//
//            @Override
//            public void onClick(View view) {
//                if (imageUrl_shareInfo_string.length() <= 1 && text_shareInfo_string.length() <= 1) {//글씨가 적혀있지 않은 경우를 말함
//                    imageUrl_shareInfo_string = imageWebUrl;//db에 저장할 imageUrl_shareInfo에 imageWebUrl을 넣고
//                    text_shareInfo_string = string;//db에 저장할 string부분
//                    shareInfo_text1_tv.setText(string);//textview부분 설정
//                    fromImageUrlToCircleImage(imageWebUrl, shareInfo_image1_imgview);//이미지 설정
//                    linearLayout_shareInfo_2.setVisibility(View.VISIBLE);
//                    shareInfo_image2_imgview.setVisibility(View.VISIBLE);
//                    shareInfo_image2_imgview.getResources().getDrawable(R.drawable.ic_plus_user,getTheme());
//
//                } else if (imageUrl_shareInfo_string.length() > 1 && text_shareInfo_string.length() > 1 && imageUrl_shareInfo2_string.length() <= 1 && text_shareInfo2_string.length() <= 1) {
//                    imageUrl_shareInfo2_string = imageWebUrl;//db에 저장할 imageUrl_shareInfo에 imageWebUrl을 넣고
//                    text_shareInfo2_string = string;
//                    shareInfo_text2_tv.setText(string);
//                    fromImageUrlToCircleImage(imageWebUrl, shareInfo_image2_imgview);
//                    linearLayout_shareInfo_3.setVisibility(View.VISIBLE);
//                    shareInfo_image3_imgview.setVisibility(View.VISIBLE);
//                    shareInfo_image3_imgview.getResources().getDrawable(R.drawable.ic_plus_user,getTheme());
//
//
//                } else if (imageUrl_shareInfo_string.length() > 1 && text_shareInfo_string.length() > 1 && imageUrl_shareInfo2_string.length() > 1 && text_shareInfo2_string.length() > 1
//                        && imageUrl_shareInfo3_string.length() <= 1 && text_shareInfo3_string.length() <= 1) {
//                    imageUrl_shareInfo3_string = imageWebUrl;//db에 저장할 imageUrl_shareInfo에 imageWebUrl을 넣고
//                    text_shareInfo3_string = string;
//                    shareInfo_text3_tv.setText(string);
//                    fromImageUrlToCircleImage(imageWebUrl, shareInfo_image3_imgview);
//                }
//                //
//                //누르면 그 부분에 관한 string과,
//                bottomSheetDialog.dismiss();
//            }
//        });
//
//    }


    private void fromImageUrlToCircleImage(String imageWebUrl, CircleImageView circleImageView) {
        Glide.with(this)
                .asBitmap()
                .load(imageWebUrl)
                .into(circleImageView);
    }

    private void showBottomSheetDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout);

        LinearLayout netflix = bottomSheetDialog.findViewById(R.id.netflix);
        LinearLayout youtube = bottomSheetDialog.findViewById(R.id.youtube);
        LinearLayout watcha = bottomSheetDialog.findViewById(R.id.watcha);
        LinearLayout spotify = bottomSheetDialog.findViewById(R.id.spotify);
        LinearLayout melon = bottomSheetDialog.findViewById(R.id.melon);
        LinearLayout medium = bottomSheetDialog.findViewById(R.id.medium);
        LinearLayout millie = bottomSheetDialog.findViewById(R.id.millie);
        LinearLayout ridibooks = bottomSheetDialog.findViewById(R.id.ridibooks);
        LinearLayout notion = bottomSheetDialog.findViewById(R.id.notion);
        LinearLayout adobeilluste = bottomSheetDialog.findViewById(R.id.adobeilluste);
        LinearLayout photoshop = bottomSheetDialog.findViewById(R.id.photoshop);
        LinearLayout tving = bottomSheetDialog.findViewById(R.id.tving);
        LinearLayout coupang = bottomSheetDialog.findViewById(R.id.coupang);
        LinearLayout laftel = bottomSheetDialog.findViewById(R.id.laftel);


        bottomSheetDialog.show();

        clickButton(netflix, bottomSheetDialog, "https://i.ibb.co/LgSk92r/img-service-netflix.png", imageButton);
        clickButton(youtube, bottomSheetDialog, "https://i.ibb.co/6vPFx8h/1.png", imageButton);
        clickButton(watcha, bottomSheetDialog, "https://i.ibb.co/C913dgg/img-service-whatcha.png", imageButton);
        clickButton(spotify, bottomSheetDialog, "https://i.ibb.co/nQWtfpK/img-service-spotify.png", imageButton);
        clickButton(melon, bottomSheetDialog, "https://i.ibb.co/pvtGQgZ/img-service-melon.png", imageButton);
        clickButton(medium, bottomSheetDialog, "https://i.ibb.co/hm366pC/img-service-midium.png", imageButton);
        clickButton(millie, bottomSheetDialog, "https://i.ibb.co/gDJbWMx/img-service-milli.png", imageButton);
        clickButton(ridibooks, bottomSheetDialog, "https://i.ibb.co/02yWJ1W/img-service-ridibooks.png", imageButton);
        clickButton(notion, bottomSheetDialog, "https://i.ibb.co/9y2n2V0/img-service-notion.png", imageButton);
        clickButton(adobeilluste, bottomSheetDialog, "https://i.ibb.co/pfMwZpD/img-service-illustrator.png", imageButton);
        clickButton(photoshop, bottomSheetDialog, "https://i.ibb.co/2sRgRvr/img-service-photoshop.png", imageButton);
        clickButton(tving, bottomSheetDialog, "https://i.ibb.co/5YSKhK8/img-service-tving.png", imageButton);
        clickButton(coupang, bottomSheetDialog, "https://i.ibb.co/bsyLq4j/img-service-coupang.png", imageButton);
        clickButton(laftel, bottomSheetDialog, "https://i.ibb.co/Qbsgg6q/img-service-laftel.png", imageButton);
    }

    private void clickButton(LinearLayout linearLayout, BottomSheetDialog bottomSheetDialog, String imageWebUrl, ImageView imageView) {
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromImageUrlToImage(imageWebUrl, imageView);
                if (imageUrl.length() <= 1) {
                    imageUrl = imageWebUrl;
                } else{
                    Person queriedPerson = dbHelper.getPerson(receivedPersonId);
                    imageUrl = queriedPerson.getImageUrl();
                }
                bottomSheetDialog.dismiss();
            }
        });
    }

    private void fromImageUrlToImage(String imageWebUrl, ImageView image) {
        Glide.with(this)
                .asBitmap()
                .load(imageWebUrl)
                .into(image);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.selectLogo_update:
                showBottomSheetDialog();
                break;
        }
        switch (view.getId()) {
            case R.id.uploadBtnUpdate:

                updatePerson();
                break;
        }
        switch (view.getId()) {
            case R.id.delete_text:
                deleteText();

                break;
        }
        switch (view.getId()) {
            case R.id.finishThisActivity:
                finish();
                break;
        }
//        switch (view.getId()) {
//            case R.id.shareInfo_image1_update:
//                showBottomSheetDialogShareInfo();
//                break;
//        }
//        switch (view.getId()) {
//            case R.id.shareInfo_image2_update:
//                showBottomSheetDialogShareInfo();
//                break;
//        }
//        switch (view.getId()) {
//            case R.id.shareInfo_image3_update:
//                showBottomSheetDialogShareInfo();
//                break;
//        }


    }

}