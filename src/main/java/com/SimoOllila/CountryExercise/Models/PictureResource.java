/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SimoOllila.CountryExercise.Models;

/**
 *
 * @author Adam
 */
public class PictureResource {

    String png;
    String svg;

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(String svg) {
        this.svg = svg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PictureResource{png=").append(png);
        sb.append(", svg=").append(svg);
        sb.append('}');
        return sb.toString();
    }

}
