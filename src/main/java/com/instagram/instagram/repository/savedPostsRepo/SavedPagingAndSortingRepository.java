package com.instagram.instagram.repository.savedPostsRepo;

import com.instagram.instagram.domains.Saved;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SavedPagingAndSortingRepository extends PagingAndSortingRepository<Saved, Long> {
    @Query("select s from Saved s where s.user.authUserId=:userId")
    Page<Saved> findAllByUsername(Pageable pageable,Long userId);

}