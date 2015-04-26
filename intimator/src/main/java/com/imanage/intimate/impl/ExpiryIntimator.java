package com.imanage.intimate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.imanage.PDFGen;
import com.imanage.intimate.EmailBean;
import com.imanage.models.ClubDetails;
import com.imanage.models.MemberDetails;
import com.imanage.services.register.ClubRegistrationService;
import com.imanage.util.DateUtility;
import com.itextpdf.text.pdf.PdfPTable;

@Service("intimatorService")
public class ExpiryIntimator extends AbstractIntimator<ClubDetails> {
	
	boolean all;
	
	@Autowired
	ClubRegistrationService  clubRegistrationService;
	
	@Override
	public void intimate(ClubDetails clubDetails) {
		ExpiryIntimator.this.text = clubDetails.getSmsText();
		doInitialization(clubDetails);
		intimateByEmail();
	}
	
	public void intimate(ClubDetails clubDetails, boolean all, String text) {
		ExpiryIntimator.this.text = text;
		this.all = all;
		doInitialization(clubDetails);
		intimateByEmail();
	}
	
	@Override	
	public void setEmailBean(final ClubDetails clubDetails){
		PDFGen<MemberDetails> pdfGen = new PDFGen<MemberDetails>() {
			@Override
			public boolean createPdfPTable(PdfPTable pdfPTable) {
				boolean  expires = false;
				int bal = clubDetails.getSmsCreditBal().getBalance();
				for(MemberDetails details : clubDetails.getMemberDetails()){
		        	if(all || details.getExpirydate().toString().equals(DateUtility.getTodaysDate("yyyy-MM-dd"))){
		        		pdfPTable.addCell(details.getName());
		        		pdfPTable.addCell(details.getExpirydate().toString());
		        		ExpiryIntimator.this.mobileNo = details.getPhone();
		        		//put logger here
		        		System.out.println("Member with given expiry date exist:"+details);
		        		String response = intimateBySMS();
		        		if("1".equals(response)){
		        			pdfPTable.addCell("Successful");
		        		}else{
		        			pdfPTable.addCell("Failed("+response+")");
		        		}
		        		bal--;
			    		expires = true;
		        	}
		        }
				if(expires){
					clubRegistrationService.update(clubDetails);
				}
				return expires;
			}
		};
		FileSystemResource file = null;
		try {
			file = pdfGen.getPDFDoc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		EmailBean emailBean = new EmailBean().withFromEmailId(emailConfigBean.getEmailFrom()).
				withToEmailId(clubDetails.getEmail()).withMailSubject(emailConfigBean.getPdfEmailSubject()).
				withEmailAttachment(file).withMailBody(file == null?emailConfigBean.getEmailNoPDFInfoText() : emailConfigBean.getEmailPDFInfoText());
		//put logger here
		this.emailBean = emailBean;
	}
}
