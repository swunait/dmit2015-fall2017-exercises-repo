package chinook.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import chinook.data.TrackRepository;
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
	
}
