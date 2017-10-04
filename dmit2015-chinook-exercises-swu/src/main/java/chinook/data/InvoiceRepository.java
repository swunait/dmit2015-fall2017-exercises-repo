package chinook.data;

import chinook.model.Invoice;

public class InvoiceRepository extends AbstractJpaRepository<Invoice> {
	private static final long serialVersionUID = 1L;

	public InvoiceRepository() {
		super(Invoice.class);
	}
	
	public Invoice findOne(int invoiceId) {
		return getEntityManager().createQuery(
"SELECT inv FROM Invoice inv JOIN FETCH inv.invoiceLines WHERE inv.invoiceId = :idValue", Invoice.class)
			.setParameter("idValue", invoiceId)	
			.getSingleResult();
	}

}
