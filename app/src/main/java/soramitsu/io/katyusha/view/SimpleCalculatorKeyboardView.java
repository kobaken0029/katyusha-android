package soramitsu.io.katyusha.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.GridLayout;

import soramitsu.io.katyusha.R;
import soramitsu.io.katyusha.databinding.ViewSimpleCalculatorKeyboardBinding;

public class SimpleCalculatorKeyboardView extends GridLayout {
    public static final String TAG = SimpleCalculatorKeyboardView.class.getSimpleName();

    private ViewSimpleCalculatorKeyboardBinding binding;
    private EditText targetEditView;

    public SimpleCalculatorKeyboardView(Context context) {
        super(context);
        if (!isInEditMode()) {
            init(context);
        }
    }

    public SimpleCalculatorKeyboardView(Context context, AttributeSet attr) {
        super(context, attr);
        if (!isInEditMode()) {
            init(context);
        }
    }

    public SimpleCalculatorKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            init(context);
        }
    }

    @TargetApi(21)
    public SimpleCalculatorKeyboardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!isInEditMode()) {
            init(context);
        }
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        int heightSize = MeasureSpec.getSize(heightSpec);
        binding.getRoot().setMinimumHeight(heightSize);
    }

    private void init(Context context) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_simple_calculator_keyboard, this, true);
        binding.input0.setOnClickListener(view -> targetEditView.append(targetEditView.getText().length() == 0 ? "" : "0"));
        binding.input1.setOnClickListener(view -> targetEditView.append("1"));
        binding.input2.setOnClickListener(view -> targetEditView.append("2"));
        binding.input3.setOnClickListener(view -> targetEditView.append("3"));
        binding.input4.setOnClickListener(view -> targetEditView.append("4"));
        binding.input5.setOnClickListener(view -> targetEditView.append("5"));
        binding.input6.setOnClickListener(view -> targetEditView.append("6"));
        binding.input7.setOnClickListener(view -> targetEditView.append("7"));
        binding.input8.setOnClickListener(view -> targetEditView.append("8"));
        binding.input9.setOnClickListener(view -> targetEditView.append("9"));
        binding.delete.setOnClickListener(view -> {
            String buff = targetEditView.getText().toString();
            if (buff.length() <= 0) {
                return;
            }
            targetEditView.setText(buff.substring(0, buff.length() - 1));
        });
        binding.reset.setOnClickListener(view -> targetEditView.setText(""));
    }

    public void setTargetTextView(EditText targetEditView) {
        this.targetEditView = targetEditView;
    }
}
