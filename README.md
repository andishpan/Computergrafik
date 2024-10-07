Computergrafik Project HTW Berlin WS23/24

Overview

This project implements core concepts of computer graphics, focusing on matrix and vector mathematics to handle transformations and 3D object manipulation. The project includes fundamental classes that support rendering and manipulating objects in a 3D space, as well as GLSL scripts to implement various shading techniques like Phong and Gouraud shading.

Files in the Project

  - Projekt.java: This is the main file for the project. It handles the setup and execution of the graphics rendering process, including initializing the scene and rendering objects.
  - Vector3.java: A utility class that represents and manipulates 3D vectors. It provides functions to perform vector math like addition, subtraction, scaling, and normalization, crucial for 3D graphics computations.
  - Matrix4.java: This class represents a 4x4 matrix, essential for performing transformations such as rotation, scaling, and translation in 3D space.
  - myObject.java: Defines the objects to be rendered in the scene, including their properties like position, rotation, and transformation logic.
  - Shaders : Gouraud and Phong fragment and vertex shaders

Shading Techniques

The project includes GLSL scripts to implement Phong and Gouraud shading models, which are essential for realistic lighting and shading effects in 3D graphics.

  - Phong Shading: Phong shading provides more realistic rendering by calculating lighting at every pixel, taking into account ambient, diffuse, and specular reflection. The GLSL script for Phong shading handles the calculation of normal vectors per pixel and interpolates the light across the surface for smooth highlights.

  - Gouraud Shading: Gouraud shading calculates lighting at the vertices and interpolates the results across the surfaces of polygons. This approach is computationally cheaper than Phong shading but less accurate for specular highlights. The GLSL script for Gouraud shading computes the lighting at each vertex and interpolates the colors across the face of the polygons.

These shading techniques enhance the visual realism of the objects rendered in the project, giving them a 3D appearance with smooth lighting transitions and surface details.

Running the Project

To run the project:

  - Make sure you have Java installed.
  - Compile the files:

        bash

        javac src/projekt/*.java

Run the Projekt.java class to start the rendering:

    bash

    java src/projekt/Projekt
