package chinook.controller;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import chinook.model.Track;
import chinook.service.TrackService;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class TrackController implements Serializable {

	@Inject
	private Logger log;
	
	@Inject
	private TrackService trackService;
	
	private List<Track> tracks;							// +getter

	private List<Track> tracksByGenre;					// +getter

	private Track currentNewTrack = new Track();		// +getter +setter
	private Integer currentSelectedAlbumId;				// +getter +setter
	private Integer currentSelectedMediaTypeId;			// +getter +setter
	private Integer currentSelectedGenreId;				// +getter +setter
	
	@PostConstruct
	void init() {
		tracks = trackService.findAll();
	}

	public List<Track> getTracks() {
		return tracks;
	}
	

	public List<Track> getTracksByGenre() {
		return tracksByGenre;
	}
	
	public Track getCurrentNewTrack() {
		return currentNewTrack;
	}

	public void setCurrentNewTrack(Track currentNewTrack) {
		this.currentNewTrack = currentNewTrack;
	}
	

	public Integer getCurrentSelectedAlbumId() {
		return currentSelectedAlbumId;
	}

	public void setCurrentSelectedAlbumId(Integer currentSelectedAlbumId) {
		this.currentSelectedAlbumId = currentSelectedAlbumId;
	}

	public Integer getCurrentSelectedMediaTypeId() {
		return currentSelectedMediaTypeId;
	}

	public void setCurrentSelectedMediaTypeId(Integer currentSelectedMediaTypeId) {
		this.currentSelectedMediaTypeId = currentSelectedMediaTypeId;
	}

	public Integer getCurrentSelectedGenreId() {
		return currentSelectedGenreId;
	}

	public void setCurrentSelectedGenreId(Integer currentSelectedGenreId) {
		this.currentSelectedGenreId = currentSelectedGenreId;
	}

	public void findTracksByGenre() {
		if( !Faces.isPostback() ) {
			// verify that a valid genreId was set
			if( currentSelectedGenreId > 0) {
				tracksByGenre = trackService.findAllByGenreId(currentSelectedGenreId);
				if( tracksByGenre.size() == 0 ) {
					Messages.addGlobalInfo("There are no tracks for genreID {0}", 
							currentSelectedGenreId);
				}
			} else {
				Messages.addGlobalError("Bad request. A valid genreID is required.");
			}
		}
	}

	public void createNewTrack() {
		try {
			trackService.createTrack(currentNewTrack, currentSelectedAlbumId, currentSelectedMediaTypeId, currentSelectedGenreId);
			Messages.addGlobalInfo("Create track was successful.");
			currentNewTrack = new Track();
		} catch(Exception e) {
			Messages.addGlobalError("Create track was not successful");
			log.info(e.getMessage());
		}
	}
		
}
