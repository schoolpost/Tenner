package cmput301w18t22.com.tenner.classes;

import java.math.BigDecimal;
import java.util.Date;

import cmput301w18t22.com.tenner.helpers.StatusHelper;


/**
 * The Bid class represents a bid on a specific task belonging to a specific user. The user becomes
 * the provider of the task if the bid is accepted.
 *
 * @author Team 22
 * @version 1.2
 * @see Bid
 */

public class Bid {

    private User owner;
    private BigDecimal value;
    private Date date;
    private Task task;
    private String status;

    public Bid(User owner, String amount, Date date, Task task) {
        this.owner = owner;
        this.value = new BigDecimal(amount);
        this.value.setScale(2);
        this.date = date;
        this.task = task;
        this.status = status;
    }

    /**
     * Passes the bid's owner. 
     *
     * @return this bid's owner
     * @see Bid#setOwner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Set the owner of this bid.
     *
     * @param owner User who made this bid
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Passes the value of the bid.
     *
     * @return this bid's value (BigDecimal)
     * @see Bid#setValue
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Set the value of this bid, must be greater than $0. Should never be called, since a user
     * would just create a new bid instead of modifying an old one.
     *
     * @param value New value to set for this bid
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Passes the date of the bids last modification. 
     *
     * @return this bid's date
     * @see Bid#setDate
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the date this bid was created.
     *
     * @param date New date to set for this bid
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Passes the task the bid was made on. 
     *
     * @return this bid's task
     * @see Bid#setTask
     */
    public Task getTask() {
        return this.task;
    }

    /**
     * Set the task that this bid was placed on.
     *
     * @param task Task for this bid
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * Pass the status of the bid.
     *
     * @return this bid's status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Change the status of the bid.
     *
     * @param The new status for the bid.
     */
    public void setStatus(String status) {
        StatusHelper statusHelper = new StatusHelper();
        String newStatus = statusHelper.getBidStatus(status);
        this.status = newStatus;
    }

    /**
     * Returns the value of this bid as a string following a dollar sign. For use with bid adapters.
     *
     * @return String respresenting value of this bid, preceded by dollar sign
     */
    public String toString() {
        return this.value.toString();
    }
}
