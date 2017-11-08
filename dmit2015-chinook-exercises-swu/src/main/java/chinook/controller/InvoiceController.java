package chinook.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.omnifaces.util.Messages;

import chinook.data.InvoiceRepository;
import chinook.model.Invoice;

@Model
public class InvoiceController {
	
	private Integer currentSelectedCustomerId;	// +getter +setter
	private List<Invoice> invoicesByCustomer;	// +getter
	
	@NotNull(message="InvoiceId field value is required")
	private Integer currentSelectedInvoiceId;		// getter/setter
	private Invoice currentSelectedInvoice;			// getter
	
	public void findInvoice() {
		if( !FacesContext.getCurrentInstance().isPostback() ) {
			if( currentSelectedInvoiceId > 0 ) {
				currentSelectedInvoice = invoiceRepository.findOne(currentSelectedInvoiceId);
				if( currentSelectedInvoice == null ) {
					Messages.addGlobalInfo("There is no invoice with invoiceID {0}", 
							currentSelectedInvoiceId);					
				}
			} else {
				Messages.addGlobalError("Bad request. Invalid invoiceID {0}", currentSelectedInvoiceId);
			}
		}
	}

	public void findOneInvoice() {
		currentSelectedInvoice = invoiceRepository.findOne(currentSelectedInvoiceId);
		if( currentSelectedInvoice == null ) {
			Messages.addGlobalInfo("There is no invoice with invoiceID {0}", currentSelectedInvoiceId);					
		} else {
			Messages.addGlobalInfo("We found 1 result with invoiceID {0}", currentSelectedInvoiceId);								
		}
	}
	
	public void findAllInvoicesByCustomer() {
		invoicesByCustomer = invoiceRepository.findAllByCustomerId(currentSelectedCustomerId);
		currentSelectedInvoice = null;
		int resultCount = invoicesByCustomer.size();
		if (invoicesByCustomer.size() == 0) {
			Messages.addGlobalError("Unknown customerId \"{0}\". We found 0 results", currentSelectedCustomerId);
		} else {
			Messages.addGlobalInfo("We found {0} results.", resultCount);
		}
	}
	
	public void findOneInvoice(int invoiceId) {
		currentSelectedInvoiceId = invoiceId;
		findOneInvoice();
	}
	
	@Inject
	private InvoiceRepository invoiceRepository;
	
	private List<Invoice> invoices;
	
	@PostConstruct
	void init() {
		invoices = invoiceRepository.findAll();
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public Integer getCurrentSelectedInvoiceId() {
		return currentSelectedInvoiceId;
	}

	public void setCurrentSelectedInvoiceId(Integer currentSelectedInvoiceId) {
		this.currentSelectedInvoiceId = currentSelectedInvoiceId;
	}

	public Invoice getCurrentSelectedInvoice() {
		return currentSelectedInvoice;
	}

	public Integer getCurrentSelectedCustomerId() {
		return currentSelectedCustomerId;
	}

	public void setCurrentSelectedCustomerId(Integer currentSelectedCustomerId) {
		this.currentSelectedCustomerId = currentSelectedCustomerId;
	}

	public List<Invoice> getInvoicesByCustomer() {
		return invoicesByCustomer;
	}
	
	
}
