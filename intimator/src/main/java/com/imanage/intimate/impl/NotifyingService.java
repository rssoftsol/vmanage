package com.imanage.intimate.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import com.imanage.PDFGen;
import com.imanage.intimate.EmailBean;
import com.imanage.intimate.NotificationType;
import com.imanage.models.ClubDetails;
import com.imanage.models.MemberDetails;
import com.imanage.services.register.ClubRegistrationService;
import com.imanage.util.DateUtility;
import com.itextpdf.text.pdf.PdfPTable;

@Service("notifyingService")
public class NotifyingService extends AbstractIntimator<ClubDetails> {
	
	@Autowired
	ClubRegistrationService  clubRegistrationService;
	
	NotificationType notificationType = NotificationType.SCHEDULAR_REMINDER;
	
	@Override
	public void intimate(ClubDetails clubDetails) {
		intimate(clubDetails, NotificationType.SCHEDULAR_REMINDER, 
				clubDetails.getSmsText());
	}
	
	public void intimate(ClubDetails clubDetails,NotificationType notificationType, String text) {
		NotifyingService.this.text = text;
		this.notificationType = notificationType;
		doInitialization(clubDetails);
		//intimateByEmail();
	}
	
	@Override	
	public void setEmailBean(final ClubDetails clubDetails){
		PDFGen<MemberDetails> pdfGen = new PDFGen<MemberDetails>() {
			@Override
			public boolean createPdfPTable(PdfPTable pdfPTable) {
				boolean  expires = false;
				int bal = clubDetails.getSmsCreditBal().getBalance();
				for(MemberDetails details : clubDetails.getMemberDetails()){
		        	if(notificationType == NotificationType.OFFER_TO_ALL || 
		        			((notificationType == NotificationType.MANUAL_REMINDER || 
		        			notificationType == NotificationType.OFFER_TO_MEMBERSHIP_EXPIRED)
		        			&& details.getExpirydate().getTime() < DateUtility.getTodaysDate())
		        			||(notificationType == NotificationType.SCHEDULAR_REMINDER && 
		        			details.getExpirydate().getTime() == DateUtility.getTodaysDate())){
		        		pdfPTable.addCell(details.getName());
		        		pdfPTable.addCell(details.getExpirydate().toString());
		        		NotifyingService.this.mobileNo = details.getPhone();
		        		//put logger here
		        		System.out.println("Member with given expiry date exist:"+details);
		        		String response = intimateBySMS();
		        		if(response.length() > 3){
		        			pdfPTable.addCell("Successful");
		        			bal--;
		        		}else{
		        			pdfPTable.addCell("Failed("+response+")");
		        		}
			    		expires = true;
		        	}
		        }
				clubDetails.getSmsCreditBal().setBalance(bal);
				if(expires){
					clubRegistrationService.update(clubDetails);
				}
				return expires;
			}
		};
		FileSystemResource file = new FileSystemResource(getPath()+"\\"+notificationType.name()+"_"+DateUtility.getTodaysDate("ddMMyyyy")+".pdf");
		try {
			file = pdfGen.getPDFDoc(file);
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
	
	private String getPath(){
		String path = "D://intimator//"+DateUtility.getTodaysDate("dd_MM_yyyy");
		File f = new File(path);
		if(!f.exists()){
			f.mkdirs();
		}
		return path;
	}
}
