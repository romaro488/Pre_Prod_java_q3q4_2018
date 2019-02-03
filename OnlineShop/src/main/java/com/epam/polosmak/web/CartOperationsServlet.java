package com.epam.polosmak.web.servlet;

import com.epam.polosmak.entity.Cart;
import com.epam.polosmak.entity.Product;
import com.epam.polosmak.service.ItemService;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet("/cartOperationsServlet.do")
public class CartOperationsServlet extends HttpServlet {
    private static final String PRODUCT_SERVICE = "itemService";
    private static final String CART = "cart";
    private ItemService itemService;

    @Override
    public void init() {
        itemService = (ItemService) getServletContext().getAttribute(PRODUCT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        performingOperation(req, resp);
    }

    private void performingOperation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cartOperationParam = "operation";
        String quantityParam = "quantity";
        String userIdParam = "id";

        Cart cart = getCart(req);
        JSONObject jsonObject = new JSONObject();

        if (Objects.nonNull(req.getParameter(cartOperationParam))) {
            String operation = req.getParameter(cartOperationParam);
            int userId = Objects.nonNull(req.getParameter(userIdParam)) ? Integer.parseInt(req.getParameter(userIdParam)) : 0;
            Product item;
            switch (operation) {
                case "add":
                    item = itemService.getItemById(userId);
                    addToCart(item, req, cart);
                    break;
                case "remove":
                    item = itemService.getItemById(userId);
                    cart.deleteFromCart(item);
                    break;
                case "subtract":
                    item = itemService.getItemById(userId);
                    cart.subtract(item);
                    break;
                case "clear":
                    cart.clearCart();
                    break;
            }
        }

        int quantity = cart.getCountItems();
        int totalPrice = cart.getTotalPrice();

        jsonObject.put(quantityParam, quantity);
        jsonObject.put("totalPrice", totalPrice);

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(jsonObject);

        req.getSession().setAttribute(CART, cart);
        req.getSession().setAttribute(quantityParam, quantity);
    }


    private Cart getCart(HttpServletRequest req) {
        if (req.getSession().getAttribute(CART) == null)
            return new Cart();
        return (Cart) req.getSession().getAttribute(CART);
    }

    private void addToCart(Product item, HttpServletRequest req, Cart cart) {
        cart.addToCart(item, 1);
    }
}
