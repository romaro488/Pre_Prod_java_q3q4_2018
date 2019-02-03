package com.epam.polosmak.task7;

import com.epam.polosmak.task7.product.Product;
import com.epam.polosmak.task7.productMap.HandlerMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ProductMapTest {

    private static final String PRODUCT_NAME = "Puma";
    private static final String COLOR = "blue";
    private static final int PRICE = 200;
    private static final String INCORRECT_NAME = "aaa";
    private static final int SET_PRICE = 300;
    private Product product;

    @Before
    public void init() {
        HandlerMap handlerMap = new HandlerMap();
        product = handlerMap.creator();
        product.setName(PRODUCT_NAME);
        product.setColor(COLOR);
        product.setPrice(PRICE);
    }

    @Test
    public void ShouldGetProductNameFromProduct() {
        assertEquals(PRODUCT_NAME, product.getName());
    }

    @Test
    public void ShouldGetColorFromProduct() {

        assertEquals(COLOR, product.getColor());
    }

    @Test
    public void ShouldGetPriceFromProduct() {
        assertEquals(PRICE, product.getPrice());
    }

    @Test
    public void ShouldSetNameFromProduct() {
        product.setName(INCORRECT_NAME);

        assertEquals(INCORRECT_NAME, product.getName());
    }

    @Test
    public void ShouldSetColorFromProduct() {
        product.setColor(COLOR);

        assertEquals(COLOR, product.getColor());
    }

    @Test
    public void ShouldSetPriceFromProduct() {
        product.setPrice(SET_PRICE);

        assertEquals(SET_PRICE, product.getPrice());
    }
}