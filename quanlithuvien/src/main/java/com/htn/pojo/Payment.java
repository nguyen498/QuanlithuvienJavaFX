
import java.sql.Date;

public class Payment {

    private int id;
    
    private double total;

    private double fine;

    private Date createdDate;

    private int accountID;
    
    private int lendingID;

    public Payment() {
    }

    public Payment(int id, double total, double fine, Date createdDate, int accountID, int lendingID) {
        this.id = id;
        this.total = total;
        this.fine = fine;
        this.createdDate = createdDate;
        this.accountID = accountID;
        this.lendingID = lendingID;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the fine
     */
    public double getFine() {
        return fine;
    }

    /**
     * @param fine the fine to set
     */
    public void setFine(double fine) {
        this.fine = fine;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the accountID
     */
    public int getAccountID() {
        return accountID;
    }

    /**
     * @param accountID the accountID to set
     */
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    /**
     * @return the lendingID
     */
    public int getLendingID() {
        return lendingID;
    }

    /**
     * @param lendingID the lendingID to set
     */
    public void setLendingID(int lendingID) {
        this.lendingID = lendingID;
    }
    
    

    
        
}
