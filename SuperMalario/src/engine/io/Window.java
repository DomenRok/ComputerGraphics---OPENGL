/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.io;


import com.sun.javafx.geom.Vec3f;
import java.nio.DoubleBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Domen Brunček
 */
public class Window {
    private int width, height;
    private String title;
    private long window;
    private Vec3f backgroundColor;
    private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private boolean[] mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private double FPS_CAP, time, processedTime = 0;
    
    public Window(int width, int height, int fps, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        FPS_CAP = fps;
        backgroundColor = new Vec3f(0.0f,0.0f,0.0f);
    }
    
    public void create() {
        if (!GLFW.glfwInit()) {
            System.err.println("Error: Couldn't init GLFW");
            System.exit(-1);
        }
            
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        window = GLFW.glfwCreateWindow(getWidth(), getHeight(), getTitle(), 0, 0);
        if (getWindow() == 0) {
            System.err.println("Error: Window couldn't be created");
            System.exit(-1);
        }
        
        
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        
        GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(getWindow(), (videoMode.width() - getWidth()) / 2, (videoMode.height() - getHeight()) / 2);
        
        GLFW.glfwShowWindow(getWindow());
        time = getTime();
    }
    
    public boolean closed() {
        return GLFW.glfwWindowShouldClose(getWindow());
    }
    
    public void update() {
        for (int i=0; i < GLFW.GLFW_KEY_LAST; i++) keys[i] = isKeyDown(i);
        for (int i=0; i < GLFW.GLFW_MOUSE_BUTTON_LAST; i++) mouseButtons[i] = isMouseDown(i);
        GL11.glClearColor(backgroundColor.x, backgroundColor.y, backgroundColor.z, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GLFW.glfwPollEvents();
    }
    
    public void stop() {
        GLFW.glfwTerminate();
    }
    
    public void swapBuffers() {
        GLFW.glfwSwapBuffers(getWindow());
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
    
    public double getTime() {
        return (double) System.nanoTime() / (double) 1000000000;
    }
    
    public boolean isUpdating() {
        double nextTime = getTime();
        double deltaTime = nextTime - time;
        processedTime += deltaTime;
        time = nextTime;
        
        while (processedTime > 1.0/getFPS()) {
            processedTime -= 1.0/getFPS();
            return true;
        }
        return false;
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

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the window
     */
    public long getWindow() {
        return window;
    }

    /**
     * @return the FPS_CAP
     */
    public double getFPS() {
        return FPS_CAP;
    }
    
    public void setBackgroundColor(float r, float g, float b) {
        backgroundColor = new Vec3f(r, g, b);
    }
    
    
    
    
    
    
}
