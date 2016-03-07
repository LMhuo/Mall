package com.zhongmian.mall.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.zhongmian.mall.R;
import com.zhongmian.mall.adapter.BrandItemAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrandItemActivity extends Activity {
    private ListView listView;
    private BrandItemAdapter brandItemAdapter;
    private List<Map<String,String>> data;
    private int position1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_brand_item);
        init();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.listView_brand_item);
        brandItemAdapter = new BrandItemAdapter(this);
        listView.setAdapter(brandItemAdapter);
        Intent intent = getIntent();
        data = (List<Map<String, String>>) intent.getSerializableExtra("list");
        position1 = intent.getIntExtra("position",0);
        brandItemAdapter.setData(data);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView = (ImageView) view.findViewById(R.id.ImageView_listView_item);
                imageView.setEnabled(false);
                Intent intent = new Intent();
                intent.putExtra("name",data.get(position).get("name"));
                intent.putExtra("id",data.get(position).get("id"));
                intent.putExtra("position",position1);
                intent.putExtra("type",data.get(position).get("type"));
                setResult(RESULT_OK, intent);
                BrandItemActivity.this.finish();
            }
        });
    }

    public void onClick(View view){
        this.finish();
    }
}
