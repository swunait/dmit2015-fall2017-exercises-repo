package chinook.controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.omnifaces.util.Messages;

import chinook.model.Track;
import chinook.service.PlaylistService;
import chinook.service.TrackService;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class CreatePlaylistController implements Serializable {

	@NotBlank(message="Playlist Name field value is reuquired")
	private String playlistName;								// +getter +setter
	@NotNull(message="TrackId field value is required")
	private Integer currentSelectedTrackId;						// +getter +setter
	private Set<Track> tracks = new HashSet<>();					// +getter
	
	@Inject
	private TrackService trackService;
	
	@Inject
	private PlaylistService playlistService;
	
	public void addTrackToPlaylist() {
		// Find the Track entity with the currentSelectedTrackid
		Track currrentTrack = trackService.findOne(currentSelectedTrackId);
		if (currrentTrack == null) {
			Messages.addGlobalWarn("{0} is not a valid TrackId value.", currentSelectedTrackId);
		} else {
			tracks.add(currrentTrack);
			Messages.addGlobalInfo("Add track was successful.");
		}
	}
	
	public void removeTrack(Track currentTrack) {
		tracks.remove(currentTrack);
		Messages.addGlobalInfo("Remove track was successful");
	}
	
	public void createNewPlaylist() {
		try {
			playlistService.createPlaylist(playlistName, tracks);
			Messages.addGlobalInfo("Create playlist was successful");
			playlistName = "";
			tracks.clear();
		} catch(Exception e) {
			Messages.addGlobalError("Create playlist was not successful");
		}
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public Integer getCurrentSelectedTrackId() {
		return currentSelectedTrackId;
	}

	public void setCurrentSelectedTrackId(Integer currentSelectedTrackId) {
		this.currentSelectedTrackId = currentSelectedTrackId;
	}

	public Set<Track> getTracks() {
		return tracks;
	}
	
	
}
