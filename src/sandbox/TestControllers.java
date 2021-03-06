/*
 * Test for the Light Weight Java game library
 * Requires library to be in path 
 */
package sandbox;

        /*
	 * Copyright (c) 2002-2008 LWJGL Project
	 * All rights reserved.
	 *
	 * Redistribution and use in source and binary forms, with or without
	 * modification, are permitted provided that the following conditions are
	 * met:
	 *
	 * * Redistributions of source code must retain the above copyright
	 *   notice, this list of conditions and the following disclaimer.
	 *
	 * * Redistributions in binary form must reproduce the above copyright
	 *   notice, this list of conditions and the following disclaimer in the
	 *   documentation and/or other materials provided with the distribution.
	 *
	 * * Neither the name of 'LWJGL' nor the names of
	 *   its contributors may be used to endorse or promote products derived
	 *   from this software without specific prior written permission.
	 *
	 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
	 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
	 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
	 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
	 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
	 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
	 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
	 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
	 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
	 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
	 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
	 */
	
	import java.awt.Dimension;
	import java.awt.event.WindowAdapter;
	import java.awt.event.WindowEvent;
	
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTextField;
        

	import org.lwjgl.input.Controller;
	import org.lwjgl.input.Controllers;
	
	
/**
	 * Oops. Forgot to document this one.
	 *
	 * @author Kevin Glass
	 */
	public class TestControllers extends JPanel {
	        public static int total;



	
	        private JTextField[] values;
	        private JTextField[] names;
	        private Controller controller;
	        private int buttonCount;
	        private int itemCount;
	
                //Constructor - Creates a Controller object for every controller detected
	        public TestControllers(int index) {
                    
                        //Get the controller index from the Controllers group
	                controller = Controllers.getController(index);
                        //Gui LayoutManager
	                setLayout(null);
	
                        //Get the number of buttons on the controller
	                buttonCount = controller.getButtonCount();
                        //Get the number of buttons + the number of hat pads
	                itemCount   = controller.getButtonCount() + controller.getAxisCount() + 2;
                        //Create a Gui Box for every button / hat pad
	                values      = new JTextField[itemCount];
	                names       = new JTextField[itemCount];
	
	                for (int i=0;i<controller.getButtonCount();i++) {
	                        names[i] = new JTextField();
	                        names[i].setEditable(false);
	                        names[i].setBounds(0,i*30,100,30);
	                        names[i].setText(controller.getButtonName(i));
	                        add(names[i]);
	                        values[i] = new JTextField();
	                        values[i].setEditable(false);
	                        values[i].setBounds(100,i*30,100,30);
	                        add(values[i]);
	                }
	                for (int i=buttonCount;i<buttonCount+controller.getAxisCount();i++) {
	                        names[i] = new JTextField();
	                        names[i].setEditable(false);
	                        names[i].setBounds(0,i*30,100,30);
	                        names[i].setText(controller.getAxisName(i-buttonCount));
	                        add(names[i]);
	                        values[i] = new JTextField();
	                        values[i].setEditable(false);
	                        values[i].setBounds(100,i*30,100,30);
	                        add(values[i]);
	                }
	
	                int i = itemCount - 2;
	                names[i] = new JTextField();
	                names[i].setEditable(false);
	                names[i].setBounds(0,i*30,100,30);
	                names[i].setText("POV X");
	                add(names[i]);
	                values[i] = new JTextField();
	                values[i].setEditable(false);
	                values[i].setBounds(100,i*30,100,30);
	                add(values[i]);
	
	                i = itemCount - 1;
	                names[i] = new JTextField();
	                names[i].setEditable(false);
	                names[i].setBounds(0,i*30,100,30);
	                names[i].setText("POV Y");
	                add(names[i]);
	                values[i] = new JTextField();
	                values[i].setEditable(false);
	                values[i].setBounds(100,i*30,100,30);
	                add(values[i]);
	
	                total++;
	
	                setPreferredSize(new Dimension(200,30*itemCount));
	                JFrame frame = new JFrame(controller.getName());
	                frame.setContentPane(new JScrollPane(this));
	                frame.addWindowListener(new WindowAdapter() {
	                        public void windowClosing(WindowEvent e) {
	                                total--;
	                                if (total == 0) {
	                                        System.exit(0);
	                                }
	                        }
	                });
	                frame.setSize(230,400);
	                frame.setLocation(index*30,index*30);
	                frame.setVisible(true);
	        }
	
                /*
                 * Updates the values of each button in the gui
                 * 
                 */
	        public void updateDetails() {
	                for (int i=0;i<controller.getButtonCount();i++) {
	                        values[i].setText(""+controller.isButtonPressed(i));
	                }
	                for (int i=buttonCount;i<buttonCount+controller.getAxisCount();i++) {
	                        values[i].setText(""+controller.getAxisValue(i-buttonCount));
	                }
	
	                values[itemCount-2].setText(""+controller.getPovX());
	                values[itemCount-1].setText(""+controller.getPovY());
	        }
                
                
                /**
                 * Converts the button number, to the game event
                 * Example: Pressing button 3 on controller means player turns right
                 * This is pro propritary to xbox controllers and snake game
                 * 
                 * @param controllerNumber The number of the controller, starts at 0
                 * @param buttonNumber The button number on the controller usually 0 - 22
                 */
                public static void handleButtonPush( int controllerNumber, int buttonNumber ) {
                    
                   // System.out.println("Controller " + controllerNumber + " button " + buttonNumber);
                    
                    switch (buttonNumber) {
                        
                        case 0: System.out.println("User " + controllerNumber + " turned North");
                            break;
                        case 1: System.out.println("User " + controllerNumber + " turned South");
                            break;
                        case 2: System.out.println("User " + controllerNumber + " turned West");
                            break;
                        case 3: System.out.println("User " + controllerNumber + " turned East");
                            break;
                            
                        
                            
                    }
                    
                    
                }
                
                
                
	
	        public static void main(String[] argv) {
                    
                        //Create the environment of controllers
	                try {
	                        Controllers.create();
	                } catch (Exception e) {
	                        e.printStackTrace();
	                        System.exit(0);
	                }
	
                        //Print how many controllers there are
	                int count = Controllers.getControllerCount();
	                System.out.println(count+" Controllers Found");
                        
                        //Print the name of each controller
	                for (int i=0;i<count;i++) {
	                        Controller controller = Controllers.getController(i);
	                        System.out.println(controller.getName() + " " );
                                System.out.println("Number of Rumblers " + controller.getRumblerCount());
                                System.out.println("Index Number " + controller.getIndex() );

	                }
	
                        //If there are no controllers, exit program
	                if (count == 0) {
	                        System.exit(0);
	                }
	
	                TestControllers[] controllerWindows = new TestControllers[count];
	                for (int i=0;i<count;i++) {
	                        controllerWindows[i] = new TestControllers(i);
	                }
	
                        //Start the "Game"
	                boolean running = true;
	                while (running) {
                                //Sleep for 100 milliseonds, no need to poll too fast
	                        try { Thread.sleep(100); } catch (Exception e) {};
                                
                                //Poll all the controllers
	                        Controllers.poll();
                                
                                /*
                                 * Controllers have an event listener
                                 * Whenever a button is pushed it add the event 
                                 * it to the buffer of the controller instance
                                 * As long as the buffer has something in it 
                                 * we print it out                                 * 
                                 */
	                        while (Controllers.next()) {
//	                                System.out.println("Event Fired: ");
//	                                System.out.println("\t"+Controllers.getEventNanoseconds());
//	                                System.out.println("\t"+Controllers.getEventSource()+":"+Controllers.getEventControlIndex()+":"+Controllers.isEventButton());
//	                                System.out.println("\t"+Controllers.isEventXAxis()+":"+Controllers.isEventYAxis());
                                       
                                    
                                        //Controller number
                                       int controllerNumber =  Controllers.getEventSource().getIndex();
                                        //Button number
                                       int buttonNumber     = Controllers.getEventControlIndex();
                                       /*
                                        * Pressing a button on an xBox controller slightly alters the analog values
                                        * This causes multiple unwanted events on button push
                                        * By checking to see if the event was a button we can strip out the uneeded events
                                        */
                                       if ( Controllers.isEventButton() == true ) {
                                        handleButtonPush( controllerNumber, buttonNumber );  
                                       }
                                       
                                        
                                   
	                        }
	
	                        for (int i=0;i<count;i++) {
	                                controllerWindows[i].updateDetails();
	                        }
	                }
	        }//end main
                
	}//end Test Controllers
