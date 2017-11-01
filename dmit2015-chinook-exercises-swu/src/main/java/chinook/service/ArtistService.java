package chinook.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import chinook.data.ArtistRepository;
import chinook.model.Artist;

@Stateless	// mark this class as a Stateless EJB
public class ArtistService {

	@Inject
	private ArtistRepository artistRepository;
	
	public void createArtist(String artistName) {
		Artist currentArtist = new Artist();
		currentArtist.setName(artistName);
		createArtist(currentArtist);
	}
	
	public void createArtist(Artist currentArtist) {
		artistRepository.persist(currentArtist);
	}
	
}
