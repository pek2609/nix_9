package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entityDto.GroupResultSet;


@Repository
public interface GroupRepository extends BaseRepository<Group> {

    @Query(value = "select new ua.com.alevel.persistence.entityDto.GroupResultSet(g, g.students.size) from Group g")
    Page<GroupResultSet> findAllGroupsWithCount(Pageable pageable);

    Page<Group> findByStudentsId(Long studentId, Pageable pageable);

}
