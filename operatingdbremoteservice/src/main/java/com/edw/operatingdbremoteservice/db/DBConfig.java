package com.edw.operatingdbremoteservice.db;

import android.annotation.SuppressLint;
import android.os.Environment;

import java.io.File;

/**
 * **************************************************************************************************
 * Project Name:    OperatingDBClient
 * <p>
 * Date:            2021-05-24
 * <p>
 * Author：         EdwardWMD
 * <p>
 * Github:          https://github.com/Edwardwmd
 * <p>
 * Blog:            https://edwardwmd.github.io/
 * <p>
 * Description：    数据库配置类
 * <p>
 * **************************************************************************************************
 */
public class DBConfig {
    //数据库名称
    public static final String DB_NAME = "china_cities_v2.db";
    //数据库表名称
    public static final String TABLE_NAME = "cities";

    //数据库表字段名
    public static final String COLUMN_ID = "id";

    public static final String COLUMN_CITY_NAME = "c_name";

    public static final String COLUMN_CITY_PINYIN = "c_pinyin";

    public static final String COLUMN_CITY_CODE = "c_code";

    public static final String COLUMN_CITY_PROVINCE = "c_province";







}
