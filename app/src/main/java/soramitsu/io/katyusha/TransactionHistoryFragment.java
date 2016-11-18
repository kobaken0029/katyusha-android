package soramitsu.io.katyusha;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        List<Transaction> history = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.amount = "1000";
        transaction.opponent = "hoge支店";
        history.add(transaction);
        binding.listView.setAdapter(new TransactionListAdapter(getContext(), history, ""));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigator = null;
    }
}
