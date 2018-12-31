/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Domen Brunček
 */
public class RawModel {
    private int vaoID;
    private int vertexCount;

    public RawModel(int vaoID, int vertexCount) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }

    /**
     * @return the vaoID
     */
    public int getVaoID() {
        return vaoID;
    }

    /**
     * @return the vertexCount
     */
    public int getVertexCount() {
        return vertexCount;
    }
    
    
}
