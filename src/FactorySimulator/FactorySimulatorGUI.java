package FactorySimulator;

import BooleanExpressionTree.BoolExpNode;
import BooleanExpressionTree.BooleanExpressionTreeGUI;

import javax.crypto.Mac;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FactorySimulatorGUI extends JPanel implements ActionListener, ChangeListener {

    private DrawPanel drawPanel;
    public static int PANEL_H = 500;
    public static int PANEL_W = 700;
    public static JButton addDispatcher, removeDispatcher, addMachine, removeMachine;
    private JLabel label;
    private static JSlider Maxconsumption, MaxProduction;
    List<Machine> machines = new LinkedList<Machine>();
    List<Dispatcher> dispatchers = new LinkedList<Dispatcher>();

    ConveyorBelt conveyorBelt = new ConveyorBelt(8);
    ConveyorBelt conveyorBelt1 = new ConveyorBelt(8);
    ConveyorBelt conveyorBelt2 = new ConveyorBelt(8);
    ConveyorBelt conveyorBelt3 = new ConveyorBelt(8);
    ConveyorBelt conveyorBelt4 = new ConveyorBelt(8);

    ConveyorBelt[] belts = {conveyorBelt, conveyorBelt1, conveyorBelt2, conveyorBelt3, conveyorBelt4};

    public FactorySimulatorGUI() {
        super(new BorderLayout());
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        super.setPreferredSize(new Dimension(PANEL_W, PANEL_H + 30));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(PANEL_W, 60));
        drawPanel = new DrawPanel();

        addDispatcher = new JButton("Add Dispatcher");
        removeDispatcher = new JButton("Remove Dispatcher");

        addMachine = new JButton("Add Machine");
        removeMachine = new JButton("Remove Machine");

        addDispatcher.addActionListener((ActionListener) this);
        removeDispatcher.addActionListener((ActionListener) this);

        addMachine.addActionListener((ActionListener) this);
        removeMachine.addActionListener((ActionListener) this);

        Maxconsumption = new JSlider(1, 10);
        MaxProduction = new JSlider(1, 10);
        Maxconsumption.addChangeListener(this::stateChanged);
        MaxProduction.addChangeListener(this::stateChanged);


        buttonPanel.add(addDispatcher);
        buttonPanel.add(removeDispatcher);
        buttonPanel.add(Maxconsumption);
        buttonPanel.add(addMachine);
        buttonPanel.add(removeMachine);
        buttonPanel.add(MaxProduction);
        super.add(drawPanel, BorderLayout.CENTER);
        super.add(buttonPanel, BorderLayout.SOUTH);

        label = new JLabel("Number of Dispatchers " + 0);
        super.add(label, BorderLayout.NORTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Thread machineThread;
        Thread dispatcherthread;

        if(source == addDispatcher){
            try {
                Dispatcher d = new Dispatcher(belts);
                dispatchers.add(d);
                dispatcherthread = new Thread(d);
                dispatcherthread.start();
            } catch (InterruptedException interruptedException) {
                System.err.println("Exception was caught");
            }
        }
        if(source == removeDispatcher){
                Random rand = new Random();
                Dispatcher d = dispatchers.remove(rand.nextInt(dispatchers.size()));

        }

        if(source == addMachine){
            try {
                Machine m = new Machine(belts);
                machines.add(m);
                machineThread = new Thread(m);
                machineThread.start();
            } catch (InterruptedException interruptedException) {
                System.err.println("Exception was caught");
            }
        }

        if(source == removeMachine){
            Random rand = new Random();
            machines.remove(rand.nextInt(machines.size()));


        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider change = (JSlider)e.getSource();
        Object source = e.getSource();

        if(source == Maxconsumption) {
            if (!change.getValueIsAdjusting()) {
                int val = (int) change.getValue();
                Machine.MAX_CONSUMPTION_TIME += val;
            }
        }

        if(source == MaxProduction) {
            if (!change.getValueIsAdjusting()) {
                int val = (int) change.getValue();
                Machine.MAX_PRODUCTION_TIME += val;
            }
        }

    }


    private static class DrawPanel extends JPanel {

        public DrawPanel() {
            super();
            super.setBackground(Color.WHITE);
            super.setPreferredSize(new Dimension(PANEL_W, PANEL_H));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }

    //This main method was a CUI trail to see if the code was working or not
//        public static void main(String[] args) throws InterruptedException {
//            List<Machine> machines = new LinkedList<Machine>();
//            List<Dispatcher> dispatchers = new LinkedList<Dispatcher>();
//
//            ConveyorBelt conveyorBelt = new ConveyorBelt(8);
//            ConveyorBelt conveyorBelt1 = new ConveyorBelt(8);
//            ConveyorBelt conveyorBelt2 = new ConveyorBelt(8);
//            ConveyorBelt conveyorBelt3 = new ConveyorBelt(8);
//            ConveyorBelt conveyorBelt4 = new ConveyorBelt(8);
//
//            ConveyorBelt[] belts = {conveyorBelt, conveyorBelt1, conveyorBelt2, conveyorBelt3, conveyorBelt4};
//
//
//            Machine m = new Machine(belts);
//            Dispatcher d = new Dispatcher(belts);
//
//            Thread mach = new Thread(m);
//            Thread disp = new Thread(d);
//
//            mach.start();
//            disp.start();
//        }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Factory Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new FactorySimulatorGUI());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        frame.pack();
        frame.setLocation(new Point((screenWidth / 2) - (frame.getWidth()/2),
                (screenHeight / 2) - (frame.getHeight() / 2)));
        frame.setVisible(true);
    }

    }

