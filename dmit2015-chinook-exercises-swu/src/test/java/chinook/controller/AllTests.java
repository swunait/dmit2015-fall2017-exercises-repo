package chinook.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AlbumControllerTest.class, ArtistControllerTest.class, CustomerControllerTest.class,
		EmployeeControllerTest.class, GenreControllerTest.class, InvoiceControllerTest.class,
		PlaylistControllerTest.class, TrackControllerTest.class })
public class AllTests {

}
