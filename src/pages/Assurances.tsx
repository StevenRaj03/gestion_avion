import { IonBackButton, IonButton, IonButtons, IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle, IonContent, IonHeader, IonIcon, IonInput, IonItem, IonLabel, IonList, IonNote, IonPage, IonSelect, IonSelectOption, IonTitle, IonToolbar } from '@ionic/react';
import { eye } from 'ionicons/icons';
import { useState, useEffect } from 'react';
import './Assurances.css';

const Assurances: React.FC = () => {

  return (
    <IonPage>
        <IonHeader>
            <IonToolbar>
                <IonButtons slot="start">
                    <IonBackButton defaultHref="/liste" />
                </IonButtons>
                <IonTitle>Gestion d'assurance</IonTitle>
            </IonToolbar>
        </IonHeader>
      <IonContent fullscreen>
        <IonHeader collapse="condense">
          <IonToolbar>
            <IonTitle size="large">Assurances</IonTitle>
          </IonToolbar>
        </IonHeader>
        <IonCard>
        <IonCardHeader>
            <IonCardTitle>Assurances</IonCardTitle>
            <IonCardSubtitle>Voir les avions.</IonCardSubtitle>
        </IonCardHeader>

        <IonCardContent>
            <form action="/liste_assurances" className="ion-padding" method="get">
                <IonItem>
                    <IonLabel position="floating">Mois</IonLabel>
                    <IonInput type="number" name='valeur' required></IonInput>
                    <IonNote slot="error">Mois invalide</IonNote>
                </IonItem>
                
                <IonButton type='submit' color="primary">
                    Voir
                </IonButton>
            </form>
        </IonCardContent>
      </IonCard>

      </IonContent>
    </IonPage>
  );
};

export default Assurances;
