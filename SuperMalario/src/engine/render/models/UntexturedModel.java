/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.render.models;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author Domen Brunček
 */
public class UntexturedModel extends RewrittenModel {
    private int vertexArrayID, vertexBufferID, indicesBufferID, vertexCount;
    
    public UntexturedModel(float[] vertices, int[] indices) {
        vertexArrayID = super.createVertexArray();
        indicesBufferID = super.bindIndicesBuffer(indices);
        vertexBufferID = super.storeData(0, 3, vertices);
        vertexCount = vertices.length;
        GL30.glBindVertexArray(0);
    }
    
    
    public void remove() {
        GL30.glDeleteVertexArrays(getVertexArrayID());
        GL15.glDeleteBuffers(vertexBufferID);
        GL15.glDeleteBuffers(indicesBufferID);
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
    
    
    
    
}
