package chinook.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import chinook.data.ArtistRepository;
import chinook.model.Artist;

@Model
public class ArtistController {

	@Inject
	private ArtistRepository artistRepository;
	
	private List<Artist> artists;
	
	@PostConstruct
	void init() {
		artists = artistRepository.findAll();
	}

	public List<Artist> getArtists() {
		return artists;
	}
	
}
