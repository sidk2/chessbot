import java.awt.Color;
import java.awt.Container;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Popup;


/**
 * The main method to run the program, the user can choose either the GUI mode
 * or the command line chess textMode
 * 
 * @author Sidharth Kannan
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: ChessBot
 *
 * @author Sources: Shreyas Kaasyap, Sidharth Kannan, Leo Yang
 */
public class Test
{

    /**
     * A main method to run the program, the user can choose either the
     * textMode(without a GUI) or an interactive GUI mode, both fully functional
     * 
     * @param args
     *            the required parameter for main
     */
    public static void main( String[] args )
    {
        System.out.print(
            "              __________   _____       _____   ____________       ___________         ___________  "
                + '\n'
                + "             /          \\ |     |     |     | |            |     /            \\     /             \\"
                + '\n'
                + "            |    _______/ |     |     |     | |    ________/    |    _________/    |    __________/"
                + '\n'
                + "            |   |         |     |     |     | |   |              \\   \\              \\   \\         "
                + '\n'
                + "            |   |         |     |_____|     | |   |_______        \\   \\              \\   \\        "
                + '\n'
                + "            |   |         |                 | |           |        \\   \\              \\   \\      "
                + '\n'
                + "            |   |         |      _____      | |    _______|         \\   \\              \\   \\      "
                + '\n'
                + "            |   |         |     |     |     | |   |                  \\   \\              \\   \\     "
                + '\n'
                + "            |   |_______  |     |     |     | |   |_______     _______\\   \\       _______\\   \\    "
                + '\n'
                + "            |           \\ |     |     |     | |           \\   /            |     /            | "
                + '\n'
                + "             \\__________/ |_____|     |_____| |____________|  \\____________/     \\____________/    "
                + '\n' );

        BufferedReader r = new BufferedReader( new InputStreamReader( System.in ) );
        String str = null;
        try
        {
            System.out.println(
                "Would you like to use the console or GUI? Type 'console' for console and 'GUI' for GUI." );
            str = r.readLine();
        }
        catch ( IOException e )
        {
        }
        if ( str.contains( "GUI" ) )
        {
            guiMode();
        }
        else
        {
            textMode();
        }
        return;
    }


    /**
     * A functional chess game without a GUI, resembles command line chess
     */
    public static void textMode()
    {
        Stack<Board> takebacks = new Stack<Board>();
        int level = 4;
        while ( true )
        {
            BufferedReader r = new BufferedReader( new InputStreamReader( System.in ) );
            String str = null;
            try
            {
                System.out.println( "Pick a level from 1 - 8" );
                str = r.readLine();
                level = Integer.parseInt( str );
                break;
            }
            catch ( IOException e )
            {
                continue;
            }
        }
        Board b = new Board();

        takebacks.push( b );
        AI ai = new AI( b, level );
        while ( true )
        {
            b = ai.minimax( b, level, true );
            b.printBoard();
            takebacks.push( b );
            if ( ai.isCheckMate() )
            {
                b.printBoard();
                if ( ai.isWhite() )
                {
                    System.out.println( "Checkmate, white won!" );
                }
                return;
            }
            ArrayList<Piece> save = new ArrayList<Piece>();

            if ( b == null )
            {
                System.out.println( "Checkmate, black won!" );
                return;
            }

            for ( Piece piece : b.getBoard() )
            {
                if ( piece instanceof Knight )
                {
                    save.add( new Knight( piece.getLoc().getXPos(),
                        piece.getLoc().getYPos(),
                        piece.getColor() ) );

                }
                else if ( piece instanceof Pawn )
                {
                    save.add( new Pawn( piece.getLoc().getXPos(),
                        piece.getLoc().getYPos(),
                        piece.getColor() ) );
                }
                else if ( piece instanceof King )
                {
                    save.add( new King( piece.getLoc().getXPos(),
                        piece.getLoc().getYPos(),
                        piece.getColor() ) );
                }
                else if ( piece instanceof Bishop )
                {
                    save.add( new Bishop( piece.getLoc().getXPos(),
                        piece.getLoc().getYPos(),
                        piece.getColor() ) );
                }
                else if ( piece instanceof Rook )
                {
                    save.add( new Rook( piece.getLoc().getXPos(),
                        piece.getLoc().getYPos(),
                        piece.getColor() ) );
                }
                else if ( piece instanceof Queen )
                {
                    save.add( new Queen( piece.getLoc().getXPos(),
                        piece.getLoc().getYPos(),
                        piece.getColor() ) );
                }
            }

            while ( true )
            {
                try
                {
                    System.out.print(
                        "Please type in your next move in the format:'Piece' at 'current row' 'current col' to 'new row' 'new column'"
                            + "\n" );

                    Board saved = new Board( save );

                    BufferedReader reader = new BufferedReader(
                        new InputStreamReader( System.in ) );

                    String string = null;
                    String[] s = null;
                    try
                    {

                        string = reader.readLine();

                        s = string.split( "\\s+" );
                    }
                    catch ( IOException e )
                    {
                        continue;
                    }

                    if ( string.contains( "takeback" ) )
                    {
                        b = takebacks.pop();
                        break;
                    }

                    if ( s[0].contains( "Knight" ) )
                    {
                        if ( b.getPiece( new Location( Integer.parseInt( s[2] ),
                            Integer.parseInt( s[3] ) ) ) == null )
                        {
                            System.out.println( "There is no Knight there! Please try again." );
                        }
                        else if ( !b
                            .getPiece(
                                new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                            .getClass()
                            .getCanonicalName()
                            .contentEquals( "Knight" ) )
                        {
                            System.out.println( "There is no Knight there! Please try again." );
                            System.out
                                .println( b
                                    .getPiece( new Location( Integer.parseInt( s[2] ),
                                        Integer.parseInt( s[3] ) ) )
                                    .getClass()
                                    .getCanonicalName() );

                        }
                        else if ( b
                            .getPiece(
                                new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                            .getColor() )
                        {
                            System.out.println( "That's not your Knight! Please try again." );
                        }
                        else
                        {
                            Knight n = (Knight)b.getPiece( new Location( Integer.parseInt( s[2] ),
                                Integer.parseInt( s[3] ) ) );
                            boolean valid = false;
                            for ( Board b1 : n.findMoves( b, b.getBoard().indexOf( n ) ) )
                            {

                                Board b2 = b;
                                b2 = b2.updateBoard( n,
                                    new Knight( Integer.parseInt( s[5] ),
                                        Integer.parseInt( s[6] ),
                                        false ) );

                                if ( b1.equals( b2 ) )
                                {
                                    valid = true;

                                    break;

                                }

                            }
                            if ( valid )
                            {
                                b = b.updateBoard(
                                    new Knight( Integer.parseInt( s[2] ),
                                        Integer.parseInt( s[3] ),
                                        false ),
                                    new Knight( Integer.parseInt( s[5] ),
                                        Integer.parseInt( s[6] ),
                                        false ) );
                                b.printBoard();
                                break;
                            }
                            else
                            {
                                System.out.println( "Invalid move! Please try again." );
                                b = new Board( save );
                            }
                        }
                    }
                    else if ( s[0].contains( "Pawn" ) )
                    {
                        if ( b.getPiece( new Location( Integer.parseInt( s[2] ),
                            Integer.parseInt( s[3] ) ) ) == null )
                        {
                            System.out.println( "There is no Pawn there! Please try again." );
                        }
                        else if ( !b
                            .getPiece(
                                new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                            .getClass()
                            .getCanonicalName()
                            .contentEquals( "Pawn" ) )
                        {
                            System.out.println( "There is no Pawn there! Please try again." );
                            System.out
                                .println( b
                                    .getPiece( new Location( Integer.parseInt( s[2] ),
                                        Integer.parseInt( s[3] ) ) )
                                    .getClass()
                                    .getCanonicalName() );

                        }
                        else if ( b
                            .getPiece(
                                new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                            .getColor() )
                        {
                            System.out.println( "That's not your Pawn! Please try again." );
                        }
                        else
                        {
                            Pawn n = (Pawn)b.getPiece( new Location( Integer.parseInt( s[2] ),
                                Integer.parseInt( s[3] ) ) );
                            boolean valid = false;
                            for ( Board b1 : n.findMoves( b, b.getBoard().indexOf( n ) ) )
                            {
                                Board b2 = b;
                                b2 = b2.updateBoard( n,
                                    new Pawn( Integer.parseInt( s[5] ),
                                        Integer.parseInt( s[6] ),
                                        false ) );

                                if ( b1.equals( b2 ) )
                                {

                                    valid = true;
                                }
                                else
                                {

                                }

                            }
                            if ( valid )
                            {
                                b = b.updateBoard(
                                    new Pawn( Integer.parseInt( s[2] ),
                                        Integer.parseInt( s[3] ),
                                        false ),
                                    new Pawn( Integer.parseInt( s[5] ),
                                        Integer.parseInt( s[6] ),
                                        false ) );
                                b.printBoard();
                                break;
                            }
                            else
                            {
                                System.out.println( "Invalid move! Please try again." );
                                b = new Board( save );
                            }
                        }
                    }
                    else if ( s[0].contains( "Rook" ) )
                    {
                        if ( b.getPiece( new Location( Integer.parseInt( s[2] ),
                            Integer.parseInt( s[3] ) ) ) == null )
                        {
                            System.out.println( "There is no Rook there! Please try again." );
                        }
                        else if ( !b
                            .getPiece(
                                new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                            .getClass()
                            .getCanonicalName()
                            .contentEquals( "Rook" ) )
                        {
                            System.out.println( "There is no Rook there! Please try again." );
                            System.out
                                .println( b
                                    .getPiece( new Location( Integer.parseInt( s[2] ),
                                        Integer.parseInt( s[3] ) ) )
                                    .getClass()
                                    .getCanonicalName() );

                        }
                        else if ( b
                            .getPiece(
                                new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                            .getColor() )
                        {
                            System.out.println( "That's not your Rook! Please try again." );
                        }
                        else
                        {
                            Rook n = (Rook)b.getPiece( new Location( Integer.parseInt( s[2] ),
                                Integer.parseInt( s[3] ) ) );
                            boolean valid = false;
                            for ( Board b1 : n.findMoves( b, b.getBoard().indexOf( n ) ) )
                            {

                                Board b2 = b;
                                b2 = b2.updateBoard( n,
                                    new Rook( Integer.parseInt( s[5] ),
                                        Integer.parseInt( s[6] ),
                                        false ) );

                                if ( b1.equals( b2 ) )
                                {
                                    valid = true;

                                    break;

                                }

                            }
                            if ( valid )
                            {
                                b = b.updateBoard(
                                    new Rook( Integer.parseInt( s[2] ),
                                        Integer.parseInt( s[3] ),
                                        false ),
                                    new Rook( Integer.parseInt( s[5] ),
                                        Integer.parseInt( s[6] ),
                                        false ) );
                                b.printBoard();
                                break;
                            }
                            else
                            {
                                System.out.println( "Invalid move! Please try again." );
                                b = new Board( save );
                            }
                        }
                    }
                    else if ( s[0].contains( "Queen" ) )
                    {
                        if ( b.getPiece( new Location( Integer.parseInt( s[2] ),
                            Integer.parseInt( s[3] ) ) ) == null )
                        {
                            System.out.println( "There is no Queen there! Please try again." );
                        }
                        else if ( !b
                            .getPiece(
                                new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                            .getClass()
                            .getCanonicalName()
                            .contentEquals( "Queen" ) )
                        {
                            System.out.println( "There is no Queen there! Please try again." );
                            System.out
                                .println( b
                                    .getPiece( new Location( Integer.parseInt( s[2] ),
                                        Integer.parseInt( s[3] ) ) )
                                    .getClass()
                                    .getCanonicalName() );

                        }
                        else if ( b
                            .getPiece(
                                new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                            .getColor() )
                        {
                            System.out.println( "That's not your Queen! Please try again." );
                        }
                        else
                        {
                            Queen n = (Queen)b.getPiece( new Location( Integer.parseInt( s[2] ),
                                Integer.parseInt( s[3] ) ) );
                            boolean valid = false;
                            for ( Board b1 : n.findMoves( b, b.getBoard().indexOf( n ) ) )
                            {

                                Board b2 = b;
                                b2 = b2.updateBoard( n,
                                    new Queen( Integer.parseInt( s[5] ),
                                        Integer.parseInt( s[6] ),
                                        false ) );

                                if ( b1.equals( b2 ) )
                                {
                                    valid = true;

                                    break;

                                }

                            }
                            if ( valid )
                            {
                                b = b.updateBoard(
                                    new Queen( Integer.parseInt( s[2] ),
                                        Integer.parseInt( s[3] ),
                                        false ),
                                    new Queen( Integer.parseInt( s[5] ),
                                        Integer.parseInt( s[6] ),
                                        false ) );
                                b.printBoard();
                                break;
                            }
                            else
                            {
                                System.out.println( "Invalid move! Please try again." );
                                b = new Board( save );
                            }
                        }
                    }
                    else if ( s[0].contains( "Bishop" ) )
                    {
                        if ( b.getPiece( new Location( Integer.parseInt( s[2] ),
                            Integer.parseInt( s[3] ) ) ) == null )
                        {
                            System.out.println( "There is no Bishop there! Please try again." );
                        }
                        else if ( !b
                            .getPiece(
                                new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                            .getClass()
                            .getCanonicalName()
                            .contentEquals( "Bishop" ) )
                        {
                            System.out.println( "There is no Bishop there! Please try again." );
                            System.out
                                .println( b
                                    .getPiece( new Location( Integer.parseInt( s[2] ),
                                        Integer.parseInt( s[3] ) ) )
                                    .getClass()
                                    .getCanonicalName() );

                        }
                        else if ( b
                            .getPiece(
                                new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                            .getColor() )
                        {
                            System.out.println( "That's not your Bishop! Please try again." );
                        }
                        else
                        {
                            Bishop n = (Bishop)b.getPiece( new Location( Integer.parseInt( s[2] ),
                                Integer.parseInt( s[3] ) ) );
                            boolean valid = false;
                            for ( Board b1 : n.findMoves( b, b.getBoard().indexOf( n ) ) )
                            {

                                Board b2 = b;
                                b2 = b2.updateBoard( n,
                                    new Bishop( Integer.parseInt( s[5] ),
                                        Integer.parseInt( s[6] ),
                                        false ) );

                                if ( b1.equals( b2 ) )
                                {
                                    valid = true;

                                    break;

                                }

                            }
                            if ( valid )
                            {
                                b = b.updateBoard(
                                    new Bishop( Integer.parseInt( s[2] ),
                                        Integer.parseInt( s[3] ),
                                        false ),
                                    new Bishop( Integer.parseInt( s[5] ),
                                        Integer.parseInt( s[6] ),
                                        false ) );
                                b.printBoard();
                                break;
                            }
                            else
                            {
                                System.out.println( "Invalid move! Please try again." );
                                b = new Board( save );
                            }
                        }
                    }
                    else if ( s[0].contains( "King" ) )
                    {
                        if ( b.getPiece( new Location( Integer.parseInt( s[2] ),
                            Integer.parseInt( s[3] ) ) ) == null )
                        {
                            System.out.println( "There is no King there! Please try again." );
                        }
                        else if ( !b
                            .getPiece(
                                new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                            .getClass()
                            .getCanonicalName()
                            .contentEquals( "King" ) )
                        {
                            System.out.println( "There is no King there! Please try again." );
                            System.out
                                .println( b
                                    .getPiece( new Location( Integer.parseInt( s[2] ),
                                        Integer.parseInt( s[3] ) ) )
                                    .getClass()
                                    .getCanonicalName() );

                        }
                        else if ( b
                            .getPiece(
                                new Location( Integer.parseInt( s[2] ), Integer.parseInt( s[3] ) ) )
                            .getColor() )
                        {
                            System.out.println( "That's not your King! Please try again." );
                        }
                        else
                        {
                            King n = (King)b.getPiece( new Location( Integer.parseInt( s[2] ),
                                Integer.parseInt( s[3] ) ) );
                            boolean valid = false;
                            for ( Board b1 : n.findMoves( b, b.getBoard().indexOf( n ) ) )
                            {

                                Board b2 = b;
                                b2 = b2.updateBoard( n,
                                    new King( Integer.parseInt( s[5] ),
                                        Integer.parseInt( s[6] ),
                                        false ) );

                                if ( b1.equals( b2 ) )
                                {
                                    valid = true;

                                    break;

                                }

                            }
                            if ( valid )
                            {
                                b = b.updateBoard(
                                    new King( Integer.parseInt( s[2] ),
                                        Integer.parseInt( s[3] ),
                                        false ),
                                    new King( Integer.parseInt( s[5] ),
                                        Integer.parseInt( s[6] ),
                                        false ) );
                                b.printBoard();
                                break;
                            }
                            else
                            {
                                System.out.println( "Invalid move! Please try again." );
                                b = new Board( save );
                            }
                        }
                    }
                }
                catch ( NumberFormatException e )
                {
                    System.out.println( "Wrong format! Try Again!" );
                }
            }
            System.out.println( "White's move!" );
        }
    }


    /**
     * A fully functional GUI for the chess board
     */
    public static void guiMode()
    {
        Stack<Board> takebacks = new Stack<>();
        int level = 4;
        while ( true )
        {
            BufferedReader r = new BufferedReader( new InputStreamReader( System.in ) );
            String str = null;
            try
            {
                System.out.println( "Pick a level from 1 - 4" );
                str = r.readLine();
                level = Integer.parseInt( str );
                break;
            }
            catch ( IOException e )
            {
                continue;
            }
        }
        Board b = new Board();
        JFrame w = new JFrame( "ChessBoard" );
        w.setBounds( 100, 100, 816, 836 );
        w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        Display panel = new Display();
        JButton button = new JButton( "Takeback" );
        button.setLocation( 458, 100 );
        panel.add( button );
        panel.setBackground( Color.WHITE );
        Container c = w.getContentPane();
        c.add( panel );
        w.setResizable( true );
        w.setVisible( true );

        takebacks.push( new Board( b.copyBoard() ) );
        AI ai = new AI( b, level );
        while ( true )
        {
            b = ai.minimax( b, level, true );
            // System.out.println(takebacks.size());
            if ( b != null )
            {
                takebacks.push( new Board( b.copyBoard() ) );
            }
            if ( ai.isCheckMate() )
            {
                panel.update( b );
                if ( ai.isWhite() )
                {
                    JOptionPane.showMessageDialog( panel, "Checkmate! White Wins!" );

                    w.setVisible( false );
                    w.dispose();
                }
                return;
            }

            if ( b == null )
            {
                JOptionPane.showMessageDialog( panel, "Checkmate! Black Wins!" );
                w.setVisible( false );
                w.dispose();
                return;
            }

            panel.update( b );
            while ( !panel.getUpdate() )
            {
                if ( button.getModel().isPressed() )
                {
                    // System.out.println("->->->->_> " + takebacks.size());

                    takebacks.pop();
                    takebacks.pop();
                    takebacks.pop();
                    // System.out.println(takebacks.size());
                    // System.out.println(takebacks.size());
                    Board temp = takebacks.pop();
                    b = new Board( temp.copyBoard() );
                    panel.setBoard( b );
                    button.getModel().setPressed( false );
                    panel.update( b );
                    continue;
                }
                try
                {
                    Thread.sleep( 10 );
                }
                catch ( Exception ex )
                {

                }
            }
            panel.setUpdate( false );
            b = panel.getBoard();
            takebacks.push( new Board( b.copyBoard() ) );
        }
    }
}
