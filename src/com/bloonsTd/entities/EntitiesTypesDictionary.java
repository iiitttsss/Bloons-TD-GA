package com.bloonsTd.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class EntitiesTypesDictionary
{

    private ArrayList<EntityType> typeDict = new ArrayList<EntityType>();

    public EntitiesTypesDictionary(String fileName)
    {
        this.loadCSV(fileName);
    }

    public abstract EntityType createNewEntity(String[] values);

    private void loadCSV(String fileName)
    {
        BufferedReader br;
        try
        {
            File file = new File(fileName);
            br = new BufferedReader(new FileReader(file));
        }
        catch (IOException e)
        {
            System.out.println("BalloonsTypesDictionary loading problem: could not load file");
            System.out.println("file name: " + fileName);
            System.out.println(e);
            return;
        }

        try
        {
            String currentLine;
            int i = 0;
            while ((currentLine = br.readLine()) != null)
            {
                String[] values = currentLine.split(",");
                if (i != 0)
                {
                    this.getTypeDict().add(this.createNewEntity(values));
                }
                i++;

            }
        }
        catch (IOException e)
        {
            System.out.println("BalloonsTypesDictionary loading problem: could not read the file");
            System.out.println("file name: " + fileName);
            return;
        }


        try
        {
            br.close();
        }
        catch (IOException e)
        {
            System.out.println("BalloonsTypesDictionary loading problem: could not close file");
            System.out.println("file name: " + fileName);
            return;
        }

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
