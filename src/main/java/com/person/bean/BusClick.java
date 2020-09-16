package com.person.bean;

import com.person.util.ExcelColumn;
import lombok.Data;

/**
 *实体类
 * 功能： 接收数据库中数据导出到excel表格中
 * @author
 */
@Data
public class BusClick {
    @ExcelColumn(value = "姓名", col = 1)
    private String name;

    @ExcelColumn(value = "电话", col = 2)
    private String tel;

    @ExcelColumn(value = "地址", col = 3)
    private String address;

    @ExcelColumn(value = "专业", col = 4)
    private String major;

    @ExcelColumn(value = "毕业学校", col = 5)
    private String school;

    @Override
    public String toString() {
        return "BusClick{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", major='" + major + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
