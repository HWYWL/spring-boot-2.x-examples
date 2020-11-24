package com.yi.ehcach;

import com.yi.ehcach.model.Book;
import com.yi.ehcach.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootEhcachApplicationTests {
    @Autowired
    BookService bookService;

    @Test
    public void test() {
        Book book = new Book(1, "三国演义", "罗贯中");
        bookService.addBook(book);

        List<Book> books = bookService.findAll();
        System.out.println(books);

        book.setName("三国演义AAA");
        bookService.updateById(book);

        Book book1 = bookService.selectById(1);
        System.out.println(book1);
    }

}
