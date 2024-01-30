<template>
            <div class="header__middle">
        <span class="material-icons">  </span>
        <input type="text" placeholder="enter the search or filter key"  v-model="searchedValue"/>
        <span class="material-icons">
          </span>
        <span class="choice">
          <div><button class="search" @click="SearchDraft()"> search</button></div>
          <label for="priority_select" > Search:</label>
      <select id="priority_select" v-model="selectedValue" >
        <option value="priority" >priority</option>
        <option value="sender">sender</option>
        <option value="receiver" >receiver</option>
        <option value="subject">subject</option>
        <option value="body">body</option>
      </select></span>
      <span class="choice">
        <div><button class="search" @click="FilterDraft()"> Filter</button></div>
          <label for="priority_select" > Filter:</label>
      <select id="priority_select" v-model="filtredValue" >
        <option value="Sender">sender</option>
        <option value="Subject">subject</option>
      </select>
      </span>
      <span class="choice">
        <div><button class="search" @click="SortDraft()">Sort</button></div>
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
      <span class="choice">
      <label for="priority_select" > order:</label>
      <select id="priority_select" v-model="order" >
        <option value="assending">assending</option>
        <option value="dessending">dssending</option>
      </select>
      </span>
      </div>
        <div class="emailList__list">
          <!-- Email Row Starts -->
          <ul>
            <li v-for=" (email,index) in Emails" :key="email">
          <div class="emailRow" >
            <div class="emailRow__options" @click="showNotification(index)" >
              <input type="checkbox" name="" id="" />
              <span class="material-icons"> View_Email</span>
            </div>

            <h3 class="emailRow__title">{{ email.sender }}</h3>

            <div class="emailRow__message">
              <h4>
                {{ email.subject }}
                <span class="emailRow__description"> - {{ email.id }} </span>
              </h4>
            </div>
            <p class="emailRow__time">{{ email.date }}</p>
          </div>
         <div class="popup" v-if="show">
            <div class="send-mail-window">
    <div class="new-message-label" style="margin-left: 0">
      <span>New message</span>
      <span class="att"><button  @click="AddReceivers();Draft(this.SelectedMailIndex)">&#128503;</button></span>
      <button><i class="fa-solid fa-circle-xmark"></i></button>
    </div>
  
    <div id = "recievers-box" style="margin-left: 0px;">
      <div class="to" id = "to">
        <div>
          <span>To:</span>
          <input type="text" id="1-reciever" v-model="reciever" />
        </div>
      </div>
      <button id = "add-reciever-button" @click="AddReceivers()" >+ reciever</button>
    </div>
  
    <div id="more-recievers">
      <span v-for="rece in receivers" :key="rece">{{ rece }}-</span>
    </div>
  
    <div class="subject">
      <span>Subject:</span>
      <input type="text" id="subject" v-model="subject" />
    </div>
    <div id="Priority">
      <label for="priority_select" > Choose Your priority:</label>
      <select id="priority_select" >
        <option value="So Important" >So Important</option>
        <option value="Important">Important</option>
        <option value="Default" selected>Default</option>
        <option value="Low">Low</option>
      </select>
    </div>
    <div id="Attachments">
      <label for="attachment">Select attachments:</label>
      <input
        type="file"
        id="attachment"
        @change="handleFile()"
      />
    </div>
    <div class="Message">
      <label for="message">Enter Your Message: </label>
      <textarea id="message" rows="8" cols="75" v-model="body"  ></textarea>
    </div>
    <div class="message-options">
      <button type="button" @click=" AddReceivers();SendToBack(this.SelectedMailIndex)" >
        <i class="fa fa-light fa-paper-plane"></i> Send E-mail
      </button>
    </div>
  </div>
         </div>
        </li>
        </ul>
        </div>
</template>

<script>
import axios from 'axios';
 import { x } from './LogIn.vue'
export default {
   data(){
    return {
        Emails: [
          {
            sender: "Rowan",
            subject: "study",
            body: "message",
            receiver: "rowan",
          }
       ],
        username:'',
        SelectedMailIndex: 0,
        show: false,
        sender:"RowanYoussef",
        reciever:"",
        subject:"",
        body:"",
        date:"",
        priority:"",
        id:0,
        fromBack: "",
        condition:"",
        searchedValue: '',
        selectedValue: '',
        filtredValue: '',
        sortedValue: '',
        receivers:[],
        order: '',
        file: null,
        
          }
   },
   methods : {
    async getDraft() {
      let y = x
        this.username=y;
        console.log(y);
        const response= await fetch("http://localhost:8085/Draft/"+this.username,{method:'get'})
            let data = await response.json();
            this.Emails=data;
          
    },
        showNotification(index) {
          this.SelectedMailIndex=index;
          this.show = true;
          this.reciever=this.Emails[this.SelectedMailIndex].receiver;
          this.subject=this.Emails[this.SelectedMailIndex].subject;
          this.body=this.Emails[this.SelectedMailIndex].body;
         // Close the notification after 10 seconds
        },
        closeNotification() {
          this.show = false;
          this.message = '';
        },
        sendFromDraft(index){
          let y = x
        this.username=y;
        this.sender=y;
        const d = new Date();
        for(var i =0;i<this.receivers.length;i++){
        this.date=d.getDate()+"-"+(parseInt(d.getMonth())+1).toString()+"-"+d.getFullYear();
        this.id=Math.random().toString();
        console.log(this.Sender);
        fetch("http://localhost:8085/Send", {
             method: "post",
             headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
  
            //make sure to serialize your JSON body
            body: JSON.stringify({
              sender: this.sender,
              receiver: this.receivers[i],
              subject: this.subject,
              body: this.body,
              date: this.date,
              priority: this.priority,
              id: this.id,

            })
          })
          .then(response => {
              return response.text();
            })
            .then(data => {
              this.fromBack = data;
              if(this.fromBack!=="Success"){
                this.condition=this.fromBack;
                console.log(this.condition);

              }
              else{
                fetch("http://localhost:8085/removeDraft/"+this.Emails[index].id+"/"+this.username,{method:'DELETE'})
               this.Emails=[];
                this.getDraft();
                   location.replace("http://localhost:8080/#/")
                  }
            });
          }
      },
      async SearchDraft() {
        let y = x
        this.username=y;
          if(this.selectedValue!=''&this.searchedValue!=''){
        const response= await fetch("http://localhost:8085/Search/Draft/"+this.username+"/"+this.selectedValue+"/"+this.searchedValue,{method:'get'})
            let data = await response.json();
            this.Emails=data;
          }
            
    },
    async FilterDraft() {
      let y = x
        this.username=y;
      if(this.searchedValue!=''&this.filtredValue!=''){
        const response= await fetch("http://localhost:8085/Filter/Draft/"+this.username+"/"+this.filtredValue+"/"+this.searchedValue,{method:'get'})
            let data = await response.json();
            this.Emails=data;
      }
            
    },
    async SortDraft() {
      let y = x
        this.username=y;
      if(this.sortedValue!=''){
        if(this.order==="dessending"){
        const response= await fetch("http://localhost:8085/Sort/Draft/"+this.username+"/"+this.sortedValue+"/false",{method:'get'})
            let data = await response.json();
            this.Emails=data;
        }else{
          const response= await fetch("http://localhost:8085/Sort/Draft/"+this.username+"/"+this.sortedValue+"/true",{method:'get'})
            let data = await response.json();
            this.Emails=data;
        }
      }  
    
   },
   AddReceivers(){
        this.receivers.push(this.reciever);
        this.reciever='';
      },
      handleFile(){
          this.file=document.getElementById('attachment').files[0]
          console.log(this.file);
          
      },
      async sendWithAttachment(index){
        let y = x
        this.sender=y;
        const d = new Date();
        for(var i =0;i<this.receivers.length;i++){
        this.date=d.getDate()+"-"+(parseInt(d.getMonth())+1).toString()+"-"+d.getFullYear();
        this.id=Math.random().toString();
        const formData = new FormData();
        formData.append('email',  JSON.stringify({
              sender: this.sender,
              receiver: this.receivers[i],
              subject: this.subject,
              body: this.body,
              date: this.date,
              priority: this.priority,
              id: this.id,

            }));
            formData.append('file',this.file);
            const response = await axios.post('http://localhost:8085/SendWithAttachment', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
          
        }
        );
        this.fromBack=response.data;
        if(this.fromBack!=="Failed"){
        fetch("http://localhost:8085/removeDraft/"+this.Emails[index].id+"/"+this.username,{method:'DELETE'})
               this.Emails=[];
                this.getDraft();
                   location.replace("http://localhost:8080/#/")
        }
        
      }
      
      this.file=null;
      },
      SendToBack(index){
        console.log(this.file)
        if(this.file==null)
        this.sendFromDraft(index);
        else
        this.sendWithAttachment(index);
      },
      RemoveAttachment(){
      document.getElementById('attachment').value=null;
      this.file=document.getElementById('attachment').files[0]
    },
    Draft(index){
        let y = x
        this.sender=y;
        console.log(this.sender);
        const d = new Date();
        for(var i =0;i<this.receivers.length;i++){
        this.date=d.getDate()+"-"+(parseInt(d.getMonth())+1).toString()+"-"+d.getFullYear();
        console.log(this.Sender);
        fetch("http://localhost:8085/Draft", {
             method: "post",
             headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
  
            //make sure to serialize your JSON body
            body: JSON.stringify({
              sender: this.sender,
              receiver: this.receivers[i],
              subject: this.subject,
              body: this.body,
              date: this.date,
              priority: this.priority,
              id: this.Emails[index].id,

            })
          })
          .then(response => {
              return response.text();
            })
            .then(data => {
              this.fromBack = data;
              if(this.fromBack!=="Success"){
                this.condition=this.fromBack;
                console.log(this.condition);
              }
              else{
                   location.replace("http://localhost:8080/#/")
                  }
            });
          }
          
      },
  },
   
   mounted () {
    this.getDraft();
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
      .send-mail-window {
      font-weight: 500;
      position: absolute;
      top: 50%;
      font-size: 1.2em;
      left: 50%;
      transform: translate(-50%, -50%);
      width: 75vw;
      height: 95vh;
      z-index: 100;
      border-radius: 10px;
      overflow: hidden;
      overflow-y: scroll;
      background-color: white;
      display: flex;
      flex-direction: column;
  }
  
  #priority_select {
      padding: 10px;
      border-radius: 5px;
  }
  
  #attachment {
      padding: 10px;
      border-radius: 5px;
  }
  
  .send-mail-window button {
      cursor: pointer;
  }
  
  .send-mail-window textarea {
      font-size: 1.2em;
      resize: none;
      
  }
  
  #recievers-box{
    display: flex;
    flex-direction: row;
  }
  
  #add-reciever-button{
  margin-left: 30px;
  border-radius: 10px;
  font-size: 1.1em;
  }
  
  .send-mail-window div{
    margin-left: 10px;
  }
  
  .new-message-label,#recievers-box,.more-reciever,.subject,#Priority,#Attachments{
    margin-bottom: 30px;
  }
  
  .send-mail-window textarea,
  .send-mail-window input {
      padding: 5px;
      border-radius: 5px;
  }
  
  .send-mail-window input {
      border: none;
  }
  
  .to,
  .subject {
      border: 1px solid black;
      width: 80%;
      padding: 10px;
     
  }
  
  .to input:focus,
  .subject input:focus {
      outline: none;
  }
  
  .to input,
  .subject input {
      font-size: 1.2em;
      width: 80%;
  }
  
  .Message {
      display: flex;
      align-items: center;
  
  }
  
  .new-message-label {
      padding: 7px;
      background-color: red;
      border-radius: 10px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 30px;
  }
  
  .new-message-label button,#add-reciever-button {
      border: none;
      background-color: red;
  }
  
  #add-reciever-button:hover{
    background-color: red;
  }
  .new-message-label button:hover {
      background-color: red;
      border-radius: 50%;
  }
  
  .new-message-label i {
      padding: 10px;
      margin: 0;
      text-align: center;
      font-size: 1.25em;
  }
  
  .message-options {
      
      margin: 0;
      bottom: 0;
      width: 98%;
      display: flex;
      justify-content: space-between;
      margin-top: 20px;
  }
  
  .message-options button {
      border: none;
      padding: 15px;
      background-color: red;
      color: black;
      font-weight: bold;
      border-radius: 10px;
      transition: 0.2s ease-in-out;
  }
  
  .message-options button:hover {
      background-color: red;
  }
  
  .more-reciever{
    border: 1px solid black;
    width: 80%;
    padding: 10px;
  }
  
  .more-reciever input{
    font-size: 1.2em;
    
    width: 80%;
  }
  
  .more-reciever input:focus{
    outline: none;
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