package soramitsu.io.katyusha.domain.entity;

import java.io.Serializable;


public abstract class QRParameter implements Serializable {
    public static final String TYPE_GIFT = "gift";
    public static final String TYPE_TRANSFER = "trans";

    public String type;
}
