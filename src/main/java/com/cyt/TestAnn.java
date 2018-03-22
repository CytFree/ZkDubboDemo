package com.cyt;

import com.cyt.utils.ValidateUtils;
import com.cyt.pojo.Stu;

/**
 * @author CaoYangTao
 * @date 2018/3/22  20:20
 */
public class TestAnn {
    public static void main(String[] args) {
        Stu stu = new Stu();
        stu.setStuName("cyt");
        stu.setStuAge(11);

        try {
            ValidateUtils.validate(stu);
            System.out.println("验证通过！");
        } catch (Exception e) {
            System.out.print("验证失败：");
            e.printStackTrace();
        }
    }
}
