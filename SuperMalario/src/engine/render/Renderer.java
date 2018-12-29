/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.render;

import engine.io.Window;
import engine.render.models.Model;
import engine.render.models.ModelEntitiy;
import engine.render.models.TexturedModel;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import shaders.StaticShader;

/**
 *
 * @author Domen Brunček
 */
public class Renderer {
    private StaticShader shader;
    private Window window;
    
    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;
    
    private Matrix4f projectionMatrix;
    
    
    public Renderer(StaticShader shader, Window window) {
        this.shader = shader;
        this.window = window;
    }
    
    
    public void create() {
        createProjectionMatrix();
        shader.loadProjectionMatrix(projectionMatrix);
    }
    
    public void sendProjectionMatrixToShader () {
        createProjectionMatrix();
        shader.loadProjectionMatrix(projectionMatrix);
    }
    
    
    
    
    
    public void renderModel(Model model) {
        GL30.glBindVertexArray(model.getVertexArrayID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVerterCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
    
    public void renderTextureModel(TexturedModel model) {
        GL30.glBindVertexArray(model.getVertexArrayID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getMaterial().getTextureID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }
    
    public void renderModelEntitiy(ModelEntitiy entitiy) {
        GL30.glBindVertexArray(entitiy.getModel().getVertexArrayID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        shader.loadTransformationMatrix(entitiy.getTransformationMatrix4f());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, entitiy.getModel().getMaterial().getTextureID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, entitiy.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        sendProjectionMatrixToShader(); // GHETTO
    }
    
    
    private void  createProjectionMatrix(){
        float aspectRatio = (float) window.getWidth() / (float) window.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;
 
        projectionMatrix = new Matrix4f().identity();
        projectionMatrix.m00(x_scale);
        projectionMatrix.m11(y_scale);
        projectionMatrix.m22(-((FAR_PLANE + NEAR_PLANE) / frustum_length));
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-((2 * NEAR_PLANE * FAR_PLANE) / frustum_length));
        projectionMatrix.m33(0);
    }
     
    
}
