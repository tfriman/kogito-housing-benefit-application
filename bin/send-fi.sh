#!/usr/bin/env bash

# Sends simple benefit application

curl -X POST http://localhost:8080/application \
    -H 'content-type: application/json' \
    -H 'accept: application/json' \
    -d '{
    "application": {
	"alkuPvm": "2020-05-19",
	"ruokakunnanKoko": 0,
	"asuinMaakunta": "1",
	"kuntaRyhma": 1,
	"asuntoTiedot": {
	    "asunnonTyyppi": "VUOKRA",
	    "kuntaNumero": "092",
	    "asuinKunta": "Helsinki",
	    "vuokraEur": "500",
	    "vesiPerHenkiloEur": "15"

	},
	"language": "FI",
	"ruokakunnanJasenet": [
      {
	"etuNimi": "Maija",
	"hetu": "010101-0123",
	"sukuNimi": "Meikäläinen",
	"lisatilanTarve": false
      }
    ]
    }
}' | jq .
