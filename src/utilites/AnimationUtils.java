package utilites;

import javax.swing.Timer;
import javax.swing.*;
import java.util.function.*;
public class AnimationUtils {

    public static final String KEY_SCALE = "anim_scale";
    public static final String KEY_ANGLE = "anim_angle";
    
    
    
    
	//im good at this
    private static double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }

    
    
    private static double easeOutCubic(double t) {
        return 1 - Math.pow(1 - t, 3);
    }

    private static double easeInOutCubic(double t) {
        return t < 0.5 ? 4 * t * t * t : 1 - Math.pow(-2 * t + 2, 3) / 2;
    }

    
    
    
    
    
    private static void applyTransform(JComponent component, double scale, double angle) {
        component.putClientProperty(KEY_SCALE, scale);
        component.putClientProperty(KEY_ANGLE, angle);
        component.repaint(); 
    }

    
    
    
    
    public static void popup(JComponent card, double peakScale, int durationMs, double degrees) {
        final double startScale = 0.0;
        final double peakAngle = Math.toRadians(degrees);
        final double riseShare = 0.4;
        final long startNanos = System.nanoTime();

        Timer timer = new Timer(16, null);
        timer.addActionListener(e -> {
            double t = (System.nanoTime() - startNanos) / 1_000_000.0 / durationMs;

            if (t >= 1.0) {
                applyTransform(card, 1.0, 0.0);
                timer.stop();
                return;
            }

            double scale, angle;
            if (t < riseShare) {
                double p = easeOutCubic(t / riseShare);
                scale = lerp(startScale, peakScale, p);
                angle = lerp(0, peakAngle, p);
            } else {
                double p = easeInOutCubic((t - riseShare) / (1 - riseShare));
                scale = lerp(peakScale, 1.0, p);
                angle = lerp(peakAngle, 0, p);
            }
            applyTransform(card, scale, angle);
        });

        applyTransform(card, startScale, 0.0);
        timer.start();
    }
    
    
    
    
    
    
    public static Timer tween(double from, double to, int durationMs,
                              DoubleUnaryOperator easing, DoubleConsumer onUpdate) {
        final long startNanos = System.nanoTime();
        Timer timer = new Timer(16, null);
        timer.addActionListener(e -> {
            double t = Math.min(1.0, (System.nanoTime() - startNanos) / 1_000_000.0 / durationMs);
            onUpdate.accept(lerp(from, to, easing.applyAsDouble(t)));
            if (t >= 1.0) timer.stop();
        });
        timer.start();
        return timer;
    }
    
    
    
    
    

    public static void pressEffect(AbstractButton button, double pressedScale) {
        final double[] current = {1.0};  
        final double[] target = {1.0};  
        final Timer[] running = {null};

    
        button.getModel().addChangeListener(e -> {
            double newTarget = button.getModel().isPressed() ? pressedScale : 1.0;
            if (newTarget == target[0]) return; 
            target[0] = newTarget;

            if (running[0] != null) {
                running[0].stop();
            }

            boolean pressing = newTarget < 1.0;
            running[0] = tween(current[0], newTarget,
                    pressing ? 70 : 130, 
                    pressing ? AnimationUtils::easeOutCubic : AnimationUtils::easeOutBack,
                    s -> {
                        current[0] = s;
                        applyTransform(button, s, 0.0);
                    });
        });
    }

    
    
    
    private static double easeOutBack(double t) {
        double c1 = 1.70158, c3 = c1 + 1;
        return 1 + c3 * Math.pow(t - 1, 3) + c1 * Math.pow(t - 1, 2);
    }

}