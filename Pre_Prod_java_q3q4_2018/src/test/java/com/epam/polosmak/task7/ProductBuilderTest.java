package com.epam.polosmak.task7;

import com.epam.polosmak.task7.factory.Builder;
import com.epam.polosmak.task7.factory.ProductBuilder;
import com.epam.polosmak.task7.product.Product;
import com.epam.polosmak.task7.product.Sportswear;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductBuilderTest {

    private static final String NAME = "Shorts";
    private static final String COLOR = "black";
    private static final int PRICE = 200;
    private static final String INCORRECT_NAME = "dfokf";
    private Builder builder;

    @Before
    public void init() {
        builder = new ProductBuilder(new Sportswear(NAME, COLOR, PRICE));
    }

    @Test
    public void ShouldCreateProductWithFactoryMethod() {
        Product product = builder.creator();

        assertEquals(NAME, product.getName());
    }

    @Test(expected = Throwable.class)
    public void ShouldCreateProductWithCreatorAndThrowException() {
        Product product = builder.creator();

        product.setName(INCORRECT_NAME);

    }
}
