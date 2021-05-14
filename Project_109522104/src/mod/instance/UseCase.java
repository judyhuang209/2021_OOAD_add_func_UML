package mod.instance;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;

import bgWork.handler.CanvasPanelHandler;
import mod.IClassPainter;
import mod.IFuncComponent;

public class UseCase extends JPanel implements IFuncComponent, IClassPainter
{
	Vector <String>		texts			= new Vector <>();
	Dimension			defSize			= new Dimension(150, 40);
	int					maxLength		= 20;
	boolean				isSelect		= false;
	int					selectBoxSize	= 5;
	CanvasPanelHandler	cph;
	int					tolerance		= selectBoxSize + 5;
	int					selectedPort	= 20210514;
	
	public UseCase(CanvasPanelHandler cph)
	{
		texts.add("New Use Case");
		reSize();
		this.setVisible(true);
		this.setLocation(0, 0);
		this.setOpaque(true);
		this.cph = cph;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		reSize();
		for (int i = 0; i < texts.size(); i ++)
		{
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, defSize.width, defSize.height);
			g.setColor(Color.BLACK);
			g.drawOval(0, 0, defSize.width - 1, defSize.height - 1);
			if (texts.elementAt(i).length() > maxLength)
			{
				g.drawString(texts.elementAt(i).substring(0, maxLength) + "...",
						3, (int) (0 + (i + 0.8) * defSize.getHeight()));
			}
			else
			{
				g.drawString(texts.elementAt(i), (int) (defSize.width * 0.2),
						(int) (defSize.getHeight()) / 2);
			}
		}
		if (isSelect == true)
		{
			paintSelect(g);
		}
	}

	public boolean isSelect()
	{
		return isSelect;
	}

	public void setSelect(boolean isSelect)
	{
		this.isSelect = isSelect;
	}

	// get which port is clicked
	public int getSelectedPort(MouseEvent e) {
		if(isSelect) {
			Point p = new Point(e.getPoint());
			Point leftTop = this.getLocation();
			
//			// ports
//			  __3__
//			1|     |2
//			 |_____|
//			    0
			Point port3 = new Point(leftTop.x + this.getWidth()/2, leftTop.y);
			Point port2 = new Point(leftTop.x + this.getWidth(), leftTop.y + this.getHeight()/2);
			Point port1 = new Point(leftTop.x, leftTop.y + this.getHeight()/2);
			Point port0 = new Point(leftTop.x + this.getWidth()/2, leftTop.y + this.getHeight());
			
			System.out.println("in class port x = " + p.x);
			System.out.println("in class port y = " + p.y);
			if (p.x > port0.getX() - tolerance
				&& p.x < port0.getX() + tolerance					
				&& p.y > port0.getY() - tolerance
				&& p.y <= port0.getY())
			{
				System.out.println("port 0!");
				selectedPort = 0;
				return selectedPort;
			}
			else if (p.x >= port1.getX()
					&& p.x < port1.getX() + tolerance					
					&& p.y > port1.getY() - tolerance
					&& p.y < port1.getY() + tolerance)
			{
				System.out.println("port 1!");
				selectedPort = 1;
				return selectedPort;
			}
			else if (p.x <= port2.getX()
					&& p.x > port2.getX() - tolerance					
					&& p.y > port2.getY() - tolerance
					&& p.y < port2.getY() + tolerance)
			{
				System.out.println("port 2!");
				selectedPort = 2;
				return selectedPort;
			}
			else if (p.x > port3.getX() - tolerance
					&& p.x < port3.getX() + tolerance					
					&& p.y >= port3.getY() 
					&& p.y < port3.getY() + tolerance)
			{
				System.out.println("port 3!");
				selectedPort = 3;
				return selectedPort;
			}
			
			else
			{
				System.out.println("no port!");
				selectedPort = 20210514;
				return selectedPort;
			}
		}
		
		else
		{
			System.out.println("no port!");
			selectedPort = 20210514;
			return selectedPort;
		}
	}
	
	@Override
	public void reSize()
	{
		switch (texts.size())
		{
			case 0:
				this.setSize(defSize);
				break;
			default:
				this.setSize(defSize.width, defSize.height);
				break;
		}
	}

	@Override
	public void setText(String text)
	{
		texts.clear();
		texts.add(text);
		this.repaint();
	}

	@Override
	public void paintSelect(Graphics gra)
	{
		gra.setColor(Color.BLACK);
		gra.fillRect(this.getWidth() / 2 - selectBoxSize, 0, selectBoxSize * 2,
				selectBoxSize);
		gra.fillRect(this.getWidth() / 2 - selectBoxSize,
				this.getHeight() - selectBoxSize, selectBoxSize * 2,
				selectBoxSize);
		gra.fillRect(0, this.getHeight() / 2 - selectBoxSize, selectBoxSize,
				selectBoxSize * 2);
		gra.fillRect(this.getWidth() - selectBoxSize,
				this.getHeight() / 2 - selectBoxSize, selectBoxSize,
				selectBoxSize * 2);
		switch (selectedPort) 
		{
			case 0:
				gra.setColor(Color.RED);
				gra.fillRect(this.getWidth() / 2 - selectBoxSize,
						this.getHeight() - selectBoxSize, selectBoxSize * 2,
						selectBoxSize);
				break;
			case 1:
				gra.setColor(Color.RED);
				gra.fillRect(0, this.getHeight() / 2 - selectBoxSize, selectBoxSize,
						selectBoxSize * 2);
				break;
			case 2:
				gra.setColor(Color.RED);
				gra.fillRect(this.getWidth() - selectBoxSize,
						this.getHeight() / 2 - selectBoxSize, selectBoxSize,
						selectBoxSize * 2);
				break;
			case 3:
				gra.setColor(Color.RED);
				gra.fillRect(this.getWidth() / 2 - selectBoxSize, 0, selectBoxSize * 2,
						selectBoxSize);
				break;
			default:
				break;
		}
	}
}
