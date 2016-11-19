package soramitsu.io.katyusha.domain.entity.request;


public class TransferRequest {
    public String command;
    public int amount;
    public String sender;
    public String receiver;
    public String signature;
    public long timestamp;
}
