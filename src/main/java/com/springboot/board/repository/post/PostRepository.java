package com.springboot.board.repository.post;

import com.springboot.board.entity.Posts;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Posts, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Posts p where p.no = :id")
    Posts findByIdForUpdate(@Param("id") long id);

}
