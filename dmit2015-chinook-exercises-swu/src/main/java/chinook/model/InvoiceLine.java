package chinook.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the InvoiceLine database table.
 * 
 */
@Entity
@NamedQuery(name="InvoiceLine.findAll", query="SELECT i FROM InvoiceLine i")
public class InvoiceLine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="InvoiceLineId")
	private int invoiceLineId;

	@Column(name="Quantity")
	private int quantity;

	@Column(name="UnitPrice")
	private BigDecimal unitPrice;

	//bi-directional many-to-one association to Invoice
	@ManyToOne
	@JoinColumn(name="InvoiceId")
	private Invoice invoice;

	//bi-directional many-to-one association to Track
	@ManyToOne
	@JoinColumn(name="TrackId")
	private Track track;

	public InvoiceLine() {
	}

	public int getInvoiceLineId() {
		return this.invoiceLineId;
	}

	public void setInvoiceLineId(int invoiceLineId) {
		this.invoiceLineId = invoiceLineId;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Invoice getInvoice() {
		return this.invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Track getTrack() {
		return this.track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	@Override
	public String toString() {
		return "\nInvoiceLine [invoiceLineId=" + invoiceLineId + ", quantity=" + quantity + ", unitPrice=" + unitPrice
				+ ", invoice=" + invoice + ", track=" + track + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + track.getTrackId();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvoiceLine other = (InvoiceLine) obj;
		if (track.getTrackId() != other.track.getTrackId())
			return false;
		return true;
	}

}