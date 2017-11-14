package chinook.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import chinook.data.AlbumRepository;
import chinook.data.GenreRepository;
import chinook.data.MediaTypeRepository;
import chinook.data.TrackRepository;
import chinook.model.Album;
import chinook.model.Genre;
import chinook.model.MediaType;
import chinook.model.Track;

@Stateless
public class TrackService {

	@Inject
	private TrackRepository trackRepository;
	
	@Inject
	private AlbumRepository albumRepository;
	
	@Inject
	private MediaTypeRepository mediaTypeRepository;
	
	@Inject
	private GenreRepository genreRepository;
	
	public List<Track> findAll() {
		return trackRepository.findAll();
	}
	
	public List<Track> findAllByGenreId(int genreId) {
		return trackRepository.findAllByGenreId(genreId);
	}
	
	private void createTrack(Track newTrack) {
		try {
			trackRepository.persist(newTrack);
		} catch(Exception e) {
			
		}
	}
	
	public void createTrack(Track newTrack, Integer albumId, Integer mediaTypeId, Integer genreId) {
		if (albumId != null) {
			Album currentAlbum = albumRepository.find(albumId);
			newTrack.setAlbum(currentAlbum);
		}
		if (mediaTypeId != null) {
			MediaType currentMediaType = mediaTypeRepository.find(mediaTypeId);
			newTrack.setMediaType(currentMediaType);
		}
		if (genreId != null) {
			Genre currentGenre = genreRepository.find(genreId);
			newTrack.setGenre(currentGenre);
		}
		createTrack(newTrack);
	}
}
