package chinook.controller;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import chinook.data.AbstractJpaRepository;
import chinook.data.EmployeeRepository;
import chinook.util.Resources;

@RunWith(Arquillian.class)
public class EmployeeControllerTest {

	@Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
        		.addPackage("chinook.model")
                .addClasses(EmployeeController.class, EmployeeRepository.class, AbstractJpaRepository.class, Resources.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml");
    }
	
	@Inject
	private EmployeeController artistController;
	
	@Test
	public void shouldFindAllEmployees() {
		assertEquals(8, artistController.getEmployees().size());
	}

}
