/** Simple Whiteboard drawing application with a modern-looking UI, built with Java Swing.
 *      By Garrison Moore
 *~
 *~
 *      Guiding resources:
 *~
 * Source - https://docs.oracle.com/javase/tutorial/uiswing/painting/index.html
 *      // "Oracle's own guide. Majorly helpful, but somewhat outdated."
 * Source - https://stackoverflow.com/q
 *      Posted by Jean Fredrik Leirtrø, modified by community. See post 'Timeline' for change history
 *      Retrieved 2025-12-09, License - CC BY-SA 3.0
 *      // "Copied this source code for the basis of my project. Expanded and refined code to build this application."
 * Source - https://stackoverflow.com/q
 *      Posted by Ionel Lupu, modified by community. See post 'Timeline' for change history
 *      Retrieved 2025-12-09, License - CC BY-SA 3.0
 *      // "Found this older block of code for creating a buffered image, fed this into the AI assistant, and asked
 *          it how to adapt the code for my use case."
 * Source - IntelliJ AI assistant
 *      // "A major help in showing me resources I otherwise would have to dig for.
 *          Although it seems to prefer to use old deprecated code,
 *          it is extremely useful for explaining how and why things work."
 *~
 * This code builds on these resources.
 */

// Source - https://stackoverflow.com/q
// Posted by Jean Fredrik Leirtrø, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-09, License - CC BY-SA 3.0
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * The WhiteBoard class implements a simple graphical application for drawing with various brush sizes and colors.
 * It provides a GUI built with Swing components where users can interact with a canvas using mouse input to create
 * freehand drawings.
 *~
 * The class extends JFrame and implements MouseMotionListener for handling mouse events on the drawing canvas.
 * Users can choose brush sizes and colors using the provided buttons, and they can clear the canvas using a clear button.
 */
public class WhiteBoard extends JFrame implements MouseMotionListener {

    // clear button
    public JButton clearbutton = new JButton("CLEAR CANVAS");

    // size buttons
    public JLabel sizeLabel = new JLabel("Brush Size");
    public JButton s1 = new JButton("1");
    public JButton s2 = new JButton("2");
    public JButton s3 = new JButton("3");
    public JButton s4 = new JButton("4");
    public JButton s5 = new JButton("5");
    public JButton s6 = new JButton("6");
    public JButton s7 = new JButton("7");
    public JButton s8 = new JButton("8");

    // color buttons
    public JLabel colorlabel = new JLabel("Brush Color");
    public JButton buttonBLACK = new JButton("BLACK");
    public JButton buttonRED = new JButton("RED");
    public JButton buttonGREEN = new JButton("GREEN");
    public JButton buttonBLUE = new JButton("BLUE");
    public JButton buttonYELLOW = new JButton("YELLOW");
    public JButton buttonORANGE = new JButton("ORANGE");
    public JButton buttonMAGENTA = new JButton("MAGENTA");
    public JButton buttonCYAN = new JButton("CYAN");

    //main button panels
    public JPanel buttonpanel = new JPanel();
    public JPanel sizeButtons = new JPanel();
    public JPanel colorButtons = new JPanel();
    public JPanel canvas = new JPanel();

    // variable for the line thickness, used in 'size' buttons
    int currentsize = 1;
    // variable for line color, used in 'color' buttons
    Color currentColor = Color.BLACK;

    // storing colors in variables for ease of use
    Color black = Color.BLACK;
    Color red = Color.RED;
    Color green = Color.GREEN;
    Color blue = Color.BLUE;
    Color yellow = Color.YELLOW;
    Color orange = Color.ORANGE;
    Color magenta = Color.MAGENTA;
    Color cyan = Color.CYAN;


    // adding a buffered image to draw into memory, instead of directly on the frame.
    BufferedImage buffer;


    /** run() method
     * Initializes and sets up the user interface for the WhiteBoard application.
     * The `run` method configures the main JFrame, sets its layout, and integrates the components necessary
     * for the WhiteBoard functionality. Components include buttons for changing drawing size and color,
     * a clear button for resetting the canvas, and a canvas for drawing with mouse input.
     *
     * Key Functionalities:
     * - Sets up the main window's properties (title, layout, size, close operation).
     * - Configures a button panel containing controls for clearing the canvas, selecting brush sizes, and choosing colors.
     * - Creates and sets up the drawing canvas, where user input from mouse movements updates the display.
     * - Implements action listeners for buttons to modify drawing settings and behavior dynamically.
     * - Adds a mouse motion listener to the drawing canvas to track user interaction for drawing.
     */
    public WhiteBoard(){
        // sunflower icon image
        ImageIcon image = new ImageIcon("src/pic.jpg");
        this.setIconImage(image.getImage());
        setTitle("Whiteboard");
        setLayout(new BorderLayout());

        // giving the color buttons colored backgrounds
        buttonBLACK.setBackground(black);
        buttonRED.setBackground(red);
        buttonGREEN.setBackground(green);
        buttonBLUE.setBackground(blue);
        buttonYELLOW.setBackground(yellow);
        buttonORANGE.setBackground(orange);
        buttonMAGENTA.setBackground(magenta);
        buttonCYAN.setBackground(cyan);

        // centering the brush size label
        sizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // setting up the size button panel layout
        sizeButtons.setLayout(new GridLayout(0,1));
        sizeButtons.setPreferredSize(new Dimension(90, 50));
        sizeButtons.setBorder(BorderFactory.createEmptyBorder(0,0,10,5));
        sizeButtons.setBackground(Color.GRAY);
        // adding size buttons to the button panel
        sizeButtons.add(sizeLabel);
        sizeButtons.add(s1);
        sizeButtons.add(s2);
        sizeButtons.add(s3);
        sizeButtons.add(s4);
        sizeButtons.add(s5);
        sizeButtons.add(s6);
        sizeButtons.add(s7);
        sizeButtons.add(s8);

        // centering the color label
        colorlabel.setHorizontalAlignment(SwingConstants.CENTER);
        // setting up the color button panel layout
        colorButtons.setLayout(new GridLayout(0,1));
        colorButtons.setPreferredSize(new Dimension(90, 50));
        colorButtons.setBorder(BorderFactory.createEmptyBorder(0,5,10,0));
        colorButtons.setBackground(Color.GRAY);
        // adding color buttons to the button panel
        colorButtons.add(colorlabel);
        colorButtons.add(buttonBLACK);
        colorButtons.add(buttonRED);
        colorButtons.add(buttonGREEN);
        colorButtons.add(buttonBLUE);
        colorButtons.add(buttonYELLOW);
        colorButtons.add(buttonORANGE);
        colorButtons.add(buttonMAGENTA);
        colorButtons.add(buttonCYAN);

        // setting up the main button panel layout
        buttonpanel.setLayout(new BorderLayout());
        buttonpanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        buttonpanel.setPreferredSize(new Dimension(200, 100));
        buttonpanel.setBackground(Color.GRAY);
        // adding the button panel to the frame, aligned to the left
        add(buttonpanel, BorderLayout.WEST);

        // making the clear button clear the canvas by setting buffer to null and calling repaint()
        clearbutton.addActionListener(e ->  {buffer = null;canvas.repaint();});
        clearbutton.setPreferredSize(new Dimension(0, 50));

        // adding values to the size button variables
        s1.addActionListener(e -> {currentsize = 1;});
        s2.addActionListener(e -> {currentsize = 2;});
        s3.addActionListener(e -> {currentsize = 4;});
        s4.addActionListener(e -> {currentsize = 6;});
        s5.addActionListener(e -> {currentsize = 8;});
        s6.addActionListener(e -> {currentsize = 10;});
        s7.addActionListener(e -> {currentsize = 12;});
        s8.addActionListener(e -> {currentsize = 14;});


        // adding values to the color button variables
        buttonBLACK.addActionListener(e -> {currentColor = black;});
        buttonRED.addActionListener(e -> {currentColor = red;});
        buttonGREEN.addActionListener(e -> {currentColor = green;});
        buttonBLUE.addActionListener(e -> {currentColor = blue;});
        buttonYELLOW.addActionListener(e -> {currentColor = yellow;});
        buttonORANGE.addActionListener(e -> {currentColor = orange;});
        buttonMAGENTA.addActionListener(e -> {currentColor = magenta;});
        buttonCYAN.addActionListener(e -> {currentColor = cyan;});

        /** Main 'canvas' panel, where drawing takes place
         * @Overrides paintComponent() method from JPanel class.
         *
         * Implements buffer-resizing logic
         * - creates a new buffer that matches the dimensions of the panel after resizing.
         */
        canvas = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                // if the buffer is null and is smaller than the panel dimensions, create a new buffer
                if (buffer == null || buffer.getWidth() < getWidth() || buffer.getHeight() < getHeight()) {

                    // match new dimensions to the panel
                    int newWidth = getWidth();
                    int newHeight = getHeight();

                    // fill the width and height of the new buffer with the largest dimension of the current buffer
                    if (buffer != null) {
                        newWidth = Math.max(newWidth, buffer.getWidth());
                        newHeight = Math.max(newHeight, buffer.getHeight());
                    }

                    // create the new, larger buffer
                    BufferedImage newBuffer = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2 = newBuffer.createGraphics();
                    // fill background white
                    g2.setColor(Color.WHITE);
                    // fills the dimensions of the frame with the buffered image
                    g2.fillRect(0, 0, getWidth(), getHeight());
                    // dispose of the graphics object after it is used, necessary for performance / stability.
                    g2.dispose();
                    buffer = newBuffer;
                }
                // draw the new line segment onto the buffer (save it to memory)
                // Draw this buffer to the screen. Setting x and y to 0 will lock the canvas to the top left corner of the panel
                g.drawImage(buffer, 0, 0, null);
            }
        };

        // adding size, color, and clear buttons to the main buttons panel
        buttonpanel.add(clearbutton, BorderLayout.SOUTH);
        buttonpanel.add(sizeButtons, BorderLayout.WEST);
        buttonpanel.add(colorButtons, BorderLayout.EAST);

        // add canvas to the frame, aligned to the center
        add(canvas, BorderLayout.CENTER);
        setResizable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // added close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // attach mouse listener to the canvas
        canvas.addMouseMotionListener(this);
        // set the frame visible
        setVisible(true);
    }

    /**
     * Handles the action of dragging the mouse on the canvas. Draws a line segment
     * between the previous mouse position (`start`) and the current mouse position (`end`).
     * Also triggers a repaint of the canvas to reflect the drawn line.
     *
     * @param evt The MouseEvent triggered by dragging the mouse. Contains
     *            information about the mouse's coordinates and state.
     */
    public void mouseDragged(MouseEvent evt) {
        // ensures lines connect together smoothly
        start = end;
        // capture the current mouse position and create a new Point object from it
        end = new Point(evt.getX(),evt.getY());

        // Drawing logic // moved from paintComponent() to mouseDragged() for smoother lines
        if (start != null && end != null) {
            // create the graphics object from the buffer
            Graphics2D g2 = buffer.createGraphics();
            // enable anti-aliasing for smoother lines. Doesn't seem to work
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // set line color to black
            g2.setColor(currentColor);
            // setting line thickness to the 'currentsize' variable, added CAPPING and JOINING for smoother lines
            g2.setStroke(new BasicStroke(currentsize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            // draw the line segment from 'start' to 'end'
            g2.drawLine(start.x, start.y, end.x, end.y);
            // dispose of the graphics object immediately after it is used.
            g2.dispose();
        }
        // repaint the canvas, not frame
        canvas.repaint();
    }

    // mouseMoved() is not needed, but included from the source code
    public void mouseMoved(MouseEvent evt) {
        end = null;
    }

    /* not needed here. leaving this in because it was part of the source code.

    public void paint(Graphics g) {

    }

    public void update(Graphics g) {
        paint(g);
    }*/

    // default 'start' and 'end' objects to null
    Point start=null;
    Point end=null;
}
/** The code below was copied from stackoverflow.
 * Finding it difficult to locate modern source code or tutorials for this project.
 * I fed this method into the AI assistant and asked it how to adapt the buffered image for my use case.
 * AI showed me an updated version that I modified and adapted for use in the methods above.
 */
// Source - https://stackoverflow.com/q
// Posted by Ionel Lupu, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-09, License - CC BY-SA 3.0
/*
    public void start(){

        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        int[] pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
        boolean running=true;
        while(running){
            BufferStrategy bs=this.getBufferStrategy();
            if(bs==null){
                createBufferStrategy(4);
                return;
            }
            for (int i = 0; i < WIDTH * HEIGHT; i++)
                pixels[i] = 0;

            Graphics g= bs.getDrawGraphics();
            g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
            g.dispose();
            bs.show();

        }
    }}

*/