#version 330
out vec3 pColor;
out vec3 finalColor;

vec2 A = vec2(100.0, 100.0);
vec2 B = vec2(500.0, 400.0);

float thickness = 5.0;


bool isInCircle(vec2 point, vec2 center, float radius){

    return distance(point,center) <= radius;
}

bool isInRectangle(vec2 point, vec2 bottomLeft, vec2 topRight){
    return point.x >= bottomLeft.x && point.x <= topRight.x && point.y >= bottomLeft.y && point.y <= topRight.y;
}

bool isInRotatedRectangle(vec2 point, vec2 bottomLeft, vec2 topRight, float theta){

    mat2 rotation = mat2(cos(theta), -sin(theta), sin(theta), cos(theta));


    vec2 rotatedPoint = rotation * (point - (bottomLeft + topRight) * 0.5) + (bottomLeft + topRight) * 0.5;


    return rotatedPoint.x >= bottomLeft.x && rotatedPoint.x <= topRight.x &&
    rotatedPoint.y >= bottomLeft.y && rotatedPoint.y <= topRight.y;
}

void drawRotatedRectangle(vec2 canvas, vec2 bottomLeft, vec2 topRight, vec3 color){

    float theta = radians(45.0);

    if(isInRotatedRectangle(canvas, bottomLeft, topRight,theta)){
        finalColor = color;
    }

}


void drawCircle(vec2 canvas, vec2 center, float radius, vec3 color){

    if(isInCircle(canvas, center, radius)){
        finalColor = color;
    }
}


void drawRectangle(vec2 canvas, vec2 bottomLeft, vec2 topRight, vec3 color){
    if (isInRectangle(canvas, bottomLeft, topRight)) {
        finalColor = color;
    }
}

/*void drawSimpleLine(vec2 pixelPoint, vec3 color){

	float m = (B.y - A.y) / (B.x - A.x);
	float expectedY = m * (pixelPoint.x - A.x) + A.y;

	if(abs(pixelPoint.y - expectedY) < thickness * 0.5){
    	finalColor = color;
	}
}  */

float distanceToLine(vec2 p1, vec2 p2, vec2 point) {
    float a = p1.y-p2.y;
    float b = p2.x-p1.x;
    return abs(a*point.x+b*point.y+p1.x*p2.y-p2.x*p1.y) / sqrt(a*a+b*b);
}

/*void drawLineUsingDistance(vec2 P, vec3 color) {


	if(distanceToLine(A, B, P) < thickness * 0.5) {
    	finalColor = color;
	}
}  */


/*void drawLine(vec2 pixelPoint, vec3 color){

	vec2 directionAtoB =  B - A;

	vec2 directionAtoP = pixelPoint - A;

	float t = dot(directionAtoP,directionAtoB) / dot(directionAtoB,directionAtoB);

	vec2 closest =  A + t * directionAtoB;

	float d = length(closest - pixelPoint);

	if(d < thickness * 0.5){
    	finalColor = color;
	}

} */

bool isPointBetween(vec2 A, vec2 B, vec2 P) {
    vec2 AP = P - A;
    vec2 BP = P - B;

    return dot(AP, BP) < 0.0;
}

void drawLineSegment(vec2 P, vec3 color) {
    float dist = distanceToLine(A, B, P);

    if (dist < 3.0 && isPointBetween(A, B, P)) {
        finalColor = color;
    }
}


void drawShapes(vec2 canvas){
    finalColor = vec3(1.0,2.0,1.0);

    vec2 center = vec2(350.0, 350.0);
    float radius = 200.0;

    vec2 rectBottomLeft = vec2(300.0, 300.0);
    vec2 rectTopRight = vec2(500.0, 400.0);

    drawCircle(canvas, vec2(350.0, 350.0), 200.0, vec3(0.2, 0.7, 0.7));
    //drawRectangle(canvas, vec2(300.0, 300.0), vec2(500.0, 400.0), vec4(0.5, 0.0, 0.0, 1.0));

    drawCircle(canvas, vec2(550.0, 550.0), 150.0, vec3(0.7, 0.7, 0.2));
    drawRectangle(canvas, vec2(100.0, 100.0), vec2(250.0, 200.0), vec3(0.0, 0.5, 0.0));
    drawRotatedRectangle(canvas, vec2(300.0, 300.0), vec2(500.0, 400.0), vec3(0.5, 0.0, 0.0));

    drawLineSegment(canvas,vec3(1.0,0.0,0.0));
}




/*void drawCircle2(vec2 canvas){
   vec2 center = vec2(350.0, 350.0);
   float radius = 200.0;

   if(isInCircle(canvas, center, radius)){
   	finalColor = vec4(0.2,0.7,0.7,1.0);
   }else{
   	finalColor = vec4(1.0,1.0,1.0,1.0);
   }

}  */



void main() {

    //drawCircle(gl_FragCoord.xy);
    //drawRectanglePixelSpace(300.0, 500.0, 300.0, 400.0);
    drawShapes(gl_FragCoord.xy);
}
