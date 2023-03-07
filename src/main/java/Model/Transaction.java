package Model;
import java.util.Date;

public class Transaction {
    public enum Transaction_Type {
        NULL, DEPOSIT, WITHDRAWL, TRANSFER
    };
    public Transaction_Type transaction_type;
    /*
     * Transaction Size
     */
    public int sum;
    /*
     * Transaction time
     */
    public Date date;
    /*
     * From account to account
     * Should be the same for DEPOSIT/WITHDRAWL
     */
    public int account_from, account_to;

    public Transaction() {
        this.transaction_type = Transaction_Type.NULL;
    }

    public Transaction(Transaction_Type type, int sum, Date date, int from, int to) {
        this.transaction_type = type;
        this.sum = sum;
        this.date = date;
        this.account_from = from;
        this.account_to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction transact = (Transaction) o;
        return (transaction_type == Transaction_Type.NULL && transact.transaction_type == Transaction_Type.NULL) || (
            transaction_type == transact.transaction_type && sum == transact.sum && date.getTime() == transact.date.getTime() &&
            account_from == transact.account_from && account_to == transact.account_to
        );
    }

    @Override
    public String toString() {
        return "Transaction{transaction_type=" + transaction_type.name() +
            ", sum=" + sum + ", date=" + date.toString() +
            ", account_from=" + account_from +
            ", account_to=" + account_to +
            '}';
    }
}
