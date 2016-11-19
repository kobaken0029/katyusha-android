package soramitsu.io.katyusha.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soramitsu.io.katyusha.R;
import soramitsu.io.katyusha.databinding.FragmentTabHostBinding;
import soramitsu.io.katyusha.view.Navigator;
import soramitsu.io.katyusha.view.adapter.TabPagerAdapter;

public class TabHostFragment extends Fragment {
    public static final String TAG = TabHostFragment.class.getSimpleName();

    Navigator navigator;
    FragmentTabHostBinding binding;
    TabPagerAdapter pagerAdapter;

    public static TabHostFragment newInstance() {
        TabHostFragment fragment = new TabHostFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Navigator) {
            navigator = (Navigator) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Navigator");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_host, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pagerAdapter = new TabPagerAdapter(getContext(), getChildFragmentManager());
        binding.pagerMainContents.setAdapter(pagerAdapter);
        binding.tabMainContents.setupWithViewPager(binding.pagerMainContents);
    }
}
