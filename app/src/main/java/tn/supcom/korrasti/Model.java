package tn.supcom.korrasti;

import java.util.ArrayList;

/**
 * Created by Eya on 29/11/2017.
 */

public class Model {
    public static ArrayList<Itamname> Items;



    public static void LoadModel()
    {
        Items = new ArrayList<Itamname>();
        Items.add(new Itamname(1, "alphabet.png", "Alphabet","CAT1"));
		/*
		 * here 1 is the order id please keep in order than you have to define category image which
		 * is reside in CATIMAGE Folder then put name Category name then define folder of that
		 */
        Items.add(new Itamname(2, "animal.png", "Animal","CAT2"));
        Items.add(new Itamname(3, "boys.png", "Boys","CAT3"));
        Items.add(new Itamname(4, "cartoon.png", "Cartoon","CAT4"));
        Items.add(new Itamname(5, "christmas.png", "Christmas","CAT5"));
        Items.add(new Itamname(6, "girls.png", "Girls","CAT6"));
        Items.add(new Itamname(7, "number.png", "Number","CAT7"));
        Items.add(new Itamname(8, "shape.png", "Shape","CAT8"));
        Items.add(new Itamname(9, "vehical.png", "Vehical","CAT9"));



    }
    public static Itamname GetbyId(int id){

        for(Itamname item : Items) {
            if (item.Id == id) {
                return item;
            }
        }
        return null;
    }
}


    class Itamname {


    public int Id;
    public String IconFile;
    public String Name;
    public String FolderName;

    public Itamname(int id, String iconFile, String name,String foldername) {

        Id = id;
        IconFile = iconFile;
        Name = name;
        FolderName=foldername;

    }
}
