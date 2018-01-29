package com.woowahan.codesquad001.repository;

import com.woowahan.codesquad001.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
