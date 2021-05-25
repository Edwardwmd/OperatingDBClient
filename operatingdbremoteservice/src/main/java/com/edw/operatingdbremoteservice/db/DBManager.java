package com.edw.operatingdbremoteservice.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import com.edw.operatingdbremoteservice.City;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.edw.operatingdbremoteservice.db.DBConfig.COLUMN_CITY_CODE;
import static com.edw.operatingdbremoteservice.db.DBConfig.COLUMN_CITY_NAME;
import static com.edw.operatingdbremoteservice.db.DBConfig.COLUMN_CITY_PINYIN;
import static com.edw.operatingdbremoteservice.db.DBConfig.COLUMN_CITY_PROVINCE;
import static com.edw.operatingdbremoteservice.db.DBConfig.COLUMN_ID;
import static com.edw.operatingdbremoteservice.db.DBConfig.DB_NAME;
import static com.edw.operatingdbremoteservice.db.DBConfig.TABLE_NAME;

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
public class DBManager implements IDatabaseOption {
    private static final String TAG = "DBManager";
    private static final int BUFFER_SIZE = 2048;
    public static String DB_PATH = null;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;


    private DBManager() {
    }

    private static class SingletonHolder {
        @SuppressLint("StaticFieldLeak")
        private static final DBManager HOLDER = new DBManager();
    }

    public static DBManager getInstance(Context mC) {
        mContext = mC;
        DB_PATH = File.separator + "data"
                + Environment.getDataDirectory().getAbsolutePath() + File.separator
                + mContext.getPackageName() + File.separator + "databases" + File.separator;
        copyDatabaseFromAssets();
        Log.e(TAG, "DB_PATH------> " + DB_PATH);
        return SingletonHolder.HOLDER;
    }


    private static void copyDatabaseFromAssets() {
        //创建存放数据库的文件夹
        File file = new File(DB_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        //将Assets中数据库写入在上面创建的文件夹里
        File dbFile = new File(DB_PATH + DB_NAME);
        if (!dbFile.exists()) {
            InputStream is = null;
            OutputStream os = null;
            try {
                is = mContext.getResources().getAssets().open(DB_NAME);
                os = new FileOutputStream(dbFile);
                byte[] bytes = new byte[BUFFER_SIZE];
                int length;
                while ((length = is.read(bytes, 0, bytes.length)) > 0) {
                    os.write(bytes, 0, length);
                }

            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            } finally {
                //关闭流
                try {
                    if (os != null) {
                        os.flush();
                        os.close();
                    }

                    if (is != null) {
                        is.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    @Override
    public Single<List<City>> queryCityByProvince(String province) {

        return Single.create(emitter -> {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
            List<City> cities = new ArrayList<>();
            String sql = "select * from " + TABLE_NAME + " where " + COLUMN_CITY_PROVINCE + " like ? ";
            try (Cursor cursor = db.rawQuery(sql, new String[]{"%" + province + "%"})) {
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                    String cityName = cursor.getString(cursor.getColumnIndex(COLUMN_CITY_NAME));
                    String cityPinyin = cursor.getString(cursor.getColumnIndex(COLUMN_CITY_PINYIN));
                    String cityCode = cursor.getString(cursor.getColumnIndex(COLUMN_CITY_CODE));
                    String cityProvince = cursor.getString(cursor.getColumnIndex(COLUMN_CITY_PROVINCE));
                    City city = new City(id, cityName, cityPinyin, cityCode, cityProvince);
                    cities.add(city);
                }
                emitter.onSuccess(cities);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                emitter.onError(e);
            }
        });
    }

    @Override
    public Single<City> queryCityById(int id) {
        return Single.create(emitter -> {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
            String sql = "select * from  " + TABLE_NAME + " where " + COLUMN_ID + " like ? ";
            try (Cursor cursor = db.rawQuery(sql, new String[]{"%" + id + "%"})) {
                while (cursor.moveToNext()) {
                    int c_Id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                    String c_Name = cursor.getString(cursor.getColumnIndex(COLUMN_CITY_NAME));
                    String c_PinYin = cursor.getString(cursor.getColumnIndex(COLUMN_CITY_PINYIN));
                    String c_Code = cursor.getString(cursor.getColumnIndex(COLUMN_CITY_CODE));
                    String c_Province = cursor.getString(cursor.getColumnIndex(COLUMN_CITY_PROVINCE));
                    City city = new City(c_Id, c_Name, c_PinYin, c_Code, c_Province);
                    emitter.onSuccess(city);
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                emitter.onError(e);
            }

        });
    }

    @Override
    public Single<List<City>> queryAllCities() {
        Log.e(TAG, "queryAllCities-->分当前线程：" + Thread.currentThread().getName() + "  主线程： " + Looper.getMainLooper().getThread().getName());
        return Single.create(emitter -> {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_NAME, null);
            List<City> cities = new ArrayList<>();
            String sql = "select * from " + TABLE_NAME;
            try (Cursor cursor = db.rawQuery(sql, null)) {
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                    String cityName = cursor.getString(cursor.getColumnIndex(COLUMN_CITY_NAME));
                    String cityPinyin = cursor.getString(cursor.getColumnIndex(COLUMN_CITY_PINYIN));
                    String cityCode = cursor.getString(cursor.getColumnIndex(COLUMN_CITY_CODE));
                    String cityProvince = cursor.getString(cursor.getColumnIndex(COLUMN_CITY_PROVINCE));
                    City city = new City(id, cityName, cityPinyin, cityCode, cityProvince);
                    cities.add(city);
                }
                emitter.onSuccess(cities);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                emitter.onError(e);
            }

        });
    }


}
