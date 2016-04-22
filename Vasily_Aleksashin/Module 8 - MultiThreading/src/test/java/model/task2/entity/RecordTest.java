package model.task2.entity;

import org.junit.Test;
import utility.Constant;

import static org.junit.Assert.assertEquals;

public class RecordTest {
	@Test
	public void testToString() {
		final String expected = "Record{senderId=1, recipientId=2, sum=100}";
		assertEquals(expected, new Record(Constant.SENDER_ID, Constant.RECIPIENT_ID, Constant.SUM).toString());
	}
}