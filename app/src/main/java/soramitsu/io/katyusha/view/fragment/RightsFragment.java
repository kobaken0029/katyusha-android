package soramitsu.io.katyusha.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soramitsu.io.katyusha.R;
import soramitsu.io.katyusha.entity.Right;
import soramitsu.io.katyusha.view.Navigator;
import soramitsu.io.katyusha.view.adapter.RightsAdapter;

/**
 * Created by Andrey on 19.11.2016.
 */

public class RightsFragment extends Fragment {
    public static final String TAG = RightsFragment.class.getSimpleName();

    Navigator navigator;

    public static RightsFragment newInstance() {
        return new RightsFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rights, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_rights);

        Right right = new Right();
        right.initializeData();

        RightsAdapter rightsAdapter = new RightsAdapter(right.getRights(), navigator::gotoRight);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        rv.setLayoutManager(mLayoutManager);
        rv.setAdapter(rightsAdapter);
        return rootView;
    }
}
