/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PdfFiles;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.Sides;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

/**
 *
 * @author Vision
 */
public class print {

    public void print(File filename) {
        try {
            PrintService myPrintService = findPrintService("EPSON L380 Series");
            PDDocument doc = PDDocument.load(filename);
            //PDDocument doc = PDDocument.load(new File("E:\\Ashwini\\Netbeans\\DoctorOPD(29 June 2018)\\DoctorOPD\\CustomerBills\\1.pdf"));
            PrinterJob job = PrinterJob.getPrinterJob();

            // define custom paper
            Paper paper = new Paper();
            paper.setSize(700, 600); // 1/72 inch
            paper.setImageableArea(-135, 0, paper.getWidth(), paper.getHeight()); // no margins

            // custom page format
            PageFormat pageFormat = new PageFormat();
            pageFormat.setPaper(paper);

            // override the page format
            Book book = new Book();//890445
            // append all pages
            book.append(new PDFPrintable(doc, Scaling.SHRINK_TO_FIT), pageFormat, doc.getNumberOfPages());
            job.setPageable(book);
            job.setPrintService(myPrintService);
            job.print();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().trim().equals(printerName)) {
                return printService;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException, PrinterException {
    
    //print p=new print();
    //p.print();
   
    
    }

}
