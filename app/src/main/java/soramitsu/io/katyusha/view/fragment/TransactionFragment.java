package soramitsu.io.katyusha.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soramitsu.io.katyusha.view.Navigator;
import soramitsu.io.katyusha.R;
import soramitsu.io.katyusha.databinding.FragmentTransactionBinding;
import soramitsu.io.katyusha.view.dialogs.OnQRReaderListener;
import soramitsu.io.katyusha.view.dialogs.QRReaderDialogFragment;

public class TransactionFragment extends Fragment implements OnQRReaderListener {
    public static final String TAG = TransactionFragment.class.getSimpleName();

    Navigator navigator;
    FragmentTransactionBinding binding;

    public static TransactionFragment newInstance() {
        TransactionFragment fragment = new TransactionFragment();
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
        return inflater.inflate(R.layout.fragment_transaction, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);
        binding.transferCard.setOnClickListener(v -> {
            QRReaderDialogFragment fragment = QRReaderDialogFragment.newInstance();
            fragment.show(getChildFragmentManager(), QRReaderDialogFragment.TAG);
        });
        binding.receiverCard.setOnClickListener(v -> navigator.gotoReceive());
    }

    @Override
    public void setOnResult(String result) {
        navigator.gotoConfirmTransaction();
    }
}
