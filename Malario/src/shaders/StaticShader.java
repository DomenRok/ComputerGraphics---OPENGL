/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shaders;

import org.joml.Matrix4f;

/**
 *
 * @author Domen Brunček
 */
public class StaticShader extends Shader {
    private static final String VERTEX_FILE = "./src/shaders/vertexShader.vs", FRAGMENT_FILE = "./src/shaders/fragmentShader.fs";
    
    
    
    private int tvpMatrixLocation;
    
    private Matrix4f transformationMatrix = new Matrix4f().identity(), projectionMatrix = new Matrix4f().identity();
    
    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    public void bindAllAtributes() {
        super.bindAttribute(0, "vertices");
        super.bindAttribute(1, "textCoords");
    }

    @Override
    protected void getAllUniforms() {
         tvpMatrixLocation = super.getUniform("tvpMatrix");
         //locationProjectionMatrix = super.getUniform("projection");
    }
    
    public void useMatrices() {
        System.out.println("projekcijska matrika je: \n" + projectionMatrix);
        System.out.println("transformacijska matrika je: \n" + transformationMatrix);
        super.loadMatrixUniform(tvpMatrixLocation, transformationMatrix);
        System.out.println("projekcijska matrika po multiplikaciji je: \n" + projectionMatrix);
        System.out.println("transformacijska matrika po multiplikaciji je: \n" + transformationMatrix);
        
    }
    
    public void loadTransformationMatrix(Matrix4f matrix) {
        transformationMatrix = matrix;
    }
    
    
    public void loadProjectionMatrix(Matrix4f matrix) {
        projectionMatrix = matrix;
     }
    
    
    
    
    
}
