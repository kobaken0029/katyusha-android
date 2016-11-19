package soramitsu.io.katyusha.entity.request;


public class TransferRequest {
    public String command;
    public int amount;
    public String sender;
    public String receiver;
    public String signature;
    public long timestamp;
}
