
import edu.princeton.cs.algs4.StdDraw;

import java.io.File;

/**
 * Created by zhangjinrong on 2018/3/15.
 */
public class NBody1 {

    public static double readRadius(String file){
        In in=new In(file);
        in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String file){
        In in=new In(file);
        int numOfPlanets=in.readInt();
        Planet[] planets=new Planet[numOfPlanets];
        in.readDouble();
        for(int i=0;i<numOfPlanets;i++){
            double xxPos=in.readDouble();
            double yyPos=in.readDouble();
            double xxVel=in.readDouble();
            double yyVel=in.readDouble();
            double mass=in.readDouble();
            String imgFileName=in.readString();
            planets[i]=new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filePath="./data/"+args[2];
        double radius=readRadius(filePath);
        StdDraw.setScale(-3e+11, 3e+11);
        StdDraw.picture(0,0, "./images/starfield.jpg");
        Planet[] planets=readPlanets(filePath);
        for(Planet planet:planets){
            StdDraw.picture(planet.xxPos,planet.yyPos,"./images/"+planet.imgFileName);
        }
        for(double t=dt;t<T;t+=dt){
            double[] xForces=new double[planets.length];
            double[] yForces=new double[planets.length];
            int i=0;
            for(Planet planet:planets){
                xForces[i]=planet.calcNetForceExertedByX(planets);
                yForces[i++]=planet.calcNetForceExertedByY(planets);
            }
            for (int j=0;j<xForces.length;j++){
                planets[j].update(dt,xForces[j],yForces[j]);
            }
            StdDraw.clear();
            StdDraw.picture(0,0, "./images/starfield.jpg");
            for(Planet planet:planets){
                StdDraw.picture(planet.xxPos,planet.yyPos,"./images/"+planet.imgFileName);
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
    }
}
