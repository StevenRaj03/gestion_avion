import { IonButton, IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle, IonContent, IonHeader, IonIcon, IonImg, IonInput, IonItem, IonLabel, IonList, IonListHeader, IonPage, IonSelect, IonSelectOption, IonThumbnail, IonTitle, IonToolbar } from '@ionic/react';
import { eye, image } from 'ionicons/icons';
import { useEffect, useState } from 'react';
import './Liste.css';
interface ContainerProps {  }

function redirect(page: string) {
  window.location.href = page;
}

export const Liste: React.FC<ContainerProps> = () => {
  const [posts, setPosts] = useState([]);
  useEffect(() => {
    async function sendRequest() {
      fetch('http://localhost:8080/GestionFlotte/avions', {
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
          <IonTitle>Avions</IonTitle>
          <IonButton slot="end" color="primary" onClick={() => redirect("/assurances")}>
                Assurance
            </IonButton>
        </IonToolbar>
      </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Avions</IonTitle>
          </IonToolbar>
        </IonHeader>

        {posts?.map((post: any) => 
          <IonCard>
            <IonCardHeader>
                <IonCardTitle>{post.id}. {post.matricule}</IonCardTitle>
                <IonCardSubtitle></IonCardSubtitle>
            </IonCardHeader>

            <IonCardContent>
              <IonThumbnail slot="start">
              <IonImg src={`data:image/jpeg;base64, ${post.image}`} />
              </IonThumbnail>
                Modele : {post.modele.nom} | Marque : {post.modele.marque.nom}
                <form className='ion-padding' method='GET' action='/details'>
                  <IonInput id="avion_id" value={post.id} name="avion_id" hidden></IonInput>
                  <IonInput id="avion_image" value={post.image} name="avion_image" hidden></IonInput>
                  <IonInput id="avion_modele" value={post.modele.nom} name="avion_modele" hidden></IonInput>
                  <IonInput id="avion_marque" value={post.modele.marque.nom} name="avion_marque" hidden></IonInput>
                  <IonInput id="avion_matricule" value={post.matricule} name="avion_matricule" hidden></IonInput>
                  <div className="voir-btn">
                  <IonButton type='submit' color="primary" shape="round" fill="outline" size='small'>
                      <IonIcon slot="middle" icon={eye}></IonIcon>
                      voir
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

export default Liste;