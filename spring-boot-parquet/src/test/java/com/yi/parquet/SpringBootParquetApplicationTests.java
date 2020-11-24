package com.yi.parquet;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.yi.parquet.model.Baike;
import com.yi.parquet.model.BaikeExample;
import com.yi.parquet.model.BaikeSchema;
import com.yi.parquet.service.BaikeService;
import com.yi.parquet.utils.ParquetUtils;
import org.apache.avro.reflect.ReflectData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.parquet.hadoop.ParquetFileWriter.Mode.OVERWRITE;
import static org.apache.parquet.hadoop.metadata.CompressionCodecName.SNAPPY;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootParquetApplicationTests {

    @Autowired
    BaikeService baikeService;

    /**
     * 添加数据
     */
    @Test
    public void test1() {
        List<String> list1 = new ArrayList<>();
        list1.add("文学");
        list1.add("小说");

        String tag1 = JSONUtil.toJsonStr(list1);
        Baike baike1 = new Baike("老人与海", tag1, 1000, 10, "海明威", "男", 100);

        List<String> list2 = new ArrayList<>();
        list2.add("魔幻");
        list2.add("小说");
        String tag2 = JSONUtil.toJsonStr(list2);
        Baike baike2 = new Baike("全职法师", tag2, 1000000, 5, "乱", "男", 1000);

        baikeService.insertSelective(baike1);
        baikeService.insertSelective(baike2);
    }

    /**
     * 把查询的数据转换为parquet文件
     */
    @Test
    public void test2() throws IOException {
        Path dataFile = new Path("D:/test/demo.snappy.parquet");

        BaikeExample example = new BaikeExample();
        BaikeExample.Criteria criteria = example.createCriteria();

        criteria.andNameEqualTo("海明威");

        List<Baike> baikes = baikeService.selectByExample(example);

        System.out.println(baikes);

        try (ParquetWriter<BaikeSchema> writer = AvroParquetWriter.<BaikeSchema>builder(dataFile)
                .withSchema(ReflectData.AllowNull.get().getSchema(BaikeSchema.class))
                .withDataModel(ReflectData.get())
                .withConf(new Configuration())
                .withCompressionCodec(SNAPPY)
                .withWriteMode(OVERWRITE)
                .build()) {

            for (Baike baike : baikes) {
                BaikeSchema baikeSchema = BaikeSchema.builder().build();
                BeanUtil.copyProperties(baike, baikeSchema);

                baikeSchema.setCratedate(DateUtil.format(baike.getCratedate(), DatePattern.NORM_DATETIME_PATTERN));
                baikeSchema.setUpdatedate(DateUtil.format(baike.getUpdatedate(), DatePattern.NORM_DATETIME_PATTERN));
                writer.write(baikeSchema);
            }
        }
    }

    /**
     * 把parquet二进制文件转为数据
     */
    @Test
    public void test3() throws IOException {
        String parquetPath = "D:/test/demo.parquet";
        Map<String, List<String[]>> stringListMap = ParquetUtils.viewParquet(parquetPath, 10);

        for (String key : stringListMap.keySet()) {
            System.out.println("key=" + key + ",value = " + JSONUtil.toJsonPrettyStr(stringListMap.get(key)));
        }
    }
}
