import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.geom.Arc2D;

class Vindu extends JFrame{
	public Vindu(String tittel){
		setTitle(tittel);
		setSize(200,120); //bredde, høyde
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tegning tegningen = new Tegning();
		add(tegningen);
	}
}

class Tegning extends JPanel{
	public void paintComponent(Graphics tegneflate){
		super.paintComponent(tegneflate);
		Color papayawhip = new Color(255,239,213);
		Color munnFarge = new Color(255,190,213);
		Color bakgrunn = new Color(150,190,243);
		setBackground(bakgrunn);
		Font skrift = new Font("Comic Sans MS", Font.BOLD, 50);
		tegneflate.setFont(skrift);

		
		tegneflate.drawOval(250,250,250,250); //kropp
		tegneflate.setColor(papayawhip);
		tegneflate.fillOval(250,250,250,250);
		
		tegneflate.drawOval(300,300,50,50); //venstre øye
		tegneflate.setColor(Color.WHITE);
		tegneflate.fillOval(300,300,50,50);
		
		tegneflate.drawOval(310,320,30,30);
		tegneflate.setColor(Color.BLUE);    //venstre øyefarge
		tegneflate.fillOval(310,320,30,30);
		
		tegneflate.drawOval(320,320,15,15);
		tegneflate.setColor(Color.BLACK);    //venstre pupil
		tegneflate.fillOval(320,330,15,15);
		
		
		tegneflate.drawOval(400,300,50,50);
		tegneflate.setColor(Color.WHITE);
		tegneflate.fillOval(400,300,50,50);//høyre øye
		
		tegneflate.drawOval(410,320,30,30);
		tegneflate.setColor(Color.BLUE);    //høyre øyefarge
		tegneflate.fillOval(410,320,30,30);
		
		tegneflate.drawOval(420,330,15,15);
		tegneflate.setColor(Color.BLACK);    //høyre pupill
		tegneflate.fillOval(420,330,15,15);
		
		 tegneflate.drawArc(340, 360, 80, 80, 0, -180);
		 tegneflate.setColor(munnFarge);				//munn
		 tegneflate.fillArc(340, 360, 80, 80, 0, -180);
		 
		 
		 tegneflate.drawArc(355, 420, 50, 20, 0, -180);
		 tegneflate.setColor(Color.RED);				//tunge
		 tegneflate.fillArc(355, 420, 50, 20, 0, -180);
		 
		 tegneflate.drawString("Death is inevitable" , 100, 100);
		 
		
		
		
	}
}

class Smiley{
	public static void main(String[] args){
		Vindu etVindu = new Vindu("meat beater");
		etVindu.setVisible(true);
	}
}