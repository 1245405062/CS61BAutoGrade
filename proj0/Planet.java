/**
 * Created by zhangjinrong on 2018/3/14.
 */


public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double G = 6.67e-11;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.xxVel = p.xxVel;
        this.yyPos = p.yyPos;
        this.yyVel=p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((p.xxPos - xxPos) * (p.xxPos - xxPos) + (p.yyPos - yyPos) * (p.yyPos - yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        return (G * p.mass * this.mass) / (calcDistance(p) * calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double F = 0;
        for (Planet planet : planets) {
            if (!equals(planet)) {
                F += calcForceExertedByX(planet);
            }
        }
        return F;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double F = 0;
        for (Planet planet : planets) {
            if (!equals(planet)) {
                F += calcForceExertedByY(planet);
            }
        }
        return F;
    }
    public void update(double time,double xforce,double yforce){
        double xAcc=xforce/mass;
        double yAcc=yforce/mass;
        xxVel=xxVel+xAcc*time;
        yyVel=yyVel+yAcc*time;
        xxPos=xxPos+xxVel*time;
        yyPos=yyPos+yyVel*time;
    }

}
