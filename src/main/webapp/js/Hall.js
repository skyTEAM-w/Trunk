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
    var iframe = document.getElementById("componentContainer");
    iframe.src = componentName + ".jsp";

}