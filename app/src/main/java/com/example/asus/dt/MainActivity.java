package com.example.asus.dt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "response";
    private ActionBar actionBar;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //getActionBar
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //抽屉把手
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);

                //重新创建选项菜单
                invalidateOptionsMenu();

            }
        };
        //同步状态,更新抽屉图标
        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 获取周围加油站信息
     * @param v
     */
    public void OrderRefuel(View v) {

        //http://apis.juhe.cn/oil/local?key=您申请的APPKEY&lon=116.403119&lat=39.916042&format=2&r=3000

        String httpkey = "key=6c8136c696f3441e6b2f6e2d54f067f6";
        String lon = "&lon=112.999087";
        String lat = "&lat=28.134519";
        String r = "&r=5000";
        String httpUrl = "http://apis.juhe.cn/oil/local?" + httpkey + lon + lat + r;

        //抽屉关闭
        drawerLayout.closeDrawer(GravityCompat.START);

        Net.getInstance(getApplicationContext()).addRequestToQueue(new StringRequest(Request.Method.GET, httpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //获取json对象
                Log.d(TAG, response);
                //跳转到数据显示界面
                Intent intent = new Intent(MainActivity.this, OrdRefActivity.class);
                intent.putExtra("response", response);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "101", Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
