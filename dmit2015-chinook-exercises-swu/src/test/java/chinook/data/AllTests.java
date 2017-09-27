package chinook.data;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AlbumRepositoryTest.class, ArtistRepositoryTest.class, CustomerRepositoryTest.class,
		EmployeeRepositoryTest.class, GenreRepositoryTest.class, 
		InvoiceRepositoryTest.class, MediaTypeRepositoryTest.class, PlaylistRepositoryTest.class,
		TrackRepositoryTest.class })
public class AllTests {

}
