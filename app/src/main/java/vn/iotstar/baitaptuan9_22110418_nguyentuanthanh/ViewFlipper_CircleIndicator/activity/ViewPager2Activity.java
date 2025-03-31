package vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.R;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.adapters.ImagesViewPager2Adapter;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.models.Images;

public class ViewPager2Activity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private List<Images> imagesList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);

        // Ánh xạ view
        viewPager2 = findViewById(R.id.viewpage2);
        circleIndicator3 = findViewById(R.id.circle_indicator3);

        // Lấy danh sách ảnh
        imagesList1 = getListImages();

        // Thiết lập adapter
        ImagesViewPager2Adapter adapter1 = new ImagesViewPager2Adapter(imagesList1);
        viewPager2.setAdapter(adapter1);

        // Liên kết ViewPager2 với CircleIndicator3
        circleIndicator3.setViewPager(viewPager2);

        // Lắng nghe sự kiện khi ViewPager2 chuyển trang
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000); // Chạy auto slide sau 5 giây
            }
        });

        // Thiết lập hiệu ứng chuyển đổi trang cho ViewPager2
        // viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        viewPager2.setPageTransformer(new DepthPageTransformer());

    }

    private List<Images> getListImages() {
        List<Images> list = new ArrayList<>();
        list.add(new Images(R.drawable.quangcao));
        list.add(new Images(R.drawable.coffee));
        list.add(new Images(R.drawable.companypizza));
        list.add(new Images(R.drawable.themoingon));
        return list;
    }

    // Auto-run để tự động chuyển ảnh trong ViewPager2
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // Xử lý tự động chuyển đổi ViewPager2
            if (viewPager2.getCurrentItem() == imagesList1.size() - 1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        }
    };
    public class DepthPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // Trang nằm ngoài màn hình bên trái
                view.setAlpha(0f);
            } else if (position <= 0) { // Trang di chuyển từ phải sang trái
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setTranslationZ(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);
            } else if (position <= 1) { // Trang di chuyển từ trái sang phải
                view.setAlpha(1 - position);
                view.setTranslationX(pageWidth * -position);
                view.setTranslationZ(-1f);

                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else { // Trang nằm ngoài màn hình bên phải
                view.setAlpha(0f);
            }
        }
    }

    public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // Trang nằm ngoài màn hình bên trái
                view.setAlpha(0f);
            } else if (position <= 1) { // Trang trong khoảng [-1,1]
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;

                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Thu nhỏ trang lại
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Làm mờ trang dần khi chuyển đổi
                view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) /
                        (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else { // Trang nằm ngoài màn hình bên phải
                view.setAlpha(0f);
            }
        }
    }




}
