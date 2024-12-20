package View;

import Bean.*;
import Controller.*;
import Map.Mapper;
import Map.MessageBuffer;
import Util.SaveGameToFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    // 控制面板上的按钮
    private JButton withdrawButton;  // 撤回按钮
    private JButton quitButton;      // 退出按钮
    private JButton previousButton;  // 上一步按钮
    private JButton nextButton;      // 下一步按钮
    private JButton saveToFile;      // 保存到文件按钮

    // 控制面板的构造函数，初始化各个按钮并设置其监听器
    public ControlPanel() {
        // 设置面板的布局为 2 行 2 列的 GridLayout
        this.setLayout(new GridLayout(2, 2));
        // 设置面板的边距
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 初始化撤回按钮并为其添加事件监听器
        this.withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(java.awt.event.ActionEvent e) {
               // 如果棋盘上没有棋子，直接返回
               if(Mapper.getChesses().size() == 0) {
                   return;
               }
               Chess toSend = null;
               // 遍历所有棋子，找到当前玩家的棋子并进行撤回
               for(int i = Mapper.getChesses().size() - 1; i >= 0; i--) {
                   if(Mapper.getChesses().get(i).getCondition().equals(Controller.getSide())) {
                       toSend = Mapper.getChesses().get(i);  // 获取该棋子
                       Mapper.getChesses().remove(i);  // 从棋盘中移除该棋子
                       Viewer.getViewer().withdrawChess(toSend);  // 更新界面
                       break;
                   }
               }
               // 发送撤回消息
               MessageBuffer.getInstance().setBattleMessage(new BattleMessage(toSend, Actions.WITHDRAW, ""));
           }
        });
        this.add(this.withdrawButton);  // 将撤回按钮添加到面板中

        // 初始化退出按钮并为其添加事件监听器
        this.quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(java.awt.event.ActionEvent e) {
               // 发送退出消息
               MessageBuffer.getInstance().setBattleMessage(new BattleMessage(Actions.QUIT));
               // 设置对手为赢家
               Controller.setWinner(Controller.getEnemyPlayer());
               // 设置游戏结束
               Controller.getController().setBoardCondition(BoardCondition.FINISH);
               Controller.getController().notifyBoardPress(-1, -1);  // 通知棋盘游戏结束

               Controller.setThreadKill(true);  // 结束当前线程
           }
        });
        this.add(this.quitButton);  // 将退出按钮添加到面板中

        // 初始化保存到文件按钮并为其添加事件监听器
        this.saveToFile = new JButton("Save to File");
        saveToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // 调用 SaveGameToFile 类保存游戏数据
                SaveGameToFile.getInstance().saveGame(Controller.getCurrentPlayer(), Controller.getEnemyPlayer(), Mapper.getChesses());
            }
        });
        this.add(this.saveToFile);  // 将保存到文件按钮添加到面板中

        // 初始化上一步按钮并为其添加事件监听器
        this.previousButton = new JButton("Previous");
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // 如果棋盘上没有棋子，直接返回
                if(Mapper.getChesses().size() == 0) {
                    return;
                } else {
                    // 将最后一个棋子移到撤回队列，并从棋盘上移除
                    Chess removed = Mapper.getChesses().remove(Mapper.getChesses().size() - 1);
                    ControllerReview.getChessVector().add(removed);  // 将移除的棋子加入回放队列
                    Viewer.getViewer().withdrawChess(removed);  // 更新界面，撤回棋子
                }
            }
        });
        this.add(this.previousButton);  // 将上一步按钮添加到面板中

        // 初始化下一步按钮并为其添加事件监听器
        this.nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // 如果回放队列为空，直接返回
                if(ControllerReview.getChessVector().size() == 0) {
                    return;
                } else {
                    // 从回放队列中移除一个棋子并恢复到棋盘
                    Chess toSet = ControllerReview.getChessVector().remove(ControllerReview.getChessVector().size() - 1);
                    Mapper.getChesses().add(toSet);  // 添加回棋盘
                    Viewer.getViewer().setChess(toSet);  // 更新界面，放置棋子
                }
            }
        });
        this.add(this.nextButton);  // 将下一步按钮添加到面板中
    }

    // 设置为在线模式，启用和禁用相应的按钮
    public void setOnlineMode() {
        this.withdrawButton.setEnabled(true);  // 启用撤回按钮
        this.quitButton.setEnabled(true);     // 启用退出按钮
        this.previousButton.setEnabled(false); // 禁用上一步按钮
        this.nextButton.setEnabled(false);     // 禁用下一步按钮
    }

    // 设置为离线模式，启用和禁用相应的按钮
    public void setOfflineMode() {
        this.withdrawButton.setEnabled(false); // 禁用撤回按钮
        this.quitButton.setEnabled(false);     // 禁用退出按钮
        this.saveToFile.setEnabled(false);     // 禁用保存到文件按钮
        this.previousButton.setEnabled(true);  // 启用上一步按钮
        this.nextButton.setEnabled(true);      // 启用下一步按钮
    }
}
