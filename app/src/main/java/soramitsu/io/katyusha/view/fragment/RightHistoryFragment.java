package soramitsu.io.katyusha.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import soramitsu.io.katyusha.R;
import soramitsu.io.katyusha.databinding.FragmentTransactionHistoryBinding;
import soramitsu.io.katyusha.domain.entity.Transaction;
import soramitsu.io.katyusha.view.Navigator;
import soramitsu.io.katyusha.view.adapter.TransactionListAdapter;

public class RightHistoryFragment extends Fragment {
    public static final String TAG = RightHistoryFragment.class.getSimpleName();

    Navigator navigator;
    FragmentTransactionHistoryBinding binding;

    public static RightHistoryFragment newInstance() {
        RightHistoryFragment fragment = new RightHistoryFragment();
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
        return inflater.inflate(R.layout.fragment_transaction_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);

        List<Transaction> history = new ArrayList<Transaction>() {{
            Transaction transaction = new Transaction();
            transaction.amount = "can drink vodka";
            transaction.opponent = "mining A";
            transaction.timestamp = 1465148524;
            add(transaction);

            Transaction transaction1 = new Transaction();
            transaction1.amount = "can drink sake";
            transaction1.opponent = "mining B";
            transaction1.timestamp = 1478799724;
            add(transaction1);

            Transaction transaction2 = new Transaction();
            transaction2.amount = "can drink vodka";
            transaction2.opponent = "brewing A";
            transaction2.timestamp = 1479577537;
            add(transaction2);
        }};

        binding.listView.setAdapter(new TransactionListAdapter(getContext(), history, ""));
    }
}
