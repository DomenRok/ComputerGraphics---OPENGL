#version 400 core

in vec2 passTextCoords;

out vec4 fragColor;

uniform sampler2D semple;

void main() {
    fragColor = texture(semple, passTextCoords);
}