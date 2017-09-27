package chinook.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import chinook.data.PlaylistRepository;
import chinook.model.Playlist;

@Model
public class PlaylistController {

	@Inject
	private PlaylistRepository playlistRepository;
	
	private List<Playlist> playlists;
	
	@PostConstruct
	void init() {
		playlists = playlistRepository.findAll();
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}
	
}
