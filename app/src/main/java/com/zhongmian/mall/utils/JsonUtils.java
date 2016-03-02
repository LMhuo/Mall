package com.zhongmian.mall.utils;


import android.util.Log;

import com.zhongmian.mall.comment.Uris;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by L on 2016/2/28 0028.
 */
public class JsonUtils {
    public static List<Map<String, String>> jsonHome(String json) {
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        JSONObject jsonObject1 = null;
        try {
            jsonObject1 = new JSONObject(json);
            JSONArray jsonArray = jsonObject1.optJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.optJSONObject(i);
                JSONArray jsonArray2 = new JSONArray();
                jsonArray2 = jsonObject2.optJSONArray("rows");
                for (int j = 0; j < jsonArray2.length(); j++) {

                    JSONObject jsonObject3 = jsonArray2.optJSONObject(j);
                    map = new HashMap<String, String>();
                    map.put("alt", jsonObject3.optString("alt"));
                    String imageUrl = Uris.IMAGE_URL + jsonObject3.optString("pic");
                    map.put("pic", imageUrl);
                    map.put("url", jsonObject3.optString("url"));
                    map.put("prodname", jsonObject3.optString("prodname"));
                    map.put("price", jsonObject3.optString("price"));
                    map.put("type", jsonObject2.optString("type"));
                    maps.add(map);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return maps;

    }

    public static List<Map<String, String>> jsonBrand1(String json){
        LogUtils.i("brand","json"+json);
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i =0;i<jsonArray.length();i++){
                Map<String,String> map = new HashMap<String,String>();
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                String enName = jsonObject.optString("enName");
                String hyunPic = Uris.IMAGE_URL+jsonObject.optString("hyunPic");
                String id = jsonObject.optString("id");
                String logo = Uris.IMAGE_URL+jsonObject.optString("logo");
                map.put("hyunPic",hyunPic);
                map.put("enName",enName);
                map.put("id",id);
                map.put("logo",logo);
                maps.add(map);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.i("brand","maps:"+maps.toString());
        return maps;
    }

    public static List<Map<String, String>> jsonBrand2(String json){
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Map<String,String> map = new HashMap<String,String>();
                String brandShowFirstName = jsonObject.optString("brandShowFirstName");
                String enName = jsonObject.optString("enName");
                String id = jsonObject.optString("id");
                String logo = jsonObject.optString("logo");
                String name = jsonObject.optString("name");
                map.put("brandShowFirstName",brandShowFirstName);
                map.put("enName",enName);
                map.put("id",id);
                map.put("logo", logo);
                map.put("name",name);
                maps.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return maps;
    }

    public static List<Map<String,String>>jsonDepartment(String json){
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();

        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Map<String,String> map = new HashMap<String,String>();
                String id = jsonObject.optString("id");
                String name = jsonObject.optString("name");
                map.put("id",id);
                map.put("name",name);
                maps.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return maps;
    }
    public static Map<String,String>jsonDepartment2(String json){
        Map<String, String> map = new HashMap<String, String>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            String subList = jsonObject.optString("subList");
            String name = jsonObject.optString("name");
            String id = jsonObject.optString("id");
            map.put("subList",subList);
            map.put("name",name);
            map.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }
    public static List<Map<String,String>>jsonDepartment3(String json){
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();

        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++){
                JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                for(int j =0;j<jsonArray1.length();j++){
                    Map<String,String>map =new HashMap<String,String>();
                    JSONObject jsonObject = jsonArray1.optJSONObject(j);

                    String icon = jsonObject.optString("icon");

                    if(!"".equals(icon)){
                        icon = Uris.IMAGE_URL+icon+Uris.IMAGE_URL2;
                    }

                    String id = jsonObject.optString("id");
                    String name = jsonObject.optString("name");
                    map.put("icon",icon);
                    map.put("id",id);
                    map.put("name",name);
                    maps.add(map);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return maps;
    }

}
