package amazonOA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class TopNBuzzwords {
	/**
	 * You work on a team whose job is to understand the most sought after toys for
	 * the holiday season. A teammate of yours has built a webcrawler that extracts
	 * a list of quotes about toys from different articles. You need to take these
	 * quotes and identify which toys are mentioned most frequently. Write an
	 * algorithm that identifies the top N toys out of a list of quotes and list of
	 * toys.
	 * 
	 * Your algorithm should output the top N toys mentioned most frequently in the
	 * quotes.
	 * 
	 * Input: The input to the function/method consists of five arguments:
	 * 
	 * numToys, an integer representing the number of toys topToys, an integer
	 * representing the number of top toys your algorithm needs to return; toys, a
	 * list of strings representing the toys, numQuotes, an integer representing the
	 * number of quotes about toys; quotes, a list of strings that consists of
	 * space-sperated words representing articles about toys
	 * 
	 * Output: Return a list of strings of the most popular N toys in order of most
	 * to least frequently mentioned
	 * 
	 * Note: The comparison of strings is case-insensitive. If the value of topToys
	 * is more than the number of toys, return the names of only the toys mentioned
	 * in the quotes. If toys are mentioned an equal number of times in quotes, sort
	 * alphabetically.
	 * 
	 * Example 1:
	 * 
	 * Input: numToys = 6 topToys = 2 toys = ["elmo", "elsa", "legos", "drone",
	 * "tablet", "warcraft"] numQuotes = 6 quotes = [ "Elmo is the hottest of the
	 * season! Elmo will be on every kid's wishlist!", "The new Elmo dolls are super
	 * high quality", "Expect the Elsa dolls to be very popular this year, Elsa!",
	 * "Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good", "For
	 * parents of older kids, look into buying them a drone", "Warcraft is slowly
	 * rising in popularity ahead of the holiday season" ];
	 * 
	 * Output: ["elmo", "elsa"]
	 * 
	 * Explanation: elmo - 4 elsa - 4 "elmo" should be placed before "elsa" in the
	 * result because "elmo" appears in 3 different quotes and "elsa" appears in 2
	 * different quotes.
	 * 
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(topToys(6, 2, new String[] { "elmo", "elsa", "legos", "drone", "tablet", "warcraft" }, 6,
				new String[] { "Elmo is the hottest of the season! Elmo will be on every kid's wishlist!",
						"The new Elmo dolls are super high quality",
						"Expect the Elsa dolls to be very popular this year, Elsa!",
						"Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good",
						"For parents of older kids, look into buying them a drone",
						"Warcraft is slowly rising in popularity ahead of the holiday season" }));
	}

	// NLog(K)
	public static List<String> topToys(int numToys, int topToys, String[] toys, int numQuotes, String[] quotes) {
		Map<String, int[]> freq = new HashMap<>();// toy, [number of occur, number of occur in different quotes]
		for (String toy : toys) {
			freq.put(toy, new int[] { 0, 0 });
		}

		// if the quote is the same, then only count quote once.
		Set<String> quotesSet = new HashSet<>();
		for (String quote : quotes) {
			quotesSet.add(quote);
		}

		for (String quote : quotes) {
			Set<String> used = new HashSet<>();
			// position is not letter, then split
			String[] words = quote.toLowerCase().split("\\W+");
			for (String word : words) {
				// not a toy name, skip
				if (!freq.containsKey(word)) {
					continue;
				}

				int[] nums = freq.get(word);
				// tot num occur
				nums[0]++;
				// current quote, first occur this toy, nums[1]++
				if (!used.contains(word)) {
					nums[1]++;
				}

				used.add(word);
			}
		}

		PriorityQueue<String> pq = new PriorityQueue<>((t1, t2) -> {
			// choose the bigger occur number
			if (freq.get(t1)[0] != freq.get(t2)[0]) {
				return freq.get(t1)[0] - freq.get(t2)[0];
			}
			// occur number is same, then choose the bigger number of different quotes.
			if (freq.get(t1)[1] != freq.get(t2)[1]) {
				return freq.get(t1)[1] - freq.get(t2)[1];
			}
			// other, alphabetically
			return t2.compareTo(t1);
		});
		// how many number to print out
		if (topToys > numToys) {
			for (String toy : freq.keySet()) {
				if (freq.get(toy)[0] > 0) {
					pq.add(toy);
				}
			}
		} else {
			for (String toy : toys) {
				pq.add(toy);

				if (pq.size() > topToys) {
					pq.poll();
				}
			}
		}

		List<String> output = new ArrayList<>();
		while (!pq.isEmpty()) {
			output.add(pq.poll());
		}

		Collections.reverse(output);

		return output;
	}
	
	
}
