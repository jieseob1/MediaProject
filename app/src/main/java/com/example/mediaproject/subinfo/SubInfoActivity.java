package com.example.mediaproject.subinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mediaproject.R;
import com.example.mediaproject.add.Utils.PersonDBHelper;
import com.example.mediaproject.add.model.Person;
import com.example.mediaproject.update.UpdateActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SubInfoActivity";

    Button updateBtn;
    LinearLayout changeMemberShip_subinfo;
    LinearLayout dismissMemberShip_subinfo;
    TextView text_serviceName_info;
    TextView text_typeOfMembership_info;
    TextView text_price_info;
    TextView text_lastPaymentDate_info;
    TextView moreDetail_info;
    ImageView image;
    TextView gotoUpdateText, pushAlarmText;
    private long receivedPersonId;
    private PersonDBHelper dbHelper;

    private String text_shareInfo_string = "", text_shareInfo2_string = "", text_shareInfo3_string = "";
    private String imageUrl_shareInfo_string = "", imageUrl_shareInfo2_string = "", imageUrl_shareInfo3_string = "";
    LinearLayout linearLayout_shareInfo_1, linearLayout_shareInfo_2, linearLayout_shareInfo_3;
    CircleImageView shareInfo_image1_imgview, shareInfo_image2_imgview, shareInfo_image3_imgview;
    TextView shareInfo_text1_tv, shareInfo_text2_tv, shareInfo_text3_tv;


    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_info);

        gotoUpdateText = (TextView) findViewById(R.id.gotoupdateText);
        changeMemberShip_subinfo = (LinearLayout) findViewById(R.id.changeMemberShip_subinfo);
        dismissMemberShip_subinfo = (LinearLayout) findViewById(R.id.dismissMemberShip_subinfo);
        pushAlarmText = (TextView) findViewById(R.id.pushAlarmText);

        text_serviceName_info = (TextView) findViewById(R.id.text_serviceName_info);
        text_typeOfMembership_info = (TextView) findViewById(R.id.text_typeOfMembership_info);
        text_price_info = (TextView) findViewById(R.id.text_price_info);
        text_lastPaymentDate_info = (TextView) findViewById(R.id.text_lastPaymentDate_info);
        moreDetail_info = (TextView) findViewById(R.id.moreDetail_info);
        image = findViewById(R.id.selectLogo_info);
        ;


        linearLayout_shareInfo_1 = findViewById(R.id.linearLayout_shareInfo_1_update);
        linearLayout_shareInfo_2 = findViewById(R.id.linearLayout_shareInfo_2_update);
        linearLayout_shareInfo_3 = findViewById(R.id.linearLayout_shareInfo_3_update);

        shareInfo_image1_imgview = findViewById(R.id.shareInfo_image1_update);
        shareInfo_image2_imgview = findViewById(R.id.shareInfo_image2_update);
        shareInfo_image3_imgview = findViewById(R.id.shareInfo_image3_update);

        shareInfo_text1_tv = findViewById(R.id.shareInfo_text1_update);
        shareInfo_text2_tv = findViewById(R.id.shareInfo_text2_update);
        shareInfo_text3_tv = findViewById(R.id.shareInfo_text3_update);

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
        text_serviceName_info.setText(queriedPerson.getServiceName());
        text_typeOfMembership_info.setText(queriedPerson.getTypeOfMembership());
        text_price_info.setText(queriedPerson.getPrice());
        text_lastPaymentDate_info.setText(queriedPerson.getLastPaymentDate());
        moreDetail_info.setText(queriedPerson.getDetail());
        pushAlarmText.setText(queriedPerson.getSwitchCondition());
        if (pushAlarmText.equals("ON")) {
            pushAlarmText.setTextColor(getResources().getColor(R.color.pink, getTheme()));
        } else if (pushAlarmText.equals("OFF")) {
            pushAlarmText.setTextColor(getResources().getColor(R.color.gray, getTheme()));
        }
        Glide.with(this)
                .asBitmap()
                .load(queriedPerson.getImageUrl())
                .into(image);

        shareInfo_text1_tv.setText(queriedPerson.getShareInfotext1());
        shareInfo_text2_tv.setText(queriedPerson.getShareInfotext2());
        shareInfo_text3_tv.setText(queriedPerson.getShareInfotext3());

        fromImageUrlToCircleImage(queriedPerson.getShareInfoimageurl1(), shareInfo_image1_imgview);
        fromImageUrlToCircleImage(queriedPerson.getShareInfoimageurl2(), shareInfo_image2_imgview);
        fromImageUrlToCircleImage(queriedPerson.getShareInfoimageurl3(), shareInfo_image3_imgview);


//        이 부분은 마지막 결제일을 눌렀을 때 진행될 부분
        gotoUpdateText.setOnClickListener(this);//누르면 update액티비티로 넘어간다
        changeMemberShip_subinfo.setOnClickListener(this);
        dismissMemberShip_subinfo.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();

        dbHelper = new PersonDBHelper(this);
        try {
            //get intent to get person id
            receivedPersonId = getIntent().getLongExtra("USER_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Person queriedPerson = dbHelper.getPerson(receivedPersonId);
        //set field to this user data
        text_serviceName_info.setText(queriedPerson.getServiceName());
        text_typeOfMembership_info.setText(queriedPerson.getTypeOfMembership());
        text_price_info.setText(queriedPerson.getPrice());
        text_lastPaymentDate_info.setText(queriedPerson.getLastPaymentDate());
        moreDetail_info.setText(queriedPerson.getDetail());
        pushAlarmText.setText(queriedPerson.getSwitchCondition());
        Glide.with(this)
                .asBitmap()
                .load(queriedPerson.getImageUrl())
                .into(image);

        shareInfo_text1_tv.setText(queriedPerson.getShareInfotext1());
        shareInfo_text2_tv.setText(queriedPerson.getShareInfotext2());
        shareInfo_text3_tv.setText(queriedPerson.getShareInfotext3());

        fromImageUrlToCircleImage(queriedPerson.getShareInfoimageurl1(), shareInfo_image1_imgview);
        fromImageUrlToCircleImage(queriedPerson.getShareInfoimageurl2(), shareInfo_image2_imgview);
        fromImageUrlToCircleImage(queriedPerson.getShareInfoimageurl3(), shareInfo_image3_imgview);

    }

    private void fromImageUrlToCircleImage(String imageWebUrl, CircleImageView circleImageView) {
        Glide.with(this)
                .asBitmap()
                .load(imageWebUrl)
                .into(circleImageView);
    }

    private void setChangeMemberShip_subinfo(String myImageUrl, LinearLayout button) {
        if (myImageUrl.equals("https://i.ibb.co/LgSk92r/img-service-netflix.png")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://help.netflix.com/ko/node/22"));
            startActivity(i);//넷플릭스
        } else if (myImageUrl.equals("https://i.ibb.co/6vPFx8h/1.png")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/paid_memberships"));
            startActivity(i); //유튜브
        } else if (myImageUrl.equals("https://i.ibb.co/C913dgg/img-service-whatcha.png")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://watcha.com/webview/faqs?category=payment"));
            startActivity(i); //왓챠
        } else if (myImageUrl.equals("https://i.ibb.co/nQWtfpK/img-service-spotify.png")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://support.spotify.com/kr-ko/article/spotify-new-subscriber-offers/"));
            startActivity(i); //스포티파이
        } else if (myImageUrl.equals("https://i.ibb.co/pvtGQgZ/img-service-melon.png")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.melon.com/buy/pamphlet/all.htm"));
            startActivity(i); //멜론
        } else if (myImageUrl.equals("https://i.ibb.co/hm366pC/img-service-midium.png")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://help.medium.com/hc/en-us/articles/360006277374"));
            startActivity(i); //미디엄
        } else if (myImageUrl.equals("https://i.ibb.co/gDJbWMx/img-service-milli.png")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.millie.co.kr/customer/faq.html?open=176"));
            startActivity(i); //밀리의 서재
        } else if (myImageUrl.equals("https://i.ibb.co/02yWJ1W/img-service-ridibooks.png")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://help.ridibooks.com/hc/ko/articles/360001753288--%EB%A6%AC%EB%94%94%EC%85%80%EB%A0%89%ED%8A%B8-%EA%B5%AC%EB%8F%85%EC%8B%A0%EC%B2%AD%ED%95%9C-%EC%B9%B4%EB%93%9C%EC%A0%95%EB%B3%B4-%EB%B3%80%EA%B2%BD%ED%95%98%EA%B8%B0"));
            startActivity(i);//리디북스
        } else if (myImageUrl.equals("https://i.ibb.co/9y2n2V0/img-service-notion.png")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.notion.so/b0b47859d2c04c0a935469a481c22437#064c3013b5364aeeaf072d467f59c9f2"));
            startActivity(i);//노션
        } else if (myImageUrl.equals("https://i.ibb.co/pfMwZpD/img-service-illustrator.png")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://helpx.adobe.com/kr/manage-account/using/change-plan.html"));
            startActivity(i);//어도비 일러스트
        } else if (myImageUrl.equals("https://i.ibb.co/2sRgRvr/img-service-photoshop.png")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://helpx.adobe.com/kr/manage-account/using/change-plan.html"));
            startActivity(i);//어도비 포토샵
        } else if (myImageUrl.equals("https://i.ibb.co/bsyLq4j/img-service-coupang.png")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mc.coupang.com/ssr/desktop/contact/faq?keyword=%EB%A9%A4%EB%B2%84%EC%8B%AD"));
            startActivity(i);//쿠팡
        } else if (myImageUrl.equals("https://i.ibb.co/5YSKhK8/img-service-tving.png")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tving.com/faq/main.do#searchWord=%ED%95%B4%EC%A7%80"));
            startActivity(i);//티빙
        } else if (myImageUrl.equals("https://i.ibb.co/Qbsgg6q/img-service-laftel.png")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(" https://help.laftel.net/hc/ko/articles/360051433793-%EB%A9%A4%EB%B2%84%EC%8B%AD-%EA%B2%B0%EC%A0%9C-%EC%88%98%EB%8B%A8-%EB%B3%80%EA%B2%BD-%ED%95%98%EA%B3%A0-%EC%8B%B6%EC%96%B4%EC%9A%94"));
            startActivity(i);//라프텔
        }
        System.out.println("this is my image URL:" + myImageUrl);


    }

    private void setdismissMemberShip_subinfo(String myImageUrl, LinearLayout button) {
        if (myImageUrl.equals("https://i.ibb.co/LgSk92r/img-service-netflix.png")) {
            Intent i1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://help.netflix.com/ko/node/407"));
            startActivity(i1);//넷플릭스
        } else if (myImageUrl.equals("https://i.ibb.co/6vPFx8h/1.png")) {
            Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/paid_memberships"));
            startActivity(i2); //유튜브
        } else if (myImageUrl.equals("https://i.ibb.co/C913dgg/img-service-whatcha.png")) {
            Intent i3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://watcha.com/webview/faqs?category=cancel"));
            startActivity(i3); //왓챠
        } else if (myImageUrl.equals("https://i.ibb.co/nQWtfpK/img-service-spotify.png")) {
            Intent i4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://support.spotify.com/kr-ko/article/cancel-premium/"));
            startActivity(i4); //스포티파이
        } else if (myImageUrl.equals("https://i.ibb.co/pvtGQgZ/img-service-melon.png")) {
            Intent i5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://faqs2.melon.com/customer/faq/informFaq.htm?no=23&faqId=QUES20140619000019&orderChk=&SEARCH_KEY=&SEARCH_PAR_CATEGORY=CATE20130909000002&SEARCH_CATEGORY=CATE20130909000013"));
            startActivity(i5); //멜론
        } else if (myImageUrl.equals("https://i.ibb.co/hm366pC/img-service-midium.png")) {
            Intent i6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://help.medium.com/hc/en-us/articles/360006277374"));
            startActivity(i6); //미디엄
        } else if (myImageUrl.equals("https://i.ibb.co/gDJbWMx/img-service-milli.png")) {
            Intent i7 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.millie.co.kr/customer/faq.html?open=213"));
            startActivity(i7); //밀리의 서재
        } else if (myImageUrl.equals("https://i.ibb.co/02yWJ1W/img-service-ridibooks.png")) {
            Intent i8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://help.ridibooks.com/hc/ko/articles/360039999233--%EB%A6%AC%EB%94%94%EC%85%80%EB%A0%89%ED%8A%B8-%EB%93%B1%EB%A1%9D%ED%95%9C-%EC%9D%B4%EC%9A%A9%EA%B6%8C%EC%9D%84-%ED%95%B4%EC%A7%80-%EC%9D%BC%EC%8B%9C%EC%A4%91%EC%A7%80-%ED%95%98%EA%B3%A0-%EC%8B%B6%EC%8A%B5%EB%8B%88%EB%8B%A4-"));
            startActivity(i8);//리디북스
        } else if (myImageUrl.equals("https://i.ibb.co/9y2n2V0/img-service-notion.png")) {
            Intent i9 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.notion.so/b0b47859d2c04c0a935469a481c22437#064c3013b5364aeeaf072d467f59c9f2"));
            startActivity(i9);//노션
        } else if (myImageUrl.equals("https://i.ibb.co/pfMwZpD/img-service-illustrator.png")) {
            Intent i10 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://helpx.adobe.com/kr/manage-account/using/cancel-subscription.html"));
            startActivity(i10);//어도비 일러스트
        } else if (myImageUrl.equals("https://i.ibb.co/2sRgRvr/img-service-photoshop.png")) {
            Intent i11 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://helpx.adobe.com/kr/manage-account/using/cancel-subscription.html"));
            startActivity(i11);//어도비 포토샵
        } else if (myImageUrl.equals("https://i.ibb.co/bsyLq4j/img-service-coupang.png")) {
            Intent i12 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mc.coupang.com/ssr/desktop/contact/faq?keyword=%EB%A9%A4%EB%B2%84%EC%8B%AD"));
            startActivity(i12);//쿠팡
        } else if (myImageUrl.equals("https://i.ibb.co/5YSKhK8/img-service-tving.png")) {
            Intent i13 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tving.com/faq/main.do#searchWord=%ED%95%B4%EC%A7%80"));
            startActivity(i13);//티빙
        } else if (myImageUrl.equals("https://i.ibb.co/Qbsgg6q/img-service-laftel.png")) {
            Intent i14 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://help.laftel.net/hc/ko/articles/360050869313-%EB%A9%A4%EB%B2%84%EC%8B%AD-%EC%A0%95%EA%B8%B0%EA%B2%B0%EC%A0%9C-%ED%95%B4%EC%A7%80-%ED%95%98%EA%B3%A0-%EC%8B%B6%EC%96%B4%EC%9A%94"));
            startActivity(i14);//라프텔
        }


    }


    private void goToUpdateActivity(long personId) {
        Intent goToUpdateactivity = new Intent(getApplicationContext(), UpdateActivity.class);
        goToUpdateactivity.putExtra("USER_ID", personId);
        startActivity(goToUpdateactivity);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gotoupdateText:
                goToUpdateActivity(receivedPersonId);
                break;
        }
        switch (view.getId()) {
            case R.id.changeMemberShip_subinfo:
                setChangeMemberShip_subinfo(dbHelper.getPerson(receivedPersonId).getImageUrl(), changeMemberShip_subinfo);
                break;
        }
        switch (view.getId()) {
            case R.id.dismissMemberShip_subinfo:
                setdismissMemberShip_subinfo(dbHelper.getPerson(receivedPersonId).getImageUrl(), dismissMemberShip_subinfo);
                break;
        }


    }

}