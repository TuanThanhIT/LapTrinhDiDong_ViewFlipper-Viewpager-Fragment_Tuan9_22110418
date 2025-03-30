package vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.Fragment_Tablayout_Viewpager2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.iotstar.baitaptuan9_22110418_nguyentuanthanh.databinding.FragmentNeworderBinding;

public class CancelFragment extends Fragment {
    private FragmentNeworderBinding binding;

    public CancelFragment() {
        // Constructor mặc định
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Ánh xạ layout bằng View Binding
        binding = FragmentNeworderBinding.inflate(inflater, container, false);

        // Vị trí để load dữ liệu: Adapter, RecyclerView, v.v.

        return binding.getRoot();
    }
}
