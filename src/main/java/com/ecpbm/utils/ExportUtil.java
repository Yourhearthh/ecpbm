package com.ecpbm.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**导出Excel工具类
 * @author baoguangyu
 * @version 1.0
 * @date 2021/3/15 11:22
 */
public class ExportUtil {
    public static void exportExcel(String templatePath, String excelFileName, Map<String, Object> map, HttpServletRequest request, HttpServletResponse response){
        Assert.notNull(templatePath,"模板路径不能为空");
        TemplateExportParams params=new TemplateExportParams(templatePath,true);
        //开启横向遍历
        params.setColForEach(true);
        //响应输入流
        OutputStream responseOut =null;
        try {
            String responseFileName=excelFileName + ".xlsx";
            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                responseFileName = URLEncoder.encode(responseFileName, "UTF-8");
            } else {
                responseFileName = new String(responseFileName.getBytes("utf-8"), "ISO-8859-1");
            }
            // 设置强制下载不打开
            response.setContentType("application/force-download;charset=utf-8");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" +responseFileName);
            response.addHeader("Access-Control-Expose-Headers","FileName");
            response.addHeader("FileName",responseFileName);
            responseOut = response.getOutputStream();
            Workbook workbook= ExcelExportUtil.exportExcel(params,map);
            workbook.write(responseOut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(responseOut!=null){
                try {
                    responseOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void exportExcelTest(String templatePath, String excelFileName, Map<String, Object> map, HttpServletRequest request, HttpServletResponse response, String mark){
        Assert.notNull(templatePath,"模板路径不能为空");
        TemplateExportParams params=new TemplateExportParams(templatePath,true);
        //开启横向遍历
        params.setColForEach(true);
        //响应输入流
        OutputStream responseOut =null;
        try {
            String responseFileName=excelFileName;
            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                responseFileName = URLEncoder.encode(responseFileName, "UTF-8");
            } else {
                responseFileName = new String(responseFileName.getBytes("utf-8"), "ISO-8859-1");
            }
            // 设置强制下载不打开
            response.setContentType("application/force-download;charset=utf-8");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" +responseFileName);
            response.addHeader("Access-Control-Expose-Headers","FileName");
            response.addHeader("FileName",responseFileName);
            responseOut = response.getOutputStream();
            Workbook workbook= ExcelExportUtil.exportExcel(params,map);
            //对于特殊的数据需要经过处理
            if ("road-bridge".equals(mark)) {
                Sheet sheet = workbook.getSheetAt(0);
                //自动换行样式
                CellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setWrapText(true);
                cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
                //上报工作量行
                Row row = sheet.getRow(14);
                Cell cell = row.getCell(1);
                cell.setCellStyle(cellStyle);
                List<Map<String, Object>> mapList1 = (List<Map<String, Object>>) map.get("beforeImages1");
                if (mapList1 == null || mapList1.size() == 0) {
                    sheet.removeRow(sheet.getRow(24));
                }
                List<Map<String, Object>> mapList4 = (List<Map<String, Object>>) map.get("afterImages1");
                if (mapList4 == null || mapList4.size() == 0) {
                    sheet.removeRow(sheet.getRow(26));
                }
                List<Map<String, Object>> mapList5 = (List<Map<String, Object>>) map.get("afterImages2");
                if (mapList5 == null || mapList5.size() == 0) {
                    sheet.removeRow(sheet.getRow(28));
                }
            }
            workbook.write(responseOut);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(responseOut!=null){
                try {
                    responseOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
