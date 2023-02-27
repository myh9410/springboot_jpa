package com.springboot.board.repository.post;

import com.springboot.board.entity.Posts;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Posts, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from posts p where p.no = :id")
    @QueryHints({
        @QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")
    })
    Posts findByIdForUpdate(@Param("id") long id);

}
