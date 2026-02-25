function logout() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/logout", true);
    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log("Logout successful");
            window.location.href = "/login";
        } else {
            console.error("Logout failed");
        }
    };
    xhr.send();
}

function updateComponent(componentName) {
    var iframe = document.getElementById("componentContainer");
    iframe.src = "/" + componentName; // Assume controller maps /Detection etc.
}
