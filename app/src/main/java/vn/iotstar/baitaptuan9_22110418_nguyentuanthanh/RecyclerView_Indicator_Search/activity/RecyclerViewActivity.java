package vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.RecyclerView_Indicator_Search.activity;


import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.R;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.RecyclerView_Indicator_Search.adapter.IconAdapter;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.RecyclerView_Indicator_Search.model.IconModel;

public class RecyclerViewActivity extends AppCompatActivity {

    List<IconModel> arrayList1;
    IconAdapter iconAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_indicator);

        RecyclerView rcIcon = findViewById(R.id.rcIcon);
        arrayList1 = new ArrayList<>();
        arrayList1.add(new IconModel(R.drawable.icon1, "Biểu tượng trang chủ"));
        arrayList1.add(new IconModel(R.drawable.icon2, "Biểu tượng tìm kiếm"));
        arrayList1.add(new IconModel(R.drawable.icon3, "Biểu tượng cài đặt"));
        arrayList1.add(new IconModel(R.drawable.icon4, "Biểu tượng hồ sơ cá nhân"));
        arrayList1.add(new IconModel(R.drawable.icon5, "Biểu tượng thông báo"));
        arrayList1.add(new IconModel(R.drawable.icon6, "Biểu tượng tin nhắn"));
        arrayList1.add(new IconModel(R.drawable.icon7, "Biểu tượng yêu thích"));
        arrayList1.add(new IconModel(R.drawable.icon8, "Biểu tượng tải xuống"));
        arrayList1.add(new IconModel(R.drawable.icon9, "Biểu tượng đăng xuất"));
        arrayList1.add(new IconModel(R.drawable.icon1, "Biểu tượng trang chủ"));
        arrayList1.add(new IconModel(R.drawable.icon2, "Biểu tượng tìm kiếm"));
        arrayList1.add(new IconModel(R.drawable.icon3, "Biểu tượng cài đặt"));
        arrayList1.add(new IconModel(R.drawable.icon4, "Biểu tượng hồ sơ cá nhân"));
        arrayList1.add(new IconModel(R.drawable.icon5, "Biểu tượng thông báo"));
        arrayList1.add(new IconModel(R.drawable.icon6, "Biểu tượng tin nhắn"));
        arrayList1.add(new IconModel(R.drawable.icon7, "Biểu tượng yêu thích"));
        arrayList1.add(new IconModel(R.drawable.icon8, "Biểu tượng tải xuống"));
        arrayList1.add(new IconModel(R.drawable.icon9, "Biểu tượng đăng xuất"));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2,
                GridLayoutManager.HORIZONTAL, false);
        // Khởi tạo LinearLayoutManager
       // LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcIcon.setLayoutManager(gridLayoutManager);

        iconAdapter = new IconAdapter(getApplicationContext(), arrayList1);
        rcIcon.setAdapter(iconAdapter);
        rcIcon.addItemDecoration(new LinePagerIndicatorDecoration(this));

        SearchView searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterListener(newText);
                return true;
            }
        });

    }

    public class LinePagerIndicatorDecoration extends RecyclerView.ItemDecoration {
        private final int colorActive = 0xFF1976D2; // Màu xanh dương đậm
        private final int colorInactive = 0x448AB6F8; // Xanh nhạt mờ
        private final float DP;

        private final Paint paint = new Paint();
        private final float indicatorHeight;
        private final float indicatorItemLength;
        private final float indicatorItemPadding;
        private final float activeIndicatorRadius;
        private final float inactiveIndicatorRadius;

        public LinePagerIndicatorDecoration(Activity activity) {
            DP = activity.getResources().getDisplayMetrics().density;

            indicatorHeight = 20 * DP;
            indicatorItemLength = 10 * DP;
            indicatorItemPadding = 6 * DP;
            activeIndicatorRadius = 6 * DP;
            inactiveIndicatorRadius = 4 * DP;

            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
        }

        @Override
        public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            RecyclerView.Adapter<?> adapter = parent.getAdapter();
            if (adapter == null) return;

            int itemCount = adapter.getItemCount();
            if (itemCount == 0) return;

            float totalLength = indicatorItemLength * itemCount;
            float paddingBetweenItems = (itemCount - 1) * indicatorItemPadding;
            float indicatorTotalWidth = totalLength + paddingBetweenItems;
            float indicatorStartX = (parent.getWidth() - indicatorTotalWidth) / 2F;
            float indicatorPosY = parent.getHeight() - indicatorHeight / 2F;

            drawInactiveIndicators(canvas, indicatorStartX, indicatorPosY, itemCount);

            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            if (layoutManager == null) return;

            int activePosition = layoutManager.findFirstVisibleItemPosition();
            if (activePosition == RecyclerView.NO_POSITION) return;

            View activeChild = layoutManager.findViewByPosition(activePosition);
            if (activeChild == null) return;

            float progress = Math.abs(activeChild.getLeft()) / (float) activeChild.getWidth();
            drawActiveIndicator(canvas, indicatorStartX, indicatorPosY, activePosition, progress);
        }

        private void drawInactiveIndicators(Canvas canvas, float startX, float posY, int itemCount) {
            paint.setColor(colorInactive);
            float itemWidth = indicatorItemLength + indicatorItemPadding;

            for (int i = 0; i < itemCount; i++) {
                float x = startX + i * itemWidth;
                canvas.drawCircle(x, posY, inactiveIndicatorRadius, paint);
            }
        }

        private void drawActiveIndicator(Canvas canvas, float startX, float posY, int highlightPosition, float progress) {
            paint.setColor(colorActive);
            float itemWidth = indicatorItemLength + indicatorItemPadding;
            float highlightX = startX + highlightPosition * itemWidth + (progress * itemWidth);

            float animatedRadius = inactiveIndicatorRadius + (activeIndicatorRadius - inactiveIndicatorRadius) * (1 - progress);
            canvas.drawCircle(highlightX, posY, animatedRadius, paint);
        }
    }

    private void filterListener(String text) {
        List<IconModel> list = new ArrayList<>();
        for (IconModel iconModel : arrayList1) {
            if (iconModel.getDesc().toLowerCase().contains(text.toLowerCase())) {
                list.add(iconModel);
            }
        }
        if (list.isEmpty()) {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            iconAdapter.setListenerList(list);
        }
    }
}
