package com.cyt.pojo;

import com.cyt.annotation.NotNull;

/**
 * @author CaoYangTao
 * @date 2018/3/22  20:01
 */
public class Stu {
    @NotNull(value = "学生姓名", msg = "不能为空")
    private String stuName;

    @NotNull(value = "学生年龄", msg = "不能为空")
    private Integer stuAge;

    @NotNull(value = "学生梦想", msg = "不能为空")
    private Dream dream;

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
}
