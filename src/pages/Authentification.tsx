import { IonAlert, IonCardSubtitle, IonCardTitle, IonCheckbox, IonContent, IonLabel, IonPage, } from '@ionic/react';
import { useParams } from 'react-router';
import ExploreContainer from '../components/ExploreContainer';

import './Home.css';
import { useEffect, useState } from 'react';

const Authentification = () => {
  const queryParams = new URLSearchParams(window.location.search);
  const email = queryParams.get('email');
  const mdp = queryParams.get('mdp');

  const [conect,setConnect]=useState('');
  console.log( email, mdp); 
    
    const [posts, setPosts] = useState<any>([]);

   useEffect(() => {
    async function sendRequest(){
      fetch('http://localhost:8080/GestionFlotte/login/'+email+'/'+mdp,{
      method: "GET" })
       .then((res) => res.json())
       .then((result) => {
        
          console.log(result);
          setPosts(result);
       })
       .catch((err) => {
          console.log(err.message);
       });
    }
    sendRequest();
    
 },[]);
 if(posts==true){
  console.log(true);
  var port=window.location.origin;
  console.log("origin : "+origin);
  window.location.replace(origin+'/liste');
 }else{
  window.location.replace(origin+"/login?error=1");
 }
  return (
    <IonPage>
      <IonContent fullscreen>
        
        <IonPage>
          <div>
           <p></p>
            </div>
        </IonPage>
        
      </IonContent>
      
    </IonPage>
    
  );
};

export default Authentification;
