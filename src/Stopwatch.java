import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopwatch implements ActionListener {

    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JLabel timeLabel = new JLabel();
    int elapsedTime = 0; //3600000
    int millisecond = 0;
    int seconds = 0;
    int minutes = 0;
    boolean started = false;
    String milliseconds_string = String.format("%02d", millisecond);
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);

    Timer timer = new Timer(1, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime+= 1000;
            minutes = (elapsedTime/3600000) % 60;
            seconds = (elapsedTime/60000) % 60;
            millisecond = (elapsedTime/1000) % 60;
            milliseconds_string = String.format("%02d", millisecond);
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            timeLabel.setText(minutes_string + ":" + seconds_string + ":" + milliseconds_string);
        }
    });

    Stopwatch(){

        timeLabel.setText(minutes_string + ":" + seconds_string + ":" + milliseconds_string);
        timeLabel.setBounds(100,100,200,100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN,35));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100,200,100,50);
        startButton.setFont(new Font("Verdana", Font.PLAIN,20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(200,200,100,50);
        resetButton.setFont(new Font("Verdana", Font.PLAIN,20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
            start();
            if(started == false) {
                started = true;
                startButton.setText("STOP");
                start();
            }
            else {
                started = false;
                startButton.setText("START");
                stop();
            }
        }
        if(e.getSource() == resetButton){
            started = false;
            startButton.setText("START");
            reset();
        }
    }
    void start() {
        timer.start();
    }
    void stop() {
        timer.stop();
    }
    void reset() {
        timer.stop();
        elapsedTime = 0;
        millisecond = 0;
        seconds = 0;
        minutes = 0;

        milliseconds_string = String.format("%02d", millisecond);
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        timeLabel.setText(minutes_string + ":" + seconds_string + ":" + milliseconds_string);
    }
}
