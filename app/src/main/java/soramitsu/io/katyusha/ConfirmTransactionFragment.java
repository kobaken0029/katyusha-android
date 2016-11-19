package soramitsu.io.katyusha;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import soramitsu.io.katyusha.databinding.FragmentConfirmTransactionBinding;
import soramitsu.io.katyusha.dialogs.MyProgressDialog;
import soramitsu.io.katyusha.dialogs.SuccessDialog;
import soramitsu.io.katyusha.repository.TransactionRepository;
import soramitsu.io.katyusha.repository.impl.TransactionRepositoryImpl;

public class ConfirmTransactionFragment extends Fragment {
    public static final String TAG = ConfirmTransactionFragment.class.getSimpleName();

    Navigator navigator;
    FragmentConfirmTransactionBinding binding;

    private TransactionRepository transactionRepository = new TransactionRepositoryImpl();

    public static ConfirmTransactionFragment newInstance() {
        ConfirmTransactionFragment fragment = new ConfirmTransactionFragment();
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
        return inflater.inflate(R.layout.fragment_confirm_transaction, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);
        binding.cancelButton.setOnClickListener(v -> navigator.gotoTransaction());
        binding.sendButton.setOnClickListener(v -> {
            final MyProgressDialog progressDialog = new MyProgressDialog();
            progressDialog.show(getActivity(), getString(R.string.connection_progress_title), getString(R.string.send_payment_message));
            transactionRepository.sendPayment(null)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            responseObject -> {
                                if (responseObject.status == 200) {
                                    new Timer().schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            getActivity().runOnUiThread(() -> {
                                                progressDialog.hide();

                                                final SuccessDialog successDialog = new SuccessDialog(getLayoutInflater(savedInstanceState));
                                                successDialog.show(getActivity(), responseObject.message, vv -> {

                                                    ((Katyusha) getActivity().getApplication()).getUserInfo().amount -= 50;

                                                    successDialog.hide();
                                                    navigator.gotoTransaction();
                                                });
                                            });
                                        }
                                    }, 2000);
                                }
                            },
                            throwable -> {
                            },
                            () -> {
                            }
                    );
        });
//        Glide.with(this).load(R.raw.arrow).into(new GlideDrawableImageViewTarget(binding.forward));
    }
}
