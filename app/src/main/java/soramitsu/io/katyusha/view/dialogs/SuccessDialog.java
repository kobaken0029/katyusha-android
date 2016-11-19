package soramitsu.io.katyusha.view.dialogs;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soramitsu.io.katyusha.databinding.DialogSuccessfulBinding;


public class SuccessDialog {
    private AlertDialog dialog;
    private DialogSuccessfulBinding dialogSuccessfulBinding;

    public SuccessDialog(LayoutInflater inflater) {
        dialogSuccessfulBinding = DialogSuccessfulBinding.inflate(inflater);
    }

    public void show(Activity activity, String message, View.OnClickListener clickListener) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }

        dialogSuccessfulBinding.message.setText(message);
        dialogSuccessfulBinding.ok.setOnClickListener(clickListener);

        ViewGroup parent = ((ViewGroup) dialogSuccessfulBinding.getRoot().getParent());
        if (parent != null) {
            parent.removeView(dialogSuccessfulBinding.getRoot());
        }

        dialog = new AlertDialog.Builder(activity)
                .setView(dialogSuccessfulBinding.getRoot())
                .create();
        dialog.show();
    }

    public void hide() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
