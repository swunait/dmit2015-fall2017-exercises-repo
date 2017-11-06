package chinook.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import chinook.data.AlbumRepository;
import chinook.model.Album;

@Stateless
public class AlbumService {

	@Inject
	private AlbumRepository albumRepository;
		
	public void createAlbum(Album currentAlbum) {
		albumRepository.persist(currentAlbum);
	}
	
	public Album updateAlbum(Album currentAlbum) {
		return albumRepository.edit(currentAlbum);
	}
	
	public void removeAlbum(Album currentAlbum) {
		albumRepository.remove(currentAlbum);
	}
	
	public Album findOneAlbum(int albumId) {
		return albumRepository.find(albumId);
	}
	
}
