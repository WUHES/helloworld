package wuhe_view.com.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import org.json.JSONObject;
class text
{

}
public class MainActivity extends Activity implements View.OnClickListener {

    private Button MBt;
    private Button MvolleyBt;
    private JsonObjectRequest jsonObjectRequest;
    private static RequestQueue mQueue ;
    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {

            // TODO 在这边可以自定义图片加载库来加载 ImageView，例如 Glide、Picasso、ImageLoader 等
            Glide.with(context).load(path).into(imageView);
        }
    };
    public void startVolley() {
         jsonObjectRequest = new JsonObjectRequest("http://m.weather.com.cn/data/101010100.html", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);

    }

    private void startImageview()
    {
        ImgSelActivity.startActivity(this, config, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initvolley();
        initListenter();

    }

    private void initvolley() {

        mQueue= Volley.newRequestQueue(this);

    }

    private void initListenter() {
        MBt.setOnClickListener(this);
        MvolleyBt.setOnClickListener(this);
    }

    private void init() {
         MBt = (Button) findViewById(R.id.Bt_im);
        MvolleyBt = (Button) findViewById(R.id.Bt_volley);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.Bt_im:
               startImageview();
            case R.id.Bt_volley:
                startVolley();
            break;
        }
    }
    ImgSelConfig config = new ImgSelConfig.Builder(loader)

            // 是否多选
            .multiSelect(false)
            // “确定”按钮背景色
            .btnBgColor(Color.GRAY)
            // “确定”按钮文字颜色
            .btnTextColor(Color.BLUE)
            // 使用沉浸式状态栏
            .statusBarColor(Color.parseColor("#3F51B5"))
            // 返回图标 ResId
            .backResId(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_mtrl_am_alpha)
            // 标题
            .title("图片")
            // 标题文字颜色
            .titleColor(Color.WHITE)
            // TitleBar 背景色
            .titleBgColor(Color.parseColor("#3F51B5"))
            // 裁剪大小。needCrop 为 true 的时候配置
            .cropSize(1, 1, 200, 200)
            .needCrop(true)
            // 第一个是否显示相机
            .needCamera(false)
            // 最大选择图片数量

            .maxNum(9)
            .build();


}
