window.addEventListener("DOMContentLoaded",()=>{
    let UserData = JSON.parse(localStorage.getItem("UserData"));
    if(!UserData){
        window.location.href = "./Auth.html";
    }
})

let Ul = document.querySelector("nav ul")
window.addEventListener("DOMContentLoaded",()=>{
    let UserData = JSON.parse(localStorage.getItem("UserData"));
    if(UserData){
        Ul.innerHTML = `
            <li><a href="../Home/Home.html">Home</a></li>
            <li><a href="../Movies/Movies.html">Movies</a></li>
            <li><a href="../Auth/Profile.html">Profile</a></li>
           
        `
    }
    else
    {
        Ul.innerHTML = `
            <li><a href="../Home/Home.html">Home</a></li>
            <li><a href="../Auth/Auth.html">Sign In</a></li>
        `
    }
})