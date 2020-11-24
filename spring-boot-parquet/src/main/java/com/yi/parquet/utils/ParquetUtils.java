package com.yi.parquet.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.hadoop.ParquetReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取parquet文件数据
 *
 * @author YI
 * @date 2019-8-1
 */
@Slf4j
public class ParquetUtils {

    /**
     * 读取头和数据
     *
     * @param path    parquet文件
     * @param maxLine 读取的行数
     * @return
     * @throws IOException
     */
    public static Map<String, List<String[]>> viewParquet(String path, int maxLine) throws IOException {
        Map<String, List<String[]>> parquetInfo = new HashMap<>(16);
        List<String[]> dataList = new ArrayList<>();

        try (ParquetReader<GenericData.Record> reader = AvroParquetReader.<GenericData.Record>builder(new Path(path)).build()) {

            GenericData.Record record;
            //解析Parquet数据逐行读取
            while ((record = reader.read()) != null && maxLine > 0) {
                final List<Schema.Field> fieldsList = record.getSchema().getFields();
                String[] fieldNames = new String[fieldsList.size()];
                //读取第一行获取列头信息
                if (parquetInfo.get("head") == null) {
                    for (int i = 0; i < fieldsList.size(); i++) {
                        fieldNames[i] = fieldsList.get(i).name();
                    }

                    dataList.add(fieldNames);
                    parquetInfo.put("head", dataList);
                    dataList = new ArrayList<>();
                } else {
                    fieldNames = parquetInfo.get("head").get(0);
                }

                String[] dataString = new String[fieldNames.length];
                //读取数据获取列头信息
                for (int i = 0; i < fieldNames.length; i++) {
                    dataString[i] = record.get(fieldNames[i]).toString();
                }

                dataList.add(dataString);
                maxLine--;
            }
        }
        parquetInfo.put("data", dataList);

        return parquetInfo;
    }
}
