package com.alex.bookstoremanager.publishers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.bookstoremanager.publishers.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long>{

}
