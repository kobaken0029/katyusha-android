package soramitsu.io.katyusha;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import soramitsu.io.katyusha.entity.Badge;

/**
 * Created by Andrey on 19.11.2016.
 */

class BadgeAdapter extends RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder> {
    private List<Badge> badgeList;

    BadgeAdapter(List<Badge> badgeList) {
        this.badgeList = badgeList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public BadgeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_badge, parent, false);
        return new BadgeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BadgeViewHolder holder, int position) {
        holder.badgeName.setText(badgeList.get(position).getBadgeName());
        holder.badgeDescription.setText(badgeList.get(position).getBadgeDescription());
        holder.badgeImage.setImageResource(badgeList.get(position).getBadgeImageId());
    }

    @Override
    public int getItemCount() {
        return badgeList.size();
    }

    class BadgeViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView badgeName;
        TextView badgeDescription;
        ImageView badgeImage;

        BadgeViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv);
            badgeName = (TextView) itemView.findViewById(R.id.textViewBadgeName);
            badgeDescription = (TextView) itemView.findViewById(R.id.badge_description);
            badgeImage = (ImageView) itemView.findViewById(R.id.badge_image);
        }
    }
}
