package chinook.report;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GenreSales {

	private String genreName;		// +getter+setter
	private BigDecimal totalSales;	// +getter+setter
	
	public GenreSales(String genreName, BigDecimal totalSales) {
		super();
		this.genreName = genreName;
		this.totalSales = totalSales;
	}
	public GenreSales(String genreName, double totalSales) {
		super();
		this.genreName = genreName;
		this.totalSales = BigDecimal.valueOf(totalSales).setScale(2, RoundingMode.HALF_UP);
	}
	
	
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public BigDecimal getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}
}
