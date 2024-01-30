
<template>
    <div class="light-blocker"></div>
  <div class="send-mail-window">
    <div class="new-message-label" style="margin-left: 0">
      <span>New message</span>
      <span class="att"><button  @click="Draft()">&#128503;</button></span>
    </div>
  
    <div id = "recievers-box" style="margin-left: 0px;">
      <div class="to" id = "to">
        <div>
          <span>To:</span>
          <input type="text" id="1-reciever" v-model="reciever"/>
        </div>
      </div>
      <button id = "add-reciever-button"  @click="AddReceivers()">+ reciever</button>
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
      <select id="priority_select" v-model="priority" >
        <option value="So Important" >So Important</option>
        <option value="Important">Important</option>
        <option value="Default" >Default</option>
        <option value="Low">Low</option>
      </select>
    </div>
    <div id="Attachments" >
      <span class="att">
      <label for="attachment" >Select attachments:</label>
      <input style="width: 200px; margin-left: 0;"
        type="file"
        id="attachment"
        @change="handleFile()"
      />
    </span>
    <span class="att"><button @click="RemoveAttachment()" class="close">X</button></span>
    </div>
    <div class="Message">
      <label for="message">Enter Your Message: </label>
      <textarea id="message" rows="8" cols="75" v-model="body"></textarea>
    </div>
    <div class="message-options">
      <button type="button" @click="AddReceivers();SendToBack();" >
        <i class="fa fa-light fa-paper-plane"></i> Send E-mail
      </button>
      <button type="button" @click="AddReceivers();Draft()" >
        <i class="fa-regular fa-file"></i> Move to Draft
      </button>
    </div>
  </div>
  </template>
  <script>
  import axios from 'axios';
  import { x } from './LogIn.vue'
  export default {
    name: 'ComposeC',
    components: {
     
    },
    data (){
      return {
        sender:"",
        reciever:"",
        subject:"",
        body:"",
        date:"",
        priority:"",
        id:0,
        fromBack: "",
        condition:"",
        receivers:[],
        file: null,
      }
    },
    methods:{
      send(){
       let y = x
        this.sender=y;
        //console.log(y);
        console.log(this.sender+"success")
        console.log(this.sender+"success")
        console.log(this.sender+"success")
        const d = new Date();
        for(var i =0;i<this.receivers.length;i++){
        this.date=d.getDate()+"-"+(parseInt(d.getMonth())+1).toString()+"-"+d.getFullYear();
        this.id=Math.random().toString();
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
                   location.replace("http://localhost:8080/#/")
                  }
            });
          }
      },
      Draft(){
        let y = x
        this.sender=y;
        console.log(this.sender);
        const d = new Date();
        for(var i =0;i<this.receivers.length;i++){
        this.date=d.getDate()+"-"+(parseInt(d.getMonth())+1).toString()+"-"+d.getFullYear();
        this.id=Math.random().toString();
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
                   location.replace("http://localhost:8080/#/")
                  }
            });
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
      async sendWithAttachment(){
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
        location.replace("http://localhost:8080/#/")
        
      }
      
      this.file=null;
      },
      SendToBack(){
        console.log(this.file)
        if(this.file==null)
        this.send();
        else
        this.sendWithAttachment();
      },
      RemoveAttachment(){
      document.getElementById('attachment').value=null;
      this.file=document.getElementById('attachment').files[0]
    }
    },
  }
  </script>
  
  <style>
  .light-blocker {
      position: absolute;
      width: 100vw;
      height: 100vh;
      background-color: #000000;
      opacity: 0.3;
      top: 0;
      right: 0;
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
  
  </style>