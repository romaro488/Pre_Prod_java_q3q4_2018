package com.epam.polosmak.web.extractor;

import com.epam.polosmak.enumeration.MysqlProductFields;
import com.epam.polosmak.web.bean.ProductFilter;
import com.epam.polosmak.web.exctractor.ProductFilterBeanExtractor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductFilterBeanExtractorTest {
    private final Integer DEFAULT_PRODUCT_LIMIT = 9;
    private final Integer DEFAULT_PAGE = 0;

    private final String EXPECTED_NAME = "expectedName";
    private final String TRUE_STRING = "true";
    private final String FALSE_STRING = "false";

    private final String PRODUCT_FILTER_BEAN = "productFilter";
    private final String CATEGORIES = "category-checkbox";
    private final String MANUFACTURERS = "manufacturer-checkbox";
    private final String ORDER_BY = "orderBy";
    private final String DESC = "desc";
    private final String NAME = "name";
    private final String MIN_PRICE = "minPrice";
    private final String MAX_PRICE = "maxPrice";
    private final String LIMIT = "limit";
    private final String PAGE = "page";
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession session;

    private ProductFilter filter;
    private ProductFilterBeanExtractor extractor;

    @Before
    public void setUp() {
        extractor = new ProductFilterBeanExtractor();
        filter = new ProductFilter();
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void shouldReturnNewProductFilterIfAttributesDoesNotContainsProductFilter() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(null);
        ProductFilter actuallyFilter = extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        Assert.assertEquals(filter, actuallyFilter);
    }

    @Test
    public void shouldSetListOfCategoriesIntoProductFilter() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);

        String[] categories = {"1", "2", "3"};
        when(request.getParameterValues(CATEGORIES)).thenReturn(categories);

        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(1);
        expectedList.add(2);
        expectedList.add(3);

        extractor.extractFromRequest(request);
        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameterValues(CATEGORIES);

        Assert.assertEquals(expectedList, filter.getCategories());
    }

    @Test
    public void shouldSetListOfManufacturersIntoProductFilter() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);

        String[] manufacturers = {"1", "2", "3"};
        when(request.getParameterValues(MANUFACTURERS)).thenReturn(manufacturers);

        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(1);
        expectedList.add(2);
        expectedList.add(3);

        extractor.extractFromRequest(request);
        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameterValues(MANUFACTURERS);

        Assert.assertEquals(expectedList, filter.getManufacturers());
    }

    @Test
    public void shouldSetOrderByIntoProductFilter() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(ORDER_BY)).thenReturn("name");

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(ORDER_BY);

        Assert.assertEquals(MysqlProductFields.NAME, filter.getOrderBy());
    }

    @Test
    public void shouldLeaveAsBeforeOrderByIntoProductFilter() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(ORDER_BY)).thenReturn("Wrong orderBy");

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(ORDER_BY);

        Assert.assertNull(filter.getOrderBy());
    }

    @Test
    public void shouldSetNameIntoProductFilter() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(NAME)).thenReturn(EXPECTED_NAME);

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(NAME);

        Assert.assertEquals(EXPECTED_NAME, filter.getName());
    }

    @Test
    public void shouldSetTrueDescendinglyIntoProductFilter() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(DESC)).thenReturn(TRUE_STRING);

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(DESC);

        Assert.assertTrue(filter.isDescendingly());
    }

    @Test
    public void shouldSetFalseDescendinglyIntoProductFilter() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(DESC)).thenReturn(FALSE_STRING);

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(DESC);

        Assert.assertFalse(filter.isDescendingly());
    }

    @Test
    public void shouldLeaveAsBeforeMinPriceIntoProductFilterIfMinPriceParameterIsNegative() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(MIN_PRICE)).thenReturn("-1");

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(MIN_PRICE);

        Assert.assertNull(filter.getMinPrice());
    }

    @Test
    public void shouldSetValueMinPriceIntoProductFilterIfMinPriceParameterIsNumber() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(MIN_PRICE)).thenReturn("0");

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(MIN_PRICE);

        Assert.assertEquals(Integer.valueOf(0), filter.getMinPrice());
    }

    @Test
    public void shouldLeaveAsBeforeMinPriceIntoProductFilterIfMinPriceParameterIsNotANumber() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(MIN_PRICE)).thenReturn("abc");

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(MIN_PRICE);

        Assert.assertNull(filter.getMinPrice());
    }

    @Test
    public void shouldSetValueMinPriceIntoProductFilterIfMaxPriceParameterIsNumber() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(MAX_PRICE)).thenReturn("0");

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(MAX_PRICE);

        Assert.assertEquals(Integer.valueOf(0), filter.getMaxPrice());
    }

    @Test
    public void shouldLeaveAsBeforeMinPriceIntoProductFilterIfMaxPriceParameterIsNegative() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(MAX_PRICE)).thenReturn("-1");

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(MAX_PRICE);

        Assert.assertNull(filter.getMaxPrice());
    }

    @Test
    public void shouldLeaveAsBeforeMinPriceIntoProductFilterIfMaxPriceParameterIsNotANumber() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(MAX_PRICE)).thenReturn("abc");

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(MAX_PRICE);

        Assert.assertNull(filter.getMaxPrice());
    }

    @Test
    public void shouldSetValueMinPriceIntoProductFilterIfLimitParameterIsNumber() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(LIMIT)).thenReturn("0");

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(LIMIT);

        Assert.assertEquals(Integer.valueOf(0), filter.getProductLimit());
    }

    @Test
    public void shouldLeaveAsBeforeMinPriceIntoProductFilterIfLimitParameterIsNotANumber() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(LIMIT)).thenReturn("abc");

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(LIMIT);

        Assert.assertEquals(DEFAULT_PRODUCT_LIMIT, filter.getProductLimit());
    }

    @Test
    public void shouldLeaveAsBeforeMinPriceIntoProductFilterIfInputLimitParameterIsNegative() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(LIMIT)).thenReturn("-1");

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(LIMIT);

        Assert.assertEquals(DEFAULT_PRODUCT_LIMIT, filter.getProductLimit());
    }

    @Test
    public void shouldSetValueMinPriceIntoProductFilterIfPageParameterIsNumber() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(PAGE)).thenReturn("0");

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(PAGE);

        Assert.assertEquals(Integer.valueOf(0), filter.getPage());
    }

    @Test
    public void shouldLeaveAsBeforeMinPriceIntoProductFilterIfInputPageParameterIsNotANumber() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(PAGE)).thenReturn("abc");

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(PAGE);

        Assert.assertEquals(DEFAULT_PAGE, filter.getPage());
    }

    @Test
    public void shouldLeaveAsBeforeMinPriceIntoProductFilterIfInputPageParameterIsNegative() {
        when(session.getAttribute(PRODUCT_FILTER_BEAN)).thenReturn(filter);
        when(request.getParameter(PAGE)).thenReturn("-1");

        extractor.extractFromRequest(request);

        verify(session).getAttribute(PRODUCT_FILTER_BEAN);
        verify(request).getParameter(PAGE);

        Assert.assertEquals(DEFAULT_PAGE, filter.getPage());
    }

}