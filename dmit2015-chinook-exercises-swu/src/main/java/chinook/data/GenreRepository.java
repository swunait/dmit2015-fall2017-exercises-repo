package chinook.data;

import chinook.model.Genre;

public class GenreRepository extends AbstractJpaRepository<Genre> {
	private static final long serialVersionUID = 1L;

	public GenreRepository() {
		super(Genre.class);
	}
}
