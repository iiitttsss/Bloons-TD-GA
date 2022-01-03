package com.bloonsTd.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class EntitiesTypesDictionary
{

    private static ArrayList<EntityType> typeDict = new ArrayList<EntityType>();

    public static ArrayList<String[]> loadCSV(String fileName)
    {
        ArrayList<String[]> data = new ArrayList<String[]>();
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (i != 0) {
                    data.add(values);
                }
                i++;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("BalloonsTypesDictionary loading problem");
        }
        return data;
    }

    public ArrayList<EntityType> getTypeDict()
    {
        return typeDict;
    }

    public void setTypeDict(ArrayList<EntityType> typeDict)
    {
        this.typeDict = typeDict;
    }
}
