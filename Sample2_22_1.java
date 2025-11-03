public class Sample2_22_1 {                                                          // 宣告公開類別 Sample2_22_1（檔名需與類別同名）
    private static final int CHINESE = 0;                                            // 常數：國語在二維陣列中的列索引為 0
    private static final int MATH = 1;                                               // 常數：數學在二維陣列中的列索引為 1
    private static final int STUDENTS = 5;                                           // 常數：學生人數（每科都有 5 位學生）
    private static final String[] SUBJECT_NAMES = {"國語", "數學"};                   // 科目名稱陣列：用於輸出時的人類可讀標籤

    public static void main(String[] args) {                                         // 主程式進入點（JVM 從這裡開始執行）
        int[][] scores = new int[2][STUDENTS];                                       // 建立二維整數陣列：2 科 × 5 人（預設值皆為 0）

        scores[CHINESE] = new int[]{80, 60, 22, 50, 75};                             // 初始化國語成績列（索引 0）：依序填入 5 位學生分數
        scores[MATH]    = new int[]{90, 55, 68, 72, 0};                              // 初始化數學成績列（索引 1）：原題未給第 5 位，保留 0 當預設

        for (int i = 0; i < STUDENTS; i++) {                                         // 依學生索引從 0 迭代到 4（共 5 位）
            System.out.printf("第%d個人的%s分數是%d分%n",                              // 使用格式化輸出：顯示第幾位、科目名稱與分數
                              i + 1,                                                  // 第 i 位學生的人類序號（從 1 開始）
                              SUBJECT_NAMES[CHINESE],                                 // 取出國語的科目名稱
                              scores[CHINESE][i]);                                    // 取出國語對應學生的分數
            System.out.printf("第%d個人的%s分數是%d分%n",                              // 顯示同一位學生的數學分數
                              i + 1,                                                  // 同上：第 i 位學生的人類序號
                              SUBJECT_NAMES[MATH],                                    // 取出數學的科目名稱
                              scores[MATH][i]);                                       // 取出數學對應學生的分數（第 5 位為 0）
        }                                                                             // for 迴圈結束
    }                                                                                 // main 方法結束
}                                                                                     // 類別 Sample2_22_1 結束

