package chinook.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import chinook.data.AlbumRepository;
import chinook.data.ArtistRepository;
import chinook.model.Album;
import chinook.model.Artist;
import chinook.service.AlbumService;

@Model
public class AlbumController {

	@Inject
	private AlbumRepository albumRepository;
	
	private List<Album> albums;
	
	@PostConstruct
	void init() {
		albums = albumRepository.findAll();
	}

	public List<Album> getAlbums() {
		return albums;
	}
	
	private Album currentNewAlbum = new Album();	// +getter +setter
	private Integer currentNewAlbumArtistId;		// +getter +setter
	@Inject
	private ArtistRepository artistRepository;
	@Inject
	private AlbumService albumService;
	
	public void createNewAlbum() {
		try {
			// Find the Artist of the Album
			Artist albumArist = artistRepository.find(currentNewAlbumArtistId);
			// Set the Artist of the Album
			currentNewAlbum.setArtist(albumArist);
			// Save the new Album
			albumService.createAlbum(currentNewAlbum);
			// Send a feedback message to the client
			Messages.addGlobalInfo("Create new album was successful.");
			// Clear all fields in web form
			currentNewAlbum = new Album();
			currentNewAlbumArtistId = null;
		} catch(Exception e) {
			Messages.addGlobalError("Create new album was not successful. {0}",
					e.getMessage());
		}
	}

	public Album getCurrentNewAlbum() {
		return currentNewAlbum;
	}

	public void setCurrentNewAlbum(Album currentNewAlbum) {
		this.currentNewAlbum = currentNewAlbum;
	}

	public Integer getCurrentNewAlbumArtistId() {
		return currentNewAlbumArtistId;
	}

	public void setCurrentNewAlbumArtistId(Integer currentNewAlbumArtistId) {
		this.currentNewAlbumArtistId = currentNewAlbumArtistId;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
