package env;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import cartago.Artifact;
import cartago.OPERATION;

public class ArCondicionado extends Artifact {

    private int temperature;
    private JLabel label;
    private JSlider slider;

    void init() {
        temperature = 12; // temperatura inicial

        defineObsProperty("temperature", temperature);
        defineObsProperty("status", "idle");

        // cria UI
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(".:: Room Controller ::.");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            label = new JLabel("Temp: " + getObsProperty("temperature").getValue() + " | Status: " + getObsProperty("status").getValue(), SwingConstants.CENTER);
            label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            frame.add(label, BorderLayout.NORTH);

            slider = new JSlider(0, 40, temperature);
            slider.setPaintLabels(true);
            frame.add(slider, BorderLayout.SOUTH);

            slider.addChangeListener(e -> {
                int newTemp = slider.getValue();
                temperature = newTemp;
                updateObsProperty("temperature", temperature);
                label.setText("Temp: " + getObsProperty("temperature").getValue() + " | Status: " + getObsProperty("status").getValue());
            });

            frame.setSize(300, 150);
            frame.setVisible(true);
        });
    }

    @OPERATION
    void aumentar() {
        System.out.println("HEATING");
        temperature++;

        updateObsProperty("status", "heating");
        updateObsProperty("temperature", temperature);
        
        SwingUtilities.invokeLater(() -> {
            label.setText("Temp: " + getObsProperty("temperature").getValue() + " | Status: " + getObsProperty("status").getValue());
            slider.setValue(temperature);
        });
        await_time(1000);
}

    @OPERATION
    void diminuir() {
        System.out.println("COOLING");
        temperature--;

        updateObsProperty("status", "cooling");
        updateObsProperty("temperature", temperature);

            SwingUtilities.invokeLater(() -> {
                label.setText("Temp: " + getObsProperty("temperature").getValue() + " | Status: " + getObsProperty("status").getValue());
                slider.setValue(temperature);
            });

        await_time(1000);

    }

    @OPERATION
    void parar(){
        System.out.println("IDLE");
        updateObsProperty("status", "idle");

        SwingUtilities.invokeLater(() -> {
                label.setText("Temp: " + getObsProperty("temperature").getValue() + " | Status: " + getObsProperty("status").getValue());
                slider.setValue(temperature);
            });

    }
}
