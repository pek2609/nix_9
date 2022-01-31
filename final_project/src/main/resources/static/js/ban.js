// function changeButtons() {
//     let ban = document.getElementById("ban");
//     let unban = document.getElementById("unban");
//     banButton(ban, unban)
//
//     if ($(ban).is(':hidden')) {
//         ban.setAttribute("type", "hidden")
//         unban.setAttribute("type", "button")
//     }
//     if (unban.getAttribute("type") === "button") {
//         unban.setAttribute("type", "hidden")
//         ban.setAttribute("type", "button")
//     }
// }

function banButtons() {
    let ban = document.getElementById("ban");
    let unban = document.getElementById("unban");
    ban.style.display = "none";
    unban.style.display = "block";
}

function unbanButtons() {
    let ban = document.getElementById("ban");
    let unban = document.getElementById("unban");
    ban.style.display = "block";
    unban.style.display = "none";
}