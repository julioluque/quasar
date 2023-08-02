# Quasar project


Este es un proyecto es un confidencial perteneciente a la rebelion. No lo cuentes a nadie que:

  - Triangula posiciones
  - Intercepta mensajes enemigos


Triangula posiciones con dos metodos:

  - 1 Formula de triangulacion general
  - 2 Libreria super secreta de com.lemmingapex.trilateration

### 1 Formula de triangulacion general

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

### 2 Libreria super secreta de com.lemmingapex.trilateration fuente:
	https://github.com/lemmingapex/trilateration
	
	<dependency>
		<groupId>com.lemmingapex.trilateration</groupId>
		<artifactId>trilateration</artifactId>
		<version>1.0.2</version>
	</dependency>	



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
