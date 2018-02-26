package cmput301w18t22.com.tenner;

import android.test.ActivityInstrumentationTestCase2;
import java.io.IOException;

public class BidTest extends ActivityInstrumentationTestCase2 {

	public BidTest() {
		super(MyBidsActivity.class);
	
	}

	public void testSetOwner() {
		
		Bids bid = new Bids();
		bid.setOwner("Name");
		
		assertTrue(bid.owner());
		
	}
	
	public void testSetValue() {
		Bids bid = new Bids();
		bid.setValue(1.0);
		
		assertTrue(bid.value());
	}
	
	
	public void testGetOwner() {
		Bids bid = new Bids();
		bid.setOwner("Name");
		
		assertEquals(bid.getOwner(), "Name");
	}

	public void testGetValue() {
		Bids bid = new Bids();
		bid.setValue(1.0);
		assertEquals(bid.getValue(), 1.0);
	}

}