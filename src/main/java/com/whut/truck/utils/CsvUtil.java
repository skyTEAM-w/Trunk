package com.whut.truck.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CsvUtil {

    public static InputStream resultSetToCSV(ResultSet rs) throws SQLException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Check if we need headers. Usually for data processing we might not need headers if the Python script expects raw data.
        // But let's verify if csvSave skips the first line?
        // In SensorDaoImpl.csvSave:
        // int counter = 1;
        // while... if (counter != 0) ... counter++;
        // It processes every line. counter starts at 1.
        // So no header in input files?
        // Wait, if counter starts at 1, counter != 0 is always true.
        // So it processes the first line too.
        // So input files have NO headers.
        
        // So output should probably have NO headers.
        
        while (rs.next()) {
            StringBuilder row = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                String value = rs.getString(i);
                row.append(value != null ? value : "");
                if (i < columnCount) {
                    row.append(" "); // Use space as delimiter to match input format
                }
            }
            row.append("\n");
            try {
                outputStream.write(row.toString().getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException("Error writing to ByteArrayOutputStream", e);
            }
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
