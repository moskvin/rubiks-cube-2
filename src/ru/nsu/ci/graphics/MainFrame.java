package ru.nsu.ci.graphics;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.LayoutStyle.ComponentPlacement;

import ru.nsu.ci.graphics.ControlPanel.OnControlListener;


//import ru.nsu.ci.graphics.ControlPanel.OnClickListener;

public class MainFrame extends JFrame {
    private final ControlPanel controlPanel;
    private final DrawPanel drawPanel;

	public MainFrame() throws HeadlessException {
		this.drawPanel = new DrawPanel();
		this.controlPanel = new ControlPanel();
		this.controlPanel.setListener(new OnControlListener(){

			@Override
			public void start() {
				// TODO Auto-generated method stub
				try {
					drawPanel.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void stop() {
				// TODO Auto-generated method stub
				drawPanel.stop();
			}
				
		} );
       GroupLayout layout = new GroupLayout(getContentPane());
       getContentPane().setLayout(layout);

        // Horizontal layouts
        ParallelGroup horizontalGroup = layout.createParallelGroup(Alignment.LEADING);

        SequentialGroup sequentialGroup = layout.createSequentialGroup();
        sequentialGroup.addContainerGap();
        sequentialGroup.addComponent(drawPanel.getCanvas(), GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE);
        sequentialGroup.addPreferredGap(ComponentPlacement.RELATED);
        sequentialGroup.addComponent(controlPanel);
		sequentialGroup.addContainerGap();
		
		horizontalGroup.addGroup(sequentialGroup);

		// Vertical layouts
		ParallelGroup verticalGroup = layout.createParallelGroup(Alignment.LEADING);
		
		SequentialGroup innerVerticalGroup = layout.createSequentialGroup();
		innerVerticalGroup.addContainerGap();
		ParallelGroup linearLayout = layout.createParallelGroup(Alignment.LEADING);
		linearLayout.addComponent(drawPanel.getCanvas(), GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE);
		linearLayout.addComponent(controlPanel);
        innerVerticalGroup.addGroup(linearLayout);
        innerVerticalGroup.addContainerGap();
        verticalGroup.addGroup(innerVerticalGroup);

        layout.setHorizontalGroup(horizontalGroup);
        layout.setVerticalGroup(verticalGroup);

		this.setTitle("Rubik's Cube Interpreter");
		this.setVisible(true);
		this.setMinimumSize(new Dimension(800,600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
        //this.pack();
	}

	public void exit() {
        drawPanel.stop();
        this.dispose();
        System.exit(0);
    }

}
