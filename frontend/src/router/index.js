import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'
import SignUp from '../views/SignUp.vue'
import LogIn from '../views/LogIn.vue'
import ComposeC from '../views/ComposeC.vue'
import InboxI from '../views/InboxI.vue'
import SentS from '../views/SentS.vue'
import TrashT from '../views/TrashT.vue'
import DraftD from '../views/DraftD.vue'
import ContactsC from '../views/ContactsC.vue'
import FolderF from '../views/FolderF.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomePage,
    children: [
      {
        path:'Inbox',
        name: 'Inbox',
        component: InboxI,
      },
      {
        path:'sent',
        name: 'SentS',
        component: SentS,
      },
      {
        path:'trash',
        name: 'TrashT',
        component: TrashT,
      },
      {
        path: 'compose',
        name: 'ComposeC',
        component: ComposeC,
        props: true
      },
      {
        path: 'Draft',
        name: 'DraftD',
        component: DraftD,
        props: true
      },
      {
        path: 'contacts',
        name: 'ContactsC',
        component: ContactsC,
        props: true
      },
      {
        path: 'folders',
        name: 'FolderF',
        component: FolderF,
        props: true
      },
    ]
  },
  {
    path: '/SignUp',
    name: 'SignUp',
    component: SignUp,
  },
  {
    path: '/LogIn',
    name: 'LogIn',
    component: LogIn,
  },
 


]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
