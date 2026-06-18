#!/bin/bash

# =============================================================================
#  Script de démonstration – Ajout de deux entités par module (sans jq)
# =============================================================================
#  Utilisation : bash demo-complete.sh
# =============================================================================

set -e

# Couleurs
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
NC='\033[0m'

# Fonction pour extraire le token de la réponse JSON
extract_token() {
  local response="$1"
  echo "$response" | sed -n 's/.*"token":"\([^"]*\)".*/\1/p'
}

# Fonction pour extraire une valeur (par ex. id) d'une réponse JSON simple
extract_id() {
  local json="$1"
  echo "$json" | grep -o '"id":[0-9]*' | head -1 | cut -d: -f2
}

# Fonction pour extraire le premier ID d'une liste
get_first_id() {
  local endpoint="$1"
  local response
  response=$(curl -s -X GET "http://localhost:8080$endpoint" -H "Authorization: Bearer $TOKEN")
  extract_id "$response"
}

# =============================================================================
#  1. Authentification
# =============================================================================
echo -e "${YELLOW}🔐 Authentification en cours...${NC}"
AUTH_RESPONSE=$(curl -s -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}')

TOKEN=$(extract_token "$AUTH_RESPONSE")

if [ -z "$TOKEN" ] || [ "$TOKEN" = "null" ]; then
  echo -e "${RED}❌ Échec de l'authentification.${NC}"
  echo "$AUTH_RESPONSE"
  exit 1
fi
echo -e "${GREEN}✅ Token récupéré (début : ${TOKEN:0:20}...)${NC}"
echo ""

# Fonction pour faire un POST et afficher le résultat
post() {
  local name="$1"
  local endpoint="$2"
  local data="$3"
  local response
  response=$(curl -s -X POST "http://localhost:8080$endpoint" \
    -H "Authorization: Bearer $TOKEN" \
    -H "Content-Type: application/json" \
    -d "$data")
  # On essaye d'extraire un ID ou un message
  local id=$(echo "$response" | grep -o '"id":[0-9]*' | head -1 | cut -d: -f2)
  if [ -n "$id" ]; then
    echo -e "${GREEN}  ✔ $name : ID $id${NC}"
  else
    local msg=$(echo "$response" | sed -n 's/.*"message":"\([^"]*\)".*/\1/p')
    if [ -n "$msg" ]; then
      echo -e "${GREEN}  ✔ $name : $msg${NC}"
    else
      echo -e "${GREEN}  ✔ $name : OK${NC}"
    fi
  fi
}

# =============================================================================
#  2. Module Étudiants (2 entrées)
# =============================================================================
echo -e "${YELLOW}📚 Module Étudiants${NC}"
post "Création étudiant 1" "/api/etudiants" '{
  "ine": "20240050",
  "nom": "Diop",
  "prenom": "Fatou",
  "dateNaissance": "2000-03-10",
  "formation": "Informatique",
  "promo": "2024-2026",
  "anneeDebut": 2024,
  "anneeSortie": 2026,
  "diplomes": ["Licence Info"],
  "autresFormations": "Anglais"
}'
post "Création étudiant 2" "/api/etudiants" '{
  "ine": "20240051",
  "nom": "Ndiaye",
  "prenom": "Oumar",
  "dateNaissance": "2001-07-20",
  "formation": "Mathématiques",
  "promo": "2024-2026",
  "anneeDebut": 2024,
  "anneeSortie": 2026,
  "diplomes": ["Licence Maths"],
  "autresFormations": ""
}'
echo ""

# =============================================================================
#  3. Module Formations (2 entrées)
# =============================================================================
echo -e "${YELLOW}📚 Module Formations${NC}"
post "Création formation 1" "/api/formations" '{
  "dateDebut": "2025-09-01",
  "dateFin": "2026-06-30",
  "typeFormation": "Master",
  "niveau": "Bac+5",
  "montant": 850000,
  "typeFinancement": "Public",
  "nbHommes": 40,
  "nbFemmes": 30
}'
post "Création formation 2" "/api/formations" '{
  "dateDebut": "2025-10-01",
  "dateFin": "2026-07-31",
  "typeFormation": "Licence",
  "niveau": "Bac+3",
  "montant": 450000,
  "typeFinancement": "Privé",
  "nbHommes": 25,
  "nbFemmes": 35
}'
echo ""

# =============================================================================
#  4. Module Formateurs (2 entrées)
# =============================================================================
echo -e "${YELLOW}👨‍🏫 Module Formateurs${NC}"
post "Création formateur 1" "/api/formateurs" '{
  "nom": "Sow",
  "prenom": "Mamadou",
  "email": "mamadou.sow@unchk.sn",
  "type": "ENSEIGNANT"
}'
post "Création formateur 2" "/api/formateurs" '{
  "nom": "Fall",
  "prenom": "Aminata",
  "email": "aminata.fall@unchk.sn",
  "type": "TUTEUR"
}'
echo ""

# =============================================================================
#  5. Module Emplois du temps – nécessite IDs de formation et formateur
# =============================================================================
echo -e "${YELLOW}📅 Module Emplois du temps${NC}"
FORM_ID=$(get_first_id "/api/formations")
TEACH_ID=$(get_first_id "/api/formateurs")
post "Création emploi 1" "/api/emplois-temps" "{
  \"jour\": \"LUNDI\",
  \"heureDebut\": \"08:00:00\",
  \"heureFin\": \"10:00:00\",
  \"salle\": \"A101\",
  \"matiere\": \"Algorithmique\",
  \"formation\": { \"id\": $FORM_ID },
  \"formateur\": { \"id\": $TEACH_ID }
}"
post "Création emploi 2" "/api/emplois-temps" "{
  \"jour\": \"MERCREDI\",
  \"heureDebut\": \"14:00:00\",
  \"heureFin\": \"16:00:00\",
  \"salle\": \"B205\",
  \"matiere\": \"Analyse\",
  \"formation\": { \"id\": $FORM_ID },
  \"formateur\": { \"id\": $TEACH_ID }
}"
echo ""

# =============================================================================
#  6. Module Comptes rendus (2 entrées)
# =============================================================================
echo -e "${YELLOW}📝 Module Comptes rendus${NC}"
post "Création CR 1" "/api/comptes-rendus" '{
  "titre": "Réunion pédagogique du 18/06",
  "contenu": "Discussion sur les programmes et évaluations.",
  "dateCreation": "2025-06-18T09:00:00",
  "type": "REUNION",
  "auteur": "Dr. Diallo",
  "documentJoint": "",
  "rolesAutorises": ["ROLE_ADMIN", "ROLE_ENSEIGNANT"]
}'
post "Création CR 2" "/api/comptes-rendus" '{
  "titre": "Séminaire sur l’IA",
  "contenu": "Présentation des avancées en intelligence artificielle.",
  "dateCreation": "2025-06-19T14:30:00",
  "type": "SEMINAIRE",
  "auteur": "Pr. Ba",
  "documentJoint": "",
  "rolesAutorises": ["ROLE_ADMIN"]
}'
echo ""

# =============================================================================
#  7. Module Documents – upload (2 documents)
# =============================================================================
echo -e "${YELLOW}📂 Module Documents (upload)${NC}"
echo "Contenu du document 1" > /tmp/doc1.txt
echo "Contenu du document 2" > /tmp/doc2.txt

curl -s -X POST http://localhost:8080/api/documents \
  -H "Authorization: Bearer $TOKEN" \
  -F 'document={"titre":"Note de service","type":"NOTE_SERVICE","description":"Première note","auteur":"Admin"};type=application/json' \
  -F 'file=@/tmp/doc1.txt' > /dev/null
echo -e "${GREEN}  ✔ Upload document 1 : Note de service${NC}"

curl -s -X POST http://localhost:8080/api/documents \
  -H "Authorization: Bearer $TOKEN" \
  -F 'document={"titre":"Circulaire","type":"CIRCULAIRE","description":"Circulaire interne","auteur":"Secrétariat"};type=application/json' \
  -F 'file=@/tmp/doc2.txt' > /dev/null
echo -e "${GREEN}  ✔ Upload document 2 : Circulaire${NC}"

rm -f /tmp/doc1.txt /tmp/doc2.txt
echo ""

# =============================================================================
#  8. Module Budgets (2 entrées)
# =============================================================================
echo -e "${YELLOW}💰 Module Budgets${NC}"
post "Création budget 1" "/api/budgets" '{
  "type": "PROJET",
  "description": "Budget projet pédagogique",
  "montant": 3000000,
  "annee": 2025
}'
post "Création budget 2" "/api/budgets" '{
  "type": "REALISE",
  "description": "Budget réalisé 2024",
  "montant": 2800000,
  "annee": 2024
}'
echo ""

# =============================================================================
#  9. Module Stages – nécessite un étudiant existant
# =============================================================================
echo -e "${YELLOW}💼 Module Stages${NC}"
ETUD_ID=$(get_first_id "/api/etudiants")
post "Création stage 1" "/api/stages" "{
  \"entreprise\": \"Orange Sénégal\",
  \"dateDebut\": \"2025-07-01\",
  \"dateFin\": \"2025-09-30\",
  \"bilan\": \"Très bon stage\",
  \"tuteur\": \"M. Fall\",
  \"etudiant\": { \"id\": $ETUD_ID }
}"
post "Création stage 2" "/api/stages" "{
  \"entreprise\": \"Sonatel\",
  \"dateDebut\": \"2025-08-01\",
  \"dateFin\": \"2025-10-31\",
  \"bilan\": \"Stage enrichissant\",
  \"tuteur\": \"Mme. Sow\",
  \"etudiant\": { \"id\": $ETUD_ID }
}"
echo ""

# =============================================================================
#  10. Module Partenaires (2 entrées)
# =============================================================================
echo -e "${YELLOW}🤝 Module Partenaires${NC}"
post "Création partenaire 1" "/api/partenaires" '{
  "nom": "Sonatel",
  "secteur": "Télécoms",
  "typePartenariat": "Académique",
  "contact": "contact@sonatel.sn"
}'
post "Création partenaire 2" "/api/partenaires" '{
  "nom": "Free Sénégal",
  "secteur": "Internet",
  "typePartenariat": "Professionnel",
  "contact": "partenariat@free.sn"
}'
echo ""

# =============================================================================
#  11. Module Statistiques insertion (2 entrées)
# =============================================================================
echo -e "${YELLOW}📊 Module Statistiques${NC}"
post "Création stat 1" "/api/statistiques" '{
  "annee": 2025,
  "nbAutoEmploi": 18,
  "nbSalarie": 42
}'
post "Création stat 2" "/api/statistiques" '{
  "annee": 2024,
  "nbAutoEmploi": 12,
  "nbSalarie": 38
}'
echo ""

# =============================================================================
#  12. Module Réunions – nécessite une formation existante
# =============================================================================
echo -e "${YELLOW}👥 Module Réunions${NC}"
FORM_ID2=$(get_first_id "/api/formations")
post "Création réunion 1" "/api/reunions" "{
  \"titre\": \"Suivi tutorat L1\",
  \"type\": \"SUIVI_TUTORAT\",
  \"dateReunion\": \"2025-06-20T09:00:00Z\",
  \"description\": \"Bilan du premier semestre\",
  \"responsable\": \"Dr. Ndiaye\",
  \"formation\": { \"id\": $FORM_ID2 }
}"
post "Création réunion 2" "/api/reunions" "{
  \"titre\": \"Préparation des examens\",
  \"type\": \"PREPARATION_EVALUATION\",
  \"dateReunion\": \"2025-06-25T14:30:00Z\",
  \"description\": \"Organisation des évaluations finales\",
  \"responsable\": \"Pr. Ba\",
  \"formation\": { \"id\": $FORM_ID2 }
}"
echo ""

# =============================================================================
#  Fin
# =============================================================================
echo -e "${GREEN}✅ Toutes les entités ont été créées avec succès !${NC}"
echo -e "${YELLOW}💡 Consultez l'application pour voir les nouvelles données.${NC}"