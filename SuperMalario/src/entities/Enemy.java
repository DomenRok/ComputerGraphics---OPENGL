/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import Models.TexturedModel;
import java.util.Random;
import org.lwjgl.glfw.GLFW;
import org.lwjglx.util.vector.Vector3f;
import renderEngine.io.Window;

/**
 *
 * @author Domen Brunček
 */
public class Enemy extends Entity {
    Window window;
    private float currentSpeed = 5;
    private float radius;
    
    private Vector3f[] movingStyle = {new Vector3f(90, 3, (float)(Math.random() * 10)),
                                     new Vector3f()};
    
    double time = GLFW.glfwGetTime();
    
    public Enemy(Window window,TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
        this.window = window;
        setHitBox(1.3f);
    }
    
    public void move() {
        if (getPosition().x <= 1)  {
            super.setPosition(90, 3,(float) Math.random() * 40);
        }  
        currentSpeed += 0.01f;
        super.increasePosition(-(float) (currentSpeed * window.getFrameTimeSeconds()), 0, (float) (Math.cos(Math.toRadians(time))));
        
        time += 1;
    }
    
    public boolean intersects(Player player) {
        Vector3f sphere = getPosition();
        Vector3f other = player.getPosition();
        double distance = Math.sqrt((sphere.x - other.x) * (sphere.x - other.x) +
                                    (sphere.y - other.y) * (sphere.y - other.y) +
                                    (sphere.z - other.z) * (sphere.z - other.z));
        return distance < (radius + player.getRadius());
    }
    
    public void setHitBox(float radius) {
        this.radius = radius;
    }
    
    public void changeSpeed(float speed) {
        currentSpeed = speed;
    }
    
    
}
