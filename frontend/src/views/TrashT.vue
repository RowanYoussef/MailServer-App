<template>
  <div class="header__middle">
<span class="material-icons">  </span>
<input type="text" placeholder="enter the search or filter key"  v-model="searchedValue"/>
<span class="material-icons">
</span>
<span class="choice">
<div><button class="search" @click="SearchTrash()"> search</button></div>
<label for="priority_select" > Search:</label>
<select id="priority_select" v-model="selectedValue" >
<option value="priority" >priority</option>
<option value="sender">sender</option>
<option value="receiver" >receiver</option>
<option value="subject">subject</option>
<option value="body">body</option>
</select></span>
<span class="choice">
<div><button class="search" @click="FilterTrash()"> Filter</button></div>
<label for="priority_select" > Filter:</label>
<select id="priority_select" v-model="filtredValue" >
<option value="Sender">sender</option>
<option value="Subject">subject</option>
</select>
</span>
<span class="choice">
<div><button class="search" @click="SortTrash()">Sort</button></div>
<label for="priority_select" > Sort:</label>
<select id="priority_select" v-model="sortedValue" >
<option value="Sender">sender</option>
<option value="Subject">subject</option>
<option value="Receiver">receiver</option>
<option value="Date">Date</option>
<option value="Body">body</option>
<option value="Priority">priority</option>
</select>
</span>
</div>
<div class="emailList__list">
<!-- Email Row Starts -->
<ul>
<li v-for=" (email,index) in Emails" :key="email">
<div class="emailRow" >
<div class="emailRow__options"  >
<input type="checkbox" name="" id="" />
<span class="material-icons" @click="showNotification(index)"> View_Email</span>
</div>

<h3 class="emailRow__title">{{ email.sender }}</h3>

<div class="emailRow__message">
<h4>
{{ email.subject }}
<span class="emailRow__description"> - {{ email.priority }} </span>
</h4>
</div>
<p class="emailRow__time">{{ email.date }}</p>
</div>
<div class="popup" v-if="show">
<h3>From: {{ this.Emails[this.SelectedMailIndex].sender }} </h3>
<h3>about: {{ this.Emails[this.SelectedMailIndex].subject }}</h3>
<p>message: {{ this.Emails[this.SelectedMailIndex].body }}</p>
<button @click="downLoad()" v-if="this.Emails[this.SelectedMailIndex].attachments.length!==0" style="width:180px">Open Attachment</button>
<button class="close" @click="closeNotification">Close</button>
<button class="Restore" @click="Restore(this.Emails[this.SelectedMailIndex].id)">Restore</button>
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
Emails: [
{
sender: "Rowan",
subject: "study",
body: "message",
id:"id",
attachments:[
  {
     attachment:['k'],
     contentType: 'u',
     filename: 'o',
  }
]
}
],
username:'',
SelectedMailIndex: 0,
show: false,
searchedValue: '',
selectedValue: '',
filtredValue: '',
sortedValue: '',

}
},
methods : {
async getTrash() {
let y = x
this.username=y;
console.log(y);
const response= await fetch("http://localhost:8085/Trash/"+this.username,{method:'get'})
let data = await response.json();
this.Emails=data;
},
async Restore(id) {
let y = x
this.username=y;
fetch("http://localhost:8085/Restore/"+this.username+"/"+id,{method:'get'})
location.replace("http://localhost:8080/#/Trash")
this.getTrash();

},
showNotification(index) {
this.SelectedMailIndex=index;
this.show = true;
},
closeNotification() {
this.show = false;
this.message = '';
},
async SearchTrash() {
let y = x
this.username=y;
if(this.selectedValue!=''){
const response= await fetch("http://localhost:8085/Search/Trash/"+this.username+"/"+this.selectedValue+"/"+this.searchedValue,{method:'get'})
let data = await response.json();
this.Emails=data;
}

},
async FilterTrash() {
let y = x
this.username=y;
if(this.searchedValue!=''){
const response= await fetch("http://localhost:8085/Filter/Trash/"+this.username+"/"+this.filtredValue+"/"+this.searchedValue,{method:'get'})
let data = await response.json();
this.Emails=data;
}

},
async SortTrash() {
let y = x
this.username=y;
if(this.selectedValue!=''){
const response= await fetch("http://localhost:8085/Sort/Trash/"+this.username+"/"+this.sortedValue+"/true",{method:'get'})
let data = await response.json();
this.Emails=data;
}  

},
downLoad(){
const base64EncodedString = this.Emails[this.SelectedMailIndex].attachments[0].attachment; 


const uint8Array = new Uint8Array(atob(base64EncodedString).split('').map(char => char.charCodeAt(0)));

const blob = new Blob([uint8Array],{ type: this.Emails[this.SelectedMailIndex].attachments[0].contentType });


const link = document.createElement('a');
link.href = window.URL.createObjectURL(blob);


link.download = 'original_file';


document.body.appendChild(link);
link.click()
}


},
mounted () {
this.getTrash();
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
margin-right: 10px;
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
</style>