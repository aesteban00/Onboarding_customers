#!/bin/bash
CON=`grep '^CONNECTIONSTRING' ./config.py | awk '{print $3}' | sed 's/"//g'`
DB=`grep '^DB' ./config.py | awk '{print $3}' | sed 's/"//g'`
COLL=`grep '^COLL' ./config.py | awk '{print $3}' | sed 's/"//g'`

echo "Modificando documentos en base de datos $DB en la colección $COLL"


echo "Proceso para borrar documentos"
mongoimport --uri $CON --collection $COLL --db $DB --type json --jsonArray --upsertFields "country,documentNumber" --mode delete --numInsertionWorkers 2 --file ./scripts/7_onboarding_countryC3_actualizado_david_delete.json --writeConcern "{w:'majority'}" &
echo "Proceso para insertar documentos"
mongoimport --uri $CON --collection $COLL --db $DB --type json --jsonArray --mode insert --numInsertionWorkers 2 --file ./scripts/7_onboarding_countryC3_actualizado_david_insert.json --writeConcern "{w:'majority'}" &
echo "Proceso para modificar documentos"
mongoimport --uri $CON --collection $COLL --db $DB --type json --jsonArray --upsertFields "country,documentNumber" --mode upsert --numInsertionWorkers 2 --file ./scripts/7_onboarding_countryC3_actualizado_david_upsert.json --writeConcern "{w:'majority'}" &
echo "Proceso para reemplazar documentos"
mongoimport --uri $CON --collection $COLL --db $DB --type json --jsonArray --upsertFields "country,documentNumber" --mode merge --numInsertionWorkers 2 --file ./scripts/7_onboarding_countryC3_actualizado_david_merge.json --writeConcern "{w:'majority'}" &


wait
echo "Todo hecho"
