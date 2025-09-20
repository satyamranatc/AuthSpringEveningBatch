let Ul = document.querySelector("nav ul")
let token = ""
window.addEventListener("DOMContentLoaded",()=>{
    let UserData = JSON.parse(localStorage.getItem("UserData"));
    if(UserData){
        token = UserData.token;
        console.log(token);
        Ul.innerHTML = `
            <li><a href="../Home/Home.html">Home</a></li>
            <li><a href="../Movies/Movies.html">Movies</a></li>
            <li><a href="../Auth/Profile.html">Profile</a></li>
        `
    }
    else
    {
        window.location.href = "./Auth.html";
        Ul.innerHTML = `
            <li><a href="../Home/Home.html">Home</a></li>
            <li><a href="../Auth/Auth.html">Sign In</a></li>
        `
    }
})



function DiplayMovies(data)
{
    let Movies = document.getElementById("Movies");
    Movies.innerHTML = "";
    for (let i = 0; i < data.length; i++) {
        Movies.innerHTML += `
            <div class="movie">
                <h2>${data[i]}</h2>
            </div>
        `;
    }
}



async function getData() 
{
    let Res = await fetch("http://localhost:8080/api/topMovies",{
        method:"GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
    });
    console.log(Res);

    
}

getData()

