package soramitsu.io.katyusha.entity;

import java.util.ArrayList;
import java.util.List;

import soramitsu.io.katyusha.R;

/**
 * Created by Andrey on 19.11.2016.
 */

public class Right {
    private String rightName;
    private String rightsAmount;
    private String rightsPrice;
    private int rightImageId;
    private List<Right> rights;

    public Right(String rightName, String rightsAmount, String rightsPrice, int rightImageId) {
        this.rightName = rightName;
        this.rightsAmount = rightsAmount;
        this.rightsPrice = rightsPrice;
        this.rightImageId = rightImageId;
    }

    public Right() {
    }

    public String getRightsPrice() {
        return rightsPrice;
    }

    public void setRightsPrice(String rightsPrice) {
        this.rightsPrice = rightsPrice;
    }

    public String getRightsAmount() {
        return rightsAmount;
    }

    public void setRightsAmount(String rightsAmount) {
        this.rightsAmount = rightsAmount;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public int getRightImageId() {
        return rightImageId;
    }

    public void setRightImageId(int rightImageId) {
        this.rightImageId = rightImageId;
    }

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }

    public void initializeData() {
        rights = new ArrayList<>();
        rights.add(new Right("Vodka", "1000", "3.0$", R.drawable.vodka_only));
        rights.add(new Right("Bread", "2000", "2.0$", R.drawable.bread_only));
    }
}
