<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css"/>

    <!-- Slick -->
    <link type="text/css" rel="stylesheet" href="/css/slick.css"/>
    <link type="text/css" rel="stylesheet" href="/css/slick-theme.css"/>

    <!-- nouislider -->
    <link type="text/css" rel="stylesheet" href="/css/nouislider.min.css"/>

    <!-- Font Awesome Icon -->
    <link rel="stylesheet" href="/css/font-awesome.min.css">

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="/css/style.css"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<!-- HEADER -->
<header>
    <!-- TOP HEADER -->
    <div id="top-header">
        <div class="container">
            <ul class="header-links pull-left">
                <li><a href="tel:123-456-7890"><i class="fa fa-phone"></i> +021-95-51-84</a></li>
                <li><a href="mailto: abc@example.com"><i class="fa fa-envelope-o"></i> email@email.com</a></li>
                <li><a href="https://goo.gl/maps/ieFDZ37N7s5HdDYQ9" target="_blank"><i class="fa fa-map-marker"></i>Shkoder Albania</a></li>
            </ul>
            <ul class="header-links pull-right">
                <c:if test="${user.id == null}">

                    <div class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                            <span class="white"><i class="fa fa-user-o"></i>  Login</span>
                        </a>
                        <div class="cart-dropdown">
                            <div class="cart-list">
                                <div class="product-widget">
                                    <div class="product-body">
                                        <h3 class="product-name"><a href="/accountAdmin"><i class="fa fa-user-o"></i> Seller Login</a></h3>
                                    </div>
                                </div>

                                <div class="product-widget">
                                    <div class="product-body">
                                        <h3 class="product-name"><a href="/accountUser"><i class="fa fa-user-o"></i>  User Login</a></h3>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:if>
                <c:if test="${user.id != null}">
                    <div class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                            <span class="white"><i class="fa fa-user-o"></i>  ${user.userName}</span>
                        </a>
                        <div class="cart-dropdown">
                            <div class="cart-list">

                                <div class="product-widget">
                                    <div class="product-body">
                                        <h3 class="product-name"><a href="#"> <i class="fa fa-shopping-cart"></i>  Cart</a></h3>
                                    </div>
                                </div>

                                <div class="product-widget">
                                    <div class="product-body">
                                        <h3 class="product-name"><a href="#"> <i class="fa fa-heart-o"></i>  Wish List</a></h3>
                                    </div>
                                </div>

                                <div class="product-widget">
                                    <div class="product-body">
                                        <h3 class="product-name"><a href="/logout">Log out</a></h3>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>

            </ul>
        </div>
    </div>
    <!-- /TOP HEADER -->

    <!-- MAIN HEADER -->
    <div id="header">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <!-- LOGO -->
                <div class="col-md-3">
                    <div class="header-logo">
                        <a href="/" class="logo">
                            <img src="/img/logo.png" alt="">
                        </a>
                    </div>
                </div>
                <!-- /LOGO -->

                <!-- SEARCH BAR -->
                <div class="col-md-6">
                    <div class="header-search">
                        <form action="/search" method="post">
                            <input class="input" id="name" name="name" placeholder="Search here by product name">
                            <button class="search-btn">Search</button>
                        </form>
                    </div>
                </div>
                <!-- /SEARCH BAR -->

                <!-- ACCOUNT -->
                <div class="col-md-3 clearfix">
                    <div class="header-ctn">
                        <!-- Wishlist -->

                        <div class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                <i class="fa fa-heart-o"></i>
                                <span>Your Wishlist</span>
                                <div class="qty">${userWishlist}</div>
                            </a>
                            <div class="cart-dropdown">
                                <div class="cart-list">
                                    <c:forEach var="wishList" items="${user.addWishlist}">
                                        <div class="product-widget">
                                            <div class="product-img">
                                                <img src="${wishList.url}" alt="">
                                            </div>
                                            <div class="product-body">
                                                <h3 class="product-name"><a href="#">${wishList.name}</a></h3>
                                                <h4 class="product-price">$ ${wishList.price}</h4>
                                            </div>
                                            <a href="/delete/wishlist/${wishList.id}" class="delete"><i class="fa fa-close"></i></a>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>

                        <!-- /Wishlist -->

                        <!-- Cart -->
                        <div class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                <i class="fa fa-shopping-cart"></i>
                                <span>Your Cart</span>
                                <div class="qty">${userCart}</div>
                            </a>
                            <div class="cart-dropdown">
                                <div class="cart-list">
                                    <c:forEach var="cart" items="${user.addCart}">
                                        <div class="product-widget">
                                            <div class="product-img">
                                                <img src="${cart.url}" alt="">
                                            </div>
                                            <div class="product-body">
                                                <h3 class="product-name"><a href="#">${cart.name}</a></h3>
                                                <h4 class="product-price"><span class="qty">1x</span>$${cart.price}</h4>
                                            </div>
                                            <a href="/delete/cart/${cart.id}" class="delete"><i class="fa fa-close"></i></a>
                                        </div>
                                    </c:forEach>
                                </div>

                                <div class="cart-summary">
                                    <small>${userCart} Item(s) selected</small>
                                    <h5>SUBTOTAL: ${user.getTotal()}</h5>
                                </div>
                                <div class="cart-btns">
                                    <a href="#">View Cart</a>
                                    <a href="/checkout">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
                                </div>
                            </div>
                        </div>

                        <!-- /Cart -->

                        <!-- Menu Toogle -->
                        <div class="menu-toggle">
                            <a href="#">
                                <i class="fa fa-bars"></i>
                                <span>Menu</span>
                            </a>
                        </div>
                        <!-- /Menu Toogle -->
                    </div>
                </div>
                <!-- /ACCOUNT -->
            </div>
            <!-- row -->
        </div>
        <!-- container -->
    </div>
    <!-- /MAIN HEADER -->
</header>
<!-- /HEADER -->

<!-- NAVIGATION -->
<nav id="navigation">
    <!-- container -->
    <div class="container">
        <!-- responsive-nav -->
        <div id="responsive-nav">
            <!-- NAV -->

            <!-- /NAV -->
        </div>
        <!-- /responsive-nav -->
    </div>
    <!-- /container -->
</nav>
<!-- /NAVIGATION -->

<!-- SECTION -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">

            <!-- section title -->
            <div class="col-md-12">
                <div class="section-title">
                    <h3 class="title">Categori ${cateName.name}</h3>
                </div>
            </div>
            <!-- /section title -->

            <!-- Products tab & slick -->
            <div class="col-md-12">
                <div class="row">
                    <div class="products-tabs">
                        <!-- tab -->
                        <div id="tab1" class="tab-pane active">
                            <div class="products-slick" data-nav="#slick-nav-1">
                                <!-- product -->
                                <c:forEach items="${categoriPro}" var="productsVar">
                                    <div class="product">
                                        <div class="product-img">
                                            <img src="${productsVar.url}" alt="not good"/>
                                            <div class="product-label">
                                            </div>
                                        </div>
                                        <div class="product-body">
                                            <p class="product-category"></p>
                                            <h3 class="product-name"><a href="/products/${productsVar.id}">${productsVar.name}</a></h3>
                                            <h4 class="product-price">$ ${productsVar.price}</h4>
                                            <div class="product-rating">
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                                <i class="fa fa-star"></i>
                                            </div>
                                            <c:if test="${user != null}">
                                                <div class="product-btns">
                                                    <a href="/wishlist/${productsVar.id}" class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></a>
                                                    <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                </div>
                                            </c:if>
                                            <c:if test="${user == null}">
                                                <div class="product-btns">
                                                    <a href="/accountUser" class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></a>
                                                    <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                </div>
                                            </c:if>
                                        </div>
                                        <c:if test="${user != null}">
                                            <div class="add-to-cart">
                                                <a href="/addCart/${productsVar.id}" class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</a>
                                            </div>
                                        </c:if>
                                        <c:if test="${user == null}">
                                            <div class="add-to-cart">
                                                <a href="/accountUser" class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</a>
                                            </div>
                                        </c:if>
                                    </div>
                                </c:forEach>
                                <!-- /product -->

                            </div>
                            <div id="slick-nav-1" class="products-slick-nav"></div>
                        </div>
                        <!-- /tab -->
                    </div>
                </div>
            </div>
            <!-- Products tab & slick -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /SECTION -->


<!-- FOOTER -->
<footer id="footer">
    <!-- top footer -->
    <div class="section">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row">
                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">About Us</h3>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.</p>
                        <ul class="footer-links">
                            <li><a href="#"><i class="fa fa-map-marker"></i>1734 Stonecoal Road</a></li>
                            <li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
                            <li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Categories</h3>
                        <ul class="footer-links">
                            <li><a href="#">Hot deals</a></li>
                            <li><a href="#">Laptops</a></li>
                            <li><a href="#">Smartphones</a></li>
                            <li><a href="#">Cameras</a></li>
                            <li><a href="#">Accessories</a></li>
                        </ul>
                    </div>
                </div>

                <div class="clearfix visible-xs"></div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Information</h3>
                        <ul class="footer-links">
                            <li><a href="#">About Us</a></li>
                            <li><a href="#">Contact Us</a></li>
                            <li><a href="#">Privacy Policy</a></li>
                            <li><a href="#">Orders and Returns</a></li>
                            <li><a href="#">Terms & Conditions</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Service</h3>
                        <ul class="footer-links">
                            <li><a href="#">My Account</a></li>
                            <li><a href="#">View Cart</a></li>
                            <li><a href="#">Wishlist</a></li>
                            <li><a href="#">Track My Order</a></li>
                            <li><a href="#">Help</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>
    <!-- /top footer -->

    <!-- bottom footer -->
    <div id="bottom-footer" class="section">
        <div class="container">
            <!-- row -->
            <div class="row">
                <div class="col-md-12 text-center">
                    <ul class="footer-payments">
                        <li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
                        <li><a href="#"><i class="fa fa-credit-card"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
                    </ul>
                    <span class="copyright">
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</span>
                </div>
            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>
    <!-- /bottom footer -->
</footer>
<!-- /FOOTER -->

<!-- jQuery Plugins -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/slick.min.js"></script>
<script src="/js/nouislider.min.js"></script>
<script src="/js/jquery.zoom.min.js"></script>
<script src="/js/main.js"></script>
</body>
</html>