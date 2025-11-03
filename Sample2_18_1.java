public class Sample2_18_1 {                                   // 宣告公開類別 Sample2_18_1（檔名需與類別同名）
    private static final int[] SCORES = {80, 60, 22, 50, 75};  // 以常數陣列直接初始化分數資料，避免重複賦值並提升可讀性

    public static void main(String[] args) {                   // 主程式進入點（JVM 從這裡開始執行）
        for (int i = 0; i < SCORES.length; i++) {              // 使用陣列長度作為上限，避免魔術數字（自動隨資料大小調整）
            System.out.printf("第%d個人的分數是%d分%n",          // 使用格式化輸出：語意清楚、避免字串連接開銷
                              i + 1,                            // 顯示人員序號（從 1 起算）
                              SCORES[i]);                      // 顯示對應索引的分數
        }                                                      // for 迴圈結束
    }                                                          // main 方法結束
}                                                              // 類別 Sample2_18_1 結束
