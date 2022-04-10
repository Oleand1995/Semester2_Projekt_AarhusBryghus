package storage;

import application.model.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Storage implements Serializable {
    private static Storage storage;

    private ArrayList<Salg> salgs;
    private ArrayList<Udlejning> udlejninger;
    private ArrayList<ProduktGruppe> produktGruppes;
    private ArrayList<Prisliste> prislister;

    public Storage(){
        salgs = new ArrayList<>();
        udlejninger = new ArrayList<>();
        produktGruppes = new ArrayList<>();
        prislister = new ArrayList<>();
    }

    public static Storage getStorage(){
        if (storage == null){
            storage = new Storage();
        }
        return storage;
    }

    //------------------------------------------------------------------------------------------------------------------
    public ArrayList<Salg> getSalg(){
        return new ArrayList<>(salgs);
    }
    public void addSalg(Salg salg){salgs.add(salg);}
    public void removeSalg(Salg salg){salgs.remove(salg);}

    //------------------------------------------------------------------------------------------------------------------

    public ArrayList<Udlejning> getUdlejninger(){
        return new ArrayList<>(udlejninger);
    }
    public void addUdlejning(Udlejning udlejning){udlejninger.add(udlejning);}
    public void removeUdlejning(Udlejning udlejning){udlejninger.remove(udlejning);}

    //------------------------------------------------------------------------------------------------------------------
    public ArrayList<ProduktGruppe> getProduktGrupper(){return new ArrayList<>(produktGruppes);}
    public void addProduktGruppe(ProduktGruppe produktGruppe){produktGruppes.add(produktGruppe);}
    public void removeProduktGruppe(ProduktGruppe produktGruppe){produktGruppes.remove(produktGruppe);}

    //------------------------------------------------------------------------------------------------------------------
    public ArrayList<Prisliste> getPrislister(){return new ArrayList<>(prislister);}
    public void addPrisliste(Prisliste prisliste){prislister.add(prisliste);}
    public void removePrisliste(Prisliste prisliste){prislister.remove(prisliste);}

    //------------------------------------------------------------------------------------------------------------------



}
