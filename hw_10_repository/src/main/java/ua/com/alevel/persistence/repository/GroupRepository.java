package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Group;


@Repository
public interface GroupRepository extends BaseRepository<Group> {

    Page<Group> findByStudentsId(Long studentId, Pageable pageable);
}
