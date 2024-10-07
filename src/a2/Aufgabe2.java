package a2;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe2 extends AbstractOpenGLBase {

	ShaderProgram shaderProgram;
	private Data data;
	public static void main(String[] args) {
		new Aufgabe2().start("CG Aufgabe 2", 700, 700);
	}

	@Override
	protected void init() {


		shaderProgram = new ShaderProgram("aufgabe2");
		//renderer = new Renderer();

		float[] positions = new float[]{
				// Triangle 1
				-0.9f, -0.5f, 0.0f, // Vertex 1
				-0.0f, -0.5f, 0.0f, // Vertex 2
				-0.45f, 0.5f, 0.0f, // Vertex 3

				// Triangle 2
				0.1f, -0.5f, 0.0f, // Vertex 4
				1.0f, -0.5f, 0.0f, // Vertex 5
				0.55f, 0.5f, 0.0f  // Vertex 6
		};


		int[] indices = new int[]{

				0, 1, 2,


				3, 4, 5
		};

		//data = new Data(positions,colors, indices);


	}

	public void renderNew(Data data) {
		// Activate shader program
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glUseProgram(shaderProgram.getId());

		// Draw the mesh
		data.render();

		// Restore state
		glUseProgram(0);
	}

	@Override
	public void update() {
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT); // Zeichenfläche leeren

		// hier vorher erzeugte VAOs zeichnen
		renderNew(data);
		//glDrawArrays(GL_TRIANGLES, 0, 6);
	}
}
