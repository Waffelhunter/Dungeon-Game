package org.example.UI;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.Menu;
import de.gurkenlabs.litiengine.input.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class KeyboardMenu extends Menu {
    public static final Color BUTTON_RED = new Color(140, 16, 16, 200);
    public static final Color BUTTON_BLACK = new Color(0, 0, 0, 201);
    public static final int MENU_DELAY = 180;
    public static long lastMenuInput;
    private final CopyOnWriteArrayList<Consumer<Integer>> confirmConsumer;
    protected int currentFocus = -1;


    public KeyboardMenu(double x, double y, double width, double height, String... items) {
        super(x, y, width, height, items);
        this.confirmConsumer = new CopyOnWriteArrayList<>();

        Input.keyboard().onKeyReleased(e -> {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (this.menuInputIsLocked()) {
                            return;

                        }
                        this.confirm();
                        lastMenuInput = Game.time().now();
                    }
                }
        );
        Input.keyboard().onKeyPressed(KeyEvent.VK_UP, e -> {
            if (this.menuInputIsLocked()) {
                return;
            }

            decFocus();
        });
        Input.keyboard().onKeyPressed(KeyEvent.VK_DOWN, e -> {
            if (this.menuInputIsLocked()) {
                return;
            }

            incFocus();
        });

    }


    private boolean menuInputIsLocked() {
        // disable menu if the game has started
        if (this.isSuspended() || !this.isVisible() || !this.isEnabled()) {
            return true;
        }
        return Game.time().since(lastMenuInput) < MENU_DELAY;
    }
    @Override
    public void prepare() {
        super.prepare();
        this.setForwardMouseEvents(false);
        this.getCellComponents().forEach(comp -> comp.setForwardMouseEvents(false));

        if (!this.getCellComponents().isEmpty()) {
            this.currentFocus = 0;
            this.getCellComponents().get(0).setHovered(true);
        }

        this.getCellComponents().forEach(comp -> {
            comp.getAppearance().setForeColor(Color.WHITE);
            comp.getAppearance().setBackgroundColor1(BUTTON_BLACK);
            comp.getAppearanceHovered().setBackgroundColor1(BUTTON_RED);
            comp.getAppearance().setTransparentBackground(false);
            comp.getAppearanceHovered().setTransparentBackground(false);
            //comp.getAppearance().setTextAntialiasing(RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            //comp.getAppearanceHovered().setTextAntialiasing(RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        });
    }
    public void onConfirm(Consumer<Integer> cons) {
        this.confirmConsumer.add(cons);
    }

    private void confirm() {
        for (Consumer<Integer> cons : this.confirmConsumer) {
            cons.accept(this.currentFocus);
        }
    }

    protected void decFocus() {
        this.currentFocus = Math.floorMod(--this.currentFocus, this.getCellComponents().size());
        this.updateFocus();
    }

    protected void incFocus() {
        this.currentFocus = ++this.currentFocus % this.getCellComponents().size();
        this.updateFocus();
    }

    protected void updateFocus() {
        this.setCurrentSelection(this.currentFocus);
        for (int i = 0; i < this.getCellComponents().size(); i++) {
            this.getCellComponents().get(i).setHovered(i == this.currentFocus);
        }

        lastMenuInput = Game.time().now();

        // if (this.isVisible()) {
        //    Game.audio().playSound(SETTING_CHANGE_SOUND);
        //  }
    }
}
