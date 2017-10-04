package chinook.data;

import chinook.model.Artist;

public class ArtistRepository extends AbstractJpaRepository<Artist> {
	private static final long serialVersionUID = 1L;

	public ArtistRepository() {
		super(Artist.class);
	}
}
