package chinook.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import chinook.data.TrackRepository;
import chinook.model.Genre;
import chinook.model.Track;

@Model
public class TrackController {

	@Inject
	private TrackRepository trackRepository;
	
	private List<Track> tracks;
	
	@PostConstruct
	void init() {
		tracks = trackRepository.findAll();
	}

	public List<Track> getTracks() {
		return tracks;
	}
	
	private List<Track> tracksByGenre;	// getter
	private int currentSelectedGenreId;	// getter/setter
//	private Genre currentSelectedGenre;	// getter
	
	public void findTracksByGenre() {
		if( !FacesContext.getCurrentInstance().isPostback() ) {
			// verify that a valid genreId was set
			if( currentSelectedGenreId > 0) {
				tracksByGenre = trackRepository.findAllByGenreId(currentSelectedGenreId);
				if( tracksByGenre.size() == 0 ) {
					Messages.addGlobalInfo("There are no tracks for genreID {0}", 
							currentSelectedGenreId);
				}
			} else {
				Messages.addGlobalError("Bad request. A valid genreID is required.");
			}
		}
	}

	public int getCurrentSelectedGenreId() {
		return currentSelectedGenreId;
	}

	public void setCurrentSelectedGenreId(int currentSelectedGenreId) {
		this.currentSelectedGenreId = currentSelectedGenreId;
	}

	public List<Track> getTracksByGenre() {
		return tracksByGenre;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
