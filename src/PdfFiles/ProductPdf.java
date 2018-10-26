/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PdfFiles;

//import FirstPdf.saleProduct1;
//import GetShopDetails;
import Pojo.ProductDetails;
import Pojo.ShopDetails;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.LargeElement;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.awt.Canvas;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.DefaultEditorKit;

//import org.apache.commons.lang3.StringUtils;
public class ProductPdf {

    private static String FILE = "";

    private static Font smallfont = new Font(Font.FontFamily.HELVETICA, 10,
            Font.NORMAL);
    private static Font smallfont3 = new Font(Font.FontFamily.HELVETICA, 12,
            Font.NORMAL);
    private static Font smallfontBold = new Font(Font.FontFamily.HELVETICA, 10,
            Font.BOLD);
    private static Font smallfont1 = new Font(Font.FontFamily.HELVETICA, 8,
            Font.NORMAL);
    private static Font Heading = new Font(Font.FontFamily.TIMES_ROMAN, 22,
            Font.BOLD);

    static ProductDetails productInfo = null;

    public ProductPdf() {
    }

    public ProductPdf(ProductDetails saleProduct) throws FileNotFoundException, DocumentException, IOException {

        FILE = System.getProperty("user.dir") + "\\CustomerBills\\" + saleProduct.getBill_no() + ".pdf";
        File f = new File(FILE);

        Document document = new Document();
        document.setPageSize(PageSize.A5);
//        document.setMargins(10, 10,35, 35);
        productInfo = saleProduct;
        //  new SplitRowAtEndOfPage().manipulatePdf(FILE);
        PdfWriter docWriter = PdfWriter.getInstance(document, new FileOutputStream(FILE));

        document.open();

//     document.setPageSize(new com.itextpdf.text.Rectangle(0, 0));
        // addMetaData(document);
        addTitlePage(document, docWriter);

        //addContent(document);
        document.close();
    }

    private static void addTitlePage(Document document, PdfWriter docWriter)
            throws DocumentException, BadElementException, IOException {

        Chunk chunk = new Chunk(new VerticalPositionMark());
        GetShopDetails gsd = new GetShopDetails();
        ShopDetails shopInfo = gsd.get_Shop_Info();

        PdfPTable head = new PdfPTable(1);
        head.setWidthPercentage(100);

        Paragraph pShopInfo = new Paragraph();

        pShopInfo.setAlignment(Element.ALIGN_CENTER);
        pShopInfo.add(new Paragraph(10, "''Om Sai Ram''", smallfontBold));
//        pShopInfo.add(Chunk.NEWLINE);
        pShopInfo.add(new Paragraph(35, shopInfo.getShop_Name(), Heading));
//        pShopInfo.add(Chunk.NEWLINE);
        pShopInfo.add(new Paragraph(10, shopInfo.getShop_Address(), smallfont));
//        pShopInfo.add(Chunk.NEWLINE);
        PdfPCell c1 = new PdfPCell();
        c1.addElement(pShopInfo);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(PdfPCell.NO_BORDER);
        head.addCell(c1);
        Paragraph dlno = new Paragraph();
        dlno.add(new Paragraph(10, "D.L.No.: " + shopInfo.getD_l_no(), smallfont));
        dlno.setSpacingAfter(-5);
        dlno.setAlignment(Element.ALIGN_LEFT);
        PdfPTable cstno = new PdfPTable(2);
        cstno.setWidthPercentage(100);
        Paragraph pcstno = new Paragraph();
        pcstno.add(new Paragraph(10, "GSTINO :" + shopInfo.getGst_Tin_No(), smallfont));
        pcstno.setAlignment(Element.ALIGN_LEFT);
        Paragraph pmobileno = new Paragraph();
        pmobileno.add(new Paragraph(10, "Mobile No :" + shopInfo.getMobile_No(), smallfont));
        pmobileno.setAlignment(Element.ALIGN_RIGHT);
        c1 = new PdfPCell();
        c1.addElement(pcstno);
        c1.setBorder(PdfPCell.NO_BORDER);
        cstno.addCell(c1);
        c1 = new PdfPCell();
        c1.addElement(pmobileno);
        c1.setBorder(PdfPCell.NO_BORDER);
        cstno.addCell(c1);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////2nd PART
        PdfPTable customerinfo = new PdfPTable(2);
        customerinfo.setWidthPercentage(100);
        Paragraph customerInfo = new Paragraph();
        customerInfo.add(new Paragraph(5, "Bill No: " + productInfo.getBill_no(), smallfont));
        customerInfo.add(new Paragraph(5, "Customer Name : " + productInfo.getCustomer_name(), smallfont));
//        customerInfo.add(Chunk.NEWLINE);
        customerInfo.add(new Paragraph(10, "Customer Address : " + productInfo.getCustomer_address(), smallfont));
//        customerInfo.add(Chunk.NEWLINE);
//        customerInfo.add(Chunk.NEWLINE);
        customerInfo.setAlignment(Element.ALIGN_LEFT);

        Paragraph drinfo = new Paragraph();
        drinfo.add(new Paragraph(10, "Date : " + productInfo.getDate(), smallfont));
        drinfo.add(new Paragraph(10, "Dr Name : " + productInfo.getDr_name(), smallfont));
//        drinfo.add(Chunk.NEWLINE);
        drinfo.add(new Paragraph(10, "Dr Address : " + productInfo.getDr_address(), smallfont));
        drinfo.setAlignment(Element.ALIGN_RIGHT);

//        drinfo.add(Chunk.NEWLINE);
//        drinfo.add(Chunk.NEWLINE);
        c1 = new PdfPCell(customerInfo);
        c1.setBorder(PdfPCell.NO_BORDER);
        customerinfo.addCell(c1);
        c1 = new PdfPCell(drinfo);
        c1.setBorder(PdfPCell.NO_BORDER);
        customerinfo.addCell(c1);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Paragraph heading = new Paragraph();
//        DottedLineSeparator dottedline = new DottedLineSeparator();
//         dottedline.setOffset(-2);
//        dottedline.setGap(2f);
        Paragraph line = new Paragraph();
//        line.setSpacingAfter(-20);
        LineSeparator line1 = new LineSeparator();
        line1.setPercentage(120);
        line1.setOffset(-5);
        line.add(line1);
        Paragraph linebottom = new Paragraph();
        LineSeparator line11 = new LineSeparator();
        line11.setPercentage(120);
        line11.setOffset(+10);
//          line11.setOffset(+2);
        linebottom.add(line11);
//  heading.add(dottedline);

        heading.setSpacingBefore(-20);
        heading.setSpacingAfter(-10);
        heading.add(line);
        heading.add(new Paragraph(10, "Sr.No          Product Name                Qty        Unit          MRP       Total Amount", smallfont));
        heading.add(linebottom);
//        heading.add(c);
        PdfPTable table = new PdfPTable(new float[]{10, 30, 10, 10, 15, 15});
        table.setWidthPercentage(100);

        String allItemDetails[] = productInfo.getProduct_info().trim().split(":");

        int l = allItemDetails.length;
        int totalQuantity = 0;
        int total = 0;
        int finaltotalamount = 0;

        if (allItemDetails[0].equals("")) {

        } else {
            if (l <= 21) {
                for (int i = 0; i < allItemDetails.length; i++) {
//                for (String listOfChunk1 : listOfChunk) {
                    String[] itemDetail = allItemDetails[i].split(",");
//                   
                    PdfPCell f1 = new PdfPCell();
                    f1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    pShopInfo.setLeading(15, 0);
                    f1.addElement(pShopInfo);
//                    f1.setFixedHeight(150);

                    f1.setBorder(PdfPCell.NO_BORDER);
//                    f1.setBorder(Rectangle.OUT_TOP);

                    f1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    f1 = new PdfPCell(new Phrase(itemDetail[0].trim(), smallfont));
                    f1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    f1.setBorder(PdfPCell.NO_BORDER);
//                    f1.setBorder(Rectangle.OUT_RIGHT | Rectangle.OUT_BOTTOM);
                    table.addCell(f1);

                    f1 = new PdfPCell(new Phrase(itemDetail[1].trim(), smallfont));
                    f1.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    f1.setBorder(Rectangle.OUT_BOTTOM);
                    f1.setBorder(PdfPCell.NO_BORDER);
                    table.addCell(f1);

                    f1 = new PdfPCell(new Phrase(itemDetail[2], smallfont));
                    f1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    f1.setBorder(PdfPCell.NO_BORDER);
//                    f1.setBorder(Rectangle.OUT_BOTTOM);
                    table.addCell(f1);

                    f1 = new PdfPCell(new Phrase(itemDetail[3], smallfont));
//                    
                    f1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    f1.setBorder(PdfPCell.NO_BORDER);
//                    f1.setBorder(Rectangle.OUT_BOTTOM);
                    table.addCell(f1);

                    f1 = new PdfPCell(new Phrase(itemDetail[4], smallfont));
                    f1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    f1.setBorder(PdfPCell.NO_BORDER);
//                    f1.setBorder(Rectangle.OUT_BOTTOM);
                    table.addCell(f1);

                    f1 = new PdfPCell(new Phrase(itemDetail[5], smallfont));
                    totalQuantity = totalQuantity + Integer.parseInt(itemDetail[5]);
                    f1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    f1.setBorder(PdfPCell.NO_BORDER);
//                    f1.setBorder(Rectangle.OUT_BOTTOM);
                    table.addCell(f1);
                }

                Paragraph ptotal = new Paragraph();
                ptotal.setSpacingBefore(-20);
                ptotal.setSpacingAfter(-15);
                ptotal.add(line);
                ptotal.add(new Paragraph(10, "Total : " + String.valueOf(totalQuantity), smallfont));
                ptotal.setAlignment(Element.ALIGN_RIGHT);
                ptotal.add(linebottom);

                Paragraph finalamount1 = new Paragraph();
//                finalamount1.add(Chunk.NEWLINE);
                finalamount1.add(new Paragraph(10, "Final Amount : " + totalQuantity, smallfont3));
                finalamount1.setAlignment(Element.ALIGN_RIGHT);
                Paragraph pamountinword = new Paragraph();
                WordAmount word = new WordAmount();
                String amountinword = word.convertNumberToWords(totalQuantity);
                pamountinword.add(new Paragraph(10, "Amount In Word : " + amountinword + " Only", smallfont3));
                pamountinword.setAlignment(Element.ALIGN_LEFT);
//                pamountinword.add(Chunk.NEWLINE);
                Paragraph debitcredit = new Paragraph();
//                debitcredit.setSpacingAfter(+5);
//                debitcredit.setSpacingBefore(-10);
                debitcredit.add(new Paragraph(10, "Debit & credit Card Accpted : ", smallfont1));
                debitcredit.setAlignment(Element.ALIGN_CENTER);
                Paragraph sing = new Paragraph();
                sing.add(new Paragraph(10, "Signature: ", smallfont3));
                sing.setAlignment(Element.ALIGN_RIGHT);
                document.add(head);
                document.add(dlno);
                document.add(cstno);
                document.add(customerinfo);
                document.add(heading);
                document.add(table);
                document.add(ptotal);
                document.add(finalamount1);
                document.add(pamountinword);
                document.add(debitcredit);
                document.add(sing);

//                document.setMargins(DefaultPdfDocumentSettings.LEFT_MARGIN, DefaultPdfDocumentSettings.RIGHT_MARGIN, 0f, 0f);
            } else {
                java.util.List<java.util.List<String>> listOfChunks = createBatch(get(productInfo.getProduct_info()), 23);
                int size = 0;
                int check = 0;
                int totalAmount = 0;
                String amount[] = new String[listOfChunks.size()];
                for (java.util.List<String> listOfChunk : listOfChunks) {
                    //fist here  
                    size = listOfChunk.size();

                    for (String listOfChunk1 : listOfChunk) {
                        String[] itemDetail = listOfChunk1.split(",");
                        PdfPCell f1 = new PdfPCell();
                        f1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        pShopInfo.setLeading(15, 0);
                        f1.addElement(pShopInfo);
//                   

//                f1.setBorder(PdfPCell.NO_BORDER);
                        f1.setBorder(Rectangle.OUT_TOP);

                        f1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        f1 = new PdfPCell(new Phrase(itemDetail[0].trim(), smallfont));
                        f1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        f1.setBorder(PdfPCell.NO_BORDER);
//                        f1.setBorder(Rectangle.OUT_RIGHT | Rectangle.OUT_BOTTOM);
                        table.addCell(f1);

                        f1 = new PdfPCell(new Phrase(itemDetail[1].trim(), smallfont));
                        f1.setHorizontalAlignment(Element.ALIGN_LEFT);
//                        f1.setBorder(Rectangle.OUT_BOTTOM);
                        f1.setBorder(PdfPCell.NO_BORDER);
                        table.addCell(f1);

                        f1 = new PdfPCell(new Phrase(itemDetail[2], smallfont));
                        f1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        f1.setBorder(PdfPCell.NO_BORDER);
//                        f1.setBorder(Rectangle.OUT_BOTTOM);
                        table.addCell(f1);

                        f1 = new PdfPCell(new Phrase(itemDetail[3], smallfont));
//                    
                        f1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        f1.setBorder(PdfPCell.NO_BORDER);
//                        f1.setBorder(Rectangle.OUT_BOTTOM);
                        table.addCell(f1);

                        f1 = new PdfPCell(new Phrase(itemDetail[4], smallfont));
                        f1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        f1.setBorder(PdfPCell.NO_BORDER);
//                        f1.setBorder(Rectangle.OUT_BOTTOM);
                        table.addCell(f1);

                        f1 = new PdfPCell(new Phrase(itemDetail[5], smallfont));
                        totalAmount = totalAmount + Integer.parseInt(itemDetail[5]);
                        f1.setHorizontalAlignment(Element.ALIGN_CENTER);
//                        f1.setBorder(Rectangle.OUT_BOTTOM);
                        f1.setBorder(PdfPCell.NO_BORDER);
                        table.addCell(f1);

                    }
                    System.out.println("come here first time");
                    if (size == 23) {
                        amount[check] = String.valueOf(totalAmount);
                        System.out.println("size is 19");

                        Paragraph ptotal = new Paragraph();
                        ptotal.setSpacingBefore(-20);
                        ptotal.setSpacingAfter(+7);
                        ptotal.add(line);
                        ptotal.add(new Paragraph(10, "Total : " + String.valueOf(totalAmount), smallfont));
                        ptotal.setAlignment(Element.ALIGN_RIGHT);
                        ptotal.add(linebottom);

                        Paragraph p = new Paragraph();
//                        p.setSpacingBefore(10);
//                      p.setSpacingAfter(50);
                        int pagecount = check;
//                          p.add(Chunk.NEWLINE);
                        p.add(new Paragraph(10, "Page: " + ++pagecount + "  Continue on NextPage", smallfont));

                        p.setAlignment(Element.ALIGN_CENTER);
                        document.add(head);
                        document.add(dlno);
                        document.add(cstno);
                        document.add(customerinfo);
                        document.add(heading);
                        document.add(table);
                        document.add(ptotal);

                        table.deleteBodyRows();
                        check = check + 1;
                        int total1 = 0;
                        if (amount.length == check) {
//                            if (amount.length == 1) {

//                                Paragraph pamountinword = new Paragraph();
//                                WordAmount word = new WordAmount();
//                                String amountinword = word.convertNumberToWords(totalAmount);
//                                pamountinword.add(new Paragraph(10, "Amount In Word : " + amountinword + " Only", smallfont3));
//                                pamountinword.setAlignment(Element.ALIGN_LEFT);
////                                pamountinword.add(Chunk.NEWLINE);
//                                Paragraph debitcredit = new Paragraph();
//                                debitcredit.add(new Paragraph(10, "Debit & credit Card Accpted : ", smallfont1));
//                                debitcredit.setAlignment(Element.ALIGN_CENTER);
//                                Paragraph sing = new Paragraph();
//                                sing.add(new Paragraph(10, "Signature: ", smallfont3));
//                                sing.setAlignment(Element.ALIGN_RIGHT);
//                                document.add(pamountinword);
//                                document.add(debitcredit);
//                                document.add(sing);
//                            } else {
                                Paragraph finalamount = new Paragraph();
//                            finalamount.add(Chunk.NEWLINE);

                                for (int i = 0; i < amount.length; i++) {
                                    finalamount.add("Total" + String.valueOf(i + 1) + " : ");
                                    finalamount.add(amount[i] + "       ");
                                    total1 = total1 + Integer.parseInt(amount[i]);
                                }

//                            finalamount.add(Chunk.NEWLINE);
//                            finalamount.add(Chunk.NEWLINE);
                                Paragraph pfinalamount = new Paragraph();
                                pfinalamount.add(new Paragraph(10, "Final Amount : " + total1, smallfont3));
                                pfinalamount.setAlignment(Element.ALIGN_RIGHT);
                                Paragraph pamountinword = new Paragraph();
                                WordAmount word = new WordAmount();
                                String amountinword = word.convertNumberToWords(total1);
                                pamountinword.add(new Paragraph(10, "Amount In Word : " + amountinword + " Only", smallfont3));
                                pamountinword.setAlignment(Element.ALIGN_LEFT);
                                pamountinword.add(Chunk.NEWLINE);
                                Paragraph debitcredit = new Paragraph();
                                debitcredit.add(new Paragraph(10, "Debit & credit Card Accpted : ", smallfont1));
                                debitcredit.setAlignment(Element.ALIGN_CENTER);
                                Paragraph sing = new Paragraph();
                                sing.add(new Paragraph(10, "Signature: ", smallfont3));
                                sing.setAlignment(Element.ALIGN_RIGHT);

                                document.add(pfinalamount);
                                document.add(pamountinword);
                                document.add(debitcredit);
                                document.add(sing);
//                            }
                        } else {
                            document.add(p);
                        }

                        totalAmount = 0;

//                    }
                    } else {

                        amount[check] = String.valueOf(totalAmount);
                        System.out.println("else part");

                        Paragraph ptotal = new Paragraph();
                        ptotal.setSpacingBefore(-20);
                        ptotal.setSpacingAfter(-15);
                        ptotal.add(line);
                        ptotal.add(new Paragraph(10, "Total : " + String.valueOf(totalAmount), smallfont3));
                        ptotal.setAlignment(Element.ALIGN_RIGHT);
                        ptotal.add(linebottom);
                        Paragraph p = new Paragraph();
                        int l1 = amount.length;
                        Paragraph finalamount = new Paragraph();
                        finalamount.add(Chunk.NEWLINE);
                        int total1 = 0;
                        for (int i = 0; i < amount.length; i++) {

                            finalamount.add("Total" + String.valueOf(i + 1) + " : ");
                            finalamount.add(amount[i] + "            ");
                            total1 = total1 + Integer.parseInt(amount[i]);

                        }
//                        finalamount.add(Chunk.NEWLINE);
//                        finalamount.add(Chunk.NEWLINE);
                        Paragraph pfinalamount = new Paragraph();
                        pfinalamount.add(new Paragraph(10, "Final Amount : " + total1, smallfont3));
                        pfinalamount.setAlignment(Element.ALIGN_RIGHT);
                        Paragraph pamountinword = new Paragraph();
                        WordAmount word = new WordAmount();
                        String amountinword = word.convertNumberToWords(total1);
                        pamountinword.add(new Paragraph(10, "Amount In Word : " + amountinword + " Only", smallfont3));
                        pamountinword.setAlignment(Element.ALIGN_LEFT);
//                        pamountinword.add(Chunk.NEWLINE);
                        Paragraph debitcredit = new Paragraph();
                        debitcredit.add(new Paragraph(10, "Debit & credit Card Accpted : ", smallfont1));
                        debitcredit.setAlignment(Element.ALIGN_CENTER);
                        Paragraph sing = new Paragraph();
                        sing.add(new Paragraph(10, "Signature: ", smallfont3));
                        sing.setAlignment(Element.ALIGN_RIGHT);
                        document.add(head);
                        document.add(dlno);
                        document.add(cstno);
                        document.add(customerinfo);
                        document.add(heading);
                        document.add(table);
                        document.add(ptotal);
                        document.add(finalamount);
                        document.add(pfinalamount);
                        document.add(pamountinword);
                        document.add(debitcredit);
                        document.add(sing);
                    }

                }

            }

        }

//        document.add(ppt1);
//        document.add(totalinWords);
//        document.add(ppt2);
//        document.add(p12);
        document.close();

    }

    public static void main(String[] args) throws FileNotFoundException, DocumentException, IOException {
        ProductDetails product = new ProductDetails();
        product.setBill_no("111");
        String temp = "150";
        product.setProduct_info("1,ffffa,10,Nos,100,1000:2,ffffa,10,Nos,100,1000:3,ffffa,10,Nos,100,1000:4,ffffa,10,Nos,100,1000:5,ffffa,10,Nos,100,1000:6,ffffa,10,Nos,100,1000:7,ffffa,10,Nos,100,1000:8,ffffa,10,Nos,100,1000:9,ffffa,10,Nos,100,1000:10,ffffa,10,Nos,100,1000:11,ffffa,10,Nos,100,1000:12,ffffa,10,Nos,100,1000:13,ffffa,10,Nos,100,1000:14,ffffa,10,Nos,100,1000:15,ffffa,10,Nos,100,1000:16,ffffa,10,Nos,100,1000:17,ffffa,10,Nos,100,1000:18,ffffa,10,Nos,100,1000:19,ffffa,10,Nos,100,1000:20,ffffa,10,Nos,100,1000:21,ffffa,10,Nos,100,1000:22,ffffa,10,Nos,100,1000:23,ffffa,10,Nos,100,1000:24,ffffa,10,Nos,100,1000:25,ffffa,10,Nos,100,1000:26,ffffa,10,Nos,100,1000:27,ffffa,10,Nos,100,1000:28,ffffa,10,Nos,100,1000:29,ffffa,10,Nos,100,1000:30,ffffa,10,Nos,100,1000:31,ffffa,10,Nos,100,1000:32,ffffa,10,Nos,100,1000:33,ffffa,10,Nos,100,1000:34,ffffa,10,Nos,100,1000:35,ffffa,10,Nos,100,1000:36,ffffa,10,Nos,100,1000:37,ffffa,10,Nos,100,1000:38,ffffa,10,Nos,100,1000:39,ffffa,10,Nos,100,1000:40,ffffa,10,Nos,100,1000:41,ffffa,10,Nos,100,1000:42,ffffa,10,Nos,100,1000:43,ffffa,10,Nos,100,1000:44,ffffa,10,Nos,100,1000:45,ffffa,10,Nos,100,1000:46,ffffa,10,Nos,100,1000:47,ffffa,10,Nos,100,1000:48,ffffa,10,Nos,100,1000:49,ffffa,10,Nos,100,1000:50,ffffa,10,Nos,100,1000:51,ffffa,10,Nos,100,1000:52,ffffa,10,Nos,100,1000:53,ffffa,10,Nos,100,1000:54,ffffa,10,Nos,100,1000:55,ffffa,10,Nos,100,1000:56,ffffa,10,Nos,100,1000:57,ffffa,10,Nos,100,1000:57,ffffa,10,Nos,100,1000:57,ffffa,10,Nos,100,1000:57,ffffa,10,Nos,100,1000:57,ffffa,10,Nos,100,1000:57,ffffa,10,Nos,100,1000:57,ffffa,10,Nos,100,1000:57,ffffa,10,Nos,100,1000:");
//        product.setProduct_info("1,ffffa,10,Nos,100,1000:2,ffffa,10,Nos,100,1000:3,ffffa,10,Nos,100,1000:4,ffffa,10,Nos,100,1000:5,ffffa,10,Nos,100,1000:6,ffffa,10,Nos,100,1000:7,ffffa,10,Nos,100,1000:8,ffffa,10,Nos,100,1000:9,ffffa,10,Nos,100,1000:10,ffffa,10,Nos,100,1000:11,ffffa,10,Nos,100,1000:12,ffffa,10,Nos,100,1000:13,ffffa,10,Nos,100,1000:14,ffffa,10,Nos,100,1000:15,ffffa,10,Nos,100,1000:16,ffffa,10,Nos,100,1000:17,ffffa,10,Nos,100,1000:18,ffffa,10,Nos,100,1000:19,ffffa,10,Nos,100,1000:20,ffffa,10,Nos,100,1000:21,ffffa,10,Nos,100,1000:");//22,ffffa,10,Nos,100,1000:23,ffffa,10,Nos,100,1000:18,ffffa,10,Nos,100,1000:19,ffffa,10,Nos,100,1000:20,ffffa,10,Nos,100,1000:21,ffffa,10,Nos,100,1000:22,ffffa,10,Nos,100,1000
        ProductPdf firstPdf = new ProductPdf(product);
        ////////////////////////
        try {
            File myFile = new File(System.getProperty("user.dir") + "\\CustomerBills\\" + product.getBill_no() + ".pdf");
            Desktop.getDesktop().open(myFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static java.util.List<java.util.List<String>> createBatch(java.util.List<String> originalList,
            int chunkSize) {
        java.util.List<java.util.List<String>> listOfChunks = new ArrayList<java.util.List<String>>();
        for (int i = 0; i < originalList.size() / chunkSize; i++) {
            listOfChunks.add(originalList.subList(i * chunkSize, i * chunkSize
                    + chunkSize));
        }
        if (originalList.size() % chunkSize != 0) {
            listOfChunks.add(originalList.subList(originalList.size()
                    - originalList.size() % chunkSize, originalList.size()));
        }
        return listOfChunks;
    }

    public static java.util.List<String> get(String data) {
        java.util.List<String> list = new ArrayList<String>();
        String[] arr = data.split(":");
        for (String arr1 : arr) {
            list.add(arr1);
        }
        return list;
    }
}
