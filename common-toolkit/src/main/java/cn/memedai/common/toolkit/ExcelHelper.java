package cn.memedai.common.toolkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * Excel工具类
 * @author tongxiong.cheng
 * @date 2016-10-28 上午9:08:26
 * @version 1.0
 */
public final class ExcelHelper {

    /** 最多缓存行*/
    public static final int     MAX_ROW_IN_CACHE = 10000;
    
    /** excel 2007单个工作簿最大行数 */
    private static final int     MAX_ROW_EXCEL_2007 = 1048576;

    public void exportData(String[] header, String[][] data, OutputStream os) throws IOException {

        // 判断需要导出的数据的最大行数是否超过excel 2007单个工作簿最大行数
        if (data.length >= MAX_ROW_EXCEL_2007) {
            throw new RuntimeException("导出的数据行数超过Excel 2007版本工作簿最大行数[" + MAX_ROW_EXCEL_2007 + "]！");
        }

        // Excel 2003
        // Workbook wb = new HSSFWorkbook();
        // Excel 2007
        // Workbook wb = new XSSFWorkbook();
        Workbook wb = new SXSSFWorkbook(MAX_ROW_IN_CACHE);
        Sheet sheet = this.createSheet(wb, 1);

        // 写入表头
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            String headerValue = header[i];
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headerValue);
            cell.setCellStyle(this.getHeaderStyle(wb));
        }

        try {
        	// 写入数据行
            CellStyle dataRowStyle = this.getDataRowStyle(wb);
            for (int i = 0; i < data.length; i++) {
                this.writeRow(sheet, i + 1, dataRowStyle, data[i]);
            }
            // 返回字节流
            wb.write(os);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new IOException(e);
        }finally{
            os.flush();
            os.close();
        }
    }

    /**
     * 创建新的工作簿
     * 
     * @param wb
     * @param index
     * @return
     */
    private Sheet createSheet(Workbook wb, int index) {
        Sheet sheet = wb.createSheet("Sheet" + index);
        return sheet;
    }

    /**
     * 写行
     * 
     * @param sheet
     * @param index
     * @param style
     * @param rowData
     */
    private void writeRow(Sheet sheet, int index, CellStyle style, String[] rowData) {
        Row row = sheet.createRow(index);
        for (int i = 0; i < rowData.length; i++) {
            Cell cell = row.createCell(i);
            String dataValue = rowData[i];
            cell.setCellValue(dataValue);
            cell.setCellStyle(style);
        }
    }

    /**
     * 生成数据行表头样式
     * 
     * @param wb
     * @return
     */
	private CellStyle getHeaderStyle(Workbook wb) {
        // 表头样式
        CellStyle headerStyle = wb.createCellStyle();
        // 设置边框样式
        headerStyle.setFillBackgroundColor(IndexedColors.BLUE.index);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.index);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.index);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.index);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.index);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        // 设置表头背景
        headerStyle.setFillForegroundColor(IndexedColors.INDIGO.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 生成一个字体
        Font headerFont = wb.createFont();
        headerFont.setColor(IndexedColors.WHITE.index);
        //headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setBold(Boolean.TRUE);//加粗
        // 把字体应用到当前的样式
        headerStyle.setFont(headerFont);

        return headerStyle;
    }

    /**
     * 生成数据行样式
     * 
     * @param wb
     * @return
     */
	private CellStyle getDataRowStyle(Workbook wb) {
        // 数据样式
        CellStyle dataStyle = wb.createCellStyle();
        // 设置这些样式
        dataStyle.setFillBackgroundColor(IndexedColors.BLUE.index);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.index);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.index);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.index);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.index);
        dataStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中 
        // 生成一个字体
        Font dataFont = wb.createFont();
        //        dataFont.setFontHeightInPoints((short) 16);
        //        dataFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        dataStyle.setFont(dataFont);
        return dataStyle;
    }

      public static void main(String[] args) {
    
          ExcelHelper excel = new ExcelHelper();
    
          String[] header = { "Hello", "Hello", "Hello", "Hello", "Hello" };
          String[][] data = { { "Jim Green", "Han Meimei", "LiLei", "Poly", "Lucy" },
                  { "Jim Green", "Han Meimei", "●●◎", "Poly", "Lucy" },
                  { "Jim Green", "Han ▲→Meimei", "LiLei", "Poly", "Lucy" },
                  { "Jim Green", "Han Meimei", "LiLei", "Poly", "Lucy" },
                  { "Jim Green", "Ha＆n Meimei", "Li↑＠№Lei", "Poly", "Lucy" },
                  { "Jim Green", "Han Meimei", "LiLei", "Poly", "Lucy" },
                  { "地", "大幅度", "魂牵■梦萦村", "厅", "负担" } };
    
          try {
              FileOutputStream fos = new FileOutputStream(new File("d:/test.xlsx"));
              excel.exportData(header, data, fos);
              fos.close();
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
    
      }

}
