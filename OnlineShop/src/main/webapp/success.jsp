<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="info" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="URL" value="${pageContext.request.requestURL }"/>
<c:set var="URI" value="${pageContext.request.requestURI }"/>
<c:set var="baseURL" value="${fn:replace(URL, URI, contextPath)}"/>
<c:set var="filter" value="${sessionScope.productFilter}"/>

<html>
<head>
    <title>Big shope A Ecommerce Category Flat Bootstarp Resposive Website Template | Home :: w3layouts</title>
    <link href="${contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <!--theme-style-->
    <link href="${contextPath}/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!--//theme-style-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <script type="application/x-javascript">
         addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); }
    </script>
    <!--fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
    <!--//fonts-->
    <script src="${contextPath}/js/jquery.min.js"></script>
    <!--script-->
</head>
<body>

<!--header-->
<div class="header">
    <div class="top-header">
        <div class="container">
            <div class="top-header-left">
                <ul class="support">
                    <li><a href="#"><label> </label></a></li>
                    <li><a href="#">24x7 live<span class="live"> support</span></a></li>
                </ul>
                <ul class="support">
                    <li class="van"><a href="#"><label> </label></a></li>
                    <li><a href="#">Free shipping <span class="live">on order over 500</span></a></li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="top-header-right">
                <div class="down-top">
                    <select class="in-drop">
                        <option value="English" class="in-of">English</option>
                        <option value="Russian" class="in-of">Russian</option>

                    </select>
                </div>
                <div class="down-top top-down">
                    <select class="in-drop">

                        <option value="Dollar" class="in-of">Dollar</option>
                        <option value="UAH" class="in-of">UAH</option>
                    </select>
                </div>
                <!---->
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="bottom-header">
        <div class="container">
            <div class="header-bottom-left">
                <div class="logo">
                    <a href="${baseURL}"><img src="images/logo.png" alt=" "/></a>
                </div>

                <div class="search">
                    <form id="filter-form" method="get" action="products">

                        <input type="text" name="name" value="${filter.name}">
                        <input type="submit" value="SEARCH">
                    </form>
                </div>

                <div class="clearfix"></div>
            </div>
            <div class="header-bottom-right">
                <info:account user="${user}" avatar="avatar"/>
                <div class="cart"> <a href="cart"><span></span>CART</a> <span  class="fa-cart-arrow-down"> ${cart.getCountItems()}</span></div>
            </div>
        </div>
    </div>
<!---->

<h2 align="center">ORDER ACCEPTED</h2>

<div class="footer">
    <div class="footer-top">
        <div class="container">
            <div class="latter">
                <h6>NEWS-LETTER</h6>
                <div class="sub-left-right">
                    <form>
                        <input type="text" value="Enter email here" onfocus="this.value = '';"
                               onblur="if (this.value == '') {this.value = 'Enter email here';}"/>
                        <input type="submit" value="SUBSCRIBE"/>
                    </form>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="latter-right">
                <p>FOLLOW US</p>
                <ul class="face-in-to">
                    <li><a href="#"><span> </span></a></li>
                    <li><a href="#"><span class="facebook-in"> </span></a></li>
                    <div class="clearfix"></div>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="footer-bottom">
        <div class="container">
            <div class="footer-bottom-cate">
                <h6>CATEGORIES</h6>
                <ul>
                    <li><a href="#">CATEGORY 1</a></li>

                </ul>
            </div>
            <div class="footer-bottom-cate bottom-grid-cat">
                <h6>FEATURE PROJECTS</h6>
                <ul>
                    <li><a href="#">FEATURE PROJECT 1</a></li>

                </ul>
            </div>
            <div class="footer-bottom-cate">
                <h6>TOP BRANDS</h6>
                <ul>
                    <li><a href="#">TOP BRAND 1</a></li>

                </ul>
            </div>
            <div class="footer-bottom-cate cate-bottom">
                <h6>OUR ADDRESS</h6>
                <ul>
                    <li>ADDRESS 1</li>

                    <li class="phone">PH : 9379992</li>
                    <li class="temp"><p class="footer-class">Design by <a href="http://w3layouts.com/" target="_blank">W3layouts</a>
                    </p></li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
</body>
  <%@include file="/WEB-INF/jspf/scripts.jspf"%>
</html>