package es.ies.puerto.controlador;

import es.ies.puerto.modelo.Hunter;
import es.ies.puerto.modelo.Mapa;
import es.ies.puerto.modelo.Monster;

import java.util.Random;

public class MonsterHunter extends Thread {
    private Hunter hunter;
    private Monster monster;
    private static Mapa mapa = new Mapa(1, 5);

    public MonsterHunter(Hunter hunter) {
        this.hunter = hunter;
    }
    /*public MonsterHunter(Monster monster) {
        this.monster = monster;
    }*/



    public static void main(String[] args) {

        Monster rathalos = new Monster(1, "Rathalos",9, 9);
        Hunter maxi = new Hunter(1, "Maxi",0 , 0,0, mapa);

        mapa.setHunterPositionX(maxi.getPositionX());
        mapa.setHunterPositionY(maxi.getPositionY());
        mapa.setMonsterPositionX(rathalos.getPositionX());
        mapa.setMonsterPositionY(rathalos.getPositionY());

        Thread cazador1 = new Thread(new MonsterHunter(maxi));
        //Thread monstruo1 = new Thread(new MonsterHunter(rathalos));

        cazador1.start();
        //monstruo1.start();

    }
}
