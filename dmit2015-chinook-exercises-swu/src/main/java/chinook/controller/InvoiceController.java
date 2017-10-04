package chinook.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import chinook.data.InvoiceRepository;
import chinook.model.Invoice;

@Model
public class InvoiceController {
	
	private int currentSelectedInvoiceId;		// getter/setter
	private Invoice currentSelectedInvoice;		// getter
	
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

	public int getCurrentSelectedInvoiceId() {
		return currentSelectedInvoiceId;
	}

	public void setCurrentSelectedInvoiceId(int currentSelectedInvoiceId) {
		this.currentSelectedInvoiceId = currentSelectedInvoiceId;
	}

	public Invoice getCurrentSelectedInvoice() {
		return currentSelectedInvoice;
	}
	
}
