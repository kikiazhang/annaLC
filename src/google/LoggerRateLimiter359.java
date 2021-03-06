package google;

import java.util.HashMap;

/**
 * Design a logger system that receive stream of messages along with its
 * timestamps, each message should be printed if and only if it is not printed
 * in the last 10 seconds.
 * 
 * Given a message and a timestamp (in seconds granularity), return true if the
 * message should be printed in the given timestamp, otherwise returns false.
 * 
 * It is possible that several messages arrive roughly at the same time.
 */
public class LoggerRateLimiter359 {
	private HashMap<String, Integer> msgDict;

	/** Initialize your data structure here. */
	public LoggerRateLimiter359() {
		msgDict = new HashMap<>();
	}

	/**
	 * Returns true if the message should be printed in the given timestamp,
	 * otherwise returns false. If this method returns false, the message will not
	 * be printed. The timestamp is in seconds granularity.
	 */
	public boolean shouldPrintMessage(int timestamp, String message) {
		if (!this.msgDict.containsKey(message)) {
			this.msgDict.put(message, timestamp);
			return true;
		}

		Integer oldTimestamp = this.msgDict.get(message);
		if (timestamp - oldTimestamp >= 10) {
			this.msgDict.put(message, timestamp);
			return true;
		} else {
			return false;
		}
	}
}
