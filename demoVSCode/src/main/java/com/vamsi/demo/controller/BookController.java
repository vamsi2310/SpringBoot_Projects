package com.vamsi.demo.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
// import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.Id;

import org.apache.catalina.connector.Response;
import org.hibernate.mapping.Map;
// import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.vamsi.demo.model.Book;
import com.vamsi.demo.repo.BookRepo;

@RestController
public class BookController {
    @Autowired
    private BookRepo bookRepo;

    @GetMapping("/getAllBooks")
    
    public ResponseEntity<List<Book>> getAllBooks(){
            List<Book> bookList = new ArrayList<>();
            bookRepo.findAll().forEach(bookList::add);   
            return (bookList.isEmpty())
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(bookList.stream().sorted(Comparator.comparing(Book::getId).reversed()).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/addBookGet")
    public ResponseEntity<Book> addBook(){
        Book book = new Book();
        book.setAuthor("Author1");
        book.setTitle("Book1");
        // book.setitle(book:"Book1");
        System.out.println(book);
        Book bookObj = bookRepo.save(book);
        return new ResponseEntity<>(bookObj,HttpStatus.OK);
    
    }

    @PostMapping("/addBookPost")
    public ResponseEntity<Book> addBook(@Validated @RequestBody Book book){
        // Book book = new Book();
        // book.setAuthor("Author1");
        // book.setTitle("Book1");
        // book.setitle(book:"Book1");
        System.out.println(book);
        Book bookObj = bookRepo.save(book);
        return new ResponseEntity<>(bookObj,HttpStatus.OK);
    
    }

    @PutMapping("updateBookByID/{id}")
    public ResponseEntity<Book> updateBookByID(@PathVariable(value = "id") Long Id, @Validated @RequestBody Book bookReq){
        System.out.println(bookReq);
        Book book = bookRepo.findById(Id).get();
        // .orElseThrow(() -> new ResourceNotFoundExc("Book not found for this id :: " + Id));
        book.setAuthor(bookReq.getAuthor());
        book.setTitle(bookReq.getTitle());

        
        final Book bookRes = bookRepo.save(book);
        return ResponseEntity.ok(bookRes);
        
    }

    
//     @DeleteMapping("/deleteBook/{id}")
//     public java.util.Map <String,Boolean> deleteBook(@PathVariable(value = "id") Long Id) {
//     Book employee = bookRepo.findById(Id).get();
//     //   .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

//     bookRepo.delete(employee);
//     ResponseEntity<Book> bookRes = 
//     return response;
// }

// @PatchMapping("/employees/{id}/{firstName}")
// public ResponseEntity<Employee> updateEmployeePartially(@PathVariable Long id, @PathVariable String firstName) {
// 	try {
// 		Employee employee = employeeRepository.findById(id).get();
// 		employee.setFirstName(firstName);
// 		return new ResponseEntity<Employee>(employeeRepository.save(employee), HttpStatus.OK);
// 	} catch (Exception e) {
// 		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
// 	}
// }


}
