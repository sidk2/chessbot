 import java.awt.Color;
 import java.awt.Component;
 import java.awt.Container;
 import java.awt.Graphics;
 import java.awt.Point;
 import java.awt.event.*;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

 import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

 @SuppressWarnings("serial")
 public class Display extends JPanel implements MouseListener {
     Tile[][] disp;
     Board board;
     private boolean toMove;
     private char currPiece;
     private Tile oldTile;
     private String input = null;
     private boolean isSuccessful = false;
     public Display() {
         disp = new Tile[8][8];
         board = new Board(true);
 //      this.update(board.updateBoard(board.getPiece(new Location(0, 1)), new Pawn(0, 2, true)));
         for (int x = 0; x < 8; x++) {
             for (int y = 0; y < 8; y++) {
                 disp[x][y] = new Tile(board.charBoard()[x][y], x, y);
             }
         }
//         for(int x = 0; x < 8; x++) {
//             for(int y = 0; y < 8; y++) {
//                 System.out.print(disp[x][y].getCurrPiece() + " ");
//             }
//             System.out.println();
//         }
         this.addMouseListener(this);

         toMove = false;
         currPiece = ' ';
         oldTile = null;
     }

     public Board getBoard() {
         return board;
     }

     public String getInput() {
         return input;
     }
     
     public void setInput(String s) {
         input = s;
     }
     
     public boolean getUpdate() {
         return isSuccessful;
     }
     
     public void setUpdate(boolean change) {
         isSuccessful = change;
     }
     
     public void update(Board b) {
         ArrayList<Piece> pieces = new ArrayList<>();
         for(Piece i : b.getBoard()) {
             int xPos = i.getLoc().getXPos();
             int yPos = i.getLoc().getYPos();
             boolean color = i.getColor();
             if(i instanceof Pawn) {
                 pieces.add( new Pawn(xPos, yPos, color) );
             }
             if(i instanceof Knight) {
                 pieces.add( new Knight(xPos, yPos, color) );
             }
             if(i instanceof Bishop) {
                 pieces.add( new Bishop(xPos, yPos, color) );
             }
             if(i instanceof Queen) {
                 pieces.add( new Queen(xPos, yPos, color) );
             }
             if(i instanceof Rook) {
                 pieces.add( new Rook(xPos, yPos, color) );
             }
             if(i instanceof King) {
                 pieces.add( new King(xPos, yPos, color) );
             }
         }
         board = new Board(pieces);
         for (int x = 0; x < 8; x++) {
             for (int y = 0; y < 8; y++) {
                 disp[x][y] = new Tile(b.charBoard()[x][y], x, y);
             }
         }
         repaint();
     }

     @Override
     public void paintComponent(Graphics g) {
         super.paintComponent(g);
         for (int x = 0; x < 8; x++) {
             for (int y = 0; y < 8; y++) {
                 Tile t = disp[x][y];
                 t.paintComponent(g);
 //                    System.out.println(t.getX()+ " "  + t.getY());
             }
         }

     }

     @Override
     public void mouseClicked(MouseEvent e) {
         Tile t = disp[e.getX()/100][e.getY()/100];
 //      if(currPiece == ' ') {
             if(!toMove) {
                 System.out.println("You have Selected : " + t.currPiece + " " + t.currX + " " + t.currY);
                 currPiece = t.currPiece;
                 toMove = true;
 //          }
             oldTile = t;
         } else {
             if(toMove) {
                 System.out.println("move to:" + t.currX + " " + t.currY);
                 System.out.println();
                 toMove = false;
                 Piece p = board.getPiece(new Location(oldTile.currX, oldTile.currY));
                 if(p instanceof Pawn) {
                     //board = board.updateBoard(p, new Pawn(t.currX, t.currY, p.getColor()));
                     input = "Pawn at " + oldTile.currX + " " + oldTile.currY + " to " + t.currX + " " + t.currY;
                     String ans = helper(input);
                     if(ans.equals( "Success" )) {
                         this.update(board.updateBoard(p, new Pawn(t.currX, t.currY, p.getColor())));
                         isSuccessful = true;
                     }
                     else {
                         JOptionPane.showMessageDialog(this, ans);
                     }
                     

                 }
                 else if(p instanceof Rook) {
                     //board = board.updateBoard(p, new Rook(t.currX, t.currY, p.getColor()));
                     input = "Rook at " + oldTile.currX + " " + oldTile.currY + " to " + t.currX + " " + t.currY;
                     String ans = helper(input);
                     if(ans.equals( "Success" )) {
                         this.update(board.updateBoard(p, new Rook(t.currX, t.currY, p.getColor())));
                         isSuccessful = true;
                     }
                     else {
                         JOptionPane.showMessageDialog(this, ans);
                     }
                     
                     
                 }
                 else if(p instanceof Bishop) {
                     //board = board.updateBoard(p, new Bishop(t.currX, t.currY, p.getColor()));
                     input = "Bishop at " + oldTile.currX + " " + oldTile.currY + " to " + t.currX + " " + t.currY;
                     String ans = helper(input);
                     if(ans.equals( "Success" )) {
                         this.update(board.updateBoard(p, new Bishop(t.currX, t.currY, p.getColor())));
                         isSuccessful = true;
                     }
                     else {
                         JOptionPane.showMessageDialog(this, ans);
                     }
                     
                     
                 }
                 else if(p instanceof Knight) {
                     //board = board.updateBoard(p, new Pawn(t.currX, t.currY, p.getColor()));
                     input = "Knight at " + oldTile.currX + " " + oldTile.currY + " to " + t.currX + " " + t.currY;
                     String ans = helper(input);
                     if(ans.equals( "Success" )) {
                         this.update(board.updateBoard(p, new Knight(t.currX, t.currY, p.getColor())));
                         isSuccessful = true;
                     }
                     else {
                         JOptionPane.showMessageDialog(this, ans);
                     }
                     
                     
                 }
                 else if(p instanceof Queen) {
                     //board = board.updateBoard(p, new Pawn(t.currX, t.currY, p.getColor()));
                     input = "Queen at " + oldTile.currX + " " + oldTile.currY + " to " + t.currX + " " + t.currY;
                     String ans = helper(input);
                     if(ans.equals( "Success" )) {
                         this.update(board.updateBoard(p, new Queen(t.currX, t.currY, p.getColor())));
                         isSuccessful = true;
                     }
                     else {
                         JOptionPane.showMessageDialog(this, ans);
                     }
                     
                     
                 }
                 else if(p instanceof King) {
                     //board = board.updateBoard(p, new Pawn(t.currX, t.currY, p.getColor()));
                     input = "King at " + oldTile.currX + " " + oldTile.currY + " to " + t.currX + " " + t.currY;
                     String ans = helper(input);
                     if(ans.equals( "Success" )) {
                         this.update(board.updateBoard(p, new King(t.currX, t.currY, p.getColor())));
                         isSuccessful = true;
                     }
                     else {
                         JOptionPane.showMessageDialog(this, ans);
                     }
                     
                 }
                 //System.out.println(getInput());
             }
             currPiece = ' ';
         }
     }

     @Override
     public void mousePressed(MouseEvent e) {
         // TODO Auto-generated method stub
     }

     @Override
     public void mouseReleased(MouseEvent e) {
         // TODO Auto-generated method stub

     }

     @Override
     public void mouseEntered(MouseEvent e) {
         // TODO Auto-generated method stub

     }

     @Override
     public void mouseExited(MouseEvent e) {
         // TODO Auto-generated method stub

     }
      public static void main(String[] args) throws IOException {
             JFrame w = new JFrame( "ChessBoard - Itr2" );
             w.setBounds( 100, 100, 816, 836 );
             w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
             Display panel = new Display();
             panel.setBackground( Color.WHITE );
             Container c = w.getContentPane();
             c.add( panel );
             w.setResizable( true );
             w.setVisible( true );
      }
      
      public String helper(String str) {
          try {
              
              ArrayList<Piece> save = new ArrayList<Piece>();
              
              for (Piece piece : board.getBoard())
                {
                    if(piece instanceof Knight)
                    {
                        save.add(new Knight(piece.getLoc().getXPos(),piece.getLoc().getYPos(), piece.getColor()));
                        
                    }
                    else if(piece instanceof Pawn)
                    {
                        save.add(new Pawn(piece.getLoc().getXPos(),piece.getLoc().getYPos(), piece.getColor()));
                    }
                    else if(piece instanceof King)
                    {
                        save.add(new King(piece.getLoc().getXPos(),piece.getLoc().getYPos(), piece.getColor()));
                    }
                    else if(piece instanceof Bishop)
                    {
                        save.add(new Bishop(piece.getLoc().getXPos(),piece.getLoc().getYPos(), piece.getColor()));
                    }
                    else if(piece instanceof Rook)
                    {
                        save.add(new Rook(piece.getLoc().getXPos(),piece.getLoc().getYPos(), piece.getColor()));
                    }
                    else if(piece instanceof Queen)
                    {
                        save.add(new Queen(piece.getLoc().getXPos(),piece.getLoc().getYPos(), piece.getColor()));
                    }
               }
              
              //b.printBoard();
              //update(board);
//            System.out.print(
//                    "Please type in your next move in the format:'Piece' at 'current row' 'current col' to 'new row' 'new column'"
//                            + "\n");

              Board saved = new Board(save);
              //System.out.println(b.getPiece( new Location(1, 7) ) instanceof Knight == true);
              //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//              String string = null;
//              String[] s = null;
//            try {
//                 string = reader.readLine();
//                
//                s = string.split("\\s+");
//            } catch (IOException e) {
//                continue;
//            }
              
              //System.out.println(getInput());
              
              //b.printBoard();
              //b.printBoard();
              
//              while(getInput() == null) {
//                  try {
//                      Thread.sleep( 1000 );
//                  }
//                  catch(Exception ex){
//                      
//                  }
//                  System.out.println(getInput());
//              }
//              System.out.println("HIIIIIIIIIIIIIIIIIIII");
//              
//              if(getInput() != null) {
//                  string = getInput();
//                  s = string.split( "\\s+");
//              }
//
//              for(String i : s) {
//                  System.out.println(i + " " );
//              }
//              board.printBoard();
//              
//              try {
//                  Thread.sleep( 1000 );
//              }
//              catch(Exception ex){
//                  
//              }
              
//            if(string.contains("takeback"))
//            {
//                b = takebacks.get(takebacks.size()-3);
//                break;
//            }
              
              String[] s = str.split("\\s+");
              
              if (s[0].contains("Knight")) {
                  if (board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3]))) == null) {
                     return "There is no Knight there! Please try again.";
                  } else if (!board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3]))).getClass()
                          .getCanonicalName().contentEquals("Knight")) {
                      return "There is no Knight there! Please try again.";
                      // System.out.println(b.getPiece(new
                      // Location(Integer.parseInt(s[2]),Integer.parseInt(s[3]))).getClass().getCanonicalName());

                  } else if (board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3])))
                          .getColor()) {
                      return "That's not your Knight! Please try again.";
                  } else {
                      Knight n = (Knight) board
                              .getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3])));
                      boolean valid = false;
                      for (Board b1 : n.findMoves(board, board.getBoard().indexOf(n))) {

                          Board b2 = board;
                          b2 = b2.updateBoard(n,
                                  new Knight(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));

                          if (b1.equals(b2)) {
                              valid = true;

                              break;

                          }

                      }
                      if (valid) {
//                          board = board.updateBoard(new Knight(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false),
//                                  new Knight(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));
                          //b.printBoard();
                          return "Success";
                      } else {
                          board = new Board(save);
                          return "Invalid move! Please try again.";      
                      }
                  }
              } else if (s[0].contains("Pawn")) {
                  if (board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3]))) == null) {
                      return "There is no Pawn there! Please try again.";
                  } else if (!board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3]))).getClass()
                          .getCanonicalName().contentEquals("Pawn")) {
                      return "There is no Pawn there! Please try again.";
                      // System.out.println(b.getPiece(new
                      // Location(Integer.parseInt(s[2]),Integer.parseInt(s[3]))).getClass().getCanonicalName());

                  } else if (board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3])))
                          .getColor()) {
                      return "That's not your Pawn! Please try again.";
                  } else {
                      Pawn n = (Pawn) board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3])));
                      boolean valid = false;
                      for (Board b1 : n.findMoves(board, board.getBoard().indexOf(n))) {
                          Board b2 = board;
                          b2 = b2.updateBoard(n, new Pawn(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));
                          
                          if (b1.equals(b2)) {
                              
                              valid = true;
                          }

                      }
                      if (valid) {
//                          board = board.updateBoard(new Pawn(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false),
//                                  new Pawn(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));
                          //b.printBoard();
                          return "Success";
                      } 
                      else 
                      {
                          board = new Board(save);
                          return "Invalid move! Please try again.";
                      }
                  }
              } else if (s[0].contains("Rook")) {
                  if (board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3]))) == null) {
                      return "There is no Rook there! Please try again.";
                  } else if (!board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3]))).getClass()
                          .getCanonicalName().contentEquals("Rook")) {
                      return "There is no Rook there! Please try again.";
                      // System.out.println(b.getPiece(new
                      // Location(Integer.parseInt(s[2]),Integer.parseInt(s[3]))).getClass().getCanonicalName());

                  } else if (board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3])))
                          .getColor()) {
                      return "That's not your Rook! Please try again.";
                  } else {
                      Rook n = (Rook) board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3])));
                      boolean valid = false;
                      for (Board b1 : n.findMoves(board, board.getBoard().indexOf(n))) {

                          Board b2 = board;
                          b2 = b2.updateBoard(n, new Rook(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));

                          if (b1.equals(b2)) {
                              valid = true;

                              break;

                          }

                      }
                      if (valid) {
//                          board = board.updateBoard(new Rook(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false),
//                                  new Rook(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));
                          //b.printBoard();
                          return "Success";
                      } else {
                          board = new Board(save);
                          return "Invalid move! Please try again.";
                          }
                  }
              } else if (s[0].contains("Queen")) {
                  if (board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3]))) == null) {
                      return "There is no Queen there! Please try again.";
                  } else if (!board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3]))).getClass()
                          .getCanonicalName().contentEquals("Queen")) {
                      return "There is no Queen there! Please try again.";
                      // System.out.println(b.getPiece(new
                      // Location(Integer.parseInt(s[2]),Integer.parseInt(s[3]))).getClass().getCanonicalName());

                  } else if (board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3])))
                          .getColor()) {
                      return "That's not your Queen! Please try again.";
                  } else {
                      Queen n = (Queen) board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3])));
                      boolean valid = false;
                      for (Board b1 : n.findMoves(board, board.getBoard().indexOf(n))) {

                          Board b2 = board;
                          b2 = b2.updateBoard(n,
                                  new Queen(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));

                          if (b1.equals(b2)) {
                              valid = true;

                              break;

                          }

                      }
                      if (valid) {
//                          board = board.updateBoard(new Queen(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false),
//                                  new Queen(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));
                          //b.printBoard();
                          return "Success";
                      } else {
                          board = new Board(save);
                          return "Invalid move! Please try again.";
                          }
                  }
              } else if (s[0].contains("Bishop")) {
                  if (board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3]))) == null) {
                      return "There is no Bishop there! Please try again.";
                  } else if (!board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3]))).getClass()
                          .getCanonicalName().contentEquals("Bishop")) {
                      return "There is no Bishop there! Please try again.";
                      // System.out.println(b.getPiece(new
                      // Location(Integer.parseInt(s[2]),Integer.parseInt(s[3]))).getClass().getCanonicalName());

                  } else if (board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3])))
                          .getColor()) {
                      return "That's not your Bishop! Please try again.";
                  } else {
                      Bishop n = (Bishop) board
                              .getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3])));
                      boolean valid = false;
                      for (Board b1 : n.findMoves(board, board.getBoard().indexOf(n))) {

                          Board b2 = board;
                          b2 = b2.updateBoard(n,
                                  new Bishop(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));

                          if (b1.equals(b2)) {
                              valid = true;

                              break;

                          }

                      }
                      if (valid) {
//                          board = board.updateBoard(new Bishop(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false),
//                                  new Bishop(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));
                          //b.printBoard();
                          return "Success";
                      } else {
                          board = new Board(save);
                          return "Invalid move! Please try again.";
                      }
                  }
              } else if (s[0].contains("King")) {
                  if (board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3]))) == null) {
                      return "There is no King there! Please try again.";
                  } else if (!board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3]))).getClass()
                          .getCanonicalName().contentEquals("King")) {
                      return "There is no King there! Please try again.";
                      // System.out.println(b.getPiece(new
                      // Location(Integer.parseInt(s[2]),Integer.parseInt(s[3]))).getClass().getCanonicalName());

                  } else if (board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3])))
                          .getColor()) {
                      return "That's not your King! Please try again.";
                  } else {
                      King n = (King) board.getPiece(new Location(Integer.parseInt(s[2]), Integer.parseInt(s[3])));
                      boolean valid = false;
                      for (Board b1 : n.findMoves(board, board.getBoard().indexOf(n))) {

                          Board b2 = board;
                          b2 = b2.updateBoard(n, new King(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));

                          if (b1.equals(b2)) {
                              valid = true;

                              break;

                          }

                      }
                      if (valid) {
//                          board = board.updateBoard(new King(Integer.parseInt(s[2]), Integer.parseInt(s[3]), false),
//                                  new King(Integer.parseInt(s[5]), Integer.parseInt(s[6]), false));
                          //b.printBoard();
                          return "Success";
                      } else {
                          board = new Board(save);
                          return "Invalid move! Please try again.";
                      }
                  }
              }
          } catch (NumberFormatException e) {
              System.out.println("Wrong format! Try Again!");
          }
        return "Success";
      }
      

 //      System.out.println("Hi");
 //
 //      Tile t = (Tile) this.getComponentAt(new Point(e.getX(), e.getY()));
 //      System.out.println(t.getX() + " " + t.getY());
 //
 //      if(t.currPiece != ' ' && !toMove) {
 //          System.out.println("You have Selected : " + t.currPiece);
 //          currPiece = t.currPiece;
 //          toMove = true;
 //          oldTile = t;
 //      } else {
 //          oldTile.currPiece = ' ';
 //          t.currPiece = currPiece;
 //          toMove = false;
 //      }
 //      repaint();

 }