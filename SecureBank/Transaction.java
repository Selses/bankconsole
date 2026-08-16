public class Transaction {

    private String transactionId;
    private String type;
    private double amount;
    private String fromId;
    private String toId;

    public Transaction(String transactionId, String type, double amount,
                       String fromId, String toId) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.fromId = fromId;
        this.toId = toId;
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

    public String toString() {
        return type + " | Amount: Rs." + amount;
    }
}