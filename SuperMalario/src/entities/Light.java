/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.lwjglx.util.vector.Vector3f;

/**
 *
 * @author Domen Brunček
 */
public class Light {
    private Vector3f position;
    private Vector3f color;

    public Light(Vector3f position, Vector3f color) {
        this.position = position;
        this.color = color;
    }
    
    

    /**
     * @return the position
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * @return the color
     */
    public Vector3f getColor() {
        return color;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Vector3f color) {
        this.color = color;
    }
    
}
