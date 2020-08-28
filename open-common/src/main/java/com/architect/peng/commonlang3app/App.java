package com.architect.peng.commonlang3app;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shangpeng
 * @version V1.0
 * @Date 2020/8/21 16:11
 */
public class App {
    public static void main(String[] args) {
        System.out.println(StringUtils.isAnyBlank("", "a", "b"));
        System.out.println(NumberUtils.createBigDecimal("12"));
        System.out.println(NumberUtils.min(1, 34, 5, 6));
        System.out.println(SystemUtils.IS_OS_LINUX);
        System.out.println(SystemUtils.USER_NAME);
        System.out.println(DateUtils.addDays(new Date(),1).toLocaleString());
        System.out.println(WordUtils.capitalizeFully("AsDcFjKl"));
        System.out.println(ClassUtils.getAllInterfacesForClass(String.class));
        Validate.notEmpty(new ArrayList<>(), "407:%s", "数组不能为空");
    }
}
