package com.p4zd4n.bibliothecachudyana.servicetest;

import com.p4zd4n.bibliothecachudyana.dao.CartItemDAO;
import com.p4zd4n.bibliothecachudyana.dao.UserDAO;
import com.p4zd4n.bibliothecachudyana.dao.WishlistItemDAO;
import com.p4zd4n.bibliothecachudyana.entity.*;
import com.p4zd4n.bibliothecachudyana.service.implementation.UserServiceImpl;
import com.p4zd4n.bibliothecachudyana.util.PasswordEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private WishlistItemDAO wishlistItemDAO;

    @Mock
    private CartItemDAO cartItemDAO;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Test for findById() method")
    public void testFindById() {

        User user = new User();
        user.setId(1);

        when(userDAO.findById(1)).thenReturn(user);

        User result = userService.findById(1);

        assertEquals(user, result);
    }

    @Test
    @DisplayName("Test for findByUsername() method")
    public void testFindByUsername() {

        User user = new User();
        user.setUsername("testUser");

        when(userDAO.findByUsername("testUser")).thenReturn(user);

        User result = userService.findByUsername("testUser");

        assertEquals(user, result);
    }

    @Test
    @DisplayName("Test for findByAuthorities() method")
    public void testFindByAuthorities() {

        User user1 = new User();
        User user2 = new User();

        List<Authority> authorities = List.of(
                new Authority("ROLE_USER", user1),
                new Authority("ROLE_EMPLOYEE", user1));

        user1.setAuthorities(authorities);
        user2.setAuthorities(authorities);

        List<String> matchingAuthorities = List.of("ROLE_USER", "ROLE_EMPLOYEE");

        when(userDAO.findByAuthorities(matchingAuthorities)).thenReturn(List.of(user1, user2));

        List<User> result = userService.findByAuthorities(matchingAuthorities);

        assertIterableEquals(List.of(user1, user2), result);
    }

    @Test
    @DisplayName("Test for findByStatus() method")
    public void testFindByStatus() {

        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();

        user1.setEnabled(1);
        user2.setEnabled(1);
        user3.setEnabled(0);
        user4.setEnabled(1);

        List<User> enabledUsers = List.of(user1, user2, user4);
        List<User> disabledUsers = List.of(user3);

        when(userDAO.findByStatus(1)).thenReturn(enabledUsers);
        when(userDAO.findByStatus(0)).thenReturn(disabledUsers);

        List<User> result1 = userService.findByStatus(1);
        List<User> result2 = userService.findByStatus(0);

        assertIterableEquals(enabledUsers, result1);
        assertIterableEquals(disabledUsers, result2);
    }

    @Test
    @DisplayName("Test for findAll() method")
    public void testFindAll() {

        List<User> users = List.of(new User(), new User(), new User());
        users.getFirst().setUsername("testUser");

        when(userDAO.findAll()).thenReturn(users);

        List<User> result = userService.findAll();

        assertIterableEquals(users, result);
        assertEquals("testUser", users.getFirst().getUsername());
    }

    @Test
    @DisplayName("Test for isUserAlreadyRegistered() method")
    public void testIsUserAlreadyRegistered() {

        User user1 = new User();
        user1.setUsername("wiktor");
        User user2 = new User();
        user2.setEmail("test@test.test");
        User user3 = new User();
        user3.setUsername("test");
        User user4 = new User();
        user4.setEmail("t@t.t");

        when(userDAO.findByUsername("wiktor")).thenReturn(user1);
        boolean result1 = userService.isUserAlreadyRegistered(user1);

        when(userDAO.findByEmail("test@test.test")).thenReturn(user2);
        boolean result2 = userService.isUserAlreadyRegistered(user2);

        when(userDAO.findByUsername("test")).thenReturn(null);
        boolean result3 = userService.isUserAlreadyRegistered(user3);

        when(userDAO.findByEmail("t@t.t")).thenReturn(null);
        boolean result4 = userService.isUserAlreadyRegistered(user4);

        assertTrue(result1);
        assertTrue(result2);
        assertFalse(result3);
        assertFalse(result4);
    }

    @Test
    @DisplayName("Test for isUnauthorizedToEnter() method")
    public void testIsUnauthorizedToEnter() {

        // ...
    }

    @Test
    @DisplayName("Test for hasUserEmployeeAuthority() method")
    public void testHasUserEmployeeAuthority() {

        User user1 = new User();
        User user2 = new User();

        List<Authority> authorities1 = List.of(
                new Authority("ROLE_USER", user1),
                new Authority("ROLE_EMPLOYEE", user1));
        List<Authority> authorities2 = List.of(new Authority("ROLE_USER", user2));

        user1.setAuthorities(authorities1);
        user2.setAuthorities(authorities2);

        boolean result1 = userService.hasUserEmployeeAuthority(user1);
        boolean result2 = userService.hasUserEmployeeAuthority(user2);

        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    @DisplayName("Test for hasUserManagerAuthority() method")
    public void testHasUserManagerAuthority() {

        User user1 = new User();
        User user2 = new User();

        List<Authority> authorities1 = List.of(
                new Authority("ROLE_USER", user1),
                new Authority("ROLE_EMPLOYEE", user1),
                new Authority("ROLE_MANAGER", user1));
        List<Authority> authorities2 = List.of(
                new Authority("ROLE_USER", user2),
                new Authority("ROLE_EMPLOYEE", user2));

        user1.setAuthorities(authorities1);
        user2.setAuthorities(authorities2);

        boolean result1 = userService.hasUserManagerAuthority(user1);
        boolean result2 = userService.hasUserManagerAuthority(user2);

        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    @DisplayName("Test for hasUserAdminAuthority() method")
    public void testHasUserAdminAuthority() {

        User user1 = new User();
        User user2 = new User();

        List<Authority> authorities1 = List.of(
                new Authority("ROLE_USER", user1),
                new Authority("ROLE_EMPLOYEE", user1),
                new Authority("ROLE_MANAGER", user1),
                new Authority("ROLE_ADMIN", user1));
        List<Authority> authorities2 = List.of(
                new Authority("ROLE_USER", user2),
                new Authority("ROLE_EMPLOYEE", user2));

        user1.setAuthorities(authorities1);
        user2.setAuthorities(authorities2);

        boolean result1 = userService.hasUserAdminAuthority(user1);
        boolean result2 = userService.hasUserAdminAuthority(user2);

        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    @DisplayName("Test for registerUser() method")
    public void testRegisterUser() {

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        userService.registerUser(user);

        verify(userDAO, times(1)).save(user);

        assertEquals(1, user.getEnabled());
        assertEquals(1, user.getAuthorities().size());
        assertEquals("ROLE_USER", user.getAuthorities().getFirst().getAuthority());
        assertTrue(PasswordEncoder.checkPassword("testPassword", user.getPassword().replace("{bcrypt}", "")));
        assertNotNull(user.getWishlist());
        assertNotNull(user.getCart());
    }

    @Test
    @DisplayName("Test for addBookToWishlist() method")
    public void testAddBookToWishlist() {

        User user = new User();
        Wishlist wishlist = new Wishlist();
        List<WishlistItem> wishlistItems = new ArrayList<>();
        wishlist.setItems(wishlistItems);
        user.setWishlist(wishlist);

        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Pan Tadeusz");
        Book book2 = new Book();
        book2.setId(2);
        book2.setCategory("Rozwój osobisty");

        userService.addBookToWishlist(user, book1);
        userService.addBookToWishlist(user, book2);

        assertEquals(2, user.getWishlist().getItems().size());
        assertEquals("Pan Tadeusz", user.getWishlist().getItems().getFirst().getBook().getTitle());
        assertEquals("Rozwój osobisty", user.getWishlist().getItems().get(1).getBook().getCategory());

        userService.addBookToWishlist(user, book1);

        assertEquals(2, user.getWishlist().getItems().size());
    }

    @Test
    @DisplayName("Test for removeBookFromWishlist() method")
    public void testRemoveBookFromWishlist() {

        User user = new User();
        Wishlist wishlist = new Wishlist();
        List<WishlistItem> wishlistItems = new ArrayList<>();
        wishlist.setItems(wishlistItems);
        user.setWishlist(wishlist);

        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Pan Tadeusz");
        Book book2 = new Book();
        book2.setId(2);
        book2.setCategory("Rozwój osobisty");

        userService.addBookToWishlist(user, book1);
        userService.addBookToWishlist(user, book2);

        userService.removeBookFromWishlist(user, book1);

        assertEquals(1, user.getWishlist().getItems().size());
        assertFalse(user.getWishlist().getItems().contains(book1));
    }

    @Test
    @DisplayName("Test for addBookToCart() method")
    public void testAddBookToCart() {

        User user = new User();
        Cart cart = new Cart();
        List<CartItem> cartItems = new ArrayList<>();
        cart.setItems(cartItems);
        user.setCart(cart);

        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Pan Tadeusz");
        Book book2 = new Book();
        book2.setId(2);
        book2.setCategory("Rozwój osobisty");

        userService.addBookToCart(user, book1);
        userService.addBookToCart(user, book2);

        assertEquals(2, user.getCart().getItems().size());
        assertEquals("Pan Tadeusz", user.getCart().getItems().getFirst().getBook().getTitle());
        assertEquals("Rozwój osobisty", user.getCart().getItems().get(1).getBook().getCategory());

        userService.addBookToCart(user, book1);

        assertEquals(2, user.getCart().getItems().size());
    }

    @Test
    @DisplayName("Test for removeBookFromCart() method")
    public void testRemoveBookFromCart() {

        User user = new User();
        Cart cart = new Cart();
        List<CartItem> cartItems = new ArrayList<>();
        cart.setItems(cartItems);
        user.setCart(cart);

        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Pan Tadeusz");
        Book book2 = new Book();
        book2.setId(2);
        book2.setCategory("Rozwój osobisty");

        userService.addBookToCart(user, book1);
        userService.addBookToCart(user, book2);

        userService.removeBookFromCart(user, book1);

        assertEquals(1, user.getCart().getItems().size());
        assertFalse(user.getCart().getItems().contains(book1));
    }

    @Test
    @DisplayName("Test for removeAllBooksFromCart() method")
    public void testRemoveAllBooksFromCart() {

        User user = new User();
        Cart cart = new Cart();
        List<CartItem> cartItems = new ArrayList<>();
        cart.setItems(cartItems);
        user.setCart(cart);

        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("Pan Tadeusz");
        Book book2 = new Book();
        book2.setId(2);
        book2.setCategory("Rozwój osobisty");

        userService.addBookToCart(user, book1);
        userService.addBookToCart(user, book2);

        userService.removeAllBooksFromCart(user);

        assertEquals(0, user.getCart().getItems().size());
        assertFalse(user.getCart().getItems().contains(book1));
        assertFalse(user.getCart().getItems().contains(book2));
    }

    @Test
    @DisplayName("Test for save() method")
    public void testSave() {

        User user = new User();

        userService.save(user);
        verify(userDAO, times(1)).save(user);
    }

    @Test
    @DisplayName("Test for update() method")
    public void testUpdate() {

        User user = new User();

        userService.update(user);
        verify(userDAO, times(1)).update(user);
    }

    @Test
    @DisplayName("Test for delete() method")
    public void testDelete() {

        User user = new User();

        userService.delete(user);
        verify(userDAO, times(1)).delete(user);
    }
}
