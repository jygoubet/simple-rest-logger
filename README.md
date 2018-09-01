# simple-rest-logger
Simple REST Logger 

Format du POST à envoyer sur localhost:8080/log : 
```
{level: "info", data: "contenu de mon log"}
```
Pour le lancer avec maven :

```
mvn clean spring-boot:run
```

Une fois le serveur lancé, les différents niveaux de logs possibles s'affichent. Pour changer les couleurs, il suffit d'aller dans le fichier LoggerApplication
