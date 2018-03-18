package cmput301w18t22.com.tenner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Schoolpost on 2018-02-26.
 */

public class Bid {

    private User owner;
    private BigDecimal value;
    private Date date;
    private Task task;

    public void Bid(User owner, String amount, Date date, Task task) {
        this.owner = owner;

        // Convert input string to Big Decimal
        this.value = new BigDecimal(amount);
        this.value.setScale(2);

        this.date = date;

        this.task = task;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Task getTask() {return this.task;}

    public void setTask(Task task) { this.task = task; }

    public String toString() {return "$ " + this.value.toString();}
}
