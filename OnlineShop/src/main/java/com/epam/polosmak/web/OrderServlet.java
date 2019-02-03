package com.epam.polosmak.web.servlet;

import com.epam.polosmak.entity.Cart;
import com.epam.polosmak.entity.User;
import com.epam.polosmak.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createOrder")
public class OrderServlet extends HttpServlet {
    private static final String INDEX_PATH = "success.jsp";
    private static final String ORDER_PAGE = "WEB-INF/pages/createOrder.jsp";
    private static final String USER_SESSION = "userBean";
    private static final String ORDER_SERVICE = "orderService";
    private static final String CART = "cart";
    private OrderService orderService;

    @Override
    public void init() {
        orderService = (OrderService) getServletContext().getAttribute(ORDER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ORDER_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute(CART);
        User user = (User) req.getSession().getAttribute(USER_SESSION);
        orderService.createOrder(cart, user);
        req.getSession().removeAttribute(CART);
        resp.sendRedirect(INDEX_PATH);
    }
}
