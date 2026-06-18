# Université Cheikh Hamidou KANE – Application de gestion

## Description
Application web modulaire pour la gestion administrative et pédagogique de l'Université Cheikh Hamidou KANE.

## Technologies
- **Backend** : Spring Boot 3.5.15, PostgreSQL, JWT
- **Frontend** : Angular 17, Material UI, Chart.js

## Installation

### Backend
```bash
cd universite-unchk
./mvnw spring-boot:run

### Frontend
''''bash
cd universite-frontend
npm install
ng serve


Authentification par défaut
Utilisateur	Mot de passe	Rôle
admin	admin123	ADMIN
prof	prof123	ENSEIGNANT
agent	agent123	APPUI_INSERTION
etudiant1	etudiant123	ETUDIANT