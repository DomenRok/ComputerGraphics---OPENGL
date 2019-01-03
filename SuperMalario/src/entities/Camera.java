/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjglx.util.vector.Vector3f;
import renderEngine.io.Window;

/**
 *
 * @author Domen Brunček
 */
public class Camera {
    private float distanceFromPlayer = 50;
    private float angleAroundPlayer = 0;
    
    
    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;
    private Window window;
    private Player player;
    
    private float Zoom;
    
    public Camera(Window window, Player player) {
        this.window = window;
        this.player = player;
    }
    
    public void move() {
        
        /*   
        if (window.isKeyDown(GLFW.GLFW_KEY_W)) position.z -= 0.5f;
        if (window.isKeyDown(GLFW.GLFW_KEY_S)) position.z += 0.5f;
        if (window.isKeyDown(GLFW.GLFW_KEY_A)) position.x -= 0.5f;
        if (window.isKeyDown(GLFW.GLFW_KEY_D)) position.x += 0.5f;
        
        if (window.isKeyDown(GLFW.GLFW_KEY_DOWN)) position.y -= 0.2f;
        if (window.isKeyDown(GLFW.GLFW_KEY_UP)) position.y += 0.2f;
        if (window.isKeyDown(GLFW.GLFW_KEY_J)) pitch += 0.3f;
        if (window.isKeyDown(GLFW.GLFW_KEY_K)) yaw += 0.3f;
        if (window.isKeyDown(GLFW.GLFW_KEY_L)) roll += 0.3f;
        */
        
        
        
        
        
        double horizontalDistance = calculateHorizontalDistance();
        double verticalDistance = calculateVerticalDistance();
        calculateCameraPosition(horizontalDistance, verticalDistance);
        this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
    }
    
    private void calculateCameraPosition(double horizDistance, double verticDistance) {
        float theta = player.getRotY() + angleAroundPlayer;
        float offsetX = (float)(horizDistance * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float)(horizDistance * Math.cos(Math.toRadians(theta)));
        position.x = player.getPosition().x - offsetX;
        position.z = player.getPosition().z - offsetZ;
        position.y = (float)(player.getPosition().y + verticDistance);
    }
    
    
    
    private double calculateHorizontalDistance() {
        return distanceFromPlayer * Math.cos(Math.toRadians(pitch));
    }
    
    private double calculateVerticalDistance() {
        return distanceFromPlayer * Math.sin(Math.toRadians(pitch));
    }
    
    public void calculatePitch(float pitchChange) {
        pitch += pitchChange;
    }
    
    public void calculateAngleAroundPlayer(float angleChange) {
        angleAroundPlayer += angleChange;
    }
    
    
    public void calculateZoom(float dy) {
        float zoomDistance = dy;
        distanceFromPlayer -= zoomDistance;
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
