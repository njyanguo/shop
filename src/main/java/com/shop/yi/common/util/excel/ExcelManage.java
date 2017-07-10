package com.shop.yi.common.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * 从excel读取数据/往excel中写入 excel有表头，表头每列的内容对应实体类的属性
 * @author nagsh
 */
public class ExcelManage {
	private HSSFWorkbook workbook = null;

	/**
	 * 判断文件是否存在.
	 * @param fileDir 文件路径
	 * @return
	 */
	public boolean fileExist(String fileDir) {
		boolean flag = false;
		File file = new File(fileDir);
		flag = file.exists();
		return flag;
	}
	/**
	 * 判断文件的sheet是否存在.
	 * @param fileDir 文件路径
	 * @param sheetName 表格索引名
	 * @return
	 */
	public boolean sheetExist(String fileDir, String sheetName) {
		boolean flag = false;
		File file = new File(fileDir);
		if (file.exists()) { // 文件存在
			// 创建workbook
			try {
				workbook = new HSSFWorkbook(new FileInputStream(file));
				// 添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
				HSSFSheet sheet = workbook.getSheet(sheetName);
				if (sheet != null)
					flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else { // 文件不存在
			flag = false;
		}
		return flag;
	}
	/**
	 * 创建新excel.
	 * @param fileDir excel的路径
	 * @param sheetName 要创建的表格索引
	 * @param titleRow excel的第一行即表格头
	 */
	public void createExcel(String fileDir, String sheetName, String titleRow[]) {
		// 创建workbook
		workbook = new HSSFWorkbook();
		// 添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
		Sheet sheet1 = workbook.createSheet(sheetName);
		// 新建文件
		FileOutputStream out = null;
		try {
			// 添加表头
			Row row = workbook.getSheet(sheetName).createRow(0); // 创建第一行
			for (int i = 0; i < titleRow.length; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(titleRow[i]);
			}
			out = new FileOutputStream(fileDir);
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 删除文件.
	 * @param fileDir 文件路径
	 */
	public boolean deleteExcel(String fileDir) {
		boolean flag = false;
		File file = new File(fileDir);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				file.delete();
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * 合并单元格
	 * @param workbook
	 * @param fileDir
	 * @param sheetName
	 * @param rowStart 合并单元格开始行行号 从1开始数
	 * @param rowEnd 合并单元格结束行行号 从1开始数
	 * @param ColumnStart 合并单元格开始列列号 从1数
	 * @param ColumnEnd 合并单元格结束列列号 从1数
	 */
	/*public void mergedCell(HSSFWorkbook workbook, String fileDir, String sheetName, int rowStart, int rowEnd,
			int ColumnStart, int ColumnEnd) {
		try {
			FileOutputStream out = null;
			HSSFSheet sheet = workbook.getSheet(sheetName);
			sheet.addMergedRegion(new CellRangeAddress((short) rowStart - 1, (short) rowEnd - 1,
					(short) ColumnStart - 1, (short) ColumnEnd - 1));
			out = new FileOutputStream(fileDir);
			workbook.write(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	/**
	 * 删除行【彻底删除】
	 * @param workbook
	 * @param fileDir
	 * @param sheetName
	 * @param rowNum 行号
	 */
	public void removeRow(HSSFWorkbook workbook, String fileDir, String sheetName, int rowNum) {
		try {
			// 流
			FileOutputStream out = null;
			HSSFSheet sheet = workbook.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			sheet.shiftRows(rowNum, rowCount, -1); // 上移
			out = new FileOutputStream(fileDir);
			workbook.write(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除行【删除数据】
	 * @param workbook
	 * @param fileDir
	 * @param sheetName
	 * @param rowNum 行号
	 */
	public void removeRowData(HSSFWorkbook workbook, String fileDir, String sheetName, int rowNum) {
		try {
			// 流
			FileOutputStream out = null;
			HSSFSheet sheet = workbook.getSheet(sheetName);
			Row row = sheet.getRow(rowNum - 1);
			sheet.removeRow(row);
			out = new FileOutputStream(fileDir);
			workbook.write(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 往excel中写入
	 * @param fileDir 文件路径
	 * @param sheetName 表格索引
	 * @param object
	 */
	public void writeToExcel(String fileDir, String sheetName, Object object) {
		// 创建workbook
		File file = new File(fileDir);
		try {
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 流
		FileOutputStream out = null;
		HSSFSheet sheet = workbook.getSheet(sheetName);
		// 获取表格的总行数
		int rowCount = sheet.getLastRowNum() + 1; // 需要加一
		// 获取表头的列数
		int columnCount = sheet.getRow(0).getLastCellNum();
		try {
			Row row = sheet.createRow(rowCount); // 最新要添加的一行
			// 通过反射获得object的字段,对应表头插入
			// 获取该对象的class对象
			Class class_ = object.getClass();
			// 获得表头行对象
			HSSFRow titleRow = sheet.getRow(0);
			if (titleRow != null) {
				for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) { // 遍历表头
					String title = titleRow.getCell(columnIndex).toString().trim().toString().trim();
					String UTitle = Character.toUpperCase(title.charAt(0)) + title.substring(1, title.length()); // 使其首字母大写;
					String methodName = "get" + UTitle;
					Method method = class_.getDeclaredMethod(methodName); // 设置要执行的方法
					String data = method.invoke(object).toString(); // 执行该get方法,即要插入的数据
					Cell cell = row.createCell(columnIndex);
					cell.setCellValue(data);
				}
			}
			out = new FileOutputStream(fileDir);
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 向某一行添加数据
	 * @param workbook 使用该参数是为了在传递样式是保证workbook一致
	 * @param fileDir 文件路径
	 * @param sheetName 索引
	 * @param rowNumber 要添加到哪行
	 * @param datas 添加的数据 【column,data】列号-数据
	 * @param style 样式
	 */
	/*public void writeOneRow(HSSFWorkbook workbook, String fileDir, String sheetName, 
			int rowNumber, List<PoiCell> datas) {
		try {
			// 流
			FileOutputStream out = null;
			HSSFSheet sheet = workbook.getSheet(sheetName);
			sheet.autoSizeColumn(1, true); // 设置自适应宽度
			// 最新要添加的一行
			Row row = sheet.getRow(rowNumber - 1);
			if (row == null) {
				row = sheet.createRow(rowNumber - 1);
			}
			// 写入数据
			for (PoiCell data : datas) {
				Cell cell = row.createCell(data.getColumn() - 1);
				// 设置样式
				cell.setCellStyle(data.getStyle());
				// 设置数据
				cell.setCellValue(data.getData());
			}
			out = new FileOutputStream(fileDir);
			workbook.write(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	/**
	 * 将16进制的颜色代码写入样式中来设置颜色
	 * @param style 保证style统一
	 * @param color 颜色：66FFDD
	 * @param index 索引 1-48 使用时不可重复
	 * @return
	 */
	public CellStyle getColorStyle(CellStyle style, String color, short index) {
		if (color != "" && color != null) {
			// 转为RGB码
			int r = Integer.parseInt((color.substring(0, 2)), 16); // 转为16进制
			int g = Integer.parseInt((color.substring(2, 4)), 16);
			int b = Integer.parseInt((color.substring(4, 6)), 16);
			// 自定义cell颜色
			HSSFPalette palette = workbook.getCustomPalette();
			palette.setColorAtIndex((short) index, (byte) r, (byte) g, (byte) b);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(index);
		}
		return style;
	}
	/**
	 * 设置边框
	 * @param style
	 * @return
	 */
	public CellStyle setBorder(CellStyle style) {
		style.setBorderBottom(CellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(CellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(CellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(CellStyle.BORDER_THIN);// 右边框
		return style;
	}
	/**
	 * 设置字体
	 * @param style
	 * @return
	 */
	public CellStyle setFont(CellStyle style) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("仿宋");
		font.setBold(true);
		style.setFont(font);
		return style;
	}
}
