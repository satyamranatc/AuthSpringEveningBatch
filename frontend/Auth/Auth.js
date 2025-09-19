window.addEventListener("DOMContentLoaded",()=>{
    let UserData = JSON.parse(localStorage.getItem("UserData"));
    if(UserData){
        window.location.href = "./Profile.html";
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

let LoginForm = document.getElementById("LoginForm");
let SignUpForm = document.getElementById("SignUpForm");



LoginForm.addEventListener("submit",async (e)=>{
    e.preventDefault();

    let loginData = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value
    }

    console.log(loginData);

    let Res = fetch("http://localhost:8080/login",{
        method:"POST",
        headers: {
            "Content-Type": "application/json"
        },
        body:JSON.stringify(loginData)
    })

    localStorage.setItem("UserData",JSON.stringify(loginData));

    window.location.href = "./Profile.html";

    
})