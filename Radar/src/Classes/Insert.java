/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Telas.TelaPrincipal;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author comp9
 */
public class Insert {

    TelaPrincipal frame;
    Graphics grafico;
    Get_Set_Pontos ponto;
    BufferedImage img = null;
    int cont = -1;
    

    public Insert(TelaPrincipal telaprincipal) {
        this.frame = telaprincipal;
        loadImg();
    }

    public void loadImg() {
        try {
            img = ImageIO.read(new File("src/aviao.png"));
            System.out.println("Carregou imagem.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {

        System.out.println("Thread iniciada!");

        grafico = frame.getPainelRadar().getGraphics();
        grafico.drawLine(0, 200, 400, 200);
        grafico.drawLine(200, 0, 200, 400);
        Double[] coord = new Double[2];

        if (frame.isAlive()) {
            if (frame.isAcaoExclusao()) {

                while (!frame.getFilaAcao().isEmpty()) {
                    ponto = new Get_Set_Pontos();
                    ponto = frame.getFilaAcao().poll();

                    coord = normalizaPontos(ponto.getX(), ponto.getY());
                    grafico.clearRect(coord[0].intValue() - 10, coord[1].intValue() - 10, 20, 20);

                }

                frame.setAcaoExclusao(false);

            }

            grafico.drawLine(0, 200, 400, 200);
            grafico.drawLine(200, 0, 200, 400);

            for (int i = 0; i < frame.getModel().getRowCount(); i++) {
                coord = normalizaPontos(Double.parseDouble(frame.getModel().getValueAt(i, 2).toString().replace(",", ".")), Double.parseDouble(frame.getModel().getValueAt(i, 3).toString().replace(",", ".")));
                inserePonto(frame.getModel().getValueAt(i, 1).toString(), coord[0], coord[1], Double.parseDouble(frame.getModel().getValueAt(i, 7).toString().replace(",", ".")));

            }

            try {
                java.lang.Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Insert.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    
    public void inserePonto (String nome, Double x, Double y, double dir){
        
        double direcaoObjeto = Math.toRadians(360-dir);
        double posicaoX = img.getWidth() / 2;
        double posicaoY = img.getHeight() / 2;
        
        AffineTransform tx = AffineTransform.getRotateInstance(direcaoObjeto, posicaoX, posicaoY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        
        grafico.drawImage(op.filter(img, null), x.intValue() - 15, y.intValue() - 15, null);
        grafico.drawString(nome, x.intValue() - 15, y.intValue() - 15);
    }
    

    public Double[] normalizaPontos(double x, double y) {
        Double[] coordenadas = new Double[2];
        if (y >= 0) {
            y = 200 - y;
        } else {
            y = -y + 200;
        }
        x = x + 200;

        coordenadas[0] = x;
        coordenadas[1] = y;

        return coordenadas;
    }

}
