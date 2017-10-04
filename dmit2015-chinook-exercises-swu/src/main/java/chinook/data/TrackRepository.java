package chinook.data;

import chinook.model.Track;

public class TrackRepository extends AbstractJpaRepository<Track> {
	private static final long serialVersionUID = 1L;

	public TrackRepository() {
		super(Track.class);
	}

}
