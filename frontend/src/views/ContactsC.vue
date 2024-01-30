<template>
    <div class="header__middle">
  <span class="material-icons">  </span>
  <input type="text" placeholder="enter the contact name"  v-model="searchedValue"/>
  <span class="material-icons">
    </span>
  <span class="choice">
    <div><button class="search" @click="SearchContacts()"> search</button></div>
</span>
<span class="choice">
  <div><button class="search" @click="SortContacts()" >Sort</button></div>
</span>
<span><button class="search" @click="DeleteContacts()">Delete Contacts</button></span>
</div>
  <div class="emailList__list">
    <!-- Email Row Starts -->
    <ul>
      <li v-for=" (contact,index) in Contacts" :key="contact">
    <div class="emailRow" >
      <div class="emailRow__options" >
        <input type="checkbox" name="" id="" @change="AddSelected(index)"/>
        <span class="material-icons" @click="showNotification(index)"> View_contact</span>
      </div>
      <h3 class="emailRow__title">{{ contact.name }}</h3>
      <span><button class="search" @click="Addusername(index)">Add username</button>
            <input type="text" class="addusername" placeholder="add name" v-model="newName">
            <button class="search" @click="Rename(index)">Rename Contact</button>
        </span>
    </div>
   <div class="popup" v-if="show">
  <h3>Name: {{ this.Contacts[this.SelectedMailIndex].name }} </h3>
  <ul>
    <li v-for="user in Contacts[this.SelectedMailIndex].usernames" :key="user">
      <h4>{{ user }}-</h4>
    </li>
  </ul>
  <button class="close" @click="closeNotification">Close</button>
   </div>
  </li>
  </ul>
  </div>
</template>

<script>
import { x } from './LogIn.vue'
export default {
data(){
return {
  Contacts: [
    {
      name: "Rowan",
      usernames: [],
    }
  ],
  username:'',
  SelectedMailIndex: 0,
  show: false,
  searchedValue: '',
  ids: [],
  newName: '',
}
},
methods : {
async getContacts() {
let y = x
  this.username=y;
  console.log(y);
  const response= await fetch("http://localhost:8085/getContact/"+this.username,{method:'get'})
      let data = await response.json();
      this.Contacts=data;
      
},
async Addusername(index){
    let y = x
  this.username=y
  if(this.newName!='')
    fetch("http://localhost:8085/AddUsernameTocontact/"+this.username+"/"+this.Contacts[index].name+"/"+this.newName,{method:'get'})
    this.getContacts();
},
  showNotification(index) {
    this.SelectedMailIndex=index;
    this.show = true;
   // Close the notification after 10 seconds
  },
  closeNotification() {
    this.show = false;
    this.message = '';
  },
  async SearchContacts() {
    let y = x
  this.username=y;
    if(this.searchedValue!=''){
  const response= await fetch("http://localhost:8085/SearchContacts/"+this.username+"/"+this.searchedValue,{method:'get'})
      let data = await response.json();
      this.Contacts=data;
    }
      
},
async SortContacts() {
let y = x
  this.username=y;
  const response= await fetch("http://localhost:8085/SortContacts/"+this.username,{method:'get'})
      let data = await response.json();
      this.Contacts=data;

      
},
async Rename(index){
    let y = x
  this.username=y;
  fetch("http://localhost:8085/RenameContact/"+this.username+"/"+this.Contacts[index].name+"/"+this.newName,{method:'get'})
  location.replace("http://localhost:8080/#/contacts")
this.getContacts();

},
AddSelected(index) {
this.SelectedMailIndex=index;
if(!this.ids.includes(this.Contacts[this.SelectedMailIndex].name))
this.ids.push(this.Contacts[this.SelectedMailIndex].name);
else{
for (var i = 0 ; i < this.ids.length ; i++) {
if (this.ids[i] == this.Contacts[this.SelectedMailIndex].name) {
 this.ids.splice(i,1);

}
}
}
},
async DeleteContacts(){
let y = x
this.username=y;
console.log(this.ids.length)
for(var i =0;i<this.ids.length;i++){
console.log(this.ids[i]);
fetch("http://localhost:8085/DeleteContact/"+this.username+"/"+this.ids[i],{method:'DELETE'})
    
}
location.replace("http://localhost:8080/#/contacts")
this.getContacts();

}

},
mounted () {
this.getContacts();
}

}
</script>

<style>
.emailRow__options {
display: flex;
align-items: center;
}

.emailRow__options .material-icons,
input {
margin: 5px;
}

.emailRow {
display: flex;
align-items: center;
height: 50px;
border-bottom: 1px solid whitesmoke;
cursor: pointer;
z-index: 999;
}

.emailRow:hover {
border-top: 1px solid whitesmoke;
box-shadow: 0 4px 4px -2px rgba(0, 0, 0, 0.24);
}

.emailRow__message {
display: flex;
flex: 0.8;
align-items: center;
font-size: 13px;
}

.emailRow__message h4 {
width: 400px;
white-space: nowrap;
overflow: hidden;
text-overflow: ellipsis;
padding-left: 5px;
padding-right: 5px;
}

.emailRow__title {
font-size: 13px;
flex: 0.3;
}

.emailRow__time {
padding-right: 15px;
font-size: 10px;
font-weight: bold;
}

.emailRow__description {
font-weight: 400;
color: gray;
}
h3 {
color: #333;
}

.popup {
width: 400px;
background: white;
border-radius: 6px;
border-width: 6px;
border-style: solid ;
border-color: red;
position: absolute;
top: 50%;
left: 50%;
transform: translate(-50%,-50%);
text-align: center;
padding: 0 30px 30px;
color: #333;
z-index: 10;
line-break: anywhere;
}
popup h3{
  font-size: 38px;
  font-weight: 300;
  margin: 30px 0 10px;
}
.popup button {
  border-radius: 3px;
  width: 30%;
  margin-top: 50px;
  padding: 10px 0;
  background: red;
  color: white;
  border: 0;
  outline: none;
  font-size: 18px;
  cursor: pointer;
}
.popup p{
  line-break: anywhere;
}
.header__middle {
display: flex;
flex: 0.7;
align-items: center;
background-color: whitesmoke;
padding: 10px;
border-radius: 5px;
width: 100%;
}

.material-icons {
color: gray;
}

.header__middle input {
border: none;
width: 50%;
padding: 10px;
outline: none;
font-size: medium;
background-color: transparent;
}
.search {
background: red;
color: aliceblue;
border-radius: 3px;
cursor: pointer;
}
.addusername {
    height: 5px;
    width: 80px;
}
</style>
