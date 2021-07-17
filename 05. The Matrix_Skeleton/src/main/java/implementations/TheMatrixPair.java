package implementations;

import java.util.Stack;

public class TheMatrixPair {
    private char[][] matrix;
    private char fillChar;
    private char toBeReplaced;
    private int startRow;
    private int startCol;

    public TheMatrixPair(char[][] matrix, char fillChar, int startRow, int startCol) {
        this.matrix = matrix;
        this.fillChar = fillChar;
        this.startRow = startRow;
        this.startCol = startCol;
        this.toBeReplaced = this.matrix[this.startRow][this.startCol];
    }

    private class Pair {
        private int row;
        private int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public void solve() {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair (this.startRow, this.startCol));

        while (!stack.isEmpty()){
            Pair toPop = stack.pop();
            int row = toPop.row;
            int col = toPop.col;

            this.matrix[row][col] =  this.fillChar;
//
//            // for debug
//            System.out.println();
//            System.out.println(this.toOutputString());

            if (indexAndValueValidation(row-1, col))
                stack.push(new Pair (row-1, col));

            if (indexAndValueValidation(row+1, col))
                stack.push(new Pair (row+1, col));

            if (indexAndValueValidation(row, col-1))
                stack.push(new Pair (row, col-1));

            if (indexAndValueValidation(row, col+1))
                stack.push(new Pair (row, col+1));
        }
    }

    private boolean indexAndValueValidation(int row, int col) {
        return  row >= 0 && row < this.matrix.length &&
                col >= 0 && col < this.matrix[row].length &&
                this.matrix[row][col] == this.toBeReplaced;
    }

    public String toOutputString() {
        StringBuilder output = new StringBuilder();

        for (char[] chars : this.matrix) {
            for (char ch : chars)
                output.append(ch);
            output.append(System.lineSeparator());
        }
        return output.toString().trim();
    }
}
