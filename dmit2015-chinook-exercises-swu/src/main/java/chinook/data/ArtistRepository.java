package chinook.data;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import chinook.model.Artist;

@Stateless
@LocalBean
public class ArtistRepository {

	@Inject
	private EntityManager em;
	
	public List<Artist> findAll() {
		return em.createQuery("SELECT a FROM Artist a", Artist.class).getResultList();
	}
	
}
