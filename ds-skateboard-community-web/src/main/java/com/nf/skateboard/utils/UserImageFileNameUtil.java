package com.nf.skateboard.utils;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserImageFileNameUtil {

    public static String getDataTimeForFileName() {
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String format = sdf1.format(date);
        System.out.println("format = " + format);
        return format;
    }

    public static void main(String[] args) {
        UserImageFileNameUtil user = new UserImageFileNameUtil();
        System.out.println(user.getDataTimeForFileName());
        String pathhouzui = "sdfwef.jpg";
        System.out.println(pathhouzui.substring(pathhouzui.lastIndexOf(".")));
    }
}
