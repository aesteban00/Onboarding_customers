#!/bin/bash
i=2
d=0
CON=`grep '^CONNECTIONSTRING' ./config.py | awk '{print $3}' | sed 's/"//g'`
DB=`grep '^DB' ./config.py | awk '{print $3}' | sed 's/"//g'`
COLL=`grep '^COLL' ./config.py | awk '{print $3}' | sed 's/"//g'`

cd ./scripts
echo "Importando documentos en base de datos $DB en la colección $COLL"

until [ $i -gt 6 ]
do
	((d=i-2))
	mongoimport --uri $CON --collection $COLL --db $DB --type json --jsonArray --upsertFields "country,documentNumber" --mode merge --numInsertionWorkers 100 --file $i'_onboarding_countryC'$d.json --writeConcern "{w:'majority'}" &
	#sleep $i;echo mongoimport $i'_onboarding_countryC'$d.json &
	((i=i+1))
done
wait
echo "Todo hecho"
