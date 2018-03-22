package com.cyt.pojo;

import com.cyt.annotation.LengthValidate;
import com.cyt.annotation.NotNull;

/**
 * @author CaoYangTao
 * @date 2018/3/22  20:01
 */
public class Stu {
    @NotNull(value = "学生姓名", msg = "不能为空")
    @LengthValidate(value = "学生姓名", minLength = 2, maxLength = 10)
    private String stuName;

    @NotNull(value = "学生年龄", msg = "不能为空")
    private Integer stuAge;

    @NotNull(value = "学生梦想", msg = "不能为空")
    private Dream dream;

    private Integer stuHeight;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    public Dream getDream() {
        return dream;
    }

    public void setDream(Dream dream) {
        this.dream = dream;
    }

    public Integer getStuHeight() {
        return stuHeight;
    }

    public void setStuHeight(Integer stuHeight) {
        this.stuHeight = stuHeight;
    }
}
