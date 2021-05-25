package com.edw.operatingdbremoteservice;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;

import com.edw.operatingdbremoteservice.db.DBManager;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * **************************************************************************************************
 * Project Name:    OperatingDBClient
 * <p>
 * Date:            2021-05-25
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
public class CityStub extends IRemoteDatabaseService.Stub {
    private static final String TAG = "CityStub";
    private Context mC;

    public CityStub(Context mC) {
        this.mC = mC;
    }

    @Override
    public List<City> getCityByProvince(String province) throws RemoteException {
        return DBManager
                .getInstance(mC)
                .queryCityByProvince(province)
                .observeOn(Schedulers.io())
                .blockingGet();
    }

    @Override
    public City getCityById(int id) throws RemoteException {
        return DBManager
                .getInstance(mC)
                .queryCityById(id)
                .observeOn(Schedulers.io())
                .blockingGet();
    }

    @Override
    public List<City> getAllCities() throws RemoteException {
        return DBManager
                .getInstance(mC)
                .queryAllCities()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .blockingGet();
    }


}
