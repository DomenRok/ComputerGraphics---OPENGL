#version 400 core

in vec3 vertices;
in vec2 textCoords;

out vec2 passTextCoords;

uniform mat4 tvpMatrix;
//uniform mat4 projection;

void main() {
    gl_Position = tvpMatrix * vec4(vertices, 1.0);
    passTextCoords = textCoords;
}