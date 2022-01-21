package ua.com.alevel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Student;

@Repository
public interface StudentRepository extends BaseRepository<Student> {

    Page<Student> findByGroupsId(Long groupId, Pageable pageable);

}
