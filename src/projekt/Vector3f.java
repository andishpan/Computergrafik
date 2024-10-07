package projekt;

public class Vector3f {
    float x, y, z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f subtract(Vector3f other) {
        return new Vector3f(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3f cross(Vector3f other) {
        return new Vector3f(this.y * other.z - this.z * other.y,
                this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x);
    }

    public void normalize() {
        float length = (float) Math.sqrt(x * x + y * y + z * z);
        x /= length;
        y /= length;
        z /= length;
    }
}