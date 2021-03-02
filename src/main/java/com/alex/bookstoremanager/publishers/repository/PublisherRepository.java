package com.alex.bookstoremanager.publishers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.bookstoremanager.publishers.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long>{
	
	Optional<Publisher> findByNameOrCode(String name, String code);

}
