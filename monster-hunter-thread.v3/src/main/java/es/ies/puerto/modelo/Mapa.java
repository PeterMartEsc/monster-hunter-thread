package es.ies.puerto.modelo;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

//Mapa contiene posiciṕon del cazador y del moinstruo
//Metodos de colocar cazador y monstruo
//Metodo mover cazador

public class Mapa {

    private int mapId;
    private int tamanio;
    private HashMap<Integer, Hunter> listaHunters;
    private HashMap<Integer, Monster> listaMonsters;

    private Cueva cueva;


    /*public Mapa(int mapId) {
        this.mapId = mapId;
    }*/

    public Mapa(int mapId, int tamanio) {
        this.mapId = mapId;
        this.tamanio = tamanio;
        this.listaHunters = new HashMap<>();
        this.listaMonsters = new HashMap<>();
        this.cueva = new Cueva();
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public HashMap<Integer, Hunter> getListaHunters() {
        return listaHunters;
    }

    public void setListaHunters(HashMap<Integer, Hunter> listaHunters) {
        this.listaHunters = listaHunters;
    }

    public HashMap<Integer, Monster> getListaMonsters() {
        return listaMonsters;
    }

    public void setListaMonsters(HashMap<Integer, Monster> listaMonsters) {
        this.listaMonsters = listaMonsters;
    }

    public Cueva getCueva() {
        return cueva;
    }

    public void setCueva(Cueva cueva) {
        this.cueva = cueva;
    }

    public synchronized void agregarHunter(Hunter hunter){
        listaHunters.put(hunter.getIdHunter(), hunter);
    }

    public synchronized void agregarMonstruo(Monster monster){
        listaMonsters.put(monster.getIdMonster(), monster);
    }

    public synchronized void eliminarHunter(Hunter hunter){
        //listaMonsters.remove(hunter.getIdHunter());
    }

    public synchronized void eliminarMonstruo(Monster monster){
        listaMonsters.remove(monster.getId());
    }


    public synchronized boolean hayHunter (int X, int Y){
        boolean hayHunter = false;

        if(listaHunters.isEmpty()){
            return hayHunter;
        }

        for(Hunter hunter : listaHunters.values()){

            if(hunter.getPositionX() == X && hunter.getPositionY() == Y){
                hayHunter = true;
            }
        }

        return hayHunter;
    }
    public synchronized boolean hayMonstruo (int X, int Y){
        boolean hayMonstruo = false;

        if(listaMonsters.isEmpty()){
            return hayMonstruo;
        }

        for(Monster monster : listaMonsters.values()){

            if(monster.getPositionX() == X && monster.getPositionY() == Y){
                hayMonstruo = true;
            }
        }

        return hayMonstruo;
    }

    public synchronized boolean hayCueva (int X, int Y){

        boolean hayCueva = false;

        if(cueva.getPositionX() == X && cueva.getPositionY() == Y){
            hayCueva = true;
        }

        return hayCueva;
    }

    public synchronized void moverHunter(Hunter hunter){

        Random random = new Random();

        int X = random.nextInt(hunter.getMap().getTamanio());
        int Y = random.nextInt(hunter.getMap().getTamanio());

        while(hunter.getMap().hayHunter(X, Y)){
            X = random.nextInt(hunter.getMap().getTamanio());
            Y = random.nextInt(hunter.getMap().getTamanio());
        }

        hunter.setPositionX(X);
        hunter.setPositionY(Y);

        System.out.println(hunter.getNombre() + " se movió a: X=" +hunter.getPositionX()+" Y=" +hunter.getPositionY());

        hunter.getMap().agregarHunter(hunter);

    }

    public synchronized void moverMonster(Monster monster){

        Random random = new Random();

        int X = random.nextInt(monster.getMap().getTamanio());
        int Y = random.nextInt(monster.getMap().getTamanio());

        if(monster.getMap().hayCueva(X, Y)){
            monster.setPositionX(-1);   //Así no podrá chocar contra ningun monstruo ni hunter y se moverá nada más salir
            monster.setPositionY(-1);   //Así no podrá chocar contra ningun monstruo ni hunter y se moverá nada más salir
            monster.getMap().agregarMonstruo(monster);
            System.out.println(monster.getNombre()+" encontró una cueva con espacio");
            monster.getMap().getCueva().entrarCueva(monster);
            moverMonster(monster);
        }

        while(monster.getMap().hayMonstruo(X, Y) || monster.getPositionX() < 0){
            X = random.nextInt(monster.getMap().getTamanio());
            Y = random.nextInt(monster.getMap().getTamanio());
        }

        monster.setPositionX(X);
        monster.setPositionY(Y);

        System.out.println(monster.getNombre() + " se movió a: X=" +monster.getPositionX()+" Y=" +monster.getPositionY());
        monster.getMap().agregarMonstruo(monster);

    }

    public synchronized void explorar(Hunter hunter){

        if(hayCueva(hunter.getPositionX(), hunter.getPositionY())){
            System.out.println("Ante el cazador se yergue una imponente cueva.\n" +
                    "Entrar en ella podría suponer la muerte segura. El cazador sigue su rumbo");
            return;
        }

        for(Monster monster : hunter.getMap().getListaMonsters().values()){

            if(hunter.getPositionX() == monster.getPositionX() && hunter.getPositionY() == monster.getPositionY()){
                boolean caza = pelea();

                if(caza){
                    System.out.println(hunter.getNombre() + " ha cazado a " + monster.getName());
                    hunter.setMonstruosAtrapados(hunter.getMonstruosAtrapados()+1);
                    hunter.getMap().eliminarMonstruo(monster);
                    monster.setCazado(true);
                } else {
                    System.out.println(monster.getName() + " ha escapado de " +hunter.getNombre());
                }
            }

        }

        System.out.println(hunter.getNombre()+ " no ha encontrado nada");
    }

    public boolean pelea(){
        Random random = new Random();
        int result = random.nextInt(2);
        if(result>0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mapa mapa = (Mapa) o;
        return mapId == mapa.mapId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapId);
    }

    @Override
    public String toString() {
        return "Mapa{" +
                "mapId=" + mapId +
                ", tamanio=" + tamanio +
                ", listaHunters=" + listaHunters +
                ", listaMonsters=" + listaMonsters +
                '}';
    }
}
