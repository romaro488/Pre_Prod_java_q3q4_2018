package com.epam.polosmak.task4;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.repository.CartRepository;
import com.epam.polosmak.task4.repository.impl.LastAddedProductsToCart;
import com.epam.polosmak.task4.service.CartService;
import com.epam.polosmak.task4.service.StoreService;
import com.epam.polosmak.task4.service.impl.CartServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartServiceImplTest {

    private static CartService cartService;

    private static CartRepository cart;
    private static StoreService storeService;
    private static Sportswear sportswear;
    private static LastAddedProductsToCart lastAddedProductsToCart;
    private Map<Sportswear, Integer> cartMap = new HashMap<>();

    @BeforeClass
    public static void init() {
        cart = mock(CartRepository.class);
        storeService = mock(StoreService.class);


        cartService = new CartServiceImpl(lastAddedProductsToCart, cart);

        sportswear = new Sportswear();
        sportswear.setName("name");
    }

    @Test
    public void shouldReturnCartHistory_whenOnCartServiceGetCartHistory() {
        when(cart.getCartMap()).thenReturn(cartMap);
        assertEquals(cartService.getProducts(), cartMap);
    }

    @Test
    public void shouldReturnCart_whenCartServiceGetCart() {
        assertEquals(cartService.getProducts(), cart.getCartMap());
    }
}