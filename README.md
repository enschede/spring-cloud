## Aantekeningen bij Spring Cloud Netflix PoC

### Feign

### Ribbon

Ribbon seems to be not a standalone module anymore. It is part of Feign now.

Het is niet helemaal duidelijk wat de name= variabele van Ribbbon nog doet als Eureka en Zuul gebruikt worden.

### Eureka

Eureka update information can be delayed up to 1 minute. Therefor restarting a client can cause problem connecting for 1 minute.

### Zuul

Het lijkt erop dat een Zuul API filter meerdere filters kan uitvoeren. Zie hiervoor de method filterOrder.

Het is niet duidelijk waarom het filter 'null' terug geeft. Hoe wordt een gefilterd request dan verwerkt?

Ook hier duurt het even voordat de request foutloos wordt verwerkt.

### Sleuth en Zipkin

Sleuth dependencies toevoegen is voldoende. Er zijn geen code wijzigingen nodig. Logging is wel handig omdat daarmee de Sleuth-ids zichtbaar worden in de logging.

docker run -d --hostname my-rabbit --name some-rabbit rabbitmq

docker run -d --hostname my-rabbit -p 4369:4369 -p5671:5671 -p 5672:5672  --name some-rabbit rabbitmq

### Hystrix



### Reactive router (vanaf Spring Boot 2)

De essentie is dat alles nu in 1 thread draait. Snel, maar je moet voorkomen dat de thread blokkeert met een langlopende opdracht.
De aangeroepen functie geeft een Mono of Flux terug. Dat kan heel snel.
Pas bij het teruggeven van het reply bericht wordt de lambda in de Mono of Flux aangeroepen om de waarde te bepalen.

Dit werkt natuurlijk alleen als je leverancier ook reactive is of het bericht snel kan worden bepaald.

### ELK

Bron: https://github.com/deviantony/docker-elk.git

