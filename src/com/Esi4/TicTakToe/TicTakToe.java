package com.Esi4.TicTakToe;

import java.util.ArrayList;
import java.util.Arrays;

public class TicTakToe {
    ArrayList <Position> start = new ArrayList<>();
    ArrayList <Position> finish = new ArrayList<>();
    public final char X = 'x';
    public final char O = 'o';
    private int maxX = 0;
    private int maxO = 0;


    public enum Field{
        X('x'), O('o'), EmptyCell('.');
        private char n;
        Field(char symbols) {
            n = symbols;
        }
    }
    private enum Direction{
        hor(1, 0), ver(1, 0), dioL(1, 1), dioR(1, -1);

        private int x,y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Field[][] field;

    public TicTakToe(int x) {
        field = new Field[x][x];
        for (Field[] chars : field) {
            Arrays.fill(chars, Field.EmptyCell);
        }
    }

    public void cleanCell(int x, int y) { //очистка клетки
        field[y][x] = Field.EmptyCell;

    }

    public void turnPlayer1(int x, int y) {//Добавление крестика
        if(inside(x,y)) {
            field[y][x] = Field.X;
        }
    }

    public void turnPlayer2(int x, int y) { //Добавление нолика
        if(inside(x,y)) {
            field[y][x] = Field.O;
        }
    }

    private boolean inside(int x, int y) { //Проверка на присутствие координат ячейки внутри поля
        if (x < field.length && y < field.length && x >= 0 && y >= 0) return true;
        else return false;
    }

    private void update(Field symbols, int currentMax) { //фиксирование макс.длины
        switch(symbols) {
            case X:
                if (currentMax > maxX)
                    maxX = currentMax;
                break;
            case O:
                if (currentMax > maxO)
                    maxO = currentMax;
                break;
        }
    }

    private void dio(Direction direction, Field symbols, boolean up) { //проверка диагоналей на макс. последовательность
        int currentMax = 0;
        int startI = 0;
        int startJ = 0;
        for (int i = 1; i < field.length; i++) {
            int x = i;
            int y = 0;
            if ((direction == Direction.dioL) == up)
                y = field.length - 1;
            while (inside(x, y)) {
                if (field[x][y] == symbols) {
                    currentMax++;
                    if (currentMax == 1) {
                        startI = x;
                        startJ = y;
                    }
                    if (currentMax > maxX || currentMax > maxO) { //очистка и добалвение новых начальных и конечных координат
                        finish.clear();
                        finish.add(new Position(x, y));
                        start.clear();
                        start.add(new Position(startI, startJ));
                        startI = 0;
                        startJ = 0;
                    }
                }
                else {
                    update(symbols, currentMax);
                    currentMax = 0;
                }
                if (up) {
                    x--;
                    if(direction == Direction.dioL)
                        y--;
                    else y++;
                }
                else {
                    x++;
                    if(direction == Direction.dioL)
                        y++;
                    else y--;
                }
            }
            update(symbols, currentMax);
            currentMax = 0;
        }
        if (up) { //меняет область половины поля для нахождения макс.длины
            dio(direction, symbols, false);
        }
    }

    private void horVer(Direction direction, Field symbols) { //проверяет горизонтальные и вертикальные направления
        int currentMax = 0;
        int startI = 0;
        int startJ = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                switch (direction) {
                    case hor:
                        if(field[i][j] == symbols) {
                            currentMax++;
                            if (currentMax == 1) {
                                startI = i;
                                startJ = j;
                            }
                            if(currentMax > maxX || currentMax > maxO) {
                                finish.clear();
                                finish.add(new Position(i, j));
                                start.clear();
                                start.add(new Position(startI, startJ));
                                startI = 0;
                                startJ = 0;
                            }
                        } else {
                            update(symbols, currentMax);
                            currentMax = 0;
                        }
                        break;
                    case ver:
                        if(field[j][i] == symbols) {
                            currentMax++;
                            if (currentMax == 1) {
                                startI = i;
                                startJ = j;
                            }
                            if(currentMax > maxX || currentMax > maxO) {
                                finish.clear();
                                finish.add(new Position(i, j));
                                start.clear();
                                start.add(new Position(startI, startJ));
                                startI = 0;
                                startJ = 0;
                            }
                        }
                        else {
                            update(symbols, currentMax);
                            currentMax = 0;
                        }
                        break;
                }
            }
            update(symbols, currentMax);
            currentMax = 0;
        }
    }
    public void maxLength(Field symbols) { //запускает методы на нахождение макс.длины
        if (symbols == Field.X) {
         maxX = 0;
        }
        else maxO= 0;
        horVer(Direction.hor, symbols);
        horVer(Direction.ver, symbols);
        dio(Direction.dioL, symbols, true);
        dio(Direction.dioR, symbols, true);
    }

    public int maxX() {
        return maxX;
    }

    public int maxO() {
        return maxO;
    }

    private static class Position{ //класс для начальных и конечных координат
        final int startI;
        final int finishI;

        public Position(int startI, int finishI) {
            this.startI = startI;
            this.finishI = finishI;
        }
    }

    public String poison()  {
        return (start.get(0) +" "+ start.get(1)+ " " +finish.get(0)+ " " +finish.get(0));
    }

    public Field returnCell(int y, int x) {
        if (inside(x,y))
            return field[x][y];
        else
            throw new IllegalArgumentException("Клетка находится вне поля");
    }

}