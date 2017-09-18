package chinook.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import chinook.entity.Artist;

@RunWith(Arquillian.class)
public class ArtistRepositoryTest {
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class,
				"chinook-persistence.jar")
				.addPackage(Artist.class.getPackage())
				.addPackage(ArtistRepository.class.getName())
				.addAsManifestResource(
						"/META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE,
						"beans.xml");
	}
	
	@EJB
	private ArtistRepository artistRepo;

	@Test
	public void should_have_Nrecords() {
		List<Artist> artists = artistRepo.findAllOrderByName();
		assertEquals(255, artists.size());
	}

}
