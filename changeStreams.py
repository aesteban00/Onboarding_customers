#!/usr/bin/env python3

import asyncio
import time
import sys
import os
from pymongo import MongoClient
from threading import Thread

import sys
sys.path.append("./config.py")
import config as cfg


####
# Start script
####
print("==========================================")
print("    Change Stream Listener                ")
print("Change Stream Events currently monitored: ")
print("Insert, Update, Delete, Replace           ")
print("==========================================")


####
# Main start function
# Start each individual thread for each event
# Sleep momentarily after starting each thread
####
def main():
    print('Starting Change Stream Listener.\n')

    # Create the insert thread
    insert_loop = asyncio.new_event_loop()
    insert_loop.call_soon_threadsafe(insert_change_stream)
    t = Thread(target=start_loop, args=(insert_loop,))
    t.start()
    time.sleep(0.25)

    # Create the update thread
    update_loop = asyncio.new_event_loop()
    update_loop.call_soon_threadsafe(update_change_stream)
    t = Thread(target=start_loop, args=(update_loop,))
    t.start()
    time.sleep(0.25)

    # Create the delete thread
    delete_loop = asyncio.new_event_loop()
    delete_loop.call_soon_threadsafe(delete_change_stream)
    t = Thread(target=start_loop, args=(delete_loop,))
    t.start()
    time.sleep(0.25)

    # Create the replace thread
    replace_loop = asyncio.new_event_loop()
    replace_loop.call_soon_threadsafe(replace_change_stream)
    t = Thread(target=start_loop, args=(replace_loop,))
    t.start()
    time.sleep(0.25)

####
# Make sure the loop continues
####
def start_loop(loop):
    asyncio.set_event_loop(loop)
    loop.run_forever()


####
# Insert Change Stream
####
def insert_change_stream():
    print("Insert listener thread started.")
    mongo_client = MongoClient(MONGODB_ATLAS_URL)
    db = mongo_client[DATABASE]
    accounts_collection = db[COLLECTION]

    # Change stream pipeline
    pipeline = [
        {'$match': {'operationType': 'insert'}}
    ]

    try:
        for document in accounts_collection.watch(pipeline=pipeline, full_document='updateLookup'):
            result = "=== INSERT EVENT ===\n"
            result = result + "Customer: " + str(document['fullDocument']['firstName']) + '\n'
            #result = result + "Emailing user: " + document['fullDocument']['email'] + '\n'

            print(result)
    except KeyboardInterrupt:
        keyboard_shutdown()


####
# Update Change Stream
####
def update_change_stream():
    print("Update listener thread started.")
    mongo_client = MongoClient(MONGODB_ATLAS_URL)
    db = mongo_client[DATABASE]
    accounts_collection = db[COLLECTION]

    # Make sure resume counter does NOT exist as it is being used
    # to trigger the resume operation in another thread
    pipeline = [
        {'$match': {'operationType': 'update'}}
    ]

    try:
        for document in accounts_collection.watch(pipeline=pipeline, full_document='updateLookup'):
        #for document in accounts_collection.watch(pipeline=pipeline):
            result = "\n=== UPDATE EVENT ===\n"
            result = result + "Customer: " + str(document['fullDocument']['firstName']) + '\n'
            result = result + "Campos actualizados: " + str(document['updateDescription']) + '\n'

            print(result)

    except KeyboardInterrupt:
        keyboard_shutdown()


####
# Delete Change Stream
####
def delete_change_stream():
    print("Delete listener thread started.")
    mongo_client = MongoClient(MONGODB_ATLAS_URL)
    db = mongo_client[DATABASE]
    accounts_collection = db[COLLECTION]

    pipeline = [
        {'$match': {'operationType': 'delete'}}
    ]

    try:
        for document in accounts_collection.watch(pipeline=pipeline, full_document='updateLookup'):
            result = "\n=== DELETE EVENT ===\n"
            result = result + "Customer deleted from collection: " + str(document['ns']['coll']) + "\n"
            result = result + "Customer _id: " + str(document['documentKey']['_id']) + "\n"

            print(result)

    except KeyboardInterrupt:
        keyboard_shutdown()


####
# Replace Change Stream
####
def replace_change_stream():
    print("Replace listener thread started.")
    mongo_client = MongoClient(MONGODB_ATLAS_URL)
    db = mongo_client[DATABASE]
    accounts_collection = db[COLLECTION]

    pipeline = [
        {'$match': {'operationType': 'replace'}}
    ]

    try:

        for document in accounts_collection.watch(pipeline=pipeline, full_document='updateLookup'):
            result = "\n=== REPLACE EVENT ===\n"
            result = result + str(document) + "\n"

            print(result)

    except KeyboardInterrupt:
        keyboard_shutdown()

###
# "Gracefully" consume output via ctrl-c
###
def keyboard_shutdown():
    print('Interrupted\n')
    try:
        sys.exit(0)
    except SystemExit:
        os._exit(0)


####
# Constants
####
MONGODB_ATLAS_URL = cfg.CONNECTIONSTRING
DATABASE = cfg.DB
COLLECTION = cfg.COLL

####
# Main
####
if __name__ == '__main__':
    main()

