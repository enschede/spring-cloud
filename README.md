## Aantekeningen bij Spring Cloud Netflix PoC

### Feign

### Ribbon

Ribbon seems to be not a standalone module anymore. It is part of Feign now.

### Eureka

Eureka update information can be delayed up to 1 minute. Therefor restarting a client can cause problem connecting for 1 minute.

### Zuul

Het lijkt erop dat een Zuul API filter meerdere filters kan uitvoeren. Zie hiervoor de method filterOrder.

Het is niet duidelijk waarom het filter 'null' terug geeft. Hoe wordt een gefilterd request dan verwerkt?

Ook hier duurt het even voordat de request foutloos wordt verwerkt.

