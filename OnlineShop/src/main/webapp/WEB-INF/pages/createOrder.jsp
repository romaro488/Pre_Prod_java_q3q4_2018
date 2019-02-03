<%@ taglib prefix="mainPageTag" tagdir="/WEB-INF/tags/main-page" %>
<%@ taglib prefix="commonTag" tagdir="/WEB-INF/tags" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>


<html>

<head>
    <%@include file="/WEB-INF/jspf/head.jspf"%>
</head>

<body>
    <!-- top-header -->
    <!-- Button trigger modal(select-location) -->


    <div class="privacy py-sm-5 py-4">
        <div class="container py-xl-4 py-lg-2">
            <!-- tittle heading -->
            <h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
                <span>C</span>Create order
            </h3>
            <h4 class="mb-sm-4 mb-3">Your shopping cart contains:
                <span class="cartProduct">${quantity}</span> product(s)
            </h4>
            <h4 class="mb-sm-4 mb-3">Total price:
                <span class="totalPrice">${cart.getTotalPrice()}</span>
            </h4>
            <form action="createOrder" method="post">
                <div class="row">
                    <div class="col-md-3 field-label-responsive">
                        <label for="firstName">Payment type</label>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <input type="text" name="firstName" class="form-control" id="firstName" placeholder="Name" required autofocus>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 field-label-responsive">
                        <label for="firstName">Delivery type</label>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <input type="text" name="firstName" class="form-control" id="firstName" placeholder="Name" required autofocus>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 field-label-responsive">
                        <label for="firstName">Settlement account</label>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <input type="number" name="firstName" class="form-control" id="firstName" placeholder="Settlement account" required autofocus>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6 text-right">
                        <button type="submit" class="btn btn-success"> Make order</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!-- //checkout page -->

    <%@include file="/WEB-INF/jspf/scripts.jspf"%>

</body>

</html>
