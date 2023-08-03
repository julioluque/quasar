# Quasar project


Este es un proyecto es un confidencial perteneciente a la rebelion. No lo cuentes a nadie que:

- LEVEL 1
  - 1.1 Triangula posiciones
    - 1.1.1 Formula de triangulacion general
    - 1.1.2 Libreria super secreta de com.lemmingapex.trilateration
  - 1.2 Intercepta mensajes enemigos
    - 1.2.1 
    - 1.2.2 Hacemos split sobre lista original
    - 1.2.3 validamos tamaños de mensajes
    - 1.2.4 invertimos la matriz para manipular mensajes
  - 1.3 Ejecucion del programa

- LEVEL 2
  - 2.1 
    - 2.1.1 
    - 2.1.2 
  - 2.2 
    - 2.2.1
    - 2.2.2

- LEVEL 3
  - 2.1
    - 2.1.1
    - 2.1.2
  - 2.2
    - 2.2.1
    - 2.2.2
- EJECUCION DEL PROGRAMA

## LEVEL 1

### 1.1 Triangula posiciones
Triangula posiciones con dos metodos:

 

#### 1.1.1 Formula de triangulacion general

	Formula de triangulacion para encontrar las coordenadas del origen (x, y)
		
	Dadas las coordenadas (x1, y1), (x2, y2) y (x3, y3) Con distancia d1, d2 y d3
		
	https://www.geogebra.org/graphing
		
	#1_Diferencias - calculamos las diferencias de coordenadas entre cada
	satelite y el origen: dif1_x = x1 - x dif1_y = y1 - y dif2_x = x2 - x dif2_y
	= y2 - y dif3_x = x3 - x dif3_y = y3 - y
		
	#Distancias - establecemos un sistema de ecuaciones usando las distancias
	desde los satelites al origen: (dif1_x)^2 + (dif1_y)^2 = d1^2 (dif2_x)^2 +
	(dif2_y)^2 = d2^2 (dif3_x)^2 + (dif3_y)^2 = d3^2
		
	#3_Coordenadas - resolvemos el sistema de ecuaciones para encontrar las
	coordenadas (x, y) del origen.

#### 1.1.2 Libreria super secreta de com.lemmingapex.trilateration fuente:
	https://github.com/lemmingapex/trilateration

	<!-- TRIANGULACION-->
	<dependency>
		<groupId>com.lemmingapex.trilateration</groupId>
		<artifactId>trilateration</artifactId>
		<version>1.0.2</version>
	</dependency>	

### 1.2 Intercepta mensajes enemigos


#### 1.2.1 Libreria para validar palabras.

    <!-- DICCIONARIO -->
    <dependency>
        <groupId>org.languagetool</groupId>
        <artifactId>language-es</artifactId>
        <version>6.1</version>
    </dependency>

#### 1.2.2 Hacemos split sobre lista original
    Validamos las palabras usando el diccionario en castellano. 
    Las no reconocidas se reemplazan por espacio 

#### 1.2.3 validamos tamaños de mensajes
    Validamos cantidad de mensajes y el tamaño de cada mensaje para poder armar el mismo.

#### 1.2.4 invertimos la matriz para manipular mensajes
cambiamos:

		x1 x2 x3 x4 
		y1 y2 y3 y4
		z1 z2 z3 z4

por:

		x1 y1 z1
		x2 y2 z2
		x3 y3 z3
		x4 y4 z4

Asi podemos manipuar el arra y mensajes y compararlos


## LEVEL 2
- Servicio POST.
- Consume los servicios del nivel 1
- BASEURL: localhost:8080 
- http://BASEURL/topsecret/

RequestBody.

    {
        "satellites": [
            {
                "name": "kenobi",
                "distance": 100.0,
                "message": [
                    "este",
                    "",
                    "",
                    "mensaje",
                    ""
                ]
            },
            {
                "name": "skywalker",
                "distance": 115.5,
                "message": [
                    "",
                    "es",
                    "",
                    "",
                    "secreto"
                ]
            },
            {
                "name": "sato",
                "distance": 142.7,
                "message": [
                    "este",
                    "",
                    "un",
                    "",
                    ""
                ]
            }
        ]
    }

ResponseBody

    {
        "position": {
            "x": -487.0,
            "y": 1557.0
        },
        "message": "este es un mensaje secreto"
    }
    

## LEVEL 3

## Ejecucion del programa
Para poder compilarlo con maven usamos

```
mvn clean package -DskipTests=true
```

Para poder correr el proyecto compilado
```
mvn springboot:run
```
o podemos usar en la jar de la carpeta Target
```
java -jar quasar-0.0.1-SNAPSHOT.jar
```
