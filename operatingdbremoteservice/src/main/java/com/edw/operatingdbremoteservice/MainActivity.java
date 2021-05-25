package com.edw.operatingdbremoteservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.edw.operatingdbremoteservice.db.DBManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


}