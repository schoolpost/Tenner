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

    public String toString() {return "$ " + this.value.toString();}
}
