/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caro.common;


import ConnectDB.DataFunc;
import servercazo.ClientHandler;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kietdang
 */
public class Room implements Serializable{
    int id = 0;
    public ClientHandler client1 = null;
    public ClientHandler client2 = null;
    public ArrayList<ClientHandler> lstClientView = null;

    public CPiece[][] pieceses;
    
    DataFunc dataFunc;
    
    static final int NOT5        = 0;
    static final int OK5        = 1;
    static final int ILL_CHOUREN= 2;
    static final int ILL_33        = 3;
    static final int ILL_44        = 4;
    static final int ILL_NOT    = 5;

    int Dx[];
    int Dy[];

    static final int D_UP        = 0;
    static final int D_UPRIGHT    = 1;
    static final int D_RIGHT    = 2;
    static final int D_DOWNRIGHT= 3;
    static final int D_DOWN        = 4;
    static final int D_DOWNLEFT    = 5;
    static final int D_LEFT        = 6;
    static final int D_UPLEFT    = 7;
    
    static final int BOARDSIZE = 25;
    
    void NewGame()
    {
        pieceses = new CPiece[25][25];
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 25; j++) {
                pieceses[i][j] = new CPiece();
            }
        }
    }
    
    public Room(int _id) {
        id = _id;
        lstClientView = new ArrayList<ClientHandler>();
        
        NewGame();
        
        dataFunc = new DataFunc();
        
        Dx = new int[8];
        Dy = new int[8];

        Dx[0] =  0;  Dy[0] = -1;
        Dx[1] =  1;  Dy[1] = -1;
        Dx[2] =  1;  Dy[2] =  0;
        Dx[3] =  1;  Dy[3] =  1;
        Dx[4] =  0;  Dy[4] =  1;
        Dx[5] = -1;  Dy[5] =  1;
        Dx[6] = -1;  Dy[6] =  0;
        Dx[7] = -1;  Dy[7] = -1;
    }
    
    
    
    
    
    
    public int GetSequence(int color,int x,int y,int direction) {
        int num = 0;
        int dx = Dx[direction];
        int dy = Dy[direction];

        Boolean Space = false;

        while(pieceses[x][y].State == color) {
            num++;
            x += dx; y += dy;
            if( x < 0 || x >= BOARDSIZE || y < 0 || y >= BOARDSIZE ) break;
            if(pieceses[x][y].State == CPiece.EMPTY) {
                Space = true;
                break;
            }
        }
        return num;
    }
    
    
    public int Find5Block(int color,int x,int y) {

        int max,a;

        max = GetSequence(color,x,y,D_UP) + GetSequence(color,x,y,D_DOWN) - 1 ;
        a =GetSequence(color,x,y,D_LEFT) + GetSequence(color,x,y,D_RIGHT) - 1 ;
        max = Math.max(max,a);
        a = GetSequence(color,x,y,D_UPLEFT) + GetSequence(color,x,y,D_DOWNRIGHT) -1 ;
        max = Math.max(max,a);
        a = GetSequence(color,x,y,D_UPRIGHT) + GetSequence(color,x,y,D_DOWNLEFT) - 1 ;
        max = Math.max(max,a);

        if( max >= 5)
            return OK5;

        return NOT5;
    }

    public void clientWinLose(ClientHandler client, Boolean isWin)
    {
        int sum = client.user.getScore();
        if (isWin)
        {
            sum+=100;
            client.user.setWin(client.user.getWin()+1);
        }
        else
        {
            sum-=100;
            client.user.setLose(client.user.getLose()+1);
        }
        client.user.setScore(sum);
        try {
            dataFunc.updateUser(client.user);
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int put(ClientHandler client, GPos gPos) throws IOException
    {
        if (client == client1)
        {
            pieceses[gPos.x][gPos.y].State = CPiece.BLACK;
            if (Find5Block(CPiece.BLACK, gPos.x, gPos.y)==OK5)
            {
                System.out.printf("Black win");
                clientWinLose(client1, true);
                client1.SendMessage(35, "win");
                clientWinLose(client2, false);
                client2.SendMessage(35, "lose");
            }
            else
            {
                client2.SendMessage(31, null);
                client1.SendMessage(36, null);
            }
        }
        else
        {
            pieceses[gPos.x][gPos.y].State = CPiece.WHITE;
            if (Find5Block(CPiece.WHITE, gPos.x, gPos.y)==OK5)
            {
                System.out.printf("WHITE win");
                clientWinLose(client2, true);
                client2.SendMessage(35, "win");
                clientWinLose(client1, false);
                client1.SendMessage(35, "lose");
            }
            else
            {
                client1.SendMessage(31, null);
                client2.SendMessage(36, null);
            }
        }
        
        client1.SendMessage(30, pieceses);
        client2.SendMessage(30, pieceses);
        
        return 1;
    }
    
    public int countAvailable()
    {
        int n = 2;
        if (client1 != null)
            n--;
        if (client2 != null)
            n--;
        return n;
    }
    
    public Boolean add(ClientHandler client) throws IOException
    {
        if (client1==null)
        {
            client1 = client;
            return true;
        }
        if (client2==null)
        {
            client2 = client;
            NewGame();
            client1.SendMessage(31, null);
            client2.SendMessage(36, null);
            return true;
        }
        return false;
    }
    
    public void clientExit(ClientHandler clientHandler) throws IOException
    {
        if (countAvailable()==1)
        {
            if (client1!=null)
                client1.room = null;
            client1 = null;
            if (client2!=null)
                client2.room = null;
            client2 = null;
        }
        else if (countAvailable()==0)
        {
            if (client1==clientHandler)
            {
                clientWinLose(client2, true);
                client2.SendMessage(35, "win");
            }
            else
            {
                clientWinLose(client1, true);
                client1.SendMessage(35, "win");
            }
            if (client1!=null)
                client1.room = null;
            client1 = null;
            if (client2!=null)
                client2.room = null;
            client2 = null;
        }
        
        
    }
    
    @Override
    public String toString()
    {
        int n = 2;
        if (client1 != null)
            n--;
        if (client2 != null)
            n--;
        return "Room " + id + ": " + n + " available";
    }
}
