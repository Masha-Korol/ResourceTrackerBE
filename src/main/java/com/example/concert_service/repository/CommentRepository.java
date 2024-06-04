package com.example.concert_service.repository;

import com.example.concert_service.data.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {
}
