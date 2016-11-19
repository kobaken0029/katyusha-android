package soramitsu.io.katyusha;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.GsonBuilder;
import com.google.zxing.WriterException;

import java.io.UnsupportedEncodingException;

import soramitsu.io.katyusha.databinding.FragmentReceiveBinding;
import soramitsu.io.katyusha.entity.TransferQRParameter;

public class ReceiveFragment extends Fragment {
    public static final String TAG = ReceiveFragment.class.getSimpleName();

    private static final String QR_TEXT_DEFAULT = "{\"type\":\"trans\",\"account\":\"\",\"value\":0}";

    Navigator navigator;
    FragmentReceiveBinding binding;

    public static ReceiveFragment newInstance() {
        ReceiveFragment fragment = new ReceiveFragment();
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
        return inflater.inflate(R.layout.fragment_receive, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);
        try {
            binding.imageViewReceiptQrCode.setImageBitmap(QRCodeGenerator.generateQR("", 500));
        } catch (WriterException e) {
            e.printStackTrace();
        }
        binding.calculatorKeyboard.setTargetTextView(binding.numberReceiveAmount);
        binding.numberReceiveAmount.addTextChangedListener(textWatcher());
    }

    private void defaultQR() throws WriterException {
        binding.imageViewReceiptQrCode.setImageBitmap(QRCodeGenerator.generateQR(QR_TEXT_DEFAULT, 500));
    }

    private void generateQR(String qrParamsText) throws WriterException {
        binding.imageViewReceiptQrCode.setImageBitmap(QRCodeGenerator.generateQR(qrParamsText, 500));
    }

    private TextWatcher textWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    int value;
                    try {
                        value = Integer.valueOf(binding.numberReceiveAmount.getText().toString());
                    } catch (NumberFormatException e) {
                        String buff = binding.numberReceiveAmount.getText().toString();
                        if (buff.length() <= 0) {
                            defaultQR();
                            return;
                        }
                        binding.numberReceiveAmount.setText(buff.substring(0, buff.length() - 1));
                        return;
                    }
                    TransferQRParameter qrParams = new TransferQRParameter();
                    qrParams.type = "trans";
                    qrParams.account = "";
//                    qrParams.account = fetchAccountUseCase.getPublicKey().single().toBlocking().single();
                    qrParams.value = value;
                    final String alias = ((Katyusha) getActivity().getApplication()).getUserInfo().alias;
                    qrParams.alias = new String(alias.getBytes("UTF-8"), "UTF-8");
                    String qrParamsText = new GsonBuilder()
                            .disableHtmlEscaping()
                            .create()
                            .toJson(qrParams, TransferQRParameter.class);
                    Log.d(TAG, qrParamsText);
                    generateQR(qrParamsText);
                    binding.numberReceiveAmount.invalidate();
                } catch (WriterException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // nothing
            }
        };
    }
}
