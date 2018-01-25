package com.woowahan.codesquad001;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserId(String userId);
}
