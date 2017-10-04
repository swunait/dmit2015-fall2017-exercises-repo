package chinook.data;

import java.util.List;

import chinook.model.Album;

public class AlbumRepository extends AbstractJpaRepository<Album> {
	private static final long serialVersionUID = 1L;

	public AlbumRepository() {
		super(Album.class);
	}

	public List<Album> findAllByArtistId(int artistId) {
		return getEntityManager()
				.createQuery("SELECT a FROM Album a WHERE a.artist.artistId = :idValue", Album.class)
				.setParameter("idValue", artistId)
				.getResultList();
	}
}
