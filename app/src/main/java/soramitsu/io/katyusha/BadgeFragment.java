package soramitsu.io.katyusha;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BadgeFragment extends Fragment {
    public static final String TAG = BadgeFragment.class.getSimpleName();

    public static BadgeFragment newInstance() {
        BadgeFragment fragment = new BadgeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_badge, container, false);
    }

}
