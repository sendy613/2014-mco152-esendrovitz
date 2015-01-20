package sendrovitz.rottenTomatoes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class MoviesDownloadThread extends Thread {
	private RottenTomatoes frame;
	private JLabel imageLabel;
	
	
	public MoviesDownloadThread(RottenTomatoes frame, JLabel imageLabel){
	this.frame= frame;
	this.imageLabel = imageLabel;
	}
	
	@Override
	public void run(){
		//put everything in here that want to run on this thread and not main thread
		try{
		URL url = new URL("http://api.rottentomatoes.com/api/public/v1.0/lists/movies/upcoming.json?apikey=n5py79naeuyemb9xc4xbx28s&page_limit=3");
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();
		String json = IOUtils.toString(in);
		Gson gson = new Gson();
		// want to change string from json and give us back a class.
		Instance movie = gson.fromJson(json, Instance.class); 
		frame.displayFrame(movie);
		Movies[] movies = movie.getMovies();
		ImageDownloadThread thread = new ImageDownloadThread(movies[2].getPosters().getThumbnail(), imageLabel);
		thread.start();
		}
		catch(IOException e){// object
			e.getMessage();
		}
	}
}

//


