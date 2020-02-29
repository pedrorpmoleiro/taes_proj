import * as firebase from 'firebase/app'
import 'firebase/auth'
import 'firebase/firestore'
import 'firebase/database'
import 'firebase/'

const firebaseConfig = {
  apiKey: "AIzaSyCv3IKY9q9nQ-xN7FaUQ2aC0qdDn61h1ZY",
  authDomain: "sentinel-gb.firebaseapp.com",
  databaseURL: "https://sentinel-gb.firebaseio.com",
  projectId: "sentinel-gb",
  storageBucket: "sentinel-gb.appspot.com",
  messagingSenderId: "1053717743590",
  appId: "1:1053717743590:web:6fe8c61696f83b146f7827",
  measurementId: "G-RLBX4YCN5H"
};

if (!firebase.apps.length) {
  firebase.initializeApp(firebaseConfig);
}

export const EmailProvider = new firebase.auth.EmailAuthProvider();
export const auth = firebase.auth();
export const DB = firebase.database();
export const StoreDB = firebase.firestore();
export const FieldValue= firebase.firestore.FieldValue;
export default firebase;

