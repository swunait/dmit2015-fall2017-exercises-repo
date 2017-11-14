package chinook.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the Track database table.
 * 
 */
@Entity
@NamedQuery(name="Track.findAll", query="SELECT t FROM Track t")
public class Track implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TrackId")
	private int trackId;

	@Column(name="Bytes")
	private int bytes;

	@Column(name="Composer")
	private String composer;

	@Column(name="Milliseconds")
	private int milliseconds;

	@NotBlank(message="Track Name field value is required")
	@Column(name="Name")
	private String name;

	@NotNull(message="Unit Price is required")
	@DecimalMin(value="0.00", message="Unit Price field valu cannot be a negative number")
	@Column(name="UnitPrice")
	private BigDecimal unitPrice;

	//bi-directional many-to-one association to InvoiceLine
	@OneToMany(mappedBy="track")
	private List<InvoiceLine> invoiceLines;

	//bi-directional many-to-one association to Album
	@ManyToOne
	@JoinColumn(name="AlbumId")
	private Album album;

	//bi-directional many-to-one association to Genre
	@ManyToOne
	@JoinColumn(name="GenreId")
	private Genre genre;

	//bi-directional many-to-one association to MediaType
	@ManyToOne
	@JoinColumn(name="MediaTypeId")
	private MediaType mediaType;

	//bi-directional many-to-many association to Playlist
	@ManyToMany
	@JoinTable(
		name="PlaylistTrack"
		, joinColumns={
			@JoinColumn(name="TrackId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="PlaylistId")
			}
		)
	private List<Playlist> playlists;

	public Track() {
	}

	public int getTrackId() {
		return this.trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public int getBytes() {
		return this.bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public String getComposer() {
		return this.composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public int getMilliseconds() {
		return this.milliseconds;
	}

	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public List<InvoiceLine> getInvoiceLines() {
		return this.invoiceLines;
	}

	public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}

	public InvoiceLine addInvoiceLine(InvoiceLine invoiceLine) {
		getInvoiceLines().add(invoiceLine);
		invoiceLine.setTrack(this);

		return invoiceLine;
	}

	public InvoiceLine removeInvoiceLine(InvoiceLine invoiceLine) {
		getInvoiceLines().remove(invoiceLine);
		invoiceLine.setTrack(null);

		return invoiceLine;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public MediaType getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public List<Playlist> getPlaylists() {
		return this.playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	@Override
	public String toString() {
		return "Track [trackId=" + trackId + ", bytes=" + bytes + ", composer=" + composer + ", milliseconds="
				+ milliseconds + ", name=" + name + ", unitPrice=" + unitPrice + ", invoiceLines=" + invoiceLines
				+ ", album=" + album + ", genre=" + genre + ", mediaType=" + mediaType + ", playlists=" + playlists
				+ "]";
	}

}