package com.lovejoy.core;

import android.support.annotation.StringDef;

/**
 * @author yuyh.
 * @date 2016/11/10.
 */
@StringDef({
        MDFonts.USER ,
        MDFonts.TAGS,
        MDFonts.CALENDAR,
        MDFonts.USERS,
        MDFonts.SAVE,
        MDFonts.ENVELOPE,
        MDFonts.PHONE,
        MDFonts.SCHOOL,
        MDFonts.STUDENT_NUM,
        MDFonts.PASSWORD

})
public @interface MDFonts {

    String USER  = "\uf007";
    String TAGS = "\uf02c";
    String CALENDAR = "\uf073";
    String USERS = "\uf0c0";
    String SAVE="\uf0c7";
    String ENVELOPE="\ue945";
    String PHONE="\ue958";
    String SCHOOL="\uea58";
    String STUDENT_NUM="\ueab6";
    String PASSWORD="\uead8";
}