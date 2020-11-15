package tags.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement the class UndergroundSystem that supports three methods:
 * 
 * 1. checkIn(int id, string stationName, int t)
 * 
 * A customer with id card equal to id, gets in the station stationName at time
 * t. A customer can only be checked into one place at a time. 2. checkOut(int
 * id, string stationName, int t)
 * 
 * A customer with id card equal to id, gets out from the station stationName at
 * time t. 3. getAverageTime(string startStation, string endStation)
 * 
 * Returns the average time to travel between the startStation and the
 * endStation. The average time is computed from all the previous traveling from
 * startStation to endStation that happened directly. Call to getAverageTime is
 * always valid. You can assume all calls to checkIn and checkOut methods are
 * consistent. That is, if a customer gets in at time t1 at some station, then
 * it gets out at time t2 with t2 > t1. All events happen in chronological
 * order.
 */
public class DesignUndergroundSystem1396 {
	Map<String, Pair<Double, Double>> journey = new HashMap<>();// start + end stations, <total time, total trips
																// number>
	Map<Integer, Pair<String, Integer>> checkin = new HashMap<>();// user id, <start station, checkin time>

	public DesignUndergroundSystem1396() {

	}

	public void checkIn(int id, String stationName, int t) {
		checkin.put(id, new Pair<>(stationName, t));
	}

	public void checkOut(int id, String stationName, int t) {
		// 从checkin里面拿到start 时间，地点
		Pair<String, Integer> checkinDataForId = checkin.get(id);
		String startStation = checkinDataForId.getKey();
		Integer checkinTime = checkinDataForId.getValue();

		String routeKey = stationsKey(startStation, stationName);// start + end station
		// 拿到之前的total
		Pair<Double, Double> routeStats = journey.getOrDefault(routeKey, new Pair<>(0.0, 0.0));
		Double totalTime = routeStats.getKey();
		Double totalTrips = routeStats.getValue();

		double tripTime = t - checkinTime;
		journey.put(routeKey, new Pair<>(totalTime + tripTime, totalTrips + 1));
		// 记得去掉checkin中的start信息
		checkin.remove(id);
	}

	public double getAverageTime(String startStation, String endStation) {
		// 一直是valid，不考虑journey里面没有
		String routeKey = stationsKey(startStation, endStation);
		Double totalTime = journey.get(routeKey).getKey();
		Double totalTrips = journey.get(routeKey).getValue();

		return totalTime / totalTrips;
	}

	private String stationsKey(String startStation, String endStation) {
		return startStation + "->" + endStation;
	}

	class Pair<K, V> {

		private K key;

		public K getKey() {
			return key;
		}

		private V value;

		public V getValue() {
			return value;
		}

		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}
