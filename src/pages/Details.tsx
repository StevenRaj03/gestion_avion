import { IonBackButton, IonButton, IonButtons, IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle, IonContent, IonHeader, IonIcon, IonImg, IonInput, IonItem, IonLabel, IonList, IonListHeader, IonPage, IonSelect, IonSelectOption, IonThumbnail, IonTitle, IonToolbar } from '@ionic/react';
import { camera, eye } from 'ionicons/icons';
import { useState, useEffect } from 'react';
import { useParams } from 'react-router';
import './Details.css';

const queryString = window.location.search;
//console.log(queryString);
const urlParams = new URLSearchParams(queryString);
const avion_id = urlParams.get('avion_id');
const avion_image = urlParams.get('avion_image');
var avion_img:any;
avion_img = avion_image;
const avion_matricule = urlParams.get('avion_matricule');
const avion_modele = urlParams.get('avion_modele');
const avion_marque = urlParams.get('avion_marque');

  const openFileDialog = () => {
    (document as any).getElementById("file-upload").click();
 };
 
 const setImage = (_event: any) => {
   let f = _event.target.files![0];
   console.log(f);
   let reader = new FileReader();
   let image = "";
   reader.onloadend = function() {
     image = reader.result as string;
     image = image.substring(23);
     //console.log('RESULT: ', reader.result);
     console.log("my image: " + image);
   }
   reader.readAsDataURL(f);
   fetch('http://localhost:8080/GestionFlotte/avions/' + avion_id + '/image/' + image, {
      method: "PUT" })
      .catch((err) => {
        console.log(err.message);
      });
}

export const Details: React.FC = () => {
    const [posts, setPosts] = useState([]);

    //console.log(avion_id);
    useEffect(() => {
        async function sendRequest() {
        fetch('http://localhost:8080/GestionFlotte/avions/' + avion_id + '/kilometrages', {
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
                <IonBackButton defaultHref="/liste" />
            </IonButtons>
            <IonTitle>Avion</IonTitle>
        </IonToolbar>
        </IonHeader>
        <IonContent fullscreen>
        <IonHeader collapse="condense">
            <IonToolbar>
            <IonTitle size="large">Avion</IonTitle>
            </IonToolbar>
        </IonHeader>
        <IonCard>
            <IonCardHeader>
                <IonCardTitle>{avion_matricule}</IonCardTitle>
                <IonCardSubtitle>Modele : {avion_modele} | Marque : {avion_marque}</IonCardSubtitle>
            </IonCardHeader>

            <IonCardContent>
                <IonThumbnail slot="start">
                    <IonImg src={`data:image/jpeg;base64, ${avion_img}`} />
                </IonThumbnail>
                <IonList>
                {posts.map((post: any) => 
                    <IonItem>
                        Date : {post.date} | kilometrage: {post.kilometrage} Km
                    </IonItem>
                )}
                </IonList>
                <input
                    type="file"
                    id="file-upload"
                    style={{ display: "none" }}
                    onChange={setImage}
                    />

                <IonButton onClick={openFileDialog}>
                    <IonIcon slot="icon-only" icon={camera}></IonIcon>
                </IonButton>
            </IonCardContent>
        </IonCard>
            
        </IonContent>
    </IonPage>
    );
};

export default Details;

