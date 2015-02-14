package com.imanage;


import java.io.*;
import java.util.Date;
import java.util.Set;

import com.imanage.intimate.Intimator;
import com.imanage.models.MemberDetails;
import com.imanage.util.DateUtility;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
public class PDFGen {
	
	public static File getPDFDoc(Set<MemberDetails> members, String fileName, Intimator intimator) throws Exception{
		boolean expires = false;
		ClassLoader classLoader = PDFGen.class.getClassLoader();
		String path = classLoader.getResource("/resources/pdfs").getPath();
		File f = new File(path+"\\Membership_Expiry_Report_"+DateUtility.getTodaysDate("ddMMyyyy")+".pdf");
		//File f = new File("D:\\test_data\\test"+".pdf");
        OutputStream file = new FileOutputStream(f);
        Document document = new Document();
        PdfWriter.getInstance(document, file);
        //Inserting Image in PDF
           //Image image = Image.getInstance ("src/pdf/java4s.png");
           //image.scaleAbsolute(120f, 60f);//image width,height    

        //Inserting Table in PDF
        PdfPTable table=new PdfPTable(2);

        PdfPCell cell = new PdfPCell (new Paragraph ("Membership expiry details"));

        cell.setColspan (2);
        cell.setHorizontalAlignment (Element.ALIGN_CENTER);
        cell.setPadding (10.0f);
        cell.setBackgroundColor (new BaseColor (140, 221, 8));                                  

        table.addCell(cell);                                      

        table.addCell("Name");
        table.addCell("Expiry");
        for(MemberDetails details : members){
        	if(details.getExpirydate().equals(DateUtility.getTodaysDate("yyyy-MM-dd"))){
	            table.addCell(details.getName());
	            table.addCell(details.getExpirydate().toString());
	    		intimator.intimateMember(details.getPhone(), "");
	    		expires = true;
        	}
        }
        table.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
        table.setSpacingAfter(30.0f);        // Space After table starts, like margin-Bottom in CSS                                          

       //Inserting List in PDF

       //Text formating in PDF

       //Now Insert Every Thing Into PDF Document
        document.open();//PDF document opened........                  

       // document.add(image);

        document.add(Chunk.NEWLINE);   //Something like in HTML :-)

        document.add(new Paragraph("Dear Club Owner"));
        document.add(new Paragraph("Document Generated On - "+new Date().toString()));    

        document.add(table);
        document.close();
        file.close();
        if(expires){
        	return f;
        }else{
        	f.delete();
        	return null;
        }
  }
	
    public static void main(String[] args) {
 
        try {
 
              
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}