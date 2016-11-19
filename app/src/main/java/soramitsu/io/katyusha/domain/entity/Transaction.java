package soramitsu.io.katyusha.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Transaction {
    @SerializedName("asset-uuid")
    public String assetUuid;
    public String sender;
    public String opponent;
    public String amount;
    public long timestamp;
    public String kataware = null;

    public String modifyDisplayDate() {
        long now = Calendar.getInstance().getTimeInMillis() / 1000;
        long sec = now - timestamp;
        if (sec <= 0) {
            return "now";
        } else if (sec < 60) {
            return sec + "秒";
        } else if (sec < 3600) {
            return Math.round(sec / 60) + "分";
        } else if (sec < 3600 * 24) {
            return Math.round(sec / (60 * 60)) + "時間";
        } else if (sec < 3600 * 24 * 31) {
            return Math.round(sec / (60 * 60 * 24)) + "日";
        } else {
            Date date = new Date(timestamp * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
            return sdf.format(date);
        }
    }

    public boolean isSent(String publicKey) {
        return sender.equals(publicKey);
    }
}
