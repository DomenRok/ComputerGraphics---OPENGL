/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderEngine.io;

import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjglx.Sys;

/**
 *
 * @author Domen Brunček
 */
public class Window {
    private int width, height;
    private String title;
    private long window;
    private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private boolean[] mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static final int FPS_CAPS = 120;
    
    private static double lastFrameTime;
    private static double deltaTime;
    
    
    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }
    
    public void create() {
        if (!GLFW.glfwInit()) {
            System.out.println("Window init failed");
            System.exit(-1);
        }
        
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        window = GLFW.glfwCreateWindow(getWidth(), getHeight(), title, 0, 0);
        
        if (getWindow() == 0) {
            System.out.println("Error: Couldn't create window");
            System.exit(-1);
        }
        
        GLFW.glfwMakeContextCurrent(getWindow());
        GL.createCapabilities();
        
        
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        
        GLFW.glfwSetWindowPos(getWindow(), (videoMode.width() - getWidth()) / 2, (videoMode.height() - getHeight()) / 2);
        GLFW.glfwShowWindow(getWindow());
        
        GLFW.glfwSetKeyCallback(getWindow(), (window, key, scancode, action, mods) -> {
			if ( key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_PRESS )
				GLFW.glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });
        lastFrameTime = getCurrentTime();
    }
    
    public boolean closed() {
        return GLFW.glfwWindowShouldClose(getWindow());
    }
    
    public void update() {
        GLFW.glfwPollEvents();
        double currentFrameTime = getCurrentTime();
        deltaTime = (currentFrameTime - lastFrameTime);
        lastFrameTime = currentFrameTime;
        for (int i=0; i < GLFW.GLFW_KEY_LAST; i++) keys[i] = isKeyDown(i);
        for (int i=0; i < GLFW.GLFW_MOUSE_BUTTON_LAST; i++) mouseButtons[i] = isMouseDown(i);
    }
    
    public double getFrameTimeSeconds() {
        return deltaTime;
    }
    
    public void swapBuffers() {
        GLFW.glfwSwapBuffers(getWindow());
    }
    
    public void closeWindow() {
        GLFW.glfwTerminate();
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }
    
    private static double getCurrentTime() {
        return GLFW.glfwGetTime();
    }
    
    

    /**
     * @return the window
     */
    public long getWindow() {
        return window;
    }
    
    public boolean isKeyDown(int keyCode) {
        return GLFW.glfwGetKey(getWindow(), keyCode) == 1;
    }
    
    public boolean isMouseDown(int mouseButton) {
        return GLFW.glfwGetMouseButton(getWindow(), mouseButton) == 1;
    }
    
    public boolean isKeyPressed(int keyCode) {
        return isKeyDown(keyCode) && !keys[keyCode];
    }
    
    public boolean isKeyReleased(int keyCode) {
        return !isKeyDown(keyCode) && keys[keyCode];
    }
    
    public boolean isMousePressed(int mouseButton) {
        return isMouseDown(mouseButton) && !mouseButtons[mouseButton];
    }
    
    public boolean isMouseReleased(int mouseButton) {
        return !isMouseDown(mouseButton) && mouseButtons[mouseButton];
    }
    
    public double getMouseX() {
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(getWindow(), buffer, null);
        return buffer.get(0);
    }
    
    public double getMouseY() {
        DoubleBuffer buffer = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(getWindow(), null, buffer);
        return buffer.get(0);
    }
    
    
    
}
