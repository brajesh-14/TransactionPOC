package com.transaction.repository;

import com.transaction.entity.ReceiverAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceiverRepo extends JpaRepository<ReceiverAccount, Long> {

    Optional<ReceiverAccount> findByName(String name);
}
