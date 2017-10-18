package chinook.data;

import java.util.List;

import chinook.model.Genre;
import chinook.report.GenreSales;

public class GenreRepository extends AbstractJpaRepository<Genre> {
	private static final long serialVersionUID = 1L;

	public GenreRepository() {
		super(Genre.class);
	}
	
	public List<GenreSales> findGenreSales() {
		return getEntityManager().createQuery(
"SELECT new chinook.report.GenreSales(g.name, SUM(il.unitPrice * il.quantity) ) " 
	+ " FROM InvoiceLine il, IN (il.track) t, IN (t.genre) g "
	+ " GROUP BY g.name "
	+ " ORDER BY g.name ", 
			GenreSales.class)
			.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
