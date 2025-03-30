package vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;



import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.R;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.adapters.ImageSliderAdapter;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.models.ImagesSlider;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.models.MessageModel;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.services.ApiService;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.services.RetrofitClient;

public class ViewPagerActivityBT extends AppCompatActivity {
    private ViewPager viewPager;
    private CircleIndicator indicator;
    private ImageSliderAdapter adapter;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_bt);

        viewPager = findViewById(R.id.viewPager);
        indicator = findViewById(R.id.indicator);

        handler = new Handler();
        loadImages();
    }

    private void loadImages() {
        ApiService apiService = RetrofitClient.getApiService();
        apiService.LoadImageSlider(1).enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.body() != null && response.body().isSuccess()) {
                    List<ImagesSlider> imagesList = response.body().getResult();
                    adapter = new ImageSliderAdapter(ViewPagerActivityBT.this, imagesList);
                    viewPager.setAdapter(adapter);
                    indicator.setViewPager(viewPager);

                    startAutoSlide();
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Log.e("API_ERROR", "Error: " + t.getMessage());
            }
        });
    }

    private void startAutoSlide() {
        runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int totalItems = adapter.getCount();
                viewPager.setCurrentItem((currentItem + 1) % totalItems, true);
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }
}
