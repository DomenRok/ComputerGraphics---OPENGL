/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import Models.TexturedModel;
import org.lwjglx.util.vector.Vector3f;

/**
 *
 * @author Domen Brunček
 */
public class Entity {
    private TexturedModel model;
    private Vector3f position;
    private float rotX, rotY, rotZ;
    private float scale;
    

    public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
    }
    
    public void increasePosition(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }
    
    public void increaseRotation(float dx, float dy, float dz) {
        this.rotX += dx;
        this.rotY += dy;
        this.rotZ += dz;
    }
    
    public void setPosition(float dx, float dy, float dz) {
        this.position.x = dx;
        this.position.y = dy;
        this.position.z = dz;
    }
    
    
    /**
     * @return the model
     */
    public TexturedModel getModel() {
        return model;
    }

    /**
     * @return the position
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * @return the rotX
     */
    public float getRotX() {
        return rotX;
    }

    /**
     * @return the rotY
     */
    public float getRotY() {
        return rotY;
    }

    /**
     * @return the rotZ
     */
    public float getRotZ() {
        return rotZ;
    }

    /**
     * @return the scale
     */
    public float getScale() {
        return scale;
    }
    
    
}
