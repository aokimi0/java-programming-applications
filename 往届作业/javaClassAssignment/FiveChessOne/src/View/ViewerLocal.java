package View;

import Bean.*;

import javax.swing.*;
import java.awt.*;

public class ViewerLocal extends JFrame implements View {
    private static ViewerLocal viewer;  // 单例模式中的唯一实例
    private static GameInfo gameInfo;   // 存储游戏信息

    // 获取 ViewerLocal 实例（单例模式）
    public static ViewerLocal getViewer() {
        if (viewer == null) {
            viewer = new ViewerLocal();
            System.out.println("viewer");
        }
        return viewer;
    }

    // 设置游戏信息
    public static void setGameInfo(GameInfo gameInfo) {
        ViewerLocal.gameInfo = gameInfo;
    }

    private String key; // 游戏实例的唯一密钥

    // 设置密钥
    public void setKey(String key) {
        this.key = key;
    }

    // 实现 View 接口中的 setChess 方法，设置棋盘上的棋子
    @Override
    public void setChess(Chess chess) {
        chessBoard.setChess(chess.getRow(), chess.getCol(), chess.getCondition());
    }

    // 撤回棋子（将棋盘上的某个位置清空）
    public void withdrawChess(Chess chess) {
        chessBoard.setChess(chess.getRow(), chess.getCol(), ChessCondition.EMPTY);
    }

    // 实现 View 接口中的 setInfo 方法（这里未实现具体功能）
    @Override
    public void setInfo(Player info) {
        // 当前没有实现该方法的具体功能
    }

    private BoardCondition boardCondition = BoardCondition.NOPE;  // 棋盘状态，初始为未定义状态
    private chessBoardLocal chessBoard;  // 本地棋盘
    private RightPanel rightPanel;       // 右侧面板（显示玩家信息等）
    private TimerPanel timerPanel;       // 左侧计时器面板

    // 初始化 ViewerLocal 窗口，设置布局和面板
    public void init() {
        chessBoard = new chessBoardLocal();  // 初始化本地棋盘
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 设置关闭操作
        setLayout(new BorderLayout());  // 设置布局为 BorderLayout
        add(chessBoard, BorderLayout.CENTER);  // 将棋盘添加到中心位置
        rightPanel = new RightPanel();  // 初始化右侧面板
        RightPanel.setOnlineMode();  // 设置面板为在线模式
        add(rightPanel, BorderLayout.EAST);  // 将右侧面板添加到右边
        timerPanel = new TimerPanel();  // 初始化计时器面板
        add(timerPanel, BorderLayout.WEST);  // 将计时器面板添加到左边
        this.setSize(900, 600);  // 设置窗口大小
        setLocationRelativeTo(null);  // 窗口居中显示
        this.setVisible(true);  // 设置窗口可见
    }

    // 获取棋盘
    public chessBoardLocal getChessBoard() {
        return chessBoard;
    }

    // 设置棋盘状态
    public void setBoardCondition(BoardCondition boardCondition) {
        this.boardCondition = boardCondition;
    }

    // 获取右侧面板中的信息面板
    public InfoPanel getInfoPanel() {
        return rightPanel.getInfoPanel();
    }

    // 弹出消息框让用户选择黑方或白方
    public ChessCondition selectSide() {
        String[] options = {"black", "white"};  // 黑方和白方选项
        int n = JOptionPane.showOptionDialog(null, "Choose your side", "Side",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (n == 0) {
            return ChessCondition.BLACK;  // 选择黑方
        } else {
            return ChessCondition.WHITE;  // 选择白方
        }
    }

    // 显示游戏结束的消息框，提示胜负
    public void showFinishBox(boolean isWinner) {
        String[] options = {"OK"};
        if (isWinner) {
            JOptionPane.showMessageDialog(null, "YOU WIN! Game Over");
        } else {
            JOptionPane.showMessageDialog(null, "YOU LOSE! Game Over");
        }
    }

    // 设置聊天框的文本内容
    public void setChatText(String text) {
        RightPanel.getChatPanel().setText(text);  // 更新右侧面板中的聊天文本
    }

    // 获取右侧面板实例
    public RightPanel getRightPanel() {
        return rightPanel;
    }

    // 重载的 showFinishBox 方法，用于显示自定义消息框
    public void showFinishBox(String s) {
        String[] options = {"OK"};
        JOptionPane.showMessageDialog(null, s);  // 显示自定义消息
    }
}
