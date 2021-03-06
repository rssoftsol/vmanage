package com.imanage;


import java.util.Date;

import org.springframework.core.io.FileSystemResource;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
public abstract class PDFGen<T> {
	
	public FileSystemResource getPDFDoc(FileSystemResource file) throws Exception{
        Document document = new Document();
        PdfWriter.getInstance(document, file.getOutputStream());
        //Inserting Image in PDF
           //Image image = Image.getInstance ("src/pdf/java4s.png");
           //image.scaleAbsolute(120f, 60f);//image width,height    

        //Inserting Table in PDF
        PdfPTable table=new PdfPTable(3);

        PdfPCell cell = new PdfPCell (new Paragraph ("Notification Report"));

        cell.setColspan (3);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (10.0f);
        cell.setBackgroundColor (new BaseColor (140, 221, 8));                                  

        table.addCell(cell);                                      

        table.addCell("Name");
        table.addCell("Expiry");
        table.addCell("Member Notification");
        if(!createPdfPTable(table)){
        	PdfPCell cell2 = new PdfPCell (new Paragraph ("No Member with Expiry exist"));
        	cell2.setColspan (3);
        	cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
        	cell2.setPadding (10.0f);
        	table.addCell(cell2);
        }
        
        table.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
        table.setSpacingAfter(30.0f);        // Space After table starts, like margin-Bottom in CSS                                          

       //Inserting List in PDF

       //Text formating in PDF

       //Now Insert Every Thing Into PDF Document
        document.open();//PF document opened........                  

       // document.add(image);

        document.add(Chunk.NEWLINE);   //Something like in HTML :-)

        document.add(new Paragraph("Dear Club Owner"));
        document.add(new Paragraph("Document Generated On - "+new Date().toString()));    

        document.add(table);
        document.close();
        /*if(expires){
        	return file;
        }else{
        	return null;
        }*/
        return file;
  }
	
	public abstract boolean createPdfPTable(PdfPTable pdfPTable); 
    public static void main(String[] args) {
 
        try {
 
              
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}