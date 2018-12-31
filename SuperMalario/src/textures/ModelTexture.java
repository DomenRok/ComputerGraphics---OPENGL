/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textures;

/**
 *
 * @author Domen Brunček
 */
public class ModelTexture {
    private int textureID;
    
    private float shineDamper = 1;
    private float reflectivity = 0;

    public ModelTexture(int textureID) {
        this.textureID = textureID;
    }
    
    public int getID() {
        return this.textureID;
    }

    /**
     * @return the shineDamper
     */
    public float getShineDamper() {
        return shineDamper;
    }

    /**
     * @return the reflectivity
     */
    public float getReflectivity() {
        return reflectivity;
    }

    /**
     * @param shineDamper the shineDamper to set
     */
    public void setShineDamper(float shineDamper) {
        this.shineDamper = shineDamper;
    }

    /**
     * @param reflectivity the reflectivity to set
     */
    public void setReflectivity(float reflectivity) {
        this.reflectivity = reflectivity;
    }
    
}
