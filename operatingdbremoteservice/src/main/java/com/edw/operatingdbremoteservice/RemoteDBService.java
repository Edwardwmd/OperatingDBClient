package com.edw.operatingdbremoteservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.edw.operatingdbremoteservice.db.DBManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RemoteDBService extends Service {
    private static final String TAG = "RemoteDBService";
    private CityStub stub;

    public RemoteDBService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        stub = new CityStub(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}