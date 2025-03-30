package vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.Fragment_Tablayout_Viewpager2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.Fragment_Tablayout_Viewpager2.fragments.CancelFragment;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.Fragment_Tablayout_Viewpager2.fragments.DanhGiaFragment;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.Fragment_Tablayout_Viewpager2.fragments.DeliveryFragment;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.Fragment_Tablayout_Viewpager2.fragments.NewOrderFragment;
import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.Fragment_Tablayout_Viewpager2.fragments.PickupFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle){
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new NewOrderFragment();
            case 1:
                return new PickupFragment();
            case 2:
                return new DeliveryFragment();
            case 3:
                return new DanhGiaFragment();
            case 4:
                return new CancelFragment();
            default:
                return new NewOrderFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
