package com.foodie.app.entities;

import android.content.ContentValues;
import android.net.Uri;

import com.foodie.app.backend.AppContract;

import java.io.Serializable;


public class Business implements Serializable {

    private static final long serialVersionUID = 2L;


    private static int businessID = 0;

    private int _ID;

    private String businessName;

    private String businessAddress;

    private String businessPhoneNo;

    private String businessEmail;

    private String businessWebsite;

    private int cpuserID;

    private byte[] businessLogo;

    //Empty constructor.
    public Business() {

        _ID = 0;
        businessName = "New Business";
        businessAddress = "";
        businessPhoneNo = "";
        businessEmail = "";
        businessWebsite = "";
        cpuserID = 0;
        businessLogo = null;
    }
//Full constructor
    public Business(String businessName, String businessAddress, String businessPhoneNo, String businessEmail, String businessWebsite, int cpuserID, byte[] businessLogo) throws Exception {
        set_ID(businessID + 1);
        businessID++;
        setBusinessName(businessName);
        setBusinessAddress(businessAddress);
        setBusinessPhoneNo(businessPhoneNo);
        setBusinessEmail(businessEmail);
        setBusinessWebsite(businessWebsite);
        setCpuserID(cpuserID);
        setBusinessLogo(businessLogo);
    }

    public Business(int id,String businessName, String businessAddress, String businessPhoneNo, String businessEmail, String businessWebsite, int cpuserID, byte[] businessLogo) throws Exception {
        set_ID(id);
        setBusinessName(businessName);
        setBusinessAddress(businessAddress);
        setBusinessPhoneNo(businessPhoneNo);
        setBusinessEmail(businessEmail);
        setBusinessWebsite(businessWebsite);
        setCpuserID(cpuserID);
        setBusinessLogo(businessLogo);
    }

    public int get_ID() {
        return _ID;
    }

    public void set_ID(int Id) {
        _ID = Id;
    }

    public String getBusinessName() {
        return businessName;

    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }


    public String getBusinessPhoneNo() {
        return businessPhoneNo;
    }

    public void setBusinessPhoneNo(String businessPhoneNo) {
        this.businessPhoneNo = businessPhoneNo;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getBusinessWebsite() {
        return businessWebsite;
    }

    public void setBusinessWebsite(String businessWebsite) {
        this.businessWebsite = businessWebsite;
    }

    public int getCpuserID() {
        return cpuserID;
    }

    public void setCpuserID(int cpuserID) {
        this.cpuserID = cpuserID;
    }

    public byte[] getBusinessLogo() {
        return businessLogo;
    }

    public void setBusinessLogo(byte[] businessLogo) {
        this.businessLogo = businessLogo;
    }



    public ContentValues toContentValues() {
        //    public Business(int id,String businessName, String businessAddress, String businessPhoneNo, String businessEmail, String businessWebsite, int cpuserID, byte[] businessLogo) throws Exception {


        final ContentValues contentValues = new ContentValues();

        contentValues.put(AppContract.Business.BUSINESS_ID, this.get_ID());
        contentValues.put(AppContract.Business.BUSINESS_NAME, this.getBusinessName());
        contentValues.put(AppContract.Business.BUSINESS_ADDRESS, this.getBusinessAddress());
        contentValues.put(AppContract.Business.BUSINESS_PHONE_NUMBER, this.getBusinessPhoneNo());
        contentValues.put(AppContract.Business.BUSINESS_EMAIL, this.getBusinessEmail());
        contentValues.put(AppContract.Business.BUSINESS_WEBSITE, this.getBusinessWebsite());
        contentValues.put(AppContract.Business.BUSINESS_CPUSER_ID, this.getCpuserID());
        contentValues.put(AppContract.Business.BUSINESS_LOGO, this.getBusinessLogo());


        return contentValues;
    }

    public static Uri getURI() {
        return Uri.parse("content://com.foodie.app/Business");
    }
}
