package com.zhongmian.mall.utils;


import android.util.Log;

import com.zhongmian.mall.comment.Uris;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
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

    public static List<Map<String, String>> jsonBrand1(String json) {
        LogUtils.i("brand", "json" + json);
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                String enName = jsonObject.optString("enName");
                String hyunPic = Uris.IMAGE_URL + jsonObject.optString("hyunPic");
                String id = jsonObject.optString("id");
                String logo = Uris.IMAGE_URL + jsonObject.optString("logo");
                map.put("hyunPic", hyunPic);
                map.put("enName", enName);
                map.put("id", id);
                map.put("logo", logo);
                maps.add(map);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.i("brand", "maps:" + maps.toString());
        return maps;
    }

    public static List<Map<String, String>> jsonBrand2(String json) {
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Map<String, String> map = new HashMap<String, String>();
                String brandShowFirstName = jsonObject.optString("brandShowFirstName");
                String enName = jsonObject.optString("enName");
                String id = jsonObject.optString("id");
                String logo = jsonObject.optString("logo");
                String name = jsonObject.optString("name");
                map.put("brandShowFirstName", brandShowFirstName);
                map.put("enName", enName);
                map.put("id", id);
                map.put("logo", logo);
                map.put("name", name);
                maps.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return maps;
    }

    public static List<Map<String, String>> jsonDepartment(String json) {
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();

        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Map<String, String> map = new HashMap<String, String>();
                String id = jsonObject.optString("id");
                String name = jsonObject.optString("name");
                map.put("id", id);
                map.put("name", name);
                maps.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return maps;
    }

    public static Map<String, String> jsonDepartment2(String json) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            String subList = jsonObject.optString("subList");
            String name = jsonObject.optString("name");
            String id = jsonObject.optString("id");
            map.put("subList", subList);
            map.put("name", name);
            map.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static List<Map<String, String>> jsonDepartment3(String json) {
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();

        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                for (int j = 0; j < jsonArray1.length(); j++) {
                    Map<String, String> map = new HashMap<String, String>();
                    JSONObject jsonObject = jsonArray1.optJSONObject(j);

                    String icon = jsonObject.optString("icon");

                    if (!"".equals(icon)) {
                        icon = Uris.IMAGE_URL + icon + Uris.IMAGE_URL2;
                    }

                    String id = jsonObject.optString("id");
                    String name = jsonObject.optString("name");
                    map.put("icon", icon);
                    map.put("id", id);
                    map.put("name", name);
                    maps.add(map);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return maps;
    }

    public static List<Map<String, String>> jsonHomeBusiness(String json) {
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            String title = jsonObject.optString("title");
            String sharePic = Uris.IMAGE_URL + jsonObject.optString("sharePic");

            JSONArray jsonArray = jsonObject.optJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String type = jsonObject1.optString("type");
                if ("6".equals(type)) {
                    JSONArray jsonArray1 = jsonObject1.optJSONArray("rows");


                    for (int j = 0; j < jsonArray1.length(); j++) {
                        Map<String, String> map = new HashMap<String, String>();
                        JSONObject jsonObject2 = jsonArray1.optJSONObject(j);
                        String price = jsonObject2.optString("price");
                        String haveGoods = jsonObject2.optString("haveGoods");
                        String prodname = jsonObject2.optString("prodname");
                        String pic = Uris.IMAGE_URL + jsonObject2.optString("pic");
                        String url = jsonObject2.optString("url");
                        map.put("price", price);
                        map.put("title", title);
                        map.put("sharePic", sharePic);
                        map.put("haveGoods", haveGoods);
                        map.put("prodname", prodname);
                        map.put("pic", pic);
                        map.put("url", url);
                        maps.add(map);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return maps;
    }

    public static List<Map<String, String>> jsonBrandBusiness1(String json) {
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String frontPic = Uris.IMAGE_URL + jsonObject1.getString("frontPic");
                String prodName = jsonObject1.getString("prodName");
                String mobilePrice = jsonObject1.getString("mobilePrice");
                String spuId = jsonObject1.getString("spuId");
                String prodId = jsonObject1.getString("prodId");
                String brandName = jsonObject1.getString("brandName");
                map.put("brandName", brandName);
                map.put("pic", frontPic);
                map.put("prodname", prodName);
                map.put("price", mobilePrice);
                map.put("spuId", spuId);
                map.put("prodId", prodId);
                maps.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("maps::"+maps.toString());
        return maps;
    }

    public static List<Map<String, String>> jsonBrandBusiness2(String json) {
        List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("sc");
            for (int i = 0; i < jsonArray.length(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                String id = jsonObject1.optString("id");
                String name = jsonObject1.optString("name");
                map.put("id", id);
                map.put("name", name);
                maps.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return maps;
    }

    public static List<Map<String, Object>> jsonBrandBusiness3(String json) {
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("skuSpec");

            for (int i = 0; i < jsonArray.length(); i++) {
                List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                Map<String, Object> bigMap = new HashMap<String, Object>();
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                String cateId = jsonObject1.getString("cateId");
                String cateName = jsonObject1.getString("cateName");
                JSONArray jsonArray1 = jsonObject1.optJSONArray("list");

                for (int j = 0; j < jsonArray1.length(); j++) {
                    Map<String, String> map = new HashMap<String, String>();
                    JSONObject jsonObject2 = jsonArray1.optJSONObject(j);
                    String id = jsonObject2.optString("id");
                    String name = jsonObject2.optString("name");
                    map.put("id", id);
                    map.put("name", name);
                    map.put("type", "skuSpec");
                    list.add(map);

                }
                bigMap.put("cateId", cateId);
                bigMap.put("cateName", cateName);
                bigMap.put("list", list);
                maps.add(bigMap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("spuAttr");

            for (int i = 0; i < jsonArray.length(); i++) {
                List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                Map<String, Object> bigMap = new HashMap<String, Object>();
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                String cateId = jsonObject1.getString("cateId");
                String cateName = jsonObject1.getString("cateName");
                JSONArray jsonArray1 = jsonObject1.optJSONArray("list");

                for (int j = 0; j < jsonArray1.length(); j++) {
                    Map<String, String> map = new HashMap<String, String>();
                    JSONObject jsonObject2 = jsonArray1.optJSONObject(j);
                    String id = jsonObject2.optString("id");
                    String name = jsonObject2.optString("name");
                    map.put("id", id);
                    map.put("name", name);
                    map.put("type", "spuAttr");
                    list.add(map);

                }
                bigMap.put("cateId", cateId);
                bigMap.put("cateName", cateName);
                bigMap.put("list", list);
                maps.add(bigMap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("spuSpec");

            for (int i = 0; i < jsonArray.length(); i++) {
                List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                Map<String, Object> bigMap = new HashMap<String, Object>();
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                String cateId = jsonObject1.getString("cateId");
                String cateName = jsonObject1.getString("cateName");
                JSONArray jsonArray1 = jsonObject1.optJSONArray("list");

                for (int j = 0; j < jsonArray1.length(); j++) {
                    Map<String, String> map = new HashMap<String, String>();
                    JSONObject jsonObject2 = jsonArray1.optJSONObject(j);
                    String id = jsonObject2.optString("id");
                    String name = jsonObject2.optString("name");
                    map.put("id", id);
                    map.put("name", name);
                    map.put("type", "spuSpec");
                    list.add(map);

                }
                bigMap.put("cateId", cateId);
                bigMap.put("cateName", cateName);
                bigMap.put("list", list);
                maps.add(bigMap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return maps;
    }

    //
    public static Map<String, Object> jsonInformation(String json) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, String> map2 = new HashMap<String, String>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();
        List<String> list3 = new ArrayList<String>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            String brandName = jsonObject.optString("brandName");
            String name = jsonObject.optString("name");
            map.put("brandName", brandName);
            map.put("name", name);
            JSONArray jsonArray = jsonObject.optJSONArray("spuItems");
            list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < jsonArray.length(); i++) {
                map1 = new HashMap<String, Object>();
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                String attrs = jsonObject1.optString("attrs");
                String id = jsonObject1.optString("id");
                String mobilePrice = jsonObject1.optString("mobilePrice");
                String marketPrice = jsonObject1.optString("marketPrice");
                String detail = jsonObject1.optString("detail");
                map1.put("attrs", attrs);
                map1.put("id", id);
                map1.put("mobilePrice", mobilePrice);
                map1.put("marketPrice", marketPrice);
                map1.put("detail",detail);
                JSONArray jsonArray1 = jsonObject1.optJSONArray("skuItems");
                list2 = new ArrayList<Map<String, String>>();
                for (int j = 0; j < jsonArray1.length(); j++) {
                    map2 = new HashMap<String, String>();
                    JSONObject jsonObject2 = jsonArray1.optJSONObject(j);
                    String id2 = jsonObject2.optString("id");
                    String marketPrice2 = jsonObject2.optString("marketPrice");
                    String mobilePrice2 = jsonObject2.optString("mobilePrice");
                    String specName2 = jsonObject2.optString("specName");
                    String specValueName2 = jsonObject2.optString("specValueName");
                    String storeNum2 = jsonObject2.optString("storeNum");
                    String code= jsonObject2.optString("code");
                    String salePrice2= jsonObject2.optString("salePrice");

                    map2.put("id2", id2);
                    map2.put("marketPrice2", marketPrice2);
                    map2.put("mobilePrice2", mobilePrice2);
                    map2.put("specName2", specName2);
                    map2.put("specValueName2", specValueName2);
                    map2.put("storeNum2", storeNum2);
                    map2.put("code",code);
                    map2.put("salePrice2",salePrice2);
                    list2.add(map2);
                }
                map1.put("skuItems", list2);
                String specName = jsonObject1.optString("specName");
                String specValueName = jsonObject1.optString("specValueName");
                map1.put("specName", specName);
                map1.put("specValueName", specValueName);
                JSONArray jsonArray2 = jsonObject1.optJSONArray("spuImgs");
                list3 = new ArrayList<String>();
                for (int k = 0; k < jsonArray2.length(); k++) {
                    list3.add(Uris.IMAGE_URL+jsonArray2.optString(k));
                }
                map1.put("spuImgs", list3);

                list.add(map1);
            }
            map.put("spuItems", list);
            String subtitle = jsonObject.optString("subtitle");
            map.put("subtitle", subtitle);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("jsonmap"+map.toString());
        return map;
    }

}