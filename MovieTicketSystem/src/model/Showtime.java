package model;

public class Showtime {
    private String id;
    private Movie movie;
    private String date;
    private String time;
    private String hallType;
    private int remainingSeats;

    public Showtime(String id, Movie movie, String date, String time, String hallType, int remainingSeats) {
        this.id = id;
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.hallType = hallType;
        this.remainingSeats = remainingSeats;
    }

    // 補上無 ID 的建構子（AdminPanel 用）
    public Showtime(Movie movie, String date, String time, String hallType, int remainingSeats) {
        this.id = generateId(movie, date, time);
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.hallType = hallType;
        this.remainingSeats = remainingSeats;
    }

    private String generateId(Movie movie, String date, String time) {
        return movie.getTitleZh().hashCode() + "_" + date + "_" + time;
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getHallType() {
        return hallType;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }
}
