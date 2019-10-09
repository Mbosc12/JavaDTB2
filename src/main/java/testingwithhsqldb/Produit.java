/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingwithhsqldb;

/**
 *
 * @author pedago
 */
public class Produit {
    private int id = -1;
    private String name = "test";
    private float price = 0.0f;
        
    public Produit(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId() {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName() {
        this.name = name;
    }
    
    public float getPrice() {
        return price;
    }
    
    public void setPrice() {
        this.price = price;
    }
    
}
