package chinook.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import chinook.data.AlbumRepository;
import chinook.model.Album;

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
	
}
