package com.handong.finance.domain.userform;

import com.handong.finance.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserFormRepository extends JpaRepository<UserForm, Long> {
    Optional<UserForm> findTop1ByUserOrderByCreatedAtDesc(User user);
}
