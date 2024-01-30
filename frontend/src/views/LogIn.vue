<template>
  <div class="LogIn">
    <div class="login-form">
        <h1>Log in</h1>
        <form action="" method="get">
            <div>
                <p><label for="Username">Username*</label></p>
                <input type="Username" id="username" name="username" required autocomplete="off">
                <p><label for="password">Password*</label></p>
                <input type="password" id="pass" required>
            </div>
            <div>{{ condition }}</div>
            <button type="button" id="btn" @click="storeData();login();">Log in</button>
            <div class="donthave">Doesn't Have An Account? <router-link to="/SignUp">SignUp</router-link></div>
        </form>
        <div></div>
    </div>
    <!-- Right section of the form containing image -->
</div>
</template>
<script>
var x 
export { x }
export default ({
    data(){
      return{
        fromBack:"",
        username:"",
        password:"",
        condition:""
      };
    },
      methods:{
          storeData(){
            this.username = document.getElementById("username").value
            this.password = document.getElementById("pass").value
            x= this.username
            console.log(x);
          },
  
          login(){
            x=this.username
            fetch("http://localhost:8085/login", {
             method: "post",
             headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
  
            //make sure to serialize your JSON body
            body: JSON.stringify({
              usernameOrEmail: this.username,
              password: this.password
            })
          })
          .then(response => {
              return response.text();
            })
            .then(data => {
              this.fromBack = data;
              if(this.fromBack!=="loginSuccess"){
                this.condition=this.fromBack;
              }
              else{
                   location.replace("http://localhost:8080/#/")
                  }
            });
          
       },
      },
      getusername(){
      return this.username;
      },
      expose: ['getusername'],
  })
  </script>

<style>
.LogIn{
   width: 100vw;
    height: 100vh;
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
    font-family: sans-serif;
    background: linear-gradient(to top, pink , rgb(205, 215, 222) 100%);
    overflow: hidden;
}

.login-form{
    display: flex;
    flex-direction: column;
    text-align: center;
    position: fixed;
    top: 50%;
    left: 50%;
    width: 500px;
    transform: translate(-50%, -50%);
    padding: 25px;
    border-radius: 10px;
    background: linear-gradient(to top, rgba(140, 164, 183, 0.5) , rgb(180, 70, 167) 100%);
}
#username-error, #password-error{
    margin-top: 0px;
    width: 100%;
    font-size: 18px;
    color: #C62828;
    background: rgba(239, 76, 95, 0.248);
    text-align: center;
    padding: 5px;
    border-radius: 3px;
    border: 1px solid #f62525;
    display: none;
    margin-top: -7px;
}
p{
    font-size: small;
    text-align: left;
    font-weight: bold;
    color: black;
}
label{
    text-transform: uppercase;
    font-size: 12px;
    color: rgb(48, 48, 48);
}
.donthave{
    padding-top: 3px;
    font-size: small;
    font-weight: bold;
    color: black;
}
input{
    border: none;
    width: 100%;
    border-bottom: 1px solid lightgray;
    background-color: #FBFBFB;
    position: relative;
    top: -10px;
    padding: 5px;
}
input:focus {
    outline: none !important;
    border-bottom: 1px solid red;
    font-size: 14px;
    font-weight: bold;
}
#password:focus{
    color: red;
    font-size: 16px;
    font-weight: bold;
}
#btn{
    border: none;
    background-color: rgb(44, 14, 114);
    border-radius: 20px;
    padding: 10px;
    color: white;
    width: 120px;
    margin-top: 15px;
}
#btn:hover{
    box-shadow: 0 0 25px rgba(8, 8, 8, 0.2);
    background-color: rgb(89, 51, 176);
}
</style>