/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renderEngine;

import Models.TexturedModel;
import entities.Camera;
import entities.Entity;
import entities.Light;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import renderEngine.io.Window;
import shaders.StaticShader;

/**
 *
 * @author Domen Brunček
 */
public class MasterRenderer {
    // IN WORKS
    
    private StaticShader shader = new StaticShader();
    private Window window;
    private Renderer renderer;

    public MasterRenderer(Window window) {
        window = window;
        renderer = new Renderer(window, shader);
    }
    
    
    
    private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
    
    public void render(Light sun, Camera camera) {
        renderer.preapare();
        shader.start();
        shader.loadLight(sun);
        shader.loadViewMatrix(camera);
        renderer.render(entities);
        shader.stop();
        entities.clear();
    }
    
    public void processEntity(Entity entity) {
        TexturedModel entityModel = entity.getModel();
        List<Entity> batch = entities.get(entityModel);
        if (batch!=null) {
            batch.add(entity);
        } else {
            List<Entity> newBatch = new ArrayList<Entity>();
            newBatch.add(entity);
            entities.put(entityModel, newBatch);
            
        }
    }
    
    public void cleanUp() {
        shader.cleanUP();
    }
    
    
    
}
