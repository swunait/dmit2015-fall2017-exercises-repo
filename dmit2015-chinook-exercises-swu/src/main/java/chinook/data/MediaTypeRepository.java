package chinook.data;

import chinook.model.MediaType;

public class MediaTypeRepository extends AbstractJpaRepository<MediaType> {
	private static final long serialVersionUID = 1L;

	public MediaTypeRepository() {
		super(MediaType.class);
	}
}
