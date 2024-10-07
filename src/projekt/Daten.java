package projekt;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.ARBVertexArrayObject.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Daten {
    private int vaoId;
    private List<Integer> vboIdList;
    private int vertexCount;

    public Daten(float[] positions) {
        vertexCount = positions.length / 3; // Assuming each vertex has 3 coordinates (x, y, z)
        vaoId = glGenVertexArrays();
        vboIdList = new ArrayList<>();
        glBindVertexArray(vaoId);

        // Create position VBO
        int vboId = glGenBuffers();
        vboIdList.add(vboId);
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, positions, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void render() {
        glBindVertexArray(getVaoId());
        glDrawArrays(GL_TRIANGLES, 0, getVertexCount());
        glBindVertexArray(0);
    }

    // Uncomment and update the cleanup method when needed
    /*
    public void cleanup() {
        glDisableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        for (int vboId : vboIdList) {
            glDeleteBuffers(vboId);
        }

        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }
    */
}
