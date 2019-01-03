/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderEngine.io;

import entities.Camera;
import entities.Player;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

/**
 *
 * @author Domen Brunček
 */
public class InputManager extends GLFWScrollCallback {
    Camera camer;
    
    
    @Override
    public void invoke(long window, double xpos, double ypos) {
        camer.calculateZoom((float)ypos);
    }

}
