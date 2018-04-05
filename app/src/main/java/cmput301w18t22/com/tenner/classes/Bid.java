package cmput301w18t22.com.tenner.classes;

import java.math.BigDecimal;
import java.util.Date;

import cmput301w18t22.com.tenner.Helpers.Status;


/**
 * The Bid class represents a bid on a specific task belonging to a specific user. The user becomes
 * the provider of the task if the bid is accepted.
 *
 * @author Team 22
 * @version 1.1
 * @see Bid
 */


public class Bid {

    private User owner;
    private BigDecimal value;
    private Date date;
    private Task task;
    private Status.bidStatus status;

    public Bid(User owner, String amount, Date date, Task task, Status.bidStatus status) {
        this.owner = owner;
        // Convert input string to Big Decimal
        this.value = new BigDecimal(amount);
        this.value.setScale(2);
        this.date = date;
        this.task = task;
        this.status = status;
    }


    /**
     * @return this bid's owner
     * @see Bid#setOwner
     */
    public User getOwner() {
        return owner;
    }


    /**
     * Set the owner of this bid
     *
     * @param owner User who made this bid
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }


    /**
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
     * Returns the value of this bid as a string following a dollar sign. For use with bid adapters.
     *
     * @return String respresenting value of this bid, preceded by dollar sign
     */
    public String toString() {
        return this.value.toString();
    }
}
