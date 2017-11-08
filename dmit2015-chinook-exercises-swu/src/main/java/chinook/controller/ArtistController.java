package chinook.controller;

import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.Part;

import org.hibernate.validator.constraints.NotBlank;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import chinook.data.ArtistRepository;
import chinook.model.Artist;
import chinook.service.ArtistService;

@Model
public class ArtistController {
	
	private Part uploadedFile;	// +getter +setter
	
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
	
	@Inject
	private ArtistService artistService;
	
	@NotBlank(message="Artist Name value is required.")
	private String artistName;	// +getter+setter
	
	public void createNewArtist() {
		byte[] picture = null;
		if (uploadedFile != null) {
			long size = uploadedFile.getSize();
			InputStream content;
			try {
				content = uploadedFile.getInputStream();
				picture = new byte[(int) size];
				content.read(picture);				
			} catch(Exception e) {
				Messages.addGlobalError("File upload was not successful.");
			}
		}
		try {
			artistService.createArtist(artistName, picture);
			Messages.addGlobalInfo("Create artist was successful.");
			artistName = "";
		} catch(Exception e) {
			Messages.addGlobalError("Error creating artist with exception: {0}", 
					e.getMessage());
			//Messages.addGlobalWarn("Create artist was not successful.");
		}
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	
}
