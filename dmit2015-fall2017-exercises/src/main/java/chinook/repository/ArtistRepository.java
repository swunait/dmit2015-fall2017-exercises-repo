package chinook.repository;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import chinook.entity.Artist;

@Stateless
@LocalBean
public class ArtistRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Artist save(Artist currentArtist) {
		return entityManager.merge(currentArtist);
	}
	
	public Artist findOne(int artistId) {
		return entityManager.find(Artist.class, artistId);
	}
	
	public void delete(int artistId) {
		Artist currentArtist = findOne(artistId);
		delete(currentArtist);
	}
	
	public void delete(Artist currentArtist) {
		entityManager.remove(currentArtist);
	}
	
	public List<Artist> findAllOrderByName() {
		return entityManager.createQuery(
			"SELECT a FROM Artist a ORDER BY a.name",
			Artist.class).getResultList();
	}
	
}
