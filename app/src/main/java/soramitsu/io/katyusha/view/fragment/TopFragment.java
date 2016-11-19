package soramitsu.io.katyusha.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soramitsu.io.katyusha.Katyusha;
import soramitsu.io.katyusha.view.Navigator;
import soramitsu.io.katyusha.R;
import soramitsu.io.katyusha.databinding.FragmentTopBinding;
import soramitsu.io.katyusha.domain.entity.UserInfo;

public class TopFragment extends Fragment {
    public static final String TAG = TopFragment.class.getSimpleName();

    Navigator navigator;
    FragmentTopBinding binding;

    public static TopFragment newInstance() {
        TopFragment fragment = new TopFragment();
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);
        binding.transactionCard.setOnClickListener(v -> navigator.gotoTransaction());
        binding.transactionHistoryCard.setOnClickListener(v -> navigator.gotoTabHost());
        binding.badgeCard.setOnClickListener(v -> navigator.gotoBadgeList());
        binding.rightsCard.setOnClickListener(v -> navigator.gotoRightsList());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UserInfo userInfo = ((Katyusha) getActivity().getApplication()).getUserInfo();
        binding.setUserInfo(userInfo);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigator = null;
    }
}
