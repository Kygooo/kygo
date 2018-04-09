package com.kygo.common.utils;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class ExcelUtil {
	
	private static final String XLS = "xls";
	private static final String XLSX = "xlsx";
	
	public static List<String[]> readExcel(InputStream inputStream, int column) throws IOException, OpenXML4JException, ParserConfigurationException, SAXException{
		return XLSXCovertCSVReader.readerExcel(inputStream, column);
	}
	
	public static List<String[]> readExcel(String path, int minColumns) throws IOException, OpenXML4JException, ParserConfigurationException, SAXException{
		return XLSXCovertCSVReader.readerExcel(path, minColumns);
	}

	public static void checkExcelFile(String filename) throws IOException {
		// 判断文件是否存在
		if (filename == null) {
			throw new FileNotFoundException("文件不存在！");
		}
		if (!filename.endsWith(XLS) && !filename.endsWith(XLSX)) {
			//logger.error(filename + "不是excel文件");
			throw new IOException(filename + "不是excel文件");
		}
	}
}
