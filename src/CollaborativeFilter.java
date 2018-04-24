import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author Harsha Kokel
 *
 */
public class CollaborativeFilter {
	static HashMap<String, HashSet<String>> playlistTrackMap;
	static HashSet<String> trainTracks;
	static HashMap<String, String> testMap;
	static HashSet<String> testTracks;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String trainingfilename = args[0];
		playlistTrackMap = new HashMap<String, HashSet<String>>();
		trainTracks = new HashSet<String>();
		testMap = new HashMap<String, String>();
		testTracks = new HashSet<String>();
		hashPlaylistTracks(trainingfilename);
		testPlaylistTracks();
	}

	private static void testPlaylistTracks() {
		// TODO Auto-generated method stub
		Iterator<String> it = testMap.keySet().iterator();
		String playlist1, track; 
		double k=0.0, predictedValue;
		while(it.hasNext()) {
			playlist1 = it.next();
			predictedValue = 0.0;
			track = testMap.get(playlist1);
			for (String playlist2 : playlistTrackMap.keySet()) {
				if(playlistTrackMap.get(playlist2).contains(track)) {					
					predictedValue += calculateCorrelation(playlist1, playlist2);
				}
			}
			System.out.println(playlist1 +" - "+ track +" -> "+ predictedValue);
		}
	}

	private static double calculateCorrelation(String playlist1, String playlist2) {
		// TODO Auto-generated method stub
		HashSet<String> commonTracks = (HashSet<String>) playlistTrackMap.get(playlist1).clone();
		commonTracks.retainAll(playlistTrackMap.get(playlist2));
		return commonTracks.size();
	}

	/**
	 * Hash the Playlist and Track Data set from the training file
	 * 
	 * @param filename
	 */
	private static void hashPlaylistTracks(String filename) {
		// TODO Auto-generated method stub
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",", playlistId;
		String[] playlist;
		HashSet<String> tracks;
		
		try {
			br = new BufferedReader(new FileReader(filename));
			int counts = 0, rand;
			String track, testTrack;
			while ((line = br.readLine()) != null) {
				 playlist = line.split(cvsSplitBy);
				 playlistId = playlist[0];
				 playlist= (String[]) ArrayUtils.removeElement(playlist, playlistId);
				 counts += playlist.length;
				 tracks = new HashSet<String>();
				 rand = new Random().nextInt(playlist.length);
				 testTrack = playlist[rand];
				 playlist= (String[]) ArrayUtils.removeElement(playlist, testTrack);
				 for(int i = 0; i< playlist.length; i++ ) {
						 tracks.add(playlist[i]);
				 }
				 playlistTrackMap.put(playlistId, tracks);
				 trainTracks.addAll(tracks);
				 testTracks.add(testTrack);
				 testMap.put(playlistId, testTrack);
			}
			System.out.println("Number of Playlists: "+ playlistTrackMap.size());
			System.out.println("Number of tracks: "+ counts);
			System.out.println("Number of unique train tracks: "+ trainTracks.size());
			System.out.println("Number of unique test tracks: "+ testTracks.size());
			HashSet<String> temp = (HashSet<String>) testTracks.clone();
			temp.removeAll(trainTracks);
			System.out.println("Number of new test tracks: "+temp.size());
			System.out.println("Number of test playlists: "+ testMap.size());
			// Drop Test cases with new Tracks;
			Iterator it = ((HashMap<String, String>) testMap.clone()).keySet().iterator();
			String key;
			HashSet<String> tempSet;
			while (it.hasNext()) {
				key = (String) it.next();
				if(temp.contains(testMap.get(key))) {
					testMap.remove(key);
				}
			}
			System.out.println("Number of test playlists: "+ testMap.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
