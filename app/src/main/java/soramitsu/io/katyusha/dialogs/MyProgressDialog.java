package soramitsu.io.katyusha.dialogs;

import android.app.Activity;
import android.app.ProgressDialog;


public class MyProgressDialog {
    private ProgressDialog progressDialog;

    public void show(Activity activity, String title, String message) {
        if (progressDialog != null && progressDialog.isShowing()) {
            return;
        }

        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void hide() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
