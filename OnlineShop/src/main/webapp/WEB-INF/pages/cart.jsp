<%@ taglib prefix="mainPageTag" tagdir="/WEB-INF/tags/main-page" %>
<%@ taglib prefix="commonTag" tagdir="/WEB-INF/tags" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<html>

<head>
    <%@include file="/WEB-INF/jspf/head.jspf"%>
</head>

<body>
    <!-- top-header -->
    <div class="privacy py-sm-5 py-4">
        <div class="container py-xl-4 py-lg-2">
            <!-- tittle heading -->
            <h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
                <span>C</span>art
            </h3>
            <!-- //tittle heading -->
            <c:if test="${not empty cart}">
                <h4 class="mb-sm-4 mb-3">Your shopping cart contains:
                    <span class="cartProduct">${quantity}</span> product(s)
                </h4>
                <h4 class="mb-sm-4 mb-3">Total price:
                    <span class="totalPrice">${cart.getTotalPrice()}</span>
                </h4>
                <a href="products">Products page</a>
            </c:if>
            <c:if test="${empty cart}">
                <h4 class="mb-sm-4 mb-3">Your shopping cart contains:
                    <span class="cartProduct">0</span> product(s)
                </h4>
                <h4 class="mb-sm-4 mb-3">Total price:
                    <span class="totalPrice">0</span>
                </h4>
                <a href="products">Products page</a>
            </c:if>
            <c:if test="${cart.getTotalPrice() > 0}">
                <div class="checkout-right">
                    <div class="table-responsive">
                        <table class="timetable_sub">
                            <thead>
                                <tr>
                                    <th>№</th>
                                    <th>Quality</th>
                                    <th>Product Name</th>
                                    <th>Price</th>
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cart" items="${cart.getAllItems()}">
                                    <c:set var="k" value="${k+1}" />
                                    <tr id="product${cart.key.id}" class="rem1">
                                        <td class="invert">${k}</td>

                                        <td class="invert">
                                            <div class="quantity">
                                                <div class="quantity-select">
                                                    <div class="entry value-minus subractitem" data-parameter="${cart.key.id}">&nbsp;</div>
                                                    <div class="entry value">
                                                        <span class="currentElement${cart.key.id}">${cart.value}</span>
                                                    </div>
                                                    <div class="entry value-plus active addToCart" data-parameter="${cart.key.id}">&nbsp;</div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="invert">${cart.key.name}</td>
                                         <td class="invert">${cart.key.price}</td>
                                         <td class="invert">
                                         <div class="rem">
                                         <div class="close1 delItem" data-parameter="${cart.key.id}"></div>
                                         </div>
                                         </td>
                                         </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <button class="btn clearCart mt-2">Clear cart</button>
                    <c:if test="${not empty userBean}">
                        <button class="btn mt-2"><a href="createOrder" class="btn mt-2">Make order</a></button>
                    </c:if>
                    <c:if test="${empty userBean && quantity > 0}">
                        <button><a href="register" class="btn mt-2">Make order</a></button>
                    </c:if>
                </div>
            </c:if>
        </div>
    </div>
    <!-- //checkout page -->
    <%@include file="/WEB-INF/jspf/scripts.jspf"%>
</body>
</html>
