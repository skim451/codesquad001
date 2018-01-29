package com.woowahan.codesquad001.repository;

import com.woowahan.codesquad001.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserId(String userId);
}
