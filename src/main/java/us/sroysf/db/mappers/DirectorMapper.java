package us.sroysf.db.mappers;

import us.sroysf.db.entity.Director;

import java.util.List;

public interface DirectorMapper
{
    List<Director> findAllDirectors();
    Director findDirectorById(Integer id);
    void insertDirector(Director director);
}