package chinook.controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.el.functions.Numbers;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import chinook.model.InvoiceLine;
import chinook.model.Track;
import chinook.service.TrackService;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ShoppingCartController implements Serializable {

	private Set<InvoiceLine> items = new HashSet<>();	// +getter
	
	@Inject
	private TrackService trackService;
	
	public String addItem() {	
		String trackIdParam = Faces.getRequestParameter("trackId");
		if( trackIdParam != null && !trackIdParam.isEmpty() ) {
			int trackId = Integer.parseInt(trackIdParam);
			Track currentTrack = trackService.findOne(trackId);
			if( currentTrack != null ) {
				addItem(currentTrack);
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

	public Set<InvoiceLine> getItems() {
		return items;
	}
	
	public String getTotalPrice() {
		double totalPrice = 0;
		for(InvoiceLine item : items) {
			totalPrice += item.getQuantity() * item.getUnitPrice().doubleValue();
		}
		return Numbers.formatCurrency(totalPrice, "$");
	}
}
