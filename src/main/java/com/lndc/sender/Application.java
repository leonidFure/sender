package com.lndc.sender;

import com.lndc.sender.utils.EmailSenderService;
import com.lndc.sender.utils.EmailSenderServiceImpl;
import com.lndc.sender.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

@SpringBootApplication
public class Application extends JFrame {

    @Autowired
    private EmailSenderService emailSenderService;

    public Application(){
        initUI();
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {
            context.getBean(Application.class).setVisible(true);
        });
    }

    private void initUI() {

        JButton quitButton = new JButton("Quit");
        JButton sendButton = new JButton("Send");

        quitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        sendButton.addActionListener((ActionEvent event) ->{
            emailSenderService.sendEmail("lndcsender@gmail.com", "sad", "sda");
        });

        createLayout(quitButton, sendButton);

        setTitle("Quit button");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0]).addComponent(arg[1])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0]).addComponent(arg[1])
        );
    }

}
