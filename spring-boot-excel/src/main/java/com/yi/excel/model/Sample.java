package com.yi.excel.model;

import io.github.biezhi.excel.plus.annotation.ExcelColumn;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * SampleData.xlsx Test Model
 *
 * @author biezhi
 * @date 2018-12-12
 */
@Data
public class Sample {

    @ExcelColumn(title = "日期", index = 0, datePattern = "M/d/yy")
    private LocalDate date;

    @ExcelColumn(title = "地区", index = 1)
    private String location;

    @ExcelColumn(title = "单位", index = 4)
    private int proportion;

    @ExcelColumn(title = "单位成本", index = 5)
    private double ss;

    @ExcelColumn(title = "总额", index = 6)
    private BigDecimal amount;

    public Sample() {
    }

    public Sample(LocalDate date, String location, int proportion) {
        this.date = date;
        this.location = location;
        this.proportion = proportion;
    }

}
