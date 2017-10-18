package chinook.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import chinook.data.GenreRepository;
import chinook.model.Genre;
import chinook.report.GenreSales;

@Model
public class GenreController {

	@Inject
	private GenreRepository genreRepository;
	
	private List<Genre> genres;
	
	@PostConstruct
	void init() {
		genres = genreRepository.findAll();
	}

	public List<Genre> getGenres() {
		return genres;
	}
	
	public List<GenreSales> retreiveGenreSales() {
		return genreRepository.findGenreSales();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
