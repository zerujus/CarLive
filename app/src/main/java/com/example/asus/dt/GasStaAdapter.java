package com.example.asus.dt;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by asus on 2016/5/11.
 */
public class GasStaAdapter extends BaseAdapter {

    private List<HashMap<String, String>> list;
    private Context context;
    private LayoutInflater layoutInflater;

    /**
     * 重写构造方法
     *
     * @param context 上下文
     * @param list    需要显示的数据
     */
    public GasStaAdapter(Context context, List<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 获取数据总数
     *
     * @return
     */
    @Override
    public int getCount() {
        return 10;
    }

    /**
     * 获取指定位置的数据
     *
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * 获取特定位置加载了数据的视图
     *
     * @param position    位置
     * @param convertView 可重用视图
     * @param parent      父元素
     * @return 列表项
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_gasstation, parent, false);


            viewHolder = new ViewHolder();
            viewHolder.textView_num = (TextView) convertView.findViewById(R.id.textView_num);
            viewHolder.textView_GSname = (TextView) convertView.findViewById(R.id.textView_GSname);
            viewHolder.textView_distance = (TextView) convertView.findViewById(R.id.textView_distance);
            viewHolder.textView_brandname = (TextView) convertView.findViewById(R.id.textView_brandname);
            viewHolder.textView_93 = (TextView) convertView.findViewById(R.id.textView_93);
            viewHolder.textView_90 = (TextView) convertView.findViewById(R.id.textView_90);
            viewHolder.textView_97 = (TextView) convertView.findViewById(R.id.textView_97);
            viewHolder.textView_0 = (TextView) convertView.findViewById(R.id.textView_0);
            viewHolder.textView_price0 = (TextView) convertView.findViewById(R.id.textView_price0);
            viewHolder.textView_price90 = (TextView) convertView.findViewById(R.id.textView_price90);
            viewHolder.textView_price93 = (TextView) convertView.findViewById(R.id.textView_price93);
            viewHolder.textView_price97 = (TextView) convertView.findViewById(R.id.textView_price97);
            viewHolder.textView_address = (TextView) convertView.findViewById(R.id.textView_address);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //加载position中的数据
        HashMap<String, String> map = list.get(position);

        //编号
        String num = map.get("num");
        viewHolder.textView_num.setText(num + ".");
        //加油站名字
        String name = map.get("name");
        viewHolder.textView_GSname.setText(name);
        //距离
        String distance = map.get("distance");
        viewHolder.textView_distance.setText(distance + "m");
        //品牌名称
        String brandname = map.get("brandname");
        viewHolder.textView_brandname.setText(brandname);

        //不同油的油价
        String prices = map.get("price");
        Log.d("start get prices", prices);
        String n_prices = "[" + prices + "]";
        try {
            JSONArray array = new JSONArray(n_prices);
            JSONObject object = array.getJSONObject(0);
            String price_90 = object.getString("E90");
            String price_93 = object.getString("E93");
            String price_97 = object.getString("E97");
            String price_0 = object.getString("E0");
            viewHolder.textView_price90.setText(price_90 + "元每升");
            viewHolder.textView_price93.setText(price_93 + "元每升");
            viewHolder.textView_price97.setText(price_97 + "元每升");
            viewHolder.textView_price0.setText(price_0 + "元每升");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String address = map.get("address");
        viewHolder.textView_address.setText(address);

        return convertView;
    }

    /**
     * 可重用视图
     */
    private static class ViewHolder {
        TextView textView_num;
        TextView textView_GSname;
        TextView textView_distance;
        TextView textView_brandname;
        TextView textView_93;
        TextView textView_0;
        TextView textView_90;
        TextView textView_97;
        TextView textView_price90;
        TextView textView_price93;
        TextView textView_price97;
        TextView textView_price0;
        TextView textView_address;
    }
}
