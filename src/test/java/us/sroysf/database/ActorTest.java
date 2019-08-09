package us.sroysf.database;

import us.sroysf.db.entity.Actor;
import us.sroysf.db.mappers.ActorMapper;
import us.sroysf.db.mybatis3.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ActorTest {

    @Test
    public void testActors() {
        try (SqlSession sqlSession = MyBatisSqlSessionFactory.openSession()) {

            ActorMapper actorMapper = sqlSession.getMapper(ActorMapper.class);

            Actor arnold = new Actor();
            arnold.setName("Arnold Schwarzenegger");
            actorMapper.insertActor(arnold);

            Actor sweaver = new Actor();
            sweaver.setName("Sigourney Weaver");
            actorMapper.insertActor(sweaver);

            // Confirm find by id
            Actor foundActor = actorMapper.findActorById(arnold.getId());
            assertEquals(foundActor.getName(), arnold.getName());

            List<Actor> allActors = actorMapper.findAllActors();
            assertEquals(2, allActors.size());
            Set<String> directorNames = allActors.stream().map(Actor::getName).collect(Collectors.toSet());
            assertTrue(directorNames.contains(arnold.getName()));
            assertTrue(directorNames.contains(sweaver.getName()));
        } catch (Exception ex) {
            throw ex;
        }
    }
}
