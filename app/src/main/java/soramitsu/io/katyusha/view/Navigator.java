package soramitsu.io.katyusha.view;

public interface Navigator {
    void gotoTop();
    void gotoTransaction();
    void gotoConfirmTransaction(String target, String sender);
    void gotoReceive();
    void gotoRightsList();
    void gotoRight(String target);
    void gotoBadgeList();
    void gotoTabHost();
}
