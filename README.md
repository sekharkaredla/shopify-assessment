# Description
A simple and sophisticated demonstration of backend services development using Spring Boot.
# Author
Anantha Sashi Sekhar Karedla

# Features implemented
- Basic CRUD Functionality.
- Filtering based on fields/inventory count/tags/other metadata.

# How to run the services

1) Goto the `res/jar` folder.
2) Download `apis.jar`.
3) Make sure you have `Java SE 11` or `OpenJDK 11` installed on your system.

The below command will start the Spring web services on localhost 8080 port
```text
java -jar apis.jar
```
Incase your 8080 port is busy, use the below command to run it on a different port.
```text
java -jar apis.jar --server.port=8081
```

Looks like this:-

1) <img alt="how_to_run" height="100" src="./res/images/how_to_run.png?raw=true" title="how_to_run_1"/>
2) <img alt="how_to_run 2" height="500" src="./res/images/how_to_run_2.png?raw=true" title="how_to_run_2"/>


# After running the services

Goto [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) and click on item-controller.

Direct link to [item-controller](http://localhost:8080/swagger-ui.html#/item45controller). 


# Some example runs (Basic CRUD)

1) Creating few items using the /item POST API.

looks like this :-

<img alt="create" height="800" src="./res/images/create_1.png?raw=true" title="create_1"/>

2) Getting all Items using the /item GET API.

```json
[
  {
    "item": {
      "id": "902a455a-e165-4a6a-9449-d1ccd64ce258",
      "itemName": "avgPhone",
      "itemType": "phone",
      "manufacturingCompany": "banana",
      "manufacturingDate": "2022-01-04T02:02:10.466+00:00",
      "tags": [
        "electronic",
        "cheap"
      ]
    }
  },
  {
    "item": {
      "id": "4e0e0257-e5f1-47a6-a1f5-9c891847b0cb",
      "itemName": "heavyCotton",
      "itemType": "cloth",
      "manufacturingCompany": "farmer",
      "manufacturingDate": "2022-01-01T02:02:10.466+00:00",
      "tags": [
        "country",
        "cheap"
      ]
    }
  },
  {
    "item": {
      "id": "bcd22922-3b03-4a99-b56a-e3fb27da7224",
      "itemName": "avgPhone",
      "itemType": "phone",
      "manufacturingCompany": "orange",
      "manufacturingDate": "2022-01-05T02:02:10.466+00:00",
      "tags": [
        "electronic",
        "costly"
      ]
    }
  },
  {
    "item": {
      "id": "0c2ac0a1-5675-436f-8f5c-a7cbda88b78c",
      "itemName": "tinyPhone",
      "itemType": "phone",
      "manufacturingCompany": "banana",
      "manufacturingDate": "2022-01-05T02:02:10.466+00:00",
      "tags": [
        "electronic",
        "cheap"
      ]
    }
  }
]
```

3) Getting an individual item using the /item/{itemId} GET API.

looks like this:-

<img alt="get" height="800" src="./res/images/get_1.png?raw=true" title="get_1"/>


4) Updating an item using the /item/{itemId} PUT API.
Adding an extra tag "broken" to the item.


looks like this:-

<img alt="put" height="800" src="./res/images/put_1.png?raw=true" title="put_1"/>

5) Deleting an item using the /item/{itemId} DELETE API.
Deleting the item which was updated above.

looks like this:-

<img alt="delete" height="500" src="./res/images/delete_1.png?raw=true" title="delete_1"/>

# Fun with filters

1) filter based on manufacturing company

```text
request URL : http://localhost:8080/item?company=banana
```
response:
```json
[
  {
    "item": {
      "id": "aa1fa3c7-6adb-4a2a-be93-5bccb1f123ba",
      "itemName": "slowPc",
      "itemType": "laptop",
      "manufacturingCompany": "banana",
      "manufacturingDate": "2021-01-01T02:02:10.466+00:00",
      "tags": [
        "costly",
        "not_worth"
      ]
    }
  },
  {
    "item": {
      "id": "902a455a-e165-4a6a-9449-d1ccd64ce258",
      "itemName": "avgPhone",
      "itemType": "phone",
      "manufacturingCompany": "banana",
      "manufacturingDate": "2022-01-04T02:02:10.466+00:00",
      "tags": [
        "electronic",
        "cheap"
      ]
    }
  }
]
```

2) filter based on manufacturing company and item type

```text
request URL : http://localhost:8080/item?type=phone&company=banana
```
response:
```json
[
  {
    "item": {
      "id": "902a455a-e165-4a6a-9449-d1ccd64ce258",
      "itemName": "avgPhone",
      "itemType": "phone",
      "manufacturingCompany": "banana",
      "manufacturingDate": "2022-01-04T02:02:10.466+00:00",
      "tags": [
        "electronic",
        "cheap"
      ]
    }
  }
]
```


```text
request URL : http://localhost:8080/item?type=laptop&company=banana
```
response:
```json
[
  {
    "item": {
      "id": "aa1fa3c7-6adb-4a2a-be93-5bccb1f123ba",
      "itemName": "slowPc",
      "itemType": "laptop",
      "manufacturingCompany": "banana",
      "manufacturingDate": "2021-01-01T02:02:10.466+00:00",
      "tags": [
        "costly",
        "not_worth"
      ]
    }
  }
]
```

3) Single tag filter

```text
request URL : http://localhost:8080/item?tags=electronic
```
response:
```json
[
  {
    "item": {
      "id": "902a455a-e165-4a6a-9449-d1ccd64ce258",
      "itemName": "avgPhone",
      "itemType": "phone",
      "manufacturingCompany": "banana",
      "manufacturingDate": "2022-01-04T02:02:10.466+00:00",
      "tags": [
        "electronic",
        "cheap"
      ]
    }
  },
  {
    "item": {
      "id": "bcd22922-3b03-4a99-b56a-e3fb27da7224",
      "itemName": "avgPhone",
      "itemType": "phone",
      "manufacturingCompany": "orange",
      "manufacturingDate": "2022-01-05T02:02:10.466+00:00",
      "tags": [
        "electronic",
        "costly"
      ]
    }
  },
  {
    "item": {
      "id": "6d327c94-d25e-4814-8ab0-d969a9bb135e",
      "itemName": "tinyPC",
      "itemType": "laptop",
      "manufacturingCompany": "mango",
      "manufacturingDate": "2021-10-01T02:02:10.466+00:00",
      "tags": [
        "cheap",
        "electronic"
      ]
    }
  }
]
```

4) Multi tag filter

```text
request URL : http://localhost:8080/item?tags=electronic&tags=cheap
```

response
```json
[
  {
    "item": {
      "id": "902a455a-e165-4a6a-9449-d1ccd64ce258",
      "itemName": "avgPhone",
      "itemType": "phone",
      "manufacturingCompany": "banana",
      "manufacturingDate": "2022-01-04T02:02:10.466+00:00",
      "tags": [
        "electronic",
        "cheap"
      ]
    }
  },
  {
    "item": {
      "id": "6d327c94-d25e-4814-8ab0-d969a9bb135e",
      "itemName": "tinyPC",
      "itemType": "laptop",
      "manufacturingCompany": "mango",
      "manufacturingDate": "2021-10-01T02:02:10.466+00:00",
      "tags": [
        "cheap",
        "electronic"
      ]
    }
  }
]
```


5) Multi tag filter with item type

```text
request URL : http://localhost:8080/item?type=phone&tags=electronic&tags=cheap
```
response
```json
[
  {
    "item": {
      "id": "902a455a-e165-4a6a-9449-d1ccd64ce258",
      "itemName": "avgPhone",
      "itemType": "phone",
      "manufacturingCompany": "banana",
      "manufacturingDate": "2022-01-04T02:02:10.466+00:00",
      "tags": [
        "electronic",
        "cheap"
      ]
    }
  }
]
```


6) The manufacturing date filter

```text
request URL : http://localhost:8080/item?date=2021-01-05
```
response (there is a timezone, requesting timezone is different from UTC)
```json
[
  {
    "item": {
      "id": "9f860b12-ed97-4ea0-a422-e0328600c4c1",
      "itemName": "k",
      "itemType": "b",
      "manufacturingCompany": "c",
      "manufacturingDate": "2021-01-06T02:41:12.353+00:00",
      "tags": [
        "x"
      ]
    }
  },
  {
    "item": {
      "id": "4ccfbaa4-9f5b-4c06-ab25-6a335ece8920",
      "itemName": "a",
      "itemType": "b",
      "manufacturingCompany": "c",
      "manufacturingDate": "2021-01-06T02:41:12.353+00:00",
      "tags": [
        "x"
      ]
    }
  }
]
```

# Error handling

1) Handling invalid item ids.

looks like this:-

<img alt="invalid_id" height="500" src="./res/images/invalid_id_1.png?raw=true" title="invalid_id_1"/>


2) Invalid inputs during create

looks like this:-

<img alt="invalid_inputs" height="600" src="./res/images/invalid_inp_1.png?raw=true" title="invalid_inp_1"/>

