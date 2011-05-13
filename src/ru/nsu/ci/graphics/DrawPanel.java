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
	
	class Clr {
		
		public Clr(float r, float g, float b) {
			super();
			this.r = r;
			this.g = g;
			this.b = b;
		}
		float r;
		float g;
		float b;
	}
		
	Clr[][][][] kube = new Clr[3][3][3][6];
	
	Clr white = new Clr(1.0f,1.0f,1.0f);
	Clr red = new Clr(1.0f,0.0f,0.0f);
	Clr green = new Clr(0.0f,0.7f,0.0f);
	Clr blue = new Clr(0.0f,0.0f,1.0f);
	Clr yellow = new Clr(1.0f,1.0f,0.0f);
	Clr orange = new Clr(1.0f,0.5f,0.0f);
	
	
	public void display(GLAutoDrawable gLDrawable) {
		final GL2 gl = gLDrawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -5.0f);
		
		
		
		//for(int i=0;i>3;i++)
		//	kube[i][0][0]=[red,yellow,green,blue,white];
		
		
		// rotate on the three axis
		//gl.glRotatef(rotateT, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(rotateT, 0.0f, 1.0f, 0.0f);
		//gl.glRotatef(rotateT, 0.0f, 0.0f, 1.0f);
		// Draw A Quad
		
		for(int i=0;i<=2;i++)
			for(int j=0;j<=2;j++)
				for(int k=0;k<=2;k++)
				{
					kube[i][j][k][0]=green;
					kube[i][j][k][1]=white;
					kube[i][j][k][2]=orange;
					kube[i][j][k][3]=yellow;
					kube[i][j][k][4]=blue;
					kube[i][j][k][5]=red;
				}
		Clr[] tmp=new Clr[3];
		tmp[0]=kube[0][2][2][0];
		tmp[1]=kube[1][2][2][0];
		tmp[2]=kube[2][2][2][0];
		
		kube[0][2][2][0]=kube[2][2][2][1];
		kube[1][2][2][0]=kube[2][2][1][1];
		kube[2][2][2][0]=kube[2][2][0][1];
		
		kube[2][2][2][1]=kube[2][2][0][5];
		kube[2][2][1][1]=kube[1][2][0][5];
		kube[2][2][0][1]=kube[0][2][0][5];
		
		kube[2][2][0][5]=kube[0][2][0][4];
		kube[1][2][0][5]=kube[0][2][1][4];
		kube[0][2][0][5]=kube[0][2][2][4];
		
		kube[0][2][0][4]=tmp[0];
		kube[0][2][1][4]=tmp[1];
		kube[0][2][2][4]=tmp[2];
		
		Clr[] tmp1=new Clr[3];
		tmp1[0]=kube[2][1][2][0];
		tmp1[1]=kube[2][1][1][0];
		tmp1[2]=kube[2][1][0][0];
		
		kube[0][1][2][0]=kube[0][1][0][4];
		kube[1][1][2][0]=kube[0][1][1][4];
		kube[2][1][2][0]=kube[0][1][2][4];
		
		kube[0][1][0][1]=kube[2][1][0][5];
		kube[0][1][1][1]=kube[1][1][0][5];
		kube[0][1][2][1]=kube[0][1][0][5];
		
		kube[2][1][0][5]=kube[2][1][2][4];
		kube[1][1][0][5]=kube[2][1][1][4];
		kube[0][1][0][5]=kube[2][1][0][4];
		
		kube[2][1][2][4]=tmp1[0];
		kube[2][1][1][4]=tmp1[1];
		kube[2][1][0][4]=tmp1[2];
		
		gl.glBegin(GL2.GL_QUADS);
		for(int i=0;i<=2;i++)
			for(int j=0;j<=2;j++)
				for(int k=0;k<=2;k++)
				{
					
					//0
					gl.glColor3f(kube[i][j][k][0].r,kube[i][j][k][0].g,kube[i][j][k][0].b);          
					gl.glVertex3f(-0.3f+(i-1)*0.65f, 0.3f+(j-1)*0.65f, 0.3f+(k-1)*0.65f); // Top Left
					gl.glVertex3f(0.3f+(i-1)*0.65f, 0.3f+(j-1)*0.65f, 0.3f+(k-1)*0.65f); // Top Right
					gl.glVertex3f(0.3f+(i-1)*0.65f, -0.3f+(j-1)*0.65f, 0.3f+(k-1)*0.65f); // Bottom Right
					gl.glVertex3f(-0.3f+(i-1)*0.65f, -0.3f+(j-1)*0.65f, 0.3f+(k-1)*0.65f); // Bottom Left
					
                    //1
					gl.glColor3f(kube[i][j][k][1].r,kube[i][j][k][1].g,kube[i][j][k][1].b); 
					gl.glVertex3f(0.3f+(i-1)*0.65f, 0.3f+(j-1)*0.65f, -0.3f+(k-1)*0.65f); // Top Left
					gl.glVertex3f(0.3f+(i-1)*0.65f, 0.3f+(j-1)*0.65f, 0.3f+(k-1)*0.65f); // Top Right
					gl.glVertex3f(0.3f+(i-1)*0.65f, -0.3f+(j-1)*0.65f, 0.3f+(k-1)*0.65f); // Bottom Right
					gl.glVertex3f(0.3f+(i-1)*0.65f, -0.3f+(j-1)*0.65f, -0.3f+(k-1)*0.65f); // Bottom Left
					
					//2
					gl.glColor3f(kube[i][j][k][2].r,kube[i][j][k][2].g,kube[i][j][k][2].b); 
					gl.glVertex3f(-0.3f+(i-1)*0.65f, 0.3f+(j-1)*0.65f, -0.3f+(k-1)*0.65f); // Top Left
					gl.glVertex3f(-0.3f+(i-1)*0.65f, 0.3f+(j-1)*0.65f, 0.3f+(k-1)*0.65f); // Top Right
					gl.glVertex3f(0.3f+(i-1)*0.65f, 0.3f+(j-1)*0.65f, 0.3f+(k-1)*0.65f); // Bottom Right
					gl.glVertex3f(0.3f+(i-1)*0.65f, 0.3f+(j-1)*0.65f, -0.3f+(k-1)*0.65f); // Bottom Left
						
					//3
					gl.glColor3f(kube[i][j][k][3].r,kube[i][j][k][3].g,kube[i][j][k][3].b); 
					gl.glVertex3f(-0.3f+(i-1)*0.65f, -0.3f+(j-1)*0.65f, -0.3f+(k-1)*0.65f); // Top Left
					gl.glVertex3f(-0.3f+(i-1)*0.65f, -0.3f+(j-1)*0.65f, 0.3f+(k-1)*0.65f); // Top Right
					gl.glVertex3f(0.3f+(i-1)*0.65f, -0.3f+(j-1)*0.65f, 0.3f+(k-1)*0.65f); // Bottom Right
					gl.glVertex3f(0.3f+(i-1)*0.65f, -0.3f+(j-1)*0.65f, -0.3f+(k-1)*0.65f); // Bottom Left
					
					//4
					gl.glColor3f(kube[i][j][k][4].r,kube[i][j][k][4].g,kube[i][j][k][4].b); 
					gl.glVertex3f(-0.3f+(i-1)*0.65f, 0.3f+(j-1)*0.65f, -0.3f+(k-1)*0.65f); // Top Left
					gl.glVertex3f(-0.3f+(i-1)*0.65f, 0.3f+(j-1)*0.65f, 0.3f+(k-1)*0.65f); // Top Right
					gl.glVertex3f(-0.3f+(i-1)*0.65f, -0.3f+(j-1)*0.65f, 0.3f+(k-1)*0.65f); // Bottom Right
					gl.glVertex3f(-0.3f+(i-1)*0.65f, -0.3f+(j-1)*0.65f, -0.3f+(k-1)*0.65f); // Bottom Left
					
					//5
					gl.glColor3f(kube[i][j][k][5].r,kube[i][j][k][5].g,kube[i][j][k][5].b); 
					gl.glVertex3f(-0.3f+(i-1)*0.65f, 0.3f+(j-1)*0.65f, -0.3f+(k-1)*0.65f); // Top Left
					gl.glVertex3f(0.3f+(i-1)*0.65f, 0.3f+(j-1)*0.65f, -0.3f+(k-1)*0.65f); // Top Right
					gl.glVertex3f(0.3f+(i-1)*0.65f, -0.3f+(j-1)*0.65f, -0.3f+(k-1)*0.65f); // Bottom Right
					gl.glVertex3f(-0.3f+(i-1)*0.65f, -0.3f+(j-1)*0.65f, -0.3f+(k-1)*0.65f); // Bottom Left
				}
		// Done Drawing The Quad
		gl.glEnd();

		// increasing rotation for the next iteration
		rotateT += 0.3f;
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