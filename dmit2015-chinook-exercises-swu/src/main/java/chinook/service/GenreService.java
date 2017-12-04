package chinook.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import chinook.data.GenreRepository;
import chinook.model.Genre;

@Stateless
public class GenreService {

	@Inject
	private Logger log;
	
	@Inject
	private GenreRepository genreRepository;
	
	public List<Genre> findAll() {
		return genreRepository.findAll();
	}
	
	public Genre findOne(int genreId) {
		Genre currentGenre = null;
		try {
			currentGenre = genreRepository.find(genreId);
		} catch(NoResultException e) {
			log.fine(e.getMessage());
		}
		return currentGenre;
	}
	
	public void createGenre(Genre newGenre) {
		genreRepository.persist(newGenre);
	}
	
	public Genre updateGenre(Genre currentGenre) {
		return genreRepository.edit(currentGenre);
	}
	
	public void deleteGenre(int genreId) {
		Genre currentGenre = findOne(genreId);
		if (currentGenre != null) {
			genreRepository.remove(currentGenre);
		}
	}
}
