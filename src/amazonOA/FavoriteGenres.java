package amazonOA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoriteGenres {
	/**
	 * Given a map Map<String, List<String>> userSongs with user names as keys and a
	 * list of all the songs that the user has listened to as values.
	 * 
	 * Also given a map Map<String, List<String>> songGenres, with song genre as
	 * keys and a list of all the songs within that genre as values. The song can
	 * only belong to only one genre.
	 * 
	 * The task is to return a map Map<String, List<String>>, where the key is a
	 * user name and the value is a list of the user's favorite genre(s). Favorite
	 * genre is the most listened to genre. A user can have more than one favorite
	 * genre if he/she has listened to the same number of songs per each of the
	 * genres.
	 * 
	 * Example 1:
	 * 
	 * Input: userSongs = { "David": ["song1", "song2", "song3", "song4", "song8"],
	 * "Emma": ["song5", "song6", "song7"] }, songGenres = { "Rock": ["song1",
	 * "song3"], "Dubstep": ["song7"], "Techno": ["song2", "song4"], "Pop":
	 * ["song5", "song6"], "Jazz": ["song8", "song9"] }
	 * 
	 * Output: { "David": ["Rock", "Techno"], "Emma": ["Pop"] }
	 * 
	 * Explanation: David has 2 Rock, 2 Techno and 1 Jazz song. So he has 2 favorite
	 * genres. Emma has 2 Pop and 1 Dubstep song. Pop is Emma's favorite genre.
	 */
	/**
	 * Time: O((S + G) + U * (S + G)) => O((U + 1) * (S + G)) => O(U * (S + G))
	 * Space: O(S + G + U * G) => O(S + U * G) Where S = total number of songs, G =
	 * total number of genres, U = total number of users
	 */
	public Map<String, List<String>> favoritegenre(Map<String, List<String>> userMap,
			Map<String, List<String>> genreMap) {
		Map<String, List<String>> res = new HashMap<>();
		Map<String, String> songstogenre = new HashMap<>();// song, genre

		for (String genre : genreMap.keySet()) {
			List<String> songs = genreMap.get(genre);
			for (String song : songs) {
				songstogenre.put(song, genre);
			}
		}
		Map<String, Integer> count = new HashMap<String, Integer>();// genre, count for one user
		int max = 0;
		for (String user : userMap.keySet()) {
			count = new HashMap<String, Integer>();
			max = 0;
			res.put(user, new ArrayList<String>());
			List<String> songs = userMap.get(user);
			for (String song : songs) {
				String genre = songstogenre.get(song);
				int c = count.getOrDefault(genre, 0) + 1;
				count.put(genre, c);
				max = Math.max(c, max);
			}
			for (String key : count.keySet()) {
				if (count.get(key) == max) {
					res.get(user).add(key);
				}
			}
		}
		return res;
	}
}
