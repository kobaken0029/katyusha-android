package soramitsu.io.katyusha;

public interface Navigator {
    void gotoTop();
    void gotoTransaction();
    void gotoConfirmTransaction();
    void gotoBadgeList();
    void gotoTransactionHistory();
}
