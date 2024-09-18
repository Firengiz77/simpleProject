package com.bookstore.simpleblog.repository;

import com.bookstore.simpleblog.dto.BlogDto;
import com.bookstore.simpleblog.dto.response.BlogResponse;
import com.bookstore.simpleblog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query(value = "select id,description,title from blog where description like %?1%",nativeQuery = true)
    List<BlogResponse> findByDescriptionLike(String description);

}
