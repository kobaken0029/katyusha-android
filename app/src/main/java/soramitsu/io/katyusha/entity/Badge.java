package soramitsu.io.katyusha.entity;

import java.util.ArrayList;
import java.util.List;

import soramitsu.io.katyusha.R;

/**
 * Created by Andrey on 19.11.2016.
 */

public class Badge {
    private String badgeName;
    private String badgeDescription;
    private int badgeImageId;
    private List<Badge> badges;
    private boolean badgeState;

    public Badge(String badgeName, String badgeDescription, int badgeImageId, boolean badgeState) {
        this.badgeName = badgeName;
        this.badgeDescription = badgeDescription;
        this.badgeImageId = badgeImageId;
        this.badgeState = badgeState;
    }

    public Badge() {
    }

    private Badge(String badgeName, String badgeDescription, int badgeImageId) {
        this.badgeName = badgeName;
        this.badgeDescription = badgeDescription;
        this.badgeImageId = badgeImageId;
    }

    public boolean isBadgeState() {
        return badgeState;
    }

    public void setBadgeState(boolean badgeState) {
        this.badgeState = badgeState;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getBadgeDescription() {
        return badgeDescription;
    }

    public void setBadgeDescription(String badgeDescription) {
        this.badgeDescription = badgeDescription;
    }

    public int getBadgeImageId() {
        return badgeImageId;
    }

    public void setBadgeImageId(int badgeImageId) {
        this.badgeImageId = badgeImageId;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    public void initializeData() {
        badges = new ArrayList<>();
        badges.add(new Badge("StackOverflow", "Собери 100.000 прав одного типа", R.drawable.plum));
        badges.add(new Badge("Numismatist", "Собери 100 разных прав", R.drawable.plum));
        badges.add(new Badge("Jedi Exchanger", "Конвертируй 100.000 прав из одного вида в другой", R.drawable.plum));
    }
}
