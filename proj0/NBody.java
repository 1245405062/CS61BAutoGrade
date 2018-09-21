package proj0;
public class NBody{
  public static double readRadius(String address){
    In in = new In(address);
    double first = in.readDouble();
    return in.readDouble();
  }

  public static Planet[] readPlanets(String address){
    In in = new In(address);
    int n = in.readInt();
    Planet[] p = new Planet[n];
    double radius = in.readDouble();
    for (int i = 0; i < n; i++){
      p[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
    }
    return p;
  }

  public static void main(String[] args) {
    String imageToDraw = "images/starfield.jpg";
    double init;
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = "./data/"+args[2];
    double radius = NBody.readRadius(filename);
    Planet[] all = NBody.readPlanets(filename);
    In in = new In(filename);
    int n = in.readInt();
    double[] xForces = new double[n];
    double[] yForces = new double[n];
    StdDraw.setScale(-3e+11, 3e+11);
    //StdDraw.clear();
    //StdDraw.picture(0, 0, imageToDraw);
    StdDraw.enableDoubleBuffering();


    for(init = 0; init < T; init += dt ){
      StdDraw.picture(0, 0, imageToDraw);
      for (int j = 0; j < n; j++){
        for (int i = 0; i < n; i++){
          xForces[i] = all[i].calcNetForceExertedByX(all);
          yForces[i] = all[i].calcNetForceExertedByY(all);
        }
        all[j].update(dt, xForces[j], yForces[j]);
        StdDraw.clear();
        StdDraw.picture(0,0, "./images/starfield.jpg");
        for(Planet planet:all){
          StdDraw.picture(planet.xxPos,planet.yyPos,"./images/"+planet.imgFileName);
        }
      }
      StdDraw.show();
      StdDraw.pause(10);
    }
    StdOut.printf("%d\n", all.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < all.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                      all[i].xxPos, all[i].yyPos, all[i].xxVel,
                      all[i].yyVel, all[i].mass, all[i].imgFileName);
    }



  }

}
