$(document).ready(function () {
    $('.addToCart').click(function () {
        var id = this.getAttribute('data-parameter')
        $.ajax({
            type: 'GET',
            data: {
                id: id,
                operation: 'add'
            },
            url: 'cartOperationsServlet.do',
            success: function (result) {
                var returnedData = JSON.parse(result);
                var currentValue = $('.currentElement' + id).text();
                $('.fa-cart-arrow-down').html(returnedData["quantity"]);
                $('.currentElement' + id).html(++currentValue);
                $('.cartProduct').html(returnedData["quantity"]);
                $('.totalPrice').html(returnedData["totalPrice"]);
            }
        });
    });

    $('.subractitem').click(function () {
        var id = this.getAttribute('data-parameter')
        $.ajax({
            type: 'GET',
            data: {
                id: id,
                operation: 'subtract'
            },
            url: 'cartOperationsServlet.do',
            success: function (result) {
                var returnedData = JSON.parse(result);
                var currentValue = $('.currentElement' + id).text();
                $('.fa-cart-arrow-down').html(returnedData["quantity"]);
                $('.cartProduct').html(returnedData["quantity"]);

                --currentValue;
                if (currentValue == 0) {
                    $('#product' + id).remove();
                } else {
                    $('.currentElement' + id).html(currentValue);
                }

                if (returnedData["quantity"] == 0) {
                    $('.checkout-right').remove();
                }

                $('.totalPrice').html(returnedData["totalPrice"]);
            }
        });
    });

    $('.clearCart').click(function () {
        $.ajax({
            type: 'GET',
            data: {
                operation: 'clear'
            },
            url: 'cartOperationsServlet.do',
            success: function (result) {
                var returnedData = JSON.parse(result);
                $('.cartProduct').html(returnedData["quantity"]);
                $('.checkout-right').remove();
                $('.fa-cart-arrow-down').html(returnedData["quantity"]);
                $('.totalPrice').html(returnedData["totalPrice"]);
            }
        });
    });

    $('.makeOrder').click(function () {
        $.ajax({
            type: 'GET',
            data: {
                operation: 'makeOrder'
            },
            url: 'cartOperationsServlet.do',
            success: function (result) {
                var returnedData = JSON.parse(result);
                $('.cartProduct').html(returnedData["quantity"]);
                $('.checkout-right').remove();
                $('.totalPrice').html(returnedData["totalPrice"]);

            }
        });
    });

    $('.delItem').click(function () {
        var id = this.getAttribute('data-parameter')
        $.ajax({
            type: 'GET',
            data: {
                id: id,
                operation: 'remove'
            },
            url: 'cartOperationsServlet.do',
            success: function (result) {
                var returnedData = JSON.parse(result);
                $('#product' + id).remove();
                $('.cartProduct').html(result);
                $('.cartProduct').html(returnedData["quantity"]);
                 $('.fa-cart-arrow-down').html(returnedData["quantity"]);
                $('.totalPrice').html(returnedData["totalPrice"]);
            }
        });
    });
});
