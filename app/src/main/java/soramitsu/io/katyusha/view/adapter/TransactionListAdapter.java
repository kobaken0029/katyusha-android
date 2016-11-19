package soramitsu.io.katyusha.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import soramitsu.io.katyusha.R;
import soramitsu.io.katyusha.databinding.ListItemBinding;
import soramitsu.io.katyusha.domain.entity.Transaction;

public class TransactionListAdapter extends BaseAdapter {
    private Context context;
    private List<Transaction> transactionHistory;
    private String publicKey;

    public TransactionListAdapter(Context context, List<Transaction> transactionHistory, String publicKey) {
        this.context = context;
        this.transactionHistory = transactionHistory;
        this.publicKey = publicKey;
    }

    @Override
    public int getCount() {
        return transactionHistory.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Transaction getItem(int position) {
        if (transactionHistory.size() <= position || position < 0) {
            return null;
        }
        return transactionHistory.get(position);
    }

    public void setItems(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_item, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ListItemBinding) convertView.getTag();
        }
        binding.setTransaction(getItem(getCount() - 1 - position));
        binding.state.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_send_money_24dp));
//        binding.setPublicKey(publicKey);
//        binding.setContext(context);
        return convertView;
    }
}
