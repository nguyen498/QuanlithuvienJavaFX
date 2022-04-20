
import java.sql.Date;

public class LendingTicket {
    
    private int id;

    private int totalBookLended;

    private Date dateLending;

    private int status;
    
    private int cardID;

    public LendingTicket() {
    }

    public LendingTicket(int id, int totalBookLended, Date dateLanding, int status, int cardID) {
        this.id = id;
        this.totalBookLended = totalBookLended;
        this.dateLending = dateLanding;
        this.status = status;
        this.cardID = cardID;
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
     * @return the totalBookLended
     */
    public int getTotalBookLended() {
        return totalBookLended;
    }

    /**
     * @param totalBookLended the totalBookLended to set
     */
    public void setTotalBookLended(int totalBookLended) {
        this.totalBookLended = totalBookLended;
    }

    /**
     * @return the dateLanding
     */
    public Date getDateLanding() {
        return dateLending;
    }

    /**
     * @param dateLanding the dateLanding to set
     */
    public void setDateLanding(Date dateLanding) {
        this.dateLending = dateLanding;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the cardID
     */
    public int getCardID() {
        return cardID;
    }

    /**
     * @param cardID the cardID to set
     */
    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    

	

}
