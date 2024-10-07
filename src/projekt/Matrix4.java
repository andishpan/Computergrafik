package projekt;

//Alle Operationen aendern das Matrixobjekt selbst und geben das eigene Matrixobjekt zurueck
//Dadurch kann man Aufrufe verketten, z.B.
//Matrix4 m = new Matrix4().scale(5).translate(0,1,0).rotateX(0.5f);

public class Matrix4 {

	float[][] daten = new float[4][4];


	public Matrix4() {
		// TODO mit der Identitaetsmatrix initialisieren

		daten = new float[][]{
				{1,0,0,0},
				{0,1,0,0},
				{0,0,1,0},
				{0,0,0,1}
		};
	}


	public Matrix4(Matrix4 copy) {
		// TODO neues Objekt mit den Werten von "copy" initialisieren

		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				this.daten[i][j] = copy.daten[i][j];
			}
		}
	}

	public Matrix4(float near, float far) {
		// TODO erzeugt Projektionsmatrix mit Abstand zur nahen Ebene "near" und Abstand zur fernen Ebene "far", ggf. weitere Parameter hinzufuegen

		float fov = (float)Math.toRadians(60);
		float aspectRatio = 1.0f;
		float tanHalfFOV = (float)Math.tan(fov / 2);

		daten[0][0] = 1.0f / (aspectRatio * tanHalfFOV);
		daten[1][1] = 1.0f / tanHalfFOV;
		daten[2][2] = -(far + near) / (far - near);
		daten[3][2] = -(2 * far * near) / (far - near);
		daten[2][3] = -1.0f;

	}



	public Matrix4 multiply(Matrix4 other) {
		// TODO hier Matrizenmultiplikation "this = other * this" einfuegen

		float[][] result = new float[4][4];
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				for (int k = 0; k < 4; k++) {
					result[row][col] += other.daten[row][k] * this.daten[k][col];
				}
			}
		}
		this.daten = result;
		return this;


	}

	public Matrix4 translate(float x, float y, float z) {
		// TODO Verschiebung um x,y,z zu this hinzufuegen

		Matrix4 translation = new Matrix4();
		translation.daten = new float[][]{
				{1,0,0,x},
				{0,1,0,y},
				{0,0,1,z},
				{0,0,0,1}
		};
		return this.multiply(translation);
	}

	public Matrix4 scale(float uniformFactor) {
		// TODO gleichmaessige Skalierung um Faktor "uniformFactor" zu this hinzufuegen

		Matrix4 gleichScale = new Matrix4();
		gleichScale.daten = new float[][]{
				{uniformFactor, 0,0,0},
				{0,uniformFactor,0,0},
				{0,0,uniformFactor,0},
				{0,0,0,1}
		};
		return this.multiply(gleichScale);
	}

	public Matrix4 scale(float sx, float sy, float sz) {
		// TODO ungleichfoermige Skalierung zu this hinzufuegen
		Matrix4 ungleichScale = new Matrix4();

		ungleichScale.daten = new float[][]{
				{sx,0,0,0},
				{0,sy,0,0},
				{0,0,sz,0},
				{0,0,0,1}
		};
		return this.multiply(ungleichScale);
	}

	public Matrix4 rotateX(float angle) {
		// TODO Rotation um X-Achse zu this hinzufuegen
		Matrix4 xRotation = new Matrix4();

		xRotation.daten = new float[][]{
				{1,0,0,0},
				{0, (float) Math.cos(angle),-(float) Math.sin(angle),0},
				{0,(float)Math.sin(angle), (float) Math.cos(angle),0},
				{0,0,0,1}
		};
		return this.multiply(xRotation);
	}

	public Matrix4 rotateY(float angle) {
		// TODO Rotation um Y-Achse zu this hinzufuegen

		Matrix4 yRotation = new Matrix4();

		yRotation.daten = new float[][]{
				{(float) Math.cos(angle), 0, -(float) Math.sin(angle),0},
				{0,1,0,0},
				{(float) Math.sin(angle), 0, (float) Math.cos(angle), 0},
				{0,0,0,1}
		};
		return this.multiply(yRotation);
	}

	public Matrix4 rotateZ(float angle) {
		// TODO Rotation um Z-Achse zu this hinzufuegen

		Matrix4 zRotation = new Matrix4();

		zRotation.daten = new float[][]{

				{(float) Math.cos(angle), -(float) Math.sin(angle), 0,0 },
				{(float) Math.sin(angle), (float) Math.cos(angle), 0,0},
				{0,0,1,0},
				{0,0,0,1}
		};
		return this.multiply(zRotation);
	}




	public float[] getValuesAsArray() {
		// TODO hier Werte in einem Float-Array mit 16 Elementen (spaltenweise gefuellt) herausgeben
		float[] values = new float[16];
		for (int i = 0, k = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++, k++) {
				values[k] = daten[j][i]; // Column-major order
			}
		}
		return values;

	}

	public Matrix4 transpose() {
		Matrix4 result = new Matrix4();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				result.daten[i][j] = this.daten[j][i];
			}
		}
		return result;
	}

	public Matrix4 getNormalMatrix() {
		return this.transpose();
	}

}
