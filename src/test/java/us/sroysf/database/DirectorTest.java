package us.sroysf.database;

import us.sroysf.db.entity.Director;
import us.sroysf.db.mappers.DirectorMapper;
import us.sroysf.db.mybatis3.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DirectorTest {

    @Test
    public void testDirectors() {
        try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession()) {

            DirectorMapper directorMapper = sqlSession.getMapper(DirectorMapper.class);

            Director jamesCameron = new Director();
            jamesCameron.setName("James Cameron");
            directorMapper.insertDirector(jamesCameron);

            Director ridleyScott = new Director();
            ridleyScott.setName("Ridley Scott");
            directorMapper.insertDirector(ridleyScott);

            // Confirm find by id
            Director foundDirector = directorMapper.findDirectorById(jamesCameron.getId());
            assertEquals(foundDirector.getName(), jamesCameron.getName());

            List<Director> allDirectors = directorMapper.findAllDirectors();
            assertEquals(2, allDirectors.size());
            Set<String> directorNames = allDirectors.stream().map(Director::getName).collect(Collectors.toSet());
            assertTrue(directorNames.contains(jamesCameron.getName()));
            assertTrue(directorNames.contains(ridleyScott.getName()));
        } catch (Exception ex) {
            throw ex;
        }
    }

    // Demonstrates that we can do duplicate inserts without harm using postgres upsert functionality
    @Test
    public void testUpsert() {
        try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession()) {

            DirectorMapper directorMapper = sqlSession.getMapper(DirectorMapper.class);

            Director jamesCameron = new Director();
            jamesCameron.setName("James Cameron");
            directorMapper.insertDirector(jamesCameron);

            // Now do it again a few times
            directorMapper.insertDirector(jamesCameron);
            directorMapper.insertDirector(jamesCameron);

            List<Director> allDirectors = directorMapper.findAllDirectors();
            assertEquals(1, allDirectors.size());
            assertEquals(jamesCameron.getName(), allDirectors.get(0).getName());
        } catch (Exception ex) {
            throw ex;
        }
    }
}
