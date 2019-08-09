package us.sroysf.db.mappers;

import us.sroysf.db.entity.Movie;

import java.util.List;

public interface MovieMapper
{
    List<Movie> findAllMovies();
    Movie findMovieById(Integer id);
    void insertMovie(Movie movie);
}