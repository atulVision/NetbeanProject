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
public class ProductDetails {

    private String bill_no;
    private String date;
    private String customer_name;
    private String customer_address;
    private String dr_name;
    private String dr_address;

  
    private String product_info;

    public ProductDetails() {
    }

    public ProductDetails(String bill_no, String date, String customer_name, String customer_address, String dr_name, String dr_address, String product_info) {
        this.bill_no = bill_no;
        this.date = date;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.dr_name = dr_name;
        this.dr_address = dr_address;
        this.product_info = product_info;
    }

    public String getBill_no() {
        return bill_no;
    }

    public void setBill_no(String bill_no) {
        this.bill_no = bill_no;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getDr_name() {
        return dr_name;
    }

    public void setDr_name(String dr_name) {
        this.dr_name = dr_name;
    }

    public String getDr_address() {
        return dr_address;
    }

    public void setDr_address(String dr_address) {
        this.dr_address = dr_address;
    }

    public String getProduct_info() {
        return product_info;
    }

    public void setProduct_info(String product_info) {
        this.product_info = product_info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
