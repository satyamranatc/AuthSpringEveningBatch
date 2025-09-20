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

    let Res = await fetch("http://localhost:8080/api/auth/login",{
        method:"POST",
        headers: {
            "Content-Type": "application/json"
        },
        body:JSON.stringify(loginData)
    })
    Res = await Res.json();
    console.log(Res)
    
    if (Res.userData)
    {
       
           let UserData = {
             "userInfo":Res.userData,
            "token":Res.token
        }
        localStorage.setItem("UserData",JSON.stringify(UserData));
        // window.location.href = "./Profile.html";
    }
    else
    {
        alert("Invalid username or password!");
    }

    
})


SignUpForm.addEventListener("submit",async (e)=>{
    e.preventDefault();

    let signUpData = {
        avatar: document.getElementById("avatar").value,
        fullName: document.getElementById("fullName").value,
        age: document.getElementById("age").value,
        username: document.getElementById("S_username").value,
        password: document.getElementById("S_password").value
    }

    console.log(signUpData);

    let Res = await fetch("http://localhost:8080/api/auth/register",{
        method:"POST",
        headers: {
            "Content-Type": "application/json"
        },
        body:JSON.stringify(signUpData)
    })
    Res = await Res.json();
    console.log(Res);

   if (Res.userData)
    {
           
        let UserData = {
             "userInfo":Res.userData,
            "token":Res.token
        }
        localStorage.setItem("UserData",JSON.stringify(UserData));
        window.location.href = "./Profile.html";
    }
    else
    {
        alert("Invalid Credentials!");
    }

    
})