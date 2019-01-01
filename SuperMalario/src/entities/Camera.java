/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.lwjgl.glfw.GLFW;
import org.lwjglx.util.vector.Vector3f;
import renderEngine.io.Window;

/**
 *
 * @author Domen Brunček
 */
public class Camera {
    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;
    private Window window;
    
    public Camera(Window window) {
        this.window = window;
    }
    
    public void move() {
        if (window.isKeyDown(GLFW.GLFW_KEY_W)) position.z -= 0.02f;
        if (window.isKeyDown(GLFW.GLFW_KEY_S)) position.z += 0.02f;
        if (window.isKeyDown(GLFW.GLFW_KEY_A)) position.x -= 0.02f;
        if (window.isKeyDown(GLFW.GLFW_KEY_D)) position.x += 0.02f;
        if (window.isKeyDown(GLFW.GLFW_KEY_DOWN)) position.y -= 0.02f;
        if (window.isKeyDown(GLFW.GLFW_KEY_UP)) position.y += 0.02f;
        if (window.isKeyDown(GLFW.GLFW_KEY_J)) pitch += 0.3f;
        if (window.isKeyDown(GLFW.GLFW_KEY_K)) yaw += 0.3f;
        if (window.isKeyDown(GLFW.GLFW_KEY_L)) roll += 0.3f;
        
    }
    
    
    

    /**
     * @return the position
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * @return the pitch
     */
    public float getPitch() {
        return pitch;
    }

    /**
     * @return the yaw
     */
    public float getYaw() {
        return yaw;
    }

    /**
     * @return the roll
     */
    public float getRoll() {
        return roll;
    }
    
    
}
