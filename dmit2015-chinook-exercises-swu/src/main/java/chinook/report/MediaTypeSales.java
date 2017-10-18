package chinook.report;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MediaTypeSales {

	private String mediaTypeName;	// +getter+setter
	private BigDecimal totalSales;	// +getter+setter
	
	public MediaTypeSales(String mediaTypeName, BigDecimal totalSales) {
		super();
		this.mediaTypeName = mediaTypeName;
		this.totalSales = totalSales;
	}
	
	public MediaTypeSales(String mediaTypeName, double totalSales) {
		super();
		this.mediaTypeName = mediaTypeName;
		this.totalSales = BigDecimal.valueOf(totalSales).setScale(2, RoundingMode.HALF_UP);
	}
	
	
	public String getMediaTypeName() {
		return mediaTypeName;
	}
	public void setMediaTypeName(String mediaTypeName) {
		this.mediaTypeName = mediaTypeName;
	}
	public BigDecimal getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}
	
}
