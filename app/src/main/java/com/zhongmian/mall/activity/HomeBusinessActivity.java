package com.zhongmian.mall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.zhongmian.mall.R;
import com.zhongmian.mall.adapter.BusinessAdapter;
import com.zhongmian.mall.comment.Uris;
import com.zhongmian.mall.utils.BitmapCache;
import com.zhongmian.mall.utils.HttpUtils;
import com.zhongmian.mall.utils.JsonUtils;
import com.zhongmian.mall.view.MyGridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeBusinessActivity extends Activity {
    private ImageView imageView;
    private MyGridView gridView;
    private TextView textView_title;
    private String url;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private BusinessAdapter businessAdapter;
    private List<Map<String, String>> list = new ArrayList<Map<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home_business);
        requestQueue = Volley.newRequestQueue(this);
        imageLoader = new ImageLoader(requestQueue, BitmapCache.getBitmapCache());
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        initView();
    }

    private void initView() {
        textView_title = (TextView) findViewById(R.id.textView_home_business_title);
        imageView = (ImageView) findViewById(R.id.imageView_home_business);
        gridView = (MyGridView) findViewById(R.id.gridView_home_business);
        String topicId = url.substring(url.length() - 4);
        String params = "topicId=" + topicId + "&channel=2&version=2.1.3";
        businessAdapter = new BusinessAdapter(this);
        gridView.setAdapter(businessAdapter);
        new HomeBusinessAsyncTask().execute(Uris.HOME_BUSINESS_URL, params);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = list.get(position).get("url");
                String str[] = url.split("/");
                Intent intent1 = new Intent(HomeBusinessActivity.this, InformationActivity.class);
                intent1.putExtra("id",str[3]);
                intent1.putExtra("type","2");
                startActivity(intent1);
            }
        });

    }

    //异步加载JSON数据
    private class HomeBusinessAsyncTask extends AsyncTask<String, View, List<Map<String, String>>> {

        @Override
        protected List<Map<String, String>> doInBackground(String... params) {
            byte[] bytes = HttpUtils.postNetBytes(params[0], params[1]);
            if (bytes != null) {
                String json = new String(bytes);

                list = JsonUtils.jsonHomeBusiness(json);
                return list;
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Map<String, String>> maps) {
            super.onPostExecute(maps);
            //下载顶部图片并设置
            if (maps != null) {
                textView_title.setText(maps.get(0).get("title"));
                businessAdapter.setData(maps);
                ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                imageLoader.get(maps.get(0).get("sharePic"), imageListener);
                System.out.println("list.size" + list.size());

            }
        }
    }
}
