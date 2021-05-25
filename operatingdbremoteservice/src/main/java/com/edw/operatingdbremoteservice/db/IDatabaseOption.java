package com.edw.operatingdbremoteservice.db;

import com.edw.operatingdbremoteservice.City;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

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
public interface IDatabaseOption {
    /**
     * 根据省会获取其下级城市
     * @param province 省会
     * @return 下级城市
     */
     Single<List<City>> queryCityByProvince(String province);

    /**
     * 根据Id获取对应城市
     * @param id id
     * @return 城市
     */
    Single<City> queryCityById(int id);

    /**
     * 获取所有城市
     * @return 所有城市
     */
    Single<List<City>> queryAllCities();


}
