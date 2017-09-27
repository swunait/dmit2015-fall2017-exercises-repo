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
import chinook.data.AlbumRepository;
import chinook.util.Resources;

@RunWith(Arquillian.class)
public class AlbumControllerTest {

	@Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
        		.addPackage("chinook.model")
                .addClasses(AlbumController.class, AlbumRepository.class, AbstractJpaRepository.class, Resources.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                // Deploy our test datasource
                .addAsWebInfResource("test-ds.xml");
    }
	
	@Inject
	private AlbumController albumController;
	
	@Test
	public void shouldFindAllAlbums() {
		assertEquals(347, albumController.getAlbums().size());
	}

}
