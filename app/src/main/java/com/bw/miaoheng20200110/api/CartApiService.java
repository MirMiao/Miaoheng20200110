package com.bw.miaoheng20200110.api;

import com.bw.miaoheng20200110.entity.CartEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * 时间 :2020/1/10  8:52
 * 作者 :苗恒
 * 功能 :
 */
public interface CartApiService {
      @GET("small/order/verify/v1/findShoppingCart")
      Observable<CartEntity> getData(@Header("userId") String userId,@Header("sessionId") String sessionId);
}
