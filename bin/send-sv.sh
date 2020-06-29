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
	"language": "SV",
	"ruokakunnanJasenet": [
      {
	"etuNimi": "Maja",
	"hetu": "020202-1224",
	"sukuNimi": "Medborgare",
	"lisatilanTarve": false
      },
            {
	"etuNimi": "Mats",
	"hetu": "020202-1234",
	"sukuNimi": "Medborgare",
	"lisatilanTarve": false
      }

    ]
    }
}' | jq .
