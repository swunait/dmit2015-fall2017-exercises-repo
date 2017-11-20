package chinook.service;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import chinook.data.PlaylistRepository;
import chinook.model.Playlist;
import chinook.model.Track;

@Stateless
public class PlaylistService {

	@Inject
	private PlaylistRepository playlistRepository;
	
	public Playlist createPlaylist(String playlistName, Set<Track> playlistTracks) {
		// Create a new Playlist entity and persist the entity
		Playlist newPlaylist = new Playlist();
		newPlaylist.setName(playlistName);
		newPlaylist.setTracks(playlistTracks);
		playlistRepository.persist(newPlaylist);	
		return newPlaylist;
	}
}
