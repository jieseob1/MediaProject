package com.example.mediaproject.add.model;

/**
 * Created by Ronsoft on 11/13/2017.
 */
// et_serviceName = (EditText) findViewById(R.id.et_serviceName);
//        et_typeOfMembership = (EditText) findViewById(R.id.et_typeOfMembership);
//        et_price = (EditText) findViewById(R.id.et_price);
//        et_lastPaymentDate = (EditText) findViewById(R.id.et_lastPaymentDate);
//        moreDetail = (EditText) findViewById(R.id.moreDetail);
public class Person {

    private long id;
    private String serviceName;
    private String price;
    private String typeOfMembership;
    private String lastPaymentDate;
    private String detail;
    private String imageUrl;
    private String switchCondition;

    private String shareInfoimageurl1,shareInfoimageurl2,shareInfoimageurl3;
    private String shareInfotext1,shareInfotext2,shareInfotext3;
    private String nextMonthPaymentDate;
    private String diffPaymentDate;

//    공유하는 text,image 넣을것, switchconditon,남은날짜와 다음달 날짜

    public Person() {
    }

    public Person(String serviceName, String price, String typeOfMembership,String lastPaymentDate,String detail, String image,
                  String switchCondition,String nextMonthPaymentDate,String diffPaymentDate,String shareInfoimageurl1,
                  String shareInfoimageurl2,String shareInfoimageurl3,String shareInfotext1,String shareInfotext2,String shareInfotext3) {
        this.serviceName = serviceName;
        this.price = price;
        this.typeOfMembership = typeOfMembership;
        this.lastPaymentDate = lastPaymentDate;
        this.detail = detail;
        this.imageUrl = image;
        this.switchCondition = switchCondition;
        this.nextMonthPaymentDate = nextMonthPaymentDate;
        this.diffPaymentDate = diffPaymentDate;
        this.shareInfoimageurl1 = shareInfoimageurl1;
        this.shareInfoimageurl2 = shareInfoimageurl2;
        this.shareInfoimageurl3 = shareInfoimageurl3;
        this.shareInfotext1 = shareInfotext1;
        this.shareInfotext2 = shareInfotext2;
        this.shareInfotext3 = shareInfotext3;

    }


    public String getShareInfoimageurl1() {
        return shareInfoimageurl1;
    }

    public void setShareInfoimageurl1(String shareInfoimageurl1) {
        this.shareInfoimageurl1 = shareInfoimageurl1;
    }

    public String getShareInfoimageurl2() {
        return shareInfoimageurl2;
    }

    public void setShareInfoimageurl2(String shareInfoimageurl2) {
        this.shareInfoimageurl2 = shareInfoimageurl2;
    }

    public String getShareInfoimageurl3() {
        return shareInfoimageurl3;
    }

    public void setShareInfoimageurl3(String shareInfoimageurl3) {
        this.shareInfoimageurl3 = shareInfoimageurl3;
    }

    public String getShareInfotext1() {
        return shareInfotext1;
    }

    public void setShareInfotext1(String shareInfotext1) {
        this.shareInfotext1 = shareInfotext1;
    }

    public String getShareInfotext2() {
        return shareInfotext2;
    }

    public void setShareInfotext2(String shareInfotext2) {
        this.shareInfotext2 = shareInfotext2;
    }

    public String getShareInfotext3() {
        return shareInfotext3;
    }

    public void setShareInfotext3(String shareInfotext3) {
        this.shareInfotext3 = shareInfotext3;
    }

    public String getNextMonthPaymentDate() {
        return nextMonthPaymentDate;
    }

    public void setNextMonthPaymentDate(String nextMonthPaymentDate) {
        this.nextMonthPaymentDate = nextMonthPaymentDate;
    }

    public String getDiffPaymentDate() {
        return diffPaymentDate;
    }

    public void setDiffPaymentDate(String diffPaymentDate) {
        this.diffPaymentDate = diffPaymentDate;
    }

    public String getSwitchCondition() {
        return switchCondition;
    }

    public void setSwitchCondition(String switchCondition) {
        this.switchCondition = switchCondition;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTypeOfMembership() {
        return typeOfMembership;
    }

    public void setTypeOfMembership(String typeOfMembership) {
        this.typeOfMembership = typeOfMembership;
    }

    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
