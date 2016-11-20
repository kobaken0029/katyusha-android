package soramitsu.io.katyusha.view.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import soramitsu.io.katyusha.R;
import soramitsu.io.katyusha.domain.entity.Right;

/**
 * Created by Andrey on 19.11.2016.
 */

public class RightsAdapter extends RecyclerView.Adapter<RightsAdapter.RightsViewHolder> {
    private List<Right> rightList;
    private OnClickItemListener listener;

    public interface OnClickItemListener {
        void onClickItem(String contentName);
    }

    public RightsAdapter(List<Right> rightsList, OnClickItemListener listener) {
        this.rightList = rightsList;
        this.listener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RightsAdapter.RightsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_right, parent, false);
        return new RightsAdapter.RightsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RightsAdapter.RightsViewHolder holder, int position) {
        holder.rightName.setText(rightList.get(position).getRightName());
        holder.rightAmount.setText(rightList.get(position).getRightsAmount());
        holder.rightPrice.setText(rightList.get(position).getRightsPrice());
        holder.rightImage.setImageResource(rightList.get(position).getRightImageId());
        holder.cardView.setOnClickListener(v -> listener.onClickItem(holder.rightName.getText().toString()));
    }

    @Override
    public int getItemCount() {
        return rightList.size();
    }

    class RightsViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView rightName;
        TextView rightPrice;
        TextView rightAmount;
        ImageView rightImage;

        RightsViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv_rights);
            rightName = (TextView) itemView.findViewById(R.id.textViewRightName);
            rightPrice = (TextView) itemView.findViewById(R.id.textViewThingPrices);
            rightAmount = (TextView) itemView.findViewById(R.id.right_amount);
            rightImage = (ImageView) itemView.findViewById(R.id.right_image);
        }
    }
}
