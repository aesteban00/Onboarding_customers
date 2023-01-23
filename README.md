# MongoDB Atlas - Onboarding of customers

---
## Setup

**_1. Copy MongoDB Atlas connection string (we will use on step 5)_**

![conn_string_1](img/conn_string_1.png "conn_string_1")

![conn_string_2](img/conn_string_2.png "conn_string_2")


**_2. Configure MongoDB Atlas IP WhiteList to allow all (don't do in Production please !!)_**

![add_all_IPs](img/add_all_IPs.png "add_all_IPs")


**_3. Start GitHub Codespaces_**

![create_Codespace](img/create_Codespace.png "create_Codespace")


**_4. Change README.md to Preview mode_**

![readme](img/readme.png "readme")


**_5. Paste MongoDB Atlas connection string (obtained on step 1)_**

```
Edit ./config.py and insert your MongoDB Atlas connection string
```

**_6. Execute setup\_1.sh_**

```bash
./scripts/setup_1.sh
```

**_7. Import JSON files to MongoDB Atlas (it should take 3 min for 500.000 docs using small Lab cluster)_**

```bash
./scripts/import_JSON_files.sh
```

![mongoimport](img/mongoimport.png "mongoimport")

---
## Execution Lab 1 and 2 on Jupyter NOTEBOOK

**_Jump to Jupyter NOTEBOOK to practice with MongoDB Python_**

```
Open ./NOTEBOOK.ipynb and execute the exercises
```

---
## Execution Lab 3 with ChangeStreams Python script

**_Lab 3 - Exercise 1 - Start ChangeStreams_**

![modify_docs](img/modify_docs.png "modify_docs")

**_Lab 3 - Exercise 2 - Import JSON files with changes (from Bash shell, not ChangeStreams shell from step before)_**

These 4 files contains updates, inserts, deletes and upserts. With this script all operations will run in parallel and events can be seen in the Python ChangeStream app.
Execute on bash shell:

![modify_docs_by_bash](img/modify_docs_by_bash.png "modify_docs_by_bash")

```bash
./changestreams_modify_JSON_files.sh
```

**_Lab 3 - Challenge time!_**

Edit the Python script ./changeStreams.py to do the following:
- Filter updates and print in the console only the updates performed on customers where document type is a passport.


---
## MongoDB Atlas Charts

**_A chart can be created based on the aggregation to display the result of the aggregation in a chart_**

![charts1](img/charts1.png "Adding the aggregation to the chart")
![charts2](img/charts2.png "Chart with aggregation created")

This is the aggregation pipeline you can use:

```yaml
[{$set:{
	companyEndDate:{
		$toDate:'$companyEndDate'
	}
	,companyStartDate:{
		$toDate:'$companyStartDate'
	}
}
}
,{
$group:{
	_id:{
		$year:'$companyStartDate'
	}
	,numberofcompanies:{
		$sum:1
	}
}
}
,{
$sort:{
	_id:-1
}
}
,{
$set:{
	creationyear:'$_id'
}
}
,{
$project:{
	creationyear:1,numberofcompanies:1,_id:0
}
}]
```


**_We can create other types of charts. Next chart represents company names by number (word size) and country (word colour)_**

![charts3](img/charts3.png "Word chart")

**_This is a general view with all charts_**
![charts4](img/charts4.png "General view with all charts")