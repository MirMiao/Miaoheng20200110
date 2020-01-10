package com.bw.miaoheng20200110;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bw.miaoheng20200110.adapter.CartAdapter;
import com.bw.miaoheng20200110.api.Api;
import com.bw.miaoheng20200110.api.CartApiService;
import com.bw.miaoheng20200110.entity.CartEntity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        sum = findViewById(R.id.sum);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initView();
        initData();
    }
    private void initView() {
    }
    @SuppressLint("CheckResult")
    private void initData() {
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
     retrofit.create(CartApiService.class)
             .getData("13387","157861833888713387")
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new Consumer<CartEntity>() {
                 /**
                  * 成功
                  * @param cartEntity
                  * @throws Exception
                  */
                 @Override
                 public void accept(CartEntity cartEntity) throws Exception {
                     List<CartEntity.ResultBean> result = cartEntity.getResult();
                     CartAdapter cartAdapter = new CartAdapter(MainActivity.this, result);
                     recyclerView.setAdapter(cartAdapter);
                 }
             }, new Consumer<Throwable>() {
                 /**
                  * 失败
                  * @param throwable
                  * @throws Exception
                  */
                 @Override
                 public void accept(Throwable throwable) throws Exception {

                 }
             });
    }


}
