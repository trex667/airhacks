Queues: z.B. queue für http requests monitoren, kann bei allen AS gemacht werden. Wenn dei queue bei stresstests zuviele Elemente enthält, dann kann das ein erstes Zeichen für performance probleme sein. Größe der queue kann auch eingestellt werden, ist aber mit vorsicht zu genießen, da eine größere queue nicht zu schnellerer Verarbeitung führt, dann eher thread pool vergrößern!
Thread pools: z.B. worker pool für http requests. Arbeiten die requests aus der queue ab; Größe kann konfiguriert werden.
Während der stresstests, die Queues und pools mit den boardmitteln des AS monitoren

Von Anfang an Stresstests machen und die Ergebnisse monitoren.
Tools: JMeter, apache-benchmark, JMH
Er verwendet JMeter wenn er UI benötigt, aber JMH auf der Kommandozeile. JMH bietet bessere Möglichkeiten gerade bzgl. Automatisierung. JMH ist ein benchmarking tool von openJDK. Zusammen mit dem maven shade plugin kann man einfach jars bauen, die dann z.B. in einer jenkins pipeline eigebunden werden können.

NOGO: synchronized static methoden!! Dann ist keine paralelle Verarbeitung möglich, dann hilft auch kein Erhöhen des thread pools.



Immer so einfach wie möglich machen.

die boundary Klassen, die quasi die API nach außen darstellen, mit @Stateless annotieren. Alles andere als POJO
Vorteil: pooling der EJBs, transactions,
Durch das pooling und weniger proxy/reflection wie CDI, ist EJB sogar performanter wie CDI. Kann sich in Zukunft ändern, aber zumindest für javee 7 und auch java ee 8 ist das @Stateless besser. Dadurch dass alles andere POJOs sind und mit CDI injected werden ist das EJB pooling efficient und die POJOs werden mit CDI injected und sind dann quasi an die Transaction des EJBs gebunden. D.h. sie werden nach der transaction auch vom GC weggeräumt.
VORSICHT IN DEM ZUSAMMENHANG MIT "LAZY CDI":
@Inject
Instance<ServiceClass> service;
Hier wird bei jedem call ein neues object von service erzeugt und das EJB behält eine reference auf den proxy des objectes. Dadurch werden die nie vom GC weg geräumt (EJBs sind ja gepoolt) und das ist definitiv ein memory leak. Siehe Beispiel lazy-di




Tools zur Analyse: visualVM, JCMD, eclipse memory analyzer, jconsole, mission control


Implementierung von healthcheck REST endpoints für:
ready: einfacher ping rest call, der verwendet werden kann, ob der microservice ready ist
Healthcheck: rest endpoint, der wichtige informationen der konkreten app zurück gibt. Z.B. rent a bike app: wieviele Fahrräder sind gerade vermietet und wieviele sind frei, reserviert und so. Hier sollten auch die ping services von anderen Microservices die benutzt werden abgefragt werden, um zu sehen ob die ready sind.


caching
vermeiden wenn es geht! Nur verwenden, um wirklich Daten nur zu cachen. Niemals darauf vertrauen, dass der cache da ist!
Caches werden oft eingeführt, obwohl nicht notwendig oder noch nicht notwendig.
Zuerst Stresstesten und dann cache einführen.
Bei Rest calls in Verbindung mit revers proxies cache headers von http nutzen!! proxies haben meist sehr effiziente caches
https://www.nginx.com/blog/nginx-caching-guide/
Caches sind oft "toys of developers" und machen die App oft unnötig kompliziert. Bei eigenen impl. caches sogar oft langsamer!!

