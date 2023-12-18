package com.whut.truck.utils;

import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Csv工具类
 * <p>用于获取数据库数据并转换成Csv形式的InputStream</p>
 */
public class CsvUtil {
    /**
     * ResultSet转CSV的InputStream
     * @param resultSet 数据库获取后的ResultSet
     * @return CSV内容的InputStream
     */
    public static InputStream resultSetToCSV(ResultSet resultSet) {
        StringBuilder csvData = new StringBuilder();
        StringWriter stringWriter = new StringWriter();
        try (CSVWriter csvWriter = new CSVWriter(stringWriter)) {
            // Write column headers
            String[] columnHeaders = getColumnHeaders(resultSet);
//            System.out.println(Arrays.toString(columnHeaders));
            csvWriter.writeNext(columnHeaders);

            // Write data rows
            while (resultSet.next()) {
                String[] rowData = getRowData(resultSet);
//                System.out.println(Arrays.toString(rowData));
                csvWriter.writeNext(rowData);
            }
            try {
                stringWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Convert CSV data to InputStream
            csvData.append(csvWriter);
            String csvString = stringWriter.toString();
//            System.out.println(csvString);
            return new ByteArrayInputStream(csvString.getBytes(StandardCharsets.UTF_8));
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取表头
     * @param resultSet 数据库查询结果集合
     * @return 表头
     * @throws SQLException
     */
    private static String[] getColumnHeaders(ResultSet resultSet) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();
        String[] columnHeaders = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnHeaders[i - 1] = resultSet.getMetaData().getColumnName(i);
        }
        return columnHeaders;
    }

    /**
     * 行数据获取
     * @param resultSet 数据库查询集合
     * @return 列数据数据
     * @throws SQLException
     */
    private static String[] getRowData(ResultSet resultSet) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();
        String[] rowData = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            rowData[i - 1] = resultSet.getString(i);
        }
        return rowData;
    }
}
