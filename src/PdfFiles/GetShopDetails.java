/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PdfFiles;

import Pojo.ShopDetails;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DEMO-OK
 */
public class GetShopDetails {

    public ShopDetails get_Shop_Info() {
        ShopDetails shopInfo = new ShopDetails();
        ArrayList<String> inputData = new ArrayList<>();
        try {
            Scanner in = new Scanner(new BufferedReader(new FileReader("shop.txt")));
            while (in.hasNextLine()) {
                String line = in.nextLine().trim();
                if (!line.isEmpty()) // Ignore blank lines
                {
                    inputData.add(line);
                }
            }
        } catch (Exception e) {

            System.out.println("" + e);
        }

        shopInfo.setShop_Name(inputData.get(0));
        shopInfo.setShop_Address(inputData.get(1));
        shopInfo.setD_l_no(inputData.get(2));
        shopInfo.setGst_Tin_No(inputData.get(3));
        shopInfo.setMobile_No(inputData.get(4));

        return shopInfo;
    }

    public static void main(String[] args) {

        GetShopDetails details = new GetShopDetails();

        ShopDetails info = details.get_Shop_Info();

        System.out.println("Shop Name : " + info.getShop_Name());
        System.out.println("Shop Address : " + info.getShop_Address());
        System.out.println("Shop DLno : " + info.getD_l_no());
        System.out.println("Shop CSTNo : " + info.getGst_Tin_No());
        System.out.println("Shop MobileNO : " + info.getMobile_No());

    }
}
