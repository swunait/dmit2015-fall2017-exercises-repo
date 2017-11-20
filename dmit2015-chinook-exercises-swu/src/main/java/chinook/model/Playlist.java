package chinook.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the Playlist database table.
 * 
 */
@Entity
@NamedQuery(name="Playlist.findAll", query="SELECT p FROM Playlist p")
public class Playlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PlaylistId")
	private int playlistId;

	@Column(name="Name")
	private String name;

	//bi-directional many-to-many association to Track
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JoinTable(name="PlaylistTrack",
			joinColumns=@JoinColumn(name="PlaylistId", referencedColumnName="PlaylistId"),
			inverseJoinColumns=@JoinColumn(name="TrackId", referencedColumnName="TrackId"))
	private Set<Track> tracks;

	public Playlist() {
	}

	public int getPlaylistId() {
		return this.playlistId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Track> getTracks() {
		return this.tracks;
	}

	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}

	@Override
	public String toString() {
		return "Playlist [playlistId=" + playlistId + ", name=" + name + ", tracks=" + tracks + "]";
	}

}