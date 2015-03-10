package com.imanage.util.excel.members;

import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;

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
			HSSFCell cell = (HSSFCell) cellIter.next();
            switch (_case) {
				case 0:
					if(cell.getCellType() == 0){
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						detailUploadBean.memberId = cell.getStringCellValue();
					}else
						detailUploadBean.memberId = cell.toString();
					break;
				case 1:
					if(cell.getCellType() == 0){
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						detailUploadBean.phonenumber = cell.getStringCellValue();
					}else
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
		System.out.println("MembersDetailUploadBean :"+detailUploadBean);
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
