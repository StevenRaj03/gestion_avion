import { IonBackButton, IonButton, IonButtons, IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle, IonContent, IonHeader, IonIcon, IonImg, IonInput, IonItem, IonLabel, IonList, IonNote, IonPage, IonSelect, IonSelectOption, IonThumbnail, IonTitle, IonToolbar } from '@ionic/react';
import { eye } from 'ionicons/icons';
import { useState, useEffect } from 'react';
import './Liste_assurances.css';

const Liste_assurances: React.FC = () => {
    const queryString = window.location.search;
    //console.log(queryString);
    const urlParams = new URLSearchParams(queryString);
    const valeur = urlParams.get('valeur');
    const [posts, setPosts] = useState([]);
    useEffect(() => {
        async function sendRequest() {
        fetch('http://localhost:8080/GestionFlotte/assurances/expiration/' + valeur, {
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
    }, []);
  return (
    <IonPage>
        <IonHeader>
            <IonToolbar>
                <IonButtons slot="start">
                    <IonBackButton defaultHref="/assurances" />
                </IonButtons>
                <IonTitle>Avions</IonTitle>
            </IonToolbar>
        </IonHeader>
      <IonContent fullscreen>
        <IonItem color="danger">Assurance expirée dans {valeur} mois.</IonItem>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Liste des avions avec expiration d'assurance</IonTitle>
          </IonToolbar>
        </IonHeader>

      {posts?.map((post: any) => 
          <IonCard>
            <IonCardHeader>
                <IonCardTitle>{post.avion.id}. {post.avion.matricule}</IonCardTitle>
            </IonCardHeader>

            <IonCardContent>
            <IonThumbnail slot="start">
              <IonImg src={`data:image/jpeg;base64, ${post.image}`} />
              </IonThumbnail>
                Modele : {post.avion.modele.nom} | Marque : {post.avion.modele.marque.nom} <br/>
                Expiration : {post.date_expiration} | Montant : {post.montant} Ar
                <form className='ion-padding' method='GET' action='/details'>
                  <IonInput id="avion_id" value={post.avion.id} name="avion_id" hidden></IonInput>
                  <IonInput id="avion_image" value={post.image} name="avion_image" hidden></IonInput>
                  <IonInput id="avion_modele" value={post.avion.modele.nom} name="avion_modele" hidden></IonInput>
                  <IonInput id="avion_marque" value={post.avion.modele.marque.nom} name="avion_marque" hidden></IonInput>
                  <IonInput id="avion_matricule" value={post.avion.matricule} name="avion_matricule" hidden></IonInput>
                  <div className="voir-btn">
                  <IonButton type='submit' color="primary" shape="round" fill="outline" size='small'>
                      <IonIcon slot="middle" icon={eye}></IonIcon>
                      Avion
                  </IonButton>
                  </div>
                </form>
            </IonCardContent>
          </IonCard>
        )}

      </IonContent>
    </IonPage>
  );
};

export default Liste_assurances;
