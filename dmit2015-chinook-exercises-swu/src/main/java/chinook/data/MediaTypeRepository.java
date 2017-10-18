package chinook.data;

import java.util.List;

import chinook.model.MediaType;
import chinook.report.MediaTypeSales;

public class MediaTypeRepository extends AbstractJpaRepository<MediaType> {
	private static final long serialVersionUID = 1L;

	public MediaTypeRepository() {
		super(MediaType.class);
	}
	
	public List<MediaTypeSales> findMediaTypeSales() {
		return getEntityManager().createQuery(
"SELECT new chinook.report.MediaTypeSales( m.name, SUM(il.unitPrice * il.quantity) As TotalSales ) "
	+ " FROM InvoiceLine il, IN (il.track) t, IN (t.mediaType) m "
	+ " GROUP BY m.name "
	+ " ORDER BY m.name ", 
			MediaTypeSales.class)
			.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
