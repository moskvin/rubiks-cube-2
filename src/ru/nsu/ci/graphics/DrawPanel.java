package ru.nsu.ci.graphics;

import java.awt.Component;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.GLLightingFunc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;
import javax.swing.JPanel;

import com.jogamp.opengl.util.Animator;

/**
 * Здесь рисуем кубик
 */
public class DrawPanel extends JPanel implements GLEventListener, KeyListener {
	float rotateT = 0.0f;

	static GLU glu = new GLU();

	static GLCanvas canvas = new GLCanvas();

	static Animator animator = new Animator(canvas);

	public void display(GLAutoDrawable gLDrawable) {
		final GL2 gl = gLDrawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -5.0f);
				
		class Kube
		{
			int[] left = new int[27]; //0x000000;
			int right[];
			int down[];
			int up[];
			int front[];
			int back[];
		}
		
		// rotate on the three axis
		gl.glRotatef(rotateT, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);
		gl.glRotatef(rotateT, 0.0f, 0.0f, 1.0f);
		// Draw A Quad
		gl.glBegin(GL2.GL_QUADS);
		for(int i=-1;i<=1;i++)
			for(int j=-1;j<=1;j++)
				for(int k=-1;k<=1;k++)
				{
					//gl.glColor(Kube.front[n]); // set the color of the quad
					gl.glColor3f(1.0f, 0.0f, 0.0f);
					gl.glVertex3f(-0.3f+i*0.65f, 0.3f+j*0.65f, -0.3f+k*0.65f); // Top Left
					gl.glVertex3f(0.3f+i*0.65f, 0.3f+j*0.65f, -0.3f+k*0.65f); // Top Right
					gl.glVertex3f(0.3f+i*0.65f, -0.3f+j*0.65f, -0.3f+k*0.65f); // Bottom Right
					gl.glVertex3f(-0.3f+i*0.65f, -0.3f+j*0.65f, -0.3f+k*0.65f); // Bottom Left
					
					//gl.glColor3f(r[n], g[n], b[n]);
					gl.glColor3f(0.0f, 0.5f, 0.0f);
					gl.glVertex3f(-0.3f+i*0.65f, 0.3f+j*0.65f, 0.3f+k*0.65f); // Top Left
					gl.glVertex3f(0.3f+i*0.65f, 0.3f+j*0.65f, 0.3f+k*0.65f); // Top Right
					gl.glVertex3f(0.3f+i*0.65f, -0.3f+j*0.65f, 0.3f+k*0.65f); // Bottom Right
					gl.glVertex3f(-0.3f+i*0.65f, -0.3f+j*0.65f, 0.3f+k*0.65f); // Bottom Left
					
					gl.glColor3f(0.0f,0.0f,1.0f);
					gl.glVertex3f(-0.3f+i*0.65f, 0.3f+j*0.65f, -0.3f+k*0.65f); // Top Left
					gl.glVertex3f(-0.3f+i*0.65f, 0.3f+j*0.65f, 0.3f+k*0.65f); // Top Right
					gl.glVertex3f(-0.3f+i*0.65f, -0.3f+j*0.65f, 0.3f+k*0.65f); // Bottom Right
					gl.glVertex3f(-0.3f+i*0.65f, -0.3f+j*0.65f, -0.3f+k*0.65f); // Bottom Left
					
					gl.glColor3f(1.0f,1.0f,1.0f);
					gl.glVertex3f(0.3f+i*0.65f, 0.3f+j*0.65f, -0.3f+k*0.65f); // Top Left
					gl.glVertex3f(0.3f+i*0.65f, 0.3f+j*0.65f, 0.3f+k*0.65f); // Top Right
					gl.glVertex3f(0.3f+i*0.65f, -0.3f+j*0.65f, 0.3f+k*0.65f); // Bottom Right
					gl.glVertex3f(0.3f+i*0.65f, -0.3f+j*0.65f, -0.3f+k*0.65f); // Bottom Left
					
					gl.glColor3f(1.0f,1.0f,0.0f);
					gl.glVertex3f(-0.3f+i*0.65f, -0.3f+j*0.65f, -0.3f+k*0.65f); // Top Left
					gl.glVertex3f(-0.3f+i*0.65f, -0.3f+j*0.65f, 0.3f+k*0.65f); // Top Right
					gl.glVertex3f(0.3f+i*0.65f, -0.3f+j*0.65f, 0.3f+k*0.65f); // Bottom Right
					gl.glVertex3f(0.3f+i*0.65f, -0.3f+j*0.65f, -0.3f+k*0.65f); // Bottom Left
					
					gl.glColor3f(1.0f,0.5f,0.0f);
					gl.glVertex3f(-0.3f+i*0.65f, 0.3f+j*0.65f, -0.3f+k*0.65f); // Top Left
					gl.glVertex3f(-0.3f+i*0.65f, 0.3f+j*0.65f, 0.3f+k*0.65f); // Top Right
					gl.glVertex3f(0.3f+i*0.65f, 0.3f+j*0.65f, 0.3f+k*0.65f); // Bottom Right
					gl.glVertex3f(0.3f+i*0.65f, 0.3f+j*0.65f, -0.3f+k*0.65f); // Bottom Left
				}
		// Done Drawing The Quad
		gl.glEnd();

		// increasing rotation for the next iteration
		rotateT += 0.05f;
	}

	public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged,
			boolean deviceChanged) {
	}

	public void init(GLAutoDrawable gLDrawable) {
		GL2 gl = gLDrawable.getGL().getGL2();
		gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);
		gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
	}

	public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width,
			int height) {
		GL2 gl = gLDrawable.getGL().getGL2();
		if (height <= 0) {
			height = 1;
		}
		float h = (float) width / (float) height;
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(50.0f, h, 1.0, 1000.0);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}


	public DrawPanel() {
		super();
		// add(canvas);
		
		setPreferredSize(new Dimension(600, 600));

	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public GLCanvas getCanvas() {
		return canvas;
	}

	public void start() throws IOException {
		
		canvas.addGLEventListener(new DrawPanel());
		setSize(800,600);
		animator.start();
		canvas.requestFocus();
		}

	public void stop() {
		animator.stop();
		
	}
}