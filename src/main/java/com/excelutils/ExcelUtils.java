package com.excelutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static Workbook wb;
	public static Sheet ws;
	public static Row row;
	public static Cell cell;
	public static Cell excelCell;
	public static CellStyle style;
	public static int getRowCount(String xlfile, String xlsheet) throws IOException {
		fi = new FileInputStream(xlfile);

		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int rowcount = ws.getLastRowNum();
		System.out.println(rowcount);
		wb.close();
		fi.close();
		return rowcount;
	}

	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}

	public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		try {
			data = cell.getStringCellValue();
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	/*
	 * new row datas
	 */
public static Integer getCellNumericData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		Integer data = null;
		try {
			
			data = Integer.valueOf((int) cell.getNumericCellValue());
		} catch (Exception e) {
			// data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data)
			throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	

	public static void setCellDatas(String xlfile, String xlsheet, int rownum,int colnum,String requestId)
	throws IOException, InterruptedException {
		System.out.println("inside setCellDatas");
		String idvalue = null;
		System.out.println("data insert in excel :" + requestId);//18937
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		if (excelCell == null) {
			excelCell=row.createCell(colnum);
			System.out.println("inside if");
			 
			Thread.sleep(6000);
			excelCell.setCellValue(requestId);
			idvalue  =excelCell.getStringCellValue();
			Thread.sleep(4000);
			 System.out.println("data :" + idvalue);
			 FileOutputStream fo1 = new FileOutputStream(xlfile);
			wb.write(fo1);
	
			System.out.println("print cell value :"+ excelCell.getStringCellValue());//18936
			// idvalue=cell.getStringCellValue();

	
			Thread.sleep(2000);
		}
		// System.out.println("idvalue :"+ idvalue);
		
				else  {
					Cell excelCell1=row.createCell(colnum);
					System.out.println("inside else");
					System.out.println("inside else print cell value :"+ excelCell.getStringCellValue());
					excelCell1.setCellValue(excelCell.getStringCellValue()+"/"+requestId);
					System.out.println("print cell value :"+ excelCell1.getStringCellValue());
					FileOutputStream fo2 = new FileOutputStream(xlfile);
					wb.write(fo2);
					excelCell = excelCell1;
					System.out.println("print cell value :"+ excelCell.getStringCellValue());
				}
			 
		    		
		
		
		idvalue=excelCell.getStringCellValue();
      		System.out.println("print cell value end :"+ excelCell.getStringCellValue());
			
			

			
			
			
			
			
			
			
			

	}

	
	public static void fillGreenColor(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);

		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	public static void fillRedColor(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);

		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(style);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	public static String[][] excelData(String excelPath, String sheetName, int rownum) throws IOException {

		String[][] data = null;
		File src = new File(excelPath);
		fi = new FileInputStream(src);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetName);
		int row = ws.getLastRowNum();
		System.out.println(row);
		int cols = ws.getRow(rownum).getLastCellNum();
		System.out.println(cols);
		data = new String[row][cols];
		for (int i = 1; i <= row; i++) {
			for (int j = 0; j <= cols - 1; j++) {
				DataFormatter format = new DataFormatter();
				Cell cell = ws.getRow(j).getCell(j);
				data[i - 1][j] = format.formatCellValue(cell);
			}
		}
		return data;
	}



	
	/*public static void setCellDataWithMultipleValue(String xlfile, String xlsheet, int rownum, int colnum,String data) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);


		List<Class> list = new ArrayList<Class>();

        Iterator<Row> itr = ws.iterator();
        String newName = null; 
        String oldName = null;

        while(itr.hasNext()){
            Row nextRow = itr.next();
            // For each row, iterate through all the columns
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) 
            {
               Cell cell = cellIterator.next();

                newName =  nextRow.getCell(0).toString();

                if(nextRow.getCell(1).toString().contains(",")){
                    StringTokenizer st = new StringTokenizer(nextRow.getCell(1).toString(),",");
                    while(st.hasMoreTokens()){
                        oldName = st.nextToken();
                    }
               }
                else{
                     oldName = nextRow.getCell(1).toString();
                    }
            }
            System.out.println();
        }  
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
        
        
	}
	
	
	*/
	
	
	
	
	
	
	
	
	
	

}
