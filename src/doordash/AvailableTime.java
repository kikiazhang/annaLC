package doordash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * *Google Calendar, Outlook, iCal has been banned from your company! So an
 * intrepid engineer has decided to roll their own implementation. Unfortunately
 * one major missing feature is the ability to find out what time slots are free
 * for a particular individual.
 * 
 * Given a list of time blocks where a particular person is already booked/busy,
 * a start and end time to search between, a minimum duration to search for,
 * find all the blocks of time that a person is free for a potential meeting
 * that will last the aforementioned duration.
 * 
 * Given: starttime, endtime, duration, meetingslist -> suggestedmeetingtimes
 * 
 * Let's assume we abstract the representation of times as simple integers, so a
 * valid time is any valid integer supported by your environment. Here is an
 * example input:
 * 
 * meetingslist: [3,20], [-2, 0], [0,2], [16,17], [19,23], [30,40], [27, 33]
 * 
 * starttime: -5
 * 
 * endtime: 27
 * 
 * minduration: 2
 * 
 * expected answer:
 * 
 * freetime: [-5, -2], [23,27]*
 */
public class AvailableTime {
	public List<List<Integer>> availableTime(int[][] schedules, int min_time, int min_duration) {

		List<List<Integer>> res = new ArrayList<>();

		// Initialize min_time to current time
		int curr_time = min_time;

		// Sort array by start time
		Arrays.sort(schedules, Comparator.comparingInt(a -> a[0]));// sort by start time

		// Loop through all intervals
		for (int[] interval : schedules) {
			int st_tm = interval[0];
			int end_tm = interval[1];

			if (curr_time < st_tm) { // if current time is < start time of the interval
				if (st_tm - curr_time >= min_duration) { // if the time between the 2 is greater than min_duration
					res.add(Arrays.asList(curr_time, st_tm));
				}
				curr_time = end_tm; // Update end time to be current_time
			} else {
				curr_time = Math.max(end_tm, curr_time); // For overlapping intervals, make sure you tax the max between
															// current time and end time
			}
		}
		return res;

	}

	public static void main(String[] args) {

		int[][] schedules = { { 3, 20 }, { -2, 0 }, { 0, 2 }, { 16, 17 }, { 19, 23 }, { 30, 40 }, { 27, 33 } };

		AvailableTime doorDashInterview = new AvailableTime();

		System.out.println(doorDashInterview.availableTime(schedules, -5, 3));
	}
}
