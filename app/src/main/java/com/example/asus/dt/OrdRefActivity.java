package com.example.asus.dt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdRefActivity extends AppCompatActivity {

    private Spinner spinner_distance;
    private Spinner spinner_supplier;
    private ListView listView_gasstation;
    private List<HashMap<String, String>> list;
    private GasStaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ord_ref);

        Intent intent = getIntent();
        String response = intent.getStringExtra("response");
        listView_gasstation = (ListView) findViewById(R.id.listView_gasstation);

        list = analysis(response);
        adapter = new GasStaAdapter(this, list);
        listView_gasstation.setAdapter(adapter);

    }

    private List<HashMap<String, String>> analysis(String response) {

        list = new ArrayList<>();

        try {
            String str = "[" + response + "]";
            JSONArray array = new JSONArray(str);
            JSONObject object = array.getJSONObject(0);
            String result = object.getString("result");
            String str1 = "[" + result + "]";
            JSONArray array1 = new JSONArray(str1);
            JSONObject object1 = array1.getJSONObject(0);
            String data = object1.getString("data");
            JSONArray array2 = new JSONArray(data);
            int len = array2.length();
            for (int i = 0; i < len; i++) {
                JSONObject object2 = array2.getJSONObject(i);
                String id = object2.getString("id");
                String name = object2.getString("name");
                String area = object2.getString("area");
                String areaname = object2.getString("areaname");
                String address = object2.getString("address");
                String brandname = object2.getString("brandname");
                String type = object2.getString("type");
                String discount = object2.getString("distance");
                String exhaust = object2.getString("exhaust");
                String position = object2.getString("position");
                String lon = object2.getString("lon");
                String lat = object2.getString("lat");
                String price = object2.getString("price");
                String gastprice = object2.getString("gastprice");
                String fwlsmc = object2.getString("fwlsmc");
                String distance = object2.getString("distance");
                HashMap<String, String> map = new HashMap<>();
                map.put("num", String.valueOf(i + 1));
                map.put("id", id);
                map.put("name", name);
                map.put("area", area);
                map.put("areaname", areaname);
                map.put("address", address);
                map.put("brandname", brandname);
                map.put("type", type);
                map.put("discount", discount);
                map.put("exhaust", exhaust);
                map.put("position", position);
                map.put("lon", lon);
                map.put("lat", lat);
                map.put("price", price);
                map.put("gastprice", gastprice);
                map.put("fwlsmc", fwlsmc);
                map.put("distance", distance);
                list.add(map);
            }

//            OrderRefuelData refuelData = new OrderRefuelData(id, name, area, areaname,
//                    address, brandname, type, discount, exhaust, position,
//                    lon, lat, price, gastprice, fwlsmc, distance);
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
