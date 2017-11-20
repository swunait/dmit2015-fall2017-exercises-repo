package chinook.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

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
	
	@Inject
	private Logger log;
	
	public Track findOne(int trackId) {
		Track currentTrack = null;
		try {
			currentTrack = trackRepository.find(trackId);
		} catch(NoResultException nre) {
			currentTrack = null;
		}
		return currentTrack;
	}
	
	public List<Track> findAll() {
		return trackRepository.findAll();
	}
	
	public List<Track> findAllByGenreId(int genreId) {
		return trackRepository.findAllByGenreId(genreId);
	}
	
	public void createTrack(Track newTrack) {
		try {
			trackRepository.persist(newTrack);
		} catch(Exception e) {
			log.info(e.getMessage());
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
