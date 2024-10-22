package es.ies.puerto.controlador;

import es.ies.puerto.modelo.Hunter;
import es.ies.puerto.modelo.Mapa;
import es.ies.puerto.modelo.Monster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MonsterHunter {
    private Hunter hunter;
    private Monster monster;

    private static ArrayList<String> nombresMonsters;
    
    private static Mapa mapa = new Mapa(1, 9);


    /*public MonsterHunter(Hunter hunter) {
        this.hunter = hunter;
    }
    public MonsterHunter(Monster monster) {
        this.monster = monster;
    }*/


    public static void main(String[] args) {

        generarMonstruos();
        generarHunters();



        //System.out.println("Los cazadores supervivientes son: " +mapa.getListaHunters().values());
    }

    public static void generarMonstruos(){

        Random random = new Random();
        nombresMonsters = new ArrayList<>(Arrays.asList("Rathalos", "Wyvern", "Relicto", "Gore Magala", "Laviente", "Laviente Berserker", "Raphinos", "Shogun Ceanataur"));
        int vueltasAleatorias = (int) (Math.random() * 5) + 1;

        for(int i = 0 ;  i<=vueltasAleatorias ; i++){
            int X = random.nextInt(mapa.getTamanio());
            int Y = random.nextInt(mapa.getTamanio());

            while(mapa.hayMonstruo(X, Y)){
                X = random.nextInt(mapa.getTamanio());
                Y = random.nextInt(mapa.getTamanio());
            }
            Monster monster = new Monster(i, nombresMonsters.get(random.nextInt(nombresMonsters.size())), X, Y, mapa);
            mapa.agregarMonstruo(monster);

            Thread monstruo = new Thread(monster);
            monstruo.start();
        }

        System.out.println("Se han generado: " +mapa.getListaMonsters().size()+ " monstruos");
    }

    public static void generarHunters(){

        Random random = new Random();
        //nombresHunters = new ArrayList<>(Arrays.asList("Maxi", "Pedro", "Relicto", "Gore Magala", "Laviente", "Laviente Berserker", "Raphinos", "Shogun Ceanataur"));
        int vueltasAleatorias = (int) (Math.random() * 5) + 1;
        for(int i = 0 ;  i<=vueltasAleatorias ; i++){

            int X = random.nextInt(mapa.getTamanio());
            int Y = random.nextInt(mapa.getTamanio());

            while(mapa.hayHunter(X, Y)){
                X = random.nextInt(mapa.getTamanio());
                Y = random.nextInt(mapa.getTamanio());
            }

            Hunter hunter = new Hunter(i, "Cazador "+(i+1),0 , X, Y, mapa);
            mapa.agregarHunter(hunter);

            Thread cazador = new Thread(hunter);
            cazador.start();
        }

        System.out.println("Se han generado: " +mapa.getListaHunters().size()+ " cazadores");

    }
}
