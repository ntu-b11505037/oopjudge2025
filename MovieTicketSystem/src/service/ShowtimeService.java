package service;

import model.Movie;
import model.Showtime;

import java.util.*;

public class ShowtimeService {

    private Map<String, List<Showtime>> showtimesByMovie = new HashMap<>();
    private Map<String, Showtime> showtimeById = new HashMap<>();

    public ShowtimeService() {
    }

    public ShowtimeService(MovieService movieService) {
        preloadSampleShowtimes(movieService.getAllMovies());
    }

    public void addShowtime(Showtime showtime) {
        String movieTitle = showtime.getMovie().getTitleZh();
        showtimesByMovie.putIfAbsent(movieTitle, new ArrayList<>());
        showtimesByMovie.get(movieTitle).add(showtime);
        showtimeById.put(showtime.getId(), showtime);
    }

    public List<Showtime> getShowtimesByMovie(Movie movie) {
        return showtimesByMovie.getOrDefault(movie.getTitleZh(), new ArrayList<>());
    }

    public Showtime getShowtimeById(String showtimeId) {
        return showtimeById.get(showtimeId);
    }

    public List<Showtime> getAllShowtimes() {
        List<Showtime> all = new ArrayList<>();
        for (List<Showtime> list : showtimesByMovie.values()) {
            all.addAll(list);
        }
        return all;
    }

    private void preloadSampleShowtimes(List<Movie> movies) {
        int count = 0;
        for (Movie movie : movies) {
            String hallType = (count < 4) ? "large" : "small";
            addShowtime(new Showtime("S" + (count + 1) + "A", movie, "2025-06-15", "14:00", hallType, hallType.equals("large") ? 300 : 120));
            addShowtime(new Showtime("S" + (count + 1) + "B", movie, "2025-06-15", "19:00", hallType, hallType.equals("large") ? 300 : 120));
            count++;
        }
    }
}
