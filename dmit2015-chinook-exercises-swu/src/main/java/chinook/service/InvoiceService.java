package chinook.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import chinook.exception.IllegalQuantityException;
import chinook.exception.NoInvoiceLinesException;
import chinook.model.Customer;
import chinook.model.Invoice;
import chinook.model.InvoiceLine;

@Stateless
public class InvoiceService {
	
	@Resource
	private EJBContext context;
	
	@Inject
	private EntityManager entityManager;
	
	public int createInvoice(
			Customer invoiceCustomer,
			String billingAddress,
			String billingCity,
			String billingState,
			String billingCountry,
			String billingPostalCode,
			List<InvoiceLine> items
			) throws NoInvoiceLinesException, IllegalQuantityException  {
		int invoiceId = 0;
		
		if (items == null || items.size() == 0) {
			context.setRollbackOnly();
			throw new NoInvoiceLinesException("There are no items in the invoice");			
		}
		
		Invoice newInvoice = new Invoice();
		newInvoice.setCustomer(invoiceCustomer);
		newInvoice.setBillingAddress(billingAddress);
		newInvoice.setBillingCity(billingCity);
		newInvoice.setBillingState(billingState);
		newInvoice.setBillingCountry(billingCountry);
		newInvoice.setBillingPostalCode(billingPostalCode);
		
		// assign the invoiceDate and total
		Date today = Calendar.getInstance().getTime();
		newInvoice.setInvoiceDate( new Timestamp(today.getTime()) );
		// calculate the invoice total
		double total = 0;
		for(InvoiceLine singleItem : items) {
			total += singleItem.getQuantity() * singleItem.getUnitPrice().doubleValue();
		}
		// set the invoice total
		newInvoice.setTotal(BigDecimal.valueOf(total));
		// persist the invoice
		entityManager.persist(newInvoice);
		// get the system generated invoiceId 
		invoiceId = newInvoice.getInvoiceId();
		
		// iterate through each InvoiceLine and persist it
		for (InvoiceLine singleItem : items) {
			// rollback the transaction if quantity is less than one
			if (singleItem.getQuantity() < 1) {
				context.setRollbackOnly();
				throw new IllegalQuantityException("Invalid quantity ordered.");
			}
			// set the invoice of each InvoiceLine
			singleItem.setInvoice(newInvoice);
			// persist the InvoiceLine
			entityManager.persist(singleItem);
		}		
		
		return invoiceId;
	}
	
	public int createInvoice(Invoice newInvoice, List<InvoiceLine> items) 
			throws NoInvoiceLinesException, IllegalQuantityException {		
		int invoiceId = 0;
		
		if (items == null || items.size() == 0) {
			context.setRollbackOnly();
			throw new NoInvoiceLinesException("There are no items in the invoice");			
		}
		
		// assign the invoiceDate and total
		Date today = Calendar.getInstance().getTime();
		newInvoice.setInvoiceDate( new Timestamp(today.getTime()) );
		// calculate the invoice total
		double total = 0;
		for(InvoiceLine singleItem : items) {
			total += singleItem.getQuantity() * singleItem.getUnitPrice().doubleValue();
		}
		// set the invoice total
		newInvoice.setTotal(BigDecimal.valueOf(total));
		// persist the invoice
		entityManager.persist(newInvoice);
		// get the system generated invoiceId 
		invoiceId = newInvoice.getInvoiceId();
		
		// iterate through each InvoiceLine and persist it
		for (InvoiceLine singleItem : items) {
			// rollback the transaction if quantity is less than one
			if (singleItem.getQuantity() < 1) {
				context.setRollbackOnly();
				throw new IllegalQuantityException("Invalid quantity ordered.");
			}
			// set the invoice of each InvoiceLine
			singleItem.setInvoice(newInvoice);
			// persist the InvoiceLine
			entityManager.persist(singleItem);
		}		
		
		return invoiceId;
	}

}
