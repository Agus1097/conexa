# Documentación de Challenge - Conexa

Es una aplicación para integrar api de Star Wars (https://swapi.tech/documentation) desarrollada con Java 8, Spring Boot y H2, con sistema de autenticación con Spring Security y JWT. <br/>
Permite obtener todos los datos de forma paginada, mediante ID y/o nombre de las entidades Films, People, Starships y Vehicles. <br/>

Para poder utilizarla por primera vez es necesario registrarse y luego loguerse. Este último nos brindará un token que lo vamos a utilizar en los endpoints que lo requieran.

## Endpoints

Debe utilizar una aplicación similar a Postman para ejecutar las siguientes URLs. En donde hay colocar los endpoints que se han dejado en la documentación, con su autenticación si es necesario y los parametros o resquestBody que le correspondan.

## Autenticación

- ### Registrarse

**Descripción:** `Este endpoint permite crear un usuario y contraseña. Es necesario estar registrado para ver las entidades de Star Wars. No se permiten usuarios repetidos`

**Endpoint:** `https://conexa-production.up.railway.app/auth/register`

**Método:** `POST`

**Autenticación:** `Ninguna`

**Request:**
```json
{
    "username": "test",
    "password": "12345"
}
```

**Response 200 OK:**
```json
{
    "message": "Usuario creado con éxito"
}
```

**Response 409 Conflict:**
```json
{
    "message": "El usuario test ya se encuentra registrado",
    "code": 409
}
```
- ### Loguearse

**Descripción:** `Este endpoint permite loguearse con usuario y contraseña registrados anteriormente. Es necesario estar logueado para ver las entidades de Star Wars. Si el usuario o la contraseña no coinciden no le va a permitir loguearse. Y la response va a brindar un Bearer Token que se va a utilizar para los endpoints que lo requieran.`

**Endpoint:** `https://conexa-production.up.railway.app/auth/login`

**Método:** `POST`

**Autenticación:** `Ninguna`

**Request:**
```json
{
    "username": "test",
    "password": "12345"
}
```

**Response 200 OK:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzIwNTAzODYyLCJleHAiOjE3MjA1OTAyNjJ9.qLPZOjbNfoyH2ToE5Dfnvs93e3ppcryvkrlGVJAToqw"
}
```

**Response 401 Unauthorized:**
```json
{
    "message": "Usuario o contraseña incorrecta",
    "code": 401
}
```
## Films

- ### Listar todas las películas

**Descripción:** `Este endpoint permite listar todos las películas de StarWars.`

**Endpoint:** `https://conexa-production.up.railway.app/films`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 200 OK:**
```json
{
    "message": "ok",
    "result": [
        {
            "properties": {
                "characters": [
                    "http://localhost:8080/people/1",
                    "http://localhost:8080/people/2",
                    "http://localhost:8080/people/3",
                    "http://localhost:8080/people/4",
                    "http://localhost:8080/people/5",
                    "http://localhost:8080/people/6",
                    "http://localhost:8080/people/7",
                    "http://localhost:8080/people/10",
                    "http://localhost:8080/people/11",
                    "http://localhost:8080/people/12",
                    "http://localhost:8080/people/13",
                    "http://localhost:8080/people/20",
                    "http://localhost:8080/people/21",
                    "http://localhost:8080/people/33",
                    "http://localhost:8080/people/35",
                    "http://localhost:8080/people/46",
                    "http://localhost:8080/people/51",
                    "http://localhost:8080/people/52",
                    "http://localhost:8080/people/53",
                    "http://localhost:8080/people/54",
                    "http://localhost:8080/people/55",
                    "http://localhost:8080/people/56",
                    "http://localhost:8080/people/58",
                    "http://localhost:8080/people/63",
                    "http://localhost:8080/people/64",
                    "http://localhost:8080/people/67",
                    "http://localhost:8080/people/68",
                    "http://localhost:8080/people/75",
                    "http://localhost:8080/people/78",
                    "http://localhost:8080/people/79",
                    "http://localhost:8080/people/80",
                    "http://localhost:8080/people/81",
                    "http://localhost:8080/people/82",
                    "http://localhost:8080/people/83"
                ],
                "starships": [
                    "http://localhost:8080/starships/2",
                    "http://localhost:8080/starships/32",
                    "http://localhost:8080/starships/48",
                    "http://localhost:8080/starships/59",
                    "http://localhost:8080/starships/61",
                    "http://localhost:8080/starships/63",
                    "http://localhost:8080/starships/64",
                    "http://localhost:8080/starships/65",
                    "http://localhost:8080/starships/66",
                    "http://localhost:8080/starships/68",
                    "http://localhost:8080/starships/74",
                    "http://localhost:8080/starships/75"
                ],
                "vehicles": [
                    "http://localhost:8080/vehicles/33",
                    "http://localhost:8080/vehicles/50",
                    "http://localhost:8080/vehicles/53",
                    "http://localhost:8080/vehicles/56",
                    "http://localhost:8080/vehicles/60",
                    "http://localhost:8080/vehicles/62",
                    "http://localhost:8080/vehicles/67",
                    "http://localhost:8080/vehicles/69",
                    "http://localhost:8080/vehicles/70",
                    "http://localhost:8080/vehicles/71",
                    "http://localhost:8080/vehicles/72",
                    "http://localhost:8080/vehicles/73",
                    "http://localhost:8080/vehicles/76"
                ],
                "producer": "Rick McCallum",
                "title": "Revenge of the Sith",
                "director": "George Lucas",
                "episode_id": 3,
                "release_date": "2005-05-19",
                "opening_crawl": "War! The Republic is crumbling\r\nunder attacks by the ruthless\r\nSith Lord, Count Dooku.\r\nThere are heroes on both sides.\r\nEvil is everywhere.\r\n\r\nIn a stunning move, the\r\nfiendish droid leader, General\r\nGrievous, has swept into the\r\nRepublic capital and kidnapped\r\nChancellor Palpatine, leader of\r\nthe Galactic Senate.\r\n\r\nAs the Separatist Droid Army\r\nattempts to flee the besieged\r\ncapital with their valuable\r\nhostage, two Jedi Knights lead a\r\ndesperate mission to rescue the\r\ncaptive Chancellor...."
            },
            "uid": "6"
        }
    ]
}
```
- ### Buscar película por ID
  
**Descripción:** `Este endpoint permite buscar una película de StarWars por ID. Describe detalladamente a la película.`

**Endpoint:** `https://conexa-production.up.railway.app/films/1`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 200 OK:**
```json
{
    "message": "ok",
    "result": {
        "properties": {
            "characters": [
                "http://localhost:8080/people/1",
                "http://localhost:8080/people/2",
                "http://localhost:8080/people/3",
                "http://localhost:8080/people/4",
                "http://localhost:8080/people/5",
                "http://localhost:8080/people/6",
                "http://localhost:8080/people/7",
                "http://localhost:8080/people/8",
                "http://localhost:8080/people/9",
                "http://localhost:8080/people/10",
                "http://localhost:8080/people/12",
                "http://localhost:8080/people/13",
                "http://localhost:8080/people/14",
                "http://localhost:8080/people/15",
                "http://localhost:8080/people/16",
                "http://localhost:8080/people/18",
                "http://localhost:8080/people/19",
                "http://localhost:8080/people/81"
            ],
            "starships": [
                "http://localhost:8080/starships/2",
                "http://localhost:8080/starships/3",
                "http://localhost:8080/starships/5",
                "http://localhost:8080/starships/9",
                "http://localhost:8080/starships/10",
                "http://localhost:8080/starships/11",
                "http://localhost:8080/starships/12",
                "http://localhost:8080/starships/13"
            ],
            "vehicles": [
                "http://localhost:8080/vehicles/4",
                "http://localhost:8080/vehicles/6",
                "http://localhost:8080/vehicles/7",
                "http://localhost:8080/vehicles/8"
            ],
            "producer": "Gary Kurtz, Rick McCallum",
            "title": "A New Hope",
            "director": "George Lucas",
            "episode_id": 4,
            "release_date": "1977-05-25",
            "opening_crawl": "It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy...."
        },
        "uid": "1"
    }
}
```
**Endpoint:** `https://conexa-production.up.railway.app/films/8`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 404 NotFound:**
```json
{
    "message": "FILM not found",
    "code": 404
}
```

## People

- ### Listar todos los personajes

**Descripción:** `Este endpoint permite listar todos los personajes de StarWars de forma paginada. En caso de no pasarle el parametro page y/o el parametro limit por defecto va a mostrar los 10 primeros personajes en la página 1.`

**Endpoint:** `https://conexa-production.up.railway.app/people?page=2&limit=10`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 200 OK:**
```json
{
    "message": "ok",
    "previous": "http://localhost:8080/people?page=1&limit=10",
    "next": "http://localhost:8080/people?page=3&limit=10",
    "results": [
        {
            "name": "Anakin Skywalker",
            "uid": "11"
        },
        {
            "name": "Wilhuff Tarkin",
            "uid": "12"
        },
        {
            "name": "Chewbacca",
            "uid": "13"
        },
        {
            "name": "Han Solo",
            "uid": "14"
        },
        {
            "name": "Greedo",
            "uid": "15"
        },
        {
            "name": "Jabba Desilijic Tiure",
            "uid": "16"
        },
        {
            "name": "Wedge Antilles",
            "uid": "18"
        },
        {
            "name": "Jek Tono Porkins",
            "uid": "19"
        },
        {
            "name": "Yoda",
            "uid": "20"
        },
        {
            "name": "Palpatine",
            "uid": "21"
        }
    ],
    "total_records": 82,
    "total_pages": 9
}
```
- ### Buscar personaje por ID
  
**Descripción:** `Este endpoint permite buscar un personaje de StarWars por ID. Describe detalladamente al personaje.`

**Endpoint:** `https://conexa-production.up.railway.app/people/1`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 200 OK:**
```json
{
    "message": "ok",
    "result": {
        "properties": {
            "height": "172",
            "mass": "77",
            "gender": "male",
            "name": "Luke Skywalker",
            "hair_color": "blond",
            "skin_color": "fair",
            "eye_color": "blue",
            "birth_year": "19BBY"
        },
        "uid": "1"
    }
}
```
**Endpoint:** `https://conexa-production.up.railway.app/people/17`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 404 NotFound:**
```json
{
    "message": "PEOPLE not found",
    "code": 404
}
```

- ### Buscar personaje por name
  
**Descripción:** `Este endpoint permite buscar un personaje de StarWars por el atributo name. Describe detalladamente al personaje.`

**Endpoint:** `https://conexa-production.up.railway.app/people/?name=Lu`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 200 OK:**
```json
{
    "message": "ok",
    "result": [
        {
            "properties": {
                "height": "172",
                "mass": "77",
                "gender": "male",
                "name": "Luke Skywalker",
                "hair_color": "blond",
                "skin_color": "fair",
                "eye_color": "blue",
                "birth_year": "19BBY"
            },
            "uid": "1"
        },
        {
            "properties": {
                "height": "170",
                "mass": "56.2",
                "gender": "female",
                "name": "Luminara Unduli",
                "hair_color": "black",
                "skin_color": "yellow",
                "eye_color": "blue",
                "birth_year": "58BBY"
            },
            "uid": "64"
        }
    ]
}
```
## Starships

- ### Listar todos las naves espaciales

**Descripción:** `Este endpoint permite listar todos las naves espaciales de StarWars de forma paginada. En caso de no pasarle el parametro page y/o el parametro limit por defecto va a mostrar las 10 primeras naves espaciales en la página 1.`

**Endpoint:** `https://conexa-production.up.railway.app/starships?page=3&limit=10`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 200 OK:**
```json
{
    "message": "ok",
    "previous": "http://localhost:8080/starships?page=2&limit=10",
    "next": "http://localhost:8080/starships?page=4&limit=10",
    "results": [
        {
            "name": "J-type diplomatic barge",
            "uid": "43"
        },
        {
            "name": "Scimitar",
            "uid": "41"
        },
        {
            "name": "AA-9 Coruscant freighter",
            "uid": "47"
        },
        {
            "name": "Jedi starfighter",
            "uid": "48"
        },
        {
            "name": "Republic Assault ship",
            "uid": "52"
        },
        {
            "name": "H-type Nubian yacht",
            "uid": "49"
        },
        {
            "name": "Trade Federation cruiser",
            "uid": "59"
        },
        {
            "name": "Solar Sailer",
            "uid": "58"
        },
        {
            "name": "Theta-class T-2c shuttle",
            "uid": "61"
        },
        {
            "name": "Republic attack cruiser",
            "uid": "63"
        }
    ],
    "total_records": 36,
    "total_pages": 4
}
```
- ### Buscar nave espacial por ID
  
**Descripción:** `Este endpoint permite buscar una nave espacial de StarWars por ID. Describe detalladamente a la nave espacial.`

**Endpoint:** `https://conexa-production.up.railway.app/starships/2`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 200 OK:**
```json
{
    "message": "ok",
    "result": {
        "properties": {
            "model": "CR90 corvette",
            "manufacturer": "Corellian Engineering Corporation",
            "length": "150",
            "crew": "30-165",
            "passengers": "600",
            "consumables": "1 year",
            "starship_class": "corvette",
            "cost_in_credits": "3500000",
            "max_atmosphering_speed": "950",
            "hyperdrive_rating": "2.0",
            "MGLT": "60",
            "cargo_capacity": "3000000",
            "name": "CR90 corvette"
        },
        "uid": "2"
    }
}
```
**Endpoint:** `https://conexa-production.up.railway.app/starships/1`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 404 NotFound:**
```json
{
    "message": "STARSHIP not found",
    "code": 404
}
```

- ### Buscar nave espacial por name
  
**Descripción:** `Este endpoint permite buscar una nave espacial de StarWars por el atributo name. Describe detalladamente a la nave espacial.`

**Endpoint:** `https://conexa-production.up.railway.app/starships/?name=Cr`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 200 OK:**
```json
{
    "message": "ok",
    "result": [
        {
            "properties": {
                "model": "CR90 corvette",
                "manufacturer": "Corellian Engineering Corporation",
                "length": "150",
                "crew": "30-165",
                "passengers": "600",
                "consumables": "1 year",
                "starship_class": "corvette",
                "cost_in_credits": "3500000",
                "max_atmosphering_speed": "950",
                "hyperdrive_rating": "2.0",
                "MGLT": "60",
                "cargo_capacity": "3000000",
                "name": "CR90 corvette"
            },
            "uid": "2"
        },
        {
            "properties": {
                "model": "Sentinel-class landing craft",
                "manufacturer": "Sienar Fleet Systems, Cyngus Spaceworks",
                "length": "38",
                "crew": "5",
                "passengers": "75",
                "consumables": "1 month",
                "starship_class": "landing craft",
                "cost_in_credits": "240000",
                "max_atmosphering_speed": "1000",
                "hyperdrive_rating": "1.0",
                "MGLT": "70",
                "cargo_capacity": "180000",
                "name": "Sentinel-class landing craft"
            },
            "uid": "5"
        },
        {
            "properties": {
                "model": "MC80 Liberty type Star Cruiser",
                "manufacturer": "Mon Calamari shipyards",
                "length": "1200",
                "crew": "5400",
                "passengers": "1200",
                "consumables": "2 years",
                "starship_class": "Star Cruiser",
                "cost_in_credits": "104000000",
                "max_atmosphering_speed": "n/a",
                "hyperdrive_rating": "1.0",
                "MGLT": "60",
                "cargo_capacity": "unknown",
                "name": "Calamari Cruiser"
            },
            "uid": "27"
        },
        {
            "properties": {
                "model": "Consular-class cruiser",
                "manufacturer": "Corellian Engineering Corporation",
                "length": "115",
                "crew": "9",
                "passengers": "16",
                "consumables": "unknown",
                "starship_class": "Space cruiser",
                "cost_in_credits": "unknown",
                "max_atmosphering_speed": "900",
                "hyperdrive_rating": "2.0",
                "MGLT": "unknown",
                "cargo_capacity": "unknown",
                "name": "Republic Cruiser"
            },
            "uid": "31"
        },
        {
            "properties": {
                "model": "Providence-class carrier/destroyer",
                "manufacturer": "Rendili StarDrive, Free Dac Volunteers Engineering corps.",
                "length": "1088",
                "crew": "600",
                "passengers": "48247",
                "consumables": "4 years",
                "starship_class": "capital ship",
                "cost_in_credits": "125000000",
                "max_atmosphering_speed": "1050",
                "hyperdrive_rating": "1.5",
                "MGLT": "unknown",
                "cargo_capacity": "50000000",
                "name": "Trade Federation cruiser"
            },
            "uid": "59"
        },
        {
            "properties": {
                "model": "Senator-class Star Destroyer",
                "manufacturer": "Kuat Drive Yards, Allanteen Six shipyards",
                "length": "1137",
                "crew": "7400",
                "passengers": "2000",
                "consumables": "2 years",
                "starship_class": "star destroyer",
                "cost_in_credits": "59000000",
                "max_atmosphering_speed": "975",
                "hyperdrive_rating": "1.0",
                "MGLT": "unknown",
                "cargo_capacity": "20000000",
                "name": "Republic attack cruiser"
            },
            "uid": "63"
        }
    ]
}
```
## Vehicles

- ### Listar todos los vehículos

**Descripción:** `Este endpoint permite listar todos los vehículos de StarWars de forma paginada. En caso de no pasarle el parametro page y/o el parametro limit por defecto va a mostrar los 10 primeros vehículos en la página 1.`

**Endpoint:** `https://conexa-production.up.railway.app/vehicles?page=2&limit=10`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 200 OK:**
```json
{
    "message": "ok",
    "previous": "http://localhost:8080/vehicles?page=1&limit=10",
    "next": "http://localhost:8080/vehicles?page=3&limit=10",
    "results": [
        {
            "name": "Bantha-II cargo skiff",
            "uid": "25"
        },
        {
            "name": "Imperial Speeder Bike",
            "uid": "30"
        },
        {
            "name": "TIE/IN interceptor",
            "uid": "26"
        },
        {
            "name": "Vulture Droid",
            "uid": "33"
        },
        {
            "name": "Multi-Troop Transport",
            "uid": "34"
        },
        {
            "name": "Armored Assault Tank",
            "uid": "35"
        },
        {
            "name": "Single Trooper Aerial Platform",
            "uid": "36"
        },
        {
            "name": "C-9979 landing craft",
            "uid": "37"
        },
        {
            "name": "Tribubble bongo",
            "uid": "38"
        },
        {
            "name": "Sith speeder",
            "uid": "42"
        }
    ],
    "total_records": 39,
    "total_pages": 4
}
```
- ### Buscar vehículo por ID
  
**Descripción:** `Este endpoint permite buscar un vehículo de StarWars por ID. Describe detalladamente al vehículo.`

**Endpoint:** `https://conexa-production.up.railway.app/vehicles/2`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 200 OK:**
```json
{
    "message": "ok",
    "result": {
        "properties": {
            "model": "t-47 airspeeder",
            "manufacturer": "Incom corporation",
            "length": "4.5",
            "crew": "2",
            "passengers": "0",
            "consumables": "none",
            "films": [],
            "pilots": [
                "http://localhost:8080/people/1",
                "http://localhost:8080/people/18"
            ],
            "name": "Snowspeeder",
            "vehicle_class": "airspeeder",
            "cost_in_credits": "unknown",
            "max_atmosphering_speed": "650",
            "cargo_capacity": "10"
        },
        "uid": "14"
    }
}
```
**Endpoint:** `https://conexa-production.up.railway.app/vehicles/1`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 404 NotFound:**
```json
{
    "message": "VEHICLE not found",
    "code": 404
}
```

- ### Buscar vehículos por name
  
**Descripción:** `Este endpoint permite buscar vehículos de StarWars por el atributo name. Describe detalladamente al vehículo.`

**Endpoint:** `https://conexa-production.up.railway.app/vehicles/?name=Sa`

**Método:** `GET`

**Autenticación:** `Bearer Token`

**Response 200 OK:**
```json
{
    "message": "ok",
    "result": [
        {
            "properties": {
                "model": "Digger Crawler",
                "manufacturer": "Corellia Mining Corporation",
                "length": "36.8 ",
                "crew": "46",
                "passengers": "30",
                "consumables": "2 months",
                "films": [],
                "pilots": [],
                "name": "Sand Crawler",
                "vehicle_class": "wheeled",
                "cost_in_credits": "150000",
                "max_atmosphering_speed": "30",
                "cargo_capacity": "50000"
            },
            "uid": "4"
        },
        {
            "properties": {
                "model": "Modified Luxury Sail Barge",
                "manufacturer": "Ubrikkian Industries Custom Vehicle Division",
                "length": "30",
                "crew": "26",
                "passengers": "500",
                "consumables": "Live food tanks",
                "films": [],
                "pilots": [],
                "name": "Sail barge",
                "vehicle_class": "sail barge",
                "cost_in_credits": "285000",
                "max_atmosphering_speed": "100",
                "cargo_capacity": "2000000"
            },
            "uid": "24"
        },
        {
            "properties": {
                "model": "Armoured Assault Tank",
                "manufacturer": "Baktoid Armor Workshop",
                "length": "9.75",
                "crew": "4",
                "passengers": "6",
                "consumables": "unknown",
                "films": [],
                "pilots": [],
                "name": "Armored Assault Tank",
                "vehicle_class": "repulsorcraft",
                "cost_in_credits": "unknown",
                "max_atmosphering_speed": "55",
                "cargo_capacity": "unknown"
            },
            "uid": "35"
        }
    ]
}
```
