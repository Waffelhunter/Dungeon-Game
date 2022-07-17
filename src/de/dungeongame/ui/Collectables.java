package de.dungeongame.ui;

import de.gurkenlabs.litiengine.Align;
import de.gurkenlabs.litiengine.util.Imaging;
import de.dungeongame.logic.CollectablesManager;

import java.awt.*;

public class Collectables extends CollectablesMenu {

    public static final Color BUTTON_BLACK = new Color(0, 0, 0, 0);


    public Collectables(double x, double y, double width, double height, String... items) {
        super(x, y, width, height, items);


    }

    @Override
    public void prepare() {
        super.prepare();
        this.setForwardMouseEvents(true);
        this.getCellComponents().forEach(comp -> comp.setForwardMouseEvents(true));

        if (!this.getCellComponents().isEmpty()) {
            this.getCellComponents().get(0).setHovered(true);
        }

        this.getCellComponents().forEach(comp -> {

                    if (CollectablesManager.imgVisible[comp.getComponentId()]) {

                        comp.setImage(Imaging.scale(CollectablesManager.img[comp.getComponentId()], 5.0));
                        comp.setImageAlign(Align.RIGHT);

                        comp.getAppearance().setTransparentBackground(true);
                        comp.getAppearance().setBackgroundColor1(BUTTON_BLACK);
                        comp.getAppearance().setForeColor(BUTTON_BLACK);

                        comp.onHovered(componentMouseEvent -> {
                            this.getCellComponents().forEach(com -> {
                                if (CollectablesManager.imgVisible[com.getComponentId()]) {

                                    com.setImage(Imaging.scale(CollectablesManager.img[com.getComponentId()], 4.0));
                                    com.getAppearance().setTransparentBackground(true);
                                    com.setText(null);
                                }
                            });
                            comp.setImage(Imaging.scale(CollectablesManager.img[comp.getComponentId()], 5.0));
                            comp.setImageAlign(Align.RIGHT);
                            comp.setText("A Brave Warrior, born to fight and left to die");
                            comp.setTextAlign(Align.LEFT);
                            comp.setFontSize(10);
                            comp.getAppearance().setTransparentBackground(false);
                            comp.setTextAntialiasing(true);


                        });


                    } else {

                        System.out.println("hi");
                    }
                }
        );
    }


}



