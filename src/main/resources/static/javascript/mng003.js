function toggleDiv() {
    var div = document.getElementById("japaneseleveldiv");
    if (div.style.display === "none") {
    console.log(div.style.display);
      div.style.display = "block";
    } else {
    console.log(div.style.display);
      div.style.display = "none";
    }
  }

function onSubmitSignUpPage() {
    console.log("AAAAAAAAAAAAAAAAA");

    var username = document.getElementById("loginName").value;
    if (username.trim() === "") loginNameM.innerText = "「アカウント名」を入力してください";


    var fullName = document.getElementById("fullName").value;
    if (fullName.trim() === "") fullNameM.innerText = "「氏名」を入力してください";


    var fullNameKana = document.getElementById("fullNameKana").value;
    if (fullNameKana.trim() === "") fullNameKanaM.innerText = "「カタカナ氏名」を入力してください";


    var email = document.getElementById("email").value;
    if (email.trim() === "") emailM.innerText = "「メールアドレス」を入力してください";


    var tel = document.getElementById("tel").value;
    if (tel.trim() === "") telM.innerText = "「電話番号」を入力してください";


    var password = document.getElementById("password").value;
    if (password.trim() === "") passwordM.innerText = "「パスワード」を入力してください";

    var password2 = document.getElementById("password2").value;
    if (password.trim() !== password2.trim()) passwordM2.innerText = "「パスワード（確認)」が不正です。";



    if (username.trim() === "" || fullName.trim() === "" || fullNameKana.trim() === "" || email.trim() === "" || tel.trim() === "" || password.trim() === "" || (password.trim() !== password2.trim())) {
        return false;
    }
    else return true;
}

function back() {
    window.history.back();
}

function directToTop() {
    window.location.href = "/top";
}