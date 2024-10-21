package es.ies.puerto.modelo;

import java.util.Objects;

public class Hunter extends Thread{
    private int idHunter;
    private String nombre;
    private int monstruosAtrapados;

    private int positionX;
    private int positionY;

    private Mapa map;

    public Hunter(int idHunter, String nombre, int monstruosAtrapados, int positionX, int positionY, Mapa map) {
        this.idHunter = idHunter;
        this.nombre = nombre;
        this.monstruosAtrapados = monstruosAtrapados;
        this.positionX = positionX;
        this.positionY = positionY;
        this.map = map;
    }

    public int getIdHunter() {
        return idHunter;
    }

    public void setIdHunter(int idHunter) {
        this.idHunter = idHunter;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMonstruosAtrapados() {
        return monstruosAtrapados;
    }

    public void setMonstruosAtrapados(int monstruosAtrapados) {
        this.monstruosAtrapados = monstruosAtrapados;
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

            //for(Hunter hunter : this.getMap().getListaHunters().values()){

                this.getMap().moverHunter(this);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                this.getMap().explorar(this);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            //}

            if(tiempoActual - tiempoInicio >= TIEMPO_MAXIMO){
                TIEMPO_AGOTADO = true;
            }

            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        System.out.println("¡¡¡¡¡La cacería ha terminado!!!!! " +this.getNombre() + " ha cazado " +this.getMonstruosAtrapados());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hunter hunter = (Hunter) o;
        return idHunter == hunter.idHunter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHunter);
    }

    @Override
    public String toString() {
        return "Hunter{" +
                "id=" + idHunter +
                ", nombre='" + nombre + '\'' +
                ", monstruosAtrapados=" + monstruosAtrapados +
                ", positionX=" + positionX +
                ", positonY=" + positionY +
                '}';
    }
}
