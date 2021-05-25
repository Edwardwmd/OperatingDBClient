// IRemoteDatabaseService.aidl
package com.edw.operatingdbremoteservice;

// Declare any non-default types here with import statements
import com.edw.operatingdbremoteservice.City;
import java.util.List;

interface IRemoteDatabaseService {
    //根据省会获取该省市下的下级城市
   List<City> getCityByProvince(String province);
    //根据Id获取对应的城市
   City getCityById(int id);
    //获取所有城市
   List<City> getAllCities();

}