package chinook.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import chinook.model.Genre;
import chinook.service.GenreService;

@Path("webapi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChinookWebAPI {
	
	@Inject
	private GenreService genreService;
	
	@Path("genres")
	@GET
	public List<Genre> findAllGenre() {
		return genreService.findAll();
	}
	
	@Path("genres/{id}")
	@GET
	public Genre findOneGenre(@PathParam("id") int genreId) {
		return genreService.findOne(genreId);
	}

	@Path("genres")
	@POST
	public void createGenre(Genre newGenre) {
		genreService.createGenre(newGenre);
	}
	
	@Path("genres")
	@PUT
	public void updateGenre(Genre currentGenre) {
		genreService.updateGenre(currentGenre);
	}
	
	@Path("genres/{id}")
	public void deleteGenre(@PathParam("id") int genreId) {
		genreService.deleteGenre(genreId);
	}

}
