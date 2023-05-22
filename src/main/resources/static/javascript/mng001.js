function onSubmitLoginPage() {
           var usernameValue = document.getElementById("username").value;
           var passwordValue = document.getElementById("password").value;
           var errorMessage = document.getElementById("errorMessage");
           if (usernameValue.trim() ===  "" && passwordValue.trim() !== "") {
               errorMessage.innerText = "「アカウント名」を入力してください";
               return false;
           } else if (passwordValue.trim() === "" & usernameValue.trim() !== "") {
               errorMessage.innerText = "「パスワード」を入力してください";
               return false;
           }
           else if (usernameValue.trim() ===  "" && passwordValue.trim() === "") {
               errorMessage.innerText = "「アカウント名」または「パスワード」を入力してください";
               return false;
           }
           else {
               errorMessage.innerText = "";
               return true;
           }
}