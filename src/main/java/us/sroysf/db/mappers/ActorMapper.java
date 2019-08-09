package us.sroysf.db.mappers;

import us.sroysf.db.entity.Actor;

import java.util.List;

public interface ActorMapper
{
    List<Actor> findAllActors();
    Actor findActorById(Integer id);
    void insertActor(Actor movie);
}