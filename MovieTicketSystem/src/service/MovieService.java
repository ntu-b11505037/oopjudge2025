// File: src/service/MovieService.java
package service;

import model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private List<Movie> movies = new ArrayList<>();
    private MemberService memberService;

    public MovieService() {
        // 可選擇於初始化時注入 MemberService，或另外提供 setter
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    // 設定與取得 MemberService
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public MemberService getMemberService() {
        return memberService;
    }
}
