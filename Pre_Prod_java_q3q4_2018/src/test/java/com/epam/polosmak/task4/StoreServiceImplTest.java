package com.epam.polosmak.task4;

import com.epam.polosmak.task1.entity.Sportswear;
import com.epam.polosmak.task4.repository.ProductsRepository;
import com.epam.polosmak.task4.repository.impl.ProductsRepositoryImpl;
import com.epam.polosmak.task4.service.StoreService;
import com.epam.polosmak.task4.service.impl.StoreServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.TreeMap;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class StoreServiceImplTest {
    private final static String FIRST_NAME = "Test1";
    private final static String SECOND_NAME = "Test2";
    private final static String NOT_USED_NAME = "Test3";

    private static ProductsRepository repository;
    private static StoreService storeService;
    private static Sportswear sportswear1;
    private static Sportswear sportswear2;

    private static TreeMap<String, Sportswear> productMap = new TreeMap<>();

    @BeforeClass
    public static void init() {
        repository = mock(ProductsRepositoryImpl.class);

        storeService = new StoreServiceImpl(repository);

        sportswear1 = new Sportswear();
        sportswear2 = new Sportswear();

        sportswear1.setName(FIRST_NAME);
        sportswear2.setName(SECOND_NAME);

        productMap.put(sportswear1.getName(), sportswear1);
        productMap.put(sportswear1.getName(), sportswear2);

        storeService = new StoreServiceImpl(repository);
    }

    @Test
    public void shouldReturnAllProductsFromStoreService_whenGetAllFromRepository() {
        when(repository.getProductMap()).thenReturn(productMap);

        assertEquals(productMap, repository.getProductMap());
    }

    @Test
    public void shouldReturnSportswearByName_whenServiceGetProductByName() {
        when(repository.getProductByName(SECOND_NAME)).thenReturn(sportswear2);

        assertEquals(sportswear2, storeService.getProduct(SECOND_NAME));
    }

    @Test
    public void shouldReturnNull_whenNoProductsInProductMapWithSuchName() {
        when(repository.getProductByName(NOT_USED_NAME)).thenReturn(null);

        assertNull(storeService.getProduct(NOT_USED_NAME));
    }
}
