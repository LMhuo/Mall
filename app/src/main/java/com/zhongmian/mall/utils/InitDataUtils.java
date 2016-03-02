package com.zhongmian.mall.utils;

import android.util.Log;

import com.zhongmian.mall.entity.Brand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by L on 2016/3/1 0001.
 */
public class InitDataUtils {
    public static List<Map<String, String>> brandData(List<Map<String, String>> list1,
                                                      List<Map<String, String>> list2) {
        LogUtils.isDebug=true;
        boolean flag = true;
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
        List<Map<String, String>> list3 = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        TreeSet<Brand> treeSet = new TreeSet<Brand>();
        List<Brand> listBrand = new ArrayList<Brand>();


        for (Map<String, String> map1 : list1) {
            if (flag) {
                map = new HashMap<String, String>();
                map.put("enName1", map1.get("enName"));
                map.put("hyunPic1", map1.get("hyunPic"));
                map.put("id1", map1.get("id"));
                map.put("logo1", map1.get("logo"));
                map.put("name1", map1.get("name"));
                map.put("type", "1");
                flag = false;
            } else {
                map.put("enName2", map1.get("enName"));
                map.put("hyunPic2", map1.get("hyunPic"));
                map.put("id2", map1.get("id"));
                map.put("logo2", map1.get("logo"));
                map.put("name2", map1.get("name"));
                maps.add(map);
                flag = true;
            }
        }


        Map<String, String> map3 = new HashMap<String,String>();
        map3.put("type", "3");
        maps.add(map3);

        for (Map<String, String> map2 : list2) {

            Brand brand = new Brand(map2.get("brandShowFirstName"), map2.get("enName"), map2.get("id"),
                    map2.get("logo"), map2.get("name"),"2");
            treeSet.add(brand);
        }

        //遍历treeSet获取首字母添加首字母
        String str ="sb";
        for(Brand brand :treeSet){
            String firstStr =brand.getBrandShowFirstName();
            if(!str.equals(firstStr)) {
                str = firstStr;
                Brand brand1 = new Brand(firstStr,firstStr,"4");
                listBrand.add(brand1);

            }
        }

        //遍历listBrand集合将集合中数据添加到treeSet集合中
        for(Brand brand : listBrand){
            treeSet.add(brand);
        }


        for (Brand brand2 : treeSet) {
            map = new HashMap<String, String>();
            map.put("brandShowFirstName",brand2.getBrandShowFirstName());
            map.put("enName",brand2.getEnName());
            map.put("id",brand2.getId());
            map.put("logo", brand2.getLogo());
            map.put("name",brand2.getName());
            map.put("type",brand2.getType());
            list3.add(map);

        }

        maps.addAll(list3);
        return maps;
    }
}
