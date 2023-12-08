package com.whut.truck.utils;

import com.opencsv.CSVWriter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        try (CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(System.out))) {
            // Write column headers
            String[] columnHeaders = getColumnHeaders(resultSet);
            csvWriter.writeNext(columnHeaders);

            // Write data rows
            while (resultSet.next()) {
                String[] rowData = getRowData(resultSet);
                csvWriter.writeNext(rowData);
            }

            // Convert CSV data to InputStream
            csvData.append(csvWriter);
            return new ByteArrayInputStream(csvData.toString().getBytes(StandardCharsets.UTF_8));
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
