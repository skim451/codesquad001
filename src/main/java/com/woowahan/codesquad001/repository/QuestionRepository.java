package com.woowahan.codesquad001.repository;

import com.woowahan.codesquad001.entity.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
