# Projet-IHM-2019 - Makefile
# ---
# AUTEUR : Loïc BERNARD
# AUTEUR : ANTOINE MAN
# VERSION : X

# BUT FINAL

but: src/modele/Main.class


# VARIABLES

JC = javac


JFLAGS = -implicit:none -encoding UTF-8

# DEPENDANCES CONTROLEUR

src/controleur/Fermeture.class: src/controleur/Fermeture.java
	$(JC) $(JFLAGS) ./src/controleur/Fermeture.java

src/controleur/AccueilListener.class: src/controleur/AccueilListener.java
	$(JC) $(JFLAGS) ./src/controleur/AccueilListener.java

src/controleur/MenuListener.class: src/controleur/MenuListener.java \
			src/modele/JardinFactory.class \
			src/vue/DessinerJardin.class \
			src/vue/Page_Jardin.class
	$(JC) $(JFLAGS) ./src/controleur/MenuListener.java

src/controleur/CreationJardinListener.class: src/controleur/CreationJardinListener.java \
					src/modele/VerifNomJardin.class \
					src/modele/JardinFactory.class \
					src/vue/Page_Jardin.class
	$(JC) $(JFLAGS) ./src/controleur/CreationJardinListener.java

src/controleur/MenuJardinListener.class: src/controleur/MenuJardinListener.java \
					src/modele/ParcelleFactory.class \
					src/modele/Orientation.class \
					src/vue/DessinerJardin.class \
					src/modele/ActionSol.class \
					src/modele/ActionLegume.class \
					src/modele/ActionSolType.class \
					src/vue/GestionnaireAction.class \
					src/vue/JTableHistorique.class
	$(JC) $(JFLAGS) ./src/controleur/MenuJardinListener.java

src/controleur/MouseJardinListener.class: src/controleur/MouseJardinListener.java \
					src/vue/DessinerJardin.class \
					src/vue/PageParcelle.class
	$(JC) $(JFLAGS) ./src/controleur/MouseJardinListener.java

src/controleur/MenuActionListener.class: src/controleur/MenuActionListener.java \
					src/modele/ActionSol.class \
					src/modele/ActionSolType.class \
					src/modele/ActionLegumeType.class \
					src/modele/ActionLegume.class \
					src/modele/LegumeBase.class \
					src/modele/VerifNomLegume.class
	$(JC) $(JFLAGS) ./src/controleur/MenuActionListener.java

# DEPENDANCES VUE

src/vue/Page_connexion.class: src/vue/Page_connexion.java \
			src/controleur/AccueilListener.class \
			src/vue/Background.class
	$(JC) $(JFLAGS) ./src/vue/Page_connexion.java

src/vue/Page_Jardin.class: src/vue/Page_Jardin.java \
			src/controleur/MenuJardinListener.class \
			src/controleur/MouseJardinListener.class \
			src/vue/DessinerJardin.class \
			src/modele/DonneeJardin.class 
	$(JC) $(JFLAGS) ./src/vue/Page_Jardin.java

src/vue/Menu_principal.class: src/vue/Menu_principal.java \
			src/controleur/MenuListener.class \
			src/controleur/CreationJardinListener.class \
			src/modele/TrouverJardin.class \
			src/modele/JardinFactory.class
	$(JC) $(JFLAGS) ./src/vue/Menu_principal.java

src/vue/Gestionnaire_vue.class: src/vue/Gestionnaire_vue.java \
			src/vue/Menu_principal.class \
			src/vue/Page_connexion.class \
			src/controleur/Fermeture.class
	$(JC) $(JFLAGS) ./src/vue/Gestionnaire_vue.java

src/vue/DessinerJardin.class: src/vue/DessinerJardin.java
	$(JC) $(JFLAGS) ./src/vue/DessinerJardin.java

src/vue/DessinerParcelle.class: src/vue/DessinerParcelle.java
	$(JC) $(JFLAGS) ./src/vue/DessinerParcelle.java

src/vue/PageParcelle.class: src/vue/PageParcelle.java \
			src/vue/DessinerParcelle.class \
			src/controleur/MenuJardinListener.class
	$(JC) $(JFLAGS) ./src/vue/PageParcelle.java

src/vue/Background.class: src/vue/Background.java
	$(JC) $(JFLAGS) ./src/vue/Background.java

src/vue/GestionnaireAction.class: src/vue/GestionnaireAction.java \
				src/controleur/Fermeture.class \
				src/vue/PageActionSol.class \
				src/vue/PageActionLegume.class
	$(JC) $(JFLAGS) ./src/vue/GestionnaireAction.java

src/vue/PageActionSol.class: src/vue/PageActionSol.java \
				src/controleur/MenuActionListener.class
	$(JC) $(JFLAGS) ./src/vue/PageActionSol.java

src/vue/PageActionLegume.class: src/vue/PageActionLegume.java \
				src/controleur/MenuActionListener.class \
				src/modele/LegumeBase.class
	$(JC) $(JFLAGS) ./src/vue/PageActionLegume.java

src/vue/TableauHistoriqueLegume.class: src/vue/TableauHistoriqueLegume.java \
				src/modele/ActionLegume.class \
				src/vue/TupleLegume.class
	$(JC) $(JFLAGS) ./src/vue/TableauHistoriqueLegume.java

src/vue/TupleLegume.class: src/vue/TupleLegume.java
	$(JC) $(JFLAGS) ./src/vue/TupleLegume.java

src/vue/JTableHistorique.class: src/vue/JTableHistorique.java \
				src/vue/TableauHistoriqueLegume.class \
				src/vue/TableauHistoriqueSol.class \
				src/controleur/Fermeture.class
	$(JC) $(JFLAGS) ./src/vue/JTableHistorique.java

src/vue/TableauHistoriqueSol.class: src/vue/TableauHistoriqueSol.java \
				src/modele/ActionLegume.class \
				src/vue/TupleSol.class
	$(JC) $(JFLAGS) ./src/vue/TableauHistoriqueSol.java

src/vue/TupleSol.class: src/vue/TupleSol.java
	$(JC) $(JFLAGS) ./src/vue/TupleSol.java

# DEPENDANCES MODELE

src/modele/Main.class: src/modele/Main.java \
			src/vue/Gestionnaire_vue.class
	$(JC) $(JFLAGS) ./src/modele/Main.java

src/modele/VerifNomJardin.class: src/modele/VerifNomJardin.java
	$(JC) $(JFLAGS) ./src/modele/VerifNomJardin.java

src/modele/TrouverJardin.class: src/modele/TrouverJardin.java
	$(JC) $(JFLAGS) ./src/modele/TrouverJardin.java

src/modele/JardinFactory.class: src/modele/JardinFactory.java \
			src/modele/AbstractJardinFactory.class
	$(JC) $(JFLAGS) ./src/modele/JardinFactory.java

src/modele/AbstractJardinFactory.class: src/modele/AbstractJardinFactory.java
	$(JC) $(JFLAGS) ./src/modele/AbstractJardinFactory.java

src/modele/AbstractParcelleFactory.class: src/modele/AbstractParcelleFactory.java
	$(JC) $(JFLAGS) ./src/modele/AbstractParcelleFactory.java

src/modele/Orientation.class: src/modele/Orientation.java
	$(JC) $(JFLAGS) ./src/modele/Orientation.java

src/modele/ParcelleFactory.class: src/modele/ParcelleFactory.java \
			src/modele/Orientation.class \
			src/modele/AbstractParcelleFactory.class
	$(JC) $(JFLAGS) ./src/modele/ParcelleFactory.java

src/modele/DonneeJardin.class: src/modele/DonneeJardin.java
	$(JC) $(JFLAGS) ./src/modele/DonneeJardin.java

src/modele/Action.class: src/modele/Action.java
	$(JC) $(JFLAGS) ./src/modele/Action.java

src/modele/ActionSolType.class: src/modele/ActionSolType.java
	$(JC) $(JFLAGS) ./src/modele/ActionSolType.java

src/modele/LegumeBase.class: src/modele/LegumeBase.java
	$(JC) $(JFLAGS) ./src/modele/LegumeBase.java

src/modele/ActionSol.class: src/modele/ActionSol.java \
			src/modele/Action.class \
			src/modele/ActionSolType.class
	$(JC) $(JFLAGS) ./src/modele/ActionSol.java

src/modele/ActionLegumeType.class: src/modele/ActionLegumeType.java
	$(JC) $(JFLAGS) ./src/modele/ActionLegumeType.java		

src/modele/ActionLegume.class: src/modele/ActionLegume.java \
			src/modele/ActionLegumeType.class \
			src/modele/ActionLegumeFactory.class
	$(JC) $(JFLAGS) ./src/modele/ActionLegume.java

src/modele/VerifNomLegume.class: src/modele/VerifNomLegume.java
	$(JC) $(JFLAGS) ./src/modele/VerifNomLegume.java

src/modele/ActionLegumeFactory.class: src/modele/ActionLegumeFactory.java \
					src/modele/ActionLegumeType.class
	$(JC) $(JFLAGS) ./src/modele/ActionLegumeFactory.java

# NETTOYAGE DES FICHIERS .class

clean:
	find ./src/* -name "*.class" -exec rm {} \;


# BUTS

.PHONY: run

run:
	java -cp ".://home/mana/Documents/ProjetACDA/mariadb-client.jar" src.modele.Main

#Chemin Loïc Perso: .//home/mnt/c/Users/user/OneDrive/Bureau/ACDA_JAVA_PROJET/ProjetACDA/mariadb-client.jar
#Chemin Loïc Université: .:/export/home/senart18/bernardl/Desktop/ProjetACDA/Projet-IHM-2019/mariadb-client.jar
#Chemin Antoine Perso: .://home/mana/Documents/ProjetACDA/mariadb-client.jar
#Chemin Antoine Université: .:/export/home/an18/mana/ProjetACDA/Projet-IHM-2019/mariadb-client.jar
#Chemin Université général: .:/export/documents/mariadb-client.jar
