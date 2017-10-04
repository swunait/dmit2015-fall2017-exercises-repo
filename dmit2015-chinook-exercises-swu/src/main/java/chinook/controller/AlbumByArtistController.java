package chinook.controller;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import chinook.data.AlbumRepository;
import chinook.model.Album;

@Model
public class AlbumByArtistController {

	@Inject
	private AlbumRepository albumRepository;
	
	private List<Album> albums;
	
	private int selectedArtistId;
	
	public void findAlbumsByArtist() {
		if( !FacesContext.getCurrentInstance().isPostback() ) {
			if( selectedArtistId == 0 ) {
//				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//				FacesContext.getCurrentInstance().responseComplete();
//				ec.redirect(ec.getRequestContextPath() + "/pages/assignment02/productlines.xhtml");
				String message = String.format("Bad request. Unknown artistId %s", selectedArtistId);
				Messages.addGlobalInfo(message);
			} else {
				albums = albumRepository.findAllByArtistId(selectedArtistId);
				if( albums == null || albums.size() == 0 ) {
					String message = String.format("There are no albums associated with artistId %s", selectedArtistId);
					Messages.addGlobalInfo(message);
				}
			}
			
		}
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public int getSelectedArtistId() {
		return selectedArtistId;
	}

	public void setSelectedArtistId(int selectedArtistId) {
		this.selectedArtistId = selectedArtistId;
	}
	
}
