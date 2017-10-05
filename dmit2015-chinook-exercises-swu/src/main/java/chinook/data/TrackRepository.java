package chinook.data;

import java.util.List;

import chinook.model.Track;

public class TrackRepository extends AbstractJpaRepository<Track> {
	private static final long serialVersionUID = 1L;

	public TrackRepository() {
		super(Track.class);
	}
	
	public List<Track> findAllByGenreId(int genreId) {
		return getEntityManager().createQuery(
			"SELECT t FROM Track t WHERE t.genre.genreId = :idValue", Track.class)
			.setParameter("idValue", genreId)
			.getResultList();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
