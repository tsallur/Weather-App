package com.example.android.a25_hw3;
/*
Thomas Sallurday
C17123785
tsallur@g.clemson.edu
*/
//class gets track of city names and associates an ID with them
public class City {
    private int id;
    private String name;
/**
 * Citation:Class based on Band Class from Zybooks 5.2
 */
    /**
     * @pre none
     * @post this.name = name && this.id = id
     */
    public City(int id,String name){
        this.name = name;
        this.id = id;
    }

    /**
     * @pre none
     * @post id = #id
     */
    public int getId() {
        return id;
    }

    /**
     * @pre none
     * @post id = #id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @pre none
     * @post name = #name
     */
    public String getName() {
        return name;
    }
}
