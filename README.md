# BanConnesso

### :wrench: Funzionamento
Il sistema di BanConnesso è una blacklist che serve a prevenire i giocatori dall'accedere ai server membri di FounderConnessi qualora dovessero violare il regolamento del gruppo disponibile sul sito. Le finalità del regolamento sono di tipo: sicurezza collettiva (grief, attacchi bot e ddos, ecc...), scam della community, diffamazione grave e chargeback.

Ricordati di specificare nel regolamento del tuo server che adottate anche il regolamento di FounderConnessi.

Ogni server membro di FounderConnessi può avviare una segnalazione sul server Discord ufficiale, allegando le prove e partecipando al dibattito con gli altri founder per valutare al meglio la situazione. Una volta che la discussione si considera esaustiva, si procede alla votazione per la blacklist. Ogni server può esprimere tramite il proprio referente un solo voto, per garantire l'equità di tutti i membri a prescindere dal numero di founder.

Si può votare la contrarietà alla blacklist oppure tre livelli di gravità della blacklist (1 LOW, 2 MEDIUM, 3 HIGH). Una volta conclusa la votazione, il giocatore verrà blacklistato o meno con il livello di gravità che ottiene il maggior numero di voti oppure non verrà blacklistato se i contrari sono superiori al numero totale di persone che ha votato per uno dei tre livelli di blacklist.

Ogni server può decidere tramite il config del plugin se far accedere o meno i giocatori blacklistati con livello di gravità 1 o 2. Le blacklist di livello 3 sono bloccate di default.


### :warning: Compatibilità
- Spigot
- BungeeCord
- Velocity


### :inbox_tray: Installazione
1. Scaricare l'ultima versione del plugin da questa [pagina](https://github.com/FounderConnessi/BanConnesso/releases).
2. Caricare il plugin nella cartella <em><strong>plugins</strong></em> del server.
3. Riavviare il server per permettere la generazione della configurazione di BanConnesso.
4. Modificare la configurazione del plugin e ricaricare il plugin usando il comando <em>/banconnesso reload<em>