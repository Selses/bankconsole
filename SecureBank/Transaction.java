import java.time.LocalDateTime;

public class Transaction {

    private String transactionId;
    private String type;
    private double amount;
    private String fromId;
    private String toId;
    private LocalDateTime timestamp;

    public Transaction(String transactionId, String type, double amount,
                       String fromId, String toId) {

        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.fromId = fromId;
        this.toId = toId;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getFromId() {
        return fromId;
    }

    public String getToId() {
        return toId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String toString() {
        return timestamp + " | " +
                type + " | Amount: Rs." + amount;
    }
}