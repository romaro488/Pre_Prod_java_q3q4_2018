<div class="row main-top-w3l py-2">
    <div class="col-lg-3 header-most-top">
        <form id="signInForm" method="post" action="login">
            <div class="form-group row">
                <div class="col-sm-5">
                    <input type="text" name="login" id="login" class="form-control form-control-sm" id="colFormLabelSm" placeholder="Login" required>
                </div>
                <div class="col-sm-5">
                    <input type="password" name="password" id="passwordSignIn" class="form-control form-control-sm" id="colFormLabelSm" placeholder="Password" required>
                </div>
                <div class="col-sm-2">
                    <button type="submit" id="signInButton" class="btn btn-sm btn-primary custom-btn-class mb-2">Sign In</button>
                </div>
            </div>
        </form>
        <c:if test="${not empty signInError}">
            <span style="color: white;">${signInError.signInError}</span>
        </c:if>
    </div>
    <div class="col-lg-9 header-right mt-lg-0 mt-2">
        <!-- header lists -->
        <ul class="text-right border-right text-white">
            <li class="text-center border-right text-white">
                <a class="play-icon popup-with-zoom-anim text-white" href="#small-dialog1">
                    <i class="fas fa-globe mr-2"></i>Select language</a>
            </li>
            <li class="text-center text-white">
                <a href="register" class="text-white">
                    <i class="fas fa-registered mr-2"></i>Registration </a>
            </li>
        </ul>
        <!-- //header lists -->
    </div>
</div>