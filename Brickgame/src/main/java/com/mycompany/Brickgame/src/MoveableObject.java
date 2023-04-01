import java.awt.Color;
import java.awt.event.KeyEvent;


public abstract class MoveableObject extends GameObject {
        static int track_x=300,track_y=600;
        static int ball_x,ball_y;
	protected boolean live = true; // isLive
	protected int deltaX;	  // SPEED
	protected int deltaY;
	//public static enum Direction {UP, RIGHT, DOWN, LEFT}
	protected int strength; 	  // life value
	protected int direction=KeyEvent.VK_RIGHT;
	
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	/**
	 * 
	 */
	public MoveableObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 */
	public MoveableObject(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public int getDeltaX() {
		return deltaX;
	}
	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}
	public int getDeltaY() {
		return deltaY;
	}

   
	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	};
	public void move() {
		switch (this.direction) {
		case KeyEvent.VK_UP:
			y-=deltaY;
                        y-=2;
			break;
		case KeyEvent.VK_RIGHT:
			x+=deltaX;
                        x+=2;
			break;
		case KeyEvent.VK_DOWN:
			y+=deltaY;
                        y+=2;
			break;
		case KeyEvent.VK_LEFT:
			x-=deltaX;
                        x-=2;
			break;

		default:
			break;
		}
                track_x=x;
                track_y=y;
                //System.out.println("track_x="+getTrack_x()+",track_y="+getTrack_y());
	}
		public void moveball() {
                    ball_x=x;
                    ball_y=y;
                 if(this.direction==KeyEvent.VK_RIGHT)
                 {
                    if(x==780)
                    {
                        this.direction=KeyEvent.VK_LEFT;
                    }
                    else if(y==15)
                    {
                        this.direction=KeyEvent.VK_DOWN;
                      
                    }
                 }
                 else if(this.direction==KeyEvent.VK_LEFT)
                 {
                     if(x==20)
                     {
                         this.direction=KeyEvent.VK_RIGHT;
                     }
                     else if(y==15)
                     {
                         this.direction=KeyEvent.VK_UP;
                     }
                 }
                 else if(this.direction==KeyEvent.VK_UP)
                 {
                     if(x==20)
                     {
                         this.direction=KeyEvent.VK_DOWN;
                     }
                     else if(y==605||(((track_x)<=x&&(track_x+60>=x))&&(track_y-15<=y&&track_y+15>=y)))
                     {
                         
                         this.direction=KeyEvent.VK_LEFT;
                     }
                 }
                 else if(this.direction==KeyEvent.VK_DOWN)
                 {
                     if(y==605||(((track_x)<=x&&(track_x+60>=x))&&(track_y-15<=y&&track_y+15>=y)))
                     {
                         this.direction=KeyEvent.VK_RIGHT;
                     }
                     else if(x==780)
                     {
                         this.direction=KeyEvent.VK_UP;
                     }
                 }
                    //System.out.println("track_x"+track_x);
		switch (this.direction) {
                ///down and left
		case KeyEvent.VK_UP:
			x-=deltaX;
                        y+=deltaY;
			break;
                ///up and right
		case KeyEvent.VK_RIGHT:
			x+=deltaX;
                        y-=deltaY;
			break;
                ///down and right
		case KeyEvent.VK_DOWN:
			x+=deltaX;
                        y+=deltaY;
			break;
                ///up and left
		case KeyEvent.VK_LEFT:
                        x-=deltaX;
			y-=deltaY;
			break;

		default:
			break;
		}
                
	}
	/*public void move(int dir) {
		//this.direction = dir;
		switch (dir) {
		case KeyEvent.VK_UP:
			y-=deltaY;
			break;
		case KeyEvent.VK_RIGHT:
			x+=deltaX;
			break;
		case KeyEvent.VK_DOWN:
			y+=deltaY;
			break;
		case KeyEvent.VK_LEFT:
			x-=deltaX;
			break;

		default:
			break;
		}
	}*/
	
	public boolean  collidedWith (GameObject go) { //�ۼ�����

		if (this.contain(go.x, go.y) || 
			 this.contain(go.x, go.y+go.getHeight()) ||
			 this.contain(go.x+go.getWidth(), go.y) ||
			 this.contain(go.x+go.getWidth(), go.y+go.getHeight())) {
			return true;
		}

		return false;
	}
	
}
