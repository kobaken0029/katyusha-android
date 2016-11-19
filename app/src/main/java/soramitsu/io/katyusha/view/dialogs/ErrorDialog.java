package soramitsu.io.katyusha.view.dialogs;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import soramitsu.io.katyusha.databinding.DialogErrorBinding;


public class ErrorDialog {
    private AlertDialog dialog;
    private DialogErrorBinding dialogErrorBinding;

    public ErrorDialog(LayoutInflater inflater) {
        dialogErrorBinding = DialogErrorBinding.inflate(inflater);
    }

    public void show(Activity activity, String message) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }

        dialogErrorBinding.message.setText(message);
        dialogErrorBinding.ok.setOnClickListener(v -> dialog.dismiss());

        ViewGroup parent = ((ViewGroup) dialogErrorBinding.getRoot().getParent());
        if (parent != null) {
            parent.removeView(dialogErrorBinding.getRoot());
        }

        dialog = new AlertDialog.Builder(activity)
                .setView(dialogErrorBinding.getRoot())
                .create();
        dialog.show();
    }
}
