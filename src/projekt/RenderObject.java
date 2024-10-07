package projekt;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.*;
//import a2.Data;
import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;
import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
public class RenderObject {
    private float angle;
    private float[] vertices;
    private float[] colors;
    private float[] normals;
    private int vaoId;
    private int vboId;
    private int vboColors;
    private int vboNormals;
    private Matrix4 modelMatrix;
    private ShaderProgram shaderProgram;

    // Constructor
    public RenderObject(float[] vertices, float[] colors, float[] normals, ShaderProgram shaderProgram) {
        this.vertices = vertices;
        this.colors = colors;
        this.normals = normals;
        this.shaderProgram = shaderProgram;
        this.modelMatrix = new Matrix4(); // Initialize with an identity matrix
        initializeBuffers();
    }

    int getVaoId() {
        return this.vaoId;
    }

    private void initializeBuffers() {
// Create and bind VAO, VBOs, etc.
// Similar to what you have in your init() method
        glUseProgram(shaderProgram.getId());
// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
        this.vaoId = glGenVertexArrays();
//data = new Data(vertices, indices);
// In your init method:
// vaoId = glGenVertexArrays();
        glBindVertexArray(this.vaoId);
// Create a VBO for vertices
        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);
        vboColors = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboColors);
        glBufferData(GL_ARRAY_BUFFER, colors, GL_STATIC_DRAW);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(1);
        vboNormals = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboNormals);
        glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(2);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
//projectionMatrix = new Matrix4(0.1f,1000.f);
//viewMatrix = new Matrix4();
// modelMatrix = new Matrix4();
        int uMatrixLocation = glGetUniformLocation(shaderProgram.getId(), "myMatrix");
// trans = glGetUniformLocation(shaderProgram.getId(), "transformationMatrix");
        float scaleFactor = 0.5f; // Adjust this value to scale the pyramid
        modelMatrix.scale(scaleFactor, scaleFactor, scaleFactor);
        glUniformMatrix4fv(uMatrixLocation, false, modelMatrix.getValuesAsArray());
        glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
//glEnable(GL_CULL_FACE); // backface culling aktivieren
    }

    // Additional methods to update modelMatrix (like rotate, translate, etc.)
    public void translate(float x, float y, float z) {
        modelMatrix.translate(x, y, z);
    }

    /*public void rotate(float angle) {
    modelMatrix.rotateX(angle);
    } */
// Getter for modelMatrix if needed
    public Matrix4 getModelMatrix() {
        return modelMatrix;
    }

    public void update() {
// Assuming you have a similar mechanism to control object rotation
        modelMatrix.rotateZ(0.01f);
        modelMatrix.rotateX(0.01f);
        Matrix4 transformationMatrix = new Matrix4();
        angle += 0.01;
        transformationMatrix.rotateY(angle);
        transformationMatrix.multiply(modelMatrix);
        glUseProgram(shaderProgram.getId());
        int trans = glGetUniformLocation(shaderProgram.getId(), "transformationMatrix");
        glUniformMatrix4fv(trans, false, transformationMatrix.getValuesAsArray());
        glUseProgram(0);
    }


}
