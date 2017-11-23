package chinook.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.el.functions.Numbers;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import chinook.data.CustomerRepository;
import chinook.exception.IllegalQuantityException;
import chinook.exception.NoInvoiceLinesException;
import chinook.model.Customer;
import chinook.model.Invoice;
import chinook.model.InvoiceLine;
import chinook.model.Track;
import chinook.service.InvoiceService;
import chinook.service.TrackService;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ShoppingCartController implements Serializable {

	private Set<InvoiceLine> items = new HashSet<>();	// +getter
	
	@Inject
	private TrackService trackService;
	
	@NotNull(message="TrackId value is required")
	private Integer currentTrackId;						// +getter +setter
	
	private Integer currentSelectedCustomerId;			// +getter +setter
	
	@Inject
	private CustomerRepository customerRepository;
	
	
	@Inject
	private InvoiceService invoiceService;
	
	public void addItemWithTrackId() {
		Track currentTrack = trackService.findOne(currentTrackId);
		if (currentTrack != null) {
			addItem(currentTrack);	
		} else {
			Messages.addGlobalError("Invalid trackId {0}", currentTrackId);
		}
	}
	
	public String addItemWithTrackIdQueryParameter() {	
		String trackIdParam = Faces.getRequestParameter("trackId");
		if( trackIdParam != null && !trackIdParam.isEmpty() ) {
			int trackId = Integer.parseInt(trackIdParam);
			Track currentTrack = trackService.findOne(trackId);
			if( currentTrack != null ) {
				addItem(currentTrack);
			} else {
				Messages.addGlobalError("Invalid trackId {0}", currentTrackId);
			}
		}
		return "/public/transaction/shoppingCart.xhtml?faces-redirect=true";
	}
	
	public String addItem(Track currentTrack) {
		InvoiceLine item = new InvoiceLine();
		item.setTrack(currentTrack);
		item.setQuantity(1);
		item.setUnitPrice( currentTrack.getUnitPrice() );
		// Add item to shopping cart
		if (!items.add(item)) {
			// Item is already in the shopping cart
			// Get existing item and increment quantity by 1
			InvoiceLine existingItem = items.stream().filter( 
					singleItem -> singleItem.getTrack().getTrackId() == currentTrack.getTrackId() )
					.findFirst().orElse(null);
			if (existingItem != null) {
				existingItem.setQuantity( existingItem.getQuantity() + 1);
				Messages.addFlashGlobalInfo("Item quantity was updated");
			}
		} else {
			Messages.addFlashGlobalInfo("Item was added to cart");
		}
		
		// return navigation to the page shoppingBag.xhtml
		return "/public/transaction/shoppingCart.xhtml?faces-redirect=true";
	}
	
	public void removeItem(InvoiceLine item) {
		items.remove(item);
	}
	
	public void emptyCart() {
		items.clear();
	}

	public void submitOrder() {
		try {
			int customerId = currentSelectedCustomerId;
			Customer invoiceCustomer = customerRepository.find(customerId);
			Invoice newInvoice = new Invoice();
			newInvoice.setCustomer(invoiceCustomer);
			newInvoice.setBillingAddress(invoiceCustomer.getAddress());
			newInvoice.setBillingCity(invoiceCustomer.getCity());
			newInvoice.setBillingCountry(invoiceCustomer.getCompany());
			newInvoice.setBillingPostalCode(invoiceCustomer.getPostalCode());
			newInvoice.setBillingState(invoiceCustomer.getState());
			
			int invoiceId = invoiceService.createInvoice(newInvoice, new ArrayList<>(items));
			Messages.addGlobalInfo("Successfully created invoice #{0}", invoiceId);

			// clear the customer selection
			currentSelectedCustomerId = null;			
			// empty the shopping cart
			items.clear();			
		} catch( NoInvoiceLinesException | IllegalQuantityException e ) {
			Messages.addGlobalError(e.getMessage());
		} catch( Exception e ) {
			Messages.addGlobalError("Create invoice was not successful");
		}
	}
	
	public Set<InvoiceLine> getItems() {
		return items;
	}
	
	
	public Integer getCurrentTrackId() {
		return currentTrackId;
	}

	public void setCurrentTrackId(Integer currentTrackId) {
		this.currentTrackId = currentTrackId;
	}

	public Integer getCurrentSelectedCustomerId() {
		return currentSelectedCustomerId;
	}

	public void setCurrentSelectedCustomerId(Integer currentSelectedCustomerId) {
		this.currentSelectedCustomerId = currentSelectedCustomerId;
	}

	public String getTotalPrice() {
		double totalPrice = 0;
		for(InvoiceLine item : items) {
			totalPrice += item.getQuantity() * item.getUnitPrice().doubleValue();
		}
		return Numbers.formatCurrency(totalPrice, "$");
	}
}
