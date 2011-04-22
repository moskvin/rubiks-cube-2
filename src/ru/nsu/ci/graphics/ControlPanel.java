package ru.nsu.ci.graphics;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Здесь помещаем компоненты ввода текста, 
 * кнопки запуска и остановки интерпретации
 */
public class ControlPanel extends JPanel{
	private JButton stopButton;
	private JButton startButton;
	//private JTextArea textCode;
	private OnControlListener listener;
	
	public ControlPanel() {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		
		//c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		
		startButton = new JButton(Strings.BUTTON_START);
		stopButton = new JButton(Strings.BUTTON_STOP);
		//textCode = new JTextArea(30, 50);
		//textCode.setBackground(Color.LIGHT_GRAY);

		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.5;
		add(startButton, c);
		
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.5;
		add(stopButton, c);
		
		c.gridx = 0;
		c.gridy = 0;		
		c.gridwidth = 2;
		//add(textCode, c);
		ActionListener l = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				String command = event.getActionCommand();
				if (command.equals(Strings.BUTTON_START)){
					startButton.setEnabled(false);
					stopButton.setEnabled(true);
					listener.start();
				}
				if (command.equals(Strings.BUTTON_STOP)){
					startButton.setEnabled(true);
					stopButton.setEnabled(false);
					listener.stop();
				}
			}
				
		};
		startButton.addActionListener(l);
		stopButton.addActionListener(l);
		
		clear();
	}
	
	public interface OnControlListener{
		void start();
		void stop();
	};
	
	public OnControlListener getListener() {
		return listener;
	}

	public void setListener(OnControlListener listener) {
		this.listener = listener;
	}

	public JButton getStopButton() {
		return stopButton;
	}

	public void setStopButton(JButton stopButton) {
		this.stopButton = stopButton;
	}

	public JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(JButton startButton) {
		this.startButton = startButton;
	}
	
	/**
	 * Возврат в начальное состояние
	 */
	public void clear() {
		startButton.setEnabled(true);
		stopButton.setEnabled(false);
	}
}

