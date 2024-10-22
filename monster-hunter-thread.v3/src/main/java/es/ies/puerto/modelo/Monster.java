package es.ies.puerto.modelo;

import java.util.Objects;

public class Monster extends Thread {

    private int idMonster;
    private String nombre;
    private int positionX;
    private int positionY;
    private Mapa map;

    public Monster(int idMonster, String nombre, int positionX, int positionY, Mapa map) {
        this.idMonster = idMonster;
        this.nombre = nombre;
        this.positionX = positionX;
        this.positionY = positionY;
        this.map = map;
    }

    public int getIdMonster() {
        return idMonster;
    }

    public void setIdMonster(int idMonster) {
        this.idMonster = idMonster;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Mapa getMap() {
        return map;
    }

    public void setMap(Mapa map) {
        this.map = map;
    }

    @Override
    public void run(){
        int TIEMPO_MAXIMO = 60000;

        boolean TIEMPO_AGOTADO = false;

        long tiempoInicio = System.currentTimeMillis();

        while(!TIEMPO_AGOTADO){

            long tiempoActual = System.currentTimeMillis();

            //for(Monster monster : this.getMap().getListaMonsters().values()){
                this.getMap().moverMonster(this);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            //}

            if(tiempoActual - tiempoInicio >= TIEMPO_MAXIMO){
                System.out.println("Se ha acabado el tiempo de los monstruos");
                TIEMPO_AGOTADO = true;
            }

        }

        //System.out.println("¡¡¡¡¡La cacería ha terminado!!!!!");

        System.out.println(this.getNombre()+" ha sobrevivido");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return idMonster == monster.idMonster;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMonster);
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + idMonster +
                ", name='" + nombre + '\'' +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }
}
