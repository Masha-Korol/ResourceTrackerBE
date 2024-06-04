package com.example.resource_tracker.repository;

import com.example.resource_tracker.data.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Integer> {
}
