package com.bwei.MoNi_San.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bwei.MoNi_San.R;
import com.bwei.MoNi_San.adapter.ProductAdapter;
import com.bwei.MoNi_San.bean.ProductBean;
import com.bwei.MoNi_San.common.Api;
import com.bwei.MoNi_San.presenter.RecyPresenter;
import com.bwei.MoNi_San.utils.OkHttpUtils;
import com.bwei.MoNi_San.utils.RequestCallback;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class LieBiaoActivity extends AppCompatActivity implements XRecyclerView.LoadingListener {

    private RecyPresenter presenter;
    private LinearLayoutManager manager;
    private View view;
    private int page=1;//莫惹第一页
    private ProductBean productBean;
    private Handler handler = new Handler();
    private ProductAdapter productAdapter;
    private Button btn_chaxun;
    private EditText edit_txt;
    private String goodsname="手机";
    private XRecyclerView xrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lie_biao);
        xrv = findViewById(R.id.xrecycler_view);
        edit_txt = findViewById(R.id.edit_txtC);
        btn_chaxun = findViewById(R.id.chaxun);
        //设置支持刷新加载
        xrv.setLoadingListener(this);
        xrv.setLoadingMoreEnabled(true);

        //请求商品列表
        requestProduct(goodsname);
        //输入进行查询
        btn_chaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsname = edit_txt.getText().toString();
                requestProduct(goodsname);
            }
        });
    }


    private void requestProduct(String goodsname) {
        HashMap<String, String> params = new HashMap<>();
        params.put("keywords",goodsname);
        params.put("page",page+"");

        OkHttpUtils.getInstance().postData(Api.PRODUCT_URL, params, new RequestCallback() {

            private String result;

            @Override
            public void failure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                String result = null;
                if (response.code() == 200){
                    try {
                        result = response.body().string();
                        System.out.println("result:"+result);
                        parseProductBean(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    /**
     * 解析
     * @param result
     */
    private void parseProductBean(String result) {
        productBean = new Gson().fromJson(result, ProductBean.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
                fillDatas();
            }
        });
    }
    /**
     * 绘制列表 使用recyclerview
     */
    private void fillDatas() {
        System.out.println("page:"+page);
        xrv.setLayoutManager(new LinearLayoutManager(this));
        if (page==1){
            productAdapter = new ProductAdapter(this, productBean.data);
            xrv.setAdapter(productAdapter);
            xrv.refreshComplete();
        }else {
            if (productAdapter!=null){
                productAdapter.loadData(productBean.data);
                xrv.loadMoreComplete();
            }
        }
    }



    @Override
    public void onRefresh() {
        page = 1;
        requestProduct(goodsname);
    }

    @Override
    public void onLoadMore() {
        page++;
        requestProduct(goodsname);
    }
}
