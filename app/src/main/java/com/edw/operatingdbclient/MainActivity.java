package com.edw.operatingdbclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.telecom.ConnectionService;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.edw.operatingdbclient.utils.CommonUtils;
import com.edw.operatingdbremoteservice.City;
import com.edw.operatingdbremoteservice.IRemoteDatabaseService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private com.edw.operatingdbclient.databinding.ActivityMainBinding vb;
    private ServiceConnection conn = null;
    private IRemoteDatabaseService databaseService = null;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vb = com.edw.operatingdbclient.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(vb.getRoot());
        initView();
        bindRemoteService();
        unBindRemoteService();
        startQuery();

    }

    private void initView() {

        myAdapter = new MyAdapter();
        vb.recy.setLayoutManager(new LinearLayoutManager(this));
        vb.recy.setHasFixedSize(true);
        vb.recy.addItemDecoration(new MyItemDecoration());
        vb.recy.setAdapter(myAdapter);
    }

    /**
     * 绑定远程服务
     */
    public void bindRemoteService() {
        vb.bindService.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction("com.edw.operatingdbremoteservice.RemoteDBService.Action");
            ComponentName cn = new ComponentName("com.edw.operatingdbremoteservice", "com.edw.operatingdbremoteservice.RemoteDBService");
            intent.setComponent(cn);
            if (conn == null) {
                conn = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        databaseService = IRemoteDatabaseService.Stub.asInterface(service);
                        Log.e(TAG, "----->databaseService" + databaseService);


                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                };
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
                CommonUtils.toast(this, "远程服务连接成功^_^");
            } else {
                CommonUtils.toast(this, "远程服务已连接~~");
            }
        });
    }

    /**
     * 解绑远程服务
     */
    public void unBindRemoteService() {
        vb.unbindService.setOnClickListener(v -> {
            if (conn != null) {
                unbindService(conn);
                conn = null;
                databaseService = null;
                myAdapter.removeAll();
                CommonUtils.toast(this, "远程服务解绑成功~~~");
            } else {
                CommonUtils.toast(this, "远程服务已经解绑！！！");
            }
        });
    }

    public void startQuery() {
        vb.queryAll.setOnClickListener(v -> {
            if (vb.inputCity.getVisibility() == View.VISIBLE) {
                vb.inputCity.setVisibility(View.GONE);
            }
            myAdapter.removeAll();
            try {
                if (databaseService != null) {
                    myAdapter.setData(databaseService.getAllCities());
                } else {
                    CommonUtils.toast(this, "服务已断开");
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

        });
        vb.queryProvince.setOnClickListener(v -> {
            myAdapter.removeAll();
            if (vb.inputCity.getVisibility() == View.GONE) {
                vb.inputCity.setVisibility(View.VISIBLE);
            }
            try {
                if (databaseService != null) {
                    myAdapter.setData(databaseService.getCityByProvince(vb.inputCity.getText().toString()));
                } else {
                    CommonUtils.toast(this, "服务已断开");
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

        });
        vb.queryId.setOnClickListener(v -> {
            myAdapter.removeAll();
            if (vb.inputCity.getVisibility() == View.GONE) {
                vb.inputCity.setVisibility(View.VISIBLE);
            }
            try {
                if (databaseService != null) {
                    List<City> cities = new ArrayList<>();
                    City city = databaseService.getCityById(Integer.parseInt(vb.inputCity.getText().toString()));
                    cities.add(city);
                    myAdapter.setData(cities);
                } else {
                    CommonUtils.toast(this, "服务已断开");
                }
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

        });

    }
}