/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

/**
 *
 * @author DEMO-OK
 */
public class ShopDetails {
   
    private String shop_Name;
    private String shop_Address; 
    private String d_l_no;
    private String gst_Tin_No;
    private String mobile_No;
    public ShopDetails() {
    }

    public ShopDetails(String shop_Name, String shop_Address, String d_l_no, String gst_Tin_No, String mobile_No) {
        this.shop_Name = shop_Name;
        this.shop_Address = shop_Address;
        this.d_l_no = d_l_no;
        this.gst_Tin_No = gst_Tin_No;
        this.mobile_No = mobile_No;
    }


    public String getShop_Name() {
        return shop_Name;
    }

    public void setShop_Name(String shop_Name) {
        this.shop_Name = shop_Name;
    }

    public String getShop_Address() {
        return shop_Address;
    }

    public void setShop_Address(String shop_Address) {
        this.shop_Address = shop_Address;
    }

    public String getD_l_no() {
        return d_l_no;
    }

    public void setD_l_no(String d_l_no) {
        this.d_l_no = d_l_no;
    }

    public String getGst_Tin_No() {
        return gst_Tin_No;
    }

    public void setGst_Tin_No(String gst_Tin_No) {
        this.gst_Tin_No = gst_Tin_No;
    }

    @Override
    public String toString() {
        return "ShopDetails{" + "shop_Name=" + shop_Name + ", shop_Address=" + shop_Address + ", d_l_no=" + d_l_no + ", gst_Tin_No=" + gst_Tin_No + '}';
    }

    public String getMobile_No() {
        return mobile_No;
    }

    public void setMobile_No(String mobile_No) {
        this.mobile_No = mobile_No;
    }
    

 

}
