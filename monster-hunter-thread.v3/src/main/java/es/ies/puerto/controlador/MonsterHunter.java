package es.ies.puerto.controlador;

import es.ies.puerto.modelo.Cueva;
import es.ies.puerto.modelo.Hunter;
import es.ies.puerto.modelo.Mapa;
import es.ies.puerto.modelo.Monster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MonsterHunter {

    private static ArrayList<String> nombresMonsters;

    static int cantidadMonstruosAleatoria = (int) (Math.random() * 5) + 1;
    static int cantidadHuntersAleatoria = (int) (Math.random() * 5) + 1;

    private static Mapa mapa = new Mapa(1, 9);

    private static Cueva cueva;


    public static void main(String[] args) {

        System.out.println("Se ha generado la cueva en X=" +mapa.getCueva().getPositionX()+ " e Y="+mapa.getCueva().getPositionY());
        generarCueva();

        System.out.println("Se generarán: " +cantidadHuntersAleatoria+ " cazadores");
        generarHunters();

        System.out.println("Se generarán: " +cantidadMonstruosAleatoria+ " monstruos");
        generarMonstruos();

        //System.out.println("Los cazadores supervivientes son: " +mapa.getListaHunters().values());
    }

    public static void generarHunters(){

        Random random = new Random();
        //nombresHunters = new ArrayList<>(Arrays.asList("Maxi", "Pedro", "Relicto", "Gore Magala", "Laviente", "Laviente Berserker", "Raphinos", "Shogun Ceanataur"));

        for(int i = 0 ;  i<=cantidadHuntersAleatoria ; i++){

            int X = random.nextInt(mapa.getTamanio());
            int Y = random.nextInt(mapa.getTamanio());

            //Solo comprueba si hay hunters y cueva, ya que no hay posibilidad de que se hayan creado monstruos aun
            while(mapa.hayHunter(X, Y) || mapa.hayCueva(X, Y)){
                X = random.nextInt(mapa.getTamanio());
                Y = random.nextInt(mapa.getTamanio());
            }

            Hunter hunter = new Hunter(i, "Cazador "+(i+1),0 , X, Y, mapa);
            mapa.agregarHunter(hunter);

            Thread cazador = new Thread(hunter);
            cazador.start();
        }

    }

    public static void generarMonstruos(){

        Random random = new Random();
        nombresMonsters = new ArrayList<>(Arrays.asList("Rathalos", "Wyvern", "Relicto", "Gore Magala", "Laviente", "Laviente Berserker", "Raphinos", "Shogun Ceanataur"));


        for(int i = 0 ;  i<=cantidadMonstruosAleatoria ; i++){
            int X = random.nextInt(mapa.getTamanio());
            int Y = random.nextInt(mapa.getTamanio());

            //Solo comprueba si hay hunters o monstruos o cueva, por que ya se han creado.
            while(mapa.hayHunter(X, Y) || mapa.hayMonstruo(X,Y) || mapa.hayCueva(X,Y)){
                X = random.nextInt(mapa.getTamanio());
                Y = random.nextInt(mapa.getTamanio());
            }
            Monster monster = new Monster(i, nombresMonsters.get(random.nextInt(nombresMonsters.size()))+"-"+(i+1), X, Y, mapa);
            mapa.agregarMonstruo(monster);

            Thread monstruo = new Thread(monster);
            monstruo.start();
        }
    }

    public static void generarCueva(){
        Random random = new Random();
        int X = random.nextInt(mapa.getTamanio());
        int Y = random.nextInt(mapa.getTamanio());

        int espacio = cantidadMonstruosAleatoria/2;

        cueva = new Cueva(espacio, X, Y, false);


    }
}
