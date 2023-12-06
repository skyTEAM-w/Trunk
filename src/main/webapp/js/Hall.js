function logout() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "Logout", true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log("Logout successful");
            window.location.href = "Login.jsp";
        } else {
            console.error("Logout failed");
        }
    };
    xhr.send();
}

function updateComponent(componentName) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById(componentName).innerHTML = this.responseText;
        }
    }
    xhr.open("GET", "HallServlet?componentName=" + componentName, true)
    xhr.send();
}