package vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.services;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.models.MessageModel;

public interface ApiService {
    @FormUrlEncoded
    @POST("newimagesmanager.php")
    Call<MessageModel> LoadImageSlider(@Field("position") int position);
}
