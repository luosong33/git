package com.jumore.jmbi.common.util.poi;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {
	public HSSFWorkbook exportExcel(String tmpContentCn, String sheetName, List dataList)
			throws Exception {
		HSSFWorkbook workbook = null;
		String[] titles_CN = tmpContentCn.split(",");
		try {
			// 这里的数据即时你要从后台取得的数据

			// 创建工作簿实例
			workbook = new HSSFWorkbook();
			// 创建工作表实例
			HSSFSheet sheet = workbook.createSheet(sheetName);
			// 设置列宽
			this.setSheetColumnWidth(titles_CN, sheet);
			// 获取样式
			HSSFCellStyle style = this.createTitleStyle(workbook);
			if (dataList != null && dataList.size() > 0) {
				// 创建第一行标题
				HSSFRow row = sheet.createRow((short) 0);// 建立新行

				for (int i = 0; i < titles_CN.length; i++) {
					this.createCell(row, i, null, HSSFCell.CELL_TYPE_STRING,
							titles_CN[i]);
				}
				// 给excel填充数据
				for (int i = 0; i < dataList.size(); i++) {
					// 将dataList里面的数据取出来
					String[] model = (String[]) dataList.get(i);
					HSSFRow row1 = sheet.createRow((short) (i + 1));// 建立新行
					// this.createCell(row1, 0, style,
					// HSSFCell.CELL_TYPE_STRING,
					// i + 1);
					for (int j = 0; j < model.length; j++)
						this.createCell(row1, j, style,
								HSSFCell.CELL_TYPE_STRING, model[j]);

				}
			} else {
				this.createCell(sheet.createRow(0), 0, style,
						HSSFCell.CELL_TYPE_STRING, "无数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;

	}

	private void setSheetColumnWidth(String[] titles_CN, HSSFSheet sheet) {

		// 根据你数据里面的记录有多少列，就设置多少列
        for (int i = 0; i < titles_CN.length; i++ ) {
        	sheet.setColumnWidth(i, 3000);
        }
	
		

	}

	// 设置excel的title样式
	private HSSFCellStyle createTitleStyle(HSSFWorkbook wb) {

		HSSFFont boldFont = wb.createFont();

		boldFont.setFontHeight((short) 200);

		HSSFCellStyle style = wb.createCellStyle();

		style.setFont(boldFont);

		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));

		return style;

	}

	// 创建Excel单元格

	private void createCell(HSSFRow row, int column, HSSFCellStyle style,

	int cellType, Object value) {

		HSSFCell cell = row.createCell(column);

		if (style != null) {

			cell.setCellStyle(style);

		}

		switch (cellType) {

		case HSSFCell.CELL_TYPE_BLANK: {

		}

			break;

		case HSSFCell.CELL_TYPE_STRING: {

			cell.setCellValue(value.toString());

		}

			break;

		case HSSFCell.CELL_TYPE_NUMERIC: {

			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);

			cell.setCellValue(Double.parseDouble(value.toString()));

		}

			break;

		default:

			break;

		}

	}
}
