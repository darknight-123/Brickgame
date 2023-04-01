
import static TankWar.setStartWarGame;
import static TankWar.setStartWarGame;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class WarField extends GameObject {
    MoveableObject f;
    ArrayList<Tank> enemyTanks = new ArrayList<Tank>();
    ArrayList<Missile> enemyMissiles = new ArrayList<Missile>();
    Tank ourTank;
    ArrayList<Missile> ourMissiles = new ArrayList<Missile>();
    ArrayList<Missile> deadMissiles = new ArrayList<Missile>();
    int numberOfEnemyTanks = 0;
    Missile ball=new Missile( 320, 545, 15, 15, Color.YELLOW);
    public int getNumberOfEnemyTanks() {
        return numberOfEnemyTanks;
    }

    public void setNumberOfEnemyTanks(int numberOfEnemyTanks) {
        this.numberOfEnemyTanks = numberOfEnemyTanks;
    }

    public void restart() {
        //reset our tank position if necessary
        //this.ourTank.setX(50);
        //this.ourTank.setY(250);
        //this, 590-40*h, i * 30+50, 40, 30,Color.WHITE
       // this.enemyTanks.clear();
       
       this.enemyTanks.clear();
        for (int h = 0; h < 12; h++) {
            for (int i = 0; i < 4; i++) {
                this.enemyTanks.add(new EnemyTank(this, 590 - 45 * h, i * 35 + 50, 40, 30,
                        Color.WHITE));
            } 
        }
    }

    public ArrayList<Tank> getEnemyTanks() {
        return enemyTanks;
    }

    public void setEnemyTanks(ArrayList<Tank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public ArrayList<Missile> getEnemyMissiles() {
        return enemyMissiles;
    }

    public void setEnemyMissiles(ArrayList<Missile> enemyMissiles) {
        this.enemyMissiles = enemyMissiles;
    }

    /*public WarField() {
		this.setBackground(Color.GREEN);
		// TODO Auto-generated constructor stub
		ourTank = new Tank(this, 50, 350, 30, 30, Color.RED);
		for (int i = 0; i < 10; i++)
			this.enemyTanks.add(new Tank(this, 650, 30 + i * 50, 30, 30,
					Color.BLUE));
	}*/
    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public WarField(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        // TODO Auto-generated constructor stub
        //this.setBackground(Color.GREEN);
        ourTank = new Tank(this, 300, 600, 60, 5, Color.GREEN);
        
        for (int h = 0; h < 12; h++) {
            for (int i = 0; i < 4; i++) {
                this.enemyTanks.add(new EnemyTank(this, 590 - 45 * h, i * 35 + 50, 40, 30,
                        Color.WHITE));
            } 
        }
        
    }
    
    @Override
    void draw(Graphics g) {
        ourTank.draw(g);
        ball.draw(g);
        for (Missile m : ourMissiles) {
            m.draw(g);
        }
        numberOfEnemyTanks = 0;
        for (Tank enemyTank : this.enemyTanks) {
            if (enemyTank.isLive()) {
                enemyTank.draw(g);
                numberOfEnemyTanks++;
            }
        }
        //System.out.println("loss="+MoveableObject.ball_y);
        if (MoveableObject.ball_y==605) {
                System.out.println("game over");
                g.drawString("Game Over", 400, 300);
                TankWar.setStartWarGame(false);
               
            }
        //g.drawString("Number of Enemy Tanks left:"+numberOfEnemyTanks, 600, 20);
        for (Missile enemyMissile : this.enemyMissiles) {
            enemyMissile.draw(g);
            if (MoveableObject.ball_y==605) {
                System.out.println("game over");
                g.drawString("Game Over", 400, 300);
                TankWar.setStartWarGame(false);
            }
        }
     
       // for (Missile ball : ourMissiles) {
            for (Tank enemyTank : this.enemyTanks) {
                if (enemyTank.isLive()) {
                    ball.hitTank(enemyTank);
                }
            }
       // }
        
        clearDeadMissiles();

    }
    
    public ArrayList<Missile> getOurMissiles() {
        return ourMissiles;
    }

    public void setOurMissiles(ArrayList<Missile> ourMissiles) {
        this.ourMissiles = ourMissiles;
    }

    private void clearDeadMissiles() {
        // clear dead missiles
        for (Missile m : ourMissiles) {
            if (!m.isLive()) {
                this.deadMissiles.add(m);
            }
        }
        this.ourMissiles.removeAll(this.deadMissiles);
        this.deadMissiles.clear();
        for (Missile m : this.enemyMissiles) {
            if (!m.isLive()) {
                this.deadMissiles.add(m);
            }
        }
        this.enemyMissiles.removeAll(this.deadMissiles);
    }

    public Tank getOurTank() {
        return ourTank;
    }

    public void setOurTank(Tank ourTank) {
        this.ourTank = ourTank;
    }

    public void processKeyPressed(KeyEvent e) {
        ourTank.processKeyPressed(e);

    }

}
