/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import Models.TexturedModel;
import org.lwjgl.glfw.GLFW;
import org.lwjglx.util.vector.Vector3f;
import renderEngine.io.Window;

/**
 *
 * @author Domen Brunček
 */
public class Player extends Entity {
    
    Window window;
    private static final float RUN_SPEED = 20;
    private static final float TURN_SPEED = 160;
    private static final float GRAVITY = -50;
    private static final float JUMP_POWER = 30;
    
    private static final float TERRAIN_HEIGHT = 0;
    
    private float currentSpeed = 0;
    private float currentTurnSpeed = 0;
    private float upwardSpeed = 0;
    
    private boolean isInAir = false;

    public Player(Window window, TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
        this.window = window;
    }
    
    public void move() {
        checkInputs();
        super.increaseRotation(0, (float) (currentTurnSpeed * window.getFrameTimeSeconds()), 0);
        float distance = (float) (currentSpeed * window.getFrameTimeSeconds());
        System.out.println(distance);
        float dx = (float)(distance * Math.sin(Math.toRadians(super.getRotY())));
        float dz = (float)(distance * Math.cos(Math.toRadians(super.getRotY())));
        super.increasePosition( dx, 0,  dz);
        upwardSpeed += GRAVITY * window.getFrameTimeSeconds();
        super.increasePosition(0, (float) (upwardSpeed * window.getFrameTimeSeconds()), 0);
        if(super.getPosition().y < TERRAIN_HEIGHT) {
            upwardSpeed = 0;
            isInAir = false;
            super.getPosition().y = TERRAIN_HEIGHT;
        }
    }
    
    private void jump() {
        if (!isInAir) {
            this.upwardSpeed = JUMP_POWER;
            isInAir = true;
        }
    }
    
    private void checkInputs() {
        if (window.isKeyDown(GLFW.GLFW_KEY_W)) this.currentSpeed = RUN_SPEED;
        else if (window.isKeyDown(GLFW.GLFW_KEY_S)) this.currentSpeed = -RUN_SPEED;
        else this.currentSpeed = 0;
        
        if (window.isKeyDown(GLFW.GLFW_KEY_D)) currentTurnSpeed = -TURN_SPEED;
        else if (window.isKeyDown(GLFW.GLFW_KEY_A)) currentTurnSpeed = TURN_SPEED;
        else currentTurnSpeed = 0;
        
        if (window.isKeyDown(GLFW.GLFW_KEY_SPACE)) jump();
        
        
    }
    
}
