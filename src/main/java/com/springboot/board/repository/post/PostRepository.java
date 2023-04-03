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

    @Query("select p from posts p where p.no = :id")
    Posts findByIdForUpdate(@Param("id") long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from posts p where p.no = :id")
    @QueryHints({
        @QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")
    })
    Posts findByIdWithPessimisticLock(@Param("id") long id);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select p from posts p where p.no = :id")
    Posts findByIdWithOptimisticLock(@Param("id") long id);

    @Query(value = "select get_lock(:key, 1000)", nativeQuery = true)
    int getLock(long key);

    @Query(value = "select release_lock(:key)", nativeQuery = true)
    int releaseLock(long key);

}
