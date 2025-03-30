package vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.R;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.adapters.ImagesViewPageAdapter;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.ViewFlipper_CircleIndicator.models.Images;

public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private List<Images> imagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.viewpager);
        circleIndicator = findViewById(R.id.circle_indicator);

        imagesList = getListImages();
        ImagesViewPageAdapter adapter = new ImagesViewPageAdapter(imagesList);
        viewPager.setAdapter(adapter);

        // Liên kết viewPager và indicator
        circleIndicator.setViewPager(viewPager);

        // Gọi runnable để tự động chuyển trang sau mỗi 3 giây
        handler.postDelayed(runnable, 3000);

        // Lắng nghe sự kiện chuyển trang của ViewPager
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Không cần xử lý trong trường hợp này
            }

            @Override
            public void onPageSelected(int position) {
                // Khi người dùng chọn trang mới, reset lại timer
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Không cần xử lý trong trường hợp này
            }
        });
    }
    private List<Images> getListImages() {
        List<Images> list = new ArrayList<>();
        list.add(new Images(R.drawable.quangcao));
        list.add(new Images(R.drawable.coffee));
        list.add(new Images(R.drawable.companypizza));
        list.add(new Images(R.drawable.themoingon));
        return list;
    }


    // Auto-run ViewPager
    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = viewPager.getCurrentItem();
            int nextItem = (currentItem == imagesList.size() - 1) ? 0 : currentItem + 1;

            viewPager.setCurrentItem(nextItem, true);

            // Lặp lại sau 3 giây (3000ms)
            handler.postDelayed(this, 3000);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        // Dừng auto slide khi activity bị tạm dừng
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Tiếp tục auto slide khi activity hoạt động trở lại
        handler.postDelayed(runnable, 3000);
    }


}
