package com.web.shop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class XlsFormatUtila {
	/**
	 * 返回一个object【】数组
	 * 其中object[0]代表学号列
	 * 其中object[1】代表学生姓名列
	 * @param file
	 * @return
	 */
	public static Object[] getFormat(File file) {
		Object[] strs = new Object[2];
		try {
			for(int j = 0 ;j<2;j++){
				ArrayList<String> columnList = new ArrayList<String>();
				FileInputStream in = new FileInputStream(file);

				HSSFWorkbook wb = new HSSFWorkbook(in);

				Sheet sheet = wb.getSheetAt(0);
				int firstRowNum = sheet.getFirstRowNum();
				int lastRowNum = sheet.getLastRowNum();

				Row row = null;
				Cell cell_a = null;
				Cell cell_b = null;
				for (int i = firstRowNum; i <= lastRowNum; i++) {
					row = sheet.getRow(i); // 取得第i行
					cell_a = row.getCell(j); // 取得i行的第一列
					row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
					String cellValue = cell_a.getStringCellValue().trim();
					columnList.add(cellValue);
				}
				strs[j] = columnList;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strs;
	}

}
