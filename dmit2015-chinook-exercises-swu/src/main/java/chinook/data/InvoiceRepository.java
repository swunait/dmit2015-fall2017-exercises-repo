package chinook.data;

import chinook.model.Invoice;

public class InvoiceRepository extends AbstractJpaRepository<Invoice> {
	private static final long serialVersionUID = 1L;

	public InvoiceRepository() {
		super(Invoice.class);
	}

}
