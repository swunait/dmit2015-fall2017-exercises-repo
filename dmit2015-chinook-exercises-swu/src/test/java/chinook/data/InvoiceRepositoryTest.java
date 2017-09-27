package chinook.data;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import chinook.model.Invoice;
import chinook.util.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class InvoiceRepositoryTest {

	@Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
        		.addPackage("chinook.model")
                .addClasses(InvoiceRepository.class, AbstractJpaRepository.class, Resources.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml");
    }

	@Inject
	private InvoiceRepository invoiceRepository;
	
	@Test
	public void shouldFindAll() {
		List<Invoice> invoices = invoiceRepository.findAll();
		System.out.println("Found " + invoices.size() + " invoices.");
		assertEquals(412, invoices.size());
	}
}
