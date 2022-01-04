package com.bloonsTd.entities.balloons;

import com.bloonsTd.entities.EntitiesTypesDictionary;
import com.bloonsTd.entities.EntityType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BalloonsTypesDictionary extends EntitiesTypesDictionary
{
    public static final int ERROR = -1;
    public static final int RED_BALLOON = 0;
    public static final int BLUE_BALLOON = 1;
    public static final int GREEN_BALLOON = 2;
    public static final int YELLOW_BALLOON = 3;
    public static final int PINK_BALLOON = 4;
    public static final int BLACK_BALLOON = 5;
    public static final int WHITE_BALLOON = 6;
    public static final int LEAD_BALLOON = 7;
    public static final int ZEBRA_BALLOON = 8;
    public static final int RAINBOW_BALLOON = 9;
    public static final int CERAMIC_BALLOON = 10;
    public static final int MOAB_BALLOON = 11;
    public static final int BFB_BALLOON = 12;
    public static final int ZOMG_BALLOON = 13;

    public static void initTypeDict()
    {
        ArrayList<String[]> data = EntitiesTypesDictionary.loadCSV();
        for(String[] line : data)
        {
            BalloonsTypesDictionary.ty
        }
        try {
            String fileName = "Bloons-TD-GA/src/com/data/entities types/balloon types.csv";
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (i != 0) {
                    BalloonType newType = new BalloonType(Float.parseFloat(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]), Float.parseFloat(values[5]), Float.parseFloat(values[6]));
                    BalloonsTypesDictionary.typeDict.add(newType);
                }
                i++;
            }
            br.close();
        } catch (IOException e) {
            System.out.println("BalloonsTypesDictionary loading problem");
        }
    }

    @Override
    public EntityType createNewEntityType()
    {
        return null;
    }
}
