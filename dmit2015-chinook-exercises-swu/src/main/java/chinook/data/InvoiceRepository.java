package chinook.data;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import chinook.model.Invoice;

public class InvoiceRepository extends AbstractJpaRepository<Invoice> {
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;
	
	public InvoiceRepository() {
		super(Invoice.class);
	}
	
	public Invoice findOne(int invoiceId) {
		Invoice singleResult;
		
		try {
			singleResult = getEntityManager().createQuery(
"SELECT inv FROM Invoice inv JOIN FETCH inv.invoiceLines WHERE inv.invoiceId = :idValue", Invoice.class)
			.setParameter("idValue", invoiceId)	
			.getSingleResult();
		} catch(NoResultException nre) {
			singleResult = null;
			log.info(nre.getMessage());
		}
		
		return singleResult;
	}

	public List<Invoice> findAllByCustomerId(int customerId) {
		return getEntityManager().createQuery(
			"SELECT inv FROM Invoice inv WHERE inv.customer.customerId = :customerIdValue", 
			Invoice.class)
			.setParameter("customerIdValue", customerId)
			.getResultList();
	}
	
	public List<Invoice> findAllByDateRange(Date startDate, Date endDate) {
		return getEntityManager().createQuery(
			"SELECT i FROM Invoice i WHERE i.invoiceDate BETWEEN :startDateValue AND :endDateValue",
			Invoice.class)
			.setParameter("startDateValue", startDate)
			.setParameter("endDateValue", endDate)
			.getResultList();
	}
}
