package com.zhongmian.mall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.zhongmian.mall.R;
import com.zhongmian.mall.utils.BitmapCache;
import com.zhongmian.mall.utils.LogUtils;
import com.zhongmian.mall.view.BrandView01;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by L on 2016/3/1 0001.
 */
public class BrandAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, String>> maps;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;


    public BrandAdapter(Context context) {
        this.context = context;
        maps = new ArrayList<Map<String, String>>();
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, BitmapCache.getBitmapCache());
    }

    public void setData(List<Map<String, String>> maps) {
        this.maps = maps;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return maps.size();
    }

    @Override
    public int getItemViewType(int position) {
        String type = maps.get(position).get("type");

        int t = 0;
        switch (type) {
            case "1":
                t = 0;
                break;
            case "2":
                t = 1;
                break;
            case "3":
                t = 2;
                break;
            case "4":
                t = 3;
                break;
            default:
                break;
        }
        return t;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return maps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String type = maps.get(position).get("type");
        ViewHolder1 viewHolder1 = null;
        ViewHolder2 viewHolder2 = null;
        ViewHolder3 viewHolder3 = null;
        ViewHolder4 viewHolder4 = null;
        if (convertView == null) {
            switch (type) {

                case "1":
                    convertView = LayoutInflater.from(context).inflate(R.layout.brand_item_layout1, parent, false);
                    viewHolder1 = new ViewHolder1();
                    viewHolder1.pic1 = (ImageView) convertView.findViewById(R.id.imageView_brand_item_left);
                    viewHolder1.logoPic1 = (ImageView) convertView.findViewById(R.id.imageView_brand_item_logo_left);
                    viewHolder1.pic2 = (ImageView) convertView.findViewById(R.id.imageView_brand_item_right);
                    viewHolder1.logoPic2 = (ImageView) convertView.findViewById(R.id.imageView_brand_item_logo_right);
                    convertView.setTag(viewHolder1);
                    break;
                case "2":
                    convertView = LayoutInflater.from(context).inflate(R.layout.brand_item_layout4, parent, false);
                    viewHolder2 = new ViewHolder2();
                    viewHolder2.name = (TextView) convertView.findViewById(R.id.textView__brand_item4_name);
                    convertView.setTag(viewHolder2);
                    break;
                case "3":
                    convertView = LayoutInflater.from(context).inflate(R.layout.brand_item_layout2, parent, false);
                    viewHolder3 = new ViewHolder3();
                    viewHolder3.allBrand = (TextView) convertView.findViewById(R.id.textView_brand_item4_allBrand);
                    convertView.setTag(viewHolder3);
                    break;
                case "4":
                    convertView = LayoutInflater.from(context).inflate(R.layout.brand_item_layout3, parent, false);
                    viewHolder4 = new ViewHolder4();
                    viewHolder4.brandView01 = (BrandView01) convertView.findViewById(R.id.brandView01_item03);
                    convertView.setTag(viewHolder4);
                    break;
                default:
                    break;
            }
        } else {
            switch (type) {
                case "1":
                    viewHolder1 = (ViewHolder1) convertView.getTag();
                    break;
                case "2":
                    viewHolder2 = (ViewHolder2) convertView.getTag();
                    break;
                case "3":
                    viewHolder3 = (ViewHolder3) convertView.getTag();
                    break;
                case "4":
                    viewHolder4 = (ViewHolder4) convertView.getTag();
                    break;
                default:
                    break;
            }
        }
        //设置数据
        switch (type) {
            case "1":
                ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(viewHolder1.pic1, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                imageLoader.get(maps.get(position).get("hyunPic1"), imageListener);
                ImageLoader.ImageListener imageListener1 = ImageLoader.getImageListener(viewHolder1.logoPic1, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                imageLoader.get(maps.get(position).get("logo1"), imageListener1);
                ImageLoader.ImageListener imageListener2 = ImageLoader.getImageListener(viewHolder1.pic2, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                imageLoader.get(maps.get(position).get("hyunPic2"), imageListener2);
                ImageLoader.ImageListener imageListener3 = ImageLoader.getImageListener(viewHolder1.logoPic2, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                imageLoader.get(maps.get(position).get("logo2"), imageListener3);
                viewHolder1.pic1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "点击了左边图片", Toast.LENGTH_SHORT).show();
                    }
                });
                viewHolder1.pic2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "点击了右边图片", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case "2":
                String name = maps.get(position).get("enName") + "/" + maps.get(position).get("name");
                viewHolder2.name.setText(name);
                viewHolder2.name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "点击了文字", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case "3":
                viewHolder3.allBrand.setText("全部品牌");
                break;
            case "4":
                viewHolder4.brandView01.setText(maps.get(position).get("brandShowFirstName"));
                break;
            default:
                break;
        }

        return convertView;

    }

    class ViewHolder1 {
        private ImageView pic1;
        private ImageView logoPic1;
        private ImageView pic2;
        private ImageView logoPic2;
    }

    class ViewHolder2 {
        private TextView name;
    }

    class ViewHolder3 {
        private TextView allBrand;
    }

    class ViewHolder4 {
        private BrandView01 brandView01;
    }
}
