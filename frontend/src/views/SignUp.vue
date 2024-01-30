<template>
  <div class="SignUp">
    <div class="signup-form">
        <h1>Sign Up</h1>
        <form action="" method="get">
            <div>
                <p><label for="fname">First name</label></p>
                <input type="text" id="fname" autocomplete="off" />
            </div>
            <div>
                <p><label for="lname">last name</label></p>
                <input type="text" id="lname" autocomplete="off" />
            </div>
            <div>
                <p><label for="UserName">Username*</label></p>
                <input type="text" id="username" autocomplete="off" />
            </div>
            <div>
                <p><label for="Email">Email</label></p>
                <input type="text" id="email" autocomplete="off" />
            </div>
            <div id="user-error">Your username should contain at least 4 characters/symbols</div>
            <div>
                <p><label for="password">Password*</label></p>
                <input type="password" id="pass" required />
            </div>
            <div>
                <p><label for="password">Confirm Password*</label></p>
                <input type="password" id="confPass" required />
            </div>
            <div id="password-error">Your password should contain at least 8 characters/symbols</div>
            <div>{{ condition }}</div>
            <button type="button" id="btn"  @click="storeData();signUp()">Sign Up Now</button>
            <div class="alreadyhave">Already have an account? <router-link to="/LogIn">LogIn</router-link></div>
        </form>
        <div></div>
    </div>
</div>
</template>
<script>
export default ({
  data(){
    return{
      fromBack:"",
      firstName:"",
      lastName:"",
      username:"",
      email:"",
      password:"",
      confPass:"",
      condition:""
    };
  },
    methods:{

        storeData(){
          this.firstName = document.getElementById("fname").value
          this.lastName = document.getElementById("lname").value
          this.username = document.getElementById("username").value
          this.email = document.getElementById("email").value
          this.password = document.getElementById("pass").value
          this.confPass = document.getElementById("confPass").value
          console.log(this.firstName);
        },

        signUp(){
          fetch("http://localhost:8085/signup", {
           method: "post",
           headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },

          //make sure to serialize your JSON body
          body: JSON.stringify({
            firstname: this.firstName,
            lastname:this.lastName,
            username: this.username,
            email: this.email,
            password: this.password,
            passConfirm: this.confPass
          })
        })
        .then(response => {
            return response.text();
          })
          .then(data => {
            this.fromBack = data;
            if(this.fromBack!=="signUpSuccess"){
              this.condition=this.fromBack;
            }
            else{
              location.replace("http://localhost:8080/#/")
            }
          });

     },
    }
})
</script>
<style scoped>
.SignUp{
    width: 100vw;
    height: 100vh;
    margin: 0px;
    padding: 0px;
    box-sizing: border-box;
    font-family: sans-serif;
    background: linear-gradient(to top,pink , rgb(205, 215, 222) 100%);
    overflow: hidden;
}
.signup-form{
    display: flex;
    flex-direction: column;
    position: fixed;
    text-align: center;
    top: 50%;
    left: 50%;
    width: 500px;
    transform: translate(-50%, -50%);
    padding: 25px;
    border-radius: 10px;
    /* background: #FBFBFB; */
    background: linear-gradient(to top, rgba(140, 164, 183, 0.5) , rgb(180, 70, 167) 100%);
}
#user-error, #password-error{
    margin-top: 0px;
    width: 100%;
    font-size: 18px;
    color: #C62828;
    background: rgba(239, 76, 95, 0.248);
    text-align: center;
    padding: 5px;
    border-radius: 3px;
    border: 1px solid #f62525;
    margin-top: -7px;
    display: none;
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
    text-align: left;
}
.alreadyhave{
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
    background-color: rgb(44, 14, 114);;
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

