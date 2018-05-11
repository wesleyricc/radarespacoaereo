package Classes;

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

    public double[] calculaRotacao(double x, double y, double ang) {

        result[0] = x * Math.cos(Math.toRadians(ang)) - y * Math.sin(Math.toRadians(ang)); //x' = x * cos(B) - y * sen(B)
        result[1] = y * Math.cos(Math.toRadians(ang)) + x * Math.sin(Math.toRadians(ang)); //y' = y * cos(B) + x * sen(B)

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

}
