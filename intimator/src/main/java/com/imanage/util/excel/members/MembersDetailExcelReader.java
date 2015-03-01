package com.imanage.util.excel.members;

import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.apache.poi.xssf.usermodel.XSSFCell;

import com.imanage.models.MemberDetails;
import com.imanage.util.excel.ExcelProcessor;

public class MembersDetailExcelReader extends ExcelProcessor<MembersDetailUploadBean>{
	
	public Set<String> memberDetails = null;
	
	@Override
	public void processExcelRow(
			Vector<MembersDetailUploadBean> detailUploadBeans, Iterator cellIter) {
		// TODO Auto-generated method stub
		int _case = 0;
		MembersDetailUploadBean detailUploadBean = new MembersDetailUploadBean();
		
		while(cellIter.hasNext()){
            XSSFCell cell = (XSSFCell) cellIter.next();
            switch (_case) {
				case 0:
					if(cell.getCellType() == 0)
						detailUploadBean.memberId = cell.getRawValue();
					else
						detailUploadBean.memberId = cell.toString();
					break;
				case 1:
					if(cell.getCellType() == 0)
						detailUploadBean.phonenumber = cell.getRawValue();
					else
						detailUploadBean.phonenumber = cell.toString();
					break;
					
				case 2:
					detailUploadBean.name = cell.toString();
					break;
	
				case 3:
					detailUploadBean.expiryDate = cell.toString();
					break;
			}
           _case++;
        }
		detailUploadBeans.addElement(detailUploadBean);
		validateProcessedRow(detailUploadBean);
	}
	
	@Override
	public void validateProcessedRow(MembersDetailUploadBean detailUploadBean) {
		detailUploadBean.validDate();
		detailUploadBean.validMemberId(memberDetails);
		detailUploadBean.validName();
		detailUploadBean.validPhonenumber();
	}
	
	@Override
	public boolean validExcelFormat(Iterator cellIter) {
		while (cellIter.hasNext()) {
			String header = cellIter.next().toString();
			try {
				Header.valueOf(header);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return false;
			}
		}
		return true;
	}
}

enum Header{
	MEMBERID,PHONE,NAME,EXPIRYDATE;
}
