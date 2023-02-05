package ua.prof.hw5.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.prof.hw5.models.Book;
import ua.prof.hw5.models.Genre;
import ua.prof.hw5.repo.BookRepository;
import ua.prof.hw5.repo.GenreRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/")
    public String main(Model model) {


        return "main";
    }

    @GetMapping("/addbook")
    public String addBook(Model model) {
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "addBook";
    }
    @GetMapping("/addgenre")
    public String addGenre(Model model) {
        return "addGenre";
    }
    @GetMapping("/showbooks")
    public String showBooks(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "showBooks";
    }
    @GetMapping("/showgenres")
    public String showGenres(Model model) {
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "showGenres";
    }

    @PostMapping("/addbook")
    public String addBookPost(@RequestParam String name, @RequestParam String author,@RequestParam float price, @RequestParam int genre, Model model){
        Book book = new Book(name, author, price, genre);
        bookRepository.save(book);
        return "redirect:/";
    }

    @PostMapping("/addgenre")
    public String addGenrePost(@RequestParam String name, @RequestParam String description, Model model){
        Genre genre = new Genre(name, description);
        genreRepository.save(genre);
        return "redirect:/";
    }

    @GetMapping("/showbooks/{bookId}/edit")
    public String editBook(@PathVariable(value = "bookId")Integer id, Model model) {
        if(!bookRepository.existsById(id)){
            return "redirect:/";
        }
        Optional<Book> book = bookRepository.findById(id);
        ArrayList<Book> res = new ArrayList<>();
        book.ifPresent(res::add);
        model.addAttribute("book", res);

        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "bookEdit";
    }

    @PostMapping("/showbooks/{bookId}/edit")
    public String SaveBook(@PathVariable(value = "bookId")Integer id, @RequestParam String name, @RequestParam String author,@RequestParam float price, @RequestParam int genre, Model model) {
        Book book = bookRepository.findById(id).orElseThrow();
        book.setAuthor(author);
        book.setName(name);
        book.setPrice(price);
        book.setGenre_id(genre);
        bookRepository.save(book);
        return "redirect:/showbooks";
    }

    @PostMapping("/showbooks/{bookId}/remove")
    public String RemoveBook(@PathVariable(value = "bookId")Integer id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(book);
        return "redirect:/showbooks";
    }

}
