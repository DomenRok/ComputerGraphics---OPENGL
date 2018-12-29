/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.render.models;

import engine.render.Material;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author Domen Brunček
 */
public class TexturedModel extends RewrittenModel {
    private int vertexArrayID, vertexBufferID, textureCoordsBufferID, indicesBufferID, vertexCount;
    private Material material;
    
    public TexturedModel(float[] vertices, float[] textureCoords, int[] indices, String Texturefile) {
        vertexArrayID = super.createVertexArray();
        indicesBufferID = super.bindIndicesBuffer(indices);
        vertexBufferID = super.storeData(0, 3, vertices);
        textureCoordsBufferID = super.storeData(1, 2, textureCoords);
        vertexCount = vertices.length;
        GL30.glBindVertexArray(0);
        material = new Material(Texturefile);
    }
    
    
    public void remove() {
        GL30.glDeleteVertexArrays(getVertexArrayID());
        GL15.glDeleteBuffers(vertexBufferID);
        GL15.glDeleteBuffers(indicesBufferID);
        GL15.glDeleteBuffers(textureCoordsBufferID);
        getMaterial().remove();
    }

    /**
     * @return the vertexArrayID
     */
    public int getVertexArrayID() {
        return vertexArrayID;
    }

    /**
     * @return the vertexCount
     */
    public int getVertexCount() {
        return vertexCount;
    }

    /**
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }
    
    
    
    
}
