/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermalario2;

import org.lwjgl.glfw.GLFW;
import renderEngine.Loader;
import Models.RawModel;
import Models.TexturedModel;
import entities.Camera;
import entities.Entity;
import org.lwjglx.util.vector.Vector3f;
import renderEngine.Generator;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import renderEngine.Window;
import shaders.StaticShader;
import textures.ModelTexture;

/**
 *
 * @author Domen Brunček
 */
public class SuperMalario2 {
    private static final int width = 800, height = 600;
    
    public static void main(String[] args) {
        Window window = new Window(width, height, "Jebeno");
        window.create();        
        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(window, shader);
        
        //RawModel model = Generator.generateCube();
        RawModel model = OBJLoader.loadObjModel("stall", loader);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("stallTexture")));
        
        Entity entity = new Entity(staticModel, new Vector3f(0, 0, -5), 0, 0, 0, 0.2f);
        Camera camera = new Camera(window);
        
        Entity entitiy2 = Generator.generateKvadrat();
        
        while (!window.closed()) {
            
            
            entity.increaseRotation(0.01f, 0.03f, 0.0f);
            entitiy2.increaseRotation(0.045f, 0.03f, 0.0f);
            camera.move();
            renderer.preapare();
            
            
            shader.start();
            shader.loadViewMatrix(camera);
            renderer.render(entity, shader);
            renderer.render(entitiy2, shader);
            shader.stop();
            
            window.update();
            window.swapBuffers();
        }
        
        shader.cleanUP();
        loader.CleanUp();
        window.closeWindow();
    }
}
