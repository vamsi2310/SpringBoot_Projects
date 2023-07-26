package com.vamsi.demo.repo;

import org.springframework.stereotype.Repository;
import com.vamsi.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;;;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
}
