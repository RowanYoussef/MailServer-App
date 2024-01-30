<template>
        <div class="header__middle">
        <span class="material-icons"> 
            <button class="delete" @click="DeleteFolders()">Delete Folders</button>
            <button class="delete" @click="DeleteEmails()">Delete Folder Emails</button>
             </span>
        <span class="material-icons">
          </span>
          </div>
   <ul>
            <li v-for=" (folder,index) in Folders" :key="folder">
          <div class="folderRow" >
            <div class="folderRow__options" >
              <input type="checkbox" name="" id="" @change="AddSelected(index)"/>
              <span class="material-icons" @click="GetFolderEmails(index)"> View_Folder</span>
            </div>
            <h3 class="folderRow__title">{{ folder.foldername }}</h3>
            <button class="delete" @click="Rename(index)"> Rename Folder</button>
              <input type="text" placeholder="folder name" v-model="newName" class="name">
            </div>
            </li>
            </ul>
            <div class="emailList__list">
          <!-- Email Row Starts -->
          <ul>
            <li v-for=" (email,index) in Emails" :key="email">
          <div class="emailRow" >
            <div class="emailRow__options" >
              <input type="checkbox" name="" id="" @change="AddSelectedEmail(index)"/>
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
  Folders: [
    {
      foldername: "R",
      emails: [
        {
            sender:'fjjf',
            subject: 'jfj',
            priority: 'hh',
            date: 'yy',
            attachments:[
              {
                 attachment:['k'],
                 contentType: 'u',
                 filename: 'o',
              }
            ]
        }

      ],
    }
  ],
  Emails: [],
  username:'',
  SelectedMailIndex: 0,
  SelectedFolderIndex: 0,
  show: false,
  ids: [],
  names: [],
  newName: '',
}
},
methods : {
async getFolders() {
let y = x
  this.username=y;
  console.log(y);
  const response= await fetch("http://localhost:8085/getFolder/"+this.username,{method:'get'})
      let data = await response.json();
      this.Folders=data;

      
},
async GetFolderEmails(index){
    const response= await fetch("http://localhost:8085/GetFolderEmails/"+this.username+"/"+this.Folders[index].foldername,{method:'get'})
      let data = await response.json();
      this.Emails=data;
      this.SelectedFolderIndex=index;
},
async DeleteFolders(){
    let y = x
this.username=y;
console.log(this.names.length)
for(var i =0;i<this.names.length;i++){
console.log(this.names[i]);
fetch("http://localhost:8085/DeleteFolder/"+this.username+"/"+this.names[i],{method:'DELETE'});
this.Emails =[];
location.replace("http://localhost:8080/#/folders")
this.getFolders();
}
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
async Rename(index){
    let y = x
  this.username=y;
  fetch("http://localhost:8085/RenameFolder/"+this.username+"/"+this.Folders[index].foldername+"/"+this.newName,{method:'get'})
  location.replace("http://localhost:8080/#/Folders")
this.getFolders();

},
AddSelected(index) {
this.SelectedFolderIndex=index;
if(!this.names.includes(this.Folders[this.SelectedFolderIndex].foldername))
this.names.push(this.Folders[this.SelectedFolderIndex].foldername);
else{
for (var i = 0 ; i < this.names.length ; i++) {
if (this.names[i] == this.Folders[this.SelectedFolderIndex].foldername) {
 this.names.splice(i,1);

}
}
}
},
AddSelectedEmail(index) {
this.SelectedMailIndex=index;
if(!this.ids.includes(this.Emails[this.SelectedMailIndex].id))
this.ids.push(this.Emails[this.SelectedMailIndex].id);
else{
for (var i = 0 ; i < this.ids.length ; i++) {
if (this.ids[i] == this.Emails[this.SelectedMailIndex].id) {
 this.ids.splice(i,1);

}
}
}
},
async DeleteEmails(){
let y = x
this.username=y;
console.log(this.ids.length)
for(var i =0;i<this.ids.length;i++){
console.log(this.ids[i]);
fetch("http://localhost:8085/DeleteFolderEmail/"+this.username+"/"+this.Folders[this.SelectedFolderIndex].foldername+"/"+this.ids[i],{method:'DELETE'})
    
}
location.replace("http://localhost:8080/#/folders")
this.getFolders();
this.GetFolderEmails(this.SelectedFolderIndex);

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
this.getFolders();
console.log(this.Folders[0].foldername)
}

}
</script>

<style>
.folderRow__options {
display: flex;
align-items: center;
}

.folderRow__options .material-icons,
input {
margin: 5px;
}

.folderRow {
display: flex;
align-items: center;
height: 50px;
border-bottom: 3px solid red ;
cursor: pointer;
z-index: 999;
width: 60%;
}

.folderRow:hover {
border-top: 1px solid whitesmoke;
box-shadow: 0 4px 4px -2px rgba(0, 0, 0, 0.24);
}

.folderRow__message {
display: flex;
flex: 0.8;
align-items: center;
font-size: 13px;
}

.folderRow__message h4 {
width: 400px;
white-space: nowrap;
overflow: hidden;
text-overflow: ellipsis;
padding-left: 5px;
padding-right: 5px;
}

.folderRow__title {
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
.delete {
    color: white;
    background: red;
    cursor: pointer;
    height: 20px;
}
.name {
    width: 20%;
}
</style>