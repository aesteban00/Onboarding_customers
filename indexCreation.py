############################## CREATE MongoDB Atlas connection ##############################
import pymongo
from pprint import pprint

import sys
sys.path.append("../config.py")
import config as cfg

# Connect to Atlas
client = pymongo.MongoClient(cfg.CONNECTIONSTRING)
db = client[cfg.DB]
coll = db[cfg.COLL]

############################## CREATE INDEX ##############################

resp = coll.create_index([ ("country", 1),("documentNumber", 1) ])
print ("index response:", resp)

