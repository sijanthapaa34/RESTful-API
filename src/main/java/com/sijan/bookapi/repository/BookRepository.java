package com.sijan.bookapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sijan.bookapi.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findByAuthorId(Long authorId);
	
}
