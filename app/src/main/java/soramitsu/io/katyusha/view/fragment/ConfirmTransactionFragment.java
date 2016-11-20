package soramitsu.io.katyusha.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import soramitsu.io.katyusha.Katyusha;
import soramitsu.io.katyusha.R;
import soramitsu.io.katyusha.data.repository.TransactionRepositoryImpl;
import soramitsu.io.katyusha.databinding.FragmentConfirmTransactionBinding;
import soramitsu.io.katyusha.domain.entity.UserInfo;
import soramitsu.io.katyusha.domain.repository.TransactionRepository;
import soramitsu.io.katyusha.view.Navigator;
import soramitsu.io.katyusha.view.dialogs.MyProgressDialog;
import soramitsu.io.katyusha.view.dialogs.SuccessDialog;

public class ConfirmTransactionFragment extends Fragment {
    public static final String TAG = ConfirmTransactionFragment.class.getSimpleName();

    public static final String ARG_TARGET_NAME = "target_name";
    public static final String ARG_RECEIVER = "receiver";

    Navigator navigator;
    FragmentConfirmTransactionBinding binding;

    private TransactionRepository transactionRepository = new TransactionRepositoryImpl();

    public static ConfirmTransactionFragment newInstance(@NonNull String target, @NonNull String receiver) {
        ConfirmTransactionFragment fragment = new ConfirmTransactionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TARGET_NAME, target);
        bundle.putString(ARG_RECEIVER, receiver);
        fragment.setArguments(bundle);
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

        UserInfo userInfo = ((Katyusha) getActivity().getApplication()).getUserInfo();

        final String target = getArguments().getString(ARG_TARGET_NAME);
        final String receiver = getArguments().getString(ARG_RECEIVER);

        setConfirmInfo(userInfo.amount, target, receiver);

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

                                                    if (target.equals("Vodka")) {
                                                        userInfo.amount -= 3;
                                                    } else if (target.equals("Bread")) {
                                                        userInfo.amount -= 2;
                                                    } else {
                                                        userInfo.amount -= 50;
                                                    }

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
    }

    private void setConfirmInfo(int amount, String target, String receiver) {
        String balanceText = "$" + amount + " to " + "$";
        if (target.equals("Vodka")) {
            balanceText += (amount - 3) + "";
        } else if (target.equals("Bread")) {
            balanceText += (amount - 2) + "";
        } else {
            balanceText += (amount - 50) + "";
        }
        binding.balance.setText(balanceText);

        String message = "Send ";
        if (target.equals("Vodka") || target.equals("Bread")) {
            message += target;
        } else {
            message += "$" + target + ".00";
        }
        message += " to " + receiver;
        binding.message.setText(message);

        if (receiver.equals("tony")) {
            binding.senderIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.takemiya));
            binding.receiverIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.tony));
        } else {
            binding.senderIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.tony));
            binding.receiverIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.takemiya));
        }
    }
}
