package com.test.util;

import com.opencsv.CSVWriter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CsvUtil {
    public static String resultSetToCSV(ResultSet resultSet) {
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
            csvData.append(csvWriter.toString());
//            return new ByteArrayInputStream(csvData.toString().getBytes(StandardCharsets.UTF_8));
            return csvData.toString();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static String[] getColumnHeaders(ResultSet resultSet) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();
        String[] columnHeaders = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnHeaders[i - 1] = resultSet.getMetaData().getColumnName(i);
        }
        return columnHeaders;
    }

    private static String[] getRowData(ResultSet resultSet) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();
        String[] rowData = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            rowData[i - 1] = resultSet.getString(i);
        }
        return rowData;
    }
}
