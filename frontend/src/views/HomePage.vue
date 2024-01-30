<template>
     <div class="header">
      <div class="header__left">
        <span class="material-icons"> {{ username }} </span>
        <img
          src="https://i.pinimg.com/originals/ae/47/fa/ae47fa9a8fd263aa364018517020552d.png"
          alt=""
        />
      </div>
    </div>
    <!-- Header Ends -->

    <!-- Main Body Starts -->
    <div class="main__body">
      <!-- Sidebar Starts -->
      <div class="sidebar">
        <button class="sidebar__compose"  @click="goToCompose()"><span class="material-icons"> add </span>Compose</button>
        <div class="sidebarOption">
          <button class="material-icons" @click="goToInbox()"> inbox </button>
          <h3>Inbox</h3>
        </div>
        <div class="sidebarOption">
          <span class="material-icons" @click="goToInSent()">Sent</span>
          <h3>Sent</h3>
        </div>

        <div class="sidebarOption">
          <span class="material-icons" @click="goToInDraft()">Drafts</span>
          <h3>Drafts</h3>
        </div>

        <div class="sidebarOption">
          <span class="material-icons" @click="goToInTrash()">Trash</span>
          <h3>Trash</h3>
        </div>

        <div class="sidebarOption">
          <span class="material-icons" >Add Contact</span>
          <h3><input type="text" class="contact" v-model="conname"></h3>
          <button class="Add" @click="addContact()">+</button>
        </div>

        <div class="sidebarOption">
          <span class="material-icons" @click="goToInContacts()">Your Contacts</span>
        </div>

        <div class="sidebarOption">
          <span class="material-icons" >Create Folder</span>
          <h3><input type="text" class="contact" v-model="Foldername"></h3>
          <button class="Add" @click="CreateFolder()">+</button>
        </div>

        <div class="sidebarOption">
          <span class="material-icons" @click="goToInFolders()">Your Folders</span>
        </div>

        <div class="sidebar__footer">
          <div class="sidebar__footerIcons">
          </div>
        </div>
        <div class="sidebarOption">
          <div><button class="material-icons" @click="logout">LOG OUT</button></div>
        </div>
      </div>
      <!-- Sidebar Ends -->

      <!-- Email List Starts -->
      <div class="emailList">
        <!-- Settings Starts -->
        <div class="emailList__settings">
        </div>
        <!-- Settings Ends -->

        <!-- Section Starts -->
        <div class="emailList__sections">
          <div class="section section__selected">
            <span class="material-icons"> </span>
            <h4>Primary</h4>
          </div>

          <div class="section">
            <span class="material-icons"></span>
            <h4>Sender</h4>
          </div>

          <div class="section">
            <span class="material-icons">  </span>
            <h4>Subject</h4>
          </div>
        </div>
        <!-- Section Ends -->

        <!-- Email List rows starts -->
        <router-view></router-view>
        <!-- Email List rows Ends -->
      </div>
      <!-- Email List Ends -->
    </div>
  </template>
  
  <script>
  import { x } from './LogIn.vue'
  // @ is an alias to /src
  export default {
    name: 'HomePage',
    data (){
     return {
      Emails: [
        {
          
          }
      ],
      username: '',
      conname: '',
      Foldername: '',
     }
    },
    components: {
     
  
    },
    methods: {
     logout(){
      location.replace("http://localhost:8080/#/LogIn")
     },
     goToCompose(){
      location.replace("http://localhost:8080/#/compose")
     },
     goToInbox(){
      location.replace("http://localhost:8080/#/Inbox")
     },
     goToInSent(){
      location.replace("http://localhost:8080/#/sent")
     },
     goToInTrash(){
      location.replace("http://localhost:8080/#/trash")
     },
     goToInDraft(){
      location.replace("http://localhost:8080/#/Draft")
     },
     goToInContacts(){
      location.replace("http://localhost:8080/#/contacts")
     },
     goToInFolders(){
      location.replace("http://localhost:8080/#/folders")
     },
     async addContact(){
      let y =x;
    this.username=y;
       if(this.conname!='')
        fetch("http://localhost:8085/AddContact/"+this.username+"/"+this.conname,{method:"get"})
        this.conname="";
     },
     async CreateFolder(){
      let y =x;
    this.username=y;
       if(this.Foldername!='')
        fetch("http://localhost:8085/CreateFolder/"+this.username+"/"+this.Foldername,{method:"get"})
        this.Foldername="";
     },
    mounted (){
    let y =x;
    this.username=y;
    console.log(this.username)
    },
    }
  }
  </script>
  <style scoped>
  * {
  margin: 0;
  padding: 0;
}

/* Header */
.header__left img {
  object-fit: contain;
  height: 80px;
  margin-left: 5px;
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid whitesmoke;
}

.header__left {
  display: flex;
  align-items: center;
}

.header__left span {
  padding: 10px;
  cursor: pointer;
}

.header__middle {
  display: flex;
  flex: 0.7;
  align-items: center;
  background-color: whitesmoke;
  padding: 10px;
  border-radius: 5px;
}

.material-icons {
  color: gray;
}

.header__middle input {
  border: none;
  width: 100%;
  padding: 10px;
  outline: none;
  font-size: medium;
  background-color: transparent;
}

.header__right {
  display: flex;
  padding-right: 20px;
}

.header__right span {
  padding: 10px;
  cursor: pointer;
}

/* Sidebar */
.sidebar {
  flex: 0.3;
  max-width: 300px;
  padding-right: 20px;
}

.sidebar__compose {
  margin-top: 15px;
  margin-left: 10px;
  margin-bottom: 15px;
  text-transform: capitalize;
  color: gray;
  padding: 15px;
  border-radius: 30px;
  background-color: white;
  display: flex;
  align-items: center;
  cursor: pointer;
  outline: none;
  border: none;
  box-shadow: 0px 2px 5px -2px rgba(0, 0, 0, 0.75);
}

.sidebar__compose span {
  margin-right: 5px;
}

.sidebarOption {
  display: flex;
  align-items: center;
  height: 40px;
  padding: 0 10px;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
  cursor: pointer;
  color: #818181;
}

.sidebarOption .material-icons {
  padding: 5px;
}

.sidebarOption h3 {
  flex: 1;
  margin-left: 10px;
  font-size: 14px;
  font-weight: 400;
}

.sidebarOption:hover,
.sidebarOption:hover h3,
.sidebarOption:hover .material-icons,
.sidebarOption__active,
.sidebarOption__active h3,
.sidebarOption__active .material-icons {
  background-color: #fcecec;
  color: #c04b37;
  font-weight: 800;
}

.sidebar__footer {
  display: flex;
  justify-content: center;
}

.sidebar__footerIcons .material-icons {
  margin: 7px;
  cursor: pointer;
}

/* main body */
.main__body {
  display: flex;
  height: 100vh;
}

body {
  height: 100vh;
  overflow-y: hidden;
}

/* email list */
.emailList {
  flex: 1;
  overflow: scroll;
}

.emailList__settings {
  position: sticky;
  top: 0;
  display: flex;
  justify-content: space-between;
  border-bottom: 1px solid whitesmoke;
  background-color: white;
  padding: 8px;
}

.emailList__settingsLeft {
  display: flex;
  align-items: center;
}

.emailList__settings .material-icons {
  margin: 5px;
  cursor: pointer;
}

.section {
  display: flex;
  align-items: center;
  border-bottom-width: 2px;
  padding: 15px;
  min-width: 200px;
  cursor: pointer;
  color: grey;
  border-width: 0;
}

.emailList__sections {
  position: sticky;
  top: 0;
  display: flex;
  background-color: white;
  border-bottom: 1px solid whitesmoke;
}

.section__selected {
  background-color: whitesmoke;
  border-width: 3px;
  color: red;
  border-bottom: 3px solid red;
}

.section__selected .material-icons {
  color: red;
}

.section:hover {
  background-color: whitesmoke;
  border-width: 3px;
}

.section h4 {
  font-size: 14px;
  margin-left: 15px;
}

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
button {
  border: none;
  background: white;
}
.contact{
  width: 70%;
}
.Add{
 font-size: 30px;
 cursor: pointer;
}
  </style>