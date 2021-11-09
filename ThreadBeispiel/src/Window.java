import java.io.*;
import java.io.FileWriter;
import java.util.Random;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener, Runnable {

    JButton btn;
    JLabel label;
    Thread thread;
    JProgressBar progressBar;


    
    public Window() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(null);

        btn = new JButton("speichern");
        btn.setBounds(10, 10, 100, 25);
        add(btn);
        btn.addActionListener(this);

        label = new JLabel("Die Daten sind noch nicht gespeichert!");
        label.setBounds(10, 45, 600, 25);
        add(label);

        progressBar = new JProgressBar();
        progressBar.setBounds(10, 80, 200, 25);
        add(progressBar);
        progressBar.setStringPainted(true);  // prozentuale Anzeige aktivieren
        progressBar.setForeground(new Color(255, 0, 0));
            
    }

    public static void main(String[] args) {
        new Window().setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
         //  System.out.println("Daten werden gespeichert");
        if(thread == null || !thread.isAlive()) {
            Thread thread = new Thread(this);
            thread.start();
        }

    }    
    public void run() {
        try {
            String text = "Daten werden gespeichert! " ;
            int schreibVorgaenge = 100000;
            for(int i = 1; i <= schreibVorgaenge; i++) {
                if(i % 1000 == 0) {
                    label.setText(text + i/1000 + " %");
                    progressBar.setValue(i/1000);
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter("thread.txt", true));
                char c = (char)(new Random().nextInt(127 - 33) + 33); 
                bw.write('x'); // zufälliger Character von 33 bis 126
                bw.flush();
                bw.close();             
                } 
            } catch (Exception ex) {
            System.out.println("Schreibfehler");
        }


 
        }

    }


