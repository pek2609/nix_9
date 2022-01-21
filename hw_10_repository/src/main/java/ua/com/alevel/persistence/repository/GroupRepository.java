package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Group;

import java.util.List;
import java.util.Set;


@Repository
public interface GroupRepository extends BaseRepository<Group> {

    @Query(value = "select g from Group g")
    Page<Group> findAllGroupsWithCount(Pageable pageable);

    @Query(value = "select g from Group g join g.students s where s.id=:id")
    Page<Group> findByStudentsId(@Param("id") Long studentId, Pageable pageable);

    List<Group> findByStudentsId(Long studentId);

    @Query(value = "select g from Group g where not exists (select s from g.students s where s.id =:id)")
    Set<Group> findByStudentsIdNot(@Param("id") Long studentId);

}
