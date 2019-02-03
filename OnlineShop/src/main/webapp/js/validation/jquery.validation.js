const lettersRegex = /^[A-Za-z\u0400-\u04FF]+$/;
const emailRegex = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
const passRegex = /^[a-zA-Z\d\s\u0400-\u04FF]{8,}$/;

const fnameErrorMsg = "First name must have alphabet characters only!";
const lnameErrorMsg = "Last name must have alphabet characters only!";
const emailErrorMsg = "You have entered an invalid email address! Please enter valid email address, like: hello@gmail.com";
const passwordErrorMsg = " Password should contains more then 8 symbol!";
const confirmPasswordsErrorMsg ="Password confirm should not be empty / should be equals with Password!" ;

$(document).ready(
    $("#registration-submit").click(
        function() {

            var fname = $("input[name*='user-fname']").val();
            var lname = $("input[name*='user-lname']").val();
            var email = $("input[name*='user-email']").val();
            var password = $("input[name*='user-password']").val();
            var confirmPassword = $("input[name*='confirm-password']").val();

            validate(fname,lettersRegex,"#fname-error-msg",fnameErrorMsg);
            validate(lname,lettersRegex,"#lname-error-msg",lnameErrorMsg);
            validate(email,emailRegex,"#email-error-msg",emailErrorMsg);
            validate(password,passRegex,"#password-error-msg",passwordErrorMsg);
            validate(confirmPassword,password,"#password-confirm-error-msg",confirmPasswordsErrorMsg);

        }
    ));

function validate(field, regex, lableId, errorMsg) {

    var msgLable = $(lableId);

    if(!field.match(regex)|| field.length == 0) {
        msgLable.css('color', '#cc0000');
        msgLable.text(":  " + errorMsg);
        msgLable.focus();
        event.preventDefault();
    } else {
        msgLable.css('color', '#009900');
        msgLable.html(": &#10003");
    } 
}