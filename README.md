<div style="text-align: justify">

# Monster Hunter Threads

### Introduccion

En este repositorio trabajaremos la primera version del proyecto.

### Indice

###### Documentacion

- [Inicio](#inicio)
- [Metodo Run](#metodo-run)
- [Movimientos y Exploracion](#movimiento-y-exploracion)

###### Codigo

- [Controlador](https://github.com/PeterMartEsc/monster-hunter-thread/blob/v1/monster-hunter-threads/src/main/java/es/ies/puerto/controlador/MonsterHunter.java)
- [Clases](https://github.com/PeterMartEsc/monster-hunter-thread/tree/v1/monster-hunter-threads/src/main/java/es/ies/puerto/modelo)


##### Inicio

En la primera versión, he trabajado con 3 clases (Hunter, Monster y Map), y un controlador que irá gestionando todo lo que sucede.

En el controlador, concretamente en el metodo principal, se crean un monstruo y un cazador, a los cuales se les asocia un nombre, id, posición, mapa, etc. 

```
Monster rathalos = new Monster(1, "Rathalos",5, 5);
Hunter maxi = new Hunter(1, "Maxi",0 , 0,0, mapa);
```

Luego guardamos las posiciones de cada uno en el mapa, creamos un __Thread__ y lo iniciamos.

```
Monster rathalos = new Monster(1, "Rathalos",2, 2);
Hunter maxi = new Hunter(1, "Maxi",0 , 0,0, mapa);

mapa.setHunterPositionX(maxi.getPositionX());
mapa.setHunterPositionY(maxi.getPositionY());
mapa.setMonsterPositionX(rathalos.getPositionX());
mapa.setMonsterPositionY(rathalos.getPositionY());

Thread cazador1 = new Thread(maxi);
cazador1.start();
```

##### Metodo Run

A continuacion, se inicia el metodo _run()_ de la clase __Hunter__, el cual toma el tiempo de inicio (más adelante lo utilizaremos ), y comenzamos un bucle en el que llama a los metodos de moverse y explorar la casilla en la que se encuentran, hasta que hayan pasado 20 segundos o el __cazador__ capture al monsturo.

```
while(this.getMonstruosAtrapados() == 0 && !TIEMPO_AGOTADO){

            long tiempoActual = System.currentTimeMillis();

            this.getMapa().moverCazador(this);

            try {

                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.getMapa().explorar(this);

            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(tiempoActual - tiempoInicio >= TIEMPO_MAXIMO){
                System.out.println("Se ha acabado el tiempo");
                TIEMPO_AGOTADO = true;
            }
}
```

##### Movimiento y Exploracion

El cazador, al empezar a moverse, genera una posición random para X e Y y las guarda en el mapa, para que al consultarse se pueda saber en que posicion esta.

```
public synchronized void moverCazador(Hunter hunter){

        Random random = new Random();
        hunter.setPositionX( random.nextInt( hunter.getMapa().getTamanio() ));
        hunter.setPositionY( random.nextInt( hunter.getMapa().getTamanio() ));

        System.out.println(hunter.getNombre() + " se movió a: X=" +hunter.getPositionX()+" Y=" +hunter.getPositionY());

        hunter.getMapa().setHunterPositionX(hunter.getPositionX());
        hunter.getMapa().setHunterPositionY(hunter.getPositionY());

}
```

Durante la exploracion de la posicion donde esta situado el cazador, se comprueba si esta posicion es igual a la del monstruo (que esta almacenada en el mapa).

```
public synchronized void explorar(Hunter hunter){

        if(hunter.getPositionX() == hunter.getMapa().getMonsterPositionX() && hunter.getPositionY() == hunter.getMapa().getMonsterPositionY()){
            if(pelea()){
                System.out.println(hunter.getNombre() + " ha capturado al monstruo ");
                hunter.setMonstruosAtrapados(1);
            } else {
                System.out.println("El monstruo ha despistado a " + hunter.getNombre());
            }
        }

        System.out.println(hunter.getNombre()+ " sigue buscando");

    }
```

Si es asi, llama al metodo pelea, el cual de forma aleatoria devuelve un resultado random de victoria o derrota del cazador.

```
public boolean pelea(){

        Random random = new Random();
        int result = random.nextInt(1);
        if(result>0){
            return true;
        } else {
            return false;
        }
}
```

</div>