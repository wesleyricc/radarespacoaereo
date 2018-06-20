package Classes;

import java.text.DecimalFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author comp9
 */
public class Calc {

    double[] result = new double[2];

    public double[] transformaPolar(double x, double y) {

        double v1 = Math.pow(x, 2); //quadrado do x
        double v2 = Math.pow(y, 2); //quadrado do y

        result[0] = Math.sqrt(v1 + v2); //raiz da soma dos quadrados = tenho o Raio (R² = x² + y²)

        result[1] = Math.toDegrees((Math.atan2(y, x))); //Arco tangente ou tangente inversa de y/x = tenho o Angulo (tg-1(m) = y/x)
        return result;
    }

    public double[] transformaCartesiano(double r, double ang) {

        result[0] = r * Math.cos(Math.toRadians(ang)); //x=R.cosX  ---  Math.toRadians - converte angulo de graus para o equivalente em radianos;
        result[1] = r * Math.sin(Math.toRadians(ang)); //y=R.senY

        return result;
    }

    public double[] calculaRotacao(double x, double y, double ang, double xR, double yR) {

        x -= xR;
        y -= yR;

        result[0] = x * Math.cos(Math.toRadians(ang)) - y * Math.sin(Math.toRadians(ang)); //x' = x * cos(B) - y * sen(B)
        result[1] = y * Math.cos(Math.toRadians(ang)) + x * Math.sin(Math.toRadians(ang)); //y' = y * cos(B) + x * sen(B)

        result[0] += xR;
        result[1] += yR;

        return result;
    }

    public double[] calculaEscala(double x, double y, double Sx, double Sy) {

        result[0] = x * (Sx / 100);
        result[1] = y * (Sy / 100);

        return result;

    }

    public double[] calculaTranslacao(double x, double y, double Tx, double Ty) {

        result[0] = x + Tx;
        result[1] = y + Ty;

        return result;
    }

    public double calculaDistanciaDoisPontos(double x1, double y1, double x2, double y2) {
        double dist, dx, dy;

        dx = x2 - x1;
        dx = Math.pow(dx, 2);

        dy = y2 - y1;
        dy = Math.pow(dy, 2);

        dist = Math.sqrt(dx + dy);

        return dist;

    }

    public double calculaTempo(double x1, double y1, double vel, double x2, double y2) {

        double distancia = calculaDistanciaDoisPontos(x1, y1, x2, y2);

        return (distancia / vel) * 3600;

    }

    public double[] calculaInterseccao(double x1, double y1, double dir1, double x2, double y2, double dir2) {
        double angular1, angular2;
        double linear1, linear2;
        double X, Y;
        
        // y = mx + b
        // b = mx - y
        // m = angular
        // b = linear
        angular1 = calculaCoeficientAngular(dir1);
        angular2 = calculaCoeficientAngular(dir2);

        linear1 = calculaCoeficientLinear(angular1, x1, y1);
        linear2 = calculaCoeficientLinear(angular2, x2, y2);

        // chegou na formula da reta
        // y = angular * x + linear ou y = mx + b
        // igualar as duas equações
        // angular1 * x1 + linear1 = angular2 * x2 + linear2
        // passar pra cada lado variváveis iguais
        // angular1 * x1 - (angular2 * x2) =  linear2 - linear1
        // (x1 - x2) = (linear2 - (linear1))
        // X = (linear2 - (linear1))/resultadoDoubleDoX
        //encontrar o X
        X = parseFloat((linear2 - (linear1))) / parseFloat((angular1 - angular2 ));

        // encontrar o Y
        Y = parseFloat(angular1 * X + linear1);

        // distancia do ponto 1 até a colisão e depois do ponto 2
        // calcula velocidade
        result[0] = X;
        result[1] = Y;

        return result;
    }

    private double calculaCoeficientAngular(double angulo) {
        return parseFloat(Math.tan(Math.toRadians(angulo)));
    }

    private double calculaCoeficientLinear(double coeficienteAngular, double x, double y) {
        return parseFloat(y - coeficienteAngular * x);
    }
    
    private Double parseFloat(double value){
        return Double.valueOf(new DecimalFormat("0.##").format(value));
    }
}
