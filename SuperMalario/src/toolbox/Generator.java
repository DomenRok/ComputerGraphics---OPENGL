/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolbox;

import Models.ModelData;
import Models.RawModel;
import Models.TexturedModel;
import entities.Entity;
import org.lwjglx.util.vector.Vector3f;
import renderEngine.Loader;
import renderEngine.Loader;
import renderEngine.OBJFileLoader;
import textures.ModelTexture;

/**
 *
 * @author Domen Brunček
 */
public class Generator {
    public static RawModel generateCube() {
        Loader loader = new Loader();
        float[] vertices = {            
                -0.5f,0.5f,0,   
                -0.5f,-0.5f,0,  
                0.5f,-0.5f,0,   
                0.5f,0.5f,0,        
                 
                -0.5f,0.5f,1,   
                -0.5f,-0.5f,1,  
                0.5f,-0.5f,1,   
                0.5f,0.5f,1,
                 
                0.5f,0.5f,0,    
                0.5f,-0.5f,0,   
                0.5f,-0.5f,1,   
                0.5f,0.5f,1,
                 
                -0.5f,0.5f,0,   
                -0.5f,-0.5f,0,  
                -0.5f,-0.5f,1,  
                -0.5f,0.5f,1,
                 
                -0.5f,0.5f,1,
                -0.5f,0.5f,0,
                0.5f,0.5f,0,
                0.5f,0.5f,1,
                 
                -0.5f,-0.5f,1,
                -0.5f,-0.5f,0,
                0.5f,-0.5f,0,
                0.5f,-0.5f,1
                 
        };
         
        float[] textureCoords = {
                 
                0,0,
                0,1,
                1,1,
                1,0,            
                0,0,
                0,1,
                1,1,
                1,0,            
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0
 
                 
        };
         
        int[] indices = {
                0,1,3,  
                3,1,2,  
                4,5,7,
                7,5,6,
                8,9,11,
                11,9,10,
                12,13,15,
                15,13,14,   
                16,17,19,
                19,17,18,
                20,21,23,
                23,21,22
 
        };
        
        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        /*TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("bob")));
        
        Entity entity = new Entity(staticModel, new Vector3f(-2, 0, -1), 0, 0, 0, 1);
        */
        return model;
    }
    
    public static Entity generateKvadrat() {
        
        float[] vertices = {
            -0.5f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.5f, 0.5f, 0f
        };
        
        int[] indices = {
            0,1,3,
            3,1,2
        };
        
        float[] textureCoords = {
           0,0,
           0,1,
           1,1,
           1,0
        };
        Loader loader = new Loader();
        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("bob")));
        
        Entity entity = new Entity(staticModel, new Vector3f(-2, 0, -1), 0, 0, 0, 1);
        return entity;
    }
    
    public static Entity generateObject(String objModel, String textureFile, Loader loader, Vector3f position, float scale) {
        ModelData data =  OBJFileLoader.loadOBJ(objModel);
        RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture(textureFile)));
        ModelTexture texture = staticModel.getTexture();
        //texture.setReflectivity(0.5f);
        //texture.setShineDamper(10);
        Entity entity = new Entity(staticModel, position , 0, 0, 0, scale);
        return entity;
    }
    
    
}
