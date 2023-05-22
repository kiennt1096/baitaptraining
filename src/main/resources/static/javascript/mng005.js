function showConfirm(userid) {
    var result = confirm("削除しますが、よろしいでしょうか。?");
    if(result) {
        var url = "/deleteUser/" + userid;
        window.location.href = url;
        alert("OK");

    }
    else alert("Không xóa thì thôi!");
}

function back() {
    window.history.back();
}

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