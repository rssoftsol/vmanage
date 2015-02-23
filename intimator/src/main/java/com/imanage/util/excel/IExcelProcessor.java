package com.imanage.util.excel;

import java.io.InputStream;
import java.util.Vector;

import org.apache.poi.xssf.usermodel.XSSFCell;

public interface IExcelProcessor<T> {
	public Vector<T> importExcelSheet(InputStream is);
	public void exportExcelSheet(Vector<Vector<XSSFCell>> data);
}
