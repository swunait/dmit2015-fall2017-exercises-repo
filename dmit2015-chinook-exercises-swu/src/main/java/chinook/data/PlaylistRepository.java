package chinook.data;

import chinook.model.Playlist;

public class PlaylistRepository extends AbstractJpaRepository<Playlist> {
	private static final long serialVersionUID = 1L;

	public PlaylistRepository() {
		super(Playlist.class);
	}

}
