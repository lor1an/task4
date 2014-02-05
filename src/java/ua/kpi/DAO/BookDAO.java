/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.DAO;

import ua.kpi.DAO.Interfaces.BookCRUD;
import ua.kpi.exceptions.DAOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ua.kpi.model.Author;
import ua.kpi.model.Book;
import ua.kpi.model.Genre;
import org.apache.log4j.Logger;

/**
 *
 * @author lor1an
 */
public class BookDAO extends BasicCRUD implements BookCRUD {

    private static final String ADD_BOOK = "insert into books(Title, Author, Genre, Stock) values (?,?,?,?);";
    private static final String GET_ALL_BOOKS = "select Id, Title, Author, Genre, Stock from books;";
    private static final String FIND_BOOK_BY_ID = "select Title, Author, Genre, Stock from books where Id=?";
    private static final String FIND_BOOKS_BY_GENRE = "select ID, Title, Author,Stock from books where Genre=?;";
    private static final String FIND_BOOKS_BY_AUTHOR = "select ID, Title, Genre,Stock from books where Author=?;";
    private static final String FIND_BOOKS_BY_TITLE = "select ID, Author,Genre, Stock from books where Title=?;";
    private static final String UPDATE_BOOK_STOCK = "update books set Stock=? where ID =?;";
    private static final String UPDATE_BOOK = "update books set Title=?, Author=?, Genre=?, Stock=? where ID=?;";
    private static final String DELETE_BOOK = "delete from books where id=?";

    BookDAO(Logger logger) {
        super(logger);
    }

    @Override
    public Book findById(int id) throws DAOException {
        Book book = null;
        try {
            super.makeConnection();
            Object[] args = {id};
            result = super.getItems(FIND_BOOK_BY_ID, args);
            AuthorDAO authordao = new AuthorDAO(super.logger);
            GenreDAO genredao = new GenreDAO(super.logger);

            if (result.next()) {
                book = new Book();
                book.setId(id);
                book.setTitle(result.getString("Title"));
                book.setStock(result.getInt("Stock"));
                book.setTitle(result.getString("Title"));
                Author author = authordao.findById(result.getInt("Author"));
                Genre genre = genredao.findById(result.getInt("Genre"));
                book.setAuthor(author);
                book.setGenre(genre);
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown");
            logger.error("Exception thrown!", ex);
        }
        return book;
    }

    @Override
    public boolean update(Book entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getTitle(), entity.getAuthor().getId(), entity.getGenre().getId(),
            entity.getStock(), entity.getId()};
        resultValue = super.executeQuery(UPDATE_BOOK, args);
        super.closeConnection();
        if (resultValue > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int save(Book entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getTitle(), entity.getAuthor().getId(),
            entity.getGenre().getId(), entity.getStock()};
        resultValue = super.executeQuery(ADD_BOOK, args);
        super.closeConnection();
        if (resultValue > 0) {
            return resultValue;
        } else {
            return 0;
        }
    }

    @Override
    public boolean delete(Book entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getId()};
        resultValue = super.executeQuery(DELETE_BOOK, args);
        if (resultValue > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Book> getAll() throws DAOException {
        List<Book> books = new ArrayList<>();
        try {
            super.makeConnection();
            Object args[] = {};
            result = super.getItems(GET_ALL_BOOKS, args);
            AuthorDAO authordao = new AuthorDAO(super.logger);
            GenreDAO genredao = new GenreDAO(super.logger);
            while (result.next()) {
                Book book = new Book();
                book.setId(result.getInt("Id"));
                book.setStock(result.getInt("Stock"));
                book.setTitle(result.getString("Title"));
                Author author = authordao.findById(result.getInt("Author"));
                Genre genre = genredao.findById(result.getInt("Genre"));
                book.setAuthor(author);
                book.setGenre(genre);
                books.add(book);
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown");
            logger.error("Exception thrown!", ex);
        }
        return books;
    }

    @Override
    public Book findByTitle(String title) throws DAOException {
        Book book = null;
        try {
            super.makeConnection();
            Object[] args = {title};
            System.out.println(title);
            result = super.getItems(FIND_BOOKS_BY_TITLE, args);
            AuthorDAO authordao = new AuthorDAO(super.logger);
            GenreDAO genredao = new GenreDAO(super.logger);

            if (result.next()) {
                book = new Book();
                book.setId(result.getInt("ID"));
                book.setTitle(title);
                book.setStock(result.getInt("Stock"));
                Author author = authordao.findById(result.getInt("Author"));
                Genre genre = genredao.findById(result.getInt("Genre"));
                book.setAuthor(author);
                book.setGenre(genre);
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return book;
    }

    @Override
    public List<Book> findByAuthor(Author author) throws DAOException {
        List<Book> books = new ArrayList();
        GenreDAO gdao = new GenreDAO(super.logger);

        try {
            super.makeConnection();
            Object[] args = {author.getId()};
            result = super.getItems(FIND_BOOKS_BY_AUTHOR, args);

            while (result.next()) {
                Book book = new Book();
                book.setId(result.getInt("ID"));
                book.setTitle(result.getString("Title"));
                book.setStock(result.getInt("Stock"));
                Genre genre = gdao.findById(result.getInt("Genre"));
                book.setAuthor(author);
                book.setGenre(genre);
                books.add(book);
            }

            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return books;
    }

    @Override
    public List<Book> findByGenre(Genre genre) throws DAOException {
        List<Book> books = new ArrayList<>();
        Book book = null;
        try {
            super.makeConnection();
            Object[] args = {genre.getId()};
            result = super.getItems(FIND_BOOKS_BY_GENRE, args);
            AuthorDAO authordao = new AuthorDAO(super.logger);
            while (result.next()) {
                book = new Book();
                book.setId(result.getInt("ID"));
                book.setTitle(result.getString("Title"));
                book.setStock(result.getInt("Stock"));
                Author author = authordao.findById(result.getInt("Author"));
                book.setAuthor(author);
                book.setGenre(genre);
                books.add(book);
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return books;
    }

    public List<Book> findByAuthors(List<Author> authors) throws DAOException {
        List<Book> books = new ArrayList();
        GenreDAO gdao = new GenreDAO(super.logger);

        try {
            super.makeConnection();
            for (Author author : authors) {
                Object[] args = {author.getId()};
                result = super.getItems(FIND_BOOKS_BY_AUTHOR, args);
                while (result.next()) {
                    Book book = new Book();
                    book.setId(result.getInt("ID"));
                    book.setTitle(result.getString("Title"));
                    book.setStock(result.getInt("Stock"));
                    Genre genre = gdao.findById(result.getInt("Genre"));
                    book.setAuthor(author);
                    book.setGenre(genre);
                    books.add(book);
                }
            }
            super.closeConnection();
        } catch (SQLException ex) {
            logger.info("Sql exception thrown;");
            logger.error("Exception thrown!", ex);
        }
        return books;
    }

    public boolean updateBookStock(Book entity) throws DAOException {
        int resultValue;
        super.makeConnection();
        Object args[] = {entity.getStock(), entity.getId()};
        resultValue = super.executeQuery(UPDATE_BOOK_STOCK, args);
        super.closeConnection();
        if (resultValue > 0) {
            return true;
        } else {
            return false;
        }
    }
}
