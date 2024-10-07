#version 330

layout(location = 0) in vec2 v;

out vec3 vertexColor;
uniform float angle = 1.0f;
out mat2 rotation;
void main(){

    rotation = mat2(cos(angle), sin(angle), -sin(angle), cos(angle));
    vec2 rotatedPosition = rotation * v;
vertexColor = vec3(1.0,0.0,0.0);
gl_Position = vec4(rotatedPosition,0.0,1.0);

}