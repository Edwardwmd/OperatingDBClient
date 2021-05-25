package com.edw.operatingdbclient.utils;

import android.content.Context;
import android.widget.Toast;

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
 * Description：    ToDo
 * <p>
 * **************************************************************************************************
 */
public class CommonUtils {

    public static void toast(Context mC, String msg) {
        Toast.makeText(mC, msg, Toast.LENGTH_SHORT).show();
    }
}
