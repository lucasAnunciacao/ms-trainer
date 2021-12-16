package com.trainer.mstrainer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static Date dateFormat(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date parse = null;

        {
            try {
                parse = simpleDateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return parse;
    }
}
