package chinook.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.util.Messages;

import chinook.model.Album;
import chinook.service.AlbumService;

@SuppressWarnings("serial")
@Named			// allows object to be access using JSF-EL
@ViewScoped		// maintain object until navigation is forwarded to another page
public class FindOneAlbumController implements Serializable {

	@Inject
	private AlbumService albumService;
	
	@NotNull(message="AlbumID field value is required.")
	private Integer searchValue;		// +getter+setter
	private Album querySingleResult;	// +getter
	
	@Inject
	private Logger log;
	
	public Integer getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(Integer searchValue) {
		this.searchValue = searchValue;
	}

	public Album getQuerySingleResult() {
		return querySingleResult;
	}

	public void findAlbum() {
		try {
			querySingleResult = albumService.findOneAlbum(
					searchValue);
			if( querySingleResult == null ) {
				Messages.addGlobalInfo("We found 0 results for {0}",
						searchValue);
			} else {
				Messages.addGlobalInfo("1 result for {0}", 
						searchValue);				
			}
		} catch(Exception e) {
			log.info(e.getMessage());
			querySingleResult = null;
			Messages.addGlobalInfo("We found 0 results for {0}",
					searchValue);
		}
	}
	
	public void updateAlbum() {
		try {
			albumService.updateAlbum(querySingleResult);
			Messages.addGlobalInfo("Update was successful");
		} catch(Exception e) {;	// +getter
			Messages.addGlobalInfo("Update was not sucessful");
			log.info(e.getMessage());
		}
		
	}
	
	public void deleteAlbum() {
		try {
			albumService.removeAlbum(querySingleResult);
			querySingleResult = null;
			Messages.addGlobalInfo("Delete was successful");
		} catch(Exception e) {
			Messages.addGlobalInfo("Delete was not sucessful");
			log.info(e.getMessage());
		}
		
	}
	
	
	
	
}
