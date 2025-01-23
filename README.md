# Årsoppgave - Vår 2023

Dette var årsoppgaven min, levert på videregående vår 2023. Oppgaven består av et tekstbasert rollespill med visuelle elementer (./Javaspill) som kan lastes ned til egen PC (./setUp.bat), utviklet i kodespråket Java. Systemet inkluderer en database (./DatabaseBackup), laget med PostgreSQL, som oppdaterer de lokale filene til spillet via en egen server. I tillegg utviklet jeg en nettside (./AdminSide) som inneholder både en offentlig del og en innloggingsfunksjon for autoriserte brukere, slik at de kan gjøre endringer.

Prosjektet inkluderer også støtteverktøy (./Opplæringsmatriell), som en utviklermanual og en introduksjonsvideo.

Du kan lese mer om prosjektet i dokumentet _"Prosjektrapport_August.docx"_.

---

# GAME

Spillet tar deg gjennom en dungeon med tre etasjer. Jo lenger ned du går, jo bedre ting kan du få, men du møter også farligere monstre. Alle rom er tilfeldig generert. Begrunnelser for hvorfor jeg valgte noen løsninger finner du under prosjektbeskrivelse.

### Hvordan spille spillet

1. Last ned Java fra [Oracle](https://www.oracle.com/java/technologies/downloads/#jdk19-windows). Husk å velge _x64 Installer_ (.exe).
2. Last ned denne mappen til din PC.
3. Dobbeltklikk på `update.bat`. Denne kopierer de relevante filene til maskinen din.
4. Etter installasjonen kan du slette "Årsoppgave"-mappen. Du vil finne en snarvei til spillet på skrivebordet. Spilldata blir lagret her: `C:\game`.

Hvis du ønsker mer informasjon om spillet, kan du lese _SluttBrukerManualen_ i mappen `./Opplæringsmatriell`. Dette dokumentet gir en god forståelse av spillets funksjoner.

---

### For utviklere og administratorer

Er du her for å sette opp eller drifte prosjektet, kan du finne relevant informasjon i _UtviklerManualen_ i mappen `./Opplæringsmatriell`.

---
