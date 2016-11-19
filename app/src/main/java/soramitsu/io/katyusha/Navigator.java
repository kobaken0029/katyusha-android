package soramitsu.io.katyusha;

public interface Navigator {
    void gotoTop();
    void gotoTransaction();
    void gotoConfirmTransaction();
    void gotoReceive();
    void gotoRightsList();
    void gotoBadgeList();
    void gotoTransactionHistory();
}
