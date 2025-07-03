package com.transaction.repository;

import com.transaction.entity.SenderAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SenderRepo extends JpaRepository<SenderAccount, Long> {
    Optional<SenderAccount> findByName(String name);
}
