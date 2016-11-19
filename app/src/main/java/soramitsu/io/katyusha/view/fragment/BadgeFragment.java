package soramitsu.io.katyusha.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soramitsu.io.katyusha.R;
import soramitsu.io.katyusha.entity.Badge;
import soramitsu.io.katyusha.view.adapter.BadgeAdapter;

public class BadgeFragment extends Fragment {
    public static final String TAG = BadgeFragment.class.getSimpleName();

    public static BadgeFragment newInstance() {
        return new BadgeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_badge, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_badge);

        Badge badge = new Badge();
        badge.initializeData();

        BadgeAdapter badgeAdapter = new BadgeAdapter(badge.getBadges());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        rv.setLayoutManager(mLayoutManager);
        rv.setAdapter(badgeAdapter);
        return rootView;
    }

}
