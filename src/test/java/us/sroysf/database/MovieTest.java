package us.sroysf.database;

import us.sroysf.db.entity.Director;
import us.sroysf.db.entity.Movie;
import us.sroysf.db.mappers.DirectorMapper;
import us.sroysf.db.mappers.MovieMapper;
import us.sroysf.db.mybatis3.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MovieTest {

    @Test
    public void testMovies() {
        try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession()) {

            MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);
            DirectorMapper directorMapper = sqlSession.getMapper(DirectorMapper.class);

            Director jamesCameron = new Director();
            jamesCameron.setName("James Cameron");
            directorMapper.insertDirector(jamesCameron);

            Movie terminator = new Movie();
            terminator.setTitle("Terminator");
            terminator.setReleaseYear(1984);
            terminator.setDirector(jamesCameron);
            movieMapper.insertMovie(terminator);

            Movie avatar = new Movie();
            avatar.setTitle("Avatar");
            avatar.setReleaseYear(2009);
            avatar.setDirector(jamesCameron);
            movieMapper.insertMovie(avatar);

            // Confirm find by id
            Movie foundMovie = movieMapper.findMovieById(terminator.getId());
            assertEquals(foundMovie.getTitle(), terminator.getTitle());
            assertEquals(foundMovie.getDirector().getName(), terminator.getDirector().getName());
            assertEquals(foundMovie.getDirector().getId(), terminator.getDirector().getId());

            List<Movie> allMovies = movieMapper.findAllMovies();
            assertEquals(2, allMovies.size());
            Set<String> directorNames = allMovies.stream().map(Movie::getTitle).collect(Collectors.toSet());
            assertTrue(directorNames.contains(terminator.getTitle()));
            assertTrue(directorNames.contains(avatar.getTitle()));
        } catch (Exception ex) {
            throw ex;
        }
    }
}
