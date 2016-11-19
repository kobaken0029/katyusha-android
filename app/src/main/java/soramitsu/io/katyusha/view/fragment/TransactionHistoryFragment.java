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

import soramitsu.io.katyusha.view.Navigator;
import soramitsu.io.katyusha.R;
import soramitsu.io.katyusha.domain.entity.Transaction;
import soramitsu.io.katyusha.view.adapter.TransactionListAdapter;
import soramitsu.io.katyusha.databinding.FragmentTransactionHistoryBinding;

public class TransactionHistoryFragment extends Fragment {
    public final static String TAG = TransactionHistoryFragment.class.getSimpleName();

    Navigator navigator;
    FragmentTransactionHistoryBinding binding;

    public static TransactionHistoryFragment newInstance() {
        TransactionHistoryFragment fragment = new TransactionHistoryFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transaction_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);

        List<Transaction> history = new ArrayList<Transaction>() {{
            Transaction transaction = new Transaction();
            transaction.amount = "1000";
            transaction.opponent = "hoge支店A";
            transaction.timestamp = 1456940524;
            add(transaction);

            Transaction transaction1 = new Transaction();
            transaction1.amount = "300";
            transaction1.opponent = "hoge支店B";
            transaction1.timestamp = 1465148524;
            add(transaction1);

            Transaction transaction2 = new Transaction();
            transaction2.amount = "600";
            transaction2.opponent = "hoge支店C";
            transaction2.timestamp = 1478799724;
            add(transaction2);

            Transaction transaction3 = new Transaction();
            transaction3.amount = "7000";
            transaction3.opponent = "hoge支店D";
            transaction3.timestamp = 1479577324;
            add(transaction3);
        }};

        binding.listView.setAdapter(new TransactionListAdapter(getContext(), history, ""));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigator = null;
    }
}
