package es.ies.puerto.modelo;

import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Cueva {

    private int espacio;
    private int positionX;
    private int positionY;
    private boolean ocupada;
    private Semaphore semaphore;

    public Cueva() {
    }

    public Cueva(int espacio, int positionX, int positionY, boolean ocupada) {
        this.espacio = espacio;
        this.positionX = positionX;
        this.positionY = positionY;
        this.ocupada = ocupada;
        this.semaphore = new Semaphore(espacio);
    }

    public int getEspacio() {
        return espacio;
    }

    public void setEspacio(int espacio) {
        this.espacio = espacio;
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

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public synchronized void entrarCueva(Monster monster){

        try {
            Semaphore semaforo = monster.getMap().getCueva().getSemaphore();
            semaforo.acquire();

            System.out.println(monster.getNombre() + " ha entrado en la cueva");

            sleep(5000);
            System.out.println(monster.getNombre()+" ha salido de la cueva");
            semaforo.release();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }




    }

    @Override
    public String toString() {
        return "Cueva{" +
                "espacio=" + espacio +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", ocupada=" + ocupada +
                ", semaphore=" + semaphore +
                '}';
    }
}
