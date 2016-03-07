package com.zhongmian.mall.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;

import com.zhongmian.mall.R;
import com.zhongmian.mall.adapter.Brand_business_ListViewAdapter;
import com.zhongmian.mall.adapter.BusinessAdapter;
import com.zhongmian.mall.adapter.BusinessListViewAdapter;
import com.zhongmian.mall.comment.Uris;
import com.zhongmian.mall.utils.HttpUtils;
import com.zhongmian.mall.utils.JsonUtils;
import com.zhongmian.mall.view.MyFlowLayout;
import com.zhongmian.mall.view.MyGridView;
import com.zhongmian.mall.view.MyListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DepartmentBusinessActivity extends Activity implements View.OnClickListener {
    private MyGridView myGridView;
    private MyListView myListView;
    private BusinessAdapter businessAdapter;
    private BusinessListViewAdapter businessListViewAdapter;
    private String params;
    private int pageNo;
    private boolean isScrollBottom;
    private int flag;
    private boolean modeFlag;
    private String id;
    private TextView textView_brandName;
    private DrawerLayout drawerLayout;
    private TextView textViewComprehensive;
    private TextView textViewSales;
    private TextView textViewPrice;
    private TextView textView;
    private boolean priceFlag;
    private MyFlowLayout myFlowLayout;
    private List<Map<String, String>> flowMaps;
    private MyListView myListViewDrawer;
    private Brand_business_ListViewAdapter brand_business_ListViewAdapter;
    private List<Map<String, Object>> listMaps;
    private List<String> skuSpec = new ArrayList<String>();
    private List<String> spuAttr = new ArrayList<String>();
    private List<String> spuSpec = new ArrayList<String>();
    private List<Map<String, String>> mapsData = new ArrayList<Map<String, String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_brand_business);
        initView();
        init();
        initDrawer();
    }


    private void initView() {
        myGridView = (MyGridView) findViewById(R.id.myGridView_brand_business);
        myListView = (MyListView) findViewById(R.id.myListView_brand_business);
        textView_brandName = (TextView) findViewById(R.id.textView_brand_business_brandName);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_brand_business);
        textViewComprehensive = (TextView) findViewById(R.id.textView_brand_business_comprehensive);
        textViewComprehensive.setEnabled(false);
        textViewSales = (TextView) findViewById(R.id.textView_brand_business_sales);
        textViewPrice = (TextView) findViewById(R.id.textView_brand_business_price);
        myFlowLayout = (MyFlowLayout) findViewById(R.id.myFlowLayout_brand_business);
        myListViewDrawer = (MyListView) findViewById(R.id.myListView_drawer_brand);
    }


    private void init() {

        final Intent intent = getIntent();
        id = intent.getStringExtra("id");
        businessAdapter = new BusinessAdapter(this);
        businessListViewAdapter = new BusinessListViewAdapter(this);
        modeFlag = false;
        priceFlag = false;
        myGridView.setAdapter(businessAdapter);
        myListView.setAdapter(businessListViewAdapter);
        myListView.setVisibility(View.GONE);


        flag = 1;
        pageNo = 1;
        params = "searchPara={%22startPage%22:" + pageNo + ",%22pageSize%22:10,%22channel%22:2,%22sc%22:[{%22id%22:%22" + id + "%22}],%22sortField%22:%22%22}";

        new BrandBusinessAsyncTask().execute(Uris.BRAND_BUSINESS_URL, params);
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("mapsData" + mapsData);
                Intent intent1 = new Intent(DepartmentBusinessActivity.this, InformationActivity.class);
                intent1.putExtra("id", mapsData.get(position).get("prodId"));
                intent1.putExtra("type", "1");
                startActivity(intent1);
            }
        });
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("mapsData" + mapsData);
                Intent intent1 = new Intent(DepartmentBusinessActivity.this, InformationActivity.class);
                intent1.putExtra("id", mapsData.get(position).get("prodId"));
                intent1.putExtra("type", "1");
                startActivity(intent1);
            }
        });

        //设置监听器进行分页
        myGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isScrollBottom && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    pageNo++;
                    params = "searchPara={%22startPage%22:" + pageNo + ",%22pageSize%22:10,%22channel%22:2,%22sc%22:[{%22id%22:%22" + id + "%22}],%22sortField%22:%22%22}";
                    flag = 2;
                    new BrandBusinessAsyncTask().execute(Uris.BRAND_BUSINESS_URL, params);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isScrollBottom = firstVisibleItem + visibleItemCount == totalItemCount;
            }
        });
    }


    private static final int REQUEST_CODE = 100;

    private void initDrawer() {
        brand_business_ListViewAdapter = new Brand_business_ListViewAdapter(this);
        myListViewDrawer.setAdapter(brand_business_ListViewAdapter);
        myListViewDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DepartmentBusinessActivity.this, BrandItemActivity.class);
                List<Map<String, String>> list = (List<Map<String, String>>) listMaps.get(position).get("list");
                intent.putExtra("list", (Serializable) list);
                intent.putExtra("position", position);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }


    //点击事件
    public void onClick(View view) {
        int viewid = view.getId();
        switch (viewid) {
            case R.id.imageView_brand_business_back:
                finish();
                break;
            case R.id.textView_brand_business_screen:

                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.textView_brand_business_comprehensive:
                textViewComprehensive.setEnabled(false);
                textViewSales.setEnabled(true);
                textViewPrice.setTextColor(0xFF757575);
                flag = 1;
                pageNo = 1;
                params = "searchPara={%22startPage%22:" + pageNo + ",%22pageSize%22:10,%22channel%22:2,%22sc%22:[{%22id%22:%22" + id + "%22}],%22sortField%22:%22%22}";
                new BrandBusinessAsyncTask().execute(Uris.BRAND_BUSINESS_URL, params);
                break;
            case R.id.textView_brand_business_sales:
                textViewComprehensive.setEnabled(true);
                textViewSales.setEnabled(false);
                textViewPrice.setTextColor(0xFF757575);
                pageNo = 1;
                params = "searchPara={%22startPage%22:" + pageNo + ",%22pageSize%22:10,%22channel%22:2,%22sc%22:[{%22id%22:%22" + id + "%22}],%22sortField%22:%22salesNum%22}";
                new BrandBusinessAsyncTask().execute(Uris.BRAND_BUSINESS_URL, params);
                break;
            case R.id.textView_brand_business_price:

                textViewComprehensive.setEnabled(true);
                textViewSales.setEnabled(true);
                textViewPrice.setTextColor(0xFFD50000);
                if (priceFlag) {
                    //价格升序
                    System.out.println("price id=" + id);
                    textViewPrice.setText("价格↑");
                    pageNo = 1;
                    params = "searchPara={%22startPage%22:" + pageNo + ",%22pageSize%22:10,%22channel%22:2,%22sc%22:[{%22id%22:%22" + id + "%22}],%22sortField%22:%22salePrice%22,%22sortDir%22:%220%22}";
                    new BrandBusinessAsyncTask().execute(Uris.BRAND_BUSINESS_URL, params);
                    priceFlag = false;
                } else {
                    //价格降序
                    System.out.println("price id=" + id);
                    textViewPrice.setText("价格↓");

                    pageNo = 1;
                    params = "searchPara={%22startPage%22:" + pageNo + ",%22pageSize%22:10,%22channel%22:2,%22sc%22:[{%22id%22:%22" + id + "%22}],%22sortField%22:%22salePrice%22,%22sortDir%22:%221%22}";
                    new BrandBusinessAsyncTask().execute(Uris.BRAND_BUSINESS_URL, params);
                    priceFlag = true;
                }
                break;
            case R.id.imageView_brand_business_mode:
                if (modeFlag) {
                    myGridView.setVisibility(View.VISIBLE);
                    myListView.setVisibility(View.GONE);
                    modeFlag = false;
                } else {
                    myGridView.setVisibility(View.GONE);
                    myListView.setVisibility(View.VISIBLE);
                    modeFlag = true;
                }

                break;
            default:
                break;
        }

    }

    //异步加载JSON数据
    public class BrandBusinessAsyncTask extends AsyncTask<String, Void, List<Map<String, String>>> {

        @Override
        protected List<Map<String, String>> doInBackground(String... params) {
            byte[] bytes = HttpUtils.postNetBytes(params[0], params[1]);
            System.out.println("url-------:" + params[0] + "?" + params[1]);
            if (bytes != null) {
                String json = new String(bytes);
                mapsData = JsonUtils.jsonBrandBusiness1(json);
//                flowMaps = JsonUtils.jsonBrandBusiness2(json);
                listMaps = JsonUtils.jsonBrandBusiness3(json);
                return mapsData;
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Map<String, String>> maps) {

            super.onPostExecute(maps);
            textView_brandName.setText(maps.get(0).get("brandName"));
            switch (flag) {
                case 1:
                    businessAdapter.setData(maps);
                    businessListViewAdapter.setData(maps);
                    break;
                case 2:
                    businessAdapter.addData(maps);
                    businessListViewAdapter.addData(maps);
                    break;
                default:
                    break;
            }

            brand_business_ListViewAdapter.setData(listMaps);

        }
    }

    private String name;
    private int position1;
    private String type;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST_CODE == requestCode) {
            if (resultCode == RESULT_OK) {
                name = data.getStringExtra("name");
                String id = data.getStringExtra("id");
                position1 = data.getIntExtra("position", 0);
                type = data.getStringExtra("type");

                switch (type) {
                    case "skuSpec":
                        skuSpec.add(id);
                        break;
                    case "spuAttr":
                        skuSpec.add(id);
                        break;
                    case "spuSpec":
                        skuSpec.add(id);
                        break;
                }

            }
        }
    }


    public void button_confirm(View view) {
        StringBuffer strSkuSpec = new StringBuffer();
        StringBuffer strSpuAttr = new StringBuffer();
        StringBuffer strSpuSpec = new StringBuffer();
        for (String id : skuSpec) {
            strSkuSpec.append("{%22id%22:%22\"+id+\"%22}");
        }

        for (String id : spuAttr) {
            strSpuAttr.append("{%22id%22:%22\"+id+\"%22}");
        }

        for (String id : spuSpec) {
            strSpuSpec.append("{%22id%22:%22\"+id+\"%22}");
        }

        String params = "searchPara={%22startPage%22:1,%22pageSize%22:10,%22sortField%22:%22%22,%22channel%22:2,%22sc%22:[{%22id%22:%22401%22}],%22sc%22:[{%22id%22:%22505%22}],%22skuSpec%22:[" + skuSpec + "],%22spuSpec%22:[" + spuSpec + "],%22spuAttr%22:[" + spuAttr + "]}";
        new BrandBusinessAsyncTask().execute(Uris.BRAND_BUSINESS_URL, params);
    }

}
